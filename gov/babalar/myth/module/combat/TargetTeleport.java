/*    */ package gov.babalar.myth.module.combat;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import net.minecraft.BlockPos;
/*    */ import net.minecraft.DN;
/*    */ 
/*    */ public class TargetTeleport
/*    */   extends Module
/*    */ {
/*    */   public TargetTeleport() {
/* 15 */     super(ModuleType.COMBAT, "Target Teleport", 41);
/*    */   }
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion event) {
/* 19 */     if (KillAura.target != null) {
/*    */       
/* 21 */       double targetPosX = Utility.getPosX((DN)KillAura.target);
/* 22 */       double targetPosY = Utility.getPosY((DN)KillAura.target);
/* 23 */       double targetPosZ = Utility.getPosZ((DN)KillAura.target);
/* 24 */       BlockPos upperBlockPos = new BlockPos(targetPosX, targetPosY + 2.0D, targetPosZ);
/* 25 */       BlockPos downBlockPos = new BlockPos(targetPosX, targetPosY - 1.0D, targetPosZ);
/*    */       
/*    */       try {
/* 28 */         if (Utility.getBlockState(upperBlockPos).N() instanceof net.minecraft.fD) {
/* 29 */           Utility.getThePlayer().e(targetPosX, targetPosY + 2.0D, targetPosZ);
/* 30 */         } else if (Utility.getBlockState(downBlockPos).N() instanceof net.minecraft.fD) {
/* 31 */           Utility.getThePlayer().e(targetPosX, targetPosY - 1.0D, targetPosZ);
/*    */         } else {
/* 33 */           Utility.getThePlayer().e(targetPosX, targetPosY, targetPosZ);
/*    */         } 
/* 35 */       } catch (Exception e) {
/*    */         
/* 37 */         Utility.getThePlayer().e(targetPosX, targetPosY, targetPosZ);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\combat\TargetTeleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */