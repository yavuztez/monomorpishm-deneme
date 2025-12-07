/*    */ package monomorphism.manager.setting;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import monomorphism.manager.module.Module;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SettingsManager
/*    */ {
/* 12 */   public static SettingsManager manager = new SettingsManager();
/* 13 */   private final ArrayList<Setting> settings = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public void rSetting(Setting newsetting) {
/* 17 */     this.settings.add(newsetting);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDouble(String name, String fullName, double min, double max, double current, Module parent) {
/* 22 */     this.settings.add(new Setting(name, fullName, parent, current, min, max, false));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addDouble(String name, String fullName, double min, double max, double current, Module parent, Setting mainSetting, String currentMode) {
/* 27 */     this.settings.add(new Setting(name, fullName, parent, current, min, max, false, mainSetting, currentMode));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInt(String name, String fullName, int min, int max, int current, Module parent) {
/* 32 */     this.settings.add(new Setting(name, fullName, parent, current, min, max, true));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addInt(String name, String fullName, int min, int max, int current, Module parent, Setting mainSetting, String currentMode) {
/* 37 */     this.settings.add(new Setting(name, fullName, parent, current, min, max, true, mainSetting, currentMode));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addBoolean(String name, String fullName, boolean current, Module parent) {
/* 42 */     this.settings.add(new Setting(name, fullName, parent, current));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addMode(String name, String fullName, String current, ArrayList<String> list, Module parent) {
/* 47 */     this.settings.add(new Setting(name, fullName, parent, current, list));
/*    */   }
/*    */ 
/*    */   
/*    */   public Setting addModeAndGet(String name, String fullName, String current, ArrayList<String> list, Module parent) {
/* 52 */     Setting st = new Setting(name, fullName, parent, current, list);
/* 53 */     this.settings.add(st);
/* 54 */     return st;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void addMode(String name, String fullName, String current, ArrayList<String> list, Module parent, Setting c, String c1) {
/* 60 */     this.settings.add(new Setting(name, fullName, parent, current, list, c, c1));
/*    */   }
/*    */ 
/*    */   
/*    */   public ArrayList<Setting> getSettings() {
/* 65 */     return this.settings;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Setting> getSettingsByMod(Module func) {
/* 70 */     ArrayList<Setting> arraylist = new ArrayList<>();
/* 71 */     for (Setting setting : getSettings()) {
/*    */       
/* 73 */       if (setting.getParentMod().equals(func))
/*    */       {
/* 75 */         arraylist.add(setting);
/*    */       }
/*    */     } 
/* 78 */     if (arraylist.isEmpty())
/*    */     {
/* 80 */       return null;
/*    */     }
/*    */ 
/*    */     
/* 84 */     return arraylist;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Setting getSettingByName(String name) {
/* 90 */     for (Setting setting : getSettings()) {
/*    */       
/* 92 */       if (setting.getName().equalsIgnoreCase(name))
/*    */       {
/* 94 */         return setting;
/*    */       }
/*    */     } 
/* 97 */     return null;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\setting\SettingsManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */