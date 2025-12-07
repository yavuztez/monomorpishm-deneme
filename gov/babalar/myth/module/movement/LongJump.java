/*    */ package gov.babalar.myth.module.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ 
/*    */ public class LongJump
/*    */   extends Module {
/*    */   public LongJump() {
/* 12 */     super(ModuleType.MOTION, "LongJump", 0);
/*    */   }
/*    */   public boolean tp;
/*    */   
/*    */   public void onEnable() {
/* 17 */     super.onEnable();
/* 18 */     this.tp = false;
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void mot(EventMotion eventMotion) {
/* 23 */     if (Utility.getFallDistance() > 0.5D && !this.tp) {
/*    */       
/* 25 */       double x = Utility.getMotionX();
/* 26 */       double z = Utility.getMotionZ();
/* 27 */       double offX = 0.0D;
/* 28 */       double offZ = 0.0D;
/* 29 */       if (x > 0.0D) {
/*    */         
/* 31 */         offX += 1.5D;
/* 32 */       } else if (x < 0.0D) {
/*    */         
/* 34 */         offX -= 1.5D;
/*    */       } 
/* 36 */       if (z > 0.0D) {
/* 37 */         offZ += 1.5D;
/* 38 */       } else if (z < 0.0D) {
/*    */         
/* 40 */         offZ -= 1.5D;
/*    */       } 
/* 42 */       Utility.getThePlayer().e(Utility.getPosX() + offX, Utility.getPosY(), Utility.getPosZ() + offZ);
/* 43 */       this.tp = true;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\movement\LongJump.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */