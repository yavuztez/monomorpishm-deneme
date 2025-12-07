/*    */ package gov.babalar.myth.event;
/*    */ 
/*    */ import net.minecraft.Tz;
/*    */ 
/*    */ public class EventPacketSent extends Event {
/*    */   private Tz<?> packet;
/*    */   
/*    */   public EventPacketSent(Tz<?> packet) {
/*  9 */     this.packet = packet;
/*    */   }
/*    */   
/*    */   public void setPacket(Tz<?> packet) {
/* 13 */     this.packet = packet;
/*    */   }
/*    */   
/*    */   public Tz<?> getPacket() {
/* 17 */     return this.packet;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\event\EventPacketSent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */