package monomorphism.manager.module.impl.movement;

import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.setting.SettingsManager;

public class VClipUp extends Module {
   public VClipUp() {
      super("Phase Down", 0, Category.Movement);
      SettingsManager.manager.addDouble("Value", "upValue", 0.0D, 10.0D, 2.0D, this);
   }

   public void onEnable() {
      mc.aQ.d(getPosX(), getPosY() - SettingsManager.manager.getSettingByName("upValue").getValDouble(), getPosZ());
      this.toggle();
   }
}
