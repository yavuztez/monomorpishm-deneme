/*    */ package gov.babalar.myth.module.combat;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.event.EventReceivePacket;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Velocity
/*    */   extends Module
/*    */ {
/*    */   public Velocity() {
/* 14 */     super(ModuleType.COMBAT, "Velocity", 0);
/* 15 */     toggle();
/* 16 */     setSuffix("Zero");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onPacketReceive(EventReceivePacket e) {
/* 24 */     if (e.getPacket() instanceof net.minecraft.HY)
/*    */     {
/* 26 */       e.cancel();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\combat\Velocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */