/*     */ package monomorphism.manager.module.impl.player;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import monomorphism.manager.event.EventMotion;
/*     */ import monomorphism.manager.module.Category;
/*     */ import monomorphism.manager.module.Module;
/*     */ import monomorphism.manager.module.util.other.InventoryUtil;
/*     */ import monomorphism.manager.setting.SettingsManager;
/*     */ import net.minecraft.o6;
/*     */ import net.minecraft.oG;
/*     */ import net.minecraft.q_;
/*     */ 
/*     */ 
/*     */ public class Manager
/*     */   extends Module
/*     */ {
/*  17 */   private final int INVENTORY_ROWS = 4, INVENTORY_COLUMNS = 9, ARMOR_SLOTS = 4;
/*  18 */   private final int INVENTORY_SLOTS = 40;
/*     */   private boolean movedItem;
/*     */   
/*     */   public Manager() {
/*  22 */     super("Inv Manager", 0, Category.Player);
/*  23 */     SettingsManager.manager.addInt("Sword Slot", "swordS", 1, 10, 1, this);
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onUpdate(EventMotion event) {
/*  28 */     this.movedItem = false;
/*  29 */     for (int i = 0; i < 40; i++) {
/*  30 */       q_ itemStack = mc.aQ.b3.b(i);
/*     */       
/*  32 */       if (itemStack != null && itemStack.a() != null)
/*     */       {
/*     */         
/*  35 */         if (!InventoryUtil.itemWhitelisted(itemStack)) {
/*  36 */           InventoryUtil.throwItem(InventoryUtil.getSlotId(i), this.movedItem);
/*     */         }
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  42 */     Integer bestHelmet = null;
/*  43 */     Integer bestChestPlate = null;
/*  44 */     Integer bestLeggings = null;
/*  45 */     Integer bestBoots = null;
/*  46 */     Integer bestSword = null;
/*     */     int j;
/*  48 */     for (j = 0; j < 40; j++) {
/*  49 */       q_ itemStack = mc.aQ.b3.b(j);
/*     */       
/*  51 */       if (itemStack != null && itemStack.a() != null) {
/*     */ 
/*     */         
/*  54 */         o6 item = itemStack.a();
/*     */         
/*  56 */         if (item instanceof oG) {
/*  57 */           oG armor = (oG)item;
/*  58 */           int damageReductionItem = InventoryUtil.getArmorDamageReduction(itemStack);
/*     */ 
/*     */           
/*  61 */           if (armor.r == 0 && (
/*  62 */             bestHelmet == null || damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestHelmet.intValue())))) {
/*  63 */             bestHelmet = Integer.valueOf(j);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*  68 */           if (armor.r == 1 && (
/*  69 */             bestChestPlate == null || 
/*  70 */             damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestChestPlate.intValue())))) {
/*  71 */             bestChestPlate = Integer.valueOf(j);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*  76 */           if (armor.r == 2 && (
/*  77 */             bestLeggings == null || 
/*  78 */             damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestLeggings.intValue())))) {
/*  79 */             bestLeggings = Integer.valueOf(j);
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*  84 */           if (armor.r == 3 && (
/*  85 */             bestBoots == null || damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestBoots.intValue())))) {
/*  86 */             bestBoots = Integer.valueOf(j);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  93 */         if (item instanceof net.minecraft.pK) {
/*  94 */           float damage = InventoryUtil.getSwordDamage(itemStack);
/*  95 */           if (bestSword == null || damage > InventoryUtil.getSwordDamage(mc.aQ.b3.b(bestSword.intValue()))) {
/*  96 */             bestSword = Integer.valueOf(j);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 102 */     for (j = 0; j < 40; j++) {
/* 103 */       q_ itemStack = mc.aQ.b3.b(j);
/*     */       
/* 105 */       if (itemStack != null && itemStack.a() != null) {
/*     */ 
/*     */         
/* 108 */         o6 item = itemStack.a();
/*     */ 
/*     */         
/* 111 */         if (item instanceof oG) {
/* 112 */           oG armor = (oG)item;
/*     */           
/* 114 */           if ((armor.r == 0 && bestHelmet != null && j != bestHelmet.intValue()) || (
/* 115 */             armor.r == 1 && bestChestPlate != null && j != bestChestPlate.intValue()) || (
/* 116 */             armor.r == 2 && bestLeggings != null && j != bestLeggings.intValue()) || (
/* 117 */             armor.r == 3 && bestBoots != null && j != bestBoots.intValue())) {
/* 118 */             InventoryUtil.throwItem(InventoryUtil.getSlotId(j), this.movedItem);
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 124 */         if (item instanceof net.minecraft.pK && 
/* 125 */           bestSword != null && j != bestSword.intValue()) {
/* 126 */           InventoryUtil.throwItem(InventoryUtil.getSlotId(j), this.movedItem);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     if (bestHelmet != null)
/* 132 */       InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestHelmet.intValue()), this.movedItem); 
/* 133 */     if (bestChestPlate != null)
/* 134 */       InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestChestPlate.intValue()), this.movedItem); 
/* 135 */     if (bestLeggings != null)
/* 136 */       InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestLeggings.intValue()), this.movedItem); 
/* 137 */     if (bestBoots != null)
/* 138 */       InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestBoots.intValue()), this.movedItem); 
/* 139 */     if (bestSword != null) {
/* 140 */       int moveSwordTo = 1;
/* 141 */       InventoryUtil.moveItem(InventoryUtil.getSlotId(bestSword.intValue()), InventoryUtil.getSlotId(-36), this.movedItem);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\player\Manager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */