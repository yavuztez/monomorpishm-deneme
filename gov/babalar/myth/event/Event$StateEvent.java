/*    */ package gov.babalar.myth.event;
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
/*    */ 
/*    */ 
/*    */ public class StateEvent
/*    */   extends Event
/*    */ {
/*    */   private boolean pre = true;
/*    */   
/*    */   public boolean isPre() {
/* 21 */     return this.pre;
/* 22 */   } public boolean isPost() { return !this.pre; } public void setPost() {
/* 23 */     this.pre = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\event\Event$StateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */