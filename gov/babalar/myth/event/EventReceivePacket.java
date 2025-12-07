/*    */ package gov.babalar.myth.event;
/*    */ 
/*    */ import net.minecraft.Tz;
/*    */ 
/*    */ public class EventReceivePacket
/*    */   extends Event {
/*    */   private Tz<?> packet;
/*    */   
/*    */   public EventReceivePacket(Tz<?> packet) {
/* 10 */     this.packet = packet;
/*    */   }
/*    */   
/*    */   public Tz<?> getPacket() {
/* 14 */     return this.packet;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\event\EventReceivePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */