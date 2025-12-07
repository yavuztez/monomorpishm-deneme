/*    */ package monomorphism.manager.ui.elements;
/*    */ 
/*    */ import monomorphism.manager.module.util.Client;
/*    */ import monomorphism.manager.setting.Setting;
/*    */ import monomorphism.manager.ui.ClickGUI;
/*    */ 
/*    */ 
/*    */ public class Element
/*    */ {
/*    */   public ClickGUI clickgui;
/*    */   public ModuleButton parent;
/*    */   public Setting set;
/*    */   public double offset;
/*    */   public double x;
/*    */   public double y;
/*    */   public double width;
/*    */   public double height;
/*    */   public float anim;
/*    */   public float anim2;
/*    */   public String setstrg;
/*    */   public boolean comboextended;
/*    */   
/*    */   public void setup() {
/* 24 */     this.clickgui = this.parent.parent.clickgui;
/* 25 */     this.anim = 0.0F;
/* 26 */     this.anim2 = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick() {}
/*    */   
/*    */   public void update() {
/* 33 */     this.x = this.parent.x + this.parent.width + 2.0D;
/* 34 */     this.y = this.parent.y + this.offset;
/* 35 */     this.width = this.parent.width;
/* 36 */     this.height = 16.0D;
/* 37 */     String s = this.set.getSimpleName();
/* 38 */     if (this.set.isCheck()) {
/*    */       
/* 40 */       this.setstrg = String.valueOf(s.substring(0, 1).toUpperCase()) + s.substring(1);
/* 41 */       double d0 = this.x + this.width - Client.mc.ar.a(this.setstrg);
/*    */       
/* 43 */       if (d0 < this.x + 13.0D)
/*    */       {
/* 45 */         this.width += this.x + 13.0D - d0 + 1.0D;
/*    */       }
/*    */     }
/* 48 */     else if (this.set.isCombo()) {
/*    */       
/* 50 */       this.height = this.comboextended ? ((this.set.getOptions().size() * 12) + this.height) : this.height;
/* 51 */       this.setstrg = String.valueOf(s.substring(0, 1).toUpperCase()) + s.substring(1);
/* 52 */       int j = Client.mc.ar.a(this.setstrg);
/* 53 */       for (String s1 : this.set.getOptions()) {
/*    */         
/* 55 */         int i = Client.mc.ar.a(s1);
/* 56 */         if (i > j)
/*    */         {
/* 58 */           j = i;
/*    */         }
/*    */       } 
/* 61 */       double d1 = this.x + this.width - j;
/* 62 */       if (d1 < this.x)
/*    */       {
/* 64 */         this.width += this.x - d1 + 1.0D;
/*    */       }
/*    */     }
/* 67 */     else if (this.set.isSlider()) {
/*    */       
/* 69 */       this.setstrg = String.valueOf(s.substring(0, 1).toUpperCase()) + s.substring(1);
/* 70 */       double d1 = Math.round(this.set.getMax() * 100.0D) / 100.0D;
/* 71 */       double d2 = this.x + this.width - Client.mc.ar.a(this.setstrg) - Client.mc.ar.a(d1) - 4.0D;
/* 72 */       if (d2 < this.x)
/*    */       {
/* 74 */         this.width += this.x - d2 + 1.0D;
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {}
/*    */   
/*    */   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 83 */     return isHovered(mouseX, mouseY);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY, int state) {}
/*    */   
/*    */   public boolean isHovered(int mouseX, int mouseY) {
/* 90 */     return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manage\\ui\elements\Element.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */