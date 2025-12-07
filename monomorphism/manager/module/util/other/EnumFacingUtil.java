/*    */ package monomorphism.manager.module.util.other;
/*    */ 
/*    */ import net.minecraft.fI;
/*    */ 
/*    */ public class EnumFacingUtil
/*    */ {
/*    */   public static fI getOpposite(fI current) {
/*  8 */     fI[] array = fI.VALUES;
/*  9 */     fI nigg = null;
/* 10 */     switch (current) {
/*    */       case null:
/* 12 */         nigg = array[1];
/*    */         break;
/*    */       case UP:
/* 15 */         nigg = array[0];
/*    */         break;
/*    */       case NORTH:
/* 18 */         nigg = array[3];
/*    */         break;
/*    */       case SOUTH:
/* 21 */         nigg = array[2];
/*    */         break;
/*    */       case WEST:
/* 24 */         nigg = array[5];
/*    */         break;
/*    */       case EAST:
/* 27 */         nigg = array[4];
/*    */         break;
/*    */     } 
/* 30 */     return nigg;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\other\EnumFacingUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */