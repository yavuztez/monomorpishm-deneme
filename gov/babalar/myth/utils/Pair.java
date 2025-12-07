/*    */ package gov.babalar.myth.utils;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Objects;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public abstract class Pair<A, B>
/*    */   implements Serializable {
/*    */   public static <A, B> Pair<A, B> of(A a, B b) {
/* 11 */     return ImmutablePair.of(a, b);
/*    */   }
/*    */   
/*    */   public static <A> Pair<A, A> of(A a) {
/* 15 */     return ImmutablePair.of(a, a);
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract A getFirst();
/*    */   
/*    */   public abstract B getSecond();
/*    */   
/*    */   public abstract <R> R apply(BiFunction<? super A, ? super B, ? extends R> paramBiFunction);
/*    */   
/*    */   public abstract void use(BiConsumer<? super A, ? super B> paramBiConsumer);
/*    */   
/*    */   public int hashCode() {
/* 28 */     return Objects.hash(new Object[] { getFirst(), getSecond() });
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object that) {
/* 33 */     if (this == that) return true; 
/* 34 */     if (that instanceof Pair) {
/* 35 */       Pair<?, ?> other = (Pair<?, ?>)that;
/* 36 */       return (Objects.equals(getFirst(), other.getFirst()) && Objects.equals(getSecond(), other.getSecond()));
/*    */     } 
/* 38 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\Pair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */