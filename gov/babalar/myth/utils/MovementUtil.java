/*    */ package gov.babalar.myth.utils;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ 
/*    */ 
/*    */ public class MovementUtil
/*    */ {
/*    */   public static double getDirection() {
/*  9 */     float rotationYaw = Utility.getRotationYaw();
/*    */     
/* 11 */     float forward = Utility.getMIForward();
/* 12 */     float strafe = Utility.getMIStrafe();
/*    */     
/* 14 */     if (forward < 0.0F) {
/* 15 */       rotationYaw += 180.0F;
/*    */     }
/*    */     
/* 18 */     float yaw = (forward < 0.0F) ? 180.0F : 0.0F;
/*    */     
/* 20 */     if (strafe > 0.0F) {
/* 21 */       yaw = -90.0F;
/* 22 */     } else if (strafe < 0.0F) {
/* 23 */       yaw = 90.0F;
/*    */     } 
/*    */     
/* 26 */     return Math.toRadians((rotationYaw + yaw));
/*    */   }
/*    */   
/*    */   public static boolean isMoving() {
/* 30 */     return (Utility.getMIForward() != 0.0F || Utility.getMIStrafe() != 0.0F);
/*    */   }
/*    */   
/*    */   public static double getBaseSpeed() {
/* 34 */     return 0.2873D;
/*    */   }
/*    */   
/*    */   public static double getSpeed() {
/* 38 */     return Math.sqrt(Utility.getMotionX() * Utility.getMotionX() + Utility.getMotionZ() * Utility.getMotionZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setSpeed(double speed) {
/* 43 */     if (!isMoving())
/*    */       return; 
/* 45 */     double yaw = getDirection();
/* 46 */     Utility.setMotionX(-Math.sin(yaw) * speed);
/* 47 */     Utility.setMotionZ(Math.cos(yaw) * speed);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setSpeed(double speed, float yaw, int direction) {
/* 52 */     Utility.setMotionX(-Math.sin(Math.toRadians(yaw)) * speed * direction);
/* 53 */     Utility.setMotionZ(Math.cos(Math.toRadians(yaw)) * speed * direction);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setSpeed(double speed, double yaw, double strafe, double forward) {
/* 58 */     double motionX = forward * speed * -Math.sin(Math.toRadians(yaw)) + strafe * speed * Math.cos(Math.toRadians(yaw));
/* 59 */     double motionZ = forward * speed * Math.cos(Math.toRadians(yaw)) + strafe * speed * -Math.sin(Math.toRadians(yaw));
/*    */     
/* 61 */     Utility.setMotionX(motionX);
/* 62 */     Utility.setMotionZ(motionZ);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\MovementUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */