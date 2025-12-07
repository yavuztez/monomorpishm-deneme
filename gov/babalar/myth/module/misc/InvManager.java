/*    */ package gov.babalar.myth.module.misc;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.setting.s.SettingNumber;
/*    */ import gov.babalar.myth.utils.other.InventoryUtil;
/*    */ import net.minecraft.f3;
/*    */ import net.minecraft.t;
/*    */ 
/*    */ public class InvManager
/*    */   extends Module
/*    */ {
/* 16 */   private static final SettingNumber slotWeapon = new SettingNumber(1.0D, 1.0D, 9.0D, 1.0D, "Weapon Slot");
/*    */ 
/*    */ 
/*    */   
/*    */   public InvManager() {
/* 21 */     super(ModuleType.MISC, "InvManager", 0);
/* 22 */     this.abstractSettings.add(slotWeapon);
/*    */   }
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onMotion(EventMotion e) {
/* 28 */     getBestWeapon();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private void getBestWeapon() {
/* 34 */     for (int i = 9; i < 45; i++) {
/* 35 */       f3 is = (Utility.getThePlayer()).FF.A(i).L();
/* 36 */       if (is != null && is.C() instanceof net.minecraft.Lg && isBestWeapon(is) && InventoryUtil.getDamageScore(is) > 0.0F) {
/* 37 */         InventoryUtil.swap(i, (int)slotWeapon.value - 36);
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private void dropPots() {
/* 44 */     int pots = 0;
/* 45 */     for (int i = 9; i < 45; i++) {
/* 46 */       f3 is = (Utility.getThePlayer()).FF.A(i).L();
/* 47 */       if (is != null && is.toString().contains("instant")) {
/* 48 */         InventoryUtil.swap(i, (int)slotWeapon.value - 36);
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean isBestArmor(f3 stack, int type) {
/* 55 */     String typeStr = "";
/* 56 */     switch (type) {
/*    */       case 1:
/* 58 */         typeStr = "helmet";
/*    */         break;
/*    */       case 2:
/* 61 */         typeStr = "chestplate";
/*    */         break;
/*    */       case 3:
/* 64 */         typeStr = "leggings";
/*    */         break;
/*    */       case 4:
/* 67 */         typeStr = "boots";
/*    */         break;
/*    */     } 
/* 70 */     if (stack.U().contains(typeStr)) {
/* 71 */       float prot = InventoryUtil.getProtScore(stack);
/* 72 */       for (int i = 5; i < 45; i++) {
/* 73 */         t slot = (Utility.getThePlayer()).FF.A(i);
/* 74 */         if (slot.L() != null) {
/* 75 */           f3 is = slot.L();
/* 76 */           if (is.U().contains(typeStr) && InventoryUtil.getProtScore(is) > prot) {
/* 77 */             return false;
/*    */           }
/*    */         } 
/*    */       } 
/* 81 */       return true;
/*    */     } 
/* 83 */     return false;
/*    */   }
/*    */   
/*    */   private boolean isBestWeapon(f3 is) {
/* 87 */     if (is == null) return false; 
/* 88 */     float damage = InventoryUtil.getDamageScore(is);
/* 89 */     for (int i = 9; i < 45; i++) {
/* 90 */       t slot = (Utility.getThePlayer()).FF.A(i);
/* 91 */       if (slot.L() != null) {
/* 92 */         f3 is2 = slot.L();
/* 93 */         if (InventoryUtil.getDamageScore(is2) > damage && is2.C() instanceof net.minecraft.Lg) {
/* 94 */           return false;
/*    */         }
/*    */       } 
/*    */     } 
/* 98 */     return is.C() instanceof net.minecraft.Lg;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\misc\InvManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */