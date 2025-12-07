/*    */ package monomorphism.manager.module.util.render;
/*    */ 
/*    */ 
/*    */ public enum Direction
/*    */ {
/*  6 */   FORWARDS, BACKWARDS;
/*    */   
/*    */   public Direction opposite() {
/*  9 */     if (this == FORWARDS) {
/* 10 */       return BACKWARDS;
/*    */     }
/* 12 */     return FORWARDS;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\render\Direction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */