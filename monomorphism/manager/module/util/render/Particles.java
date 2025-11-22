package monomorphism.manager.module.util.render;

import java.awt.Color;

public class Particles {
   public double x;
   public double y;
   public double deltaX;
   public double deltaY;
   public double size;
   public double opacity;
   public Color color;

   public void render2D() {
      RenderUtil.circle(this.x, this.y, this.size, new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), (int)this.opacity));
   }

   public void updatePosition() {
      this.x += this.deltaX * 2.0D;
      this.y += this.deltaY * 2.0D;
      this.deltaY *= 0.95D;
      this.deltaX *= 0.95D;
      this.opacity -= 2.0D;
      if (this.opacity < 1.0D) {
         this.opacity = 1.0D;
      }

   }

   public void init(double x, double y, double deltaX, double deltaY, double size, Color color) {
      this.x = x;
      this.y = y;
      this.deltaX = deltaX;
      this.deltaY = deltaY;
      this.size = size;
      this.opacity = 254.0D;
      this.color = color;
   }
}
