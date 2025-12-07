/*     */ package monomorphism.manager.module.util.math;
/*     */ 
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ public class MathHelper
/*     */ {
/*     */   public static double interpolate(double current, double old, double scale) {
/*  10 */     return old + (current - old) * scale;
/*     */   }
/*     */   
/*     */   public static float interpolate(float current, float old, double scale) {
/*  14 */     return (float)(old + (current - old) * scale);
/*     */   }
/*     */   
/*     */   public static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
/*  18 */     return (int)interpolate(oldValue, newValue, (float)interpolationValue);
/*     */   }
/*     */   
/*     */   public static float interpolateFloat(float oldValue, float newValue, double interpolationValue) {
/*  22 */     return (float)interpolate(oldValue, newValue, (float)interpolationValue);
/*     */   }
/*     */ 
/*     */   
/*  26 */   public static final float SQRT_2 = sqrt_float(2.0F);
/*     */   public static final float PI = 3.1415927F;
/*     */   public static final float PI2 = 6.2831855F;
/*     */   public static final float PId2 = 1.5707964F;
/*     */   public static final float deg2Rad = 0.017453292F;
/*  31 */   private static final float[] SIN_TABLE_FAST = new float[4096];
/*  32 */   private static final float[] SIN_TABLE = new float[65536];
/*     */   private static final int[] multiplyDeBruijnBitPosition;
/*     */   private static final double field_181163_d;
/*     */   private static final double[] field_181164_e;
/*     */   private static final double[] field_181165_f;
/*     */   public static boolean fastMath = false;
/*     */   
/*     */   static {
/*     */     int k;
/*  41 */     for (k = 0; k < 65536; k++) {
/*  42 */       SIN_TABLE[k] = (float)Math.sin(k * Math.PI * 2.0D / 65536.0D);
/*     */     }
/*  44 */     for (k = 0; k < 4096; k++) {
/*  45 */       SIN_TABLE_FAST[k] = (float)Math.sin(((k + 0.5F) / 4096.0F * 6.2831855F));
/*     */     }
/*  47 */     multiplyDeBruijnBitPosition = new int[] { 0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9 };
/*  48 */     field_181163_d = Double.longBitsToDouble(4805340802404319232L);
/*  49 */     field_181164_e = new double[257];
/*  50 */     field_181165_f = new double[257];
/*  51 */     for (k = 0; k < 257; k++) {
/*  52 */       double d1 = k / 256.0D;
/*  53 */       double d0 = Math.asin(d1);
/*  54 */       field_181165_f[k] = Math.cos(d0);
/*  55 */       field_181164_e[k] = d0;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float sin(float value) {
/*  60 */     return fastMath ? SIN_TABLE_FAST[(int)(value * 651.8986F) & 0xFFF] : SIN_TABLE[(int)(value * 10430.378F) & 0xFFFF];
/*     */   }
/*     */   
/*     */   public static float cos(float value) {
/*  64 */     return fastMath ? SIN_TABLE_FAST[(int)((value + 1.5707964F) * 651.8986F) & 0xFFF] : SIN_TABLE[(int)(value * 10430.378F + 16384.0F) & 0xFFFF];
/*     */   }
/*     */   
/*     */   public static float sqrt_float(float value) {
/*  68 */     return (float)Math.sqrt(value);
/*     */   }
/*     */   
/*     */   public static float sqrt_double(double value) {
/*  72 */     return (float)Math.sqrt(value);
/*     */   }
/*     */   
/*     */   public static int floor_float(float value) {
/*  76 */     int i = (int)value;
/*  77 */     return (value < i) ? (i - 1) : i;
/*     */   }
/*     */   
/*     */   public static int truncateDoubleToInt(double value) {
/*  81 */     return (int)(value + 1024.0D) - 1024;
/*     */   }
/*     */   
/*     */   public static int floor_double(double value) {
/*  85 */     int i = (int)value;
/*  86 */     return (value < i) ? (i - 1) : i;
/*     */   }
/*     */   
/*     */   public static long floor_double_long(double value) {
/*  90 */     long i = (long)value;
/*  91 */     return (value < i) ? (i - 1L) : i;
/*     */   }
/*     */   
/*     */   public static int abs_int(int value) {
/*  95 */     return (value >= 0) ? value : -value;
/*     */   }
/*     */   
/*     */   public static float abs(float value) {
/*  99 */     return (value >= 0.0F) ? value : -value;
/*     */   }
/*     */   
/*     */   public static int clamp_int(int num, int min, int max) {
/* 103 */     return (num < min) ? min : ((num > max) ? max : num);
/*     */   }
/*     */   
/*     */   public static float clamp_float(float num, float min, float max) {
/* 107 */     return (num < min) ? min : ((num > max) ? max : num);
/*     */   }
/*     */   
/*     */   public static double clamp_double(double num, double min, double max) {
/* 111 */     return (num < min) ? min : ((num > max) ? max : num);
/*     */   }
/*     */   
/*     */   public static double wrapAngleTo180_double(double value) {
/* 115 */     value %= 360.0D;
/* 116 */     if (value >= 180.0D) value -= 360.0D; 
/* 117 */     if (value < -180.0D) value += 360.0D; 
/* 118 */     return value;
/*     */   }
/*     */   
/*     */   public static float wrapAngleTo180_float(float value) {
/* 122 */     value %= 360.0F;
/* 123 */     if (value >= 180.0F) value -= 360.0F; 
/* 124 */     if (value < -180.0F) value += 360.0F; 
/* 125 */     return value;
/*     */   }
/*     */   
/*     */   public static int roundUpToPowerOfTwo(int value) {
/* 129 */     int i = value - 1;
/* 130 */     i |= i >> 1;
/* 131 */     i |= i >> 2;
/* 132 */     i |= i >> 4;
/* 133 */     i |= i >> 8;
/* 134 */     i |= i >> 16;
/* 135 */     return i + 1;
/*     */   }
/*     */   
/*     */   private static boolean isPowerOfTwo(int value) {
/* 139 */     return (value != 0 && (value & value - 1) == 0);
/*     */   }
/*     */   
/*     */   private static int calculateLogBaseTwoDeBruijn(int value) {
/* 143 */     value = isPowerOfTwo(value) ? value : roundUpToPowerOfTwo(value);
/* 144 */     return multiplyDeBruijnBitPosition[(int)(value * 125613361L >> 27L) & 0x1F];
/*     */   }
/*     */   
/*     */   public static int calculateLogBaseTwo(int value) {
/* 148 */     return calculateLogBaseTwoDeBruijn(value) - (isPowerOfTwo(value) ? 0 : 1);
/*     */   }
/*     */   
/*     */   public static UUID getRandomUuid(Random rand) {
/* 152 */     long i = rand.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
/* 153 */     long j = rand.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
/* 154 */     return new UUID(i, j);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\math\MathHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */