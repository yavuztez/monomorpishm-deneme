package monomorphism.manager.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Field;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.time.MSTimer;
import net.minecraft.aO;
import net.minecraft.fx;
import net.minecraft.m7;

public class Flight extends Module {
   private final MSTimer groundTimer = new MSTimer();

   public Flight() {
      super("Flight", 33, Category.Movement);
   }

   @Subscribe
   public void onMotion(EventMotion event) {
      this.handleVanillaKickBypass();
      Client.mc.aQ.b7.c = true;
      Client.mc.aQ.b7.f.a(true);
      Client.mc.aQ.b7.h.a(true);
      Client.mc.aQ.b7.g = true;
      Client.mc.aQ.T = 0.0D;
      Client.mc.aQ.ag = true;
      Client.mc.aQ.b7.a(0.25F);
   }

   public void onDisable() {
      Client.mc.aQ.b7.c = false;
      Client.mc.aQ.b7.f.a(false);
      Client.mc.aQ.b7.h.a(false);
      Client.mc.aQ.b7.g = false;
      super.onDisable();
   }

   private void handleVanillaKickBypass() {
      if (this.groundTimer.hasTimePassed(1000L)) {
         double ground = this.calculateGround();
         float f = getThePlayer().w - getThePlayer().at;

         double posY;
         for(posY = getPosY(); posY > ground; posY -= 8.0D) {
            sendPacket(new fx(getPosX(), posY, getPosZ(), true, f));
            if (posY - 8.0D < ground) {
               break;
            }
         }

         sendPacket(new fx(getPosX(), ground, getPosZ(), true, f));

         for(posY = ground; posY < getPosY(); posY += 8.0D) {
            sendPacket(new fx(getPosX(), posY, getPosZ(), true, f));
            if (posY + 8.0D > getPosY()) {
               break;
            }
         }

         sendPacket(new fx(getPosX(), getPosY(), getPosZ(), true, f));
         this.groundTimer.reset();
      }
   }

   private double calculateGround() {
      aO playerBoundingBox = null;

      try {
         Field field = m7.class.getDeclaredField("ar");
         field.setAccessible(true);
         playerBoundingBox = (aO)field.get(mc.aQ);
      } catch (Exception var7) {
      }

      double blockHeight = 1.0D;

      for(double ground = getPosY(); ground > 0.0D; ground -= blockHeight) {
         aO customBox = new aO(playerBoundingBox.c, ground + blockHeight, playerBoundingBox.h, playerBoundingBox.f, ground, playerBoundingBox.g);
         if (mc.I.b(customBox)) {
            if (blockHeight <= 0.05D) {
               return ground + blockHeight;
            }

            ground += blockHeight;
            blockHeight = 0.05D;
         }
      }

      return 0.0D;
   }
}
