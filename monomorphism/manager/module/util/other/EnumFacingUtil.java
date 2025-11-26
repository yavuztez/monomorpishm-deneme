package monomorphism.manager.module.util.other;

import net.minecraft.fI;

public class EnumFacingUtil {
   // $FF: synthetic field
   private static volatile int[] $SWITCH_TABLE$net$minecraft$fI;

   public static fI getOpposite(fI current) {
      fI[] array = fI.VALUES;
      fI nigg = null;
      switch($SWITCH_TABLE$net$minecraft$fI()[current.ordinal()]) {
      case 1:
         nigg = array[1];
         break;
      case 2:
         nigg = array[0];
         break;
      case 3:
         nigg = array[3];
         break;
      case 4:
         nigg = array[2];
         break;
      case 5:
         nigg = array[5];
         break;
      case 6:
         nigg = array[4];
      }

      return nigg;
   }

   // $FF: synthetic method
   static int[] $SWITCH_TABLE$net$minecraft$fI() {
      int[] var10000 = $SWITCH_TABLE$net$minecraft$fI;
      if (var10000 != null) {
         return var10000;
      } else {
         int[] var0 = new int[fI.values().length];

         try {
            var0[fI.DOWN.ordinal()] = 1;
         } catch (NoSuchFieldError var6) {
         }

         try {
            var0[fI.EAST.ordinal()] = 6;
         } catch (NoSuchFieldError var5) {
         }

         try {
            var0[fI.NORTH.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[fI.SOUTH.ordinal()] = 4;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[fI.UP.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         try {
            var0[fI.WEST.ordinal()] = 5;
         } catch (NoSuchFieldError var1) {
         }

         $SWITCH_TABLE$net$minecraft$fI = var0;
         return var0;
      }
   }
}
