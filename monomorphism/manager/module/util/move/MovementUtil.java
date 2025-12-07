/*     */ package monomorphism.manager.module.util.move;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import monomorphism.manager.module.util.math.MathHelper;
/*     */ import net.minecraft.BlockPos;
/*     */ import net.minecraft.aO;
/*     */ import net.minecraft.m7;
/*     */ import net.minecraft.y5;
/*     */ 
/*     */ 
/*     */ public class MovementUtil
/*     */   extends Client
/*     */ {
/*     */   public static boolean isMoving() {
/*  16 */     return !(getMIForward() == 0.0F && getMIStrafe() == 0.0F);
/*     */   }
/*     */   
/*     */   public static void setSpeed(double moveSpeed, float yaw, double strafe, double forward) {
/*  20 */     if (forward != 0.0D) {
/*  21 */       if (strafe > 0.0D) {
/*  22 */         yaw += ((forward > 0.0D) ? -45 : 45);
/*  23 */       } else if (strafe < 0.0D) {
/*  24 */         yaw += ((forward > 0.0D) ? 45 : -45);
/*     */       } 
/*  26 */       strafe = 0.0D;
/*  27 */       if (forward > 0.0D) {
/*  28 */         forward = 1.0D;
/*  29 */       } else if (forward < 0.0D) {
/*  30 */         forward = -1.0D;
/*     */       } 
/*     */     } 
/*  33 */     if (strafe > 0.0D) {
/*  34 */       strafe = 1.0D;
/*  35 */     } else if (strafe < 0.0D) {
/*  36 */       strafe = -1.0D;
/*     */     } 
/*  38 */     double mx = Math.cos(Math.toRadians((yaw + 90.0F)));
/*  39 */     double mz = Math.sin(Math.toRadians((yaw + 90.0F)));
/*  40 */     setMotionX(forward * moveSpeed * mx + strafe * moveSpeed * mz);
/*  41 */     setMotionZ(forward * moveSpeed * mz - strafe * moveSpeed * mx);
/*     */   }
/*     */   public static float getSpeed() {
/*  44 */     return (float)getSpeed(getMotionX(), getMotionZ());
/*     */   }
/*     */   
/*     */   public static double getSpeed(double motionX, double motionZ) {
/*  48 */     return Math.sqrt(motionX * motionX + motionZ * motionZ);
/*     */   }
/*     */   public static void setSpeed(double moveSpeed) {
/*  51 */     if (!isMoving())
/*     */       return; 
/*  53 */     setSpeed(moveSpeed, getRotationYaw(), getMIStrafe(), getMIForward());
/*     */   }
/*     */   
/*     */   public static double getDirection() {
/*  57 */     return getDirectionRotation(getRotationYaw(), getMIStrafe(), getMIForward());
/*     */   }
/*     */   
/*     */   public static double getDirectionRotation(float yaw, float pStrafe, float pForward) {
/*  61 */     float rotationYaw = yaw;
/*     */     
/*  63 */     if (pForward < 0.0F) {
/*  64 */       rotationYaw += 180.0F;
/*     */     }
/*  66 */     float forward = 1.0F;
/*  67 */     if (pForward < 0.0F) {
/*  68 */       forward = -0.5F;
/*  69 */     } else if (pForward > 0.0F) {
/*  70 */       forward = 0.5F;
/*     */     } 
/*  72 */     if (pStrafe > 0.0F) {
/*  73 */       rotationYaw -= 90.0F * forward;
/*     */     }
/*  75 */     if (pStrafe < 0.0F) {
/*  76 */       rotationYaw += 90.0F * forward;
/*     */     }
/*  78 */     return Math.toRadians(rotationYaw);
/*     */   }
/*     */   
/*     */   public static BlockPos getForwardBlockFromMovement(double length) {
/*  82 */     float yaw = (float)Math.toRadians(getYawFromMovement());
/*  83 */     BlockPos fPos = new BlockPos(getPosX() + -Math.sin(yaw) * length, getPosY(), getPosZ() + Math.cos(yaw) * length);
/*  84 */     return fPos;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getYawFromMovement() {
/*  89 */     return getRotationFromPosition(getPosX() + getMotionX(), getPosZ() + getMotionZ(), getPosY())[0];
/*     */   }
/*     */   
/*     */   public static float[] getRotationFromPosition(double x, double z, double y) {
/*  93 */     double xDiff = x - getPosX();
/*  94 */     double zDiff = z - getPosY();
/*  95 */     double yDiff = y - getPosZ() - 1.2D;
/*     */     
/*  97 */     double dist = MathHelper.sqrt_double(xDiff * xDiff + zDiff * zDiff);
/*  98 */     float yaw = (float)(Math.atan2(zDiff, xDiff) * 180.0D / Math.PI) - 90.0F;
/*  99 */     float pitch = (float)-(Math.atan2(yDiff, dist) * 180.0D / Math.PI);
/* 100 */     return new float[] { yaw, pitch };
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isOnGround(double height) {
/* 105 */     aO entityBoudingBox = null;
/*     */     try {
/* 107 */       Field field = m7.class.getDeclaredField("w");
/* 108 */       field.setAccessible(true);
/* 109 */       entityBoudingBox = (aO)field.get(mc.aQ);
/* 110 */     } catch (Exception e) {
/* 111 */       throw new RuntimeException(e);
/*     */     } 
/* 113 */     return !getTheWorld().b((m7)getThePlayer(), entityBoudingBox.a(0.0D, -height, 0.0D)).isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public static y5 getVectorForRotation(float pitch, float yaw) {
/* 118 */     float f = MathHelper.cos(-yaw * 0.017453292F - 3.1415927F);
/* 119 */     float f1 = MathHelper.sin(-yaw * 0.017453292F - 3.1415927F);
/* 120 */     float f2 = -MathHelper.cos(-pitch * 0.017453292F);
/* 121 */     float f3 = MathHelper.sin(-pitch * 0.017453292F);
/* 122 */     return new y5((f1 * f2), f3, (f * f2));
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\move\MovementUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */