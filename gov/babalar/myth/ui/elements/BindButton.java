/*    */ package gov.babalar.myth.ui.elements;
/*    */ 
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.ui.frame.TypeFrame;
/*    */ import java.awt.Color;
/*    */ import org.lwjgl.input.Keyboard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BindButton
/*    */   extends ClickableElement
/*    */ {
/*    */   public Module module;
/*    */   public boolean hovering;
/*    */   
/*    */   public BindButton(int x, int y, Module module) {
/* 19 */     super(x, y);
/* 20 */     this.module = module;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getText() {
/* 25 */     return this.hovering ? "Listening..." : ("Bind: " + Keyboard.getKeyName(this.module.getKey()));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getHeight() {
/* 30 */     return 15;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clicked() {
/* 35 */     this.hovering = !this.hovering;
/*    */   }
/*    */ 
/*    */   
/*    */   public void keyTyped(char typedChar, int keyCode) {
/* 40 */     super.keyTyped(typedChar, keyCode);
/* 41 */     if (this.hovering) {
/*    */       
/* 43 */       if (typedChar == '0') {
/* 44 */         this.module.setKey(0);
/*    */       } else {
/* 46 */         this.module.setKey(keyCode);
/* 47 */       }  this.hovering = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Color getBackground() {
/* 53 */     return TypeFrame.NATURAL;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\elements\BindButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */