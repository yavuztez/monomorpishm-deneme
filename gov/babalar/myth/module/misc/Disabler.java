/*    */ package gov.babalar.myth.module.misc;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.event.EventPacketSent;
/*    */ import gov.babalar.myth.managers.ModuleManager;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import java.lang.reflect.Field;
/*    */ import net.minecraft.H8;
/*    */ import net.minecraft.df;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Disabler
/*    */   extends Module
/*    */ {
/*    */   public Disabler() {
/* 26 */     super(ModuleType.MISC, "Disabler", 0);
/* 27 */     toggle();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void event(EventPacketSent eventPacketSent) {
/* 34 */     if (eventPacketSent.getPacket() instanceof H8) {
/*    */       
/*    */       try {
/* 37 */         Field f = H8.class.getDeclaredField("E");
/* 38 */         f.setAccessible(true);
/* 39 */         df df = (df)f.get(eventPacketSent.getPacket());
/* 40 */         if (df.equals(df.OPEN_INVENTORY))
/*    */         {
/* 42 */           eventPacketSent.cancel();
/*    */         }
/* 44 */       } catch (Exception e) {
/* 45 */         e.printStackTrace();
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 52 */     ModuleManager.c03Index = 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\misc\Disabler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */