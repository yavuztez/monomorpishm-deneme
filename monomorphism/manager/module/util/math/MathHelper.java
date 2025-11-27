package monomorphism.manager.module.util.math;

import java.util.Random;
import java.util.UUID;

public class MathHelper {

    // [KRITIK] ESP ve Animasyonlar İçin Gerekli Metotlar
    public static double interpolate(double current, double old, double scale) {
        return old + (current - old) * scale;
    }

    public static float interpolate(float current, float old, double scale) {
        return (float) (old + (current - old) * scale);
    }

    public static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
        return (int)interpolate((double)oldValue, (double)newValue, (double)((float)interpolationValue));
    }

    public static float interpolateFloat(float oldValue, float newValue, double interpolationValue) {
        return (float)interpolate((double)oldValue, (double)newValue, (double)((float)interpolationValue));
    }

    // --- STANDART MINECRAFT MATEMATİK FONKSİYONLARI ---
    public static final float SQRT_2 = sqrt_float(2.0F);
    public static final float PI = 3.1415927F;
    public static final float PI2 = 6.2831855F;
    public static final float PId2 = 1.5707964F;
    public static final float deg2Rad = 0.017453292F;
    private static final float[] SIN_TABLE_FAST = new float[4096];
    private static final float[] SIN_TABLE = new float[65536];
    private static final int[] multiplyDeBruijnBitPosition;
    private static final double field_181163_d;
    private static final double[] field_181164_e;
    private static final double[] field_181165_f;
    public static boolean fastMath = false;

    static {
        int k;
        for(k = 0; k < 65536; ++k) {
            SIN_TABLE[k] = (float)Math.sin((double)k * 3.141592653589793D * 2.0D / 65536.0D);
        }
        for(k = 0; k < 4096; ++k) {
            SIN_TABLE_FAST[k] = (float)Math.sin((double)(((float)k + 0.5F) / 4096.0F * 6.2831855F));
        }
        multiplyDeBruijnBitPosition = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
        field_181163_d = Double.longBitsToDouble(4805340802404319232L);
        field_181164_e = new double[257];
        field_181165_f = new double[257];
        for(k = 0; k < 257; ++k) {
            double d1 = (double)k / 256.0D;
            double d0 = Math.asin(d1);
            field_181165_f[k] = Math.cos(d0);
            field_181164_e[k] = d0;
        }
    }

    public static float sin(float value) {
        return fastMath ? SIN_TABLE_FAST[(int)(value * 651.8986F) & 4095] : SIN_TABLE[(int)(value * 10430.378F) & '\uffff'];
    }

    public static float cos(float value) {
        return fastMath ? SIN_TABLE_FAST[(int)((value + 1.5707964F) * 651.8986F) & 4095] : SIN_TABLE[(int)(value * 10430.378F + 16384.0F) & '\uffff'];
    }

    public static float sqrt_float(float value) {
        return (float)Math.sqrt((double)value);
    }

    public static float sqrt_double(double value) {
        return (float)Math.sqrt(value);
    }

    public static int floor_float(float value) {
        int i = (int)value;
        return value < (float)i ? i - 1 : i;
    }

    public static int truncateDoubleToInt(double value) {
        return (int)(value + 1024.0D) - 1024;
    }

    public static int floor_double(double value) {
        int i = (int)value;
        return value < (double)i ? i - 1 : i;
    }

    public static long floor_double_long(double value) {
        long i = (long)value;
        return value < (double)i ? i - 1L : i;
    }

    public static int abs_int(int value) {
        return value >= 0 ? value : -value;
    }

    public static float abs(float value) {
        return value >= 0.0F ? value : -value;
    }

    public static int clamp_int(int num, int min, int max) {
        return num < min ? min : (num > max ? max : num);
    }

    public static float clamp_float(float num, float min, float max) {
        return num < min ? min : (num > max ? max : num);
    }

    public static double clamp_double(double num, double min, double max) {
        return num < min ? min : (num > max ? max : num);
    }

    public static double wrapAngleTo180_double(double value) {
        value %= 360.0D;
        if (value >= 180.0D) value -= 360.0D;
        if (value < -180.0D) value += 360.0D;
        return value;
    }

    public static float wrapAngleTo180_float(float value) {
        value %= 360.0F;
        if (value >= 180.0F) value -= 360.0F;
        if (value < -180.0F) value += 360.0F;
        return value;
    }

    public static int roundUpToPowerOfTwo(int value) {
        int i = value - 1;
        i |= i >> 1;
        i |= i >> 2;
        i |= i >> 4;
        i |= i >> 8;
        i |= i >> 16;
        return i + 1;
    }

    private static boolean isPowerOfTwo(int value) {
        return value != 0 && (value & value - 1) == 0;
    }

    private static int calculateLogBaseTwoDeBruijn(int value) {
        value = isPowerOfTwo(value) ? value : roundUpToPowerOfTwo(value);
        return multiplyDeBruijnBitPosition[(int)((long)value * 125613361L >> 27) & 31];
    }

    public static int calculateLogBaseTwo(int value) {
        return calculateLogBaseTwoDeBruijn(value) - (isPowerOfTwo(value) ? 0 : 1);
    }

    public static UUID getRandomUuid(Random rand) {
        long i = rand.nextLong() & -61441L | 16384L;
        long j = rand.nextLong() & 4611686018427387903L | Long.MIN_VALUE;
        return new UUID(i, j);
    }
}