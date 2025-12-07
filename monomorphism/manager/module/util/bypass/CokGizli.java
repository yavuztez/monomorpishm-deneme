/*    */ package monomorphism.manager.module.util.bypass;
/*    */ 
/*    */ import com.google.gson.Gson;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStreamReader;
/*    */ import java.net.URL;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.Arrays;
/*    */ import java.util.Base64;
/*    */ import java.util.Date;
/*    */ import java.util.Random;
/*    */ import java.util.UUID;
/*    */ import java.util.concurrent.atomic.AtomicReference;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.spec.IvParameterSpec;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ import monomorphism.manager.module.util.other.HWIDUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CokGizli
/*    */ {
/* 23 */   public static final Gson gson = new Gson();
/* 24 */   public static String xd = null;
/*    */   
/*    */   public static String yuh() {
/* 27 */     String randomC = random();
/*    */     
/* 29 */     if (HWIDUtils.getHwid() == null) (new HWIDUtils()).generateHWID();
/*    */     
/* 31 */     JsonSOGameRequest soGameRequest = new JsonSOGameRequest(getMinecraftCHC(), getLauncherCHC(), "sl", HWIDUtils.getHwid(), sig(randomC));
/* 32 */     return gson.toJson(soGameRequest);
/*    */   }
/*    */   
/*    */   public static String sig(String random) {
/*    */     try {
/* 37 */       String key = randomKey(16);
/* 38 */       long now = getTime();
/* 39 */       long now500 = now + 500L;
/* 40 */       String string = random + now + random + now500 + random + hashCodes(random);
/* 41 */       SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
/* 42 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 43 */       cipher.init(1, secretKeySpec, new IvParameterSpec(new byte[16]));
/* 44 */       String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(string.getBytes(StandardCharsets.UTF_8)));
/* 45 */       return random + key + random + encrypted;
/* 46 */     } catch (Exception var11) {
/* 47 */       var11.printStackTrace();
/* 48 */       return "err";
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String hashCodes(String random) {
/* 53 */     return "1304836502" + random + "977993101" + random + "887623108" + random + "581810360" + random + "706277948" + random + "1475134758";
/*    */   }
/*    */   
/*    */   public static String random() {
/* 57 */     return ((new Random()).nextInt(2) == 1) ? "===" : "?=?";
/*    */   }
/*    */   
/*    */   public static long getTime() {
/* 61 */     return (new Date()).getTime();
/*    */   }
/*    */   
/*    */   public static String randomKey(int n) {
/* 65 */     return UUID.randomUUID().toString().substring(0, n);
/*    */   }
/*    */ 
/*    */   
/*    */   public static String getMinecraftCHC() {
/*    */     try {
/* 71 */       URL url = new URL("https://launcher.sonoyuncu.network/minecraft/versions/sonoyuncu/1.8.9-Optifine-Ultra_.json");
/* 72 */       BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
/* 73 */       String allLines = Arrays.toString(reader.lines().toArray());
/* 74 */       return allLines.substring(allLines.indexOf("\"client\": {")).split("\"sha1\": \"")[1].split("\",")[0];
/* 75 */     } catch (Exception var4) {
/* 76 */       return "error";
/*    */     } 
/*    */   }
/*    */   
/*    */   public static String getLauncherCHC() {
/* 81 */     AtomicReference<String> chc = new AtomicReference<>("error");
/*    */     try {
/* 83 */       URL url = new URL("https://launcher.sonoyuncu.network/bootstrap.new.json");
/* 84 */       BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
/* 85 */       reader.lines().forEach(s -> {
/*    */             if (s.contains("launcher_jar_checksum")) {
/*    */               chc.set(s.split("\"launcher_jar_checksum\": \"")[1].split("\",")[0]);
/*    */             }
/*    */           });
/* 90 */       return chc.get();
/* 91 */     } catch (Exception var3) {
/* 92 */       return chc.get();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\bypass\CokGizli.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */