/*    */ package monomorphism.manager.module.util;
/*    */ 
/*    */ import com.google.common.eventbus.EventBus;
/*    */ import monomorphism.manager.mapper.Entity;
/*    */ import monomorphism.manager.module.ModuleManager;
/*    */ import net.minecraft.client.dw;
/*    */ 
/*    */ public class Client
/*    */   extends Entity
/*    */ {
/* 11 */   public static final EventBus bus = new EventBus();
/* 12 */   public static dw mc = null;
/*    */   public static void startup() {
/* 14 */     ModuleManager.INSTANCE.initialize();
/* 15 */     bus.register(new SilentUtil());
/*    */   }
/*    */   
/*    */   public static void close() {}
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */