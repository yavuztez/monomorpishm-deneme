package monomorphism.manager.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.other.InventoryUtil;
import net.minecraft.o6;
import net.minecraft.oG;
import net.minecraft.pK;
import net.minecraft.q_;

public class AutoArmor extends Module {
   private final int INVENTORY_ROWS = 4;
   private final int INVENTORY_COLUMNS = 9;
   private final int ARMOR_SLOTS = 4;
   private final int INVENTORY_SLOTS = 40;
   private boolean movedItem;

   public AutoArmor() {
      super("Auto Armor", 0, Category.Combat);
   }

   public void onEnable() {
      super.onEnable();
   }

   public void onDisable() {
      super.onDisable();
   }

   @Subscribe
   public void onUpdate(EventMotion event) {
      this.movedItem = false;
      Integer bestHelmet = null;
      Integer bestChestPlate = null;
      Integer bestLeggings = null;
      Integer bestBoots = null;
      Integer bestSword = null;

      for(int i = 0; i < 40; ++i) {
         q_ itemStack = mc.aQ.b3.b(i);
         if (itemStack != null && itemStack.a() != null) {
            o6 item = itemStack.a();
            if (item instanceof oG) {
               oG armor = (oG)item;
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
