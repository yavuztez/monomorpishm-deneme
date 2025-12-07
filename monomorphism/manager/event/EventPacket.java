/*    */ package monomorphism.manager.event;
/*    */ 
/*    */ import net.minecraft.yg;
/*    */ 
/*    */ public class EventPacket
/*    */   extends Event {
/*    */   public yg<?> packet;
/*    */   public boolean send;
/*    */   
/*    */   public EventPacket(yg<?> packet, boolean send) {
/* 11 */     this.packet = packet;
/* 12 */     this.send = send;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\event\EventPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */