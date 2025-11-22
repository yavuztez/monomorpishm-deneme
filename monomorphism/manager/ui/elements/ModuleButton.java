package monomorphism.manager.ui.elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.render.RenderUtil;
import monomorphism.manager.setting.Setting;
import monomorphism.manager.setting.SettingsManager;
import monomorphism.manager.ui.Panel;
import monomorphism.manager.ui.elements.impl.ElementCheckBox;
import monomorphism.manager.ui.elements.impl.ElementComboBox;
import monomorphism.manager.ui.elements.impl.ElementSlider;
import org.lwjgl.opengl.GL11;

public class ModuleButton {
   public Module func;
   public ArrayList<Element> menuelements;
   public Panel parent;
   public double x;
   public double y;
   public double width;
   public double height;
   public boolean extended = false;
   public boolean listening = false;

   public ModuleButton(Module ifunc, Panel pl) {
      this.func = ifunc;
      this.height = 18.0D;
      this.parent = pl;
      this.menuelements = new ArrayList();
      if (SettingsManager.manager.getSettingsByMod(ifunc) != null) {
         Iterator var4 = SettingsManager.manager.getSettingsByMod(ifunc).iterator();

         while(var4.hasNext()) {
            Setting setting = (Setting)var4.next();
            if (setting.isCheck()) {
               this.menuelements.add(new ElementCheckBox(this, setting));
            } else if (setting.isSlider()) {
               this.menuelements.add(new ElementSlider(this, setting));
            } else if (setting.isCombo()) {
               this.menuelements.add(new ElementComboBox(this, setting));
            }
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      if (this.isHovered(mouseX, mouseY)) {
         RenderUtil.drawRect(this.x - 2.0D, this.y, this.x + this.width + 2.0D, this.y + this.height + 1.0D, 572466736);
      }

      Client.mc.ar.a(this.func.getModuleName(), (int)(this.x + 4.0D), (int)(this.y - 2.0D + this.height / 2.0D), this.func.isEnabled() ? -12895429 : -6513508);
      if (SettingsManager.manager.getSettingsByMod(this.func) != null) {
         GL11.glPushMatrix();
         int k = (int)(this.x + this.width - 6.0D);
         Client.mc.ar.a(">", k, (int)(this.y - 2.0D + this.height / 2.0D), this.func.isEnabled() ? -12895429 : -6513508);
         GL11.glPopMatrix();
      }

   }

   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
      if (!this.isHovered(mouseX, mouseY)) {
         return false;
      } else {
         if (mouseButton == 0) {
            this.func.setModuleState(!this.func.getModuleState());
         } else if (mouseButton == 1) {
            if (this.menuelements != null && this.menuelements.size() > 0) {
               boolean flag = !this.extended;
               this.extended = flag;
            }
         } else if (mouseButton == 2) {
            this.listening = true;
         }

         return true;
      }
   }

   public boolean keyTyped(char typedChar, int keyCode) throws IOException {
      if (this.listening) {
         if (keyCode != 1) {
            this.func.setModuleKey(keyCode);
         } else {
            this.func.setModuleKey(0);
         }

         this.listening = false;
         return true;
      } else {
         return false;
      }
   }

   public boolean isHovered(int mouseX, int mouseY) {
      return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + this.height;
   }
}
