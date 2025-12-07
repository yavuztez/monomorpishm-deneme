/*    */ package monomorphism.manager.module.impl.movement;
/*    */ 
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.setting.SettingsManager;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VClipUp
/*    */   extends Module
/*    */ {
/*    */   public VClipUp() {
/* 13 */     super("Phase Down", 0, Category.Movement);
/* 14 */     SettingsManager.manager.addDouble("Value", "upValue", 0.0D, 10.0D, 2.0D, this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 19 */     mc.aQ.d(getPosX(), getPosY() - SettingsManager.manager.getSettingByName("upValue").getValDouble(), getPosZ());
/* 20 */     toggle();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\movement\VClipUp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */