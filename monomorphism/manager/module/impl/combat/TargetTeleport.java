/*    */ package monomorphism.manager.module.impl.combat;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.mapper.Entity;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import net.minecraft.nn;
/*    */ 
/*    */ public class TargetTeleport
/*    */   extends Module {
/*    */   public TargetTeleport() {
/* 13 */     super("Target Teleport", 41, Category.Combat);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion event) {
/* 18 */     if (KillAura.target != null) {
/* 19 */       nn target = KillAura.target;
/* 20 */       double targetPosX = Entity.getPosX(target);
/* 21 */       double targetPosY = Entity.getPosY(target);
/* 22 */       double targetPosZ = Entity.getPosZ(target);
/* 23 */       double finalY = targetPosY + 2.0D;
/* 24 */       getThePlayer().d(targetPosX, finalY, targetPosZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\combat\TargetTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */