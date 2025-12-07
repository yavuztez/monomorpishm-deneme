/*    */ package monomorphism.manager.module.impl.player;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Speedmine
/*    */   extends Module
/*    */ {
/*    */   public Speedmine() {
/* 14 */     super("Hud", 0, Category.Player);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onUpdate(EventMotion event) {}
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\player\Speedmine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */