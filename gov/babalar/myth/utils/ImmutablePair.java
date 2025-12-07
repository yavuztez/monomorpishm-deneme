/*    */ package gov.babalar.myth.utils;
/*    */ 
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public final class ImmutablePair<A, B> extends Pair<A, B> {
/*    */   private final A a;
/*    */   private final B b;
/*    */   
/*    */   ImmutablePair(A a, B b) {
/* 11 */     this.a = a;
/* 12 */     this.b = b;
/*    */   }
/*    */   
/*    */   public static <A, B> ImmutablePair<A, B> of(A a, B b) {
/* 16 */     return new ImmutablePair<>(a, b);
/*    */   }
/*    */   public Pair<A, A> pairOfFirst() {
/* 19 */     return Pair.of(this.a);
/*    */   } public Pair<B, B> pairOfSecond() {
/* 21 */     return Pair.of(this.b);
/*    */   }
/*    */   
/*    */   public A getFirst() {
/* 25 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public B getSecond() {
/* 30 */     return this.b;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <R> R apply(BiFunction<? super A, ? super B, ? extends R> func) {
/* 36 */     return func.apply(this.a, this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void use(BiConsumer<? super A, ? super B> func) {
/* 41 */     func.accept(this.a, this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\ImmutablePair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */