/*    */ package gov.babalar.myth.utils.render;
/*    */ 
/*    */ import gov.babalar.myth.utils.math.MathHelper;
/*    */ import java.awt.Color;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ColorUtil
/*    */ {
/*    */   public static int applyOpacity(int color, float opacity) {
/* 11 */     Color old = new Color(color);
/* 12 */     return applyOpacity(old, opacity).getRGB();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static Color applyOpacity(Color color, float opacity) {
/* 18 */     opacity = Math.min(1.0F, Math.max(0.0F, opacity));
/* 19 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(color.getAlpha() * opacity));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Color brighter(Color color, float FACTOR) {
/* 27 */     int r = color.getRed();
/* 28 */     int g = color.getGreen();
/* 29 */     int b = color.getBlue();
/* 30 */     int alpha = color.getAlpha();
/* 31 */     int i = (int)(1.0D / (1.0D - FACTOR));
/* 32 */     if (r == 0 && g == 0 && b == 0) {
/* 33 */       return new Color(i, i, i, alpha);
/*    */     }
/* 35 */     if (r > 0 && r < i) r = i; 
/* 36 */     if (g > 0 && g < i) g = i; 
/* 37 */     if (b > 0 && b < i) b = i;
/*    */     
/* 39 */     return new Color(Math.min((int)(r / FACTOR), 255), 
/* 40 */         Math.min((int)(g / FACTOR), 255), 
/* 41 */         Math.min((int)(b / FACTOR), 255), alpha);
/*    */   }
/*    */ 
/*    */   
/*    */   public static Color interpolateColorsBackAndForth(int speed, int index, Color start, Color end, boolean trueColor) {
/* 46 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
/* 47 */     angle = ((angle >= 180) ? (360 - angle) : angle) * 2;
/* 48 */     return trueColor ? interpolateColorHue(start, end, angle / 360.0F) : interpolateColorC(start, end, angle / 360.0F);
/*    */   }
/*    */   
/*    */   public static Color interpolateColorHue(Color color1, Color color2, float amount) {
/* 52 */     amount = Math.min(1.0F, Math.max(0.0F, amount));
/*    */     
/* 54 */     float[] color1HSB = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
/* 55 */     float[] color2HSB = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
/*    */     
/* 57 */     Color resultColor = Color.getHSBColor(MathHelper.interpolateFloat(color1HSB[0], color2HSB[0], amount), 
/* 58 */         MathHelper.interpolateFloat(color1HSB[1], color2HSB[1], amount), MathHelper.interpolateFloat(color1HSB[2], color2HSB[2], amount));
/*    */     
/* 60 */     return applyOpacity(resultColor, MathHelper.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount) / 255.0F);
/*    */   }
/*    */   
/*    */   public static Color interpolateColorC(Color color1, Color color2, float amount) {
/* 64 */     amount = Math.min(1.0F, Math.max(0.0F, amount));
/* 65 */     return new Color(MathHelper.interpolateInt(color1.getRed(), color2.getRed(), amount), 
/* 66 */         MathHelper.interpolateInt(color1.getGreen(), color2.getGreen(), amount), 
/* 67 */         MathHelper.interpolateInt(color1.getBlue(), color2.getBlue(), amount), 
/* 68 */         MathHelper.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\render\ColorUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */