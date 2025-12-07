/*    */ package monomorphism.manager.module.impl.combat;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventPacket;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoKnockback
/*    */   extends Module
/*    */ {
/*    */   public NoKnockback() {
/* 14 */     super("Velocity", 44, Category.Combat);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onPacket(EventPacket event) {
/* 19 */     if (event.send)
/* 20 */       return;  if (event.packet instanceof net.minecraft.fa)
/* 21 */       event.cancel(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\combat\NoKnockback.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */