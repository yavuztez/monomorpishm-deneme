/*     */ package gov.babalar.myth.module.visual;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import gov.babalar.myth.Utility;
/*     */ import gov.babalar.myth.event.EventRender2D;
/*     */ import gov.babalar.myth.managers.ModuleManager;
/*     */ import gov.babalar.myth.module.Module;
/*     */ import gov.babalar.myth.module.ModuleType;
/*     */ import gov.babalar.myth.setting.s.SettingMode;
/*     */ import gov.babalar.myth.utils.render.ColorUtil;
/*     */ import gov.babalar.myth.utils.render.RenderUtil;
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Hud
/*     */   extends Module {
/*  24 */   private final NumberFormat df = new DecimalFormat("0.#");
/*  25 */   public SettingMode mode = new SettingMode("Mode", new String[] { "Old", "Exhibition", "Crest" }, 0);
/*  26 */   private static final Color color1 = new Color(71, 148, 253);
/*  27 */   private static final Color color2 = new Color(71, 253, 160);
/*     */ 
/*     */   
/*     */   private final Comparator<Object> SORT_METHOD;
/*     */ 
/*     */   
/*  33 */   static Color[] blueTheme = new Color[] { new Color(102, 255, 209), new Color(6, 149, 255) };
/*  34 */   static Color[] redTheme = new Color[] { new Color(255, 7, 7), new Color(246, 127, 127) };
/*  35 */   static Color[] redTheme_TEST = new Color[] { new Color(255, 7, 7), new Color(0, 0, 0) };
/*  36 */   static Color[] colors = new Color[] { new Color(204, 102, 255), new Color(89, 6, 255) };
/*     */   
/*     */   public Hud() {
/*  39 */     super(ModuleType.VISUAL, "Hud", 0); this.SORT_METHOD = Comparator.<Object>comparingDouble(m -> { Module mod = (Module)m; String key = mod.getSuffix().isEmpty() ? mod.getName() : (mod.getName() + " [" + mod.getSuffix() + "]"); return Utility.getStringWidth(key);
/*  40 */         }).reversed(); this.abstractSettings.add(this.mode);
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onRender2DEvent(EventRender2D event) {
/*  45 */     setSuffix(this.mode.getMode());
/*  46 */     switch (this.mode.getMode()) {
/*     */       case "Old":
/*  48 */         drawOld();
/*     */         break;
/*     */       
/*     */       case "Exhibition":
/*  52 */         drawExhi();
/*     */         break;
/*     */       
/*     */       case "Crest":
/*  56 */         drawCrest();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawExhi() {
/*  63 */     String text = String.format("§e§l§n§oMyth Client§r [%s] [%s] ", new Object[] { Integer.valueOf(Utility.getFPS()), this.df.format((Math.abs(Utility.getMotionX()) + Math.abs(Utility.getMotionZ())) / 2.0D) });
/*  64 */     Utility.drawStringWithShadow(text, 5.0F, 5.0F, Color.white.getRGB());
/*  65 */     GL11.glPushMatrix();
/*  66 */     List<Module> mods = (List<Module>)ModuleManager.INSTANCE.getModules().stream().filter(Module::isEnabled).sorted(this.SORT_METHOD).collect(Collectors.toList());
/*  67 */     double[] scales = Utility.scales();
/*  68 */     int y = 5;
/*  69 */     int count = 0;
/*  70 */     for (Module mod : mods) {
/*  71 */       String key = mod.getSuffix().isEmpty() ? mod.getName() : (mod.getName() + " [" + mod.getSuffix() + "]");
/*  72 */       int width = Utility.getStringWidth(key);
/*  73 */       int index = count * 20;
/*  74 */       Utility.drawStringWithShadow(key, ((int)scales[0] - width - 5), y, RenderUtil.getRainbowAsRGB(((1800 + index) % 2600), index));
/*  75 */       y += 14;
/*  76 */       count++;
/*     */     } 
/*  78 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public void drawCrest() {
/*  82 */     String time = (new SimpleDateFormat("hh:mm a")).format(new Date());
/*  83 */     if (time.startsWith("0")) {
/*  84 */       time = time.replaceFirst("0", "");
/*     */     }
/*  86 */     double[] scales = Utility.scales();
/*  87 */     Utility.drawStringWithShadow("§4C§rrest §7" + time, 2.0F, 2.0F, 6908265);
/*  88 */     List<Module> mods = (List<Module>)ModuleManager.INSTANCE.getModules().stream().filter(Module::isEnabled).sorted(this.SORT_METHOD).collect(Collectors.toList());
/*  89 */     int y = 2;
/*  90 */     for (Module mod : mods) {
/*  91 */       String key = mod.getSuffix().isEmpty() ? mod.getName() : (mod.getName() + String.format("§4 [%s]", new Object[] { mod.getSuffix() }));
/*  92 */       int width = Utility.getStringWidth(key);
/*  93 */       Utility.drawStringWithShadow(key, ((int)scales[0] - width - 2), y, 6908265);
/*  94 */       y += 10;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void drawOld() {
/*  99 */     Color[] colors = getThemeColors();
/* 100 */     double[] scales = Utility.scales();
/* 101 */     GL11.glPushMatrix();
/* 102 */     String text = String.format("myth client [%s] ", new Object[] { Integer.valueOf(Utility.getFPS()) });
/* 103 */     RenderUtil.drawRoundedRect(2.0D, 2.0D, (Utility.getStringWidth(text) + 14), 14.0D, (new Color(0, 0, 0, 160)).getRGB());
/* 104 */     drawTestColorsex(text, 10.0F, 5.5F, 0);
/* 105 */     String build = "Build: LATEST";
/* 106 */     drawTestColorsex(build, (float)(scales[0] - Utility.getStringWidth(build) - 10.0D), (float)(scales[1] - 15.0D), 15);
/*     */ 
/*     */     
/* 109 */     GL11.glPopMatrix();
/* 110 */     GL11.glPushMatrix();
/* 111 */     List<Module> mods = (List<Module>)ModuleManager.INSTANCE.getModules().stream().filter(Module::isEnabled).sorted(this.SORT_METHOD).collect(Collectors.toList());
/* 112 */     int y = 5;
/* 113 */     int count = 0;
/* 114 */     for (Module mod : mods) {
/* 115 */       String key = mod.getSuffix().isEmpty() ? mod.getName() : (mod.getName() + " [" + mod.getSuffix() + "]");
/* 116 */       int width = Utility.getStringWidth(key);
/* 117 */       int index = count * 20;
/* 118 */       Color textcolor = ColorUtil.interpolateColorsBackAndForth(20, index, colors[0], colors[1], true);
/* 119 */       RenderUtil.rect(((int)scales[0] - width - 7), (y - 3), (width + 5), 12.0D, false, ColorUtil.applyOpacity(new Color(10, 10, 10), 0.7F));
/* 120 */       Utility.drawStringWithShadow(key, ((int)scales[0] - width - 5), y, textcolor.getRGB());
/* 121 */       y += 12;
/* 122 */       count++;
/*     */     } 
/* 124 */     GL11.glPopMatrix();
/*     */   }
/*     */   
/*     */   public static Color getThemeColor(float colorOffset) {
/* 128 */     return getThemeColor(colorOffset, 1.0F);
/*     */   }
/*     */   
/*     */   public static Color getThemeColor(float colorOffset, float timeMultiplier) {
/* 132 */     float colorOffsetMultiplier = 2.2F;
/* 133 */     double timer = System.currentTimeMillis() / 1.0E8D * timeMultiplier * 400000.0D;
/* 134 */     double factor = (Math.sin(timer + ((colorOffset *= colorOffsetMultiplier) * 0.55F)) + 1.0D) * 0.5D;
/* 135 */     return mixColors(color1, color2, factor);
/*     */   }
/*     */   
/*     */   public static Color mixColors(Color color1, Color color2, double percent) {
/* 139 */     double inverse_percent = 1.0D - percent;
/* 140 */     int redPart = (int)(color1.getRed() * percent + color2.getRed() * inverse_percent);
/* 141 */     int greenPart = (int)(color1.getGreen() * percent + color2.getGreen() * inverse_percent);
/* 142 */     int bluePart = (int)(color1.getBlue() * percent + color2.getBlue() * inverse_percent);
/* 143 */     return new Color(redPart, greenPart, bluePart);
/*     */   }
/*     */   
/*     */   public static void drawTestColorsex(String s, float d, float e, int offset) {
/* 147 */     Color[] colors = getThemeColors();
/* 148 */     for (int i = 0; i < s.length(); i++) {
/* 149 */       String sdd = String.valueOf(s.charAt(i));
/* 150 */       int color = ColorUtil.interpolateColorsBackAndForth(20, i * 15, colors[0], colors[1], true).getRGB();
/* 151 */       Utility.drawStringWithShadow(sdd, d, e, color);
/* 152 */       d += Utility.getStringWidth(sdd);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static Color[] getThemeColors() {
/* 158 */     return redTheme;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\visual\Hud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */