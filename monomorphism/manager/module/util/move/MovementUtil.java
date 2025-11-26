package monomorphism.manager.module.util.move;

import java.lang.reflect.Field;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.math.MathHelper;
import net.minecraft.BlockPos;
import net.minecraft.aO;
import net.minecraft.m7;
import net.minecraft.y5;

public class MovementUtil extends Client {
   public static boolean isMoving() {
      return getMIForward() != 0.0F || getMIStrafe() != 0.0F;
   }

   public static void setSpeed(double moveSpeed, float yaw, double strafe, double forward) {
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

      if (strafe > 0.0D) {
         strafe = 1.0D;
      } else if (strafe < 0.0D) {
         strafe = -1.0D;
      }

      double mx = Math.cos(Math.toRadians((double)(yaw + 90.0F)));
      double mz = Math.sin(Math.toRadians((double)(yaw + 90.0F)));
      setMotionX(forward * moveSpeed * mx + strafe * moveSpeed * mz);
      setMotionZ(forward * moveSpeed * mz - strafe * moveSpeed * mx);
   }

   public static float getSpeed() {
      return (float)getSpeed(getMotionX(), getMotionZ());
   }

   public static double getSpeed(double motionX, double motionZ) {
      return Math.sqrt(motionX * motionX + motionZ * motionZ);
   }

   public static void setSpeed(double moveSpeed) {
      if (isMoving()) {
         setSpeed(moveSpeed, getRotationYaw(), (double)getMIStrafe(), (double)getMIForward());
      }
   }

   public static double getDirection() {
      return getDirectionRotation(getRotationYaw(), getMIStrafe(), getMIForward());
   }

   public static double getDirectionRotation(float yaw, float pStrafe, float pForward) {
      float rotationYaw = yaw;
      if (pForward < 0.0F) {
         rotationYaw = yaw + 180.0F;
      }

      float forward = 1.0F;
      if (pForward < 0.0F) {
         forward = -0.5F;
      } else if (pForward > 0.0F) {
         forward = 0.5F;
      }

      if (pStrafe > 0.0F) {
         rotationYaw -= 90.0F * forward;
      }

      if (pStrafe < 0.0F) {
         rotationYaw += 90.0F * forward;
      }

      return Math.toRadians((double)rotationYaw);
   }

   public static BlockPos getForwardBlockFromMovement(double length) {
      float yaw = (float)Math.toRadians((double)getYawFromMovement());
      BlockPos fPos = new BlockPos(getPosX() + -Math.sin((double)yaw) * length, getPosY(), getPosZ() + Math.cos((double)yaw) * length);
      return fPos;
   }

   public static float getYawFromMovement() {
      return getRotationFromPosition(getPosX() + getMotionX(), getPosZ() + getMotionZ(), getPosY())[0];
   }

   public static float[] getRotationFromPosition(double x, double z, double y) {
      double xDiff = x - getPosX();
      double zDiff = z - getPosY();
      double yDiff = y - getPosZ() - 1.2D;
      double dist = (double)MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
      float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0D / 3.141592653589793D) - 90.0F;
      float pitch = (float)(-(Math.atan2(yDiff, dist) * 180.0D / 3.141592653589793D));
      return new float[]{yaw, pitch};
   }

   public static boolean isOnGround(double height) {
      aO entityBoudingBox = null;

      try {
         Field field = m7.class.getDeclaredField("w");
         field.setAccessible(true);
         entityBoudingBox = (aO)field.get(mc.aQ);
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }

      return !getTheWorld().b(getThePlayer(), entityBoudingBox.a(0.0D, -height, 0.0D)).isEmpty();
   }

   public static y5 getVectorForRotation(float pitch, float yaw) {
      float f = MathHelper.cos(-yaw * 0.017453292F - 3.1415927F);
      float f1 = MathHelper.sin(-yaw * 0.017453292F - 3.1415927F);
      float f2 = -MathHelper.cos(-pitch * 0.017453292F);
      float f3 = MathHelper.sin(-pitch * 0.017453292F);
      return new y5((double)(f1 * f2), (double)f3, (double)(f * f2));
   }
}
