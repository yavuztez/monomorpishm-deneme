/*    */ package gov.babalar.myth.module.misc;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.utils.BlockCache;
/*    */ import gov.babalar.myth.utils.ScaffoldUtils;
/*    */ import net.minecraft.Tz;
/*    */ import net.minecraft.Vw;
/*    */ import net.minecraft.fz;
/*    */ 
/*    */ public class Scaffold
/*    */   extends Module {
/*    */   private int slot;
/*    */   private int prevSlot;
/*    */   public static double keepYCoord;
/*    */   private BlockCache blockCache;
/*    */   private BlockCache lastBlockCache;
/*    */   public int t;
/*    */   
/*    */   public Scaffold() {
/* 24 */     super(ModuleType.MISC, "Scaffold", 45);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 29 */     this.lastBlockCache = null;
/* 30 */     if (Utility.getThePlayer() != null) {
/*    */       
/* 32 */       this.prevSlot = (Utility.getThePlayer()).Fc.b;
/* 33 */       this.slot = (Utility.getThePlayer()).Fc.b;
/*    */     } 
/* 35 */     super.onEnable();
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 40 */     (Utility.getThePlayer()).Fc.b = this.prevSlot;
/* 41 */     super.onDisable();
/*    */   }
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion e) {
/* 47 */     this.t++;
/* 48 */     if ((Utility.getGameSettings()).uw.f() && Utility.getMotionX() == 0.0D && Utility.getMotionZ() == 0.0D)
/*    */     {
/* 50 */       if (this.t % 4 == 1) {
/* 51 */         Utility.setMotionY(0.4195464D);
/* 52 */         Utility.getThePlayer().e(Utility.getPosX() - 0.035D, Utility.getPosY(), Utility.getPosZ());
/* 53 */       } else if (this.t % 4 == 0) {
/* 54 */         Utility.setMotionY(-0.5D);
/* 55 */         Utility.getThePlayer().e(Utility.getPosX() + 0.035D, Utility.getPosY(), Utility.getPosZ());
/*    */       } 
/*    */     }
/* 58 */     this.blockCache = ScaffoldUtils.getBlockInfo();
/* 59 */     if (this.blockCache != null) {
/* 60 */       this.lastBlockCache = ScaffoldUtils.getBlockInfo();
/*    */     } else {
/*    */       return;
/*    */     } 
/*    */     
/* 65 */     int slot = ScaffoldUtils.getBlockSlot();
/* 66 */     if (this.blockCache == null || this.lastBlockCache == null || slot == -1) {
/*    */       return;
/*    */     }
/* 69 */     if (Utility.getFallDistance() == 0.0F) {
/* 70 */       keepYCoord = Math.floor(Utility.getPosY() - 1.0D);
/*    */     }
/* 72 */     if ((Utility.getThePlayer()).Fc.b != slot) {
/* 73 */       (Utility.getThePlayer()).Fc.b = slot;
/*    */     }
/* 75 */     if (this.slot != slot) {
/* 76 */       this.slot = slot;
/* 77 */       Utility.sendPacket((Tz)new Vw(this.slot));
/*    */     } 
/*    */     
/* 80 */     if (Utility.onPlayerRightClick(Utility.getStackInSlot(this.slot), this.lastBlockCache.getPosition(), this.lastBlockCache.getFacing(), ScaffoldUtils.getHypixelVec3(this.lastBlockCache)))
/*    */     {
/* 82 */       Utility.sendPacket((Tz)new fz());
/*    */     }
/* 84 */     this.blockCache = null;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\misc\Scaffold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */