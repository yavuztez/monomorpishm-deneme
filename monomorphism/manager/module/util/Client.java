package monomorphism.manager.module.util;

import com.google.common.eventbus.EventBus;
import monomorphism.manager.mapper.Entity;
import monomorphism.manager.module.ModuleManager;
import net.minecraft.client.dw;

public class Client extends Entity {
   public static final EventBus bus = new EventBus();
   public static dw mc = null;

   public static void startup() {
      ModuleManager.INSTANCE.initialize();
      bus.register(new SilentUtil());
   }

   public static void close() {
   }
}
