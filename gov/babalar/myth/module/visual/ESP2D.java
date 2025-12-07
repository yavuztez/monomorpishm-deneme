/*     */ package gov.babalar.myth.module.visual;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import gov.babalar.myth.Utility;
/*     */ import gov.babalar.myth.event.EventNameTagRender;
/*     */ import gov.babalar.myth.event.EventRender2D;
/*     */ import gov.babalar.myth.event.EventRender3D;
/*     */ import gov.babalar.myth.module.Module;
/*     */ import gov.babalar.myth.module.ModuleType;
/*     */ import gov.babalar.myth.setting.s.SettingBool;
/*     */ import gov.babalar.myth.utils.ESPUtil;
/*     */ import gov.babalar.myth.utils.render.RenderUtil;
/*     */ import java.awt.Color;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.DN;
/*     */ import net.minecraft.DP;
/*     */ import net.minecraft.DZ;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Vector4f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ESP2D
/*     */   extends Module
/*     */ {
/*  31 */   private final Map<DN, Vector4f> entityPosition = new HashMap<>();
/*     */   
/*  33 */   private final NumberFormat df = new DecimalFormat("0.#");
/*     */   
/*  35 */   public SettingBool tagSetting = new SettingBool(true, "Tags"); private final Color firstColor;
/*  36 */   public SettingBool espSetting = new SettingBool(true, "ESP");
/*     */   private final Color backgroundColor;
/*     */   
/*     */   public ESP2D() {
/*  40 */     super(ModuleType.VISUAL, "ESP2D", 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     this.firstColor = Hud.getThemeColors()[0];
/*  75 */     this.backgroundColor = new Color(10, 10, 10, 130);
/*     */     this.abstractSettings.add(this.tagSetting);
/*     */     this.abstractSettings.add(this.espSetting);
/*     */     toggle();
/*     */   }
/*     */   @Subscribe
/*     */   public void onRenderNameTag(EventNameTagRender e) {
/*     */     if (this.tagSetting.value)
/*     */       e.cancel(); 
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onRender3D(EventRender3D e) {
/*     */     this.entityPosition.clear();
/*     */     for (DN entity : Utility.getEntityList()) {
/*     */       if (shouldRender(entity))
/*     */         this.entityPosition.put(entity, ESPUtil.getEntityPositionsOn2D(entity)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onRender2D(EventRender2D e) {
/*     */     for (DN entity : this.entityPosition.keySet()) {
/*     */       Vector4f pos = this.entityPosition.get(entity);
/*     */       tags2(e, entity, pos);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void csESP(EventRender2D e, DN en, Vector4f pos) {}
/*     */   
/*     */   public void tags2(EventRender2D e, DN en, Vector4f pos) {
/* 106 */     float x = pos.getX(), y = pos.getY(), right = pos.getZ(), bottom = pos.getW();
/* 107 */     if (en instanceof DP) {
/*     */       
/* 109 */       DZ entity = (DZ)en;
/*     */       
/* 111 */       if (this.tagSetting.value) {
/*     */         
/* 113 */         double fontScale = 0.75D;
/* 114 */         float middle = x + (right - x) / 2.0F;
/* 115 */         float textWidth = 0.0F;
/*     */         
/* 117 */         float health = Utility.getHealth((DP)entity);
/* 118 */         String name = Utility.getPlayerName(entity);
/* 119 */         StringBuilder text = new StringBuilder("§f" + name);
/*     */         
/* 121 */         float healthValue = health / Utility.getMaxHealth((DP)entity);
/* 122 */         Color healthColor = (healthValue > 0.75D) ? new Color(66, 246, 123) : ((healthValue > 0.5D) ? new Color(228, 255, 105) : ((healthValue > 0.35D) ? new Color(236, 100, 64) : new Color(255, 65, 68)));
/* 123 */         text.append(String.format(" §7[§r%s HP§7]", new Object[] { this.df.format(health) }));
/*     */         
/* 125 */         textWidth = Utility.getStringWidth(text.toString());
/* 126 */         middle = (float)(middle - textWidth * fontScale / 2.0D);
/* 127 */         double fontHeight = 9.0D * fontScale;
/*     */         
/* 129 */         GL11.glPushMatrix();
/* 130 */         GL11.glTranslated(middle, y - fontHeight + 2.0D, 0.0D);
/* 131 */         GL11.glScaled(fontScale, fontScale, 1.0D);
/* 132 */         GL11.glTranslated(-middle, -(y - fontHeight + 2.0D), 0.0D);
/* 133 */         RenderUtil.drawRect((middle - 3.0F), (float)(y - fontHeight + 7.0D), (textWidth + 6.0F), fontHeight / fontScale + 4.0D, this.backgroundColor.getRGB());
/*     */         
/* 135 */         RenderUtil.resetColor();
/* 136 */         Utility.drawString(text.toString(), middle, (float)(y - fontHeight + 4.0D), healthColor.getRGB());
/*     */         
/* 138 */         GL11.glPopMatrix();
/*     */       } 
/* 140 */       if (this.espSetting.value) {
/*     */         
/* 142 */         float outlineThickness = 0.5F;
/* 143 */         RenderUtil.resetColor();
/*     */         
/* 145 */         RenderUtil.drawRect(x, y, (right - x), 1.0D, this.firstColor.getRGB());
/*     */         
/* 147 */         RenderUtil.drawRect(x, y, 1.0D, (bottom - y), this.firstColor.getRGB());
/*     */         
/* 149 */         RenderUtil.drawRect(x, bottom, (right - x), 1.0D, this.firstColor.getRGB());
/*     */         
/* 151 */         RenderUtil.drawRect(right, y, 1.0D, (bottom - y + 1.0F), this.firstColor.getRGB());
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 156 */         RenderUtil.drawRect((x - 0.5F), (y - outlineThickness), (right - x + 2.0F), outlineThickness, Color.BLACK
/* 157 */             .getRGB());
/*     */         
/* 159 */         RenderUtil.drawRect((x - outlineThickness), y, outlineThickness, (bottom - y + 1.0F), Color.BLACK.getRGB());
/*     */         
/* 161 */         RenderUtil.drawRect((x - 0.5F), (bottom + 1.0F), (right - x + 2.0F), outlineThickness, Color.BLACK.getRGB());
/*     */         
/* 163 */         RenderUtil.drawRect((right + 1.0F), y, outlineThickness, (bottom - y + 1.0F), Color.BLACK.getRGB());
/*     */ 
/*     */         
/* 166 */         RenderUtil.drawRect((x + 1.0F), (y + 1.0F), (right - x - 1.0F), outlineThickness, Color.BLACK.getRGB());
/*     */         
/* 168 */         RenderUtil.drawRect((x + 1.0F), (y + 1.0F), outlineThickness, (bottom - y - 1.0F), Color.BLACK.getRGB());
/*     */         
/* 170 */         RenderUtil.drawRect((x + 1.0F), (bottom - outlineThickness), (right - x - 1.0F), outlineThickness, Color.BLACK
/* 171 */             .getRGB());
/*     */         
/* 173 */         RenderUtil.drawRect((right - outlineThickness), (y + 1.0F), outlineThickness, (bottom - y - 1.0F), Color.BLACK
/* 174 */             .getRGB());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean shouldRender(DN entity) {
/* 180 */     if (Utility.isDead(entity) || Utility.isInvisible(entity))
/*     */     {
/* 182 */       return false;
/*     */     }
/* 184 */     if (entity instanceof DZ) {
/* 185 */       return (entity != Utility.getThePlayer());
/*     */     }
/* 187 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\visual\ESP2D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */