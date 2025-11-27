package monomorphism.manager.module.util.spotify;

import com.sun.net.httpserver.HttpServer;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.specification.Track;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.pkce.AuthorizationCodePKCERequest;

import java.awt.Desktop;
import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class SpotifyAPI {
    private final static String CODE_CHALLENGE = "w6iZIj99vHGtEx_NVl9u3sthTN646vvkiP8OMCGfPmo";
    private final static String CODE_VERIFIER = "NlJx4kD4opk4HY7zBM6WfUHxX7HoF8A2TUhOIPGA74w";
    private final static File CLIENT_ID_DIR = new File(System.getProperty("user.home"), "SpotifyID.json");

    private int tokenRefreshInterval = 2;
    public SpotifyApi spotifyApi;
    public AuthorizationCodeUriRequest authCodeUriRequest;
    public Track currentTrack;
    public CurrentlyPlayingContext currentPlayingContext;
    public boolean authenticated;
    private HttpServer callbackServer;

    interface SpotifyCallBack {
        void codeCallback(final String code);
    }

    private final SpotifyCallBack callback = code -> {
        System.out.println("[Spotify] Bağlanıyor...");

        try {
            AuthorizationCodePKCERequest authCodePKCERequest = spotifyApi.authorizationCodePKCE(code, CODE_VERIFIER).build();
            final AuthorizationCodeCredentials authCredentials = authCodePKCERequest.execute();

            spotifyApi.setAccessToken(authCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authCredentials.getRefreshToken());
            tokenRefreshInterval = authCredentials.getExpiresIn();
            authenticated = true;

            // Token Yenileme Thread'i
            new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        TimeUnit.SECONDS.sleep(tokenRefreshInterval - 10);
                        try {
                            final AuthorizationCodeCredentials refreshRequest = spotifyApi.authorizationCodePKCERefresh().build().execute();
                            spotifyApi.setAccessToken(refreshRequest.getAccessToken());
                            if (refreshRequest.getRefreshToken() != null) {
                                spotifyApi.setRefreshToken(refreshRequest.getRefreshToken());
                            }
                            tokenRefreshInterval = refreshRequest.getExpiresIn();
                        } catch (IOException | SpotifyWebApiException e) {
                            System.out.println("[Spotify] Token yenileme hatası: " + e.getMessage());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();

            // Veri Çekme Thread'i
            new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1500);
                        if (spotifyApi == null || spotifyApi.getAccessToken() == null) continue;

                        try {
                            final CurrentlyPlayingContext ctx = spotifyApi.getInformationAboutUsersCurrentPlayback().build().execute();
                            if (ctx != null && ctx.getItem() != null && ctx.getItem() instanceof Track) {
                                this.currentTrack = (Track) ctx.getItem();
                                this.currentPlayingContext = ctx;
                            }
                        } catch (IOException | SpotifyWebApiException e) {
                            // Bağlantı koparsa sessiz kal
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[Spotify] Yetkilendirme hatası: " + e.getMessage());
        }
    };

    public void startConnection() {
        if (!authenticated) {
            try {
                URI uri = authCodeUriRequest.execute();
                Desktop.getDesktop().browse(uri);

                new Thread(() -> {
                    try {
                        if (callbackServer != null) {
                            callbackServer.stop(0);
                        }
                        callbackServer = HttpServer.create(new InetSocketAddress(4030), 0);
                        callbackServer.createContext("/", context -> {
                            if (context.getRequestURI().getQuery().contains("code")) {
                                String code = context.getRequestURI().getQuery().split("=")[1];
                                callback.codeCallback(code);
                                String message = "Başarılı! Oyuna dönebilirsin.";
                                context.sendResponseHeaders(200, message.length());
                                OutputStream out = context.getResponseBody();
                                out.write(message.getBytes());
                                out.close();
                            }
                            callbackServer.stop(0);
                        });
                        callbackServer.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void build(String clientID) {
        spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientID)
                .setRedirectUri(SpotifyHttpManager.makeUri("http://localhost:4030"))
                .build();

        authCodeUriRequest = spotifyApi.authorizationCodePKCEUri(CODE_CHALLENGE)
                .code_challenge_method("S256")
                .scope("user-read-playback-state user-read-playback-position user-modify-playback-state user-read-currently-playing")
                .build();
    }
}
