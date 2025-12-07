/*    */ package monomorphism.manager.module.util.render;
/*    */ 
/*    */ import monomorphism.manager.module.util.time.TimerUtil;
/*    */ 
/*    */ 
/*    */ public abstract class Animation
/*    */ {
/*  8 */   public TimerUtil timerUtil = new TimerUtil();
/*    */   protected int duration;
/*    */   protected double endPoint;
/*    */   protected Direction direction;
/*    */   
/*    */   public Animation(int ms, double endPoint) {
/* 14 */     this.duration = ms;
/* 15 */     this.endPoint = endPoint;
/* 16 */     this.direction = Direction.FORWARDS;
/*    */   }
/*    */   
/*    */   public Animation(int ms, double endPoint, Direction direction) {
/* 20 */     this.duration = ms;
/* 21 */     this.endPoint = endPoint;
/* 22 */     this.direction = direction;
/*    */   }
/*    */   
/*    */   public boolean finished(Direction direction) {
/* 26 */     return (isDone() && this.direction.equals(direction));
/*    */   }
/*    */   
/*    */   public double getLinearOutput() {
/* 30 */     return 1.0D - this.timerUtil.getTime() / this.duration * this.endPoint;
/*    */   }
/*    */   
/*    */   public double getEndPoint() {
/* 34 */     return this.endPoint;
/*    */   }
/*    */   
/*    */   public void setEndPoint(double endPoint) {
/* 38 */     this.endPoint = endPoint;
/*    */   }
/*    */   
/*    */   public void reset() {
/* 42 */     this.timerUtil.reset();
/*    */   }
/*    */   
/*    */   public boolean isDone() {
/* 46 */     return this.timerUtil.hasTimeElapsed(this.duration);
/*    */   }
/*    */   
/*    */   public void changeDirection() {
/* 50 */     setDirection(this.direction.opposite());
/*    */   }
/*    */   
/*    */   public Direction getDirection() {
/* 54 */     return this.direction;
/*    */   }
/*    */   
/*    */   public void setDirection(Direction direction) {
/* 58 */     if (this.direction != direction) {
/* 59 */       this.direction = direction;
/* 60 */       this.timerUtil.setTime(System.currentTimeMillis() - this.duration - Math.min(this.duration, this.timerUtil.getTime()));
/*    */     } 
/*    */   }
/*    */   
/*    */   public void setDuration(int duration) {
/* 65 */     this.duration = duration;
/*    */   }
/*    */   
/*    */   protected boolean correctOutput() {
/* 69 */     return false;
/*    */   }
/*    */   
/*    */   public double getOutput() {
/* 73 */     if (this.direction == Direction.FORWARDS) {
/* 74 */       if (isDone())
/* 75 */         return this.endPoint; 
/* 76 */       return getEquation(this.timerUtil.getTime()) * this.endPoint;
/*    */     } 
/* 78 */     if (isDone())
/* 79 */       return 0.0D; 
/* 80 */     if (correctOutput()) {
/* 81 */       double revTime = Math.min(this.duration, Math.max(0L, this.duration - this.timerUtil.getTime()));
/* 82 */       return getEquation(revTime) * this.endPoint;
/*    */     } 
/* 84 */     return (1.0D - getEquation(this.timerUtil.getTime())) * this.endPoint;
/*    */   }
/*    */   
/*    */   protected abstract double getEquation(double paramDouble);
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\render\Animation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */