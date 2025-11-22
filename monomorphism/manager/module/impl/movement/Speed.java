package monomorphism.manager.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import excluded.MotionY;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.move.MovementUtil;

public class Speed extends Module {
   public Speed() {
      super("TestSpeed", 21, Category.Movement);
   }

   @Subscribe
   public void onMotion(EventMotion event) {
      if (MovementUtil.isMoving()) {
         if (onGround()) {
            MovementUtil.setSpeed(1.5D);
            MotionY.degistir(0.44D);
            return;
         }

         MovementUtil.setSpeed(1.0D);
      }

      if (MovementUtil.isMoving()) {
         TargetStrafe.strafe();
      }

   }
}
