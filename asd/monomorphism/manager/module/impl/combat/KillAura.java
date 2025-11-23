package monomorphism.manager.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import com.mojang.authlib.GameProfile;
import excluded.GetHealth;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.event.EventRender2D;
import monomorphism.manager.event.EventRender3D;
import monomorphism.manager.mapper.Entity;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.ModuleManager;
import monomorphism.manager.module.impl.world.Scaffold;
import monomorphism.manager.module.util.SilentUtil;
import monomorphism.manager.module.util.render.Particles;
import monomorphism.manager.module.util.render.RenderUtil;
import monomorphism.manager.module.util.time.TimeUtil;
import monomorphism.manager.setting.SettingsManager;
import net.minecraft.BlockPos;
import net.minecraft.aX;
import net.minecraft.eV;
import net.minecraft.nn;
import net.minecraft.pK;
import net.minecraft.u0;
import net.minecraft.uy;
import net.minecraft.vx;
import net.minecraft.yB;
import net.minecraft.client.dw;
import net.minecraft.client.eT;
import net.minecraft.client.eU;
import net.minecraft.client.fx;
import net.minecraft.client.kb;
import net.minecraft.client.kr;
import org.lwjgl.opengl.GL11;

public class KillAura extends Module {
   public static nn target;
   public List<nn> targets = new ArrayList();
   public static boolean blocking;
   public static boolean attacking;
   private float displayHealth;
   private float health;
   private final List<Particles> particles = new ArrayList();
   private boolean sentParticles;
   private double scale = 1.0D;
   private final TimeUtil timeUtil = new TimeUtil();
   private final TimeUtil timer = new TimeUtil();

   public KillAura() {
      super("Suriyeli Aura", 19, Category.Combat);
      SettingsManager.manager.addDouble("Range", "auraR", 3.0D, 6.0D, 6.0D, this);
      SettingsManager.manager.addBoolean("Swing", "auraS", true, this);
   }

   @Subscribe
   public void onUpdate(EventMotion event) {
      this.sortTargets();
      if (!ModuleManager.INSTANCE.getModule(Scaffold.class).isEnabled()) {
         if (!this.targets.isEmpty()) {
            target = (nn)this.targets.get(0);
            attacking = true;
            if (SettingsManager.manager.getSettingByName("auraS").getValBoolean()) {
               swingItem();
            }

            sendPacket(new uy(target, yB.ATTACK));
            if (getThePlayer().b3.b() != null && getThePlayer().b3.b().a() instanceof pK) {
               this.syncCurrentPlayItem();
               blocking = true;
               if (blocking) {
                  mc.Q.a(mc.aQ, mc.I, mc.aQ.b3.b());
               } else {
                  sendPacket(new aX(new BlockPos(-1, -1, -1), 255, mc.aQ.b3.b(), 0.0F, 0.0F, 0.0F));
                  blocking = false;
               }
            }
         }

         if (this.targets.isEmpty()) {
            attacking = false;
            blocking = false;
         }

      }
   }

   private void syncCurrentPlayItem() {
      int currentPlayerItem = -1;

      try {
         Field field = eU.class.getDeclaredField("g");
         field.setAccessible(true);
         currentPlayerItem = (Integer)field.get(mc.Q);
      } catch (Exception var5) {
      }

      int n = mc.aQ.b3.f;
      if (n != currentPlayerItem) {
         try {
            Field field = eU.class.getDeclaredField("g");
            field.setAccessible(true);
            field.setInt(mc.Q, n);
         } catch (Exception var4) {
         }

         sendPacket(new u0(currentPlayerItem));
      }

   }

   public void sortTargets() {
      this.targets.clear();
      Iterator var2 = getEntityList().iterator();

      while(var2.hasNext()) {
         nn entity = (nn)var2.next();
         if ((double)getDistanceToEntity(entity) < SettingsManager.manager.getSettingByName("auraR").getValDouble() && entity != mc.aQ) {
            this.targets.add(entity);
         }
      }

      this.targets.sort(Comparator.comparingDouble(Entity::getDistanceToEntity));
   }

   @Subscribe
   public void onRender3D(EventRender3D event) {
      if (target != null) {
         this.drawCircle(target, 0.67D, true);
      }

   }

   private void drawCircle(nn entity, double rad, boolean shade) {
      GL11.glPushMatrix();
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glEnable(2832);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      GL11.glHint(3153, 4354);
      GL11.glDepthMask(false);
      GL11.glAlphaFunc(516, 0.0F);
      GL11.glShadeModel(7425);
      GL11.glDisable(2884);
      GL11.glBegin(5);
      eV timer = null;

      try {
         Field field = dw.class.getDeclaredField("b");
         field.setAccessible(true);
         timer = (eV)field.get(mc);
      } catch (Exception var29) {
      }

      kr renderManager = null;

      try {
         Field field = dw.class.getDeclaredField("ba");
         field.setAccessible(true);
         renderManager = (kr)field.get(mc);
      } catch (Exception var28) {
      }

      double renderPosX = -1.0D;
      double renderPosY = -1.0D;
      double renderPosZ = -1.0D;

      Field field;
      try {
         field = kr.class.getDeclaredField("u");
         field.setAccessible(true);
         renderPosX = (Double)field.get(renderManager);
      } catch (Exception var27) {
      }

      try {
         field = kr.class.getDeclaredField("m");
         field.setAccessible(true);
         renderPosY = (Double)field.get(renderManager);
      } catch (Exception var26) {
      }

      try {
         field = kr.class.getDeclaredField("r");
         field.setAccessible(true);
         renderPosZ = (Double)field.get(renderManager);
      } catch (Exception var25) {
      }

      double x = entity.ai + (getPosX(entity) - entity.ai) * (double)timer.a - renderPosX;
      double y = entity.a + (getPosY(entity) - entity.a) * (double)timer.a - renderPosY + Math.sin((double)System.currentTimeMillis() / 200.0D) + 1.0D;
      double z = entity.ah + (getPosZ(entity) - entity.ah) * (double)timer.a - renderPosZ;

      for(float i = 0.0F; (double)i < 6.283185307179586D; i = (float)((double)i + 0.09817477042468103D)) {
         double vecX = x + rad * Math.cos((double)i);
         double vecZ = z + rad * Math.sin((double)i);
         Color c = SilentUtil.getThemeColor(i, 1.0F);
         GL11.glColor4f((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F, 0.0F);
         GL11.glVertex3d(vecX, y - Math.cos((double)System.currentTimeMillis() / 200.0D) / 2.0D, vecZ);
         GL11.glColor4f((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F, 0.85F);
         GL11.glVertex3d(vecX, y, vecZ);
      }

      GL11.glEnd();
      GL11.glShadeModel(7424);
      GL11.glDepthMask(true);
      GL11.glEnable(2929);
      GL11.glAlphaFunc(516, 0.1F);
      GL11.glEnable(2884);
      GL11.glDisable(2848);
      GL11.glDisable(2848);
      GL11.glEnable(2832);
      GL11.glEnable(3553);
      GL11.glPopMatrix();
      GL11.glColor3f(255.0F, 255.0F, 255.0F);
   }

   @Subscribe
   public void onRender2D(EventRender2D event) {
      float nameWidth = 38.0F;
      float posX = (float)mc.s / (float)(mc.aN.ah * 2) - 38.0F - 45.0F + 80.0F;
      float posY = (float)mc.m / (float)(mc.aN.ah * 2) + 20.0F + 50.0F;
      if (this.timer.hasReached(9L)) {
         if (target != null && getDistanceSqToEntity(target, mc.aQ) > 100.0D) {
            this.scale = Math.max(0.0D, this.scale - (double)this.timeUtil.getElapsedTime() / 8.0E13D - (1.0D - this.scale) / 10.0D);
            if (this.scale == 0.0D) {
               this.particles.clear();
            }

            this.timer.reset();
         } else {
            this.scale = Math.min(1.0D, this.scale + (double)this.timeUtil.getElapsedTime() / 4.0E14D + (1.0D - this.scale) / 10.0D);
         }
      }

      if (target != null && target instanceof nn) {
         if (this.scale != 0.0D) {
            RenderUtil.pushMatrix();
            RenderUtil.translate((double)(posX + 38.0F + 2.0F + 64.5F) * (1.0D - this.scale), (double)(posY - 34.0F + 24.0F) * (1.0D - this.scale), 0.0D);
            RenderUtil.scale(this.scale, this.scale, 0.0D);
            double dist = (double)getDistanceToEntity(target);
            GameProfile profile = null;

            try {
               Field field = nn.class.getDeclaredField("b0");
               field.setAccessible(true);
               profile = (GameProfile)field.get(target);
            } catch (Exception var18) {
            }

            String name = profile.getName();
            RenderUtil.roundedRect((double)(posX + 38.0F + 2.0F), (double)(posY - 34.0F), 129.0D, 48.0D, 8.0D, new Color(0, 0, 0, 110));
            RenderUtil.popMatrix();
            int scaleOffset = (int)((float)target.bz * 0.35F);
            Iterator var11 = this.particles.iterator();

            while(var11.hasNext()) {
               Particles p = (Particles)var11.next();
               if (p.opacity > 4.0D) {
                  p.render2D();
               }
            }

            RenderUtil.pushMatrix();
            RenderUtil.translate((double)(posX + 38.0F + 2.0F + 64.5F) * (1.0D - this.scale), (double)(posY - 34.0F + 24.0F) * (1.0D - this.scale), 0.0D);
            RenderUtil.scale(this.scale, this.scale, 0.0D);
            double fontHeight;
            if (target instanceof eT) {
               fontHeight = (double)(-(((eT)target).bz * 23));
               RenderUtil.color(new Color(255, (int)(255.0D + fontHeight), (int)(255.0D + fontHeight)));
               renderPlayerModelTexture((double)(posX + 38.0F + 6.0F + (float)scaleOffset / 2.0F), (double)(posY - 34.0F + 5.0F + (float)scaleOffset / 2.0F), 3.0F, 3.0F, 3, 3, 30 - scaleOffset, 30 - scaleOffset, 24.0F, 24.5F, (eT)target);
               renderPlayerModelTexture((double)(posX + 38.0F + 6.0F + (float)scaleOffset / 2.0F), (double)(posY - 34.0F + 5.0F + (float)scaleOffset / 2.0F), 15.0F, 3.0F, 3, 3, 30 - scaleOffset, 30 - scaleOffset, 24.0F, 24.5F, (eT)target);
               RenderUtil.color(Color.WHITE);
            }

            fontHeight = (double)mc.ar.i;
            mc.ar.a("Distance: " + this.round(dist, 1), posX + 38.0F + 6.0F + 30.0F + 3.0F, posY - 34.0F + 5.0F + 15.0F + 2.0F, Color.WHITE.hashCode());
            RenderUtil.pushMatrix();
            mc.ar.a("Name: " + name, posX + 38.0F + 6.0F + 30.0F + 3.0F, posY - 34.0F + 5.0F + 15.0F - (float)fontHeight, Color.WHITE.hashCode());
            RenderUtil.popMatrix();
            if (!String.valueOf(GetHealth.get(target)).equals("NaN")) {
               this.health = Math.min(20.0F, GetHealth.get(target));
            }

            if (String.valueOf(this.displayHealth).equals("NaN")) {
               this.displayHealth = (float)(Math.random() * 20.0D);
            }

            if (dist > 20.0D || target.aj) {
               this.health = 0.0F;
            }

            int speed = true;
            if (this.timer.hasReached(16L)) {
               this.displayHealth = (this.displayHealth * 5.0F + this.health) / 6.0F;
               Iterator var14 = this.particles.iterator();

               while(var14.hasNext()) {
                  Particles p = (Particles)var14.next();
                  p.updatePosition();
                  if (p.opacity < 1.0D) {
                     this.particles.remove(p);
                  }
               }

               this.timer.reset();
            }

            float offset = 6.0F;
            float drawBarPosX = posX + 38.0F;
            int i;
            if ((double)this.displayHealth > 0.1D) {
               for(i = 0; (float)i < this.displayHealth * 4.0F; ++i) {
                  int color = SilentUtil.getColor((float)(10 + i), 0.5F, 1.0F);
                  fx.a((int)drawBarPosX + (int)offset, (int)posY + 5, (int)((double)(drawBarPosX + 1.0F) + (double)offset * 1.25D), (int)posY + 10, color);
                  ++offset;
               }
            }

            Particles p;
            if (target.bz == 9 && !this.sentParticles) {
               for(i = 0; i <= 15; ++i) {
                  p = new Particles();
                  Color c = new Color(SilentUtil.getColor((float)(10 + i), 0.5F, 1.0F));
                  p.init((double)(posX + 55.0F), (double)(posY - 15.0F), (Math.random() - 0.5D) * 2.0D * 1.4D, (Math.random() - 0.5D) * 2.0D * 1.4D, Math.random() * 4.0D, c);
                  this.particles.add(p);
               }

               this.sentParticles = true;
            }

            if (target.bz == 8) {
               this.sentParticles = false;
            }

            if (!(dist > 20.0D) && !target.aj) {
               mc.ar.a(String.valueOf(this.round((double)this.displayHealth, 1)), (float)((double)(drawBarPosX + 2.0F) + (double)offset * 1.25D), posY + 2.5F, -1);
            }

            ArrayList<Particles> removeList = new ArrayList();
            Iterator var25 = this.particles.iterator();

            while(var25.hasNext()) {
               p = (Particles)var25.next();
               if (p.opacity <= 1.0D) {
                  removeList.add(p);
               }
            }

            var25 = removeList.iterator();

            while(var25.hasNext()) {
               p = (Particles)var25.next();
               this.particles.remove(p);
            }

            RenderUtil.popMatrix();
            this.timeUtil.reset();
         }
      } else {
         this.particles.clear();
      }
   }

   public double round(double value, int places) {
      throw new IllegalArgumentException();
   }

   public static double getDistanceSqToEntity(nn target, nn entityIn) {
      double d0 = getPosX(target) - getPosX(entityIn);
      double d1 = getPosY(target) - getPosY(entityIn);
      double d2 = getPosZ(target) - getPosZ(entityIn);
      return d0 * d0 + d1 * d1 + d2 * d2;
   }

   public static void renderPlayerModelTexture(double x, double y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight, eT target) {
      vx skin = target.b();
      kb text = null;

      try {
         Field field = dw.class.getDeclaredField("h");
         field.setAccessible(true);
         text = (kb)field.get(mc);
      } catch (Exception var16) {
      }

      text.b(skin);
      GL11.glEnable(3042);
      fx.a((int)x, (int)y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
      GL11.glDisable(3042);
   }

   public static void renderPlayerModelTexture(double x, double y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
      vx skin = new vx("textures/entity/steve.png");
      kb text = null;

      try {
         Field field = dw.class.getDeclaredField("h");
         field.setAccessible(true);
         text = (kb)field.get(mc);
      } catch (Exception var15) {
      }

      text.b(skin);
      GL11.glEnable(3042);
      fx.a((int)x, (int)y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
      GL11.glDisable(3042);
   }

   public Color getThemeColor(float colorOffset) {
      return this.getThemeColor(colorOffset, 1.0F);
   }

   public Color getThemeColor(float colorOffset, float timeMultiplier) {
      float colorOffsetMultiplier = 2.2F;
      colorOffset *= colorOffsetMultiplier;
      double timer = (double)System.currentTimeMillis() / 1.0E8D * (double)timeMultiplier * 400000.0D;
      double factor = (Math.sin(timer + (double)(colorOffset * 0.55F)) + 1.0D) * 0.5D;
      return this.mixColors(new Color(190, 0, 255, 255), new Color(0, 190, 255, 255), factor);
   }

   public Color mixColors(Color color1, Color color2, double percent) {
      double inverse_percent = 1.0D - percent;
      int redPart = (int)((double)color1.getRed() * percent + (double)color2.getRed() * inverse_percent);
      int greenPart = (int)((double)color1.getGreen() * percent + (double)color2.getGreen() * inverse_percent);
      int bluePart = (int)((double)color1.getBlue() * percent + (double)color2.getBlue() * inverse_percent);
      return new Color(redPart, greenPart, bluePart);
   }

   public static void anim(float f, float f1) {
      float var15 = (float)Math.sin((double)(f1 * f1 * 3.1415927F));
      transformFirstPersonItem(f / 2.0F, 0.0F);
      GL11.glRotatef(-var15 * 40.0F / 2.0F, var15 / 2.0F, -0.0F, 9.0F);
      GL11.glRotatef(-var15 * 30.0F, 1.0F, var15 / 2.0F, -0.0F);
      doBlockTransformations();
      GL11.glTranslatef(-0.05F, 0.0F, 0.1F);
   }

   private static void transformFirstPersonItem(float equipProgress, float swingProgress) {
      GL11.glTranslatef(0.56F, -0.52F, -0.71999997F);
      GL11.glTranslatef(0.0F, equipProgress * -0.6F, 0.0F);
      GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
      float f = (float)Math.sin((double)(swingProgress * swingProgress * 3.1415927F));
      float f1 = (float)Math.sin((double)(sqrt_float(swingProgress) * 3.1415927F));
      GL11.glRotatef(f * -20.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
      GL11.glRotatef(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
      GL11.glScalef(0.4F, 0.4F, 0.4F);
   }

   private static void doBlockTransformations() {
      GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
      GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
   }

   public static float sqrt_float(float value) {
      return (float)Math.sqrt((double)value);
   }
}
