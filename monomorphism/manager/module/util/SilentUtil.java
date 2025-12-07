/*     */ package monomorphism.manager.module.util;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import java.awt.Color;
/*     */ import monomorphism.manager.event.EventRender2D;
/*     */ import monomorphism.manager.module.Module;
/*     */ import monomorphism.manager.module.ModuleManager;
/*     */ import monomorphism.manager.module.util.other.ModuleComparator;
/*     */ import monomorphism.manager.module.util.render.Direction;
/*     */ import monomorphism.manager.module.util.render.HudUtil;
/*     */ import net.minecraft.client.jF;
/*     */ import net.minecraft.client.lU;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SilentUtil
/*     */ {
/*     */   @Subscribe
/*     */   public void onRender2D(EventRender2D event) {
/*  24 */     sr = new jF(Client.mc);
/*  25 */     HudUtil.pushMatrix();
/*  26 */     HudUtil.scale(1.3F, 1.3F, 1.3F);
/*  27 */     rainbowText("happymeal gizli proje", 10, 10);
/*  28 */     HudUtil.popMatrix();
/*  29 */     double yOffset = 0.0D;
/*  30 */     Object font = Client.mc.ar;
/*  31 */     Object modules = ModuleManager.INSTANCE.getModules();
/*  32 */     modules.sort((new ModuleComparator()).reversed());
/*  33 */     for (Object module : modules) {
/*     */       
/*  35 */       Object moduleAnimation = ((Module)module).animation;
/*  36 */       moduleAnimation.setDirection(module.isEnabled() ? Direction.FORWARDS : Direction.BACKWARDS);
/*  37 */       if (!module.isEnabled() && moduleAnimation.finished(Direction.BACKWARDS))
/*     */         continue; 
/*  39 */       Object displayText = module.getModuleName();
/*  40 */       double textWidth = font.a((String)displayText);
/*  41 */       double xValue = (sr.a() - 2);
/*  42 */       int flip = (xValue <= (sr.a() / 2.0F)) ? 1 : 0;
/*  43 */       float x = (float)((flip != 0) ? xValue : (sr.a() - textWidth + 2.0D));
/*  44 */       float alphaAnimation = 1.0F;
/*  45 */       float y = (float)(yOffset + 3.0D);
/*  46 */       double heightVal = 12.0D;
/*  47 */       scaleStart(x + font.a((String)displayText) / 2.0F, (float)(y + heightVal / 2.0D - (((lU)font).i / 2.0F)), 
/*  48 */           (float)moduleAnimation.getOutput());
/*  49 */       alphaAnimation = (float)moduleAnimation.getOutput();
/*  50 */       font.a((String)displayText, x, y - 3.0F + getMiddleOfBox((float)heightVal), 
/*  51 */           applyOpacity((new Color(255, 255, 255, 255)).getRGB(), alphaAnimation), true);
/*  52 */       scaleEnd();
/*  53 */       yOffset += moduleAnimation.getOutput() * heightVal;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void scaleStart(float x, float y, float scale) {
/*  58 */     GL11.glPushMatrix();
/*  59 */     GL11.glTranslatef(x, y, 0.0F);
/*  60 */     GL11.glScalef(scale, scale, 1.0F);
/*  61 */     GL11.glTranslatef(-x, -y, 0.0F);
/*     */   }
/*     */   
/*     */   public void scaleEnd() {
/*  65 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public int applyOpacity(int color, float opacity) {
/*  69 */     Color old = new Color(color);
/*  70 */     return applyOpacity(old, opacity).getRGB();
/*     */   }
/*     */   
/*     */   public Color applyOpacity(Color color, float opacity) {
/*  74 */     opacity = Math.min(1.0F, Math.max(0.0F, opacity));
/*  75 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)(color.getAlpha() * opacity));
/*     */   }
/*     */   
/*     */   public float getMiddleOfBox(float boxHeight) {
/*  79 */     return boxHeight / 2.0F - 4.5F;
/*     */   }
/*     */   
/*     */   public static void rainbowText(String string, int x, int y) {
/*  83 */     int xpos = x;
/*  84 */     int i = 0;
/*  85 */     while (i < string.length()) {
/*  86 */       String s = String.valueOf(string.charAt(i));
/*  87 */       Client.mc.ar.a(s, xpos, y, getThemeColor(i, 1.0F).getRGB(), true);
/*  88 */       xpos += Client.mc.ar.a(s);
/*  89 */       i++;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Color getThemeColor(float colorOffset, float timeMultiplier) {
/*  95 */     float colorOffsetMultiplier = 2.5F;
/*     */     
/*  97 */     colorOffset *= colorOffsetMultiplier;
/*     */     
/*  99 */     double timer = System.currentTimeMillis() / 1.0E8D * timeMultiplier * 400000.0D;
/*     */     
/* 101 */     double factor = (Math.sin(timer + (colorOffset * 0.55F)) + 1.0D) * 0.5D;
/*     */     
/* 103 */     return mixColors(Color.red, Color.orange, factor);
/*     */   }
/*     */   
/*     */   public static Color mixColors(Color color1, Color color2, double percent) {
/* 107 */     double inverse_percent = 1.0D - percent;
/* 108 */     int redPart = (int)(color1.getRed() * percent + color2.getRed() * inverse_percent);
/* 109 */     int greenPart = (int)(color1.getGreen() * percent + color2.getGreen() * inverse_percent);
/* 110 */     int bluePart = (int)(color1.getBlue() * percent + color2.getBlue() * inverse_percent);
/* 111 */     return new Color(redPart, greenPart, bluePart);
/*     */   }
/*     */   
/*     */   public static int getColor(float hueoffset, float saturation, float brightness) {
/* 115 */     float speed = 4500.0F;
/* 116 */     float hue = (float)(System.currentTimeMillis() % 4500L) / 4500.0F;
/*     */     
/* 118 */     return Color.HSBtoRGB(hue - hueoffset / 54.0F, saturation, brightness);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\SilentUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */