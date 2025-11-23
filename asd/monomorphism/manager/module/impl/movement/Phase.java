package monomorphism.manager.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.move.MovementUtil;
import monomorphism.manager.module.util.time.TickTimer;

public class Phase extends Module {
   private boolean mineplexClip;
   private final TickTimer mineplexTickTimer = new TickTimer();

   public Phase() {
      super("Phase", 0, Category.Movement);
   }

   @Subscribe
   public void onUpdate(EventMotion event) {
      if (mc.aQ.H) {
         this.mineplexClip = true;
      }

      if (this.mineplexClip) {
         this.mineplexTickTimer.update();
         setMotionX(0.0D);
         setMotionZ(0.0D);
         if (this.mineplexTickTimer.hasTimePassed(3)) {
            this.mineplexTickTimer.reset();
            this.mineplexClip = false;
         } else if (this.mineplexTickTimer.hasTimePassed(1)) {
            double offset = this.mineplexTickTimer.hasTimePassed(2) ? 1.6D : 0.06D;
            double direction = MovementUtil.getDirection();
            mc.aQ.d(getPosX() + -Math.sin(direction) * offset, getPosY(), getPosZ() + Math.cos(direction) * offset);
         }

      }
   }
}
