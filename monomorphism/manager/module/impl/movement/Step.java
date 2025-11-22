package monomorphism.manager.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.Client;

public class Step extends Module {
   public Step() {
      super("Step", 0, Category.Movement);
   }

   @Subscribe
   public void onMotion(EventMotion event) {
      Client.mc.aQ.I = 1.0F;
   }

   public void onDisable() {
      Client.mc.aQ.I = 0.5F;
      super.onDisable();
   }
}
