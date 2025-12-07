/*    */ package monomorphism.manager.module.impl.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.move.MovementUtil;
/*    */ import monomorphism.manager.module.util.time.TickTimer;
/*    */ 
/*    */ public class Phase
/*    */   extends Module {
/*    */   private boolean mineplexClip;
/*    */   private final TickTimer mineplexTickTimer;
/*    */   
/*    */   public Phase() {
/* 16 */     super("Phase", 0, Category.Movement);
/*    */ 
/*    */     
/* 19 */     this.mineplexTickTimer = new TickTimer();
/*    */   }
/*    */   @Subscribe
/*    */   public void onUpdate(EventMotion event) {
/* 23 */     if (mc.aQ.H)
/* 24 */       this.mineplexClip = true; 
/* 25 */     if (!this.mineplexClip) {
/*    */       return;
/*    */     }
/* 28 */     this.mineplexTickTimer.update();
/*    */     
/* 30 */     setMotionX(0.0D);
/* 31 */     setMotionZ(0.0D);
/*    */     
/* 33 */     if (this.mineplexTickTimer.hasTimePassed(3)) {
/* 34 */       this.mineplexTickTimer.reset();
/* 35 */       this.mineplexClip = false;
/* 36 */     } else if (this.mineplexTickTimer.hasTimePassed(1)) {
/* 37 */       double offset = this.mineplexTickTimer.hasTimePassed(2) ? 1.6D : 0.06D;
/* 38 */       double direction = MovementUtil.getDirection();
/*    */       
/* 40 */       mc.aQ.d(getPosX() + -Math.sin(direction) * offset, getPosY(), getPosZ() + Math.cos(direction) * offset);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\movement\Phase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */