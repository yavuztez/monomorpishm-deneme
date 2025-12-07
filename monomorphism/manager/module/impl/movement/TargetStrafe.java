/*     */ package monomorphism.manager.module.impl.movement;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import java.lang.reflect.Field;
/*     */ import monomorphism.manager.event.EventMotion;
/*     */ import monomorphism.manager.event.EventRender3D;
/*     */ import monomorphism.manager.module.Category;
/*     */ import monomorphism.manager.module.Module;
/*     */ import monomorphism.manager.module.ModuleManager;
/*     */ import monomorphism.manager.module.impl.combat.KillAura;
/*     */ import monomorphism.manager.module.util.move.MovementUtil;
/*     */ import monomorphism.manager.module.util.render.DecelerateAnimation;
/*     */ import monomorphism.manager.module.util.render.Direction;
/*     */ import net.minecraft.client.dw;
/*     */ import net.minecraft.client.kr;
/*     */ import net.minecraft.client.li;
/*     */ import net.minecraft.eV;
/*     */ import net.minecraft.nn;
/*     */ import net.minecraft.tF;
/*     */ import org.lwjgl.input.Keyboard;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TargetStrafe
/*     */   extends Module
/*     */ {
/*  32 */   private static int strafe = 1;
/*  33 */   private final DecelerateAnimation animation = new DecelerateAnimation(250, 4.5D, Direction.FORWARDS);
/*     */   public TargetStrafe() {
/*  35 */     super("Target Strafe", 0, Category.Movement);
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onMotion(EventMotion event) {
/*  40 */     if (canStrafe()) {
/*  41 */       if (mc.aN.ak == 0) {
/*  42 */         mc.aN.ak = 1;
/*     */       }
/*  44 */       if (mc.aQ.H) {
/*  45 */         strafe = -strafe;
/*     */       } else {
/*  47 */         if (isPressed(mc.aN.p))
/*  48 */           strafe = 1; 
/*  49 */         if (isPressed(mc.aN.am))
/*  50 */           strafe = -1; 
/*     */       } 
/*  52 */     } else if (mc.aN.ak != 0) {
/*  53 */       mc.aN.ak = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onRender3D(EventRender3D event) {
/*  59 */     if (this.animation.getEndPoint() != 4.5D) this.animation.setEndPoint(4.5D); 
/*  60 */     boolean canStrafe = canStrafe();
/*  61 */     this.animation.setDirection(canStrafe ? Direction.FORWARDS : Direction.BACKWARDS);
/*  62 */     if (canStrafe || !this.animation.isDone()) {
/*  63 */       drawCircle(5.0F, -16777216);
/*  64 */       drawCircle(3.0F, -1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drawCircle(float lineWidth, int color) {
/*  69 */     nn entity = KillAura.target;
/*  70 */     if (entity == null)
/*     */       return; 
/*  72 */     eV timer = null;
/*     */     try {
/*  74 */       Field field = dw.class.getDeclaredField("b");
/*  75 */       field.setAccessible(true);
/*  76 */       timer = (eV)field.get(mc);
/*  77 */     } catch (Exception exception) {}
/*     */     
/*  79 */     kr renderManager = null;
/*     */     try {
/*  81 */       Field field = dw.class.getDeclaredField("ba");
/*  82 */       field.setAccessible(true);
/*  83 */       renderManager = (kr)field.get(mc);
/*  84 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  87 */     double x = entity.ai + (getPosX(entity) - entity.ai) * timer.a - renderManager.n;
/*  88 */     double y = entity.a + (getPosY(entity) - entity.a) * timer.a - renderManager.t;
/*  89 */     double z = entity.ah + (getPosZ(entity) - entity.ah) * timer.a - renderManager.o;
/*     */ 
/*     */     
/*  92 */     GL11.glPushMatrix();
/*  93 */     color(color, (float)(this.animation.getOutput() / 4.5D / 2.0D));
/*  94 */     GL11.glDisable(3553);
/*  95 */     GL11.glDisable(2929);
/*  96 */     GL11.glDepthMask(false);
/*  97 */     GL11.glLineWidth(lineWidth);
/*  98 */     GL11.glEnable(3042);
/*  99 */     GL11.glEnable(2848);
/*     */     
/* 101 */     GL11.glBegin(3);
/* 102 */     double pi2 = 6.283185307179586D;
/* 103 */     for (int i = 0; i <= 90; i++) {
/* 104 */       GL11.glVertex3d(x + this.animation.getOutput() * Math.cos(i * pi2 / 45.0D), y, z + this.animation.getOutput() * Math.sin(i * pi2 / 45.0D));
/*     */     }
/* 106 */     GL11.glEnd();
/*     */     
/* 108 */     GL11.glDisable(3042);
/* 109 */     GL11.glDisable(2848);
/* 110 */     GL11.glDepthMask(true);
/* 111 */     GL11.glEnable(2929);
/* 112 */     GL11.glEnable(3553);
/* 113 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 114 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public boolean isPressed(li in) {
/*     */     try {
/* 119 */       Field field = li.class.getDeclaredField("d");
/* 120 */       field.setAccessible(true);
/* 121 */       return ((Boolean)field.get(in)).booleanValue();
/* 122 */     } catch (Exception exception) {
/*     */       
/* 124 */       return false;
/*     */     } 
/*     */   }
/*     */   public static boolean canStrafe() {
/* 128 */     if (!ModuleManager.INSTANCE.isToggled(TargetStrafe.class) || !MovementUtil.isMoving() || (
/* 129 */       !ModuleManager.INSTANCE.isToggled(Flight.class) && !Keyboard.isKeyDown(57))) {
/* 130 */       return false;
/*     */     }
/* 132 */     if (!ModuleManager.INSTANCE.isToggled(Speed.class) && !ModuleManager.INSTANCE.isToggled(Flight.class)) {
/* 133 */       return false;
/*     */     }
/* 135 */     return (ModuleManager.INSTANCE.isToggled(KillAura.class) && ModuleManager.INSTANCE.isToggled(TargetStrafe.class));
/*     */   }
/*     */   
/*     */   public static boolean strafe() {
/* 139 */     return strafe(MovementUtil.getSpeed());
/*     */   }
/*     */   
/*     */   public static boolean strafe(double moveSpeed) {
/* 143 */     if (canStrafe()) {
/* 144 */       setSpeed(moveSpeed, getYaw(getPositionVector(KillAura.target)), strafe, (
/* 145 */           (getDistanceToEntity(KillAura.target) <= 4.5D) ? false : true));
/* 146 */       return true;
/*     */     } 
/* 148 */     return false;
/*     */   }
/*     */   
/*     */   public static void setSpeed(double speed, float yaw, double strafe, double forward) {
/* 152 */     if (forward == 0.0D && strafe == 0.0D) {
/* 153 */       setMotionX(0.0D);
/* 154 */       setMotionZ(0.0D);
/*     */     } else {
/* 156 */       if (forward != 0.0D) {
/* 157 */         if (strafe > 0.0D) {
/* 158 */           yaw += ((forward > 0.0D) ? -45 : 45);
/* 159 */         } else if (strafe < 0.0D) {
/* 160 */           yaw += ((forward > 0.0D) ? 45 : -45);
/*     */         } 
/* 162 */         strafe = 0.0D;
/* 163 */         if (forward > 0.0D) {
/* 164 */           forward = 1.0D;
/* 165 */         } else if (forward < 0.0D) {
/* 166 */           forward = -1.0D;
/*     */         } 
/*     */       } 
/* 169 */       setMotionX(
/* 170 */           forward * speed * -Math.sin(Math.toRadians(yaw)) + strafe * speed * Math.cos(Math.toRadians(yaw)));
/* 171 */       setMotionZ(
/* 172 */           forward * speed * Math.cos(Math.toRadians(yaw)) - strafe * speed * -Math.sin(Math.toRadians(yaw)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static tF getPositionVector(nn ent) {
/* 189 */     return new tF(getPosX(ent), getPosY(ent), getPosZ(ent));
/*     */   }
/*     */   
/*     */   public static float getYaw(tF to) {
/* 193 */     float x = (float)(to.a() - getPosX());
/* 194 */     float z = (float)(to.c() - getPosZ());
/* 195 */     float var1 = (float)(StrictMath.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
/* 196 */     float rotationYaw = getRotationYaw();
/* 197 */     return rotationYaw + wrapAngleTo180_float(var1 - rotationYaw);
/*     */   }
/*     */   
/*     */   public static float wrapAngleTo180_float(float value) {
/* 201 */     value %= 360.0F;
/*     */     
/* 203 */     if (value >= 180.0F) {
/* 204 */       value -= 360.0F;
/*     */     }
/*     */     
/* 207 */     if (value < -180.0F) {
/* 208 */       value += 360.0F;
/*     */     }
/*     */     
/* 211 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void color(int color, float alpha) {
/* 216 */     float r = (color >> 16 & 0xFF) / 255.0F;
/* 217 */     float g = (color >> 8 & 0xFF) / 255.0F;
/* 218 */     float b = (color & 0xFF) / 255.0F;
/* 219 */     GL11.glColor4f(r, g, b, alpha);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 224 */     super.onDisable();
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\movement\TargetStrafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */