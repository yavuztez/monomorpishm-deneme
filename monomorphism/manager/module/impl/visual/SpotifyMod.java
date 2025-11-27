package monomorphism.manager.module.impl.visual;

import com.google.common.eventbus.Subscribe;
import com.wrapper.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import com.wrapper.spotify.model_objects.specification.ArtistSimplified;
import com.wrapper.spotify.model_objects.specification.Track;
import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import monomorphism.manager.event.EventRender2D;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.render.RenderUtil;
import monomorphism.manager.module.util.spotify.SpotifyAPI;
import monomorphism.manager.setting.SettingsManager;
import net.minecraft.client.gui.ScaledResolution; // Orijinal Minecraft sınıfı
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display; // Garantili ekran boyutu için

public class SpotifyMod extends Module {

    public SpotifyAPI api;
    private Track currentTrack;
    private CurrentlyPlayingContext currentPlayingContext;

    private float x = 30, y = 50;
    private float dragX, dragY;
    private boolean dragging;
    private float width = 160;
    private float height = 50;

    public SpotifyMod() {
        super("Spotify", 0, Category.Visual);

        ArrayList<String> colors = new ArrayList<>();
        colors.add("Green");
        colors.add("White");
        SettingsManager.manager.addMode("Color", "spotColor", "Green", colors, this);
    }

    @Override
    public void onEnable() {
        if (api == null) api = new SpotifyAPI();
        // Client ID
        api.build("73c82f00904347f88e363b03913c903e");
        api.startConnection();
        super.onEnable();
    }

    @Subscribe
    public void onRender2D(EventRender2D event) {
        if (api == null || api.currentTrack == null || api.currentPlayingContext == null) return;

        this.currentTrack = api.currentTrack;
        this.currentPlayingContext = api.currentPlayingContext;

        handleDragging();

        // Arkaplan
        RenderUtil.drawRect(x, y, x + width, y + height, new Color(20, 20, 20, 200).getRGB());

        String trackName = currentTrack.getName();
        String artistName = "";
        for (ArtistSimplified artist : currentTrack.getArtists()) {
            artistName += artist.getName() + " ";
        }

        // Yazıları Çizdirme
        // 'a' metodu çizim yapıyorsa burada sorun yok.
        Client.mc.ar.a(trackName, x + 5.0F, y + 5.0F, -1, true);
        Client.mc.ar.a(artistName, x + 5.0F, y + 18.0F, new Color(180, 180, 180).getRGB(), true);

        // Süre Hesaplama
        int progress = currentPlayingContext.getProgress_ms();
        int duration = currentTrack.getDurationMs();
        int diff = duration - progress;

        long diffSeconds = TimeUnit.MILLISECONDS.toSeconds(diff) % 60;
        long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60;
        String time = String.format("-%s:%s", diffMinutes < 10 ? "0" + diffMinutes : diffMinutes, diffSeconds < 10 ? "0" + diffSeconds : diffSeconds);

        // [DÜZELTME 1] Yazı Genişliği
        // 'a' metodu boolean döndürüyorsa, genişlik almak için getStringWidth kullanıyoruz.
        // Eğer Client.mc.ar.getStringWidth() hata verirse, Client.mc.fontRendererObj.getStringWidth() kullan.
        float timeWidth = (float)Client.mc.fontRendererObj.getStringWidth(time);

        Client.mc.ar.a(time, x + width - timeWidth - 5.0F, y + height - 12.0F, -1, true);

        // Progress Bar
        float progressBarWidth = width - 10;
        float currentProgress = progressBarWidth * ((float)progress / (float)duration);

        Color barColor = SettingsManager.manager.getSettingByName("spotColor").getValString().equals("Green")
                ? new Color(30, 215, 96) : Color.WHITE;

        RenderUtil.drawRect(x + 5, y + 35, x + 5 + progressBarWidth, y + 37, new Color(60, 60, 60).getRGB());
        RenderUtil.drawRect(x + 5, y + 35, x + 5 + currentProgress, y + 37, barColor.getRGB());
    }

    private void handleDragging() {
        if (isHovered(x, y, width, height) && Mouse.isButtonDown(0)) {
            if (!dragging) {
                dragX = x - getMouseX();
                dragY = y - getMouseY();
                dragging = true;
            }
            x = getMouseX() + dragX;
            y = getMouseY() + dragY;
        } else {
            dragging = false;
        }
    }

    // [DÜZELTME 2] Mouse Hesaplama (GARANTİLİ ÇÖZÜM)
    // Obfuscated metodlarla (mc.g(), jF.a()) uğraşmak yerine doğrudan kütüphaneleri kullanıyoruz.
    private int getMouseX() {
        // ScaledResolution'ı Minecraft içinden al
        ScaledResolution sr = new ScaledResolution(Client.mc);
        // Display.getWidth() LWJGL'den gelir, hata vermez.
        return Mouse.getX() * sr.getScaledWidth() / Display.getWidth();
    }

    private int getMouseY() {
        ScaledResolution sr = new ScaledResolution(Client.mc);
        // Display.getHeight() LWJGL'den gelir, hata vermez.
        return sr.getScaledHeight() - Mouse.getY() * sr.getScaledHeight() / Display.getHeight() - 1;
    }

    private boolean isHovered(float x, float y, float width, float height) {
        int mouseX = getMouseX();
        int mouseY = getMouseY();
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}