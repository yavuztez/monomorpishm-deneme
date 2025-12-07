/*    */ package gov.babalar.myth.module.misc;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventPacketSent;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.utils.other.ReflectionUtils;
/*    */ import net.minecraft.br;
/*    */ 
/*    */ public class NoFall extends Module {
/*    */   public NoFall() {
/* 13 */     super(ModuleType.MISC, "NoFall", 0);
/*    */   }
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onPacketSend(EventPacketSent e) {
/* 19 */     if (e.getPacket() instanceof br) {
/*    */       
/* 21 */       br c03 = (br)e.getPacket();
/* 22 */       boolean onGround = ((Boolean)ReflectionUtils.getField(br.class, "N", c03)).booleanValue();
/* 23 */       if (!onGround && Utility.getFallDistance() > 3.0F) {
/* 24 */         ReflectionUtils.setField(br.class, "N", c03, Boolean.valueOf(true));
/* 25 */         Utility.setFallDistance(0.0F);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\misc\NoFall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */