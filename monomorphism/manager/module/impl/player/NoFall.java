/*    */ package monomorphism.manager.module.impl.player;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import net.minecraft.fF;
/*    */ import net.minecraft.yg;
/*    */ 
/*    */ 
/*    */ public class NoFall
/*    */   extends Module
/*    */ {
/*    */   public NoFall() {
/* 15 */     super("No Fall", 0, Category.Player);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onUpdate(EventMotion event) {
/* 20 */     if (mc.aQ.w > 2.0F)
/* 21 */       mc.aQ.ct.a((yg)new fF(true)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\player\NoFall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */