package monomorphism.manager.module.util.other;

import monomorphism.manager.module.util.Client;
import net.minecraft.eD;
import net.minecraft.gh;
import net.minecraft.ic;
import net.minecraft.o6;
import net.minecraft.oG;
import net.minecraft.oZ;
import net.minecraft.og;
import net.minecraft.p6;
import net.minecraft.pK;
import net.minecraft.pQ;
import net.minecraft.q_;

public class InventoryUtil extends Client {
   public static void moveItem(int slot, int newSlot, boolean movedItem) {
      try {
         if (slot != newSlot + 36 && !movedItem) {
            mc.Q.a(mc.aQ.bN.e, slot, newSlot, 2, mc.aQ);
            movedItem = true;
         }
      } catch (IndexOutOfBoundsException var4) {
      }

   }

   public static void equipArmor(int slot, boolean movedItem) {
      try {
         if (slot > 8 && !movedItem) {
            mc.Q.a(mc.aQ.bN.e, slot, 0, 1, mc.aQ);
            movedItem = true;
         }
      } catch (IndexOutOfBoundsException var3) {
      }

   }

   public static boolean itemWhitelisted(q_ itemStack) {
      o6 item = itemStack.a();
      return item instanceof oZ || item instanceof pK || item instanceof oG || item instanceof p6 || item instanceof pQ || item == og.bD || item == og.bV || item == og.bU;
   }

   public static boolean itemWhitelistedTitanium(q_ itemStack) {
      o6 item = itemStack.a();
      o6 titanium_axe = o6.a(804);
      o6 titanium_waraxe = o6.a(806);
      o6 sword_zehirlikeskinlik = o6.a(810);
      o6 sword_kankilici = o6.a(811);
      o6 sword_buzperisi = o6.a(812);
      o6 sword_mavigokyuzukilici = o6.a(813);
      return item instanceof pK || item instanceof oG || item instanceof p6 || item == titanium_axe || item == titanium_waraxe || item == sword_mavigokyuzukilici || item == sword_zehirlikeskinlik || item == sword_kankilici || item == sword_buzperisi || item == og.ae || item == og.h || item == og.bD;
   }

   public static int getSlotId(int slot) {
      if (slot >= 36) {
         return 8 - (slot - 36);
      } else {
         return slot < 9 ? slot + 36 : slot;
      }
   }

   public static void throwItem(int slot, boolean movedItem) {
      try {
         if (!movedItem) {
            mc.Q.a(mc.aQ.bN.e, slot, 1, 4, mc.aQ);
            movedItem = true;
         }
      } catch (IndexOutOfBoundsException var3) {
      }

   }

   public static float getSwordDamage(q_ itemStack) {
      pK sword = (pK)itemStack.a();
      int level = ic.a(eD.o.i, itemStack);
      return (float)((double)sword.a() + (double)level * 1.25D);
   }

   public static int getArmorDamageReduction(q_ itemStack) {
      return ((oG)itemStack.a()).t + ic.a(new q_[]{itemStack}, gh.x);
   }
}
