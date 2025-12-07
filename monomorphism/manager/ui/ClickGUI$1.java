/*    */ package monomorphism.manager.ui;
/*    */ 
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.ModuleManager;
/*    */ import monomorphism.manager.ui.elements.ModuleButton;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class ClickGUI$1
/*    */   extends Panel
/*    */ {
/*    */   ClickGUI$1(String $anonymous0, double $anonymous1, double $anonymous2, double $anonymous3, double $anonymous4, boolean $anonymous5, ClickGUI $anonymous6) {
/* 36 */     super($anonymous0, $anonymous1, $anonymous2, $anonymous3, $anonymous4, $anonymous5, $anonymous6);
/*    */   } public void setup() {
/* 38 */     for (Module function : ModuleManager.INSTANCE.getModules()) {
/* 39 */       if (function.category.equals(category))
/* 40 */         this.Elements.add(new ModuleButton(function, this)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manage\\ui\ClickGUI$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */