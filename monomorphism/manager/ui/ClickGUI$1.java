package monomorphism.manager.ui;

import java.util.Iterator;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.ModuleManager;
import monomorphism.manager.ui.elements.ModuleButton;

class ClickGUI$1 extends Panel {
   // $FF: synthetic field
   final ClickGUI this$0;
   // $FF: synthetic field
   private final Category val$category;

   ClickGUI$1(ClickGUI var1, String $anonymous0, double $anonymous1, double $anonymous2, double $anonymous3, double $anonymous4, boolean $anonymous5, ClickGUI $anonymous6, Category var13) {
      super($anonymous0, $anonymous1, $anonymous2, $anonymous3, $anonymous4, $anonymous5, $anonymous6);
      this.this$0 = var1;
      this.val$category = var13;
   }

   public void setup() {
      Iterator var2 = ModuleManager.INSTANCE.getModules().iterator();

      while(var2.hasNext()) {
         Module function = (Module)var2.next();
         if (function.category.equals(this.val$category)) {
            this.Elements.add(new ModuleButton(function, this));
         }
      }

   }
}
