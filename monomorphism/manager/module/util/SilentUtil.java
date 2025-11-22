package monomorphism.manager.module.util;

import com.google.common.eventbus.Subscribe;
import java.awt.Color;
import java.util.Iterator;
import monomorphism.manager.event.EventRender2D;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.ModuleManager;
import monomorphism.manager.module.util.other.ModuleComparator;
import monomorphism.manager.module.util.render.Animation;
import monomorphism.manager.module.util.render.Direction;
import monomorphism.manager.module.util.render.HudUtil;
import net.minecraft.client.jF;
import org.lwjgl.opengl.GL11;

public class SilentUtil {
   @Subscribe
   public void onRender2D(EventRender2D event) {
      Object sr = new jF(Client.mc);
      HudUtil.pushMatrix();
      HudUtil.scale(1.3F, 1.3F, 1.3F);
      rainbowText("happymeal gizli proje", 10, 10);
      HudUtil.popMatrix();
      double yOffset = 0.0D;
      Object font = Client.mc.ar;
      Object modules = ModuleManager.INSTANCE.getModules();
      modules.sort((new ModuleComparator()).reversed());
      Iterator var8 = modules.iterator();

      while(true) {
         Module module;
         Animation moduleAnimation;
         do {
            if (!var8.hasNext()) {
               return;
            }

            module = (Module)var8.next();
            moduleAnimation = module.animation;
            moduleAnimation.setDirection(module.isEnabled() ? Direction.FORWARDS : Direction.BACKWARDS);
         } while(!module.isEnabled() && moduleAnimation.finished(Direction.BACKWARDS));

         Object displayText = module.getModuleName();
         double textWidth = (double)font.a(displayText);
         double xValue = (double)(sr.a() - 2);
         int flip = xValue <= (double)((float)sr.a() / 2.0F);
         float x = (float)(flip ? xValue : (double)sr.a() - (textWidth + 2.0D));
         float alphaAnimation = 1.0F;
         float y = (float)(yOffset + 3.0D);
         double heightVal = 12.0D;
         this.scaleStart(x + (float)font.a(displayText) / 2.0F, (float)((double)y + heightVal / 2.0D - (double)((float)font.i / 2.0F)), (float)moduleAnimation.getOutput());
         alphaAnimation = (float)moduleAnimation.getOutput();
         font.a(displayText, x, y - 3.0F + this.getMiddleOfBox((float)heightVal), this.applyOpacity((new Color(255, 255, 255, 255)).getRGB(), alphaAnimation), true);
         this.scaleEnd();
         yOffset += moduleAnimation.getOutput() * heightVal;
      }
   }

   public void scaleStart(float x, float y, float scale) {
      GL11.glPushMatrix();
      GL11.glTranslatef(x, y, 0.0F);
      GL11.glScalef(scale, scale, 1.0F);
      GL11.glTranslatef(-x, -y, 0.0F);
   }

   public void scaleEnd() {
      GL11.glPopMatrix();
   }

   public int applyOpacity(int color, float opacity) {
      Color old = new Color(color);
      return this.applyOpacity(old, opacity).getRGB();
   }

   public Color applyOpacity(Color color, float opacity) {
      opacity = Math.min(1.0F, Math.max(0.0F, opacity));
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((float)color.getAlpha() * opacity));
   }

   public float getMiddleOfBox(float boxHeight) {
      return boxHeight / 2.0F - 4.5F;
   }

   public static void rainbowText(String string, int x, int y) {
      int xpos = x;

      for(int i = 0; i < string.length(); ++i) {
         String s = String.valueOf(string.charAt(i));
         Client.mc.ar.a(s, (float)xpos, (float)y, getThemeColor((float)i, 1.0F).getRGB(), true);
         xpos += Client.mc.ar.a(s);
      }

   }

   public static Color getThemeColor(float colorOffset, float timeMultiplier) {
      float colorOffsetMultiplier = 2.5F;
      colorOffset *= colorOffsetMultiplier;
      double timer = (double)System.currentTimeMillis() / 1.0E8D * (double)timeMultiplier * 400000.0D;
      double factor = (Math.sin(timer + (double)(colorOffset * 0.55F)) + 1.0D) * 0.5D;
      return mixColors(Color.red, Color.orange, factor);
   }

   public static Color mixColors(Color color1, Color color2, double percent) {
      double inverse_percent = 1.0D - percent;
      int redPart = (int)((double)color1.getRed() * percent + (double)color2.getRed() * inverse_percent);
      int greenPart = (int)((double)color1.getGreen() * percent + (double)color2.getGreen() * inverse_percent);
      int bluePart = (int)((double)color1.getBlue() * percent + (double)color2.getBlue() * inverse_percent);
      return new Color(redPart, greenPart, bluePart);
   }

   public static int getColor(float hueoffset, float saturation, float brightness) {
      float speed = 4500.0F;
      float hue = (float)(System.currentTimeMillis() % 4500L) / 4500.0F;
      return Color.HSBtoRGB(hue - hueoffset / 54.0F, saturation, brightness);
   }
}
