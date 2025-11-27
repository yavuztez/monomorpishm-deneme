package monomorphism.manager.event;

public class EventRender3D extends Event {
   private float ticks;

   public EventRender3D(float ticks) {
      this.ticks = ticks;
   }

   public float getTicks() {
      return this.ticks;
   }

   public void setTicks(float ticks) {
      this.ticks = ticks;
   }
}
