package monomorphism.manager.module.impl.player;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.other.InventoryUtil;
import monomorphism.manager.setting.SettingsManager;
import net.minecraft.o6;
import net.minecraft.oG;
import net.minecraft.pK;
import net.minecraft.q_;

public class Manager extends Module {
   private final int INVENTORY_ROWS = 4;
   private final int INVENTORY_COLUMNS = 9;
   private final int ARMOR_SLOTS = 4;
   private final int INVENTORY_SLOTS = 40;
   private boolean movedItem;

   public Manager() {
      super("Inv Manager", 0, Category.Player);
      SettingsManager.manager.addInt("Sword Slot", "swordS", 1, 10, 1, this);
   }

   @Subscribe
   public void onUpdate(EventMotion event) {
      this.movedItem = false;

      for(int i = 0; i < 40; ++i) {
         q_ itemStack = mc.aQ.b3.b(i);
         if (itemStack != null && itemStack.a() != null && !InventoryUtil.itemWhitelisted(itemStack)) {
            InventoryUtil.throwItem(InventoryUtil.getSlotId(i), this.movedItem);
         }
      }

      Integer bestHelmet = null;
      Integer bestChestPlate = null;
      Integer bestLeggings = null;
      Integer bestBoots = null;
      Integer bestSword = null;

      int i;
      q_ itemStack;
      o6 item;
      oG armor;
      for(i = 0; i < 40; ++i) {
         itemStack = mc.aQ.b3.b(i);
         if (itemStack != null && itemStack.a() != null) {
            item = itemStack.a();
            if (item instanceof oG) {
               armor = (oG)item;
               int damageReductionItem = InventoryUtil.getArmorDamageReduction(itemStack);
               if (armor.r == 0 && (bestHelmet == null || damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestHelmet)))) {
                  bestHelmet = i;
               }

               if (armor.r == 1 && (bestChestPlate == null || damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestChestPlate)))) {
                  bestChestPlate = i;
               }

               if (armor.r == 2 && (bestLeggings == null || damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestLeggings)))) {
                  bestLeggings = i;
               }

               if (armor.r == 3 && (bestBoots == null || damageReductionItem > InventoryUtil.getArmorDamageReduction(mc.aQ.b3.b(bestBoots)))) {
                  bestBoots = i;
               }
            }

            if (item instanceof pK) {
               float damage = InventoryUtil.getSwordDamage(itemStack);
               if (bestSword == null || damage > InventoryUtil.getSwordDamage(mc.aQ.b3.b(bestSword))) {
                  bestSword = i;
               }
            }
         }
      }

      for(i = 0; i < 40; ++i) {
         itemStack = mc.aQ.b3.b(i);
         if (itemStack != null && itemStack.a() != null) {
            item = itemStack.a();
            if (item instanceof oG) {
               armor = (oG)item;
               if (armor.r == 0 && bestHelmet != null && i != bestHelmet || armor.r == 1 && bestChestPlate != null && i != bestChestPlate || armor.r == 2 && bestLeggings != null && i != bestLeggings || armor.r == 3 && bestBoots != null && i != bestBoots) {
                  InventoryUtil.throwItem(InventoryUtil.getSlotId(i), this.movedItem);
               }
            }

            if (item instanceof pK && bestSword != null && i != bestSword) {
               InventoryUtil.throwItem(InventoryUtil.getSlotId(i), this.movedItem);
            }
         }
      }

      if (bestHelmet != null) {
         InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestHelmet), this.movedItem);
      }

      if (bestChestPlate != null) {
         InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestChestPlate), this.movedItem);
      }

      if (bestLeggings != null) {
         InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestLeggings), this.movedItem);
      }

      if (bestBoots != null) {
         InventoryUtil.equipArmor(InventoryUtil.getSlotId(bestBoots), this.movedItem);
      }

      if (bestSword != null) {
         int moveSwordTo = true;
         InventoryUtil.moveItem(InventoryUtil.getSlotId(bestSword), InventoryUtil.getSlotId(-36), this.movedItem);
      }

   }
}
