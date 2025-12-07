/*    */ package monomorphism.manager.module.impl.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import excluded.MotionY;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.move.MovementUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Speed
/*    */   extends Module
/*    */ {
/*    */   public Speed() {
/* 16 */     super("TestSpeed", 21, Category.Movement);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion event) {
/* 21 */     if (MovementUtil.isMoving()) {
/* 22 */       if (onGround()) {
/* 23 */         MovementUtil.setSpeed(1.5D);
/* 24 */         MotionY.degistir(0.44D);
/*    */         return;
/*    */       } 
/* 27 */       MovementUtil.setSpeed(1.0D);
/*    */     } 
/* 29 */     if (MovementUtil.isMoving())
/* 30 */       TargetStrafe.strafe(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\movement\Speed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */