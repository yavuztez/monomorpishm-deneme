/*    */ package gov.babalar.myth.utils.render;
/*    */ 
/*    */ import gov.babalar.myth.Client;
/*    */ import gov.babalar.myth.Utility;
/*    */ import net.minecraft.client.Pe;
/*    */ import net.minecraft.client.eT;
/*    */ import org.lwjgl.opengl.EXTFramebufferObject;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ public class StencilUtil
/*    */ {
/* 13 */   static Pe mc = Client.mc;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void checkSetupFBO(eT framebuffer) {
/* 20 */     if (framebuffer != null && 
/* 21 */       framebuffer.a > -1) {
/* 22 */       setupFBO(framebuffer);
/* 23 */       framebuffer.a = -1;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setupFBO(eT framebuffer) {
/* 33 */     EXTFramebufferObject.glDeleteRenderbuffersEXT(framebuffer.a);
/* 34 */     int stencilDepthBufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
/* 35 */     EXTFramebufferObject.glBindRenderbufferEXT(36161, stencilDepthBufferID);
/* 36 */     EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Utility.getDisplayWidth(), Utility.getDisplayHeight());
/* 37 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencilDepthBufferID);
/* 38 */     EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencilDepthBufferID);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void initStencilToWrite() {
/* 45 */     Utility.getFrameBuffer().b(false);
/* 46 */     checkSetupFBO(Utility.getFrameBuffer());
/* 47 */     GL11.glClear(1024);
/* 48 */     GL11.glEnable(2960);
/*    */     
/* 50 */     GL11.glStencilFunc(519, 1, 1);
/* 51 */     GL11.glStencilOp(7681, 7681, 7681);
/* 52 */     GL11.glColorMask(false, false, false, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void readStencilBuffer(int ref) {
/* 61 */     GL11.glColorMask(true, true, true, true);
/* 62 */     GL11.glStencilFunc(514, ref, 1);
/* 63 */     GL11.glStencilOp(7680, 7680, 7680);
/*    */   }
/*    */   
/*    */   public static void uninitStencilBuffer() {
/* 67 */     GL11.glDisable(2960);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\render\StencilUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */