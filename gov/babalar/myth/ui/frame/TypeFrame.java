/*    */ package gov.babalar.myth.ui.frame;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.managers.ModuleManager;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.ui.elements.ModuleButton;
/*    */ import gov.babalar.myth.utils.render.RoundedUtil;
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TypeFrame
/*    */   implements IFrame
/*    */ {
/*    */   public int x;
/*    */   public int y;
/*    */   public static final int width = 100;
/* 25 */   public static final Color NATURAL = new Color(34, 33, 38, 255);
/*    */   public ModuleType modType;
/*    */   public boolean dragging = false;
/* 28 */   public ArrayList<ModuleButton> modules = new ArrayList<>();
/*    */   public TypeFrame(int x, int y, ModuleType modType) {
/* 30 */     this.x = x;
/* 31 */     this.y = y;
/* 32 */     this.modType = modType;
/* 33 */     ModuleManager.INSTANCE.modules.stream().filter(module -> (module.getType() == modType)).forEach(module -> this.modules.add(new ModuleButton(0, 0, module)));
/* 34 */     int i = 0;
/* 35 */     for (ModuleButton module : this.modules) {
/* 36 */       module.x = this.x;
/* 37 */       module.y = this.y + 18 + i;
/* 38 */       i += module.getHeight();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
/* 44 */     super.drawScreen(mouseX, mouseY, partialTicks);
/* 45 */     if (this.dragging) {
/*    */       
/* 47 */       this.x = mouseX - 5;
/* 48 */       this.y = mouseY - 5;
/*    */     } 
/* 50 */     int i = 0;
/* 51 */     for (ModuleButton module : this.modules) {
/* 52 */       module.x = this.x;
/* 53 */       module.y = this.y + 18 + i;
/* 54 */       i += module.getHeight();
/*    */     } 
/* 56 */     RoundedUtil.roundedRect((this.x - 1), this.y, 102.0D, (18 + i + 4), 8.0D, NATURAL);
/* 57 */     Utility.drawCentered(this.modType.name(), (int)(this.x + 50.0F), this.y + 7, -1);
/* 58 */     for (IFrame module : this.modules) {
/* 59 */       module.drawScreen(mouseX, mouseY, partialTicks);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
/* 65 */     super.mouseClicked(mouseX, mouseY, mouseButton);
/* 66 */     this.dragging = (mouseX > this.x && mouseX < this.x + 100 && mouseY > this.y && mouseY < this.y + 18);
/* 67 */     for (IFrame module : this.modules) {
/* 68 */       module.mouseClicked(mouseX, mouseY, mouseButton);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseReleased(int mouseX, int mouseY, int mouseButton) {
/* 75 */     super.mouseReleased(mouseX, mouseY, mouseButton);
/* 76 */     this.dragging = false;
/* 77 */     for (IFrame module : this.modules) {
/* 78 */       module.mouseReleased(mouseX, mouseY, mouseButton);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyTyped(char typedChar, int keyCode) {
/* 84 */     super.keyTyped(typedChar, keyCode);
/* 85 */     for (IFrame module : this.modules)
/* 86 */       module.keyTyped(typedChar, keyCode); 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\frame\TypeFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */