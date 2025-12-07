/*    */ package gov.babalar.myth.module.movement;
/*    */ 
/*    */ import com.google.common.eventbus.Subscribe;
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.event.EventMotion;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.setting.s.SettingMode;
/*    */ import gov.babalar.myth.utils.MovementUtil;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Speed
/*    */   extends Module
/*    */ {
/* 21 */   public SettingMode mode = new SettingMode("Mode", new String[] { "Vanilla", "Bunny" }, 0);
/*    */   
/*    */   public static int ticks;
/*    */   
/*    */   public Speed() {
/* 26 */     super(ModuleType.MOTION, "Speed", 47);
/* 27 */     this.abstractSettings.add(this.mode);
/*    */   }
/*    */ 
/*    */   
/*    */   @Subscribe
/*    */   public void onMotionEvent(EventMotion event) {
/* 33 */     setSuffix(this.mode.getMode());
/* 34 */     if (!MovementUtil.isMoving())
/*    */       return; 
/* 36 */     if (this.mode.getMode().equals("Vanilla")) {
/* 37 */       MovementUtil.setSpeed(1.6D);
/*    */       
/*    */       return;
/*    */     } 
/* 41 */     if (Utility.getMotionY() != 0.0D) {
/* 42 */       double amp = (System.currentTimeMillis() % 100L == 0L) ? 2.3D : 1.0D;
/* 43 */       MovementUtil.setSpeed(MovementUtil.getSpeed() * amp);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\movement\Speed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */