/*    */ package monomorphism.manager.module.impl.visual;
/*    */ 
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.ui.ClickGUI;
/*    */ import net.minecraft.client.gL;
/*    */ 
/*    */ 
/*    */ public class ShiftGUI
/*    */   extends Module
/*    */ {
/*    */   public static gL ClickGuiScreen;
/*    */   
/*    */   public ShiftGUI() {
/* 15 */     super("Hud", 54, Category.Visual);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 20 */     super.onEnable();
/* 21 */     if (ClickGuiScreen == null) {
/* 22 */       ClickGuiScreen = (gL)new ClickGUI();
/*    */     }
/* 24 */     mc.a(ClickGuiScreen);
/* 25 */     setModuleState(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\visual\ShiftGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */