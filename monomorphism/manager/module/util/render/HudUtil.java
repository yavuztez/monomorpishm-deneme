/*    */ package monomorphism.manager.module.util.render;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ public class HudUtil
/*    */ {
/*    */   public static int getRainbow(int speed, int offset) {
/*  9 */     float hue = (float)((System.currentTimeMillis() + offset) % speed);
/* 10 */     return Color.getHSBColor(hue /= speed, 0.5F, 1.0F).getRGB();
/*    */   }
/*    */   
/*    */   public static void pushMatrix() {
/* 14 */     GL11.glPushMatrix();
/*    */   }
/*    */   
/*    */   public static void popMatrix() {
/* 18 */     GL11.glPopMatrix();
/*    */   }
/*    */   
/*    */   public static void scale(float p_179152_0_, float p_179152_1_, float p_179152_2_) {
/* 22 */     GL11.glScalef(p_179152_0_, p_179152_1_, p_179152_2_);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\render\HudUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */