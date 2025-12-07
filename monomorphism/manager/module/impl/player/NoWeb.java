/*    */ package monomorphism.manager.module.impl.player;
/*    */ 
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.ModuleManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoWeb
/*    */   extends Module
/*    */ {
/*    */   public NoWeb() {
/* 14 */     super("No Web", 0, Category.Player);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean set(boolean huh) {
/* 19 */     if (!huh || !ModuleManager.INSTANCE.getModule(NoWeb.class).isEnabled()) {
/* 20 */       return huh;
/*    */     }
/* 22 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\player\NoWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */