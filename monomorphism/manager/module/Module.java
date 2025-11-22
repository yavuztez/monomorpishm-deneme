package monomorphism.manager.module;

import java.util.Iterator;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.render.Animation;
import monomorphism.manager.module.util.render.DecelerateAnimation;

public class Module extends Client {
   public final Animation animation = new DecelerateAnimation(250, 1.0D);
   public String moduleName;
   public int moduleKey;
   public boolean moduleState;
   public Category category;

   public Module(String moduleName, int moduleKey, Category category) {
      this.moduleName = moduleName;
      this.moduleKey = moduleKey;
      this.moduleState = false;
      this.category = category;
   }

   public Module getModByName(String name) {
      Iterator var3 = ModuleManager.INSTANCE.getModules().iterator();

      Module mod;
      do {
         if (!var3.hasNext()) {
            return null;
         }

         mod = (Module)var3.next();
      } while(!mod.moduleName.trim().equalsIgnoreCase(name.trim()) && !mod.toString().trim().equalsIgnoreCase(name.trim()));

      return mod;
   }

   public Module getInstance() {
      Iterator var2 = ModuleManager.INSTANCE.getModules().iterator();

      while(var2.hasNext()) {
         Module mod = (Module)var2.next();
         if (mod.getClass().equals(this.getClass())) {
            return mod;
         }
      }

      return null;
   }

   public int getModuleKey() {
      return this.moduleKey;
   }

   public void setModuleKey(int newModuleKey) {
      this.moduleKey = newModuleKey;
   }

   public boolean isEnabled() {
      return this.moduleState;
   }

   public boolean getModuleState() {
      return this.moduleState;
   }

   public void setModuleState(boolean moduleState) {
      this.moduleState = moduleState;
      this.onToggled();
   }

   public void onEnable() {
      bus.register(this);
   }

   public void onDisable() {
      bus.unregister(this);
   }

   public void onToggled() {
      if (this.moduleState) {
         this.onEnable();
      } else {
         this.onDisable();
      }

   }

   public String getModuleName() {
      return this.moduleName;
   }

   public void toggle() {
      this.setModuleState(!this.getModuleState());
      this.onToggled();
   }
}
