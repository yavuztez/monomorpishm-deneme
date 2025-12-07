/*    */ package monomorphism.manager.module.util.render;
/*    */ 
/*    */ public class DecelerateAnimation
/*    */   extends Animation
/*    */ {
/*    */   public DecelerateAnimation(int ms, double endPoint) {
/*  7 */     super(ms, endPoint);
/*    */   }
/*    */   
/*    */   public DecelerateAnimation(int ms, double endPoint, Direction direction) {
/* 11 */     super(ms, endPoint, direction);
/*    */   }
/*    */   
/*    */   protected double getEquation(double x) {
/* 15 */     double x1 = x / this.duration;
/* 16 */     return 1.0D - (x1 - 1.0D) * (x1 - 1.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\render\DecelerateAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */