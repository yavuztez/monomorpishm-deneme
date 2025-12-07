/*    */ package monomorphism.manager.module.util.time;
/*    */ 
/*    */ public class TimerUtil
/*    */ {
/*    */   private static long prevMS;
/*  6 */   public long lastMS = System.currentTimeMillis();
/*    */   
/*    */   public TimerUtil() {
/*  9 */     prevMS = 0L;
/*    */   }
/*    */   
/*    */   public static void resetTimer() {
/* 13 */     prevMS = getTimer();
/*    */   }
/*    */   
/*    */   public static long getTimer() {
/* 17 */     return System.nanoTime() / 1000000L;
/*    */   }
/*    */   
/*    */   public static boolean delayTimer(double n) {
/* 21 */     return ((getTimer() - prevMS) >= n);
/*    */   }
/*    */   
/*    */   public void reset() {
/* 25 */     this.lastMS = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   public boolean hasTimeElapsed(long time, boolean reset) {
/* 29 */     if (System.currentTimeMillis() - this.lastMS > time) {
/* 30 */       if (reset)
/* 31 */         reset(); 
/* 32 */       return true;
/*    */     } 
/* 34 */     return false;
/*    */   }
/*    */   
/*    */   public boolean hasTimeElapsed(long time) {
/* 38 */     return (System.currentTimeMillis() - this.lastMS > time);
/*    */   }
/*    */   
/*    */   public long getTime() {
/* 42 */     return System.currentTimeMillis() - this.lastMS;
/*    */   }
/*    */   
/*    */   public void setTime(long time) {
/* 46 */     this.lastMS = time;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\time\TimerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */