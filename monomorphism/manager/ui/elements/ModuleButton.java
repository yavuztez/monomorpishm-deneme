/*     */ package monomorphism.manager.ui.elements;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import monomorphism.manager.module.Module;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import monomorphism.manager.module.util.render.RenderUtil;
/*     */ import monomorphism.manager.setting.Setting;
/*     */ import monomorphism.manager.setting.SettingsManager;
/*     */ import monomorphism.manager.ui.Panel;
/*     */ import monomorphism.manager.ui.elements.impl.ElementCheckBox;
/*     */ import monomorphism.manager.ui.elements.impl.ElementComboBox;
/*     */ import monomorphism.manager.ui.elements.impl.ElementSlider;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class ModuleButton {
/*     */   public Module func;
/*     */   public ArrayList<Element> menuelements;
/*     */   public Panel parent;
/*     */   public double x;
/*     */   public double y;
/*     */   public double width;
/*     */   public double height;
/*     */   public boolean extended = false;
/*     */   public boolean listening = false;
/*     */   
/*     */   public ModuleButton(Module ifunc, Panel pl) {
/*  28 */     this.func = ifunc;
/*  29 */     this.height = 18.0D;
/*  30 */     this.parent = pl;
/*  31 */     this.menuelements = new ArrayList<>();
/*  32 */     if (SettingsManager.manager.getSettingsByMod(ifunc) != null)
/*     */     {
/*  34 */       for (Setting setting : SettingsManager.manager.getSettingsByMod(ifunc)) {
/*     */         
/*  36 */         if (setting.isCheck()) {
/*     */           
/*  38 */           this.menuelements.add(new ElementCheckBox(this, setting)); continue;
/*     */         } 
/*  40 */         if (setting.isSlider()) {
/*     */           
/*  42 */           this.menuelements.add(new ElementSlider(this, setting)); continue;
/*     */         } 
/*  44 */         if (setting.isCombo())
/*     */         {
/*  46 */           this.menuelements.add(new ElementComboBox(this, setting));
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  54 */     if (isHovered(mouseX, mouseY))
/*     */     {
/*  56 */       RenderUtil.drawRect(this.x - 2.0D, this.y, this.x + this.width + 2.0D, this.y + this.height + 1.0D, 572466736);
/*     */     }
/*  58 */     Client.mc.ar.a(this.func.getModuleName(), (int)(this.x + 4.0D), (int)(this.y - 2.0D + this.height / 2.0D), this.func.isEnabled() ? -12895429 : -6513508);
/*  59 */     if (SettingsManager.manager.getSettingsByMod(this.func) != null) {
/*     */       
/*  61 */       GL11.glPushMatrix();
/*  62 */       int k = (int)(this.x + this.width - 6.0D);
/*  63 */       Client.mc.ar.a(">", k, (int)(this.y - 2.0D + this.height / 2.0D), this.func.isEnabled() ? -12895429 : -6513508);
/*  64 */       GL11.glPopMatrix();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
/*  70 */     if (!isHovered(mouseX, mouseY))
/*     */     {
/*  72 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  76 */     if (mouseButton == 0) {
/*     */       
/*  78 */       this.func.setModuleState(!this.func.getModuleState());
/*     */     }
/*  80 */     else if (mouseButton == 1) {
/*     */       
/*  82 */       if (this.menuelements != null && this.menuelements.size() > 0)
/*     */       {
/*  84 */         boolean flag = !this.extended;
/*  85 */         this.extended = flag;
/*     */       }
/*     */     
/*  88 */     } else if (mouseButton == 2) {
/*     */       
/*  90 */       this.listening = true;
/*     */     } 
/*  92 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean keyTyped(char typedChar, int keyCode) throws IOException {
/*  98 */     if (this.listening) {
/*     */       
/* 100 */       if (keyCode != 1) {
/*     */         
/* 102 */         this.func.setModuleKey(keyCode);
/*     */       }
/*     */       else {
/*     */         
/* 106 */         this.func.setModuleKey(0);
/*     */       } 
/* 108 */       this.listening = false;
/* 109 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHovered(int mouseX, int mouseY) {
/* 119 */     return (mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manage\\ui\elements\ModuleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */