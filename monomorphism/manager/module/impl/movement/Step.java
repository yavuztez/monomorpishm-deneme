/*    */ package monomorphism.manager.module.impl.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.Client;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Step
/*    */   extends Module
/*    */ {
/*    */   public Step() {
/* 15 */     super("Step", 0, Category.Movement);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion event) {
/* 20 */     Client.mc.aQ.I = 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 25 */     Client.mc.aQ.I = 0.5F;
/* 26 */     super.onDisable();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\movement\Step.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */