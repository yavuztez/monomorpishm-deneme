/*     */ package gov.babalar.myth.utils.math;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MathHelper
/*     */ {
/*  11 */   public static final float SQRT_2 = sqrt_float(2.0F);
/*     */   public static final float PI = 3.1415927F;
/*     */   public static final float PI2 = 6.2831855F;
/*     */   public static final float PId2 = 1.5707964F;
/*     */   public static final float deg2Rad = 0.017453292F;
/*  16 */   private static final float[] SIN_TABLE_FAST = new float[4096];
/*  17 */   private static final float[] SIN_TABLE = new float[65536];
/*     */   private static final int[] multiplyDeBruijnBitPosition;
/*     */   private static final double field_181163_d;
/*     */   private static final double[] field_181164_e;
/*     */   private static final double[] field_181165_f;
/*     */   public static boolean fastMath;
/*  23 */   public static final DecimalFormat DF_0 = new DecimalFormat("0");
/*  24 */   public static final DecimalFormat DF_1 = new DecimalFormat("0.0");
/*  25 */   public static final DecimalFormat DF_2 = new DecimalFormat("0.00");
/*  26 */   public static final DecimalFormat DF_1D = new DecimalFormat("0.#");
/*  27 */   public static final DecimalFormat DF_2D = new DecimalFormat("0.##");
/*     */ 
/*     */   
/*     */   static {
/*  31 */     fastMath = false;
/*  32 */     int i = 0;
/*  33 */     while (i < 65536) {
/*  34 */       SIN_TABLE[i] = (float)Math.sin(i * Math.PI * 2.0D / 65536.0D);
/*  35 */       i++;
/*     */     } 
/*  37 */     int j = 0;
/*  38 */     while (j < 4096) {
/*  39 */       SIN_TABLE_FAST[j] = (float)Math.sin(((j + 0.5F) / 4096.0F * 6.2831855F));
/*  40 */       j++;
/*     */     } 
/*  42 */     int l = 0;
/*  43 */     while (l < 360) {
/*  44 */       SIN_TABLE_FAST[(int)(l * 11.377778F) & 0xFFF] = (float)Math.sin((l * 0.017453292F));
/*  45 */       l += 90;
/*     */     } 
/*  47 */     int[] nArray = new int[32];
/*  48 */     nArray[1] = 1;
/*  49 */     nArray[2] = 28;
/*  50 */     nArray[3] = 2;
/*  51 */     nArray[4] = 29;
/*  52 */     nArray[5] = 14;
/*  53 */     nArray[6] = 24;
/*  54 */     nArray[7] = 3;
/*  55 */     nArray[8] = 30;
/*  56 */     nArray[9] = 22;
/*  57 */     nArray[10] = 20;
/*  58 */     nArray[11] = 15;
/*  59 */     nArray[12] = 25;
/*  60 */     nArray[13] = 17;
/*  61 */     nArray[14] = 4;
/*  62 */     nArray[15] = 8;
/*  63 */     nArray[16] = 31;
/*  64 */     nArray[17] = 27;
/*  65 */     nArray[18] = 13;
/*  66 */     nArray[19] = 23;
/*  67 */     nArray[20] = 21;
/*  68 */     nArray[21] = 19;
/*  69 */     nArray[22] = 16;
/*  70 */     nArray[23] = 7;
/*  71 */     nArray[24] = 26;
/*  72 */     nArray[25] = 12;
/*  73 */     nArray[26] = 18;
/*  74 */     nArray[27] = 6;
/*  75 */     nArray[28] = 11;
/*  76 */     nArray[29] = 5;
/*  77 */     nArray[30] = 10;
/*  78 */     nArray[31] = 9;
/*  79 */     multiplyDeBruijnBitPosition = nArray;
/*  80 */     field_181163_d = Double.longBitsToDouble(4805340802404319232L);
/*  81 */     field_181164_e = new double[257];
/*  82 */     field_181165_f = new double[257];
/*  83 */     int k = 0;
/*  84 */     while (k < 257) {
/*  85 */       double d1 = k / 256.0D;
/*  86 */       double d0 = Math.asin(d1);
/*  87 */       field_181165_f[k] = Math.cos(d0);
/*  88 */       field_181164_e[k] = d0;
/*  89 */       k++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static float sin(float p_76126_0_) {
/*  94 */     return fastMath ? SIN_TABLE_FAST[(int)(p_76126_0_ * 651.8986F) & 0xFFF] : SIN_TABLE[(int)(p_76126_0_ * 10430.378F) & 0xFFFF];
/*     */   }
/*     */   
/*     */   public static float cos(float value) {
/*  98 */     return fastMath ? SIN_TABLE_FAST[(int)((value + 1.5707964F) * 651.8986F) & 0xFFF] : SIN_TABLE[(int)(value * 10430.378F + 16384.0F) & 0xFFFF];
/*     */   }
/*     */   
/*     */   public static float sqrt_float(float value) {
/* 102 */     return (float)Math.sqrt(value);
/*     */   }
/*     */   
/*     */   public static float sqrt_double(double value) {
/* 106 */     return (float)Math.sqrt(value);
/*     */   }
/*     */   
/*     */   public static int floor_float(float value) {
/* 110 */     int i = (int)value;
/* 111 */     return (value < i) ? (i - 1) : i;
/*     */   }
/*     */   
/*     */   public static int truncateDoubleToInt(double value) {
/* 115 */     return (int)(value + 1024.0D) - 1024;
/*     */   }
/*     */   
/*     */   public static int floor_double(double value) {
/* 119 */     int i = (int)value;
/* 120 */     return (value < i) ? (i - 1) : i;
/*     */   }
/*     */   
/*     */   public static long floor_double_long(double value) {
/* 124 */     long i = (long)value;
/* 125 */     return (value < i) ? (i - 1L) : i;
/*     */   }
/*     */   
/*     */   public static int func_154353_e(double value) {
/* 129 */     return (int)((value >= 0.0D) ? value : (-value + 1.0D));
/*     */   }
/*     */   
/*     */   public static float abs(float value) {
/* 133 */     return (value >= 0.0F) ? value : -value;
/*     */   }
/*     */   
/*     */   public static int abs_int(int value) {
/* 137 */     return (value >= 0) ? value : -value;
/*     */   }
/*     */   
/*     */   public static int ceiling_float_int(float value) {
/* 141 */     int i = (int)value;
/* 142 */     return (value > i) ? (i + 1) : i;
/*     */   }
/*     */   
/*     */   public static int ceiling_double_int(double value) {
/* 146 */     int i = (int)value;
/* 147 */     return (value > i) ? (i + 1) : i;
/*     */   }
/*     */   
/*     */   public static int clamp_int(int num, int min, int max) {
/* 151 */     return (num < min) ? min : ((num > max) ? max : num);
/*     */   }
/*     */   
/*     */   public static float clamp_float(float num, float min, float max) {
/* 155 */     return (num < min) ? min : ((num > max) ? max : num);
/*     */   }
/*     */   
/*     */   public static Double interpolate(double oldValue, double newValue, double interpolationValue) {
/* 159 */     return Double.valueOf(oldValue + (newValue - oldValue) * interpolationValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public static double clamp_double(double num, double min, double max) {
/* 164 */     return (num < min) ? min : ((num > max) ? max : num);
/*     */   }
/*     */   
/*     */   public static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
/* 168 */     return interpolate(oldValue, newValue, (float)interpolationValue).intValue();
/*     */   }
/*     */   public static float interpolateFloat(float oldValue, float newValue, double interpolationValue) {
/* 171 */     return interpolate(oldValue, newValue, (float)interpolationValue).floatValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double denormalizeClamp(double p_151238_0_, double p_151238_2_, double p_151238_4_) {
/* 176 */     return (p_151238_4_ < 0.0D) ? p_151238_0_ : ((p_151238_4_ > 1.0D) ? p_151238_2_ : (p_151238_0_ + (p_151238_2_ - p_151238_0_) * p_151238_4_));
/*     */   }
/*     */   
/*     */   public static double abs_max(double p_76132_0_, double p_76132_2_) {
/* 180 */     if (p_76132_0_ < 0.0D) {
/* 181 */       p_76132_0_ = -p_76132_0_;
/*     */     }
/* 183 */     if (p_76132_2_ < 0.0D) {
/* 184 */       p_76132_2_ = -p_76132_2_;
/*     */     }
/* 186 */     return (p_76132_0_ > p_76132_2_) ? p_76132_0_ : p_76132_2_;
/*     */   }
/*     */   
/*     */   public static int bucketInt(int p_76137_0_, int p_76137_1_) {
/* 190 */     return (p_76137_0_ < 0) ? (-((-p_76137_0_ - 1) / p_76137_1_) - 1) : (p_76137_0_ / p_76137_1_);
/*     */   }
/*     */   
/*     */   public static int getRandomIntegerInRange(Random p_76136_0_, int p_76136_1_, int p_76136_2_) {
/* 194 */     return (p_76136_1_ >= p_76136_2_) ? p_76136_1_ : (p_76136_0_.nextInt(p_76136_2_ - p_76136_1_ + 1) + p_76136_1_);
/*     */   }
/*     */   
/*     */   public static float randomFloatClamp(Random p_151240_0_, float p_151240_1_, float p_151240_2_) {
/* 198 */     return (p_151240_1_ >= p_151240_2_) ? p_151240_1_ : (p_151240_0_.nextFloat() * (p_151240_2_ - p_151240_1_) + p_151240_1_);
/*     */   }
/*     */   
/*     */   public static double getRandomDoubleInRange(Random p_82716_0_, double p_82716_1_, double p_82716_3_) {
/* 202 */     return (p_82716_1_ >= p_82716_3_) ? p_82716_1_ : (p_82716_0_.nextDouble() * (p_82716_3_ - p_82716_1_) + p_82716_1_);
/*     */   }
/*     */   
/*     */   public static double average(long[] values) {
/* 206 */     long i = 0L;
/* 207 */     long[] lArray = values;
/* 208 */     int n = values.length;
/* 209 */     int n2 = 0;
/* 210 */     while (n2 < n) {
/* 211 */       long j = lArray[n2];
/* 212 */       i += j;
/* 213 */       n2++;
/*     */     } 
/* 215 */     return i / values.length;
/*     */   }
/*     */   
/*     */   public static boolean epsilonEquals(float p_180185_0_, float p_180185_1_) {
/* 219 */     return (abs(p_180185_1_ - p_180185_0_) < 1.0E-5F);
/*     */   }
/*     */   
/*     */   public static int normalizeAngle(int p_180184_0_, int p_180184_1_) {
/* 223 */     return (p_180184_0_ % p_180184_1_ + p_180184_1_) % p_180184_1_;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float wrapAngleTo180_float(float p_76142_0_) {
/* 228 */     p_76142_0_ %= 360.0F;
/*     */     
/* 230 */     if (p_76142_0_ >= 180.0F)
/*     */     {
/* 232 */       p_76142_0_ -= 360.0F;
/*     */     }
/*     */     
/* 235 */     if (p_76142_0_ < -180.0F)
/*     */     {
/* 237 */       p_76142_0_ += 360.0F;
/*     */     }
/*     */     
/* 240 */     return p_76142_0_;
/*     */   }
/*     */   
/*     */   public static double wrapAngleTo180_double(double value) {
/* 244 */     if ((value %= 360.0D) >= 180.0D) {
/* 245 */       value -= 360.0D;
/*     */     }
/* 247 */     if (value < -180.0D) {
/* 248 */       value += 360.0D;
/*     */     }
/* 250 */     return value;
/*     */   }
/*     */   
/*     */   public static int parseIntWithDefault(String p_82715_0_, int p_82715_1_) {
/*     */     try {
/* 255 */       return Integer.parseInt(p_82715_0_);
/*     */     }
/* 257 */     catch (Throwable var3) {
/* 258 */       return p_82715_1_;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int parseIntWithDefaultAndMax(String p_82714_0_, int p_82714_1_, int p_82714_2_) {
/* 263 */     return Math.max(p_82714_2_, parseIntWithDefault(p_82714_0_, p_82714_1_));
/*     */   }
/*     */   
/*     */   public static double parseDoubleWithDefault(String p_82712_0_, double p_82712_1_) {
/*     */     try {
/* 268 */       return Double.parseDouble(p_82712_0_);
/*     */     }
/* 270 */     catch (Throwable var4) {
/* 271 */       return p_82712_1_;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static double parseDoubleWithDefaultAndMax(String p_82713_0_, double p_82713_1_, double p_82713_3_) {
/* 276 */     return Math.max(p_82713_3_, parseDoubleWithDefault(p_82713_0_, p_82713_1_));
/*     */   }
/*     */   
/*     */   public static int roundUpToPowerOfTwo(int value) {
/* 280 */     int i = value - 1;
/* 281 */     i |= i >> 1;
/* 282 */     i |= i >> 2;
/* 283 */     i |= i >> 4;
/* 284 */     i |= i >> 8;
/* 285 */     i |= i >> 16;
/* 286 */     return i + 1;
/*     */   }
/*     */   
/*     */   private static boolean isPowerOfTwo(int value) {
/* 290 */     return (value != 0 && (value & value - 1) == 0);
/*     */   }
/*     */   
/*     */   private static int calculateLogBaseTwoDeBruijn(int value) {
/* 294 */     value = isPowerOfTwo(value) ? value : roundUpToPowerOfTwo(value);
/* 295 */     return multiplyDeBruijnBitPosition[(int)(value * 125613361L >> 27L) & 0x1F];
/*     */   }
/*     */   
/*     */   public static int calculateLogBaseTwo(int value) {
/* 299 */     return calculateLogBaseTwoDeBruijn(value) - (isPowerOfTwo(value) ? 0 : 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int func_154354_b(int p_154354_0_, int p_154354_1_) {
/* 304 */     if (p_154354_1_ == 0) {
/* 305 */       return 0;
/*     */     }
/* 307 */     if (p_154354_0_ == 0) {
/* 308 */       return p_154354_1_;
/*     */     }
/* 310 */     if (p_154354_0_ < 0)
/* 311 */       p_154354_1_ *= -1; 
/*     */     int i;
/* 313 */     return ((i = p_154354_0_ % p_154354_1_) == 0) ? p_154354_0_ : (p_154354_0_ + p_154354_1_ - i);
/*     */   }
/*     */   
/*     */   public static int func_180183_b(float p_180183_0_, float p_180183_1_, float p_180183_2_) {
/* 317 */     return func_180181_b(floor_float(p_180183_0_ * 255.0F), floor_float(p_180183_1_ * 255.0F), floor_float(p_180183_2_ * 255.0F));
/*     */   }
/*     */   
/*     */   public static int func_180181_b(int p_180181_0_, int p_180181_1_, int p_180181_2_) {
/* 321 */     int i = (p_180181_0_ << 8) + p_180181_1_;
/* 322 */     i = (i << 8) + p_180181_2_;
/* 323 */     return i;
/*     */   }
/*     */   
/*     */   public static int func_180188_d(int p_180188_0_, int p_180188_1_) {
/* 327 */     int i = (p_180188_0_ & 0xFF0000) >> 16;
/* 328 */     int j = (p_180188_1_ & 0xFF0000) >> 16;
/* 329 */     int k = (p_180188_0_ & 0xFF00) >> 8;
/* 330 */     int l = (p_180188_1_ & 0xFF00) >> 8;
/* 331 */     int i1 = (p_180188_0_ & 0xFF) >> 0;
/* 332 */     int j1 = (p_180188_1_ & 0xFF) >> 0;
/* 333 */     int k1 = (int)(i * j / 255.0F);
/* 334 */     int l1 = (int)(k * l / 255.0F);
/* 335 */     int i2 = (int)(i1 * j1 / 255.0F);
/* 336 */     return p_180188_0_ & 0xFF000000 | k1 << 16 | l1 << 8 | i2;
/*     */   }
/*     */   
/*     */   public static double func_181162_h(double p_181162_0_) {
/* 340 */     return p_181162_0_ - Math.floor(p_181162_0_);
/*     */   }
/*     */   
/*     */   public static long getCoordinateRandom(int x, int y, int z) {
/* 344 */     long i = (x * 3129871) ^ z * 116129781L ^ y;
/* 345 */     i = i * i * 42317861L + i * 11L;
/* 346 */     return i;
/*     */   }
/*     */   
/*     */   public static UUID getRandomUuid(Random rand) {
/* 350 */     long i = rand.nextLong() & 0xFFFFFFFFFFFF0FFFL | 0x4000L;
/* 351 */     long j = rand.nextLong() & 0x3FFFFFFFFFFFFFFFL | Long.MIN_VALUE;
/* 352 */     return new UUID(i, j);
/*     */   }
/*     */   
/*     */   public static double func_181160_c(double p_181160_0_, double p_181160_2_, double p_181160_4_) {
/* 356 */     return (p_181160_0_ - p_181160_2_) / (p_181160_4_ - p_181160_2_);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double func_181159_b(double p_181159_0_, double p_181159_2_) {
/* 363 */     double d0 = p_181159_2_ * p_181159_2_ + p_181159_0_ * p_181159_0_;
/* 364 */     if (Double.isNaN(d0)) {
/* 365 */       return Double.NaN;
/*     */     }
/* 367 */     boolean flag = (p_181159_0_ < 0.0D);
/* 368 */     if (flag) {
/* 369 */       p_181159_0_ = -p_181159_0_;
/*     */     }
/* 371 */     boolean flag1 = (p_181159_2_ < 0.0D);
/* 372 */     if (flag1) {
/* 373 */       p_181159_2_ = -p_181159_2_;
/*     */     }
/* 375 */     boolean flag2 = (p_181159_0_ > p_181159_2_);
/* 376 */     if (flag2) {
/* 377 */       double d1 = p_181159_2_;
/* 378 */       p_181159_2_ = p_181159_0_;
/* 379 */       p_181159_0_ = d1;
/*     */     } 
/* 381 */     double d9 = func_181161_i(d0);
/* 382 */     double d2 = field_181163_d + (p_181159_0_ *= d9);
/* 383 */     int i = (int)Double.doubleToRawLongBits(d2);
/* 384 */     double d3 = field_181164_e[i];
/* 385 */     double d4 = field_181165_f[i];
/* 386 */     double d5 = d2 - field_181163_d;
/* 387 */     double d6 = p_181159_0_ * d4 - (p_181159_2_ *= d9) * d5;
/* 388 */     double d7 = (6.0D + d6 * d6) * d6 * 0.16666666666666666D;
/* 389 */     double d8 = d3 + d7;
/* 390 */     if (flag2) {
/* 391 */       d8 = 1.5707963267948966D - d8;
/*     */     }
/* 393 */     if (flag1) {
/* 394 */       d8 = Math.PI - d8;
/*     */     }
/* 396 */     if (flag) {
/* 397 */       d8 = -d8;
/*     */     }
/* 399 */     return d8;
/*     */   }
/*     */   
/*     */   public static double func_181161_i(double p_181161_0_) {
/* 403 */     double d0 = 0.5D * p_181161_0_;
/* 404 */     long i = Double.doubleToRawLongBits(p_181161_0_);
/* 405 */     i = 6910469410427058090L - (i >> 1L);
/* 406 */     p_181161_0_ = Double.longBitsToDouble(i);
/* 407 */     p_181161_0_ *= 1.5D - d0 * p_181161_0_ * p_181161_0_;
/* 408 */     return p_181161_0_;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int func_181758_c(float p_181758_0_, float p_181758_1_, float p_181758_2_) {
/*     */     float f6, f5, f4;
/* 415 */     int j, k, l, i = (int)(p_181758_0_ * 6.0F) % 6;
/* 416 */     float f2 = p_181758_0_ * 6.0F - i;
/* 417 */     float f1 = p_181758_2_ * (1.0F - p_181758_1_);
/* 418 */     float f22 = p_181758_2_ * (1.0F - f2 * p_181758_1_);
/* 419 */     float f3 = p_181758_2_ * (1.0F - (1.0F - f2) * p_181758_1_);
/* 420 */     switch (i) {
/*     */       case 0:
/* 422 */         f4 = p_181758_2_;
/* 423 */         f5 = f3;
/* 424 */         f6 = f1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 461 */         j = clamp_int((int)(f4 * 255.0F), 0, 255);
/* 462 */         k = clamp_int((int)(f5 * 255.0F), 0, 255);
/* 463 */         l = clamp_int((int)(f6 * 255.0F), 0, 255);
/* 464 */         return j << 16 | k << 8 | l;case 1: f4 = f22; f5 = p_181758_2_; f6 = f1; j = clamp_int((int)(f4 * 255.0F), 0, 255); k = clamp_int((int)(f5 * 255.0F), 0, 255); l = clamp_int((int)(f6 * 255.0F), 0, 255); return j << 16 | k << 8 | l;case 2: f4 = f1; f5 = p_181758_2_; f6 = f3; j = clamp_int((int)(f4 * 255.0F), 0, 255); k = clamp_int((int)(f5 * 255.0F), 0, 255); l = clamp_int((int)(f6 * 255.0F), 0, 255); return j << 16 | k << 8 | l;case 3: f4 = f1; f5 = f22; f6 = p_181758_2_; j = clamp_int((int)(f4 * 255.0F), 0, 255); k = clamp_int((int)(f5 * 255.0F), 0, 255); l = clamp_int((int)(f6 * 255.0F), 0, 255); return j << 16 | k << 8 | l;case 4: f4 = f3; f5 = f1; f6 = p_181758_2_; j = clamp_int((int)(f4 * 255.0F), 0, 255); k = clamp_int((int)(f5 * 255.0F), 0, 255); l = clamp_int((int)(f6 * 255.0F), 0, 255); return j << 16 | k << 8 | l;case 5: f4 = p_181758_2_; f5 = f1; f6 = f22; j = clamp_int((int)(f4 * 255.0F), 0, 255); k = clamp_int((int)(f5 * 255.0F), 0, 255); l = clamp_int((int)(f6 * 255.0F), 0, 255); return j << 16 | k << 8 | l;
/*     */     } 
/*     */     throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + p_181758_0_ + ", " + p_181758_1_ + ", " + p_181758_2_);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\math\MathHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */