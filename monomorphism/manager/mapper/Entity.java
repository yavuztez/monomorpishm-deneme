/*     */ package monomorphism.manager.mapper;
/*     */ 
/*     */ import java.util.List;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import net.minecraft.client.cW;
/*     */ import net.minecraft.client.e4;
/*     */ import net.minecraft.nn;
/*     */ import net.minecraft.yg;
/*     */ 
/*     */ public class Entity
/*     */ {
/*     */   public static float getRotationYaw() {
/*  13 */     return (getThePlayer()).f;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getRotationPitch() {
/*  18 */     return (getThePlayer()).m;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getYaw(nn entity) {
/*  23 */     return entity.f;
/*     */   }
/*     */   
/*     */   public static float getMIStrafe() {
/*  27 */     return (getThePlayer()).cw.b;
/*     */   }
/*     */   
/*     */   public static float getMIForward() {
/*  31 */     return (getThePlayer()).cw.a;
/*     */   }
/*     */   
/*     */   public static double getPosX() {
/*  35 */     return (getThePlayer()).v;
/*     */   }
/*     */   
/*     */   public static void setPosX(double x) {
/*  39 */     (getThePlayer()).v = x;
/*     */   }
/*     */   
/*     */   public static double getPosY() {
/*  43 */     return (getThePlayer()).ao;
/*     */   }
/*     */   
/*     */   public static void setPosY(double y) {
/*  47 */     (getThePlayer()).ao = y;
/*     */   }
/*     */   
/*     */   public static double getPosZ() {
/*  51 */     return (getThePlayer()).r;
/*     */   }
/*     */   
/*     */   public static void setPosZ(double z) {
/*  55 */     (getThePlayer()).r = z;
/*     */   }
/*     */   
/*     */   public static double getMotionX() {
/*  59 */     return (getThePlayer()).an;
/*     */   }
/*     */   
/*     */   public static void setMotionX(double x) {
/*  63 */     (getThePlayer()).an = x;
/*     */   }
/*     */   
/*     */   public static double getMotionZ() {
/*  67 */     return (getThePlayer()).am;
/*     */   }
/*     */   
/*     */   public static void setMotionZ(double z) {
/*  71 */     (getThePlayer()).am = z;
/*     */   }
/*     */   
/*     */   public static double getPosX(nn entity) {
/*  75 */     return entity.v;
/*     */   }
/*     */   
/*     */   public static double getPosY(nn entity) {
/*  79 */     return entity.ao;
/*     */   }
/*     */   
/*     */   public static double getPosZ(nn entity) {
/*  83 */     return entity.r;
/*     */   }
/*     */   
/*     */   public static double getMotionX(nn entity) {
/*  87 */     return entity.an;
/*     */   }
/*     */   
/*     */   public static double getMotionZ(nn entity) {
/*  91 */     return entity.am;
/*     */   }
/*     */   
/*     */   public static void swingItem() {
/*  95 */     getThePlayer().J();
/*     */   }
/*     */   
/*     */   public static void sendPacket(yg<?> packetIn) {
/*  99 */     (getThePlayer()).ct.a(packetIn);
/*     */   }
/*     */   
/*     */   public static boolean onGround() {
/* 103 */     return getThePlayer().I();
/*     */   }
/*     */   
/*     */   public static void jump() {
/* 107 */     getThePlayer().E();
/*     */   }
/*     */   
/*     */   public static float getDistanceToEntity(nn entityIn) {
/* 111 */     float f = (float)(getPosX() - getPosX(entityIn));
/* 112 */     float f1 = (float)(getPosY() - getPosY(entityIn));
/* 113 */     float f2 = (float)(getPosZ() - getPosZ(entityIn));
/* 114 */     return (float)Math.sqrt((f * f + f1 * f1 + f2 * f2));
/*     */   }
/*     */   
/*     */   public static List<nn> getEntityList() {
/* 118 */     return (getTheWorld()).x;
/*     */   }
/*     */   
/*     */   public static cW getTheWorld() {
/* 122 */     return Client.mc.I;
/*     */   }
/*     */   
/*     */   public static e4 getThePlayer() {
/* 126 */     return Client.mc.aQ;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\mapper\Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */