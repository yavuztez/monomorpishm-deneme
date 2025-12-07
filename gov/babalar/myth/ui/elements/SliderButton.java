/*    */ package gov.babalar.myth.ui.elements;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.setting.s.SettingNumber;
/*    */ import gov.babalar.myth.ui.frame.TypeFrame;
/*    */ import gov.babalar.myth.utils.render.RoundedUtil;
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SliderButton
/*    */   extends SettingButton
/*    */ {
/*    */   public SettingNumber settingNumber;
/*    */   public boolean drag;
/*    */   
/*    */   public SliderButton(int x, int y, SettingNumber settingNumber) {
/* 22 */     super(x, y);
/* 23 */     this.settingNumber = settingNumber;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 28 */     return 20;
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 33 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 34 */     if (this.drag) {
/*    */       
/* 36 */       double min = this.settingNumber.min;
/* 37 */       double max = this.settingNumber.max;
/* 38 */       double inc = this.settingNumber.inc;
/* 39 */       double valAbs = (mouseX + 200) - this.x + 1.0D;
/* 40 */       double perc = valAbs / 100.0D - 2.0D;
/* 41 */       perc = Math.min(Math.max(0.0D, perc), 1.0D);
/* 42 */       double valRel = (max - min) * perc;
/* 43 */       double val = min + valRel;
/* 44 */       val = Math.round(val * 1.0D / inc) / 1.0D / inc;
/* 45 */       set(val);
/*    */     } 
/* 47 */     RoundedUtil.roundedRect(this.x, this.y, 100.0D, 20.0D, 0.0D, TypeFrame.NATURAL);
/* 48 */     double sWidth = 98.0D * (this.settingNumber.value - this.settingNumber.min) / (this.settingNumber.max - this.settingNumber.min);
/*    */     
/* 50 */     Color color = (new Color(50, 168, 164)).darker();
/* 51 */     RoundedUtil.roundedRect(this.x, (this.y + 18), (int)sWidth, 2.0D, 0.0D, color);
/* 52 */     Utility.drawStringWithShadow(this.settingNumber.name + " " + this.settingNumber.value, (this.x + 5), (this.y + 4), -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mX, int mY, int mouseButton) {
/* 57 */     super.mouseClicked(mX, mY, mouseButton);
/* 58 */     if (mX > this.x && mX < this.x + 100 && mY > this.y && mY < this.y + 20) {
/*    */       
/* 60 */       double min = this.settingNumber.min;
/* 61 */       double max = this.settingNumber.max;
/* 62 */       double inc = this.settingNumber.inc;
/* 63 */       double valAbs = (mX + 200) - this.x + 1.0D;
/* 64 */       double perc = valAbs / 100.0D - 2.0D;
/* 65 */       perc = Math.min(Math.max(0.0D, perc), 1.0D);
/* 66 */       double valRel = (max - min) * perc;
/* 67 */       double val = min + valRel;
/* 68 */       val = Math.round(val * 1.0D / inc) / 1.0D / inc;
/* 69 */       set(val);
/* 70 */       this.drag = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mX, int mY, int mouseButton) {
/* 76 */     super.mouseReleased(mX, mY, mouseButton);
/* 77 */     this.drag = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void set(double val) {
/* 83 */     this.settingNumber.value = Math.round(val * 1.0D / this.settingNumber.inc) / 1.0D / this.settingNumber.inc;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\elements\SliderButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */