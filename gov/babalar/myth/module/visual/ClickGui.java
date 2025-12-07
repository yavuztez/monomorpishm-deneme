/*    */ package gov.babalar.myth.module.visual;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.ui.UIScreen;
/*    */ import net.minecraft.client.en;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClickGui
/*    */   extends Module
/*    */ {
/*    */   public ClickGui() {
/* 16 */     super(ModuleType.VISUAL, "ClickGui", 54);
/*    */   }
/* 18 */   public static final UIScreen INSTANCE = new UIScreen();
/*    */   
/*    */   public void onEnable() {
/* 21 */     Utility.displayGui((en)INSTANCE);
/* 22 */     toggle();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\visual\ClickGui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */