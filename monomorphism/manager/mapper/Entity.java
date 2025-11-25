package monomorphism.manager.mapper;

import java.util.List;
import monomorphism.manager.module.util.Client;
import net.minecraft.nn;
import net.minecraft.yg;
import net.minecraft.client.cW;
import net.minecraft.client.e4;

public class Entity {
   public static float getRotationYaw() {
      return getThePlayer().f;
   }

   public static float getRotationPitch() {
      return getThePlayer().m;
   }

   public static float getMIStrafe() {
      return getThePlayer().cw.b;
   }

   public static float getMIForward() {
      return getThePlayer().cw.a;
   }

   public static double getPosX() {
      return getThePlayer().v;
   }

   public static void setPosX(double x) {
      getThePlayer().v = x;
   }

   public static double getPosY() {
      return getThePlayer().ao;
   }

   public static void setPosY(double y) {
      getThePlayer().ao = y;
   }

   public static double getPosZ() {
      return getThePlayer().r;
   }

   public static void setPosZ(double z) {
      getThePlayer().r = z;
   }

   public static double getMotionX() {
      return getThePlayer().an;
   }

   public static void setMotionX(double x) {
      getThePlayer().an = x;
   }

   public static double getMotionZ() {
      return getThePlayer().am;
   }

   public static void setMotionZ(double z) {
      getThePlayer().am = z;
   }

   public static double getPosX(nn entity) {
      return entity.v;
   }

   public static double getPosY(nn entity) {
      return entity.ao;
   }

   public static double getPosZ(nn entity) {
      return entity.r;
   }

   public static double getMotionX(nn entity) {
      return entity.an;
   }

   public static double getMotionZ(nn entity) {
      return entity.am;
   }

   public static void swingItem() {
      getThePlayer().J();
   }

   public static void sendPacket(yg<?> packetIn) {
      getThePlayer().ct.a(packetIn);
   }

   public static boolean onGround() {
      return getThePlayer().I();
   }

   public static void jump() {
      getThePlayer().E();
   }

   public static float getDistanceToEntity(nn entityIn) {
      float f = (float)(getPosX() - getPosX(entityIn));
      float f1 = (float)(getPosY() - getPosY(entityIn));
      float f2 = (float)(getPosZ() - getPosZ(entityIn));
      return (float)Math.sqrt((double)(f * f + f1 * f1 + f2 * f2));
   }

   public static List<nn> getEntityList() {
      return getTheWorld().x;
   }

   public static cW getTheWorld() {
      return Client.mc.I;
   }

   public static e4 getThePlayer() {
      return Client.mc.aQ;
   }
}
