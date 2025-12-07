/*    */ package monomorphism.manager.module.impl.player;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.Client;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Sprint
/*    */   extends Module
/*    */ {
/*    */   public Sprint() {
/* 15 */     super("Sprint", 0, Category.Player);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion event) {
/* 20 */     Client.mc.aQ.l(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\player\Sprint.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */