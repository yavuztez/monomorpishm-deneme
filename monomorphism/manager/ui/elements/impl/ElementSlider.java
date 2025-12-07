/*    */ package monomorphism.manager.ui.elements.impl;
/*    */ 
/*    */ import monomorphism.manager.module.util.Client;
/*    */ import monomorphism.manager.module.util.render.RenderUtil;
/*    */ import monomorphism.manager.setting.Setting;
/*    */ import monomorphism.manager.ui.elements.Element;
/*    */ import monomorphism.manager.ui.elements.ModuleButton;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class ElementSlider
/*    */   extends Element
/*    */ {
/*    */   public boolean dragging;
/*    */   
/*    */   public ElementSlider(ModuleButton iparent, Setting iset) {
/* 17 */     this.parent = iparent;
/* 18 */     this.set = iset;
/* 19 */     this.dragging = false;
/* 20 */     setup();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 25 */     double d0 = (this.set.getValDouble() - this.set.getMin()) / (this.set.getMax() - this.set.getMin());
/* 26 */     if (d0 < 0.0D)
/*    */     {
/* 28 */       d0 = 0.0D;
/*    */     }
/* 30 */     this.anim = (float)(this.anim + (d0 - this.anim) / 4.0D);
/* 31 */     double d1 = this.set.onlyInt() ? 1000.0D : 100.0D;
/* 32 */     this.anim2 = (float)(this.anim2 + (Math.round(this.set.getValDouble() * d1) / d1 - this.anim2) / 2.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 37 */     double d1, d0 = this.set.onlyInt() ? ((int)Math.round(this.anim2 * 1000.0D) / 1000.0D) : (Math.round(this.anim2 * 100.0D) / 100.0D);
/* 38 */     String s = "";
/* 39 */     if (this.set.onlyInt()) {
/*    */       
/* 41 */       int i = (int)d0;
/*    */     }
/*    */     else {
/*    */       
/* 45 */       d1 = RenderUtil.getNormalDouble(d0, 1);
/*    */     } 
/* 47 */     RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -2039584);
/* 48 */     RenderUtil.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0D, this.y + this.height, -5460820, 14737632);
/* 49 */     Client.mc.ar.a(String.valueOf(this.setstrg) + " (" + d1 + ")", (int)(this.x + 8.0D), (int)(this.y + 3.0D), -12105913);
/* 50 */     RenderUtil.drawRect(this.x + 8.0D, this.y + 11.0D, this.x + 8.0D + this.width - 16.0D, this.y + 12.5D, -15724528);
/* 51 */     RenderUtil.drawRect(this.x + 8.0D, this.y + 11.0D, (float)(this.x + 11.0D + this.anim * (this.width - 21.0D)), this.y + 12.5D, -15558688);
/* 52 */     GL11.glPushMatrix();
/* 53 */     RenderUtil.drawBorderedCircle((float)(this.x + 11.0D + this.anim * (this.width - 21.0D)), (float)(this.y + 12.0D), 3.0F, 1, -15558688, -15558688);
/* 54 */     GL11.glPopMatrix();
/* 55 */     if (this.dragging) {
/*    */       
/* 57 */       double d3 = this.set.getMax() - this.set.getMin();
/* 58 */       double d2 = this.set.getMin() + RenderUtil.clamp((mouseX - this.x + 8.0D) / (this.width - 16.0D), 0.0D, 1.0D) * d3;
/* 59 */       this.set.setValDouble(d2);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 65 */     if (mouseButton == 0 && isSliderHovered(mouseX, mouseY)) {
/*    */       
/* 67 */       this.dragging = true;
/* 68 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 72 */     return super.mouseClicked(mouseX, mouseY, mouseButton);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY, int state) {
/* 78 */     this.dragging = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSliderHovered(int mouseX, int mouseY) {
/* 83 */     return (mouseX >= this.x + 6.0D && mouseX <= this.x + 8.0D + this.width - 13.0D && mouseY >= this.y + 9.0D && mouseY <= this.y + 15.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manage\\ui\elements\impl\ElementSlider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */