/*    */ package monomorphism.manager.event;
/*    */ 
/*    */ public class EventRender3D
/*    */   extends Event
/*    */ {
/*    */   private float ticks;
/*    */   
/*    */   public EventRender3D(float ticks) {
/*  9 */     this.ticks = ticks;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getTicks() {
/* 14 */     return this.ticks;
/*    */   }
/*    */   
/*    */   public void setTicks(float ticks) {
/* 18 */     this.ticks = ticks;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\event\EventRender3D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */