/*    */ package gov.babalar.myth.utils;
/*    */ 
/*    */ 
/*    */ public class TimerUtil
/*    */ {
/*  6 */   private long lastMS = 0L;
/*    */   
/*    */   public void reset() {
/*  9 */     this.lastMS = System.currentTimeMillis();
/*    */   }
/*    */ 
/*    */   
/*    */   public void update() {}
/*    */ 
/*    */   
/*    */   public boolean hasTimePassed(long ms) {
/* 17 */     return (System.currentTimeMillis() - this.lastMS >= ms);
/*    */   }
/*    */   
/*    */   public long getTime() {
/* 21 */     return System.currentTimeMillis() - this.lastMS;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\TimerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */