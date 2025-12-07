/*    */ package monomorphism.manager.module.impl.combat;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import java.lang.reflect.Constructor;
/*    */ import monomorphism.manager.event.EventPacket;
/*    */ import monomorphism.manager.mapper.Entity;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import net.minecraft.yg;
/*    */ 
/*    */ public class Criticals
/*    */   extends Module
/*    */ {
/*    */   private Constructor<?> packetPosConstructor;
/*    */   
/*    */   public Criticals() {
/* 17 */     super("Criticals", 0, Category.Combat);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onPacket(EventPacket event) {
/* 24 */     if (event.send && event.packet.getClass().getSimpleName().equals("O") && 
/* 25 */       Entity.onGround()) {
/* 26 */       doCrit();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private void doCrit() {
/* 32 */     double x = Entity.getPosX();
/* 33 */     double y = Entity.getPosY();
/* 34 */     double z = Entity.getPosZ();
/*    */ 
/*    */     
/* 37 */     sendPosPacket(x, y + 0.0625D, z, false);
/* 38 */     sendPosPacket(x, y, z, false);
/* 39 */     sendPosPacket(x, y + 1.1E-5D, z, false);
/* 40 */     sendPosPacket(x, y, z, false);
/*    */   }
/*    */ 
/*    */   
/*    */   private void sendPosPacket(double x, double y, double z, boolean onGround) {
/*    */     try {
/* 46 */       if (this.packetPosConstructor == null) {
/* 47 */         findPacketConstructor();
/*    */       }
/*    */       
/* 50 */       if (this.packetPosConstructor != null) {
/*    */         
/* 52 */         Object packet = this.packetPosConstructor.newInstance(new Object[] { Double.valueOf(x), Double.valueOf(y), Double.valueOf(z), Boolean.valueOf(onGround) });
/*    */         
/* 54 */         Entity.sendPacket((yg)packet);
/*    */       } 
/* 56 */     } catch (Exception e) {
/* 57 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void findPacketConstructor() {
/*    */     try {
/* 65 */       Class<?> packetPlayerClass = Class.forName("net.minecraft.client.fx");
/*    */       
/* 67 */       for (Class<?> subClass : packetPlayerClass.getDeclaredClasses()) {
/*    */         
/* 69 */         for (Constructor<?> c : subClass.getDeclaredConstructors()) {
/* 70 */           c.setAccessible(true);
/* 71 */           if (c.getParameterCount() == 4) {
/* 72 */             Class<?>[] types = c.getParameterTypes();
/* 73 */             if (types[0] == double.class && types[3] == boolean.class) {
/* 74 */               this.packetPosConstructor = c;
/*    */               
/*    */               return;
/*    */             } 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */       
/*    */       try {
/* 83 */         Class<?> kClass = Class.forName("net.minecraft.k");
/* 84 */         for (Class<?> subClass : kClass.getDeclaredClasses()) {
/* 85 */           for (Constructor<?> c : subClass.getDeclaredConstructors()) {
/* 86 */             c.setAccessible(true);
/* 87 */             if (c.getParameterCount() == 4 && c.getParameterTypes()[0] == double.class) {
/* 88 */               this.packetPosConstructor = c;
/*    */               return;
/*    */             } 
/*    */           } 
/*    */         } 
/* 93 */       } catch (Exception exception) {}
/*    */     }
/* 95 */     catch (Exception e) {
/* 96 */       System.out.println("Criticals Modulu Packet Classini Bulamadi!");
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\combat\Criticals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */