package monomorphism.manager.ui.elements.impl;

import java.awt.Color;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.render.RenderUtil;
import monomorphism.manager.setting.Setting;
import monomorphism.manager.ui.elements.Element;
import monomorphism.manager.ui.elements.ModuleButton;
import org.lwjgl.opengl.GL11;

public class ElementCheckBox extends Element {
   public ElementCheckBox(ModuleButton iparent, Setting iset) {
      this.parent = iparent;
      this.set = iset;
      super.setup();
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      RenderUtil.drawRect((double)((float)this.x), (double)((float)this.y), (double)((float)this.x + (float)this.width), (double)((float)this.y + (float)this.height), -2039584);
      RenderUtil.drawGradientSideways(this.x, this.y, this.x + this.width - 100.0D, this.y + this.height, -5460820, 14737632);
      GL11.glPushMatrix();
      RenderUtil.setColor(new Color(-12105913));
      int j = (int)(this.x + 28.0D);
      int k = (int)(this.y + 5.5D);
      Client.mc.ar.a(this.setstrg, j, k, -12105913);
      GL11.glPopMatrix();
      RenderUtil.drawBorderedRect((float)this.x + 11.2F, (float)this.y + 7.0F, (float)this.x + 17.0F, (float)this.y + 11.0F, 1.0F, 633718213, this.anim < 4.5F ? -10197916 : -15558688);
      GL11.glPushMatrix();
      RenderUtil.drawBorderedCircle((float)((int)this.x + 11), (float)((int)this.y + 9), 2.0F, 1, 633718213, -15558688);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      RenderUtil.drawBorderedCircle((float)((int)this.x + 17), (float)((int)this.y + 9), 2.0F, 1, 633718213, -10197916);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      RenderUtil.enableAlpha();
      RenderUtil.enableBlend();
      if (this.anim > 0.0F) {
         GL11.glPushMatrix();
         RenderUtil.drawBorderedCircle((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 1, -4408132, -4342339);
         GL11.glPopMatrix();
         RenderUtil.drawCircle228((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 5, -15558688, -15558688, (int)(this.anim * 60.0F));
      } else {
         RenderUtil.drawBorderedCircle((float)this.x + 11.0F + this.anim, (float)this.y + 9.0F, 3.0F, 1, -4408132, -4342339);
      }

      GL11.glPopMatrix();
   }

   public void tick() {
      if (this.set.getValBoolean()) {
         this.anim = (float)((double)this.anim + 0.55D);
      } else {
         this.anim = (float)((double)this.anim - 0.55D);
      }

      if (this.anim < 0.0F) {
         this.anim = 0.0F;
      }

      if (this.anim > 6.0F) {
         this.anim = 6.0F;
      }

   }

   public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
      if (mouseButton == 0 && this.isCheckHovered(mouseX, mouseY)) {
         this.set.setValBoolean(!this.set.getValBoolean());
         return true;
      } else {
         return super.mouseClicked(mouseX, mouseY, mouseButton);
      }
   }

   public boolean isCheckHovered(int mouseX, int mouseY) {
      return (double)mouseX >= this.x + 7.5D && (double)mouseX <= this.x + 21.0D && (double)mouseY >= this.y + 7.0D && (double)mouseY <= this.y + 14.5D;
   }
}
