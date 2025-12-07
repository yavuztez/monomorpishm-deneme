/*    */ package monomorphism.manager.module.impl.player;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import monomorphism.manager.event.EventMotion;
/*    */ import monomorphism.manager.module.Category;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.other.InventoryUtil;
/*    */ import net.minecraft.q_;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TitanyumManager
/*    */   extends Module
/*    */ {
/* 16 */   private final int INVENTORY_ROWS = 4, INVENTORY_COLUMNS = 9, ARMOR_SLOTS = 4;
/* 17 */   private final int INVENTORY_SLOTS = 40;
/*    */   private boolean movedItem;
/*    */   
/*    */   public TitanyumManager() {
/* 21 */     super("Titanyum Manager", 0, Category.Player);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onUpdate(EventMotion event) {
/* 26 */     this.movedItem = false;
/* 27 */     for (int i = 0; i < 40; i++) {
/* 28 */       q_ itemStack = mc.aQ.b3.b(i);
/*    */       
/* 30 */       if (itemStack != null && itemStack.a() != null)
/*    */       {
/* 32 */         if (!InventoryUtil.itemWhitelistedTitanium(itemStack))
/* 33 */           InventoryUtil.throwItem(InventoryUtil.getSlotId(i), this.movedItem); 
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\player\TitanyumManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */