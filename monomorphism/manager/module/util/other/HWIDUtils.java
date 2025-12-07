/*    */ package monomorphism.manager.module.util.other;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Scanner;
/*    */ 
/*    */ public class HWIDUtils {
/*  7 */   private static String hwid = null;
/*    */   
/*    */   public static String getHwid() {
/* 10 */     if (hwid == null) {
/*    */       try {
/* 12 */         String[] cmd = { "wmic", "DISKDRIVE", "get", "SerialNumber" };
/* 13 */         Process process = Runtime.getRuntime().exec(cmd);
/* 14 */         Scanner scanner = new Scanner(process.getInputStream());
/* 15 */         ArrayList<String> arrayList = new ArrayList<>();
/* 16 */         process.getOutputStream().close();
/*    */         
/* 18 */         while (scanner.hasNext()) {
/* 19 */           arrayList.add(scanner.next());
/*    */         }
/* 21 */         process.getInputStream().close();
/* 22 */         scanner.close();
/* 23 */         process.destroy();
/*    */         
/* 25 */         if (!arrayList.isEmpty() && ((String)arrayList.get(0)).equalsIgnoreCase("serialnumber")) {
/* 26 */           if (arrayList.size() >= 3) {
/* 27 */             String string3 = ((String)arrayList.get(1)).trim();
/* 28 */             String string2 = ((String)arrayList.get(2)).trim();
/* 29 */             hwid = string3 + string2;
/* 30 */           } else if (arrayList.size() >= 2) {
/* 31 */             hwid = ((String)arrayList.get(1)).trim();
/*    */           } 
/*    */         }
/* 34 */       } catch (Exception e) {
/*    */         
/* 36 */         String randomHex = Long.toHexString(System.currentTimeMillis());
/* 37 */         hwid = "WD-WCC" + randomHex.toUpperCase();
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/* 42 */     if (hwid == null || hwid.isEmpty()) {
/* 43 */       hwid = "50026B7" + (long)(Math.random() * 1.0E9D);
/*    */     }
/*    */     
/* 46 */     return hwid;
/*    */   }
/*    */   
/*    */   public void generateHWID() {}
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\other\HWIDUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */