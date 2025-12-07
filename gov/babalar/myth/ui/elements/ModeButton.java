/*    */ package gov.babalar.myth.ui.elements;
/*    */ 
/*    */ import gov.babalar.myth.setting.s.SettingMode;
/*    */ import gov.babalar.myth.ui.frame.TypeFrame;
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModeButton
/*    */   extends ClickableElement
/*    */ {
/*    */   public SettingMode mode;
/*    */   
/*    */   public ModeButton(int x, int y, SettingMode mode) {
/* 20 */     super(x, y);
/* 21 */     this.mode = mode;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 26 */     return this.mode.name + ":" + this.mode.getMode();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 31 */     return 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked() {
/* 36 */     this.mode.switchMode();
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getBackground() {
/* 41 */     return TypeFrame.NATURAL;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\elements\ModeButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */