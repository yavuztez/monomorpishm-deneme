package monomorphism.manager.ui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.setting.SettingsManager;
import monomorphism.manager.ui.elements.Element;
import monomorphism.manager.ui.elements.ModuleButton;
import monomorphism.manager.ui.elements.impl.ElementSlider;
import net.minecraft.client.gL;
import net.minecraft.client.jF;
import org.lwjgl.opengl.GL11;

public class ClickGUI extends gL {
   public static ArrayList<Panel> panels;
   public static ArrayList<Panel> rpanels;
   private ModuleButton mb = null;
   public SettingsManager setmgr;

   public ClickGUI() {
      this.setmgr = SettingsManager.manager;
      panels = new ArrayList();
      double d0 = 105.0D;
      double d1 = 25.0D;
      double d2 = 10.0D;
      double d3 = 10.0D;
      Category[] var12;
      int var11 = (var12 = Category.values()).length;

      for(int var10 = 0; var10 < var11; ++var10) {
         Category category = var12[var10];
         String s = Character.toUpperCase(category.name().toLowerCase().charAt(0)) + category.name().toLowerCase().substring(1);
         panels.add(new ClickGUI$1(this, s, d2, d3, d0, d1, true, this, category));
         d2 += d0 + 5.0D;
      }

      rpanels = new ArrayList();
      Iterator var14 = panels.iterator();

      while(var14.hasNext()) {
         Panel panel = (Panel)var14.next();
         rpanels.add(panel);
      }

      Collections.reverse(rpanels);
   }

   public void i() {
      Iterator var2 = panels.iterator();

      while(var2.hasNext()) {
         Panel panel = (Panel)var2.next();
         Iterator var4 = panel.Elements.iterator();

         while(var4.hasNext()) {
            ModuleButton modulebutton = (ModuleButton)var4.next();
            Iterator var6 = modulebutton.menuelements.iterator();

            while(var6.hasNext()) {
               Element element = (Element)var6.next();
               element.tick();
               if (!panel.extended) {
                  element.anim = 0.0F;
                  element.anim2 = 0.0F;
               }

               if (!modulebutton.extended) {
                  element.anim = 0.0F;
                  element.anim2 = 0.0F;
               }
            }
         }
      }

   }

   public void a(int mouseX, int mouseY, float partialTicks) {
      a(0, 0, (new jF(this.t)).a(), (new jF(this.t)).c(), 1342177280);
      Iterator var5 = panels.iterator();

      Panel panel2;
      while(var5.hasNext()) {
         panel2 = (Panel)var5.next();
         panel2.drawScreen(mouseX, mouseY, partialTicks);
      }

      this.mb = null;
      var5 = panels.iterator();

      label93:
      while(var5.hasNext()) {
         panel2 = (Panel)var5.next();
         if (panel2 != null && panel2.visible && panel2.extended && panel2.Elements != null && panel2.Elements.size() > 0) {
            Iterator iterator = panel2.Elements.iterator();

            while(iterator.hasNext()) {
               ModuleButton modulebutton = (ModuleButton)iterator.next();
               if (modulebutton.listening) {
                  this.mb = modulebutton;
                  break label93;
               }
            }
         }
      }

      var5 = panels.iterator();

      label73:
      while(true) {
         do {
            do {
               do {
                  if (!var5.hasNext()) {
                     if (this.mb != null) {
                        jF scaledresolution = new jF(this.t);
                        a(0, 0, this.g, this.v, -872415232);
                        GL11.glPushMatrix();
                        Client.mc.ar.a("Press any key...", (float)(scaledresolution.a() / 2), (float)(scaledresolution.c() / 2 - 10), -1, true);
                        GL11.glPopMatrix();
                     }

                     super.a(mouseX, mouseY, partialTicks);
                     return;
                  }

                  panel2 = (Panel)var5.next();
               } while(!panel2.extended);
            } while(!panel2.visible);
         } while(panel2.Elements == null);

         Iterator var15 = panel2.Elements.iterator();

         while(true) {
            ModuleButton modulebutton1;
            do {
               do {
                  do {
                     if (!var15.hasNext()) {
                        continue label73;
                     }

                     modulebutton1 = (ModuleButton)var15.next();
                  } while(!modulebutton1.extended);
               } while(modulebutton1.menuelements == null);
            } while(modulebutton1.menuelements.isEmpty());

            double d0 = 0.0D;
            Color color = new Color(-13350562);
            (new Color(color.getRed(), color.getGreen(), color.getBlue(), 170)).getRGB();

            Element element;
            for(Iterator var12 = modulebutton1.menuelements.iterator(); var12.hasNext(); d0 += element.height) {
               element = (Element)var12.next();
               element.offset = d0;
               element.update();
               element.drawScreen(mouseX, mouseY, partialTicks);
            }
         }
      }
   }

   protected void b(int mouseX, int mouseY, int mouseButton) {
      if (this.mb == null) {
         Iterator var5 = rpanels.iterator();

         label66:
         while(true) {
            Panel panel;
            do {
               do {
                  do {
                     if (!var5.hasNext()) {
                        var5 = rpanels.iterator();

                        while(var5.hasNext()) {
                           panel = (Panel)var5.next();
                           if (panel.mouseClicked(mouseX, mouseY, mouseButton)) {
                              return;
                           }
                        }

                        try {
                           super.b(mouseX, mouseY, mouseButton);
                        } catch (IOException var10) {
                           var10.printStackTrace();
                        }

                        return;
                     }

                     panel = (Panel)var5.next();
                  } while(!panel.extended);
               } while(!panel.visible);
            } while(panel.Elements == null);

            Iterator var7 = panel.Elements.iterator();

            while(true) {
               ModuleButton modulebutton;
               do {
                  if (!var7.hasNext()) {
                     continue label66;
                  }

                  modulebutton = (ModuleButton)var7.next();
               } while(!modulebutton.extended);

               Iterator iterator = modulebutton.menuelements.iterator();

               while(iterator.hasNext()) {
                  Element element = (Element)iterator.next();
                  if (element.mouseClicked(mouseX, mouseY, mouseButton)) {
                     return;
                  }
               }
            }
         }
      }
   }

   protected void a(int mouseX, int mouseY, int state) {
      if (this.mb == null) {
         Iterator var5 = rpanels.iterator();

         label56:
         while(true) {
            Panel panel;
            do {
               do {
                  do {
                     if (!var5.hasNext()) {
                        var5 = rpanels.iterator();

                        while(var5.hasNext()) {
                           panel = (Panel)var5.next();
                           panel.mouseReleased(mouseX, mouseY, state);
                        }

                        super.a(mouseX, mouseY, state);
                        return;
                     }

                     panel = (Panel)var5.next();
                  } while(!panel.extended);
               } while(!panel.visible);
            } while(panel.Elements == null);

            Iterator var7 = panel.Elements.iterator();

            while(true) {
               ModuleButton modulebutton;
               do {
                  if (!var7.hasNext()) {
                     continue label56;
                  }

                  modulebutton = (ModuleButton)var7.next();
               } while(!modulebutton.extended);

               Iterator var9 = modulebutton.menuelements.iterator();

               while(var9.hasNext()) {
                  Element element = (Element)var9.next();
                  element.mouseReleased(mouseX, mouseY, state);
               }
            }
         }
      }
   }

   protected void a(char typedChar, int keyCode) {
      Iterator var4 = rpanels.iterator();

      while(true) {
         Panel panel;
         do {
            do {
               do {
                  do {
                     do {
                        if (!var4.hasNext()) {
                           try {
                              super.a(typedChar, keyCode);
                           } catch (IOException var8) {
                              var8.printStackTrace();
                           }

                           return;
                        }

                        panel = (Panel)var4.next();
                     } while(panel == null);
                  } while(!panel.visible);
               } while(!panel.extended);
            } while(panel.Elements == null);
         } while(panel.Elements.size() <= 0);

         Iterator var6 = panel.Elements.iterator();

         while(var6.hasNext()) {
            ModuleButton modulebutton = (ModuleButton)var6.next();

            try {
               if (modulebutton.keyTyped(typedChar, keyCode)) {
                  return;
               }
            } catch (IOException var9) {
               var9.printStackTrace();
            }
         }
      }
   }

   public void h() {
      Iterator var2 = panels.iterator();

      while(var2.hasNext()) {
         Panel panel = (Panel)var2.next();
         Iterator var4 = panel.Elements.iterator();

         while(var4.hasNext()) {
            ModuleButton modulebutton = (ModuleButton)var4.next();

            Element element;
            for(Iterator var6 = modulebutton.menuelements.iterator(); var6.hasNext(); element.anim2 = 0.0F) {
               element = (Element)var6.next();
               element.anim = 0.0F;
            }
         }
      }

   }

   public void closeGui() {
      Iterator var2 = rpanels.iterator();

      label46:
      while(true) {
         Panel panel;
         do {
            do {
               do {
                  if (!var2.hasNext()) {
                     return;
                  }

                  panel = (Panel)var2.next();
               } while(!panel.extended);
            } while(!panel.visible);
         } while(panel.Elements == null);

         Iterator var4 = panel.Elements.iterator();

         while(true) {
            ModuleButton modulebutton;
            do {
               if (!var4.hasNext()) {
                  continue label46;
               }

               modulebutton = (ModuleButton)var4.next();
            } while(!modulebutton.extended);

            Iterator var6 = modulebutton.menuelements.iterator();

            while(var6.hasNext()) {
               Element element = (Element)var6.next();
               if (element instanceof ElementSlider) {
                  ((ElementSlider)element).dragging = false;
               }
            }
         }
      }
   }
}
