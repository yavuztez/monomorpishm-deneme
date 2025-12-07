/*    */ package monomorphism.manager.ui.elements.impl;
/*    */ 
/*    */ import monomorphism.manager.module.util.Client;
/*    */ import monomorphism.manager.module.util.render.RenderUtil;
/*    */ import monomorphism.manager.setting.Setting;
/*    */ import monomorphism.manager.ui.elements.Element;
/*    */ import monomorphism.manager.ui.elements.ModuleButton;
/*    */ 
/*    */ public class ElementComboBox
/*    */   extends Element
/*    */ {
/*    */   public ElementComboBox(ModuleButton iparent, Setting iset) {
/* 13 */     this.parent = iparent;
/* 14 */     this.set = iset;
/* 15 */     setup();
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 20 */     RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -2039584);
/* 21 */     RenderUtil.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0D, this.y + this.height, -5460820, 14737632);
/* 22 */     RenderUtil.drawOctagon((float)this.x + 8.0F, (float)this.y + 1.0F, (float)this.width - 16.0F, 14.0F, 1.0F, 6.0F, -16746560);
/* 23 */     if (this.comboextended)
/*    */     {
/* 25 */       RenderUtil.drawRect(this.x + 12.0D, this.y + 15.0D, this.x + this.width - 12.0D, this.y + this.height, -2236963);
/*    */     }
/* 27 */     Client.mc.ar.a(this.setstrg, (int)(this.x + this.width / 2.0D - (Client.mc.ar.a(this.setstrg) / 2)), (int)(this.y + 4.0D), -1, true);
/*    */     
/* 29 */     if (this.comboextended) {
/*    */       
/* 31 */       double d0 = this.y + 16.0D;
/* 32 */       for (String s : this.set.getOptions()) {
/*    */         
/* 34 */         String s1 = String.valueOf(s.substring(0, 1).toUpperCase()) + s.substring(1);
/* 35 */         Client.mc.ar.a(s1, (int)(this.x + this.width / 2.0D - (Client.mc.ar.a(s1) / 2)), (int)(d0 + 1.0D), s.equalsIgnoreCase(this.set.getValString()) ? -16746560 : -12105913);
/* 36 */         s.equalsIgnoreCase(this.set.getValString());
/* 37 */         if (mouseX < this.x || mouseX > this.x + this.width || mouseY >= d0);
/* 38 */         d0 += 13.0D;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 45 */     if (mouseButton == 0) {
/*    */       
/* 47 */       if (isButtonHovered(mouseX, mouseY)) {
/*    */         
/* 49 */         this.comboextended = !this.comboextended;
/* 50 */         return true;
/*    */       } 
/* 52 */       if (!this.comboextended)
/*    */       {
/* 54 */         return false;
/*    */       }
/* 56 */       double d0 = this.y + 16.0D;
/* 57 */       for (String s : this.set.getOptions()) {
/*    */         
/* 59 */         if (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= d0 && mouseY <= d0 + 9.0D + 2.0D) {
/*    */           
/* 61 */           if (this.clickgui != null && this.clickgui.setmgr != null)
/*    */           {
/* 63 */             this.clickgui.setmgr.getSettingByName(this.set.getName()).setValString(s.toLowerCase());
/*    */           }
/* 65 */           return true;
/*    */         } 
/* 67 */         d0 += 15.0D;
/*    */       } 
/*    */     } 
/* 70 */     return super.mouseClicked(mouseX, mouseY, mouseButton);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isButtonHovered(int mouseX, int mouseY) {
/* 75 */     return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + 15.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manage\\ui\elements\impl\ElementComboBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */