package monomorphism.manager.module.impl.player;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import net.minecraft.fF;

public class NoFall extends Module {
   public NoFall() {
      super("No Fall", 0, Category.Player);
   }

   @Subscribe
   public void onUpdate(EventMotion event) {
      if (mc.aQ.w > 2.0F) {
         mc.aQ.ct.a(new fF(true));
      }

   }
}
