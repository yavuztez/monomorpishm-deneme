package monomorphism.manager.module.impl.movement;

import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.setting.SettingsManager;

public class VClipDown extends Module {
   public VClipDown() {
      super("Phase Up", 0, Category.Movement);
      SettingsManager.manager.addDouble("Value", "downValue", 0.0D, 10.0D, 2.0D, this);
   }

   public void onEnable() {
      mc.aQ.d(getPosX(), getPosY() + SettingsManager.manager.getSettingByName("downValue").getValDouble(), getPosZ());
      this.toggle();
   }
}
