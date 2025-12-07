/*    */ package monomorphism.manager.module.util.time;
/*    */ 
/*    */ public final class MSTimer
/*    */ {
/*  5 */   public long time = -1L;
/*    */   
/*    */   public boolean hasTimePassed(long MS) {
/*  8 */     return (System.currentTimeMillis() >= this.time + MS);
/*    */   }
/*    */   
/*    */   public long hasTimeLeft(long MS) {
/* 12 */     return MS + this.time - System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   public void reset() {
/* 16 */     this.time = System.currentTimeMillis();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\time\MSTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */