/*     */ package gov.babalar.myth.utils;
/*     */ 
/*     */ import gov.babalar.myth.Client;
/*     */ import gov.babalar.myth.Utility;
/*     */ import gov.babalar.myth.utils.math.MathHelper;
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.FloatBuffer;
/*     */ import java.nio.IntBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.DN;
/*     */ import net.minecraft.DZ;
/*     */ import net.minecraft.client.CC;
/*     */ import net.minecraft.client.PM;
/*     */ import net.minecraft.client.Pe;
/*     */ import net.minecraft.ct;
/*     */ import net.minecraft.uO;
/*     */ import org.lwjgl.BufferUtils;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.glu.GLU;
/*     */ import org.lwjgl.util.vector.Vector3f;
/*     */ import org.lwjgl.util.vector.Vector4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ESPUtil
/*     */ {
/*  30 */   private static final FloatBuffer windPos = BufferUtils.createFloatBuffer(4);
/*     */   
/*     */   private static IntBuffer intBuffer;
/*     */   private static FloatBuffer floatBuffer1;
/*     */   private static FloatBuffer floatBuffer2;
/*     */   
/*     */   public static void setupBuffers() {
/*  37 */     int capacity = 16;
/*  38 */     intBuffer = ByteBuffer.allocateDirect(capacity << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
/*  39 */     floatBuffer1 = ByteBuffer.allocateDirect(capacity << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
/*  40 */     floatBuffer2 = ByteBuffer.allocateDirect(capacity << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
/*     */   }
/*     */   
/*     */   public static double[] getInterpolatedPos(DZ entity) {
/*  44 */     uO timer = null;
/*     */     try {
/*  46 */       Field field = Pe.class.getDeclaredField("en");
/*  47 */       field.setAccessible(true);
/*  48 */       timer = (uO)field.get(Client.mc);
/*  49 */     } catch (Exception exception) {}
/*  50 */     PM renderManager = null;
/*     */     try {
/*  52 */       Field field = Pe.class.getDeclaredField("E");
/*  53 */       field.setAccessible(true);
/*  54 */       renderManager = (PM)field.get(Client.mc);
/*  55 */     } catch (Exception exception) {}
/*  56 */     if (timer == null || renderManager == null) {
/*     */       
/*  58 */       String s = (timer == null) ? "timer" : "renderManager";
/*  59 */       throw new NullPointerException(String.format("%s is null!!!!!", new Object[] { s }));
/*     */     } 
/*  61 */     float ticks = timer.G;
/*  62 */     double posX = Utility.getPosX((DN)entity);
/*  63 */     double posY = Utility.getPosY((DN)entity);
/*  64 */     double posZ = Utility.getPosZ((DN)entity);
/*  65 */     DZ dZ = entity;
/*  66 */     return new double[] {
/*  67 */         MathHelper.interpolate(((DN)dZ).pf, posX, ticks).doubleValue() - renderManager.Q, 
/*  68 */         MathHelper.interpolate(((DN)dZ).A, posY, ticks).doubleValue() - renderManager.w, 
/*  69 */         MathHelper.interpolate(((DN)dZ).s, posZ, ticks).doubleValue() - renderManager.X
/*     */       };
/*     */   }
/*     */   
/*     */   public static Vector4f getEntityPositionsOn2D(DN entity) {
/*  74 */     double[] renderingEntityPos = getInterpolatedPos((DZ)entity);
/*  75 */     double entityRenderWidth = entity.k.X().doubleValue() / 1.5D;
/*     */ 
/*     */ 
/*     */     
/*  79 */     ct bb = (new ct(renderingEntityPos[0] - entityRenderWidth, renderingEntityPos[1], renderingEntityPos[2] - entityRenderWidth, renderingEntityPos[0] + entityRenderWidth, renderingEntityPos[1] + entity.b + (entity.O() ? -0.3D : 0.18D), renderingEntityPos[2] + entityRenderWidth)).n(0.15D, 0.15D, 0.15D);
/*     */     
/*  81 */     List<Vector3f> vectors = Arrays.asList(new Vector3f[] { new Vector3f((float)bb.Q, (float)bb.G, (float)bb.I), new Vector3f((float)bb.Q, (float)bb.p, (float)bb.I), new Vector3f((float)bb.i, (float)bb.G, (float)bb.I), new Vector3f((float)bb.i, (float)bb.p, (float)bb.I), new Vector3f((float)bb.Q, (float)bb.G, (float)bb.J), new Vector3f((float)bb.Q, (float)bb.p, (float)bb.J), new Vector3f((float)bb.i, (float)bb.G, (float)bb.J), new Vector3f((float)bb.i, (float)bb.p, (float)bb.J) });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  91 */     Vector4f entityPos = new Vector4f(Float.MAX_VALUE, Float.MAX_VALUE, -1.0F, -1.0F);
/*  92 */     CC sr = new CC(Client.mc);
/*  93 */     for (Vector3f vector3f : vectors) {
/*  94 */       int scaleFactor = sr.t();
/*  95 */       if (scaleFactor != 2) scaleFactor = 2; 
/*  96 */       vector3f = projectOn2D(vector3f.x, vector3f.y, vector3f.z, scaleFactor);
/*  97 */       if (vector3f != null && vector3f.z >= 0.0D && vector3f.z < 1.0D) {
/*  98 */         entityPos.x = Math.min(vector3f.x, entityPos.x);
/*  99 */         entityPos.y = Math.min(vector3f.y, entityPos.y);
/* 100 */         entityPos.z = Math.max(vector3f.x, entityPos.z);
/* 101 */         entityPos.w = Math.max(vector3f.y, entityPos.w);
/*     */       } 
/*     */     } 
/* 104 */     return entityPos;
/*     */   }
/*     */   
/*     */   public static Vector3f projectOn2D(float x, float y, float z, int scaleFactor) {
/* 108 */     GL11.glGetFloat(2982, floatBuffer1);
/* 109 */     GL11.glGetFloat(2983, floatBuffer2);
/* 110 */     GL11.glGetInteger(2978, intBuffer);
/* 111 */     if (GLU.gluProject(x, y, z, floatBuffer1, floatBuffer2, intBuffer, windPos)) {
/* 112 */       return new Vector3f(windPos.get(0) / scaleFactor, (Utility.getDisplayHeight() - windPos.get(1)) / scaleFactor, windPos.get(2));
/*     */     }
/* 114 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\ESPUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */