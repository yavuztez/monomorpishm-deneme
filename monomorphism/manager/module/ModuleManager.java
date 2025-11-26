package monomorphism.manager.module;

import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import monomorphism.manager.event.EventKey;
import monomorphism.manager.module.impl.combat.AutoArmor;
import monomorphism.manager.module.impl.combat.KillAura;
import monomorphism.manager.module.impl.combat.NoKnockback;
import monomorphism.manager.module.impl.movement.Flight;
import monomorphism.manager.module.impl.movement.Phase;
import monomorphism.manager.module.impl.movement.Speed;
import monomorphism.manager.module.impl.movement.Step;
import monomorphism.manager.module.impl.movement.Strafe;
import monomorphism.manager.module.impl.movement.TargetStrafe;
import monomorphism.manager.module.impl.movement.VClipDown;
import monomorphism.manager.module.impl.movement.VClipUp;
import monomorphism.manager.module.impl.player.Manager;
import monomorphism.manager.module.impl.player.NoFall;
import monomorphism.manager.module.impl.player.NoWeb;
import monomorphism.manager.module.impl.player.Speedmine;
import monomorphism.manager.module.impl.player.Sprint;
import monomorphism.manager.module.impl.player.TitanyumManager;
import monomorphism.manager.module.impl.visual.ShiftGUI;
import monomorphism.manager.module.impl.world.ChestStealer;
import monomorphism.manager.module.impl.world.Scaffold;
import monomorphism.manager.module.util.Client;
import net.minecraft.client.dw;

public enum ModuleManager {
   INSTANCE;

   public List<Module> modules = new ArrayList();

   @Subscribe
   public void listenKey(EventKey event) {
      Iterator var3 = this.modules.iterator();

      while(var3.hasNext()) {
         Module mod = (Module)var3.next();
         if (mod.getModuleKey() == event.getKey()) {
            mod.toggle();
         }
      }

   }

   public void initialize() {
      Client.bus.register(this);

      try {
         Field field = dw.class.getDeclaredField("w");
         field.setAccessible(true);
         Client.mc = (dw)field.get((Object)null);
      } catch (Exception var2) {
      }

      this.modules.add(new Sprint());
      this.modules.add(new Step());
      this.modules.add(new KillAura());
      this.modules.add(new Speed());
      this.modules.add(new Manager());
      this.modules.add(new AutoArmor());
      this.modules.add(new Scaffold());
      this.modules.add(new NoKnockback());
      this.modules.add(new ShiftGUI());
      this.modules.add(new NoFall());
      this.modules.add(new Flight());
      this.modules.add(new Speedmine());
      this.modules.add(new ChestStealer());
      this.modules.add(new VClipUp());
      this.modules.add(new VClipDown());
      this.modules.add(new Phase());
      this.modules.add(new NoWeb());
      this.modules.add(new Strafe());
      this.modules.add(new TitanyumManager());
      this.modules.add(new TargetStrafe());
   }

   public Module getModule(Class<?> clazz) {
      Iterator var2 = this.getModules().iterator();

      while(var2.hasNext()) {
         Module mod = (Module)var2.next();
         if (mod.getClass() == clazz) {
            return mod;
         }
      }

      return null;
   }

   public boolean isToggled(Class<?> clazz) {
      Iterator var2 = this.getModules().iterator();

      Module mod;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         mod = (Module)var2.next();
      } while(mod.getClass() != clazz || !mod.isEnabled());

      return true;
   }

   public List<Module> getModules() {
      return this.modules;
   }
}
