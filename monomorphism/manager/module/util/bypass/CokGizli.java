package monomorphism.manager.module.util.bypass;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import monomorphism.manager.module.util.Client; // Loglama için gerekebilir
import monomorphism.manager.module.util.other.HWIDUtils; // HWIDUtils importu

public class CokGizli {
    public static final Gson gson = new Gson();
    public static String xd = null;

    public static String yuh() {
        String randomC = random();
        // HWIDUtils.getHwid() null dönerse diye önce generate edelim
        if(HWIDUtils.getHwid() == null) new HWIDUtils().generateHWID();

        JsonSOGameRequest soGameRequest = new JsonSOGameRequest(getMinecraftCHC(), getLauncherCHC(), "sl", HWIDUtils.getHwid(), sig(randomC));
        return gson.toJson(soGameRequest);
    }

    public static String sig(String random) {
        try {
            String key = randomKey(16);
            long now = getTime();
            long now500 = now + 500L;
            String string = random + now + random + now500 + random + hashCodes(random);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(new byte[16]));
            String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(string.getBytes(StandardCharsets.UTF_8)));
            return random + key + random + encrypted;
        } catch (Exception var11) {
            var11.printStackTrace();
            return "err";
        }
    }

    public static String hashCodes(String random) {
        return "1304836502" + random + "977993101" + random + "887623108" + random + "581810360" + random + "706277948" + random + "1475134758";
    }

    public static String random() {
        return (new Random()).nextInt(2) == 1 ? "===" : "?=?";
    }

    public static long getTime() {
        return (new Date()).getTime();
    }

    public static String randomKey(int n) {
        return UUID.randomUUID().toString().substring(0, n);
    }

    public static String getMinecraftCHC() {
        // Orijinal koddan URL'i koruduk, gerekirse güncel json linki ile değiştirilmeli.
        try {
            URL url = new URL("https://launcher.sonoyuncu.network/minecraft/versions/sonoyuncu/1.8.9-Optifine-Ultra_.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String allLines = Arrays.toString(reader.lines().toArray());
            return allLines.substring(allLines.indexOf("\"client\": {")).split("\"sha1\": \"")[1].split("\",")[0];
        } catch (Exception var4) {
            return "error";
        }
    }

    public static String getLauncherCHC() {
        AtomicReference<String> chc = new AtomicReference<>("error");
        try {
            URL url = new URL("https://launcher.sonoyuncu.network/bootstrap.new.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            reader.lines().forEach((s) -> {
                if (s.contains("launcher_jar_checksum")) {
                    chc.set(s.split("\"launcher_jar_checksum\": \"")[1].split("\",")[0]);
                }
            });
            return chc.get();
        } catch (Exception var3) {
            return chc.get();
        }
    }
}