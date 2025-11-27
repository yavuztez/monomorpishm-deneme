package monomorphism.manager.module.util.render;

import java.awt.Color;
import org.lwjgl.opengl.GL11;

public class RoundedUtil {

    public static void roundedRect(double x, double y, double width, double height, double edgeRadius, Color color) {
        double halfRadius = edgeRadius / 2.0D;
        width -= halfRadius;
        height -= halfRadius;
        float sideLength = (float)edgeRadius;
        sideLength /= 2.0F;

        start();
        if (color != null) color(color);

        begin(6);
        for (double i = 180.0D; i <= 270.0D; i++) {
            double angle = i * 6.283185307179586D / 360.0D;
            vertex(x + (sideLength * Math.cos(angle)) + sideLength, y + (sideLength * Math.sin(angle)) + sideLength);
        }
        vertex(x + sideLength, y + sideLength);
        end();

        stop();
        start();
        if (color != null) color(color);
        GL11.glEnable(2848);
        begin(6);
        for (double i = 0.0D; i <= 90.0D; i++) {
            double angle = i * 6.283185307179586D / 360.0D;
            vertex(x + width + (sideLength * Math.cos(angle)), y + height + (sideLength * Math.sin(angle)));
        }
        vertex(x + width, y + height);
        end();

        stop();
        start();
        if (color != null) color(color);
        GL11.glEnable(2848);
        begin(6);
        for (double i = 270.0D; i <= 360.0D; i++) {
            double angle = i * 6.283185307179586D / 360.0D;
            vertex(x + width + (sideLength * Math.cos(angle)), y + (sideLength * Math.sin(angle)) + sideLength);
        }
        vertex(x + width, y + sideLength);
        end();

        stop();
        start();
        if (color != null) color(color);
        GL11.glEnable(2848);
        begin(6);
        for (double i = 90.0D; i <= 180.0D; i++) {
            double angle = i * 6.283185307179586D / 360.0D;
            vertex(x + (sideLength * Math.cos(angle)) + sideLength, y + height + (sideLength * Math.sin(angle)));
        }
        vertex(x + sideLength, y + height);
        end();

        stop();

        // İçini doldur
        drawRect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);
        drawRect(x, y + halfRadius, halfRadius, height - halfRadius, color);
        drawRect(x + width, y + halfRadius, halfRadius, height - halfRadius, color);
        drawRect(x + halfRadius, y, width - halfRadius, halfRadius, color);
        drawRect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
    }

    // Basit DrawRect
    public static void drawRect(double x, double y, double width, double height, Color color) {
        start();
        if (color != null) color(color);
        begin(7);
        vertex(x, y);
        vertex(x + width, y);
        vertex(x + width, y + height);
        vertex(x, y + height);
        end();
        stop();
    }

    public static void start() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2884);
        GL11.glDisable(3008);
        GL11.glDisable(2929);
    }

    public static void stop() {
        GL11.glEnable(3008);
        GL11.glEnable(2929);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        color(Color.white);
    }

    public static void begin(int mode) {
        GL11.glBegin(mode);
    }

    public static void end() {
        GL11.glEnd();
    }

    public static void vertex(double x, double y) {
        GL11.glVertex2d(x, y);
    }

    public static void color(Color color) {
        if (color == null) color = Color.white;
        GL11.glColor4d(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, color.getAlpha() / 255.0F);
    }
}