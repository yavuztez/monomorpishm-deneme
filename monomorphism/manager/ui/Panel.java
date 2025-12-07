/*     */ package monomorphism.manager.ui;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import monomorphism.manager.module.util.render.RenderUtil;
/*     */ import monomorphism.manager.ui.elements.ModuleButton;
/*     */ 
/*     */ 
/*     */ public class Panel
/*     */ {
/*     */   public String title;
/*     */   public double x;
/*     */   public double y;
/*     */   private double x2;
/*     */   private double y2;
/*     */   public double width;
/*     */   public double height;
/*     */   public double animHeight;
/*     */   public boolean dragging;
/*     */   public boolean extended;
/*     */   public boolean visible;
/*  22 */   public ArrayList<ModuleButton> Elements = new ArrayList<>();
/*     */   
/*     */   public ClickGUI clickgui;
/*     */   
/*     */   public Panel(String ititle, double ix, double iy, double iwidth, double iheight, boolean iextended, ClickGUI parent) {
/*  27 */     this.title = ititle;
/*  28 */     this.x = ix;
/*  29 */     this.y = iy;
/*  30 */     this.width = iwidth;
/*  31 */     this.height = iheight;
/*  32 */     this.extended = iextended;
/*  33 */     this.dragging = false;
/*  34 */     this.visible = true;
/*  35 */     this.clickgui = parent;
/*  36 */     setup();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setup() {}
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  43 */     if (this.visible) {
/*     */       
/*  45 */       if (this.dragging) {
/*     */         
/*  47 */         this.x = this.x2 + mouseX;
/*  48 */         this.y = this.y2 + mouseY;
/*     */       } 
/*  50 */       RenderUtil.drawOctagon((float)this.x, (float)this.y, (float)this.width, (float)this.height + 1.0F, 2.0F, 12.0F, -2039584);
/*  51 */       int k = (int)this.x + 5;
/*  52 */       int l = (int)(this.y + 10.0D);
/*  53 */       Client.mc.ar.a(this.title, k, l, -13158601);
/*  54 */       if (this.extended && !this.Elements.isEmpty()) {
/*     */         
/*  56 */         double d0 = this.y + this.height;
/*     */         
/*  58 */         for (ModuleButton modulebutton : this.Elements) {
/*     */           
/*  60 */           RenderUtil.drawRect(this.x, d0, this.x + this.width, d0 + modulebutton.height + 1.0D, -1118482);
/*  61 */           if (this.Elements.get(0) == modulebutton)
/*     */           {
/*  63 */             RenderUtil.drawGradient(this.x, d0, this.x + this.width, d0 + 10.0D, -4342339, 15658734);
/*     */           }
/*  65 */           modulebutton.x = this.x + 2.0D;
/*  66 */           modulebutton.y = d0;
/*  67 */           modulebutton.width = this.width - 4.0D;
/*  68 */           modulebutton.drawScreen(mouseX, mouseY, partialTicks);
/*  69 */           d0 += modulebutton.height + 1.0D;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
/*  77 */     if (!this.visible)
/*     */     {
/*  79 */       return false;
/*     */     }
/*  81 */     if (mouseButton == 0 && isHovered(mouseX, mouseY)) {
/*     */       
/*  83 */       this.x2 = this.x - mouseX;
/*  84 */       this.y2 = this.y - mouseY;
/*  85 */       this.dragging = true;
/*  86 */       return true;
/*     */     } 
/*  88 */     if (mouseButton == 1 && isHovered(mouseX, mouseY)) {
/*     */       
/*  90 */       this.extended = !this.extended;
/*  91 */       return true;
/*     */     } 
/*     */ 
/*     */     
/*  95 */     if (this.extended)
/*     */     {
/*  97 */       for (ModuleButton modulebutton : this.Elements) {
/*     */         
/*  99 */         if (modulebutton.mouseClicked(mouseX, mouseY, mouseButton))
/*     */         {
/* 101 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int state) {
/* 111 */     if (this.visible)
/*     */     {
/* 113 */       if (state == 0)
/*     */       {
/* 115 */         this.dragging = false;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHovered(int mouseX, int mouseY) {
/* 122 */     return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manage\\ui\Panel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */