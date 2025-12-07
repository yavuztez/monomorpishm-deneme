/*    */ package monomorphism.manager.module.util.bypass;
/*    */ 
/*    */ import com.google.gson.annotations.Expose;
/*    */ 
/*    */ public class JsonSOGameRequest {
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
/* 18 */     this.minecraftCHC = minecraftCHC;
/* 19 */     this.launcherCHC = launcherCHC;
/* 20 */     this.token = token;
/* 21 */     this.suid = suid;
/* 22 */     this.sig = sig;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\bypass\JsonSOGameRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */