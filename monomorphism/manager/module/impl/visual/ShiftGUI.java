package monomorphism.manager.module.impl.visual;

import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.ui.ClickGUI;
import net.minecraft.client.gL;

public class ShiftGUI extends Module {
   public static gL ClickGuiScreen;

   public ShiftGUI() {
      super("Hud", 54, Category.Visual);
   }

   public void onEnable() {
      super.onEnable();
      if (ClickGuiScreen == null) {
         ClickGuiScreen = new ClickGUI();
      }

      mc.a(ClickGuiScreen);
      this.setModuleState(false);
   }
}
