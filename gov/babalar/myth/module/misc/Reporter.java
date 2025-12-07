/*    */ package gov.babalar.myth.module.misc;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.utils.player.ChatUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.DZ;
/*    */ 
/*    */ 
/*    */ public class Reporter
/*    */   extends Module
/*    */ {
/* 17 */   List<String> reported = new ArrayList<>();
/*    */   
/*    */   public Reporter() {
/* 20 */     super(ModuleType.MISC, "Reporter", 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onUpdate(EventMotion e) {
/* 27 */     setSuffix("Reported: " + this.reported.size());
/* 28 */     for (DZ entity : Utility.getEntityList()) {
/* 29 */       String name = Utility.getPlayerName(entity);
/* 30 */       if (entity != Utility.getThePlayer() && !this.reported.contains(name)) {
/*    */         
/* 32 */         this.reported.add(name);
/* 33 */         String message = "/report hile " + name;
/* 34 */         ChatUtil.send(message);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\misc\Reporter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */