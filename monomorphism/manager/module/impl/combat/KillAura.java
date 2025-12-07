/*     */ package monomorphism.manager.module.impl.combat;
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import excluded.GetHealth;
/*     */ import java.awt.Color;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import monomorphism.manager.event.EventMotion;
/*     */ import monomorphism.manager.event.EventPacket;
/*     */ import monomorphism.manager.event.EventRender3D;
/*     */ import monomorphism.manager.mapper.Entity;
/*     */ import monomorphism.manager.module.Category;
/*     */ import monomorphism.manager.module.impl.world.Scaffold;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import monomorphism.manager.module.util.SilentUtil;
/*     */ import monomorphism.manager.module.util.render.Particles;
/*     */ import monomorphism.manager.module.util.render.RenderUtil;
/*     */ import monomorphism.manager.module.util.time.TimeUtil;
/*     */ import monomorphism.manager.setting.SettingsManager;
/*     */ import net.minecraft.client.dw;
/*     */ import net.minecraft.client.eT;
/*     */ import net.minecraft.client.fx;
/*     */ import net.minecraft.client.kb;
/*     */ import net.minecraft.client.kr;
/*     */ import net.minecraft.m7;
/*     */ import net.minecraft.mH;
/*     */ import net.minecraft.nn;
/*     */ import net.minecraft.q_;
/*     */ import net.minecraft.u0;
/*     */ import net.minecraft.uy;
/*     */ import net.minecraft.vx;
/*     */ import net.minecraft.yB;
/*     */ import net.minecraft.yg;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class KillAura extends Module {
/*  45 */   public List<nn> targets = new ArrayList<>(); public static nn target;
/*     */   public static boolean blocking;
/*     */   public static boolean attacking;
/*     */   private float displayHealth;
/*     */   private float health;
/*  50 */   private final List<Particles> particles = new ArrayList<>();
/*     */   private boolean sentParticles;
/*  52 */   private double scale = 0.0D;
/*  53 */   private final TimeUtil timeUtil = new TimeUtil();
/*  54 */   private final TimeUtil attackTimer = new TimeUtil();
/*  55 */   private final Random random = new Random();
/*  56 */   private final TimeUtil timer = new TimeUtil();
/*     */   
/*     */   private float lastYaw;
/*     */   
/*     */   private float lastPitch;
/*     */   
/*     */   private dw mc;
/*     */   private float randomYawOffset;
/*     */   private boolean shouldReBlock = false;
/*  65 */   private final TimeUtil blockDelayTimer = new TimeUtil();
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object getFieldValue(Object instance, String fieldName) {
/*  70 */     if (instance == null) return null; 
/*     */     try {
/*  72 */       Class<?> clazz = instance.getClass();
/*  73 */       while (clazz != null) {
/*     */         try {
/*  75 */           Field f = clazz.getDeclaredField(fieldName);
/*  76 */           f.setAccessible(true);
/*  77 */           return f.get(instance);
/*  78 */         } catch (NoSuchFieldException e) {
/*  79 */           clazz = clazz.getSuperclass();
/*     */         } 
/*     */       } 
/*  82 */     } catch (Exception exception) {}
/*  83 */     return null;
/*     */   }
/*     */   private static Object callMethod(Object instance, String methodName) {
/*  86 */     if (instance == null) return null; 
/*     */     try {
/*  88 */       Class<?> clazz = instance.getClass();
/*  89 */       while (clazz != null) {
/*  90 */         for (Method m : clazz.getDeclaredMethods()) {
/*  91 */           if (m.getName().equals(methodName) && m.getParameterCount() == 0) {
/*  92 */             m.setAccessible(true);
/*  93 */             return m.invoke(instance, new Object[0]);
/*     */           } 
/*     */         } 
/*  96 */         clazz = clazz.getSuperclass();
/*     */       } 
/*  98 */     } catch (Exception exception) {}
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   private static <T> T castObject(Object o) {
/* 103 */     return (T)o;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDist(nn entityIn) {
/* 108 */     return Entity.getDistanceToEntity(entityIn);
/*     */   }
/*     */   private void swingClient() {
/* 111 */     Entity.swingItem();
/*     */   }
/*     */   private void sendPacket(Object packet) {
/* 114 */     Entity.sendPacket(castObject(packet));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float normalizeAngle(float angle) {
/* 120 */     angle %= 360.0F;
/* 121 */     if (angle >= 180.0F) {
/* 122 */       angle -= 360.0F;
/*     */     }
/* 124 */     if (angle < -180.0F) {
/* 125 */       angle += 360.0F;
/*     */     }
/* 127 */     return angle;
/*     */   }
/*     */   
/*     */   private float smoothRotation(float current, float target, float speed) {
/* 131 */     float diff = normalizeAngle(target - current);
/*     */     
/* 133 */     float maxChange = speed * (float)this.timeUtil.getElapsedTime() / 1000.0F;
/*     */     
/* 135 */     if (Math.abs(diff) > maxChange) {
/* 136 */       return current + ((diff > 0.0F) ? maxChange : -maxChange);
/*     */     }
/* 138 */     return target;
/*     */   }
/*     */   
/*     */   private float getFOV(nn entity, float currentYaw) {
/* 142 */     float[] rotations = getRotationsRaw(entity);
/* 143 */     float diff = normalizeAngle(rotations[0] - currentYaw);
/* 144 */     return Math.abs(diff);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public KillAura() {
/* 150 */     super("Suriyeli Aura", 19, Category.Combat);
/* 151 */     SettingsManager.manager.addDouble("Range", "auraR", 3.0D, 6.0D, 4.2D, this);
/* 152 */     SettingsManager.manager.addDouble("Min CPS", "minCPS", 1.0D, 20.0D, 13.0D, this);
/* 153 */     SettingsManager.manager.addDouble("Max CPS", "maxCPS", 1.0D, 20.0D, 16.0D, this);
/*     */     
/* 155 */     SettingsManager.manager.addDouble("Rotation Speed", "auraRotSpeed", 10.0D, 150.0D, 120.0D, this);
/* 156 */     SettingsManager.manager.addMode("Priority", "auraPrio", "Distance", new ArrayList(
/*     */           
/* 158 */           Arrays.asList((Object[])new String[] { "Distance", "Health", "FOV" }, )), this);
/*     */ 
/*     */     
/* 161 */     SettingsManager.manager.addDouble("Max FOV", "auraFOV", 1.0D, 360.0D, 90.0D, this);
/* 162 */     SettingsManager.manager.addDouble("Hitbox Expand", "auraExpand", 0.0D, 0.3D, 0.0D, this);
/* 163 */     SettingsManager.manager.addBoolean("Swing", "auraS", true, this);
/* 164 */     SettingsManager.manager.addBoolean("AutoBlock", "auraB", true, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/* 169 */     this.mc = Client.mc;
/* 170 */     super.onEnable();
/* 171 */     if (this.mc != null && this.mc.aQ != null) {
/* 172 */       this.lastYaw = Entity.getRotationYaw();
/* 173 */       this.lastPitch = Entity.getRotationPitch();
/* 174 */       this.scale = 0.0D;
/* 175 */       this.attackTimer.reset();
/* 176 */       this.shouldReBlock = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/* 182 */     this.scale = 0.0D;
/* 183 */     target = null;
/* 184 */     this.particles.clear();
/* 185 */     super.onDisable();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sortTargets() {
/* 191 */     if (this.mc == null || this.mc.aQ == null)
/* 192 */       return;  double range = SettingsManager.manager.getSettingByName("auraR").getValDouble();
/* 193 */     double expand = SettingsManager.manager.getSettingByName("auraExpand").getValDouble();
/*     */     
/* 195 */     if (target != null && (
/* 196 */       getDist(target) > range + expand || target.aj)) {
/* 197 */       target = null;
/*     */     }
/*     */ 
/*     */     
/* 201 */     if (target == null) {
/* 202 */       List<nn> validTargets = new ArrayList<>();
/* 203 */       List<nn> entityList = Entity.getEntityList();
/*     */       
/* 205 */       if (entityList != null) {
/* 206 */         for (nn entity : entityList) {
/*     */           
/* 208 */           if (entity != this.mc.aQ && getDist(entity) <= range + expand && !entity.aj)
/*     */           {
/* 210 */             validTargets.add(entity);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 215 */       if (!validTargets.isEmpty()) {
/* 216 */         String priority = SettingsManager.manager.getSettingByName("auraPrio").getValString();
/* 217 */         float currentYaw = Entity.getRotationYaw();
/*     */         
/* 219 */         if (priority.equalsIgnoreCase("Distance")) {
/* 220 */           validTargets.sort(Comparator.comparingDouble(this::getDist));
/* 221 */         } else if (priority.equalsIgnoreCase("Health")) {
/* 222 */           validTargets.sort(Comparator.comparingDouble(entity -> {
/*     */                   float health = GetHealth.get((mH)entity);
/* 224 */                   return (String.valueOf(health).equals("NaN") || health <= 0.0F) ? Double.MAX_VALUE : health;
/*     */                 }));
/* 226 */         } else if (priority.equalsIgnoreCase("FOV")) {
/* 227 */           validTargets.sort(Comparator.comparingDouble(entity -> getFOV(entity, currentYaw)));
/*     */         } 
/* 229 */         target = validTargets.get(0);
/*     */       } 
/*     */     } 
/*     */     
/* 233 */     this.targets.clear();
/* 234 */     if (target != null) {
/* 235 */       this.targets.add(target);
/*     */     }
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onUpdate(EventMotion event) {
/* 241 */     if (this.mc == null || this.mc.aQ == null)
/*     */       return; 
/* 243 */     if (ModuleManager.INSTANCE.getModule(Scaffold.class).isEnabled()) {
/* 244 */       target = null;
/* 245 */       blocking = false;
/* 246 */       this.shouldReBlock = false;
/*     */       
/*     */       return;
/*     */     } 
/* 250 */     sortTargets();
/*     */ 
/*     */     
/* 253 */     this.randomYawOffset = this.random.nextFloat() * 2.0F - 1.0F;
/* 254 */     this.timeUtil.reset();
/*     */     
/* 256 */     if (target != null) {
/*     */       
/* 258 */       float[] desiredRotations = getRotationsRaw(target);
/* 259 */       float rotSpeed = (float)SettingsManager.manager.getSettingByName("auraRotSpeed").getValDouble();
/*     */       
/* 261 */       this.lastYaw = smoothRotation(this.lastYaw, desiredRotations[0], rotSpeed);
/* 262 */       this.lastPitch = smoothRotation(this.lastPitch, desiredRotations[1], rotSpeed);
/* 263 */       this.lastPitch = Math.max(-90.0F, Math.min(90.0F, this.lastPitch));
/*     */ 
/*     */       
/* 266 */       this.lastYaw += this.randomYawOffset * (0.01F + this.random.nextFloat() * 0.04F);
/* 267 */       this.lastPitch += this.randomYawOffset * (0.01F + this.random.nextFloat() * 0.04F);
/*     */ 
/*     */       
/* 270 */       if (this.shouldReBlock && !blocking && this.blockDelayTimer.hasReached((50 + this.random.nextInt(75)))) {
/* 271 */         Object itemStack = callMethod(this.mc.aQ.b3, "b");
/* 272 */         Object item = (itemStack != null) ? callMethod(itemStack, "a") : null;
/*     */         
/* 274 */         if (SettingsManager.manager.getSettingByName("auraB").getValBoolean() && itemStack != null && item instanceof net.minecraft.pK) {
/* 275 */           syncCurrentPlayItem();
/*     */           
/* 277 */           this.mc.Q.a((nn)this.mc.aQ, (gO)this.mc.I, castObject(itemStack));
/* 278 */           blocking = true;
/*     */         } 
/* 280 */         this.shouldReBlock = false;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 285 */       double minCPS = SettingsManager.manager.getSettingByName("minCPS").getValDouble();
/* 286 */       double maxCPS = SettingsManager.manager.getSettingByName("maxCPS").getValDouble();
/*     */       
/* 288 */       double cps = minCPS + this.random.nextDouble() * (maxCPS - minCPS);
/* 289 */       long delay = (long)(1000.0D / cps);
/* 290 */       float maxFOV = (float)SettingsManager.manager.getSettingByName("auraFOV").getValDouble();
/*     */       
/* 292 */       if (this.attackTimer.hasReached(delay) && getFOV(target, this.lastYaw) <= maxFOV) {
/* 293 */         attacking = true;
/*     */ 
/*     */         
/* 296 */         if (blocking) {
/* 297 */           sendPacket((yg)new aX(new BlockPos(-1, -1, -1), 255, castObject(callMethod(this.mc.aQ.b3, "b")), 0.0F, 0.0F, 0.0F));
/* 298 */           blocking = false;
/* 299 */           this.shouldReBlock = true;
/* 300 */           this.blockDelayTimer.reset();
/*     */         } 
/*     */         
/* 303 */         if (SettingsManager.manager.getSettingByName("auraS").getValBoolean()) {
/* 304 */           swingClient();
/*     */         }
/*     */ 
/*     */         
/* 308 */         sendPacket((yg)new uy((m7)target, yB.ATTACK));
/*     */         
/* 310 */         this.attackTimer.reset();
/*     */       } else {
/* 312 */         attacking = false;
/*     */       } 
/*     */     } else {
/*     */       
/* 316 */       attacking = false;
/* 317 */       blocking = false;
/* 318 */       this.shouldReBlock = false;
/* 319 */       this.attackTimer.reset();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Subscribe
/*     */   public void onPacket(EventPacket event) {
/* 326 */     if (target == null)
/*     */       return; 
/* 328 */     if (event.packet instanceof fx) {
/*     */       try {
/* 330 */         fx packet = (fx)event.packet;
/* 331 */         Field[] fields = fx.class.getDeclaredFields();
/* 332 */         int floatCount = 0;
/* 333 */         int booleanCount = 0;
/*     */ 
/*     */         
/* 336 */         for (Field f : fields) {
/* 337 */           f.setAccessible(true);
/* 338 */           if (f.getType() == float.class) {
/* 339 */             if (floatCount == 0) {
/* 340 */               f.setFloat(packet, this.lastYaw);
/* 341 */             } else if (floatCount == 1) {
/* 342 */               f.setFloat(packet, this.lastPitch);
/*     */             } 
/* 344 */             floatCount++;
/* 345 */           } else if (f.getType() == boolean.class) {
/* 346 */             if (booleanCount == 2) {
/* 347 */               f.setBoolean(packet, true);
/*     */             }
/* 349 */             booleanCount++;
/*     */           }
/*     */         
/*     */         } 
/* 353 */       } catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float[] getRotationsRaw(nn ent) {
/* 360 */     if (this.mc == null || this.mc.aQ == null) return new float[] { 0.0F, 0.0F };
/*     */ 
/*     */     
/* 363 */     double horizontalOffset = (this.random.nextDouble() - 0.5D) * 0.1D;
/*     */     
/* 365 */     double x = ent.v - this.mc.aQ.v + horizontalOffset;
/* 366 */     double z = ent.r - this.mc.aQ.r + horizontalOffset;
/*     */     
/* 368 */     float entityEyeHeight = 1.62F;
/*     */     try {
/* 370 */       Object ret1 = callMethod(ent, "aR");
/* 371 */       if (ret1 instanceof Float) entityEyeHeight = ((Float)ret1).floatValue(); 
/* 372 */     } catch (Exception exception) {}
/*     */     
/* 374 */     float myEyeHeight = 1.62F;
/*     */     try {
/* 376 */       Object ret2 = callMethod(this.mc.aQ, "aR");
/* 377 */       if (ret2 instanceof Float) myEyeHeight = ((Float)ret2).floatValue(); 
/* 378 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/* 381 */     double randomHeightMultiplier = 0.8D + this.random.nextDouble() * 0.2D;
/*     */     
/* 383 */     double verticalJitter = 0.005D + this.random.nextDouble() * 0.015D;
/*     */     
/* 385 */     double y = ent.ao + entityEyeHeight * randomHeightMultiplier - this.mc.aQ.ao + myEyeHeight - verticalJitter;
/*     */     
/* 387 */     double dist = Math.sqrt(x * x + z * z);
/* 388 */     float yaw = (float)(Math.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
/* 389 */     float pitch = (float)-(Math.atan2(y, dist) * 180.0D / Math.PI);
/*     */     
/* 391 */     yaw = normalizeAngle(yaw);
/* 392 */     pitch = normalizeAngle(pitch);
/* 393 */     return new float[] { yaw, pitch };
/*     */   }
/*     */   
/*     */   private void syncCurrentPlayItem() {
/* 397 */     if (this.mc == null || this.mc.Q == null || this.mc.aQ == null)
/* 398 */       return;  int currentPlayerItem = -1;
/* 399 */     Object playerController = this.mc.Q;
/* 400 */     Object gField = getFieldValue(playerController, "g");
/* 401 */     if (gField instanceof Integer) {
/* 402 */       currentPlayerItem = ((Integer)gField).intValue();
/*     */     }
/* 404 */     int n = this.mc.aQ.b3.f;
/* 405 */     if (n != currentPlayerItem) {
/* 406 */       sendPacket((yg)new u0(currentPlayerItem));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Subscribe
/*     */   public void onRender3D(EventRender3D event) {
/* 414 */     if (target != null) {
/* 415 */       drawCircle(target, 0.67D, true, event.getTicks());
/*     */     }
/*     */   }
/*     */   
/*     */   private void drawCircle(nn entity, double rad, boolean shade, float partialTicks) {
/* 420 */     if (this.mc == null)
/* 421 */       return;  Object renderManager = getFieldValue(this.mc, "bI");
/* 422 */     if (renderManager == null)
/*     */       return; 
/* 424 */     GL11.glPushMatrix();
/* 425 */     GL11.glDisable(3553);
/* 426 */     GL11.glEnable(2848);
/* 427 */     GL11.glEnable(2832);
/* 428 */     GL11.glEnable(3042);
/* 429 */     GL11.glBlendFunc(770, 771);
/* 430 */     GL11.glHint(3154, 4354);
/* 431 */     GL11.glHint(3155, 4354);
/* 432 */     GL11.glHint(3153, 4354);
/* 433 */     GL11.glDepthMask(false);
/* 434 */     GL11.glAlphaFunc(516, 0.0F);
/* 435 */     if (shade) {
/* 436 */       GL11.glShadeModel(7425);
/*     */     }
/* 438 */     GL11.glDisable(2884);
/* 439 */     GL11.glBegin(5);
/*     */     
/* 441 */     kr renderMan = (kr)renderManager;
/* 442 */     Double renderPosX = castObject(getFieldValue(renderMan, "u"));
/* 443 */     Double renderPosY = castObject(getFieldValue(renderMan, "m"));
/* 444 */     Double renderPosZ = castObject(getFieldValue(renderMan, "r"));
/* 445 */     if (renderPosX == null || renderPosY == null || renderPosZ == null)
/*     */       return; 
/* 447 */     double x = entity.ai + (Entity.getPosX(entity) - entity.ai) * partialTicks - renderPosX.doubleValue();
/* 448 */     double y = entity.a + (Entity.getPosY(entity) - entity.a) * partialTicks - renderPosY.doubleValue() + Math.sin(System.currentTimeMillis() / 200.0D) + 1.0D;
/* 449 */     double z = entity.ah + (Entity.getPosZ(entity) - entity.ah) * partialTicks - renderPosZ.doubleValue();
/*     */     float i;
/* 451 */     for (i = 0.0F; i < 6.283185307179586D; i = (float)(i + 0.09817477042468103D)) {
/* 452 */       double vecX = x + rad * Math.cos(i);
/* 453 */       double vecZ = z + rad * Math.sin(i);
/* 454 */       Color c = SilentUtil.getThemeColor(i, 1.0F);
/* 455 */       if (shade) {
/* 456 */         GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 0.0F);
/* 457 */         GL11.glVertex3d(vecX, y - Math.cos(System.currentTimeMillis() / 200.0D) / 2.0D, vecZ);
/* 458 */         GL11.glColor4f(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, 0.85F);
/*     */       } 
/* 460 */       GL11.glVertex3d(vecX, y, vecZ);
/*     */     } 
/*     */     
/* 463 */     GL11.glEnd();
/* 464 */     if (shade) {
/* 465 */       GL11.glShadeModel(7424);
/*     */     }
/* 467 */     GL11.glDepthMask(true);
/* 468 */     GL11.glEnable(2929);
/* 469 */     GL11.glAlphaFunc(516, 0.1F);
/* 470 */     GL11.glEnable(2884);
/* 471 */     GL11.glDisable(2848);
/* 472 */     GL11.glDisable(2848);
/* 473 */     GL11.glEnable(2832);
/* 474 */     GL11.glEnable(3553);
/* 475 */     GL11.glPopMatrix();
/* 476 */     GL11.glColor3f(1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double round(double value, int places) {
/* 482 */     if (places < 0) {
/* 483 */       throw new IllegalArgumentException();
/*     */     }
/* 485 */     BigDecimal bd = new BigDecimal(Double.toString(value));
/* 486 */     bd = bd.setScale(places, RoundingMode.HALF_UP);
/* 487 */     return bd.doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   @Subscribe
/*     */   public void onRender2D(EventRender2D event) {
/* 493 */     if (this.mc == null || this.mc.aN == null || this.mc.ar == null) {
/*     */       return;
/*     */     }
/* 496 */     if (this.timer.hasReached(9L)) {
/* 497 */       long elapsedTime = this.timer.getElapsedTime();
/* 498 */       if (target != null) {
/* 499 */         this.scale = Math.min(1.0D, this.scale + elapsedTime / 4.0E14D + (1.0D - this.scale) / 10.0D);
/*     */       } else {
/* 501 */         this.scale = Math.max(0.0D, this.scale - elapsedTime / 8.0E13D - (1.0D - this.scale) / 10.0D);
/* 502 */         if (this.scale == 0.0D) {
/* 503 */           this.particles.clear();
/*     */         }
/*     */       } 
/* 506 */       this.timer.reset();
/*     */     } 
/*     */     
/* 509 */     if (target != null && target instanceof nn) {
/* 510 */       if (this.scale > 0.0D) {
/* 511 */         float nameWidth = 38.0F;
/* 512 */         float posX = this.mc.s / (this.mc.aN.ah * 2) - 38.0F - 45.0F + 80.0F;
/* 513 */         float posY = this.mc.m / (this.mc.aN.ah * 2) + 20.0F + 50.0F;
/*     */         
/* 515 */         RenderUtil.pushMatrix();
/* 516 */         RenderUtil.translate((posX + 38.0F + 2.0F + 64.5F) * (1.0D - this.scale), (posY - 34.0F + 24.0F) * (1.0D - this.scale), 0.0D);
/* 517 */         RenderUtil.scale(this.scale, this.scale, 0.0D);
/*     */         
/* 519 */         double dist = getDist(target);
/* 520 */         GameProfile profile = null;
/*     */         try {
/* 522 */           Field field = nn.class.getDeclaredField("b0");
/* 523 */           field.setAccessible(true);
/* 524 */           profile = (GameProfile)field.get(target);
/* 525 */         } catch (Exception exception) {}
/* 526 */         String name = (profile != null) ? profile.getName() : "Unknown";
/*     */         
/* 528 */         RenderUtil.roundedRect((posX + 38.0F + 2.0F), (posY - 34.0F), 129.0D, 48.0D, 8.0D, new Color(0, 0, 0, 110));
/*     */         
/* 530 */         int scaleOffset = (int)(target.bz * 0.35F);
/* 531 */         Iterator<Particles> var11 = this.particles.iterator();
/* 532 */         while (var11.hasNext()) {
/* 533 */           Particles p = var11.next();
/* 534 */           if (p.opacity > 4.0D) {
/* 535 */             p.render2D();
/*     */           }
/*     */         } 
/*     */         
/* 539 */         if (target instanceof eT) {
/* 540 */           double d = -(((eT)target).bz * 23);
/* 541 */           RenderUtil.color(new Color(255, (int)(255.0D + d), (int)(255.0D + d)));
/* 542 */           renderPlayerModelTexture((posX + 38.0F + 6.0F + scaleOffset / 2.0F), (posY - 34.0F + 5.0F + scaleOffset / 2.0F), 3.0F, 3.0F, 3, 3, 30 - scaleOffset, 30 - scaleOffset, 24.0F, 24.5F, (eT)target);
/* 543 */           renderPlayerModelTexture((posX + 38.0F + 6.0F + scaleOffset / 2.0F), (posY - 34.0F + 5.0F + scaleOffset / 2.0F), 15.0F, 3.0F, 3, 3, 30 - scaleOffset, 30 - scaleOffset, 24.0F, 24.5F, (eT)target);
/* 544 */           RenderUtil.color(Color.WHITE);
/*     */         } 
/*     */         
/* 547 */         double fontHeight = this.mc.ar.i;
/* 548 */         this.mc.ar.a("Distance: " + round(dist, 1), posX + 38.0F + 6.0F + 30.0F + 3.0F, posY - 34.0F + 5.0F + 15.0F + 2.0F, Color.WHITE.hashCode());
/* 549 */         RenderUtil.pushMatrix();
/* 550 */         this.mc.ar.a("Name: " + name, posX + 38.0F + 6.0F + 30.0F + 3.0F, posY - 34.0F + 5.0F + 15.0F - (float)fontHeight, Color.WHITE.hashCode());
/* 551 */         RenderUtil.popMatrix();
/*     */         
/* 553 */         if (!String.valueOf(GetHealth.get((mH)target)).equals("NaN")) {
/* 554 */           this.health = Math.min(20.0F, GetHealth.get((mH)target));
/*     */         }
/* 556 */         if (String.valueOf(this.displayHealth).equals("NaN")) {
/* 557 */           this.displayHealth = (float)(Math.random() * 20.0D);
/*     */         }
/* 559 */         if (dist > 20.0D || target.aj) {
/* 560 */           this.health = 0.0F;
/*     */         }
/*     */         
/* 563 */         if (this.timer.hasReached(16L)) {
/* 564 */           this.displayHealth = (this.displayHealth * 5.0F + this.health) / 6.0F;
/*     */         }
/*     */         
/* 567 */         float offset = 6.0F;
/* 568 */         float drawBarPosX = posX + 38.0F;
/* 569 */         if (this.displayHealth > 0.1D) {
/* 570 */           for (int i = 0; i < this.displayHealth * 4.0F; i++) {
/* 571 */             int color = SilentUtil.getColor((10 + i), 0.5F, 1.0F);
/* 572 */             fx.a((int)drawBarPosX + (int)offset, (int)posY + 5, (int)((drawBarPosX + 1.0F) + offset * 1.25D), (int)posY + 10, color);
/* 573 */             offset++;
/*     */           } 
/*     */         }
/*     */         
/* 577 */         if (dist <= 20.0D && !target.aj) {
/* 578 */           this.mc.ar.a(String.valueOf(round(this.displayHealth, 1)), (float)((drawBarPosX + 2.0F) + offset * 1.25D), posY + 2.5F, -1);
/*     */         }
/*     */         
/* 581 */         ArrayList<Particles> removeList = new ArrayList<>();
/* 582 */         Iterator<Particles> var25 = this.particles.iterator();
/* 583 */         while (var25.hasNext()) {
/* 584 */           Particles p = var25.next();
/* 585 */           p.updatePosition();
/* 586 */           if (p.opacity <= 1.0D) {
/* 587 */             removeList.add(p);
/*     */           }
/*     */         } 
/* 590 */         this.particles.removeAll(removeList);
/*     */         
/* 592 */         RenderUtil.popMatrix();
/* 593 */         this.timeUtil.reset();
/*     */       } 
/*     */     } else {
/* 596 */       this.particles.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static double getDistanceSqToEntity(nn target, nn entityIn) {
/* 601 */     double d0 = Entity.getPosX(target) - Entity.getPosX(entityIn);
/* 602 */     double d1 = Entity.getPosY(target) - Entity.getPosY(entityIn);
/* 603 */     double d2 = Entity.getPosZ(target) - Entity.getPosZ(entityIn);
/* 604 */     return d0 * d0 + d1 * d1 + d2 * d2;
/*     */   }
/*     */   
/*     */   public static void renderPlayerModelTexture(double x, double y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight, eT target) {
/* 608 */     dw mcStatic = Client.mc;
/* 609 */     vx skin = (vx)callMethod(target, "b");
/* 610 */     kb text = castObject(getFieldValue(mcStatic, "h"));
/* 611 */     if (skin != null && text != null) {
/* 612 */       text.b(skin);
/*     */     }
/* 614 */     GL11.glEnable(3042);
/* 615 */     fx.a((int)x, (int)y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
/* 616 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   public static void renderPlayerModelTexture(double x, double y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
/* 620 */     dw mcStatic = Client.mc;
/* 621 */     vx skin = new vx("textures/entity/steve.png");
/* 622 */     kb text = castObject(getFieldValue(mcStatic, "h"));
/* 623 */     if (text != null) {
/* 624 */       text.b(skin);
/*     */     }
/* 626 */     GL11.glEnable(3042);
/* 627 */     fx.a((int)x, (int)y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
/* 628 */     GL11.glDisable(3042);
/*     */   }
/*     */   
/*     */   public Color getThemeColor(float colorOffset) {
/* 632 */     return getThemeColor(colorOffset, 1.0F);
/*     */   }
/*     */   
/*     */   public Color getThemeColor(float colorOffset, float timeMultiplier) {
/* 636 */     float colorOffsetMultiplier = 2.2F;
/* 637 */     colorOffset *= colorOffsetMultiplier;
/* 638 */     double timer = System.currentTimeMillis() / 1.0E8D * timeMultiplier * 400000.0D;
/* 639 */     double factor = (Math.sin(timer + (colorOffset * 0.55F)) + 1.0D) * 0.5D;
/* 640 */     return mixColors(new Color(190, 0, 255, 255), new Color(0, 190, 255, 255), factor);
/*     */   }
/*     */   
/*     */   public Color mixColors(Color color1, Color color2, double percent) {
/* 644 */     double inverse_percent = 1.0D - percent;
/* 645 */     int redPart = (int)(color1.getRed() * percent + color2.getRed() * inverse_percent);
/* 646 */     int greenPart = (int)(color1.getGreen() * percent + color2.getGreen() * inverse_percent);
/* 647 */     int bluePart = (int)(color1.getBlue() * percent + color2.getBlue() * inverse_percent);
/* 648 */     return new Color(redPart, greenPart, bluePart);
/*     */   }
/*     */   
/*     */   public static void anim(float f, float f1) {
/* 652 */     float var15 = (float)Math.sin((f1 * f1 * 3.1415927F));
/* 653 */     transformFirstPersonItem(f / 2.0F, 0.0F);
/* 654 */     GL11.glRotatef(-var15 * 40.0F / 2.0F, var15 / 2.0F, -0.0F, 9.0F);
/* 655 */     GL11.glRotatef(-var15 * 30.0F, 1.0F, var15 / 2.0F, -0.0F);
/* 656 */     doBlockTransformations();
/* 657 */     GL11.glTranslatef(-0.05F, 0.0F, 0.1F);
/*     */   }
/*     */   
/*     */   private static void transformFirstPersonItem(float equipProgress, float swingProgress) {
/* 661 */     GL11.glTranslatef(0.56F, -0.52F, -0.71999997F);
/* 662 */     GL11.glTranslatef(0.0F, equipProgress * -0.6F, 0.0F);
/* 663 */     GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
/* 664 */     float f = (float)Math.sin((swingProgress * swingProgress * 3.1415927F));
/* 665 */     float f1 = (float)Math.sin((sqrt_float(swingProgress) * 3.1415927F));
/* 666 */     GL11.glRotatef(f * -20.0F, 0.0F, 1.0F, 0.0F);
/* 667 */     GL11.glRotatef(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
/* 668 */     GL11.glRotatef(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
/* 669 */     GL11.glScalef(0.4F, 0.4F, 0.4F);
/*     */   }
/*     */   
/*     */   private static void doBlockTransformations() {
/* 673 */     GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
/* 674 */     GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
/* 675 */     GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
/* 676 */     GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
/*     */   }
/*     */   
/*     */   public static float sqrt_float(float value) {
/* 680 */     return (float)Math.sqrt(value);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\combat\KillAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */