/*     */ package gov.babalar.myth.utils.gizlibipas;
/*     */ import com.google.gson.Gson;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Base64;
/*     */ import java.util.Date;
/*     */ import java.util.Random;
/*     */ import java.util.Scanner;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.spec.IvParameterSpec;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import net.minecraft.client.Pe;
/*     */ 
/*     */ public class CokGizli {
/*  20 */   public static final Gson gson = new Gson();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String yuh() {
/*  28 */     String randomC = random();
/*     */     
/*  30 */     JsonSOGameRequest soGameRequest = new JsonSOGameRequest(getMinecraftCHC(), getLauncherCHC(), "sl", getHWID(), sig(randomC));
/*  31 */     return gson.toJson(soGameRequest);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String sig(String random) {
/*     */     try {
/*  37 */       String key = randomKey(16);
/*  38 */       long now = getTime();
/*  39 */       long now500 = now + 500L;
/*  40 */       String string = random + now + random + now500 + random + hashCodes(random);
/*  41 */       SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
/*  42 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/*  43 */       cipher.init(1, secretKeySpec, new IvParameterSpec(new byte[16]));
/*  44 */       String encrypted = Base64.getEncoder().encodeToString(cipher.doFinal(string.getBytes(StandardCharsets.UTF_8)));
/*  45 */       String out = random + key + random + encrypted;
/*  46 */       return out;
/*  47 */     } catch (Exception e) {
/*     */       
/*  49 */       e.printStackTrace();
/*  50 */       Pe.Q().error(e);
/*     */       
/*  52 */       return "err";
/*     */     } 
/*     */   }
/*     */   public static String hashCodes(String random) {
/*  56 */     return "1304836502" + random + "977993101" + random + "887623108" + random + "581810360" + random + "706277948" + random + "1475134758";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String random() {
/*  61 */     return ((new Random()).nextInt(2) == 1) ? "===" : "?=?";
/*     */   }
/*     */   
/*     */   public static long getTime() {
/*  65 */     return (new Date()).getTime();
/*     */   }
/*     */   
/*     */   public static String randomKey(int n) {
/*  69 */     return UUID.randomUUID().toString().substring(0, n);
/*     */   }
/*     */   
/*     */   public static String getMinecraftCHC() {
/*  73 */     String chc = "error";
/*     */     try {
/*  75 */       URL url = new URL("https://launcher.sonoyuncu.network/minecraft/versions/sonoyuncu/1.8.9-Optifine-Ultra_.json");
/*  76 */       BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
/*  77 */       String allLines = Arrays.toString(reader.lines().toArray());
/*     */       
/*  79 */       return allLines.substring(allLines.indexOf("\"client\": {")).split("\"sha1\": \"")[1].split("\",")[0];
/*     */ 
/*     */     
/*     */     }
/*  83 */     catch (Exception e) {
/*     */       
/*  85 */       e.printStackTrace();
/*     */       
/*  87 */       return chc;
/*     */     } 
/*     */   }
/*     */   public static String getLauncherCHC() {
/*  91 */     AtomicReference<String> chc = new AtomicReference<>("error");
/*     */     try {
/*  93 */       URL url = new URL("https://launcher.sonoyuncu.network/bootstrap.new.json");
/*  94 */       BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
/*  95 */       reader.lines().forEach(s -> {
/*     */             if (s.contains("launcher_jar_checksum")) {
/*     */               chc.set(s.split("\"launcher_jar_checksum\": \"")[1].split("\",")[0]);
/*     */             }
/*     */           });
/* 100 */       return chc.get();
/* 101 */     } catch (Exception e) {
/*     */       
/* 103 */       e.printStackTrace();
/*     */       
/* 105 */       return chc.get();
/*     */     } 
/*     */   }
/* 108 */   public static String xd = null;
/*     */   public static String getHWID() {
/* 110 */     if (xd == null) {
/*     */       try {
/* 112 */         String[] cmd = { "wmic", "DISKDRIVE", "get", "SerialNumber" };
/*     */         
/* 114 */         Process process = Runtime.getRuntime().exec(cmd);
/* 115 */         Scanner scanner = new Scanner(process.getInputStream());
/* 116 */         ArrayList<String> arrayList = new ArrayList<>();
/* 117 */         process.getOutputStream().close();
/* 118 */         while (scanner.hasNext()) {
/* 119 */           arrayList.add(scanner.next());
/*     */         }
/* 121 */         process.getInputStream().close();
/* 122 */         scanner.close();
/* 123 */         process.destroy();
/* 124 */         if (((String)arrayList.get(0)).equalsIgnoreCase("serialnumber")) {
/* 125 */           if (arrayList.size() >= 3) {
/* 126 */             String string3 = ((String)arrayList.get(1)).trim();
/* 127 */             String string2 = ((String)arrayList.get(2)).trim();
/* 128 */             xd = string3 + string2;
/*     */           } else {
/* 130 */             String string3 = ((String)arrayList.get(1)).trim();
/* 131 */             xd = string3;
/*     */           } 
/*     */         }
/* 134 */       } catch (Exception e) {
/* 135 */         e.printStackTrace();
/*     */       } 
/* 137 */       return xd;
/*     */     } 
/* 139 */     return xd;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\gizlibipas\CokGizli.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */