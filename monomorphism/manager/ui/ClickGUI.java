/*     */ package monomorphism.manager.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import monomorphism.manager.module.Category;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import monomorphism.manager.setting.SettingsManager;
/*     */ import monomorphism.manager.ui.elements.Element;
/*     */ import monomorphism.manager.ui.elements.ModuleButton;
/*     */ import monomorphism.manager.ui.elements.impl.ElementSlider;
/*     */ import net.minecraft.client.gL;
/*     */ import net.minecraft.client.jF;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClickGUI
/*     */   extends gL
/*     */ {
/*     */   public static ArrayList<Panel> panels;
/*     */   public static ArrayList<Panel> rpanels;
/*  25 */   private ModuleButton mb = null;
/*  26 */   public SettingsManager setmgr = SettingsManager.manager;
/*     */   
/*     */   public ClickGUI() {
/*  29 */     panels = new ArrayList<>();
/*  30 */     double d0 = 105.0D;
/*  31 */     double d1 = 25.0D;
/*  32 */     double d2 = 10.0D;
/*  33 */     double d3 = 10.0D; byte b; int i; Category[] arrayOfCategory;
/*  34 */     for (i = (arrayOfCategory = Category.values()).length, b = 0; b < i; ) { Category category = arrayOfCategory[b];
/*  35 */       String s = String.valueOf(Character.toUpperCase(category.name().toLowerCase().charAt(0))) + category.name().toLowerCase().substring(1);
/*  36 */       panels.add(new ClickGUI$1(this, s, d2, d3, d0, d1, true, this, category));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  45 */       d2 += d0 + 5.0D; b++; }
/*     */     
/*  47 */     rpanels = new ArrayList<>();
/*  48 */     for (Panel panel : panels) {
/*  49 */       rpanels.add(panel);
/*     */     }
/*  51 */     Collections.reverse(rpanels);
/*     */   }
/*     */ 
/*     */   
/*     */   public void i() {
/*  56 */     for (Panel panel : panels) {
/*  57 */       for (ModuleButton modulebutton : panel.Elements) {
/*  58 */         for (Element element : modulebutton.menuelements) {
/*  59 */           element.tick();
/*  60 */           if (!panel.extended) {
/*  61 */             element.anim = 0.0F;
/*  62 */             element.anim2 = 0.0F;
/*     */           } 
/*  64 */           if (!modulebutton.extended) {
/*  65 */             element.anim = 0.0F;
/*  66 */             element.anim2 = 0.0F;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(int mouseX, int mouseY, float partialTicks) {
/*  74 */     a(0, 0, (new jF(this.t)).a(), (new jF(this.t)).c(), 1342177280);
/*  75 */     for (Panel panel : panels) {
/*  76 */       panel.drawScreen(mouseX, mouseY, partialTicks);
/*     */     }
/*  78 */     this.mb = null;
/*     */     
/*  80 */     label48: for (Panel panel1 : panels) {
/*  81 */       if (panel1 != null && panel1.visible && panel1.extended && panel1.Elements != null && panel1.Elements.size() > 0) {
/*  82 */         Iterator<?> iterator = panel1.Elements.iterator();
/*     */ 
/*     */         
/*  85 */         while (iterator.hasNext()) {
/*     */ 
/*     */           
/*  88 */           ModuleButton modulebutton = (ModuleButton)iterator.next();
/*  89 */           if (modulebutton.listening) {
/*     */ 
/*     */ 
/*     */             
/*  93 */             this.mb = modulebutton; break label48;
/*     */           } 
/*     */         } 
/*     */       } 
/*  97 */     }  for (Panel panel2 : panels) {
/*  98 */       if (panel2.extended && panel2.visible && panel2.Elements != null) {
/*  99 */         for (ModuleButton modulebutton1 : panel2.Elements) {
/* 100 */           if (modulebutton1.extended && modulebutton1.menuelements != null && !modulebutton1.menuelements.isEmpty()) {
/* 101 */             double d0 = 0.0D;
/* 102 */             Color color = new Color(-13350562);
/* 103 */             (new Color(color.getRed(), color.getGreen(), color.getBlue(), 170)).getRGB();
/* 104 */             for (Element element : modulebutton1.menuelements) {
/* 105 */               element.offset = d0;
/* 106 */               element.update();
/* 107 */               element.drawScreen(mouseX, mouseY, partialTicks);
/* 108 */               d0 += element.height;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 114 */     if (this.mb != null) {
/* 115 */       jF scaledresolution = new jF(this.t);
/* 116 */       a(0, 0, this.g, this.v, -872415232);
/* 117 */       GL11.glPushMatrix();
/* 118 */       Client.mc.ar.a("Press any key...", (scaledresolution.a() / 2), (scaledresolution.c() / 2 - 10), -1, true);
/* 119 */       GL11.glPopMatrix();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     super.a(mouseX, mouseY, partialTicks);
/*     */   }
/*     */   
/*     */   protected void b(int mouseX, int mouseY, int mouseButton) {
/* 131 */     if (this.mb == null) {
/* 132 */       for (Panel panel : rpanels) {
/* 133 */         if (panel.extended && panel.visible && panel.Elements != null)
/*     */         {
/* 135 */           for (ModuleButton modulebutton : panel.Elements) {
/* 136 */             if (modulebutton.extended) {
/* 137 */               Iterator<?> iterator = modulebutton.menuelements.iterator();
/*     */               
/* 139 */               while (iterator.hasNext()) {
/*     */ 
/*     */                 
/* 142 */                 Element element = (Element)iterator.next();
/* 143 */                 if (element.mouseClicked(mouseX, mouseY, mouseButton)) {
/*     */                   return;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 152 */       for (Panel panel1 : rpanels) {
/* 153 */         if (panel1.mouseClicked(mouseX, mouseY, mouseButton)) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */       try {
/* 158 */         super.b(mouseX, mouseY, mouseButton);
/* 159 */       } catch (IOException ioexception) {
/* 160 */         ioexception.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(int mouseX, int mouseY, int state) {
/* 166 */     if (this.mb == null) {
/* 167 */       for (Panel panel : rpanels) {
/* 168 */         if (panel.extended && panel.visible && panel.Elements != null) {
/* 169 */           for (ModuleButton modulebutton : panel.Elements) {
/* 170 */             if (modulebutton.extended) {
/* 171 */               for (Element element : modulebutton.menuelements) {
/* 172 */                 element.mouseReleased(mouseX, mouseY, state);
/*     */               }
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/* 178 */       for (Panel panel1 : rpanels) {
/* 179 */         panel1.mouseReleased(mouseX, mouseY, state);
/*     */       }
/* 181 */       super.a(mouseX, mouseY, state);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(char typedChar, int keyCode) {
/* 186 */     for (Panel panel : rpanels) {
/* 187 */       if (panel != null && panel.visible && panel.extended && panel.Elements != null && panel.Elements.size() > 0) {
/* 188 */         for (ModuleButton modulebutton : panel.Elements) {
/*     */           try {
/* 190 */             if (modulebutton.keyTyped(typedChar, keyCode)) {
/*     */               return;
/*     */             }
/* 193 */           } catch (IOException ioexception1) {
/* 194 */             ioexception1.printStackTrace();
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     try {
/* 200 */       super.a(typedChar, keyCode);
/* 201 */     } catch (IOException ioexception) {
/* 202 */       ioexception.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void h() {
/* 207 */     for (Panel panel : panels) {
/* 208 */       for (ModuleButton modulebutton : panel.Elements) {
/* 209 */         for (Element element : modulebutton.menuelements) {
/* 210 */           element.anim = 0.0F;
/* 211 */           element.anim2 = 0.0F;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void closeGui() {
/* 218 */     for (Panel panel : rpanels) {
/* 219 */       if (panel.extended && panel.visible && panel.Elements != null)
/* 220 */         for (ModuleButton modulebutton : panel.Elements) {
/* 221 */           if (modulebutton.extended)
/* 222 */             for (Element element : modulebutton.menuelements) {
/* 223 */               if (element instanceof ElementSlider)
/* 224 */                 ((ElementSlider)element).dragging = false; 
/*     */             }  
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manage\\ui\ClickGUI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */