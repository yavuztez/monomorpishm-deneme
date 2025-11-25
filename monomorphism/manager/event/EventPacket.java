package monomorphism.manager.event;

import net.minecraft.yg;

public class EventPacket extends Event {
   public yg<?> packet;
   public boolean send;

   public EventPacket(yg<?> packet, boolean send) {
      this.packet = packet;
      this.send = send;
   }
}
