package monomorphism.manager.module.impl.player;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.Client;

public class Sprint extends Module {
   public Sprint() {
      super("Sprint", 0, Category.Player);
   }

   @Subscribe
   public void onMotion(EventMotion event) {
      Client.mc.aQ.l(true);
   }
}
