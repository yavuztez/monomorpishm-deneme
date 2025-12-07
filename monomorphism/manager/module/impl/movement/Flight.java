/*    */ package monomorphism.manager.module.impl.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import java.lang.reflect.Field;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.Client;
/*    */ import monomorphism.manager.module.util.time.MSTimer;
/*    */ import net.minecraft.aO;
/*    */ import net.minecraft.fx;
/*    */ import net.minecraft.m7;
/*    */ import net.minecraft.yg;
/*    */ 
/*    */ 
/*    */ public class Flight
/*    */   extends Module
/*    */ {
/* 19 */   private final MSTimer groundTimer = new MSTimer();
/*    */   public Flight() {
/* 21 */     super("Flight", 33, Category.Movement);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion event) {
/* 26 */     handleVanillaKickBypass();
/* 27 */     Client.mc.aQ.b7.c = true;
/* 28 */     Client.mc.aQ.b7.f.a(Boolean.valueOf(true));
/* 29 */     Client.mc.aQ.b7.h.a(Boolean.valueOf(true));
/* 30 */     Client.mc.aQ.b7.g = true;
/* 31 */     Client.mc.aQ.T = 0.0D;
/* 32 */     Client.mc.aQ.ag = true;
/* 33 */     Client.mc.aQ.b7.a(0.25F);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onDisable() {
/* 38 */     Client.mc.aQ.b7.c = false;
/* 39 */     Client.mc.aQ.b7.f.a(Boolean.valueOf(false));
/* 40 */     Client.mc.aQ.b7.h.a(Boolean.valueOf(false));
/* 41 */     Client.mc.aQ.b7.g = false;
/* 42 */     super.onDisable();
/*    */   }
/*    */   
/*    */   private void handleVanillaKickBypass() {
/* 46 */     if (!this.groundTimer.hasTimePassed(1000L))
/*    */       return; 
/* 48 */     double ground = calculateGround();
/*    */     
/* 50 */     float f = (getThePlayer()).w - (getThePlayer()).at;
/*    */     double posY;
/* 52 */     for (posY = getPosY(); posY > ground; posY -= 8.0D) {
/* 53 */       sendPacket((yg)new fx(getPosX(), posY, getPosZ(), true, f));
/*    */       
/* 55 */       if (posY - 8.0D < ground)
/*    */         break; 
/*    */     } 
/* 58 */     sendPacket((yg)new fx(getPosX(), ground, getPosZ(), true, f));
/*    */ 
/*    */     
/* 61 */     for (posY = ground; posY < getPosY(); posY += 8.0D) {
/* 62 */       sendPacket((yg)new fx(getPosX(), posY, getPosZ(), true, f));
/*    */       
/* 64 */       if (posY + 8.0D > getPosY())
/*    */         break; 
/*    */     } 
/* 67 */     sendPacket((yg)new fx(getPosX(), getPosY(), getPosZ(), true, f));
/*    */     
/* 69 */     this.groundTimer.reset();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private double calculateGround() {
/* 75 */     aO playerBoundingBox = null;
/*    */     try {
/* 77 */       Field field = m7.class.getDeclaredField("ar");
/* 78 */       field.setAccessible(true);
/* 79 */       playerBoundingBox = (aO)field.get(mc.aQ);
/* 80 */     } catch (Exception exception) {}
/* 81 */     double blockHeight = 1.0D;
/*    */     
/* 83 */     for (double ground = getPosY(); ground > 0.0D; ground -= blockHeight) {
/* 84 */       aO customBox = new aO(playerBoundingBox.c, ground + blockHeight, playerBoundingBox.h, playerBoundingBox.f, ground, playerBoundingBox.g);
/*    */       
/* 86 */       if (mc.I.b(customBox)) {
/* 87 */         if (blockHeight <= 0.05D) {
/* 88 */           return ground + blockHeight;
/*    */         }
/* 90 */         ground += blockHeight;
/* 91 */         blockHeight = 0.05D;
/*    */       } 
/*    */     } 
/*    */     
/* 95 */     return 0.0D;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\movement\Flight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */