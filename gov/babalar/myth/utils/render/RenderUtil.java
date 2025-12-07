/*     */ package gov.babalar.myth.utils.render;
/*     */ 
/*     */ import gov.babalar.myth.Utility;
/*     */ import java.awt.Color;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderUtil
/*     */ {
/*     */   public static int getRainbowAsRGB(double delay, double offset) {
/*  16 */     return getRainbowAsColor(delay, offset).getRGB();
/*     */   }
/*     */   
/*     */   public static Color getRainbowAsColor(double delay, double offset) {
/*  20 */     float hue = (float)((System.currentTimeMillis() + offset) % delay);
/*  21 */     hue = (float)(hue / delay);
/*  22 */     return Color.getHSBColor(hue, 0.2F, 0.9F);
/*     */   }
/*     */   
/*     */   public static void drawFlowRainbow(String s, float d, float e, int offset) {
/*  26 */     for (int i = 0; i < s.length(); i++) {
/*  27 */       String sdd = String.valueOf(s.charAt(i));
/*  28 */       Utility.drawStringWithShadow(sdd, d, e, getRainbowAsRGB(2200.0D, ((-25 + offset) * i * 3)));
/*  29 */       d += Utility.getStringWidth(sdd);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void drawRect(double x, double y, double width, double height, int color) {
/*  35 */     GLUtil.setup2DRendering(() -> GLUtil.render(7, ()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawSelectionRoundedRect(double x, double y, double width, double height, int color) {
/*  45 */     int radius1 = 0;
/*  46 */     int radius2 = 4;
/*  47 */     int radius3 = 0;
/*  48 */     int radius4 = 0;
/*  49 */     double x2 = x + width;
/*  50 */     double y2 = y + height;
/*  51 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*  52 */     GL11.glEnable(3042);
/*  53 */     GL11.glDisable(3553);
/*  54 */     GL11.glBlendFunc(770, 771);
/*  55 */     GL11.glEnable(2848);
/*     */     
/*  57 */     GL11.glPushMatrix();
/*  58 */     GL11.glBegin(9);
/*  59 */     color(color, (color >> 24 & 0xFF) / 255.0F); int i;
/*  60 */     for (i = 0; i <= 90; i++) {
/*  61 */       GL11.glVertex2d(x + radius1 + Math.sin(i * Math.PI / 180.0D) * radius1 * -1.0D, y + radius1 + Math.cos(i * Math.PI / 180.0D) * radius1 * -1.0D);
/*     */     }
/*  63 */     for (i = 90; i <= 180; i++) {
/*  64 */       GL11.glVertex2d(x + radius2 + Math.sin(i * Math.PI / 180.0D) * radius2 * -1.0D, y2 - radius2 + Math.cos(i * Math.PI / 180.0D) * radius2 * -1.0D);
/*     */     }
/*  66 */     for (i = 0; i <= 90; i++) {
/*  67 */       GL11.glVertex2d(x2 - radius3 + Math.sin(i * Math.PI / 180.0D) * radius3, y2 - radius3 + Math.cos(i * Math.PI / 180.0D) * radius3);
/*     */     }
/*  69 */     for (i = 90; i <= 180; i++) {
/*  70 */       GL11.glVertex2d(x2 - radius4 + Math.sin(i * Math.PI / 180.0D) * radius4, y + radius4 + Math.cos(i * Math.PI / 180.0D) * radius4);
/*     */     }
/*  72 */     GL11.glEnd();
/*  73 */     GL11.glPopMatrix();
/*     */     
/*  75 */     GL11.glEnable(3553);
/*  76 */     GLUtil.endBlend();
/*     */     
/*  78 */     GL11.glDisable(2848);
/*  79 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void drawGradientRect(double left, double top, double right, double bottom, int startColor, int endColor) {
/*  83 */     GLUtil.setup2DRendering(true);
/*  84 */     GL11.glEnable(2848);
/*  85 */     GL11.glShadeModel(7425);
/*  86 */     GL11.glPushMatrix();
/*  87 */     GL11.glBegin(7);
/*  88 */     color(startColor);
/*  89 */     GL11.glVertex2d(left, top);
/*  90 */     GL11.glVertex2d(left, bottom);
/*  91 */     color(endColor);
/*  92 */     GL11.glVertex2d(right, bottom);
/*  93 */     GL11.glVertex2d(right, top);
/*  94 */     GL11.glEnd();
/*  95 */     GL11.glPopMatrix();
/*  96 */     GL11.glDisable(2848);
/*  97 */     GLUtil.end2DRendering();
/*  98 */     resetColor();
/*     */   }
/*     */   
/*     */   public static void drawGradientRectBordered(double left, double top, double right, double bottom, double width, int startColor, int endColor, int borderStartColor, int borderEndColor) {
/* 102 */     drawGradientRect(left + width, top + width, right - width, bottom - width, startColor, endColor);
/* 103 */     drawGradientRect(left + width, top, right - width, top + width, borderStartColor, borderEndColor);
/* 104 */     drawGradientRect(left, top, left + width, bottom, borderStartColor, borderEndColor);
/* 105 */     drawGradientRect(right - width, top, right, bottom, borderStartColor, borderEndColor);
/* 106 */     drawGradientRect(left + width, bottom - width, right - width, bottom, borderStartColor, borderEndColor);
/*     */   }
/*     */   
/*     */   public static void drawRoundedRect(double x, double y, double width, double height, int radius, int color) {
/* 110 */     double x2 = x + width;
/* 111 */     double y2 = y + height;
/* 112 */     GLUtil.setup2DRendering(true);
/* 113 */     GL11.glEnable(2848);
/*     */     
/* 115 */     GL11.glPushMatrix();
/* 116 */     GL11.glBegin(9);
/* 117 */     color(color, (color >> 24 & 0xFF) / 255.0F); int i;
/* 118 */     for (i = 0; i <= 90; i++) {
/* 119 */       GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y + radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
/*     */     }
/* 121 */     for (i = 90; i <= 180; i++) {
/* 122 */       GL11.glVertex2d(x + radius + Math.sin(i * Math.PI / 180.0D) * radius * -1.0D, y2 - radius + Math.cos(i * Math.PI / 180.0D) * radius * -1.0D);
/*     */     }
/* 124 */     for (i = 0; i <= 90; i++) {
/* 125 */       GL11.glVertex2d(x2 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y2 - radius + Math.cos(i * Math.PI / 180.0D) * radius);
/*     */     }
/* 127 */     for (i = 90; i <= 180; i++) {
/* 128 */       GL11.glVertex2d(x2 - radius + Math.sin(i * Math.PI / 180.0D) * radius, y + radius + Math.cos(i * Math.PI / 180.0D) * radius);
/*     */     }
/* 130 */     GL11.glEnd();
/* 131 */     GL11.glPopMatrix();
/* 132 */     GL11.glDisable(2848);
/* 133 */     GLUtil.end2DRendering();
/* 134 */     resetColor();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawRoundedRect(double x, double y, double width, double height, int color) {
/* 167 */     drawRoundedRect(x, y, width, height, 6, color);
/*     */   }
/*     */   
/*     */   public static void rrect(double x, double y, double width, double height, int color) {
/* 171 */     int radius1 = 0;
/* 172 */     int radius2 = 0;
/* 173 */     int radius3 = 0;
/* 174 */     int radius4 = 0;
/* 175 */     double x2 = x + width;
/* 176 */     double y2 = y + height;
/* 177 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/* 178 */     GLUtil.startBlend();
/*     */     
/* 180 */     GL11.glDisable(3553);
/*     */     
/* 182 */     GL11.glEnable(2848);
/*     */     
/* 184 */     GL11.glPushMatrix();
/* 185 */     GL11.glBegin(9);
/* 186 */     color(color, (color >> 24 & 0xFF) / 255.0F); int i;
/* 187 */     for (i = 0; i <= 90; i++) {
/* 188 */       GL11.glVertex2d(x + radius1 + Math.sin(i * Math.PI / 180.0D) * radius1 * -1.0D, y + radius1 + Math.cos(i * Math.PI / 180.0D) * radius1 * -1.0D);
/*     */     }
/* 190 */     for (i = 90; i <= 180; i++) {
/* 191 */       GL11.glVertex2d(x + radius2 + Math.sin(i * Math.PI / 180.0D) * radius2 * -1.0D, y2 - radius2 + Math.cos(i * Math.PI / 180.0D) * radius2 * -1.0D);
/*     */     }
/* 193 */     for (i = 0; i <= 90; i++) {
/* 194 */       GL11.glVertex2d(x2 - radius3 + Math.sin(i * Math.PI / 180.0D) * radius3, y2 - radius3 + Math.cos(i * Math.PI / 180.0D) * radius3);
/*     */     }
/* 196 */     for (i = 90; i <= 180; i++) {
/* 197 */       GL11.glVertex2d(x2 - radius4 + Math.sin(i * Math.PI / 180.0D) * radius4, y + radius4 + Math.cos(i * Math.PI / 180.0D) * radius4);
/*     */     }
/* 199 */     GL11.glEnd();
/* 200 */     GL11.glPopMatrix();
/*     */     
/* 202 */     GL11.glEnable(3553);
/* 203 */     GLUtil.endBlend();
/* 204 */     GL11.glDisable(2848);
/* 205 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   public static void color(int color, float alpha) {
/* 209 */     float r = (color >> 16 & 0xFF) / 255.0F;
/* 210 */     float g = (color >> 8 & 0xFF) / 255.0F;
/* 211 */     float b = (color & 0xFF) / 255.0F;
/* 212 */     GL11.glColor4f(r, g, b, alpha);
/*     */   }
/*     */   
/*     */   public static void color(int color) {
/* 216 */     color(color, (color >> 24 & 0xFF) / 255.0F);
/*     */   }
/*     */   
/*     */   public static void resetColor() {
/* 220 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void roundedRect(double x, double y, double width, double height, double edgeRadius, Color color) {
/* 225 */     double halfRadius = edgeRadius / 2.0D;
/* 226 */     width -= halfRadius;
/* 227 */     height -= halfRadius;
/*     */     
/* 229 */     float sideLength = (float)edgeRadius;
/* 230 */     sideLength /= 2.0F;
/* 231 */     start();
/* 232 */     if (color != null)
/* 233 */       color(color); 
/* 234 */     begin(6);
/*     */     
/*     */     double i;
/* 237 */     for (i = 180.0D; i <= 270.0D; i++) {
/* 238 */       double angle = i * 6.283185307179586D / 360.0D;
/* 239 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + sideLength * Math.sin(angle) + sideLength);
/*     */     } 
/* 241 */     vertex(x + sideLength, y + sideLength);
/*     */ 
/*     */     
/* 244 */     end();
/* 245 */     stop();
/*     */     
/* 247 */     sideLength = (float)edgeRadius;
/* 248 */     sideLength /= 2.0F;
/* 249 */     start();
/* 250 */     if (color != null)
/* 251 */       color(color); 
/* 252 */     GL11.glEnable(2848);
/* 253 */     begin(6);
/*     */ 
/*     */     
/* 256 */     for (i = 0.0D; i <= 90.0D; i++) {
/* 257 */       double angle = i * 6.283185307179586D / 360.0D;
/* 258 */       vertex(x + width + sideLength * Math.cos(angle), y + height + sideLength * Math.sin(angle));
/*     */     } 
/* 260 */     vertex(x + width, y + height);
/*     */ 
/*     */     
/* 263 */     end();
/* 264 */     GL11.glDisable(2848);
/* 265 */     stop();
/*     */     
/* 267 */     sideLength = (float)edgeRadius;
/* 268 */     sideLength /= 2.0F;
/* 269 */     start();
/* 270 */     if (color != null)
/* 271 */       color(color); 
/* 272 */     GL11.glEnable(2848);
/* 273 */     begin(6);
/*     */ 
/*     */     
/* 276 */     for (i = 270.0D; i <= 360.0D; i++) {
/* 277 */       double angle = i * 6.283185307179586D / 360.0D;
/* 278 */       vertex(x + width + sideLength * Math.cos(angle), y + sideLength * Math.sin(angle) + sideLength);
/*     */     } 
/* 280 */     vertex(x + width, y + sideLength);
/*     */ 
/*     */     
/* 283 */     end();
/* 284 */     GL11.glDisable(2848);
/* 285 */     stop();
/*     */     
/* 287 */     sideLength = (float)edgeRadius;
/* 288 */     sideLength /= 2.0F;
/* 289 */     start();
/* 290 */     if (color != null)
/* 291 */       color(color); 
/* 292 */     GL11.glEnable(2848);
/* 293 */     begin(6);
/*     */ 
/*     */     
/* 296 */     for (i = 90.0D; i <= 180.0D; i++) {
/* 297 */       double angle = i * 6.283185307179586D / 360.0D;
/* 298 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + height + sideLength * Math.sin(angle));
/*     */     } 
/* 300 */     vertex(x + sideLength, y + height);
/*     */ 
/*     */     
/* 303 */     end();
/* 304 */     GL11.glDisable(2848);
/* 305 */     stop();
/*     */ 
/*     */     
/* 308 */     rect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);
/*     */ 
/*     */     
/* 311 */     rect(x, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
/* 312 */     rect(x + width, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
/*     */ 
/*     */     
/* 315 */     rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
/* 316 */     rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
/*     */   }
/*     */   
/*     */   public static void rect(double x, double y, double width, double height, Color color) {
/* 320 */     rrect(x, y, width, height, color.getRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   public static void rect(double x, double y, double width, double height, boolean filled, Color color) {
/* 325 */     rrect(x, y, width, height, color.getRGB());
/*     */   }
/*     */   
/*     */   public static void start() {
/* 329 */     GLUtil.startBlend();
/*     */ 
/*     */     
/* 332 */     disable(3553);
/* 333 */     disable(2884);
/* 334 */     disable(3008);
/* 335 */     disable(2929);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void stop() {
/* 344 */     enable(2929);
/* 345 */     enable(2884);
/* 346 */     enable(3553);
/* 347 */     GLUtil.endBlend();
/*     */     
/* 349 */     color(Color.white);
/*     */   }
/*     */   
/*     */   public static void begin(int glMode) {
/* 353 */     GL11.glBegin(glMode);
/*     */   }
/*     */   
/*     */   public static void end() {
/* 357 */     GL11.glEnd();
/*     */   }
/*     */   
/*     */   public static void vertex(double x, double y) {
/* 361 */     GL11.glVertex2d(x, y);
/*     */   }
/*     */   
/*     */   public static void setAlphaLimit(float limit) {
/* 365 */     GL11.glEnable(3008);
/* 366 */     GL11.glAlphaFunc(516, limit * 0.01F);
/*     */   }
/*     */   
/*     */   public static void color(Color color) {
/* 370 */     if (color == null)
/* 371 */       color = Color.white; 
/* 372 */     color((color.getRed() / 255.0F), (color.getGreen() / 255.0F), (color.getBlue() / 255.0F), (color.getAlpha() / 255.0F));
/*     */   }
/*     */   
/*     */   public static void color(double red, double green, double blue, double alpha) {
/* 376 */     GL11.glColor4d(red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public static void enable(int glTarget) {
/* 380 */     GL11.glEnable(glTarget);
/*     */   }
/*     */   
/*     */   public static void disable(int glTarget) {
/* 384 */     GL11.glDisable(glTarget);
/*     */   }
/*     */   public static void drawFlowRect(double x, double y, double width, double height, int offset) {
/*     */     double d;
/* 388 */     for (d = 0.0D; d < width; d++)
/*     */     {
/* 390 */       rect(x, y, d, height, getRainbowAsColor(2000.0D, offset * d * -15.0D));
/*     */     }
/*     */   }
/*     */   
/*     */   public static void drawInterpolateFlow(String key, int x, int y, int amp) {
/* 395 */     for (int i = 0; i < key.length(); i++) {
/* 396 */       String sdd = String.valueOf(key.charAt(i));
/* 397 */       Color textcolor = ColorUtil.interpolateColorsBackAndForth(360, i * amp, new Color(229, 141, 234), ColorUtil.interpolateColorC(getRainbowAsColor(1800.0D, 3.0D), new Color(98, 11, 234), 5.0F), false);
/* 398 */       Utility.drawStringWithShadow(sdd, x, y, textcolor.getRGB());
/* 399 */       x += Utility.getStringWidth(sdd);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void circleNoSmoothRGB(double x, double y, double radius, int color) {
/* 405 */     radius /= 2.0D;
/* 406 */     GL11.glEnable(3042);
/* 407 */     GL11.glDisable(3553);
/* 408 */     GL11.glDisable(2884);
/* 409 */     color(color);
/* 410 */     GL11.glBegin(6);
/*     */     double i;
/* 412 */     for (i = 0.0D; i <= 360.0D; i++) {
/* 413 */       double angle = i * 6.283185307179586D / 360.0D;
/* 414 */       GL11.glVertex2d(x + radius * Math.cos(angle) + radius, y + radius * Math.sin(angle) + radius);
/*     */     } 
/*     */     
/* 417 */     GL11.glEnd();
/* 418 */     GL11.glEnable(2884);
/* 419 */     GL11.glEnable(3553);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\render\RenderUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */