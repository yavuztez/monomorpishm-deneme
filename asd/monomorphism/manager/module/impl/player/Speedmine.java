package monomorphism.manager.module.impl.player;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;

public class Speedmine extends Module {
   public Speedmine() {
      super("Hud", 0, Category.Player);
   }

   @Subscribe
   public void onUpdate(EventMotion event) {
   }
}
