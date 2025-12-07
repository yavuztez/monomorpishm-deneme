/*    */ package gov.babalar.myth.event;
/*    */ 
/*    */ public class Event {
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public boolean isCancelled() {
/*  7 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean isIt) {
/* 11 */     this.cancelled = isIt;
/*    */   }
/*    */   
/*    */   public void cancel() {
/* 15 */     this.cancelled = true;
/*    */   }
/*    */   
/*    */   public static class StateEvent extends Event {
/*    */     private boolean pre = true;
/*    */     
/* 21 */     public boolean isPre() { return this.pre; }
/* 22 */     public boolean isPost() { return !this.pre; } public void setPost() {
/* 23 */       this.pre = false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\event\Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */