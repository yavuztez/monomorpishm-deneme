package monomorphism.manager.module.util.render;

import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class HudUtil {
   public static int getRainbow(int speed, int offset) {
      float hue = (float)((System.currentTimeMillis() + (long)offset) % (long)speed);
      return Color.getHSBColor(hue / (float)speed, 0.5F, 1.0F).getRGB();
   }

   public static void pushMatrix() {
      GL11.glPushMatrix();
   }

   public static void popMatrix() {
      GL11.glPopMatrix();
   }

   public static void scale(float p_179152_0_, float p_179152_1_, float p_179152_2_) {
      GL11.glScalef(p_179152_0_, p_179152_1_, p_179152_2_);
   }
}
