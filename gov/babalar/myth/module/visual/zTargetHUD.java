/*     */ package gov.babalar.myth.module.visual;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import gov.babalar.myth.Utility;
/*     */ import gov.babalar.myth.event.EventRender2D;
/*     */ import gov.babalar.myth.event.EventRender3D;
/*     */ import gov.babalar.myth.managers.ModuleManager;
/*     */ import gov.babalar.myth.module.Module;
/*     */ import gov.babalar.myth.module.ModuleType;
/*     */ import gov.babalar.myth.module.combat.KillAura;
/*     */ import gov.babalar.myth.utils.ESPUtil;
/*     */ import gov.babalar.myth.utils.Pair;
/*     */ import gov.babalar.myth.utils.math.MathHelper;
/*     */ import gov.babalar.myth.utils.render.ColorUtil;
/*     */ import gov.babalar.myth.utils.render.GLUtil;
/*     */ import gov.babalar.myth.utils.render.RenderUtil;
/*     */ import gov.babalar.myth.utils.render.StencilUtil;
/*     */ import java.awt.Color;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.DN;
/*     */ import net.minecraft.DP;
/*     */ import net.minecraft.DZ;
/*     */ import net.minecraft.client.eR;
/*     */ import net.minecraft.client.kP;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ import org.lwjgl.util.vector.Vector4f;
/*     */ 
/*     */ public class zTargetHUD extends Module {
/*     */   private final Map<DN, Vector4f> entityPosition;
/*     */   private KillAura killAura;
/*     */   private DZ target;
/*     */   
/*     */   public zTargetHUD() {
/*  35 */     super(ModuleType.VISUAL, "TargetHUD", 0);
/*     */ 
/*     */ 
/*     */     
/*  39 */     this.entityPosition = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  44 */     this.height = 70;
/*     */     toggle();
/*     */   } private Vector4f targetVector; private int width; private int height; @Subscribe
/*     */   public void onRender3DEvent(EventRender3D event) {
/*  48 */     if (this.killAura == null) {
/*  49 */       this.killAura = (KillAura)ModuleManager.INSTANCE.getModule(KillAura.class);
/*     */     }
/*  51 */     if (this.killAura == null || this.killAura.targets == null || this.killAura.targets.isEmpty()) {
/*     */       return;
/*     */     }
/*  54 */     this.entityPosition.clear();
/*     */     
/*  56 */     for (DZ entity : this.killAura.targets) {
/*  57 */       Vector4f pos = ESPUtil.getEntityPositionsOn2D((DN)entity);
/*  58 */       if (pos != null)
/*  59 */         this.entityPosition.put(entity, pos); 
/*     */     } 
/*     */   }
/*     */   @Subscribe
/*     */   public void onRender(EventRender2D e) {
/*     */     try {
/*     */       Pair<Float, Float> coords;
/*  66 */       if (this.killAura == null) {
/*  67 */         this.killAura = (KillAura)ModuleManager.INSTANCE.getModule(KillAura.class);
/*     */       }
/*  69 */       if (this.killAura == null || this.killAura.targets == null || this.killAura.targets.isEmpty()) {
/*     */         return;
/*     */       }
/*  72 */       this.target = KillAura.target;
/*     */       
/*  74 */       if (this.target == null) {
/*     */         return;
/*     */       }
/*  77 */       switch (this.killAura.mode.getMode()) {
/*     */         case "Single":
/*  79 */           this.targetVector = this.entityPosition.get(this.target);
/*  80 */           if (this.targetVector == null)
/*     */             return; 
/*  82 */           coords = getTrackedCoords();
/*  83 */           GL11.glPushMatrix();
/*  84 */           renderHUD(((Float)coords.getFirst()).intValue(), ((Float)coords.getSecond()).intValue(), 1.0F, this.target);
/*  85 */           GL11.glPopMatrix();
/*     */           break;
/*     */         
/*     */         case "Multi":
/*  89 */           for (DZ t : this.killAura.targets) {
/*  90 */             this.targetVector = this.entityPosition.get(t);
/*  91 */             if (this.targetVector == null)
/*     */               continue; 
/*  93 */             Pair<Float, Float> coords2 = getTrackedCoords();
/*  94 */             GL11.glPushMatrix();
/*  95 */             renderHUD(((Float)coords2.getFirst()).intValue(), ((Float)coords2.getSecond()).intValue(), 1.0F, t);
/*  96 */             GL11.glPopMatrix();
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     
/* 101 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderHUD(int x, int y, float alpha, DZ target) {
/* 108 */     if (target == null)
/* 109 */       return;  if (this.targetVector == null)
/*     */       return; 
/* 111 */     int textColor = ColorUtil.applyOpacity(-1, alpha);
/*     */     
/* 113 */     this.width = Math.max(155, Utility.getStringWidth(Utility.getPlayerName(target)) + 75);
/* 114 */     this.height = 50;
/*     */     
/* 116 */     Color c = ColorUtil.brighter(new Color(30, 30, 30), 0.65F);
/* 117 */     float colorAlpha = 0.8F * alpha;
/*     */     
/* 119 */     RenderUtil.drawRoundedRect(x, y, getWidth(), getHeight(), 3, 
/* 120 */         ColorUtil.applyOpacity(c, colorAlpha).getRGB());
/*     */     
/* 122 */     int size = 38;
/*     */     
/* 124 */     if (target instanceof kP) {
/* 125 */       StencilUtil.initStencilToWrite();
/* 126 */       RenderUtil.circleNoSmoothRGB((x + 10), (y + getHeight() / 2.0F - size / 2.0F), size, -1);
/* 127 */       StencilUtil.readStencilBuffer(1);
/* 128 */       RenderUtil.resetColor();
/* 129 */       RenderUtil.setAlphaLimit(0.0F);
/*     */       
/* 131 */       RenderUtil.color(textColor);
/* 132 */       renderPlayerModelTexture(x + 10, y + getHeight() / 2 - size / 2, size, size, (kP)target);
/* 133 */       StencilUtil.uninitStencilBuffer();
/*     */     } 
/*     */     
/* 136 */     Utility.drawCentered(Utility.getPlayerName(target), x + 10 + size + (
/* 137 */         getWidth() - 10 + size) / 2, y + 10, textColor);
/*     */ 
/*     */     
/* 140 */     float healthPercentage = Utility.getHealth((DP)target) / Utility.getMaxHealth((DP)target);
/* 141 */     float healthBarWidth = (getWidth() - size + 30);
/* 142 */     float newHealthWidth = healthBarWidth * healthPercentage;
/*     */     
/* 144 */     RenderUtil.drawRoundedRect((x + 20 + size), (y + 25), healthBarWidth, 4.0D, 2, 
/*     */         
/* 146 */         ColorUtil.applyOpacity(Color.BLACK, 0.3F * alpha).getRGB());
/*     */     
/* 148 */     RenderUtil.drawRoundedRect((x + 20 + size), (y + 25), newHealthWidth, 4.0D, 2, 
/*     */         
/* 150 */         ColorUtil.applyOpacity(Color.WHITE, alpha).getRGB());
/*     */     
/* 152 */     String healthText = MathHelper.DF_0.format((healthPercentage * 100.0F)) + "%";
/*     */     
/* 154 */     Utility.drawCentered(healthText + " - " + 
/* 155 */         Math.round(Utility.getDistanceToEntity(target)) + "m", x + 10 + size + (
/* 156 */         getWidth() - 10 + size) / 2, y + 35, textColor);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderPlayerModelTexture(int x, int y, float width, float height, kP target) {
/* 161 */     GLUtil.startBlend();
/* 162 */     Utility.getTextureManager().E(target.R());
/* 163 */     eR.L(x, y, 8.0F, 8.0F, 8, 8, (int)width, (int)height, 64.0F, 64.0F);
/* 164 */     GLUtil.endBlend();
/*     */   }
/*     */   
/*     */   private Pair<Float, Float> getTrackedCoords() {
/* 168 */     if (this.targetVector == null) {
/* 169 */       return Pair.of(Float.valueOf(0.0F), Float.valueOf(0.0F));
/*     */     }
/* 171 */     float width = getWidth(), height = getHeight();
/* 172 */     float x = this.targetVector.getX(), y = this.targetVector.getY();
/* 173 */     float entityWidth = this.targetVector.getZ() - this.targetVector.getX();
/* 174 */     float entityHeight = this.targetVector.getW() - this.targetVector.getY();
/* 175 */     float middleY = y + entityHeight / 2.0F - height / 2.0F;
/* 176 */     return Pair.of(Float.valueOf(x + entityWidth - width / 4.0F), Float.valueOf(middleY));
/*     */   }
/*     */   
/* 179 */   public int getWidth() { return this.width; } public int getHeight() {
/* 180 */     return this.height;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\visual\zTargetHUD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */