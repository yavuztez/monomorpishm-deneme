/*    */ package monomorphism.manager.module.util.time;
/*    */ 
/*    */ public final class TickTimer
/*    */ {
/*    */   public int tick;
/*    */   
/*    */   public void update() {
/*  8 */     this.tick++;
/*    */   }
/*    */   
/*    */   public void reset() {
/* 12 */     this.tick = 0;
/*    */   }
/*    */   
/*    */   public boolean hasTimePassed(int ticks) {
/* 16 */     return (this.tick >= ticks);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\time\TickTimer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */