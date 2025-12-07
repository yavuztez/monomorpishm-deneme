/*    */ package gov.babalar.myth.module.movement;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.utils.player.ChatUtil;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import net.minecraft.DN;
/*    */ import net.minecraft.DZ;
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
/*    */ 
/*    */ 
/*    */ public class Teleport
/*    */   extends Module
/*    */ {
/*    */   public Teleport() {
/* 28 */     super(ModuleType.MOTION, "Teleport", 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isTeleported = false;
/*    */   
/*    */   public static boolean waitingResponse = true;
/*    */   double toTeleportX;
/*    */   double toTeleportY;
/*    */   double toTeleportZ;
/*    */   
/*    */   public void onEnable() {
/* 40 */     List<DZ> closestEntities = Utility.getEntityList();
/* 41 */     if (closestEntities.size() == 0) {
/* 42 */       toggle();
/*    */       return;
/*    */     } 
/* 45 */     closestEntities.sort(Comparator.comparingDouble(Utility::getDistanceToEntity));
/* 46 */     for (DZ entity : closestEntities) {
/*    */       
/* 48 */       if (Utility.getDistanceToEntity(entity) < 120.0F && entity != Utility.getThePlayer()) {
/*    */         
/* 50 */         ChatUtil.print(true, String.format("§eTrying teleport to §a%s!", new Object[] { Utility.getPlayerName(entity) }));
/* 51 */         this.toTeleportX = Utility.getPosX((DN)entity);
/* 52 */         this.toTeleportY = Utility.getPosY((DN)entity);
/* 53 */         this.toTeleportZ = Utility.getPosZ((DN)entity);
/*    */         
/* 55 */         for (int i = 0; i < 320; i++) {
/* 56 */           if (Utility.getPosX((DN)entity) != this.toTeleportX) this.toTeleportX = Utility.getPosX((DN)entity); 
/* 57 */           if (Utility.getPosY((DN)entity) != this.toTeleportY) this.toTeleportY = Utility.getPosY((DN)entity); 
/* 58 */           if (Utility.getPosZ((DN)entity) != this.toTeleportZ) this.toTeleportZ = Utility.getPosZ((DN)entity); 
/* 59 */           Utility.getThePlayer().e(this.toTeleportX, this.toTeleportY, this.toTeleportZ);
/*    */         } 
/* 61 */         Utility.getThePlayer().e(this.toTeleportX, this.toTeleportY, this.toTeleportZ);
/*    */ 
/*    */ 
/*    */ 
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 73 */     toggle();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\movement\Teleport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */