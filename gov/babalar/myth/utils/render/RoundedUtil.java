/*     */ package gov.babalar.myth.utils.render;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ public class RoundedUtil
/*     */ {
/*     */   public static void roundedRect(double x, double y, double width, double height, double edgeRadius, Color color) {
/*  10 */     double halfRadius = edgeRadius / 2.0D;
/*  11 */     width -= halfRadius;
/*  12 */     height -= halfRadius;
/*     */     
/*  14 */     float sideLength = (float)edgeRadius;
/*  15 */     sideLength /= 2.0F;
/*  16 */     start();
/*  17 */     if (color != null)
/*  18 */       color(color); 
/*  19 */     begin(6);
/*     */     
/*     */     double i;
/*  22 */     for (i = 180.0D; i <= 270.0D; i++) {
/*  23 */       double angle = i * 6.283185307179586D / 360.0D;
/*  24 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + sideLength * Math.sin(angle) + sideLength);
/*     */     } 
/*  26 */     vertex(x + sideLength, y + sideLength);
/*     */ 
/*     */     
/*  29 */     end();
/*  30 */     stop();
/*     */     
/*  32 */     sideLength = (float)edgeRadius;
/*  33 */     sideLength /= 2.0F;
/*  34 */     start();
/*  35 */     if (color != null)
/*  36 */       color(color); 
/*  37 */     GL11.glEnable(2848);
/*  38 */     begin(6);
/*     */ 
/*     */     
/*  41 */     for (i = 0.0D; i <= 90.0D; i++) {
/*  42 */       double angle = i * 6.283185307179586D / 360.0D;
/*  43 */       vertex(x + width + sideLength * Math.cos(angle), y + height + sideLength * Math.sin(angle));
/*     */     } 
/*  45 */     vertex(x + width, y + height);
/*     */ 
/*     */     
/*  48 */     end();
/*  49 */     GL11.glDisable(2848);
/*  50 */     stop();
/*     */     
/*  52 */     sideLength = (float)edgeRadius;
/*  53 */     sideLength /= 2.0F;
/*  54 */     start();
/*  55 */     if (color != null)
/*  56 */       color(color); 
/*  57 */     GL11.glEnable(2848);
/*  58 */     begin(6);
/*     */ 
/*     */     
/*  61 */     for (i = 270.0D; i <= 360.0D; i++) {
/*  62 */       double angle = i * 6.283185307179586D / 360.0D;
/*  63 */       vertex(x + width + sideLength * Math.cos(angle), y + sideLength * Math.sin(angle) + sideLength);
/*     */     } 
/*  65 */     vertex(x + width, y + sideLength);
/*     */ 
/*     */     
/*  68 */     end();
/*  69 */     GL11.glDisable(2848);
/*  70 */     stop();
/*     */     
/*  72 */     sideLength = (float)edgeRadius;
/*  73 */     sideLength /= 2.0F;
/*  74 */     start();
/*  75 */     if (color != null)
/*  76 */       color(color); 
/*  77 */     GL11.glEnable(2848);
/*  78 */     begin(6);
/*     */ 
/*     */     
/*  81 */     for (i = 90.0D; i <= 180.0D; i++) {
/*  82 */       double angle = i * 6.283185307179586D / 360.0D;
/*  83 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + height + sideLength * Math.sin(angle));
/*     */     } 
/*  85 */     vertex(x + sideLength, y + height);
/*     */ 
/*     */     
/*  88 */     end();
/*  89 */     GL11.glDisable(2848);
/*  90 */     stop();
/*     */ 
/*     */     
/*  93 */     rect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);
/*     */ 
/*     */     
/*  96 */     rect(x, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
/*  97 */     rect(x + width, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
/*     */ 
/*     */     
/* 100 */     rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
/* 101 */     rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
/*     */   }
/*     */   
/*     */   public static void rect(double x, double y, double width, double height, Color color) {
/* 105 */     rect(x, y, width, height, true, color);
/*     */   }
/*     */   
/*     */   public static void rect(double x, double y, double width, double height, boolean filled, Color color) {
/* 109 */     start();
/* 110 */     if (color != null)
/* 111 */       color(color); 
/* 112 */     begin(filled ? 6 : 1);
/*     */ 
/*     */     
/* 115 */     vertex(x, y);
/* 116 */     vertex(x + width, y);
/* 117 */     vertex(x + width, y + height);
/* 118 */     vertex(x, y + height);
/* 119 */     if (!filled) {
/* 120 */       vertex(x, y);
/* 121 */       vertex(x, y + height);
/* 122 */       vertex(x + width, y);
/* 123 */       vertex(x + width, y + height);
/*     */     } 
/*     */     
/* 126 */     end();
/* 127 */     stop();
/*     */   }
/*     */   
/*     */   public static void start() {
/* 131 */     enable(3042);
/* 132 */     GL11.glBlendFunc(770, 771);
/* 133 */     disable(3553);
/* 134 */     disable(2884);
/* 135 */     disable(3008);
/* 136 */     disable(2929);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void begin(int glMode) {
/* 142 */     GL11.glBegin(glMode);
/*     */   }
/*     */   
/*     */   public static void end() {
/* 146 */     GL11.glEnd();
/*     */   }
/*     */   
/*     */   public static void vertex(double x, double y) {
/* 150 */     GL11.glVertex2d(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void stop() {
/* 156 */     enable(3008);
/* 157 */     enable(2929);
/* 158 */     enable(2884);
/* 159 */     enable(3553);
/* 160 */     disable(3042);
/* 161 */     color(Color.white);
/*     */   }
/*     */   
/*     */   public static void color(Color color) {
/* 165 */     if (color == null)
/* 166 */       color = Color.white; 
/* 167 */     color((color.getRed() / 255.0F), (color.getGreen() / 255.0F), (color.getBlue() / 255.0F), (color.getAlpha() / 255.0F));
/*     */   }
/*     */   
/*     */   public static void color(double red, double green, double blue, double alpha) {
/* 171 */     GL11.glColor4d(red, green, blue, alpha);
/*     */   }
/*     */   
/*     */   public static void enable(int glTarget) {
/* 175 */     GL11.glEnable(glTarget);
/*     */   }
/*     */   
/*     */   public static void disable(int glTarget) {
/* 179 */     GL11.glDisable(glTarget);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\render\RoundedUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */