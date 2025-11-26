package monomorphism.manager.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.move.MovementUtil;

public class Strafe extends Module {
   public Strafe() {
      super("Strafe", 0, Category.Movement);
   }

   @Subscribe
   public void onMotion(EventMotion event) {
      MovementUtil.setSpeed((double)MovementUtil.getSpeed());
   }
}
