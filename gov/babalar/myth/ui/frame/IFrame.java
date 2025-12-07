package gov.babalar.myth.ui.frame;

public interface IFrame {
  default void drawScreen(int mouseX, int mouseY, float partialTicks) {}
  
  default void mouseClicked(int mouseX, int mouseY, int mouseButton) {}
  
  default void mouseReleased(int mouseX, int mouseY, int mouseButton) {}
  
  default void keyTyped(char typedChar, int keyCode) {}
}


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\ui\frame\IFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */