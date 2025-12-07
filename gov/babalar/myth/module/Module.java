/*    */ package gov.babalar.myth.module;
/*    */ 
/*    */ import gov.babalar.myth.Client;
/*    */ import gov.babalar.myth.setting.AbstractSetting;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Module
/*    */   extends Client
/*    */ {
/*    */   private boolean enabled;
/*    */   private ModuleType type;
/*    */   private String name;
/* 19 */   private String suffix = "";
/*    */   private int key;
/* 21 */   public ArrayList<AbstractSetting> abstractSettings = new ArrayList<>();
/*    */   public Module(ModuleType type, String name, int key) {
/* 23 */     this.type = type;
/* 24 */     this.name = name;
/* 25 */     this.key = key;
/*    */   }
/*    */   
/*    */   public int getKey() {
/* 29 */     return this.key;
/*    */   }
/*    */   
/*    */   public void setKey(int key) {
/* 33 */     this.key = key;
/*    */   }
/*    */   
/*    */   public String getSuffix() {
/* 37 */     return this.suffix;
/*    */   }
/*    */   
/*    */   public void setSuffix(String suffix) {
/* 41 */     this.suffix = suffix;
/*    */   }
/*    */ 
/*    */   
/*    */   public void toggle() {
/* 46 */     this.enabled = !this.enabled;
/* 47 */     if (this.enabled) {
/* 48 */       onEnable();
/*    */     } else {
/* 50 */       onDisable();
/*    */     } 
/*    */   }
/*    */   
/*    */   public void onEnable() {
/* 55 */     bus.register(this);
/*    */   }
/*    */   
/*    */   public void onDisable() {
/* 59 */     bus.unregister(this);
/*    */   }
/*    */   
/*    */   public boolean isEnabled() {
/* 63 */     return this.enabled;
/*    */   }
/*    */   
/*    */   public ModuleType getType() {
/* 67 */     return this.type;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 71 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\Module.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */