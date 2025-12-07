/*    */ package gov.babalar.myth.utils.player;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ import net.minecraft.Tz;
/*    */ import net.minecraft.oA;
/*    */ import net.minecraft.tB;
/*    */ import net.minecraft.y;
/*    */ 
/*    */ public class ChatUtil
/*    */ {
/*    */   public static void print(boolean prefix, String message) {
/* 12 */     if (Utility.getThePlayer() != null) {
/* 13 */       if (prefix) message = "§sMyth §8» §f" + message; 
/* 14 */       Utility.getThePlayer().b((oA)new tB(message));
/*    */     } 
/*    */   }
/*    */   public static void print(Object o) {
/* 18 */     print(true, String.valueOf(o));
/*    */   }
/*    */   
/*    */   public static void send(String message) {
/* 22 */     if (Utility.getThePlayer() != null)
/* 23 */       Utility.sendPacket((Tz)new y(message)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\player\ChatUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */