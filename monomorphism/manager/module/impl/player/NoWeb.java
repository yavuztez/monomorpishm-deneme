package monomorphism.manager.module.impl.player;

import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.ModuleManager;

public class NoWeb extends Module {
   public NoWeb() {
      super("No Web", 0, Category.Player);
   }

   public static boolean set(boolean huh) {
      return !ModuleManager.INSTANCE.getModule(NoWeb.class).isEnabled() ? huh : false;
   }
}
