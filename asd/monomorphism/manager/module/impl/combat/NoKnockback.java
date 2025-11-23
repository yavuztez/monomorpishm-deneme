package monomorphism.manager.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventPacket;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import net.minecraft.fa;

public class NoKnockback extends Module {
   public NoKnockback() {
      super("Velocity", 44, Category.Combat);
   }

   @Subscribe
   public void onPacket(EventPacket event) {
      if (!event.send) {
         if (event.packet instanceof fa) {
            event.cancel();
         }

      }
   }
}
