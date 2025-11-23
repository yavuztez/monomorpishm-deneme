package monomorphism.manager.setting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import monomorphism.manager.module.Module;

public class SettingsManager {
   public static SettingsManager manager = new SettingsManager();
   private final ArrayList<Setting> settings = new ArrayList();

   public void rSetting(Setting newsetting) {
      this.settings.add(newsetting);
   }

   public void addDouble(String name, String fullName, double min, double max, double current, Module parent) {
      this.settings.add(new Setting(name, fullName, parent, current, min, max, false));
   }

   public void addDouble(String name, String fullName, double min, double max, double current, Module parent, Setting mainSetting, String currentMode) {
      this.settings.add(new Setting(name, fullName, parent, current, min, max, false, mainSetting, currentMode));
   }

   public void addInt(String name, String fullName, int min, int max, int current, Module parent) {
      this.settings.add(new Setting(name, fullName, parent, (double)current, (double)min, (double)max, true));
   }

   public void addInt(String name, String fullName, int min, int max, int current, Module parent, Setting mainSetting, String currentMode) {
      this.settings.add(new Setting(name, fullName, parent, (double)current, (double)min, (double)max, true, mainSetting, currentMode));
   }

   public void addBoolean(String name, String fullName, boolean current, Module parent) {
      this.settings.add(new Setting(name, fullName, parent, current));
   }

   public void addMode(String name, String fullName, String current, ArrayList<String> list, Module parent) {
      this.settings.add(new Setting(name, fullName, parent, current, list));
   }

   public Setting addModeAndGet(String name, String fullName, String current, ArrayList<String> list, Module parent) {
      Setting st = new Setting(name, fullName, parent, current, list);
      this.settings.add(st);
      return st;
   }

   public void addMode(String name, String fullName, String current, ArrayList<String> list, Module parent, Setting c, String c1) {
      this.settings.add(new Setting(name, fullName, parent, current, list, c, c1));
   }

   public ArrayList<Setting> getSettings() {
      return this.settings;
   }

   public List<Setting> getSettingsByMod(Module func) {
      ArrayList<Setting> arraylist = new ArrayList();
      Iterator var4 = this.getSettings().iterator();

      while(var4.hasNext()) {
         Setting setting = (Setting)var4.next();
         if (setting.getParentMod().equals(func)) {
            arraylist.add(setting);
         }
      }

      if (arraylist.isEmpty()) {
         return null;
      } else {
         return arraylist;
      }
   }

   public Setting getSettingByName(String name) {
      Iterator var3 = this.getSettings().iterator();

      while(var3.hasNext()) {
         Setting setting = (Setting)var3.next();
         if (setting.getName().equalsIgnoreCase(name)) {
            return setting;
         }
      }

      return null;
   }
}
