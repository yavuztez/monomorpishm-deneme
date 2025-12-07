/*    */ package gov.babalar.myth.utils.gizlibipas;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ 
/*    */ 
/*    */ public class JsonSOGameRequest
/*    */ {
/*    */   @Expose
/*    */   private String minecraftCHC;
/*    */   @Expose
/*    */   private String launcherCHC;
/*    */   @Expose
/*    */   private String token;
/*    */   @Expose
/*    */   private String suid;
/*    */   @Expose
/*    */   private String sig;
/*    */   
/*    */   public JsonSOGameRequest(String minecraftCHC, String launcherCHC, String token, String suid, String sig) {
/* 20 */     this.minecraftCHC = minecraftCHC;
/* 21 */     this.launcherCHC = launcherCHC;
/* 22 */     this.token = token;
/* 23 */     this.suid = suid;
/* 24 */     this.sig = sig;
/*    */   }
/*    */   
/*    */   public String getMinecraftChecksum() {
/* 28 */     return this.minecraftCHC;
/*    */   }
/*    */   
/*    */   public String getLauncherChecksum() {
/* 32 */     return this.launcherCHC;
/*    */   }
/*    */   
/*    */   public String getToken() {
/* 36 */     return this.token;
/*    */   }
/*    */   
/*    */   public String getSUID() {
/* 40 */     return this.suid;
/*    */   }
/*    */   
/*    */   public String d() {
/* 44 */     return this.sig;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\gizlibipas\JsonSOGameRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */