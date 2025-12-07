/*     */ package monomorphism.manager.setting;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import java.util.ArrayList;
/*     */ import monomorphism.manager.module.Module;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Setting
/*     */ {
/*     */   private final String display;
/*     */   private final String name;
/*     */   private final Module parent;
/*     */   private final String mode;
/*     */   private String pMode;
/*     */   private String sval;
/*     */   private ArrayList<String> options;
/*     */   private boolean bval;
/*     */   private double dval;
/*     */   private double min;
/*     */   private double max;
/*     */   private boolean onlyint = false;
/*     */   public boolean extended;
/*     */   private final boolean extraSetting;
/*     */   private String currentMode;
/*     */   private Setting mainSetting;
/*     */   
/*     */   public Setting(String iDisplay, String iname, Module iparent, String isval, ArrayList<String> ioptions) {
/*  31 */     this.display = iDisplay;
/*  32 */     this.name = iname;
/*  33 */     this.parent = iparent;
/*  34 */     this.sval = isval;
/*  35 */     this.options = ioptions;
/*  36 */     this.mode = "Combo";
/*  37 */     this.extraSetting = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String iDisplay, String iname, Module iparent, boolean ibval) {
/*  42 */     this.display = iDisplay;
/*  43 */     this.name = iname;
/*  44 */     this.parent = iparent;
/*  45 */     this.bval = ibval;
/*  46 */     this.mode = "Check";
/*  47 */     this.extraSetting = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String iDisplay, String iname, Module iparent, double idval, double imin, double imax, boolean ionlyint) {
/*  52 */     this.display = iDisplay;
/*  53 */     this.name = iname;
/*  54 */     this.pMode = "";
/*  55 */     this.parent = iparent;
/*  56 */     this.dval = idval;
/*  57 */     this.min = imin;
/*  58 */     this.max = imax;
/*  59 */     this.onlyint = ionlyint;
/*  60 */     this.mode = "Slider";
/*  61 */     this.extraSetting = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String iDisplay, String iname, Module iparent, String isval, ArrayList<String> ioptions, Setting mainSetting, String currentMode) {
/*  66 */     this.display = iDisplay;
/*  67 */     this.name = iname;
/*  68 */     this.parent = iparent;
/*  69 */     this.sval = isval;
/*  70 */     this.options = ioptions;
/*  71 */     this.mode = "Combo";
/*  72 */     this.extraSetting = true;
/*  73 */     this.currentMode = currentMode;
/*  74 */     this.mainSetting = mainSetting;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String iDisplay, String iname, Module iparent, boolean ibval, Setting mainSetting, String currentMode) {
/*  79 */     this.display = iDisplay;
/*  80 */     this.name = iname;
/*  81 */     this.parent = iparent;
/*  82 */     this.bval = ibval;
/*  83 */     this.mode = "Check";
/*  84 */     this.extraSetting = true;
/*  85 */     this.currentMode = currentMode;
/*  86 */     this.mainSetting = mainSetting;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting(String iDisplay, String iname, Module iparent, double idval, double imin, double imax, boolean ionlyint, Setting mainSetting, String currentMode) {
/*  91 */     this.display = iDisplay;
/*  92 */     this.name = iname;
/*  93 */     this.pMode = "";
/*  94 */     this.parent = iparent;
/*  95 */     this.dval = idval;
/*  96 */     this.min = imin;
/*  97 */     this.max = imax;
/*  98 */     this.onlyint = ionlyint;
/*  99 */     this.mode = "Slider";
/* 100 */     this.extraSetting = true;
/* 101 */     this.currentMode = currentMode;
/* 102 */     this.mainSetting = mainSetting;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSimpleName() {
/* 107 */     return this.display;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 112 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public Module getParentMod() {
/* 117 */     return this.parent;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getValString() {
/* 122 */     return this.sval;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValString(String in) {
/* 127 */     this.sval = in;
/*     */   }
/*     */ 
/*     */   
/*     */   public ArrayList<String> getOptions() {
/* 132 */     return this.options;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getValBoolean() {
/* 137 */     return this.bval;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValBoolean(boolean in) {
/* 142 */     this.bval = in;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getValDouble() {
/* 147 */     if (this.onlyint)
/*     */     {
/* 149 */       this.dval = (int)this.dval;
/*     */     }
/* 151 */     return this.dval;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getValFloat() {
/* 156 */     if (this.onlyint)
/*     */     {
/* 158 */       this.dval = (int)this.dval;
/*     */     }
/* 160 */     return (float)this.dval;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getValInt() {
/* 165 */     if (this.onlyint)
/*     */     {
/* 167 */       this.dval = (int)this.dval;
/*     */     }
/* 169 */     return (int)this.dval;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setValDouble(double in) {
/* 174 */     this.dval = in;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMin() {
/* 179 */     return this.min;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMax() {
/* 184 */     return this.max;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMode() {
/* 189 */     return this.mode;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCombo() {
/* 194 */     return this.mode.equalsIgnoreCase("Combo");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isColorCombo() {
/* 199 */     return this.mode.equalsIgnoreCase("ColorCombo");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCheck() {
/* 204 */     return this.mode.equalsIgnoreCase("Check");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSlider() {
/* 209 */     return this.mode.equalsIgnoreCase("Slider");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getpMode() {
/* 214 */     return this.pMode;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean onlyInt() {
/* 219 */     return this.onlyint;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExtra() {
/* 224 */     return this.extraSetting;
/*     */   }
/*     */ 
/*     */   
/*     */   public Setting getMainSetting() {
/* 229 */     return this.mainSetting;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRender() {
/* 234 */     if (!this.extraSetting)
/*     */     {
/* 236 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 240 */     if (this.mainSetting.isCombo())
/*     */     {
/* 242 */       return this.mainSetting.getValString().equalsIgnoreCase(this.currentMode);
/*     */     }
/* 244 */     return (this.mainSetting.isCheck() && this.mainSetting.getValBoolean());
/*     */   }
/*     */   
/*     */   public JsonElement toJson() {
/*     */     JsonPrimitive jsonPrimitive;
/* 249 */     JsonElement element = null; String str;
/* 250 */     switch ((str = this.mode.toLowerCase()).hashCode()) { case -899647263: if (!str.equals("slider")) {
/*     */           break;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 258 */         jsonPrimitive = new JsonPrimitive(Double.valueOf(this.dval)); break;case 94627080: if (!str.equals("check"))
/*     */           break;  jsonPrimitive = new JsonPrimitive(Boolean.valueOf(this.bval)); break;
/*     */       case 94843278: if (!str.equals("combo"))
/* 261 */           break;  jsonPrimitive = new JsonPrimitive(this.sval); break; }  return (JsonElement)jsonPrimitive;
/*     */   }
/*     */   public void fromJson(JsonElement element) {
/*     */     String str;
/* 265 */     switch ((str = this.mode.toLowerCase()).hashCode()) { case -899647263: if (!str.equals("slider")) {
/*     */           break;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 273 */         setValDouble(element.getAsDouble());
/*     */         break;
/*     */       case 94627080:
/*     */         if (!str.equals("check"))
/*     */           break; 
/*     */         setValBoolean(element.getAsBoolean());
/*     */         break;
/*     */       case 94843278:
/*     */         if (!str.equals("combo"))
/*     */           break; 
/*     */         setValString(element.getAsString());
/*     */         break; }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\setting\Setting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */