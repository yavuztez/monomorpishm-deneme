package monomorphism.manager.module.impl.world;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.time.TimerUtil;
import net.minecraft.o6;
import net.minecraft.oG;
import net.minecraft.oZ;
import net.minecraft.og;
import net.minecraft.p6;
import net.minecraft.pK;
import net.minecraft.pQ;
import net.minecraft.q_;
import net.minecraft.vR;

public class ChestStealer extends Module {
   public ChestStealer() {
      super("Stealer", 0, Category.World);
   }

   @Subscribe
   public void onUpdate(EventMotion event) {
      if (mc.aQ.ca != null && mc.aQ.ca instanceof vR) {
         vR ew = (vR)mc.aQ.ca;

         for(int k = 0; k < ew.a().b(); ++k) {
            if (ew.a().b(k) != null && this.itemWhitelisted(ew.a().b(k)) && TimerUtil.delayTimer(9.0D)) {
               mc.Q.a(ew.e, k, 0, 1, mc.aQ);
               TimerUtil.resetTimer();
            }
         }
      }

   }

   private boolean itemWhitelisted(q_ itemStack) {
      o6 item = itemStack.a();
      return item instanceof oZ || item instanceof pK || item instanceof oG || item instanceof p6 || item instanceof pQ || item == og.bD || item == og.bV || item == og.bU;
   }
}
