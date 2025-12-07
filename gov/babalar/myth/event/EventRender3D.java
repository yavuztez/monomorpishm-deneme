/*    */ package gov.babalar.myth.event;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EventRender3D
/*    */   extends Event
/*    */ {
/*    */   private float ticks;
/*    */   
/*    */   public EventRender3D(float ticks) {
/* 11 */     this.ticks = ticks;
/*    */   }
/*    */   
/*    */   public float getTicks() {
/* 15 */     return this.ticks;
/*    */   }
/*    */   
/*    */   public void setTicks(float ticks) {
/* 19 */     this.ticks = ticks;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\event\EventRender3D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */