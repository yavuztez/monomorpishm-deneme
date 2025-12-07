/*    */ package monomorphism.manager.ui.elements.impl;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import monomorphism.manager.module.util.Client;
/*    */ import monomorphism.manager.module.util.render.RenderUtil;
/*    */ import monomorphism.manager.setting.Setting;
/*    */ import monomorphism.manager.ui.elements.Element;
/*    */ import monomorphism.manager.ui.elements.ModuleButton;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class ElementCheckBox
/*    */   extends Element
/*    */ {
/*    */   public ElementCheckBox(ModuleButton iparent, Setting iset) {
/* 16 */     this.parent = iparent;
/* 17 */     this.set = iset;
/* 18 */     setup();
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 23 */     RenderUtil.drawRect((float)this.x, (float)this.y, ((float)this.x + (float)this.width), ((float)this.y + (float)this.height), -2039584);
/* 24 */     RenderUtil.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0D, this.y + this.height, -5460820, 14737632);
/* 25 */     GL11.glPushMatrix();
/* 26 */     RenderUtil.setColor(new Color(-12105913));
/* 27 */     int j = (int)(this.x + 28.0D);
/* 28 */     int k = (int)(this.y + 5.5D);
/* 29 */     Client.mc.ar.a(this.setstrg, j, k, -12105913);
/* 30 */     GL11.glPopMatrix();
/* 31 */     RenderUtil.drawBorderedRect((float)this.x + 11.2F, (float)this.y + 7.0F, (float)this.x + 17.0F, (float)this.y + 11.0F, 1.0F, 633718213, (this.anim < 4.5F) ? -10197916 : -15558688);
/* 32 */     GL11.glPushMatrix();
/* 33 */     RenderUtil.drawBorderedCircle(((int)this.x + 11), ((int)this.y + 9), 2.0F, 1, 633718213, -15558688);
/* 34 */     GL11.glPopMatrix();
/* 35 */     GL11.glPushMatrix();
/* 36 */     RenderUtil.drawBorderedCircle(((int)this.x + 17), ((int)this.y + 9), 2.0F, 1, 633718213, -10197916);
/* 37 */     GL11.glPopMatrix();
/* 38 */     GL11.glPushMatrix();
/* 39 */     RenderUtil.enableAlpha();
/* 40 */     RenderUtil.enableBlend();
/* 41 */     if (this.anim > 0.0F) {
/*    */       
/* 43 */       GL11.glPushMatrix();
/* 44 */       RenderUtil.drawBorderedCircle((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 1, -4408132, -4342339);
/* 45 */       GL11.glPopMatrix();
/* 46 */       RenderUtil.drawCircle228((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 5, -15558688, -15558688, (int)(this.anim * 60.0F));
/*    */     }
/*    */     else {
/*    */       
/* 50 */       RenderUtil.drawBorderedCircle((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 1, -4408132, -4342339);
/*    */     } 
/* 52 */     GL11.glPopMatrix();
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {
/* 57 */     if (this.set.getValBoolean()) {
/*    */       
/* 59 */       this.anim = (float)(this.anim + 0.55D);
/*    */     }
/*    */     else {
/*    */       
/* 63 */       this.anim = (float)(this.anim - 0.55D);
/*    */     } 
/*    */     
/* 66 */     if (this.anim < 0.0F)
/*    */     {
/* 68 */       this.anim = 0.0F;
/*    */     }
/*    */     
/* 71 */     if (this.anim > 6.0F)
/*    */     {
/* 73 */       this.anim = 6.0F;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 79 */     if (mouseButton == 0 && isCheckHovered(mouseX, mouseY)) {
/*    */       
/* 81 */       this.set.setValBoolean(!this.set.getValBoolean());
/* 82 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 86 */     return super.mouseClicked(mouseX, mouseY, mouseButton);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isCheckHovered(int mouseX, int mouseY) {
/* 92 */     return (mouseX >= this.x + 7.5D && mouseX <= this.x + 21.0D && mouseY >= this.y + 7.0D && mouseY <= this.y + 14.5D);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manage\\ui\elements\impl\ElementCheckBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */