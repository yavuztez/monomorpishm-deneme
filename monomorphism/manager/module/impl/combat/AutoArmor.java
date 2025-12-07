/*     */ package monomorphism.manager.module.impl.combat;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import monomorphism.manager.event.EventMotion;
/*     */ import monomorphism.manager.module.Category;
/*     */ import monomorphism.manager.module.Module;
/*     */ import monomorphism.manager.module.util.other.InventoryUtil;
/*     */ import net.minecraft.o6;
/*     */ import net.minecraft.oG;
/*     */ import net.minecraft.q_;
/*     */ 
/*     */ public class AutoArmor
/*     */   extends Module
/*     */ {
/*     */   private final int INVENTORY_ROWS = 4;
/*     */   private final int INVENTORY_COLUMNS = 9;
/*     */   
/*     */   public AutoArmor() {
/*  19 */     super("Auto Armor", 0, Category.Combat);
/*     */ 
/*     */     
/*  22 */     this.INVENTORY_ROWS = 4; this.INVENTORY_COLUMNS = 9; this.ARMOR_SLOTS = 4;
/*  23 */     this.INVENTORY_SLOTS = 40;
/*     */   }
/*     */   private final int ARMOR_SLOTS = 4; private final int INVENTORY_SLOTS = 40; private boolean movedItem;
/*     */   public void onEnable() {
/*  27 */     super.onEnable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  32 */     super.onDisable();
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onUpdate(EventMotion event) {
/*  37 */     this.movedItem = false;
/*     */ 
/*     */     
/*  40 */     Integer bestHelmet = null;
/*  41 */     Integer bestChestPlate = null;
/*  42 */     Integer bestLeggings = null;
/*  43 */     Integer bestBoots = null;
/*  44 */     Integer bestSword = null;
/*     */     
/*  46 */     for (int i = 0; i < 40; i++) {
/*  47 */       q_ itemStack = mc.aQ.b3.b(i);
/*     */       
/*  49 */       if (itemStack != null && itemStack.a() != null) {
/*     */ 
/*     */         
/*  52 */         o6 item = itemStack.a();
/*     */         
/*  54 */         if (item instanceof oG) {
/*  55 */           oG armor = (oG)item;
/*  56 */           int damageReductionItem = InventoryUtil.getArmorDamageReduction(itemStack);
/*     */ 
/*     */           
/*  59 */           if (armor.r == 0 && (
/*  60 */             bestHelmet == null || damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestHelmet.intValue())))) {
/*  61 */             bestHelmet = Integer.valueOf(i);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*  66 */           if (armor.r == 1 && (
/*  67 */             bestChestPlate == null || 
/*  68 */             damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestChestPlate.intValue())))) {
/*  69 */             bestChestPlate = Integer.valueOf(i);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*  74 */           if (armor.r == 2 && (
/*  75 */             bestLeggings == null || 
/*  76 */             damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestLeggings.intValue())))) {
/*  77 */             bestLeggings = Integer.valueOf(i);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*  82 */           if (armor.r == 3 && (
/*  83 */             bestBoots == null || damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestBoots.intValue())))) {
/*  84 */             bestBoots = Integer.valueOf(i);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  91 */         if (item instanceof net.minecraft.pK) {
/*  92 */           float damage = InventoryUtil.getSwordDamage(itemStack);
/*  93 */           if (bestSword == null || damage > InventoryUtil.getSwordDamage(mc.aQ.b3.b(bestSword.intValue()))) {
/*  94 */             bestSword = Integer.valueOf(i);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 100 */     if (bestHelmet != null)
/* 101 */       InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestHelmet.intValue()), this.movedItem); 
/* 102 */     if (bestChestPlate != null)
/* 103 */       InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestChestPlate.intValue()), this.movedItem); 
/* 104 */     if (bestLeggings != null)
/* 105 */       InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestLeggings.intValue()), this.movedItem); 
/* 106 */     if (bestBoots != null)
/* 107 */       InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestBoots.intValue()), this.movedItem); 
/* 108 */     if (bestSword != null) {
/* 109 */       int moveSwordTo = 1;
/*     */       
/* 111 */       InventoryUtil.moveItem(InventoryUtil.getSlotId(bestSword.intValue()), InventoryUtil.getSlotId(-36), this.movedItem);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\combat\AutoArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */