package monomorphism.manager.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Field;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.event.EventRender3D;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.ModuleManager;
import monomorphism.manager.module.impl.combat.KillAura;
import monomorphism.manager.module.util.move.MovementUtil;
import monomorphism.manager.module.util.render.DecelerateAnimation;
import monomorphism.manager.module.util.render.Direction;
import net.minecraft.eV;
import net.minecraft.nn;
import net.minecraft.tF;
import net.minecraft.client.dw;
import net.minecraft.client.kr;
import net.minecraft.client.li;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class TargetStrafe extends Module {
   private static int strafe = 1;
   private final DecelerateAnimation animation;

   public TargetStrafe() {
      super("Target Strafe", 0, Category.Movement);
      this.animation = new DecelerateAnimation(250, 4.5D, Direction.FORWARDS);
   }

   @Subscribe
   public void onMotion(EventMotion event) {
      if (canStrafe()) {
         if (mc.aN.ak == 0) {
            mc.aN.ak = 1;
         }

         if (mc.aQ.H) {
            strafe = -strafe;
         } else {
            if (this.isPressed(mc.aN.p)) {
               strafe = 1;
            }

            if (this.isPressed(mc.aN.am)) {
               strafe = -1;
            }
         }
      } else if (mc.aN.ak != 0) {
         mc.aN.ak = 0;
      }

   }

   @Subscribe
   public void onRender3D(EventRender3D event) {
      if (this.animation.getEndPoint() != 4.5D) {
         this.animation.setEndPoint(4.5D);
      }

      boolean canStrafe = canStrafe();
      this.animation.setDirection(canStrafe ? Direction.FORWARDS : Direction.BACKWARDS);
      if (canStrafe || !this.animation.isDone()) {
         this.drawCircle(5.0F, -16777216);
         this.drawCircle(3.0F, -1);
      }

   }

   private void drawCircle(float lineWidth, int color) {
      nn entity = KillAura.target;
      if (entity != null) {
         eV timer = null;

         try {
            Field field = dw.class.getDeclaredField("b");
            field.setAccessible(true);
            timer = (eV)field.get(mc);
         } catch (Exception var16) {
         }

         kr renderManager = null;

         try {
            Field field = dw.class.getDeclaredField("ba");
            field.setAccessible(true);
            renderManager = (kr)field.get(mc);
         } catch (Exception var15) {
         }

         double x = entity.ai + (getPosX(entity) - entity.ai) * (double)timer.a - renderManager.n;
         double y = entity.a + (getPosY(entity) - entity.a) * (double)timer.a - renderManager.t;
         double z = entity.ah + (getPosZ(entity) - entity.ah) * (double)timer.a - renderManager.o;
         GL11.glPushMatrix();
         color(color, (float)(this.animation.getOutput() / 4.5D / 2.0D));
         GL11.glDisable(3553);
         GL11.glDisable(2929);
         GL11.glDepthMask(false);
         GL11.glLineWidth(lineWidth);
         GL11.glEnable(3042);
         GL11.glEnable(2848);
         GL11.glBegin(3);
         double pi2 = 6.283185307179586D;

         for(int i = 0; i <= 90; ++i) {
            GL11.glVertex3d(x + this.animation.getOutput() * Math.cos((double)i * pi2 / 45.0D), y, z + this.animation.getOutput() * Math.sin((double)i * pi2 / 45.0D));
         }

         GL11.glEnd();
         GL11.glDisable(3042);
         GL11.glDisable(2848);
         GL11.glDepthMask(true);
         GL11.glEnable(2929);
         GL11.glEnable(3553);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         GL11.glPopMatrix();
      }
   }

   public boolean isPressed(li in) {
      try {
         Field field = li.class.getDeclaredField("d");
         field.setAccessible(true);
         return (Boolean)field.get(in);
      } catch (Exception var3) {
         return false;
      }
   }

   public static boolean canStrafe() {
      if (ModuleManager.INSTANCE.isToggled(TargetStrafe.class) && MovementUtil.isMoving() && (ModuleManager.INSTANCE.isToggled(Flight.class) || Keyboard.isKeyDown(57))) {
         if (!ModuleManager.INSTANCE.isToggled(Speed.class) && !ModuleManager.INSTANCE.isToggled(Flight.class)) {
            return false;
         } else {
            return ModuleManager.INSTANCE.isToggled(KillAura.class) && ModuleManager.INSTANCE.isToggled(TargetStrafe.class);
         }
      } else {
         return false;
      }
   }

   public static boolean strafe() {
      return strafe((double)MovementUtil.getSpeed());
   }

   public static boolean strafe(double moveSpeed) {
      if (canStrafe()) {
         setSpeed(moveSpeed, getYaw(getPositionVector(KillAura.target)), (double)strafe, (double)((double)getDistanceToEntity(KillAura.target) <= 4.5D ? 0 : 1));
         return true;
      } else {
         return false;
      }
   }

   public static void setSpeed(double speed, float yaw, double strafe, double forward) {
      if (forward == 0.0D && strafe == 0.0D) {
         setMotionX(0.0D);
         setMotionZ(0.0D);
      } else {
         if (forward != 0.0D) {
            if (strafe > 0.0D) {
               yaw += (float)(forward > 0.0D ? -45 : 45);
            } else if (strafe < 0.0D) {
               yaw += (float)(forward > 0.0D ? 45 : -45);
            }

            strafe = 0.0D;
            if (forward > 0.0D) {
               forward = 1.0D;
            } else if (forward < 0.0D) {
               forward = -1.0D;
            }
         }

         setMotionX(forward * speed * -Math.sin(Math.toRadians((double)yaw)) + strafe * speed * Math.cos(Math.toRadians((double)yaw)));
         setMotionZ(forward * speed * Math.cos(Math.toRadians((double)yaw)) - strafe * speed * -Math.sin(Math.toRadians((double)yaw)));
      }

   }

   public static tF getPositionVector(nn ent) {
      return new tF(getPosX(ent), getPosY(ent), getPosZ(ent));
   }

   public static float getYaw(tF to) {
      float x = (float)((double)to.a() - getPosX());
      float z = (float)((double)to.c() - getPosZ());
      float var1 = (float)(StrictMath.atan2((double)z, (double)x) * 180.0D / 3.141592653589793D) - 90.0F;
      float rotationYaw = getRotationYaw();
      return rotationYaw + wrapAngleTo180_float(var1 - rotationYaw);
   }

   public static float wrapAngleTo180_float(float value) {
      value %= 360.0F;
      if (value >= 180.0F) {
         value -= 360.0F;
      }

      if (value < -180.0F) {
         value += 360.0F;
      }

      return value;
   }

   public static void color(int color, float alpha) {
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      GL11.glColor4f(r, g, b, alpha);
   }

   public void onDisable() {
      super.onDisable();
   }
}
