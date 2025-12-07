/*    */ package gov.babalar.myth.module.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.setting.s.SettingMode;
/*    */ import gov.babalar.myth.setting.s.SettingNumber;
/*    */ import gov.babalar.myth.utils.MovementUtil;
/*    */ 
/*    */ public class Fly
/*    */   extends Module {
/* 14 */   public SettingMode mode = new SettingMode("Mode", new String[] { "Vanilla", "AAC" }, 0);
/* 15 */   public SettingNumber speed = new SettingNumber(2.0D, 0.0D, 9.9D, 0.1D, "Speed");
/*    */   public static int ticks;
/*    */   
/*    */   public Fly() {
/* 19 */     super(ModuleType.MOTION, "Fly", 33);
/* 20 */     this.abstractSettings.add(this.mode);
/* 21 */     this.abstractSettings.add(this.speed);
/*    */   }
/*    */   
/*    */   @Subscribe
/*    */   public void onMotionEvent(EventMotion event) {
/* 26 */     setSuffix(this.mode.getMode());
/* 27 */     if (this.mode.getMode().equals("Vanilla")) {
/* 28 */       MovementUtil.setSpeed(this.speed.value);
/* 29 */       Utility.setMotionY((Utility.getGameSettings()).uw.f() ? 1.0D : ((Utility.getGameSettings()).um.f() ? -1.0D : -0.0625D));
/*    */       return;
/*    */     } 
/* 32 */     switch (ticks) {
/*    */       case 0:
/* 34 */         Utility.setMotionY(0.4D);
/* 35 */         ticks++;
/*    */         break;
/*    */       
/*    */       case 1:
/* 39 */         MovementUtil.setSpeed(0.5D);
/* 40 */         Utility.setMotionY(0.0D);
/* 41 */         ticks++;
/*    */         break;
/*    */       
/*    */       case 2:
/* 45 */         MovementUtil.setSpeed(1.0D);
/* 46 */         Utility.setMotionY(0.0D);
/* 47 */         ticks--;
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\movement\Fly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */