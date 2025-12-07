/*    */ package gov.babalar.myth.utils;
/*    */ 
/*    */ import org.apache.commons.lang3.RandomStringUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class HWIDUtils
/*    */ {
/*    */   private static String hwid;
/*    */   
/*    */   public void generateHWID() {
/* 12 */     hwid = RandomStringUtils.randomAlphabetic(12);
/*    */   }
/*    */   
/*    */   public static String getHwid() {
/* 16 */     return hwid;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\HWIDUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */