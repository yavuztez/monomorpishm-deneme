package monomorphism.manager.event;

public class EventKey extends Event {
   private final int key;

   public EventKey(int key) {
      this.key = key;
   }

   public int getKey() {
      return this.key;
   }
}
