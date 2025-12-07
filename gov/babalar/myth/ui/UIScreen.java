/*    */ package gov.babalar.myth.ui;
/*    */ 
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.ui.frame.IFrame;
/*    */ import gov.babalar.myth.ui.frame.TypeFrame;
/*    */ import java.io.IOException;
/*    */ import net.minecraft.client.en;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UIScreen
/*    */   extends en
/*    */ {
/* 22 */   public static final IFrame[] frames = new IFrame[] { (IFrame)new TypeFrame(100, 100, ModuleType.COMBAT), (IFrame)new TypeFrame(250, 100, ModuleType.MOTION), (IFrame)new TypeFrame(400, 100, ModuleType.VISUAL), (IFrame)new TypeFrame(550, 100, ModuleType.MISC) };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void Z(int i, int i1, float v) {
/* 34 */     super.Z(i, i1, v);
/* 35 */     for (IFrame frame : frames) {
/* 36 */       frame.drawScreen(i, i1, v);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void R(int i, int i1, int i2) {
/* 46 */     super.R(i, i1, i2);
/* 47 */     for (IFrame frame : frames) {
/* 48 */       frame.mouseReleased(i, i1, i2);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void P(char c, int i) throws IOException {
/* 57 */     super.P(c, i);
/* 58 */     for (IFrame frame : frames) {
/* 59 */       frame.keyTyped(c, i);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void g(int i, int i1, int i2) throws IOException {
/* 68 */     super.g(i, i1, i2);
/* 69 */     for (IFrame frame : frames)
/* 70 */       frame.mouseClicked(i, i1, i2); 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\UIScreen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */