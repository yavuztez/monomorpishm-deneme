/*     */ package gov.babalar.myth.ui.elements;
/*     */ 
/*     */ import gov.babalar.myth.Utility;
/*     */ import gov.babalar.myth.module.Module;
/*     */ import gov.babalar.myth.setting.AbstractSetting;
/*     */ import gov.babalar.myth.setting.s.SettingBool;
/*     */ import gov.babalar.myth.setting.s.SettingMode;
/*     */ import gov.babalar.myth.setting.s.SettingNumber;
/*     */ import gov.babalar.myth.ui.frame.IFrame;
/*     */ import gov.babalar.myth.ui.frame.TypeFrame;
/*     */ import gov.babalar.myth.utils.render.RoundedUtil;
/*     */ import java.awt.Color;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuleButton
/*     */   implements IFrame
/*     */ {
/*     */   public int x;
/*     */   public int y;
/*     */   public Module module;
/*     */   public int tick;
/*     */   public boolean shouldShow;
/*  29 */   public ArrayList<SettingButton> settings = new ArrayList<>();
/*     */   public ModuleButton(int x, int y, Module module) {
/*  31 */     this.x = x;
/*  32 */     this.y = y;
/*  33 */     this.module = module;
/*  34 */     for (AbstractSetting setting : module.abstractSettings) {
/*  35 */       if (setting instanceof SettingBool) {
/*  36 */         this.settings.add(new BooleanButton(0, 0, (SettingBool)setting));
/*     */       }
/*  38 */       if (setting instanceof SettingMode) {
/*  39 */         this.settings.add(new ModeButton(0, 0, (SettingMode)setting));
/*     */       }
/*  41 */       if (setting instanceof SettingNumber)
/*     */       {
/*  43 */         this.settings.add(new SliderButton(0, 0, (SettingNumber)setting));
/*     */       }
/*     */     } 
/*  46 */     this.settings.add(new BindButton(0, 0, module));
/*  47 */     syncPos();
/*     */   }
/*     */   
/*     */   public void syncPos() {
/*  51 */     int i = 0;
/*  52 */     for (SettingButton setting : this.settings) {
/*  53 */       setting.x = this.x;
/*  54 */       setting.y = this.y + 15 + i;
/*  55 */       i += setting.getHeight();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getHeight() {
/*  60 */     if (!this.shouldShow)
/*  61 */       return 15; 
/*  62 */     int i = 15;
/*  63 */     for (SettingButton setting : this.settings) {
/*  64 */       i += setting.getHeight();
/*     */     }
/*  66 */     return i;
/*     */   }
/*     */   
/*     */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/*  70 */     syncPos();
/*  71 */     if (this.module.isEnabled() && this.tick < 255)
/*  72 */       this.tick += 5; 
/*  73 */     if (!this.module.isEnabled() && this.tick > 0)
/*  74 */       this.tick -= 5; 
/*  75 */     super.drawScreen(mouseX, mouseY, partialTicks);
/*  76 */     RoundedUtil.roundedRect(this.x, this.y, 100.0D, 15.0D, 0.0D, TypeFrame.NATURAL.darker());
/*  77 */     RoundedUtil.roundedRect(this.x, this.y, 100.0D, 15.0D, 0.0D, new Color(50, 168, 164, this.tick));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     Utility.drawStringWithShadow(this.module.getName(), (this.x + 5), (this.y + 4), -1);
/*  83 */     if (this.shouldShow)
/*     */     {
/*  85 */       for (SettingButton setting : this.settings) {
/*  86 */         setting.drawScreen(mouseX, mouseY, partialTicks);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/*  93 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/*  94 */     if (mouseX > this.x && mouseX < this.x + 100 && mouseY > this.y && mouseY < this.y + 15) {
/*  95 */       switch (mouseButton) {
/*     */         case 0:
/*  97 */           this.module.toggle();
/*     */           break;
/*     */         
/*     */         case 1:
/* 101 */           this.shouldShow = !this.shouldShow;
/*     */           break;
/*     */       } 
/*     */     }
/* 105 */     if (this.shouldShow)
/*     */     {
/* 107 */       for (SettingButton setting : this.settings) {
/* 108 */         setting.mouseClicked(mouseX, mouseY, mouseButton);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
/* 115 */     super.mouseReleased(mouseX, mouseY, mouseButton);
/* 116 */     if (this.shouldShow)
/*     */     {
/* 118 */       for (SettingButton setting : this.settings) {
/* 119 */         setting.mouseReleased(mouseX, mouseY, mouseButton);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void keyTyped(char typedChar, int keyCode) {
/* 126 */     super.keyTyped(typedChar, keyCode);
/* 127 */     if (this.shouldShow)
/*     */     {
/* 129 */       for (SettingButton setting : this.settings)
/* 130 */         setting.keyTyped(typedChar, keyCode); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\elements\ModuleButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */