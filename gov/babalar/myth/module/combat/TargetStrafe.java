/*     */ package gov.babalar.myth.module.combat;
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import gov.babalar.myth.Client;
/*     */ import gov.babalar.myth.Utility;
/*     */ import gov.babalar.myth.event.EventMotion;
/*     */ import gov.babalar.myth.event.EventRender3D;
/*     */ import gov.babalar.myth.managers.ModuleManager;
/*     */ import gov.babalar.myth.module.Module;
/*     */ import gov.babalar.myth.module.ModuleType;
/*     */ import gov.babalar.myth.utils.MovementUtil;
/*     */ import gov.babalar.myth.utils.render.RenderUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import net.minecraft.DN;
/*     */ import net.minecraft.DZ;
/*     */ import net.minecraft.Lf;
/*     */ import net.minecraft.client.PM;
/*     */ import net.minecraft.client.Pe;
/*     */ import net.minecraft.uO;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class TargetStrafe extends Module {
/*  23 */   private static int strafe = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   public TargetStrafe() {
/*  28 */     super(ModuleType.COMBAT, "TargetStrafe", 0);
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onMotion(EventMotion event) {
/*  33 */     if (canStrafe()) {
/*  34 */       if ((Utility.getThePlayer()).pQ) {
/*  35 */         strafe = -strafe;
/*     */       } else {
/*  37 */         if ((Utility.getGameSettings()).q.f()) {
/*  38 */           strafe = 1;
/*     */           
/*  40 */           if ((Utility.getGameSettings()).TZ.f()) {
/*  41 */             strafe = -1;
/*     */           }
/*     */         } 
/*  44 */         strafe();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onRender3D(EventRender3D event) {
/*  51 */     drawCircle(5.0F, -16777216);
/*  52 */     drawCircle(3.0F, -1);
/*     */   }
/*     */   
/*     */   private void drawCircle(float lineWidth, int color) {
/*  56 */     DZ entity = KillAura.target;
/*  57 */     if (entity == null) {
/*     */       return;
/*     */     }
/*  60 */     uO timer = null;
/*     */     try {
/*  62 */       Field field = Pe.class.getDeclaredField("en");
/*  63 */       field.setAccessible(true);
/*  64 */       timer = (uO)field.get(Client.mc);
/*  65 */     } catch (Exception exception) {}
/*  66 */     PM renderManager = null;
/*     */     try {
/*  68 */       Field field = Pe.class.getDeclaredField("E");
/*  69 */       field.setAccessible(true);
/*  70 */       renderManager = (PM)field.get(Client.mc);
/*  71 */     } catch (Exception exception) {}
/*  72 */     if (timer == null || renderManager == null) {
/*     */       
/*  74 */       String s = (timer == null) ? "timer" : "renderManager";
/*  75 */       throw new NullPointerException(String.format("%s is null!!!!!", new Object[] { s }));
/*     */     } 
/*  77 */     double x = entity.pf + (Utility.getPosX((DN)entity) - entity.pf) * timer.G - renderManager.Q;
/*  78 */     double y = entity.A + (Utility.getPosY((DN)entity) - entity.A) * timer.G - renderManager.w;
/*  79 */     double z = entity.s + (Utility.getPosZ((DN)entity) - entity.s) * timer.G - renderManager.X;
/*  80 */     GL11.glPushMatrix();
/*  81 */     RenderUtil.color(color, 0.11111111F);
/*  82 */     GL11.glDisable(3553);
/*  83 */     GL11.glDisable(2929);
/*  84 */     GL11.glDepthMask(false);
/*  85 */     GL11.glLineWidth(lineWidth);
/*  86 */     GL11.glEnable(3042);
/*  87 */     GL11.glEnable(2848);
/*  88 */     GL11.glBegin(3);
/*  89 */     double pi2 = 6.283185307179586D;
/*  90 */     int i = 0;
/*  91 */     while (i <= 90) {
/*  92 */       GL11.glVertex3d(x + 1.0D * Math.cos(i * pi2 / 45.0D), y, z + 1.0D * Math.sin(i * pi2 / 45.0D));
/*  93 */       i++;
/*     */     } 
/*  95 */     GL11.glEnd();
/*  96 */     GL11.glDisable(3042);
/*  97 */     GL11.glDisable(2848);
/*  98 */     GL11.glDepthMask(true);
/*  99 */     GL11.glEnable(2929);
/* 100 */     GL11.glEnable(3553);
/* 101 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 102 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static boolean strafe() {
/* 106 */     return strafe(MovementUtil.getSpeed());
/*     */   }
/*     */   
/*     */   public static boolean strafe(double moveSpeed) {
/* 110 */     if (canStrafe()) {
/* 111 */       MovementUtil.setSpeed(moveSpeed, getYaw(getPositionVector((DN)KillAura.target)), strafe, (Utility.getDistanceToEntity(KillAura.target) > 4.5D) ? 1.0D : 0.0D);
/* 112 */       return true;
/*     */     } 
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   public static Lf getPositionVector(DN ent) {
/* 118 */     return new Lf(Utility.getPosX(ent), Utility.getPosY(ent), Utility.getPosZ(ent));
/*     */   }
/*     */   
/*     */   public static float getYaw(Lf to) {
/* 122 */     float x = (float)(to.k() - Utility.getPosX());
/* 123 */     float z = (float)(to.W() - Utility.getPosZ());
/* 124 */     float var1 = (float)(StrictMath.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
/* 125 */     float rotationYaw = Utility.getRotationYaw();
/* 126 */     return rotationYaw + wrapAngleTo180_float(var1 - rotationYaw);
/*     */   }
/*     */   
/*     */   public static float wrapAngleTo180_float(float value) {
/* 130 */     if ((value %= 360.0F) >= 180.0F) {
/* 131 */       value -= 360.0F;
/*     */     }
/* 133 */     if (value < -180.0F) {
/* 134 */       value += 360.0F;
/*     */     }
/* 136 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean canStrafe() {
/* 141 */     if (!ModuleManager.INSTANCE.isToggled(TargetStrafe.class) || !MovementUtil.isMoving() || !Keyboard.isKeyDown(57)) {
/* 142 */       return false;
/*     */     }
/* 144 */     return (ModuleManager.INSTANCE.isToggled(KillAura.class) && ModuleManager.INSTANCE.isToggled(TargetStrafe.class));
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\combat\TargetStrafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */