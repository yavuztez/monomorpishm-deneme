/*    */ package monomorphism.manager.module.impl.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.move.MovementUtil;
/*    */ 
/*    */ public class Strafe
/*    */   extends Module
/*    */ {
/*    */   public Strafe() {
/* 13 */     super("Strafe", 0, Category.Movement);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion event) {
/* 18 */     MovementUtil.setSpeed(MovementUtil.getSpeed());
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\movement\Strafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */