/*    */ package monomorphism.manager.module.impl.world;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.time.TimerUtil;
/*    */ import net.minecraft.nn;
/*    */ import net.minecraft.o6;
/*    */ import net.minecraft.og;
/*    */ import net.minecraft.q_;
/*    */ import net.minecraft.vR;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChestStealer
/*    */   extends Module
/*    */ {
/*    */   public ChestStealer() {
/* 24 */     super("Stealer", 0, Category.World);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onUpdate(EventMotion event) {
/* 29 */     if (mc.aQ.ca != null && mc.aQ.ca instanceof vR) {
/* 30 */       vR ew = (vR)mc.aQ.ca;
/* 31 */       for (int k = 0; k < ew.a().b(); k++) {
/* 32 */         if (ew.a().b(k) != null && 
/* 33 */           itemWhitelisted(ew.a().b(k)) && 
/* 34 */           TimerUtil.delayTimer(9.0D)) {
/* 35 */           mc.Q.a(ew.e, k, 0, 1, (nn)mc.aQ);
/* 36 */           TimerUtil.resetTimer();
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean itemWhitelisted(q_ itemStack) {
/* 43 */     o6 item = itemStack.a();
/*    */     
/* 45 */     return !(!(item instanceof net.minecraft.oZ) && !(item instanceof net.minecraft.pK) && 
/* 46 */       !(item instanceof net.minecraft.oG) && 
/* 47 */       !(item instanceof net.minecraft.p6) && 
/* 48 */       !(item instanceof net.minecraft.pQ) && 
/* 49 */       item != og.bD && 
/* 50 */       item != og.bV && 
/* 51 */       item != og.bU);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\world\ChestStealer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */