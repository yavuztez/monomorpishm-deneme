/*    */ package gov.babalar.myth.ui.elements;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.utils.render.RoundedUtil;
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ClickableElement
/*    */   extends SettingButton
/*    */ {
/*    */   public abstract String getText();
/*    */   
/*    */   public abstract void clicked();
/*    */   
/*    */   public abstract Color getBackground();
/*    */   
/*    */   public ClickableElement(int x, int y) {
/* 22 */     super(x, y);
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 27 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 28 */     RoundedUtil.roundedRect(this.x, this.y, 100.0D, 15.0D, 0.0D, getBackground());
/* 29 */     Utility.drawStringWithShadow(getText(), (this.x + 5), (this.y + 4), -1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 34 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 35 */     if (mouseX > this.x && mouseX < this.x + 100 && mouseY > this.y && mouseY < this.y + getHeight())
/* 36 */       clicked(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\elements\ClickableElement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */