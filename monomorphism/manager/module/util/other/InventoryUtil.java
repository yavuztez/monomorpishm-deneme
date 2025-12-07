/*    */ package monomorphism.manager.module.util.other;
/*    */ 
/*    */ import monomorphism.manager.module.util.Client;
/*    */ import net.minecraft.eD;
/*    */ import net.minecraft.gh;
/*    */ import net.minecraft.ic;
/*    */ import net.minecraft.nn;
/*    */ import net.minecraft.o6;
/*    */ import net.minecraft.oG;
/*    */ import net.minecraft.og;
/*    */ import net.minecraft.pK;
/*    */ import net.minecraft.q_;
/*    */ 
/*    */ public class InventoryUtil
/*    */   extends Client
/*    */ {
/*    */   public static void moveItem(int slot, int newSlot, boolean movedItem) {
/*    */     try {
/* 19 */       if (slot != newSlot + 36 && !movedItem) {
/* 20 */         mc.Q.a(mc.aQ.bN.e, slot, newSlot, 2, (nn)mc.aQ);
/* 21 */         movedItem = true;
/*    */       } 
/* 23 */     } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public static void equipArmor(int slot, boolean movedItem) {
/*    */     try {
/* 29 */       if (slot > 8 && !movedItem) {
/* 30 */         mc.Q.a(mc.aQ.bN.e, slot, 0, 1, (nn)mc.aQ);
/* 31 */         movedItem = true;
/*    */       } 
/* 33 */     } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean itemWhitelisted(q_ itemStack) {
/* 38 */     o6 item = itemStack.a();
/*    */     
/* 40 */     return !(!(item instanceof net.minecraft.oZ) && !(item instanceof pK) && 
/* 41 */       !(item instanceof oG) && 
/* 42 */       !(item instanceof net.minecraft.p6) && 
/* 43 */       !(item instanceof net.minecraft.pQ) && 
/* 44 */       item != og.bD && 
/* 45 */       item != og.bV && 
/* 46 */       item != og.bU);
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean itemWhitelistedTitanium(q_ itemStack) {
/* 51 */     o6 item = itemStack.a();
/* 52 */     o6 titanium_axe = o6.a(804);
/* 53 */     o6 titanium_waraxe = o6.a(806);
/* 54 */     o6 sword_zehirlikeskinlik = o6.a(810);
/* 55 */     o6 sword_kankilici = o6.a(811);
/* 56 */     o6 sword_buzperisi = o6.a(812);
/* 57 */     o6 sword_mavigokyuzukilici = o6.a(813);
/* 58 */     return 
/* 59 */       !(!(item instanceof pK) && 
/* 60 */       !(item instanceof oG) && 
/* 61 */       !(item instanceof net.minecraft.p6) && 
/* 62 */       item != titanium_axe && 
/* 63 */       item != titanium_waraxe && 
/* 64 */       item != sword_mavigokyuzukilici && 
/* 65 */       item != sword_zehirlikeskinlik && 
/* 66 */       item != sword_kankilici && 
/* 67 */       item != sword_buzperisi && 
/* 68 */       item != og.ae && 
/* 69 */       item != og.h && 
/* 70 */       item != og.bD);
/*    */   }
/*    */ 
/*    */   
/*    */   public static int getSlotId(int slot) {
/* 75 */     if (slot >= 36)
/* 76 */       return 8 - slot - 36; 
/* 77 */     if (slot < 9)
/* 78 */       return slot + 36; 
/* 79 */     return slot;
/*    */   }
/*    */   
/*    */   public static void throwItem(int slot, boolean movedItem) {
/*    */     try {
/* 84 */       if (!movedItem) {
/* 85 */         mc.Q.a(mc.aQ.bN.e, slot, 1, 4, (nn)mc.aQ);
/* 86 */         movedItem = true;
/*    */       } 
/* 88 */     } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*    */   }
/*    */ 
/*    */   
/*    */   public static float getSwordDamage(q_ itemStack) {
/* 93 */     pK sword = (pK)itemStack.a();
/* 94 */     int level = ic.a(eD.o.i, itemStack);
/* 95 */     return (float)(sword.a() + level * 1.25D);
/*    */   }
/*    */   
/*    */   public static int getArmorDamageReduction(q_ itemStack) {
/* 99 */     return ((oG)itemStack.a()).t + ic.a(new q_[] { itemStack }, gh.x);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\other\InventoryUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */