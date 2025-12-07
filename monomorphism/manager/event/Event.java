/*    */ package monomorphism.manager.event;
/*    */ 
/*    */ public class Event
/*    */ {
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public boolean isCancelled() {
/*  8 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean isIt) {
/* 12 */     this.cancelled = isIt;
/*    */   }
/*    */   
/*    */   public void cancel() {
/* 16 */     this.cancelled = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\event\Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */