package monomorphism.manager.event;

public class Event {
   private boolean cancelled = false;

   public boolean isCancelled() {
      return this.cancelled;
   }

   public void setCancelled(boolean isIt) {
      this.cancelled = isIt;
   }

   public void cancel() {
      this.cancelled = true;
   }
}
