/*    */ package gov.babalar.myth.utils.other;
/*    */ 
/*    */ import java.lang.reflect.Field;
/*    */ 
/*    */ public class ReflectionUtils
/*    */ {
/*    */   public static Object getField(Class<?> clazz, String fieldName, Object instance) {
/*    */     try {
/*  9 */       Field field = clazz.getDeclaredField(fieldName);
/* 10 */       field.setAccessible(true);
/* 11 */       return field.get(instance);
/* 12 */     } catch (Exception exception) {
/* 13 */       exception.printStackTrace();
/*    */       
/* 15 */       return null;
/*    */     } 
/*    */   }
/*    */   public static void setField(Class<?> clazz, String fieldName, Object instance, Object args) {
/*    */     try {
/* 20 */       Field field = clazz.getDeclaredField(fieldName);
/* 21 */       field.setAccessible(true);
/* 22 */       field.set(instance, args);
/* 23 */     } catch (Exception exception) {
/* 24 */       exception.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\other\ReflectionUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */