/*    */ package gov.babalar.myth.utils.other;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ import java.lang.reflect.Method;
/*    */ import net.minecraft.DZ;
/*    */ import net.minecraft.HG;
/*    */ import net.minecraft.Lg;
/*    */ import net.minecraft.Pc;
/*    */ import net.minecraft.f3;
/*    */ import net.minecraft.r4;
/*    */ import net.minecraft.rE;
/*    */ 
/*    */ public class InventoryUtil {
/*    */   public static void swap(int slot, int hSlot) {
/* 15 */     Utility.getPlayerController().m(getWindowID(), slot, hSlot, 2, (DZ)Utility.getThePlayer());
/*    */   }
/*    */   
/*    */   public static void drop(int slot) {
/* 19 */     Utility.getPlayerController().m(0, slot, 1, 4, (DZ)Utility.getThePlayer());
/*    */   }
/*    */   
/*    */   public static void click(int slot, int mouseButton, boolean shiftClick) {
/* 23 */     Utility.getPlayerController().m(getWindowID(), slot, mouseButton, shiftClick ? 1 : 0, (DZ)Utility.getThePlayer());
/*    */   }
/*    */   
/*    */   public static float getProtScore(f3 stack) {
/* 27 */     float prot = 0.0F;
/* 28 */     if (stack.C() instanceof rE) {
/* 29 */       rE armor = (rE)stack.C();
/* 30 */       prot += armor.V + ((100 - armor.V) * HG.L(Pc.p.O, stack)) * 0.0075F;
/* 31 */       prot += HG.L(Pc.W.O, stack) / 100.0F;
/* 32 */       prot += HG.L(Pc.X.O, stack) / 100.0F;
/* 33 */       prot += HG.L(Pc.H.O, stack) / 100.0F;
/* 34 */       prot += HG.L(Pc.E.O, stack) / 25.0F;
/* 35 */       prot += HG.L(Pc.P.O, stack) / 100.0F;
/*    */     } 
/* 37 */     return prot;
/*    */   }
/*    */   
/*    */   public static float getDamageScore(f3 stack) {
/* 41 */     if (stack == null || stack.C() == null) return 0.0F;
/*    */     
/* 43 */     float damage = 0.0F;
/* 44 */     r4 item = stack.C();
/*    */     
/* 46 */     if (item instanceof Lg) {
/* 47 */       damage += ((Lg)item).e();
/* 48 */     } else if (item instanceof net.minecraft.L4) {
/* 49 */       damage += getItemAttackDamage(item);
/*    */     } 
/*    */     
/* 52 */     damage += HG.L(Pc.j.O, stack) * 1.25F + 
/* 53 */       HG.L(Pc.C.O, stack) * 0.1F;
/*    */     
/* 55 */     return damage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static float getItemAttackDamage(r4 item) {
/*    */     try {
/* 63 */       for (Method m : item.getClass().getDeclaredMethods()) {
/* 64 */         if (m.getName().equals("y") && (m
/* 65 */           .getReturnType() == float.class || m
/* 66 */           .getReturnType() == double.class || m
/* 67 */           .getReturnType() == int.class)) {
/*    */           
/* 69 */           m.setAccessible(true);
/* 70 */           Object result = m.invoke(item, new Object[0]);
/*    */           
/* 72 */           if (result instanceof Number) {
/* 73 */             return ((Number)result).floatValue();
/*    */           }
/*    */         } 
/*    */       } 
/* 77 */     } catch (Exception exception) {}
/*    */     
/* 79 */     return 0.0F;
/*    */   }
/*    */   
/*    */   public static boolean isWhitelisted(f3 itemStack) {
/* 83 */     String itemStackName = itemStack.toString().toLowerCase();
/* 84 */     return (itemStackName.contains("applegold") || itemStackName
/* 85 */       .contains("tnt") || itemStackName
/* 86 */       .contains("chestplate") || itemStackName
/* 87 */       .contains("sword") || itemStackName
/* 88 */       .contains("leggings") || itemStackName
/* 89 */       .contains("pearl") || itemStackName
/* 90 */       .contains("helmet") || itemStackName
/* 91 */       .contains("boots") || itemStackName
/* 92 */       .contains("tile."));
/*    */   }
/*    */   
/*    */   public static int getWindowID() {
/* 96 */     return (Utility.getThePlayer()).FF.W;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\other\InventoryUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */