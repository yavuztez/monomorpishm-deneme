/*    */ package gov.babalar.myth.module.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.utils.MovementUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Strafe
/*    */   extends Module
/*    */ {
/* 19 */   public static int ticks = 0;
/*    */   public Strafe() {
/* 21 */     super(ModuleType.MOTION, "Strafe", 22);
/* 22 */     setSuffix("Vanilla");
/*    */   }
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onMotionEvent(EventMotion event) {
/* 28 */     MovementUtil.setSpeed(MovementUtil.getSpeed());
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\movement\Strafe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */