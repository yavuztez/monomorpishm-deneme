/*    */ package gov.babalar.myth.module.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.utils.MovementUtil;
/*    */ import gov.babalar.myth.utils.TimerUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Phase
/*    */   extends Module
/*    */ {
/* 16 */   private final TimerUtil clipTimer = new TimerUtil();
/*    */   
/*    */   public Phase() {
/* 19 */     super(ModuleType.MOTION, "Phase", 0);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion event) {
/* 24 */     if (!(Utility.getThePlayer()).pQ) {
/*    */       return;
/*    */     }
/*    */     
/* 28 */     this.clipTimer.reset();
/*    */     
/* 30 */     double yaw = MovementUtil.getDirection();
/* 31 */     double offset = 0.08D;
/*    */     
/* 33 */     Utility.setMotionX(0.0D);
/* 34 */     Utility.setMotionZ(0.0D);
/*    */ 
/*    */     
/* 37 */     if (this.clipTimer.hasTimePassed(140L)) {
/*    */       return;
/*    */     }
/*    */     
/* 41 */     Utility.setPosition(
/* 42 */         Utility.getPosX() - Math.sin(yaw) * offset, 
/* 43 */         Utility.getPosY(), 
/* 44 */         Utility.getPosZ() + Math.cos(yaw) * offset);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\movement\Phase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */