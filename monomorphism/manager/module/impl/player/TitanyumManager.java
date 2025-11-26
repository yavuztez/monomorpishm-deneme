package monomorphism.manager.module.impl.player;

import com.google.common.eventbus.Subscribe;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.other.InventoryUtil;
import net.minecraft.q_;

public class TitanyumManager extends Module {
   private final int INVENTORY_ROWS = 4;
   private final int INVENTORY_COLUMNS = 9;
   private final int ARMOR_SLOTS = 4;
   private final int INVENTORY_SLOTS = 40;
   private boolean movedItem;

   public TitanyumManager() {
      super("Titanyum Manager", 0, Category.Player);
   }

   @Subscribe
   public void onUpdate(EventMotion event) {
      this.movedItem = false;

      for(int i = 0; i < 40; ++i) {
         q_ itemStack = mc.aQ.b3.b(i);
         if (itemStack.a() != null && !InventoryUtil.itemWhitelistedTitanium(itemStack)) {
            InventoryUtil.throwItem(InventoryUtil.getSlotId(i), this.movedItem);
         }
      }

   }
}
