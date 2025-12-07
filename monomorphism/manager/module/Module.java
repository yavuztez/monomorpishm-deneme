/*    */ package monomorphism.manager.module;
/*    */ 
/*    */ import monomorphism.manager.module.util.Client;
/*    */ import monomorphism.manager.module.util.render.Animation;
/*    */ import monomorphism.manager.module.util.render.DecelerateAnimation;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Module
/*    */   extends Client
/*    */ {
/* 12 */   public final Animation animation = (Animation)new DecelerateAnimation(250, 1.0D);
/*    */   public String moduleName;
/*    */   public int moduleKey;
/*    */   public boolean moduleState;
/*    */   public Category category;
/*    */   
/*    */   public Module(String moduleName, int moduleKey, Category category) {
/* 19 */     this.moduleName = moduleName;
/* 20 */     this.moduleKey = moduleKey;
/* 21 */     this.moduleState = false;
/* 22 */     this.category = category;
/*    */   }
/*    */   
/*    */   public Module getModByName(String name) {
/* 26 */     for (Module mod : ModuleManager.INSTANCE.getModules()) {
/* 27 */       if (mod.moduleName.trim().equalsIgnoreCase(name.trim()) || 
/* 28 */         mod.toString().trim().equalsIgnoreCase(name.trim())) {
/* 29 */         return mod;
/*    */       }
/*    */     } 
/* 32 */     return null;
/*    */   }
/*    */   
/*    */   public Module getInstance() {
/* 36 */     for (Module mod : ModuleManager.INSTANCE.getModules()) {
/* 37 */       if (mod.getClass().equals(getClass())) {
/* 38 */         return mod;
/*    */       }
/*    */     } 
/* 41 */     return null;
/*    */   }
/*    */   
/*    */   public int getModuleKey() {
/* 45 */     return this.moduleKey;
/*    */   }
/*    */   
/*    */   public void setModuleKey(int newModuleKey) {
/* 49 */     this.moduleKey = newModuleKey;
/*    */   }
/*    */   
/*    */   public boolean isEnabled() {
/* 53 */     return this.moduleState;
/*    */   }
/*    */   
/*    */   public boolean getModuleState() {
/* 57 */     return this.moduleState;
/*    */   }
/*    */   
/*    */   public void setModuleState(boolean moduleState) {
/* 61 */     this.moduleState = moduleState;
/* 62 */     onToggled();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 68 */     bus.register(this);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 72 */     bus.unregister(this);
/*    */   }
/*    */   
/*    */   public void onToggled() {
/* 76 */     if (this.moduleState) {
/* 77 */       onEnable();
/*    */     } else {
/* 79 */       onDisable();
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getModuleName() {
/* 84 */     return this.moduleName;
/*    */   }
/*    */   
/*    */   public void toggle() {
/* 88 */     setModuleState(!getModuleState());
/* 89 */     onToggled();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */