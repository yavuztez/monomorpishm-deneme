package monomorphism.manager.ui.elements.impl;

import java.util.Iterator;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.render.RenderUtil;
import monomorphism.manager.setting.Setting;
import monomorphism.manager.ui.elements.Element;
import monomorphism.manager.ui.elements.ModuleButton;

public class ElementComboBox extends Element {
   public ElementComboBox(ModuleButton iparent, Setting iset) {
      this.parent = iparent;
      this.set = iset;
      super.setup();
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -2039584);
      RenderUtil.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0D, this.y + this.height, -5460820, 14737632);
      RenderUtil.drawOctagon((float)this.x + 8.0F, (float)this.y + 1.0F, (float)this.width - 16.0F, 14.0F, 1.0F, 6.0F, -16746560);
      if (this.comboextended) {
         RenderUtil.drawRect(this.x + 12.0D, this.y + 15.0D, this.x + this.width - 12.0D, this.y + this.height, -2236963);
      }

      Client.mc.ar.a(this.setstrg, (float)((int)(this.x + this.width / 2.0D - (double)(Client.mc.ar.a(this.setstrg) / 2))), (float)((int)(this.y + 4.0D)), -1, true);
      if (this.comboextended) {
         double d0 = this.y + 16.0D;

         for(Iterator var7 = this.set.getOptions().iterator(); var7.hasNext(); d0 += 13.0D) {
            String s = (String)var7.next();
            String s1 = s.substring(0, 1).toUpperCase() + s.substring(1);
            Client.mc.ar.a(s1, (int)(this.x + this.width / 2.0D - (double)(Client.mc.ar.a(s1) / 2)), (int)(d0 + 1.0D), s.equalsIgnoreCase(this.set.getValString()) ? -16746560 : -12105913);
            s.equalsIgnoreCase(this.set.getValString());
            if ((double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= d0) {
            }
         }
      }

   }

   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
      if (this.isButtonHovered(mouseX, mouseY)) {
         this.comboextended = !this.comboextended;
         return true;
      } else if (!this.comboextended) {
         return false;
      } else {
         double d0 = this.y + 16.0D;

         for(Iterator var7 = this.set.getOptions().iterator(); var7.hasNext(); d0 += 15.0D) {
            String s = (String)var7.next();
            if ((double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= d0 && (double)mouseY <= d0 + 9.0D + 2.0D) {
               if (this.clickgui != null && this.clickgui.setmgr != null) {
                  this.clickgui.setmgr.getSettingByName(this.set.getName()).setValString(s.toLowerCase());
               }

               return true;
            }
         }

         return super.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   public boolean isButtonHovered(int mouseX, int mouseY) {
      return (double)mouseX >= this.x && (double)mouseX <= this.x + this.width && (double)mouseY >= this.y && (double)mouseY <= this.y + 15.0D;
   }
}
