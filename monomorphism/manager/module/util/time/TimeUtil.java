/*    */ package monomorphism.manager.module.util.time;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class TimeUtil
/*    */ {
/* 15 */   public long lastMS = 0L;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int convertToMS(int d) {
/* 21 */     return 1000 / d;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getCurrentMS() {
/* 28 */     return System.nanoTime() / 1000000L;
/*    */   }
/*    */   
/*    */   public long getElapsedTime() {
/* 32 */     return System.currentTimeMillis() - this.lastMS;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasReached(long milliseconds) {
/* 39 */     return (getCurrentMS() - this.lastMS >= milliseconds);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long getDelay() {
/* 46 */     return System.currentTimeMillis() - this.lastMS;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void reset() {
/* 53 */     this.lastMS = getCurrentMS();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLastMS() {
/* 60 */     this.lastMS = System.currentTimeMillis();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLastMS(long lastMS) {
/* 67 */     this.lastMS = lastMS;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\time\TimeUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */