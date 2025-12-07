/*     */ package gov.babalar.myth.module.combat;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import gov.babalar.myth.Utility;
/*     */ import gov.babalar.myth.event.EventMotion;
/*     */ import gov.babalar.myth.module.Module;
/*     */ import gov.babalar.myth.module.ModuleType;
/*     */ import gov.babalar.myth.setting.s.SettingMode;
/*     */ import gov.babalar.myth.setting.s.SettingNumber;
/*     */ import gov.babalar.myth.utils.TimerUtil;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.DN;
/*     */ import net.minecraft.DZ;
/*     */ import net.minecraft.TW;
/*     */ import net.minecraft.Tz;
/*     */ import net.minecraft.V8;
/*     */ 
/*     */ public class KillAura
/*     */   extends Module {
/*     */   public static DZ target;
/*  25 */   public final List<DZ> targets = new ArrayList<>();
/*     */   
/*  27 */   private final TimerUtil attackTimer = new TimerUtil();
/*  28 */   private final Random random = new Random();
/*     */   
/*  30 */   public SettingMode mode = new SettingMode("Mode", new String[] { "Single", "Multi" }, 0);
/*     */ 
/*     */ 
/*     */   
/*  34 */   public SettingNumber range = new SettingNumber(4.2D, 0.0D, 6.2D, 0.1D, "Range");
/*  35 */   public SettingNumber cps = new SettingNumber(11.0D, 8.0D, 14.0D, 0.5D, "CPS");
/*     */   
/*     */   private boolean enabled = false;
/*     */   
/*     */   public KillAura() {
/*  40 */     super(ModuleType.COMBAT, "Aura", 19);
/*  41 */     this.abstractSettings.add(this.mode);
/*  42 */     this.abstractSettings.add(this.range);
/*  43 */     this.abstractSettings.add(this.cps);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  48 */     this.enabled = true;
/*  49 */     target = null;
/*  50 */     this.targets.clear();
/*  51 */     this.attackTimer.reset();
/*  52 */     super.onEnable();
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  57 */     this.enabled = false;
/*  58 */     target = null;
/*  59 */     this.targets.clear();
/*  60 */     super.onDisable();
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onMotionEvent(EventMotion event) {
/*  65 */     if (!this.enabled)
/*     */       return; 
/*  67 */     sortTargets();
/*  68 */     setSuffix(this.mode.getMode());
/*     */     
/*  70 */     if (this.targets.isEmpty()) {
/*  71 */       target = null;
/*     */       
/*     */       return;
/*     */     } 
/*  75 */     if (this.mode.getMode().equals("Single")) {
/*  76 */       target = this.targets.get(0);
/*  77 */       tryAttack(target);
/*     */     } else {
/*  79 */       int max = Math.min(this.targets.size(), 4);
/*  80 */       for (int i = 0; i < max; i++) {
/*  81 */         target = this.targets.get(i);
/*  82 */         tryAttack(target);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void tryAttack(DZ entity) {
/*  88 */     if (entity == null)
/*  89 */       return;  if (!this.enabled)
/*     */       return; 
/*  91 */     double min = 1000.0D / (this.cps.value + this.random.nextDouble());
/*  92 */     double max = 1000.0D / (this.cps.value - this.random.nextDouble());
/*  93 */     long delay = (long)(min + this.random.nextDouble() * (max - min));
/*     */     
/*  95 */     if (!this.attackTimer.hasTimePassed(delay)) {
/*     */       return;
/*     */     }
/*  98 */     attack(entity);
/*  99 */     this.attackTimer.reset();
/*     */   }
/*     */   
/*     */   private void attack(DZ entity) {
/* 103 */     if (!this.enabled)
/*     */       return; 
/*     */     try {
/* 106 */       TW packet = new TW((DN)entity, V8.ATTACK);
/*     */       
/* 108 */       Field z = TW.class.getDeclaredField("Z");
/* 109 */       z.setAccessible(true);
/* 110 */       z.setDouble(packet, 3.02D + this.random.nextDouble() * 0.04D);
/*     */       
/* 112 */       Utility.sendPacket((Tz)packet);
/*     */       
/* 114 */       Utility.getThePlayer().N((DN)entity);
/*     */     }
/* 116 */     catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   private void sortTargets() {
/* 120 */     this.targets.clear();
/*     */     
/* 122 */     for (DZ ent : Utility.getEntityList()) {
/* 123 */       if (ent != Utility.getThePlayer() && 
/* 124 */         Utility.getDistanceToEntity(ent) < this.range.value) {
/* 125 */         this.targets.add(ent);
/*     */       }
/*     */     } 
/*     */     
/* 129 */     this.targets.sort(Comparator.comparingDouble(Utility::getDistanceToEntity));
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\combat\KillAura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */