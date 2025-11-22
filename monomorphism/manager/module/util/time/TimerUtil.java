package monomorphism.manager.module.util.time;

public class TimerUtil {
   private static long prevMS;
   public long lastMS = System.currentTimeMillis();

   public TimerUtil() {
      prevMS = 0L;
   }

   public static void resetTimer() {
      prevMS = getTimer();
   }

   public static long getTimer() {
      return System.nanoTime() / 1000000L;
   }

   public static boolean delayTimer(double n) {
      return (double)(getTimer() - prevMS) >= n;
   }

   public void reset() {
      this.lastMS = System.currentTimeMillis();
   }

   public boolean hasTimeElapsed(long time, boolean reset) {
      if (System.currentTimeMillis() - this.lastMS > time) {
         if (reset) {
            this.reset();
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean hasTimeElapsed(long time) {
      return System.currentTimeMillis() - this.lastMS > time;
   }

   public long getTime() {
      return System.currentTimeMillis() - this.lastMS;
   }

   public void setTime(long time) {
      this.lastMS = time;
   }
}
