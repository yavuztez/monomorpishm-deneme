package monomorphism.manager.module.util.bypass;

import com.google.gson.annotations.Expose;

public class JsonSOGameRequest {
    @Expose
    private String minecraftCHC;
    @Expose
    private String launcherCHC;
    @Expose
    private String token;
    @Expose
    private String suid;
    @Expose
    private String sig;

    public JsonSOGameRequest(String minecraftCHC, String launcherCHC, String token, String suid, String sig) {
        this.minecraftCHC = minecraftCHC;
        this.launcherCHC = launcherCHC;
        this.token = token;
        this.suid = suid;
        this.sig = sig;
    }
}