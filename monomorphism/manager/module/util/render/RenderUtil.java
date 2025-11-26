package monomorphism.manager.module.util.render;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.FloatBuffer;
import monomorphism.manager.module.util.Client;
import net.minecraft.client.jF;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;

public class RenderUtil {
   public long last2DFrame = System.currentTimeMillis();
   public long last3DFrame = System.currentTimeMillis();
   public float delta2DFrameTime;
   public float delta3DFrameTime;

   public static void glRenderStart() {
      GL11.glPushMatrix();
      GL11.glPushAttrib(1048575);
      GL11.glEnable(3042);
      GL11.glDisable(2884);
      GL11.glDisable(3553);
   }

   public static void glRenderStop() {
      GL11.glEnable(3553);
      GL11.glEnable(2884);
      GL11.glDisable(3042);
      GL11.glPopAttrib();
      GL11.glPopMatrix();
   }

   public static float convertColor(int count, int color) {
      return (float)(color >> count & 255) / 255.0F;
   }

   public static void setColor(Color c) {
      GL11.glColor4d((double)((float)c.getRed() / 255.0F), (double)((float)c.getGreen() / 255.0F), (double)((float)c.getBlue() / 255.0F), (double)((float)c.getAlpha() / 255.0F));
   }

   public static void drawGradient(double x, double y, double x2, double y2, int col1, int col2) {
      float f = (float)(col1 >> 24 & 255) / 255.0F;
      float f1 = (float)(col1 >> 16 & 255) / 255.0F;
      float f2 = (float)(col1 >> 8 & 255) / 255.0F;
      float f3 = (float)(col1 & 255) / 255.0F;
      float f4 = (float)(col2 >> 24 & 255) / 255.0F;
      float f5 = (float)(col2 >> 16 & 255) / 255.0F;
      float f6 = (float)(col2 >> 8 & 255) / 255.0F;
      float f7 = (float)(col2 & 255) / 255.0F;
      glRenderStart();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glPushMatrix();
      GL11.glBegin(7);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glVertex2d(x2, y);
      GL11.glVertex2d(x, y);
      GL11.glColor4f(f5, f6, f7, f4);
      GL11.glVertex2d(x, y2);
      GL11.glVertex2d(x2, y2);
      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glShadeModel(7424);
      GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
      glRenderStop();
   }

   public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
      float f = (float)(col1 >> 24 & 255) / 255.0F;
      float f1 = (float)(col1 >> 16 & 255) / 255.0F;
      float f2 = (float)(col1 >> 8 & 255) / 255.0F;
      float f3 = (float)(col1 & 255) / 255.0F;
      float f4 = (float)(col2 >> 24 & 255) / 255.0F;
      float f5 = (float)(col2 >> 16 & 255) / 255.0F;
      float f6 = (float)(col2 >> 8 & 255) / 255.0F;
      float f7 = (float)(col2 & 255) / 255.0F;
      glRenderStart();
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GL11.glShadeModel(7425);
      GL11.glPushMatrix();
      GL11.glBegin(7);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glVertex2d(left, top);
      GL11.glVertex2d(left, bottom);
      GL11.glColor4f(f5, f6, f7, f4);
      GL11.glVertex2d(right, bottom);
      GL11.glVertex2d(right, top);
      GL11.glEnd();
      GL11.glPopMatrix();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glShadeModel(7424);
      glRenderStop();
   }

   public static void drawRect(double x, double y, double d, double e, int color) {
      float f = (float)(color >> 24 & 255) / 255.0F;
      float f1 = (float)(color >> 16 & 255) / 255.0F;
      float f2 = (float)(color >> 8 & 255) / 255.0F;
      float f3 = (float)(color & 255) / 255.0F;
      glRenderStart();
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(7);
      GL11.glVertex2d(x, y);
      GL11.glVertex2d(d, y);
      GL11.glVertex2d(d, e);
      GL11.glVertex2d(x, e);
      GL11.glEnd();
      glRenderStop();
   }

   public static void drawBorderedRect(float xPos, float yPos, float width, float height, float lineWidth, int lineColor, int bgColor) {
      drawRect((double)xPos, (double)yPos, (double)width, (double)height, bgColor);
      float f = (float)(lineColor >> 24 & 255) / 255.0F;
      float f1 = (float)(lineColor >> 16 & 255) / 255.0F;
      float f2 = (float)(lineColor >> 8 & 255) / 255.0F;
      float f3 = (float)(lineColor & 255) / 255.0F;
      glRenderStart();
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glLineWidth(lineWidth);
      GL11.glEnable(2848);
      GL11.glBegin(1);
      GL11.glVertex2d((double)xPos, (double)yPos);
      GL11.glVertex2d((double)width, (double)yPos);
      GL11.glVertex2d((double)width, (double)yPos);
      GL11.glVertex2d((double)width, (double)height);
      GL11.glVertex2d((double)width, (double)height);
      GL11.glVertex2d((double)xPos, (double)height);
      GL11.glVertex2d((double)xPos, (double)height);
      GL11.glVertex2d((double)xPos, (double)yPos);
      GL11.glEnd();
      glRenderStop();
   }

   public static void drawOctagon(float xPos, float yPos, float width, float height, float length, float angle, int color) {
      float f = convertColor(24, color);
      float f1 = convertColor(16, color);
      float f2 = convertColor(8, color);
      float f3 = convertColor(0, color);
      glRenderStart();
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(9);
      GL11.glVertex2d((double)(xPos + length), (double)yPos);
      GL11.glVertex2d((double)(xPos + width - length), (double)yPos);
      GL11.glVertex2d((double)(xPos + width - length), (double)yPos);
      GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F - angle));
      GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F - angle));
      GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F + angle));
      GL11.glVertex2d((double)(xPos + width), (double)(yPos + height / 2.0F + angle));
      GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
      GL11.glVertex2d((double)(xPos + width - length), (double)(yPos + height));
      GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
      GL11.glVertex2d((double)(xPos + length), (double)(yPos + height));
      GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F + angle));
      GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F + angle));
      GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F - angle));
      GL11.glVertex2d((double)xPos, (double)(yPos + height / 2.0F - angle));
      GL11.glVertex2d((double)(xPos + length), (double)yPos);
      GL11.glEnd();
      glRenderStop();
   }

   public static void drawBorderedCircle(float x, float y, float radius, int lineWidth, int outsideC, int insideC) {
      drawCircle(x, y, radius, insideC);
      drawUnfilledCircle(x, y, radius, (float)lineWidth, outsideC);
   }

   public static void drawCircle228(float x, float y, float radius, int lineWidth, int outsideC, int insideC, int jopaSlona) {
      drawCircle228(x, y, radius, insideC, jopaSlona);
   }

   public static void drawUnfilledCircle228(float x, float y, float radius, float lineWidth, int color, int jopaSlona) {
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      float f3 = (float)(color >> 24 & 255) / 255.0F;
      GL11.glEnable(2848);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      enableBlend();
      GL11.glColor4f(f, f1, f2, f3);
      GL11.glLineWidth(lineWidth);
      GL11.glBegin(2);

      for(int i = 0; i <= jopaSlona; ++i) {
         GL11.glVertex2d((double)x + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)y + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
      disableBlend();
   }

   public static void drawUnfilledCircle(float x, float y, float radius, float lineWidth, int color) {
      float f = (float)(color >> 16 & 255) / 255.0F;
      float f1 = (float)(color >> 8 & 255) / 255.0F;
      float f2 = (float)(color & 255) / 255.0F;
      float f3 = (float)(color >> 24 & 255) / 255.0F;
      GL11.glEnable(2848);
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glDepthMask(true);
      GL11.glEnable(2848);
      GL11.glHint(3154, 4354);
      GL11.glHint(3155, 4354);
      enableBlend();
      GL11.glColor4f(f, f1, f2, f3);
      GL11.glLineWidth(lineWidth);
      GL11.glBegin(2);

      for(int i = 0; i <= 360; ++i) {
         GL11.glVertex2d((double)x + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)y + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GL11.glScalef(2.0F, 2.0F, 2.0F);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glHint(3154, 4352);
      GL11.glHint(3155, 4352);
      disableBlend();
   }

   public static void drawCircle228(float x, float y, float radius, int color, int jopaSlona) {
      float f = (float)(color >> 24 & 255) / 255.0F;
      float f1 = (float)(color >> 16 & 255) / 255.0F;
      float f2 = (float)(color >> 8 & 255) / 255.0F;
      float f3 = (float)(color & 255) / 255.0F;
      boolean flag = GL11.glIsEnabled(3042);
      boolean flag1 = GL11.glIsEnabled(2848);
      boolean flag2 = GL11.glIsEnabled(3553);
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glLineWidth(2.5F);
      GL11.glBegin(3);

      for(int i = 0; i <= jopaSlona; ++i) {
         GL11.glVertex2d((double)x + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)y + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
   }

   public static void drawCircle(float x, float y, float radius, int color) {
      float f = (float)(color >> 24 & 255) / 255.0F;
      float f1 = (float)(color >> 16 & 255) / 255.0F;
      float f2 = (float)(color >> 8 & 255) / 255.0F;
      float f3 = (float)(color & 255) / 255.0F;
      boolean flag = GL11.glIsEnabled(3042);
      boolean flag1 = GL11.glIsEnabled(2848);
      boolean flag2 = GL11.glIsEnabled(3553);
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(9);

      for(int i = 0; i <= 360; ++i) {
         GL11.glVertex2d((double)x + Math.sin((double)i * 3.141592653589793D / 180.0D) * (double)radius, (double)y + Math.cos((double)i * 3.141592653589793D / 180.0D) * (double)radius);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
   }

   public static void enableScissoring() {
      GL11.glEnable(3089);
   }

   public static void disableScissoring() {
      GL11.glDisable(3089);
   }

   public static void pushAttrib() {
      GL11.glPushAttrib(8256);
   }

   public static void popAttrib() {
      GL11.glPopAttrib();
   }

   public static void color(int color, float alpha) {
      float r = (float)(color >> 16 & 255) / 255.0F;
      float g = (float)(color >> 8 & 255) / 255.0F;
      float b = (float)(color & 255) / 255.0F;
      GL11.glColor4f(r, g, b, alpha);
   }

   public static void color(int color) {
      color(color, (float)(color >> 24 & 255) / 255.0F);
   }

   public static void resetColor() {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void clear(int mask) {
      GL11.glClear(mask);
   }

   public static void matrixMode(int mode) {
      GL11.glMatrixMode(mode);
   }

   public static void loadIdentity() {
      GL11.glLoadIdentity();
   }

   public static void pushMatrix() {
      GL11.glPushMatrix();
   }

   public static void popMatrix() {
      GL11.glPopMatrix();
   }

   public static void getFloat(int pname, FloatBuffer params) {
      GL11.glGetFloat(pname, params);
   }

   public static void ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
      GL11.glOrtho(left, right, bottom, top, zNear, zFar);
   }

   public static void rotate(float angle, float x, float y, float z) {
      GL11.glRotatef(angle, x, y, z);
   }

   public static void scale(float x, float y, float z) {
      GL11.glScalef(x, y, z);
   }

   public static Color rainbow(int speed, int index, float saturation, float brightness, float opacity) {
      int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
      float hue = (float)angle / 360.0F;
      Color color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
      return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, Math.min(255, (int)(opacity * 255.0F))));
   }

   public static void scale(double x, double y, double z) {
      GL11.glScaled(x, y, z);
   }

   public static void translate(float x, float y, float z) {
      GL11.glTranslatef(x, y, z);
   }

   public static void translate(double x, double y, double z) {
      GL11.glTranslated(x, y, z);
   }

   public static void bindTexture(int texture) {
      GL11.glBindTexture(3553, texture);
   }

   public static void depthMask(boolean flagIn) {
      GL11.glDepthMask(flagIn);
   }

   public static void disableBlend() {
      GL11.glDisable(3042);
   }

   public static void enableBlend() {
      GL11.glEnable(3042);
   }

   public static void disableAlpha() {
      GL11.glDisable(3008);
   }

   public static void enableAlpha() {
      GL11.glEnable(3008);
   }

   public static void disableTexture2D() {
      GL11.glDisable(3553);
   }

   public static void enableTexture2D() {
      GL11.glEnable(3553);
   }

   public static void enableLighting() {
      GL11.glEnable(2896);
   }

   public static void disableLighting() {
      GL11.glDisable(2896);
   }

   public static void blendFunc(int srcFactor, int dstFactor) {
      GL11.glBlendFunc(srcFactor, dstFactor);
   }

   public static void setAlphaLimit(float limit) {
      GL11.glEnable(3008);
      GL11.glAlphaFunc(516, (float)((double)limit * 0.01D));
   }

   public static long getSystemTime() {
      return Sys.getTime() * 1000L / Sys.getTimerResolution();
   }

   public static int clamp(int num, int min, int max) {
      return num < min ? min : Math.min(num, max);
   }

   public static float clamp(float num, float min, float max) {
      return num < min ? min : Math.min(num, max);
   }

   public static int getRandomInRange(int min, int max) {
      return (int)(Math.random() * (double)(max - min) + (double)min);
   }

   public static double clamp(double num, double min, double max) {
      return num < min ? min : Math.min(num, max);
   }

   public static double getNormalDouble(double d, int numberAfterZopyataya) {
      return (new BigDecimal(d)).setScale(numberAfterZopyataya, RoundingMode.HALF_EVEN).doubleValue();
   }

   public static double getNormalDouble(double d) {
      return (new BigDecimal(d)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
   }

   public static void push() {
      GL11.glPushMatrix();
   }

   public static void pop() {
      GL11.glPopMatrix();
   }

   public static void enable(int glTarget) {
      GL11.glEnable(glTarget);
   }

   public static void disable(int glTarget) {
      GL11.glDisable(glTarget);
   }

   public static void start() {
      enable(3042);
      GL11.glBlendFunc(770, 771);
      disable(3553);
      disable(2884);
      disableAlpha();
      disable(2929);
   }

   public static void stop() {
      enableAlpha();
      enable(2929);
      enable(2884);
      enable(3553);
      disable(3042);
      color(Color.white);
   }

   public static void startSmooth() {
      enable(2881);
      enable(2848);
      enable(2832);
   }

   public static void endSmooth() {
      disable(2832);
      disable(2848);
      disable(2881);
   }

   public static void begin(int glMode) {
      GL11.glBegin(glMode);
   }

   public static void end() {
      GL11.glEnd();
   }

   public static void vertex(double x, double y) {
      GL11.glVertex2d(x, y);
   }

   public static void translate(double x, double y) {
      GL11.glTranslated(x, y, 0.0D);
   }

   public static void scale(double x, double y) {
      GL11.glScaled(x, y, 1.0D);
   }

   public static void rotate(double x, double y, double z, double angle) {
      GL11.glRotated(angle, x, y, z);
   }

   public static void color(double red, double green, double blue, double alpha) {
      GL11.glColor4d(red, green, blue, alpha);
   }

   public static void color(double red, double green, double blue) {
      color(red, green, blue, 1.0D);
   }

   public static void color(Color color) {
      color = Color.white;
      color((double)((float)color.getRed() / 255.0F), (double)((float)color.getGreen() / 255.0F), (double)((float)color.getBlue() / 255.0F), (double)((float)color.getAlpha() / 255.0F));
   }

   public static void color(Color color, int alpha) {
      color = Color.white;
      color((double)((float)color.getRed() / 255.0F), (double)((float)color.getGreen() / 255.0F), (double)((float)color.getBlue() / 255.0F), 0.5D);
   }

   public static void lineWidth(double width) {
      GL11.glLineWidth((float)width);
   }

   public static void rect(double x, double y, double width, double height, boolean filled, Color color) {
      start();
      color(color);
      begin(6);
      vertex(x, y);
      vertex(x + width, y);
      vertex(x + width, y + height);
      vertex(x, y + height);
      vertex(x, y);
      vertex(x, y + height);
      vertex(x + width, y);
      vertex(x + width, y + height);
      end();
      stop();
   }

   public static void rect(double x, double y, double width, double height, boolean filled) {
      rect(x, y, width, height, filled, (Color)null);
   }

   public static void rect(double x, double y, double width, double height, Color color) {
      rect(x, y, width, height, true, color);
   }

   public static void rect(double x, double y, double width, double height) {
      rect(x, y, width, height, true, (Color)null);
   }

   public static void rectCentered(double x, double y, double width, double height, boolean filled, Color color) {
      x -= width / 2.0D;
      y -= height / 2.0D;
      rect(x, y, width, height, filled, color);
   }

   public static void rectCentered(double x, double y, double width, double height, boolean filled) {
      x -= width / 2.0D;
      y -= height / 2.0D;
      rect(x, y, width, height, filled, (Color)null);
   }

   public static void rectCentered(double x, double y, double width, double height, Color color) {
      x -= width / 2.0D;
      y -= height / 2.0D;
      rect(x, y, width, height, true, color);
   }

   public static void rectCentered(double x, double y, double width, double height) {
      x -= width / 2.0D;
      y -= height / 2.0D;
      rect(x, y, width, height, true, (Color)null);
   }

   public static void gradient(double x, double y, double width, double height, boolean filled, Color color1, Color color2) {
      start();
      GL11.glShadeModel(7425);
      enableAlpha();
      GL11.glAlphaFunc(516, 0.0F);
      color(color1);
      begin(7);
      vertex(x, y);
      vertex(x + width, y);
      color(color2);
      vertex(x + width, y + height);
      vertex(x, y + height);
      vertex(x, y);
      vertex(x, y + height);
      vertex(x + width, y);
      vertex(x + width, y + height);
      end();
      GL11.glAlphaFunc(516, 0.1F);
      disableAlpha();
      GL11.glShadeModel(7424);
      stop();
   }

   public static void gradient(double x, double y, double width, double height, Color color1, Color color2) {
      gradient(x, y, width, height, true, color1, color2);
   }

   public static void gradientCentered(double x, double y, double width, double height, Color color1, Color color2) {
      x -= width / 2.0D;
      y -= height / 2.0D;
      gradient(x, y, width, height, true, color1, color2);
   }

   public static void gradientSideways(double x, double y, double width, double height, boolean filled, Color color1, Color color2) {
      start();
      GL11.glShadeModel(7425);
      disableAlpha();
      color(color1);
      begin(6);
      vertex(x, y);
      vertex(x, y + height);
      color(color2);
      vertex(x + width, y + height);
      vertex(x + width, y);
      end();
      enableAlpha();
      GL11.glShadeModel(7424);
      stop();
   }

   public static void gradientSideways(double x, double y, double width, double height, Color color1, Color color2) {
      gradientSideways(x, y, width, height, true, color1, color2);
   }

   public static void gradientSidewaysCentered(double x, double y, double width, double height, Color color1, Color color2) {
      x -= width / 2.0D;
      y -= height / 2.0D;
      gradientSideways(x, y, width, height, true, color1, color2);
   }

   public static void polygon(double x, double y, double sideLength, double amountOfSides, boolean filled, Color color) {
      sideLength /= 2.0D;
      start();
      color(color);
      GL11.glLineWidth(2.0F);
      GL11.glEnable(2848);
      begin(6);

      for(double i = 0.0D; i <= amountOfSides / 4.0D; ++i) {
         double angle = i * 4.0D * 6.283185307179586D / 360.0D;
         vertex(x + sideLength * Math.cos(angle) + sideLength, y + sideLength * Math.sin(angle) + sideLength);
      }

      end();
      GL11.glDisable(2848);
      stop();
   }

   public static void polygon(double x, double y, double sideLength, int amountOfSides, boolean filled) {
      polygon(x, y, sideLength, (double)amountOfSides, filled, (Color)null);
   }

   public static void polygon(double x, double y, double sideLength, int amountOfSides, Color color) {
      polygon(x, y, sideLength, (double)amountOfSides, true, color);
   }

   public static void polygon(double x, double y, double sideLength, int amountOfSides) {
      polygon(x, y, sideLength, (double)amountOfSides, true, (Color)null);
   }

   public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, boolean filled, Color color) {
      x -= sideLength / 2.0D;
      y -= sideLength / 2.0D;
      polygon(x, y, sideLength, (double)amountOfSides, filled, color);
   }

   public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, boolean filled) {
      x -= sideLength / 2.0D;
      y -= sideLength / 2.0D;
      polygon(x, y, sideLength, (double)amountOfSides, filled, (Color)null);
   }

   public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, Color color) {
      x -= sideLength / 2.0D;
      y -= sideLength / 2.0D;
      polygon(x, y, sideLength, (double)amountOfSides, true, color);
   }

   public static void polygonCentered(double x, double y, double sideLength, int amountOfSides) {
      x -= sideLength / 2.0D;
      y -= sideLength / 2.0D;
      polygon(x, y, sideLength, (double)amountOfSides, true, (Color)null);
   }

   public static void circle(double x, double y, double radius, boolean filled, Color color) {
      polygon(x, y, radius, 360.0D, filled, color);
   }

   public static void circle(double x, double y, double radius, boolean filled) {
      polygon(x, y, radius, 360, filled);
   }

   public static void circle(double x, double y, double radius, Color color) {
      polygon(x, y, radius, 360, color);
   }

   public static void circle(double x, double y, double radius) {
      polygon(x, y, radius, 360);
   }

   public static void circleCentered(double x, double y, double radius, boolean filled, Color color) {
      x -= radius / 2.0D;
      y -= radius / 2.0D;
      polygon(x, y, radius, 360.0D, filled, color);
   }

   public static void circleCentered(double x, double y, double radius, boolean filled) {
      x -= radius / 2.0D;
      y -= radius / 2.0D;
      polygon(x, y, radius, 360, filled);
   }

   public static void circleCentered(double x, double y, double radius, boolean filled, int sides) {
      x -= radius / 2.0D;
      y -= radius / 2.0D;
      polygon(x, y, radius, sides, filled);
   }

   public static void circleCentered(double x, double y, double radius, Color color) {
      x -= radius / 2.0D;
      y -= radius / 2.0D;
      polygon(x, y, radius, 360, color);
   }

   public static void circleCentered(double x, double y, double radius) {
      x -= radius / 2.0D;
      y -= radius / 2.0D;
      polygon(x, y, radius, 360);
   }

   public static void triangle(double x, double y, double sideLength, boolean filled, Color color) {
      polygon(x, y, sideLength, 3.0D, filled, color);
   }

   public static void triangle(double x, double y, double sideLength, boolean filled) {
      polygon(x, y, sideLength, 3, filled);
   }

   public static void triangle(double x, double y, double sideLength, Color color) {
      polygon(x, y, sideLength, 3, color);
   }

   public static void triangle(double x, double y, double sideLength) {
      polygon(x, y, sideLength, 3);
   }

   public static void triangleCentered(double x, double y, double sideLength, boolean filled, Color color) {
      x -= sideLength / 2.0D;
      y -= sideLength / 2.0D;
      polygon(x, y, sideLength, 3.0D, filled, color);
   }

   public static void triangleCentered(double x, double y, double sideLength, boolean filled) {
      x -= sideLength / 2.0D;
      y -= sideLength / 2.0D;
      polygon(x, y, sideLength, 3, filled);
   }

   public static void triangleCentered(double x, double y, double sideLength, Color color) {
      x -= sideLength / 2.0D;
      y -= sideLength / 2.0D;
      polygon(x, y, sideLength, 3, color);
   }

   public static void triangleCentered(double x, double y, double sideLength) {
      x -= sideLength / 2.0D;
      y -= sideLength / 2.0D;
      polygon(x, y, sideLength, 3);
   }

   public static void lineNoGl(double firstX, double firstY, double secondX, double secondY, Color color) {
      start();
      color(color);
      lineWidth(1.0F);
      GL11.glEnable(2848);
      begin(1);
      vertex(firstX, firstY);
      vertex(secondX, secondY);
      end();
      GL11.glDisable(2848);
      stop();
   }

   public static void line(double firstX, double firstY, double secondX, double secondY, double lineWidth, Color color) {
      start();
      color(color);
      lineWidth(lineWidth);
      GL11.glEnable(2848);
      begin(1);
      vertex(firstX, firstY);
      vertex(secondX, secondY);
      end();
      GL11.glDisable(2848);
      stop();
   }

   public static void line(double firstX, double firstY, double secondX, double secondY, double lineWidth) {
      line(firstX, firstY, secondX, secondY, lineWidth, (Color)null);
   }

   public static void line(double firstX, double firstY, double secondX, double secondY, Color color) {
      line(firstX, firstY, secondX, secondY, 0.0D, color);
   }

   public static void line(double firstX, double firstY, double secondX, double secondY) {
      line(firstX, firstY, secondX, secondY, 0.0D, (Color)null);
   }

   public static void outlineInlinedGradientRect(double x, double y, double width, double height, double inlineOffset, Color color1, Color color2) {
      gradient(x, y, width, inlineOffset, color1, color2);
      gradient(x, y + height - inlineOffset, width, inlineOffset, color2, color1);
      gradientSideways(x, y, inlineOffset, height, color1, color2);
      gradientSideways(x + width - inlineOffset, y, inlineOffset, height, color2, color1);
   }

   public static void roundedRect(double x, double y, double width, double height, double edgeRadius, Color color) {
      double halfRadius = edgeRadius / 2.0D;
      width -= halfRadius;
      height -= halfRadius;
      float sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      begin(6);

      double angle;
      double i;
      for(i = 180.0D; i <= 270.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + (double)sideLength * Math.cos(angle) + (double)sideLength, y + (double)sideLength * Math.sin(angle) + (double)sideLength);
      }

      vertex(x + (double)sideLength, y + (double)sideLength);
      end();
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(6);

      for(i = 0.0D; i <= 90.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + width + (double)sideLength * Math.cos(angle), y + height + (double)sideLength * Math.sin(angle));
      }

      vertex(x + width, y + height);
      end();
      GL11.glDisable(2848);
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(6);

      for(i = 270.0D; i <= 360.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + width + (double)sideLength * Math.cos(angle), y + (double)sideLength * Math.sin(angle) + (double)sideLength);
      }

      vertex(x + width, y + (double)sideLength);
      end();
      GL11.glDisable(2848);
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(6);

      for(i = 90.0D; i <= 180.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + (double)sideLength * Math.cos(angle) + (double)sideLength, y + height + (double)sideLength * Math.sin(angle));
      }

      vertex(x + (double)sideLength, y + height);
      end();
      GL11.glDisable(2848);
      stop();
      rect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);
      rect(x, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
      rect(x + width, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
      rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
      rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
   }

   public static void roundedOutLine(double x, double y, double width, double height, double thickness, double edgeRadius, Color color) {
      double halfRadius = edgeRadius / 2.0D;
      width -= halfRadius;
      height -= halfRadius;
      float sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(1);

      double i;
      double angle;
      for(i = 180.0D; i <= 270.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + (double)sideLength * Math.cos(angle) + (double)sideLength, y + (double)sideLength * Math.sin(angle) + (double)sideLength);
      }

      vertex(x + (double)sideLength, y + (double)sideLength);
      end();
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(1);

      for(i = 0.0D; i <= 90.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + width + (double)sideLength * Math.cos(angle), y + height + (double)sideLength * Math.sin(angle));
      }

      vertex(x + width, y + height);
      end();
      GL11.glDisable(2848);
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(1);

      for(i = 270.0D; i <= 360.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + width + (double)sideLength * Math.cos(angle), y + (double)sideLength * Math.sin(angle) + (double)sideLength);
      }

      vertex(x + width, y + (double)sideLength);
      end();
      GL11.glDisable(2848);
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(1);

      for(i = 90.0D; i <= 180.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + (double)sideLength * Math.cos(angle) + (double)sideLength, y + height + (double)sideLength * Math.sin(angle));
      }

      vertex(x + (double)sideLength, y + height);
      end();
      GL11.glDisable(2848);
      stop();
   }

   public static void roundedRectCustom(double x, double y, double width, double height, double edgeRadius, Color color, boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
      double halfRadius = edgeRadius / 2.0D;
      width -= halfRadius;
      height -= halfRadius;
      float sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(6);

      double i;
      double angle;
      for(i = 180.0D; i <= 270.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + (double)sideLength * Math.cos(angle) + (double)sideLength, y + (double)sideLength * Math.sin(angle) + (double)sideLength);
      }

      vertex(x + (double)sideLength, y + (double)sideLength);
      end();
      GL11.glDisable(2848);
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(6);

      for(i = 0.0D; i <= 90.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + width + (double)sideLength * Math.cos(angle), y + height + (double)sideLength * Math.sin(angle));
      }

      vertex(x + width, y + height);
      end();
      GL11.glDisable(2848);
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(6);

      for(i = 270.0D; i <= 360.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + width + (double)sideLength * Math.cos(angle), y + (double)sideLength * Math.sin(angle) + (double)sideLength);
      }

      vertex(x + width, y + (double)sideLength);
      end();
      GL11.glDisable(2848);
      stop();
      sideLength = (float)edgeRadius;
      sideLength /= 2.0F;
      start();
      color(color);
      GL11.glEnable(2848);
      begin(6);

      for(i = 90.0D; i <= 180.0D; ++i) {
         angle = i * 6.283185307179586D / 360.0D;
         vertex(x + (double)sideLength * Math.cos(angle) + (double)sideLength, y + height + (double)sideLength * Math.sin(angle));
      }

      vertex(x + (double)sideLength, y + height);
      end();
      GL11.glDisable(2848);
      stop();
      rect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);
      rect(x, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
      rect(x + width, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
      rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
      rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
   }

   public static void roundedRectTop(double x, double y, double width, double height, double edgeRadius, Color color) {
      double halfRadius = edgeRadius / 2.0D;
      width -= halfRadius;
      height -= halfRadius;
      circle(x, y, edgeRadius, color);
      circle(x + width - edgeRadius / 2.0D, y, edgeRadius, color);
      rect(x, y + halfRadius, width + halfRadius, height, color);
      rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
   }

   public static void roundedRectBottom(double x, double y, double width, double height, double edgeRadius, Color color) {
      double halfRadius = edgeRadius / 2.0D;
      width -= halfRadius;
      height -= halfRadius;
      circle(x + width - edgeRadius / 2.0D, y + height - edgeRadius / 2.0D, edgeRadius, color);
      circle(x, y + height - edgeRadius / 2.0D, edgeRadius, color);
      rect(x, y, width + halfRadius, height, color);
      rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
   }

   public static void roundedRectRight(double x, double y, double width, double height, double edgeRadius, Color color1, Color color2) {
      double halfRadius = edgeRadius / 2.0D;
      width -= halfRadius;
      height -= halfRadius;
      circle(x + width - edgeRadius / 2.0D, y, edgeRadius, color2);
      circle(x + width - edgeRadius / 2.0D, y + height - edgeRadius / 2.0D, edgeRadius, color2);
      gradientSideways(x, y, width, height + halfRadius, color1, color2);
      rect(x + width, y + halfRadius, 5.0D, height - halfRadius, color2);
   }

   public static void roundedRectRightTop(double x, double y, double width, double height, double edgeRadius, Color color1, Color color2) {
      double halfRadius = edgeRadius / 2.0D;
      width -= halfRadius;
      height -= halfRadius;
      circle(x + width - edgeRadius / 2.0D, y, edgeRadius, color2);
      gradientSideways(x, y, width, height + halfRadius, color1, color2);
      rect(x + width, y + halfRadius, 5.0D, height, color2);
   }

   public static void roundedRectRightBottom(double x, double y, double width, double height, double edgeRadius, Color color1, Color color2) {
      double halfRadius = edgeRadius / 2.0D;
      width -= halfRadius;
      height -= halfRadius;
      circle(x + width - edgeRadius / 2.0D, y + height - edgeRadius / 2.0D, edgeRadius, color2);
      gradientSideways(x, y, width, height + halfRadius, color1, color2);
      rect(x + width, y, 5.0D, height, color2);
   }

   public static void drawBorder(float x, float y, float x2, float y2, float width, int color1) {
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      color(new Color(color1));
      GL11.glLineWidth(width);
      glBegin(2);
      GL11.glVertex2d((double)x2, (double)y);
      GL11.glVertex2d((double)x, (double)y);
      GL11.glVertex2d((double)x, (double)y2);
      GL11.glVertex2d((double)x2, (double)y2);
      glEnd();
      GL11.glEnable(3553);
      GL11.glDisable(3042);
      GL11.glDisable(2848);
   }

   public static void drawTracerLine(double x, double y, double z, float red, float green, float blue, float alpha, float lineWdith) {
      GL11.glPushMatrix();
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(lineWdith);
      GL11.glColor4f(red, green, blue, alpha);
      GL11.glBegin(2);
      GL11.glVertex3d(0.0D, 1.6200000047683716D, 0.0D);
      GL11.glVertex3d(x, y, z);
      GL11.glEnd();
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   public static void color4f(float red, float green, float blue, float alpha) {
      GL11.glColor4f(red, green, blue, alpha);
   }

   public static void lineWidth(float width) {
      GL11.glLineWidth(width);
   }

   public static void glBegin(int mode) {
      GL11.glBegin(mode);
   }

   public static void glEnd() {
      GL11.glEnd();
   }

   public static void putVertex3d(double x, double y, double z) {
      GL11.glVertex3d(x, y, z);
   }

   public static void drawCircle(int x, int y, double r, float f1, float f2, float f3, float f) {
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(2);

      for(int i = 0; i <= 360; ++i) {
         double x2 = Math.sin((double)i * 3.141592653589793D / 180.0D) * r;
         double y2 = Math.cos((double)i * 3.141592653589793D / 180.0D) * r;
         GL11.glVertex2d((double)x + x2, (double)y + y2);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public static void drawFilledCircle(int x, int y, double r, int c) {
      float f = (float)(c >> 24 & 255) / 255.0F;
      float f1 = (float)(c >> 16 & 255) / 255.0F;
      float f2 = (float)(c >> 8 & 255) / 255.0F;
      float f3 = (float)(c & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(6);

      for(int i = 0; i <= 360; ++i) {
         double x2 = Math.sin((double)i * 3.141592653589793D / 180.0D) * r;
         double y2 = Math.cos((double)i * 3.141592653589793D / 180.0D) * r;
         GL11.glVertex2d((double)x + x2, (double)y + y2);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public static void drawFilledCircle(int x, int y, double r, int c, int quality) {
      float f = (float)(c >> 24 & 255) / 255.0F;
      float f1 = (float)(c >> 16 & 255) / 255.0F;
      float f2 = (float)(c >> 8 & 255) / 255.0F;
      float f3 = (float)(c & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(6);

      for(int i = 0; i <= 360 / quality; ++i) {
         double x2 = Math.sin((double)(i * quality) * 3.141592653589793D / 180.0D) * r;
         double y2 = Math.cos((double)(i * quality) * 3.141592653589793D / 180.0D) * r;
         GL11.glVertex2d((double)x + x2, (double)y + y2);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public static void drawFilledCircle(double x, double y, double r, int c, int quality) {
      float f = (float)(c >> 24 & 255) / 255.0F;
      float f1 = (float)(c >> 16 & 255) / 255.0F;
      float f2 = (float)(c >> 8 & 255) / 255.0F;
      float f3 = (float)(c & 255) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glBlendFunc(770, 771);
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(6);

      for(int i = 0; i <= 360 / quality; ++i) {
         double x2 = Math.sin((double)(i * quality) * 3.141592653589793D / 180.0D) * r;
         double y2 = Math.cos((double)(i * quality) * 3.141592653589793D / 180.0D) * r;
         GL11.glVertex2d(x + x2, y + y2);
      }

      GL11.glEnd();
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glDisable(3042);
   }

   public static void drawFilledCircleNoGL(int x, int y, double r, int c) {
      float f = (float)(c >> 24 & 255) / 255.0F;
      float f1 = (float)(c >> 16 & 255) / 255.0F;
      float f2 = (float)(c >> 8 & 255) / 255.0F;
      float f3 = (float)(c & 255) / 255.0F;
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(6);

      for(int i = 0; i <= 18; ++i) {
         double x2 = Math.sin((double)(i * 20) * 3.141592653589793D / 180.0D) * r;
         double y2 = Math.cos((double)(i * 20) * 3.141592653589793D / 180.0D) * r;
         GL11.glVertex2d((double)x + x2, (double)y + y2);
      }

      GL11.glEnd();
   }

   public static void drawFilledCircleNoGL(int x, int y, double r, int c, int quality) {
      float f = (float)(c >> 24 & 255) / 255.0F;
      float f1 = (float)(c >> 16 & 255) / 255.0F;
      float f2 = (float)(c >> 8 & 255) / 255.0F;
      float f3 = (float)(c & 255) / 255.0F;
      GL11.glColor4f(f1, f2, f3, f);
      GL11.glBegin(6);

      for(int i = 0; i <= 360 / quality; ++i) {
         double x2 = Math.sin((double)(i * quality) * 3.141592653589793D / 180.0D) * r;
         double y2 = Math.cos((double)(i * quality) * 3.141592653589793D / 180.0D) * r;
         GL11.glVertex2d((double)x + x2, (double)y + y2);
      }

      GL11.glEnd();
   }

   public static void quickDrawRect(float x, float y, float x2, float y2, int color) {
      glColor(color);
      glBegin(7);
      GL11.glVertex2d((double)x2, (double)y);
      GL11.glVertex2d((double)x, (double)y);
      GL11.glVertex2d((double)x, (double)y2);
      GL11.glVertex2d((double)x2, (double)y2);
      glEnd();
   }

   public static void quickDrawBorderedRect(float x, float y, float x2, float y2, float width, int color1, int color2) {
      quickDrawRect(x, y, x2, y2, color2);
      glColor(color1);
      GL11.glLineWidth(width);
      glBegin(2);
      GL11.glVertex2d((double)x2, (double)y);
      GL11.glVertex2d((double)x, (double)y);
      GL11.glVertex2d((double)x, (double)y2);
      GL11.glVertex2d((double)x2, (double)y2);
      glEnd();
   }

   private static void glColor(int hex) {
      float alpha = (float)(hex >> 24 & 255) / 255.0F;
      float red = (float)(hex >> 16 & 255) / 255.0F;
      float green = (float)(hex >> 8 & 255) / 255.0F;
      float blue = (float)(hex & 255) / 255.0F;
      color((double)red, (double)green, (double)blue, (double)alpha);
   }

   public static void scissor(double x, double y, double width, double height) {
      jF sr = new jF(Client.mc);
      double scale = (double)sr.c();
      y = sr.b() - y;
      x *= scale;
      y *= scale;
      width *= scale;
      height *= scale;
      GL11.glScissor((int)x, (int)(y - height), (int)width, (int)height);
   }

   public static void rainbowRectangle(float x, float y, float width, float height, float divider) {
      for(int i = 0; (float)i <= width; ++i) {
         rect((double)(x + (float)i), (double)y, 1.0D, (double)height, new Color(getColor((float)i / divider, 0.7F, 1.0F)));
      }

   }

   public static int getColor(float hueoffset, float saturation, float brightness) {
      float speed = 4500.0F;
      float hue = (float)(System.currentTimeMillis() % 4500L) / 4500.0F;
      return Color.HSBtoRGB(hue - hueoffset / 54.0F, saturation, brightness);
   }
}
