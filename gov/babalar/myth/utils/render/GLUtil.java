/*    */ package gov.babalar.myth.utils.render;
/*    */ 
/*    */ import net.minecraft.client.PP;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GLUtil
/*    */ {
/*    */   public static void render(int mode, Runnable render) {
/* 11 */     GL11.glBegin(mode);
/* 12 */     render.run();
/* 13 */     GL11.glEnd();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void startBlend() {
/* 18 */     PP.o();
/* 19 */     PP.M(770, 771);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void endBlend() {
/* 25 */     PP.z();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void setup2DRendering(boolean blend) {
/* 30 */     if (blend)
/*    */     {
/* 32 */       startBlend();
/*    */     }
/* 34 */     GL11.glDisable(3553);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void end2DRendering() {
/* 39 */     GL11.glEnable(3553);
/* 40 */     endBlend();
/*    */   }
/*    */   
/*    */   public static void setup2DRendering(Runnable f) {
/* 44 */     startBlend();
/* 45 */     GL11.glDisable(3553);
/* 46 */     f.run();
/* 47 */     GL11.glEnable(3553);
/* 48 */     endBlend();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\render\GLUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */