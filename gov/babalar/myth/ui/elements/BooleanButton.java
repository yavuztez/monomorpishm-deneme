/*    */ package gov.babalar.myth.ui.elements;
/*    */ 
/*    */ import gov.babalar.myth.setting.s.SettingBool;
/*    */ import gov.babalar.myth.ui.frame.TypeFrame;
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BooleanButton
/*    */   extends ClickableElement
/*    */ {
/*    */   public SettingBool settingBool;
/*    */   
/*    */   public BooleanButton(int x, int y, SettingBool settingBool) {
/* 18 */     super(x, y);
/* 19 */     this.settingBool = settingBool;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 24 */     return this.settingBool.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 29 */     return 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked() {
/* 34 */     this.settingBool.value = !this.settingBool.value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getBackground() {
/* 39 */     return this.settingBool.value ? (new Color(50, 168, 164)).darker() : TypeFrame.NATURAL;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\elements\BooleanButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */