/*    */ package gov.babalar.myth.module.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Step
/*    */   extends Module
/*    */ {
/*    */   public Step() {
/* 18 */     super(ModuleType.MOTION, "Step", 0);
/* 19 */     setSuffix("10");
/*    */   }
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onMotionEvent(EventMotion event) {
/* 25 */     Utility.setStepHeight(10.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 30 */     Utility.setStepHeight(0.6F);
/* 31 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\movement\Step.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */