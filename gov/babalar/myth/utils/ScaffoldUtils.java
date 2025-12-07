/*     */ package gov.babalar.myth.utils;
/*     */ import gov.babalar.myth.Utility;
/*     */ import gov.babalar.myth.module.misc.Scaffold;
/*     */ import java.lang.reflect.Method;
/*     */ import net.minecraft.BlockPos;
/*     */ import net.minecraft.Qu;
/*     */ import net.minecraft.f3;
/*     */ import net.minecraft.fH;
/*     */ import net.minecraft.ri;
/*     */ 
/*     */ public class ScaffoldUtils {
/*     */   public static er getHypixelVec3(BlockCache data) {
/*  13 */     BlockPos pos = data.getPosition();
/*  14 */     Qu face = data.getFacing();
/*     */     
/*  16 */     double x = pos.X() + 0.5D;
/*  17 */     double y = pos.W() + 0.5D;
/*  18 */     double z = pos.k() + 0.5D;
/*     */     
/*  20 */     if (face != Qu.UP && face != Qu.DOWN) {
/*  21 */       y += 0.5D;
/*     */     } else {
/*  23 */       x += 0.3D;
/*  24 */       z += 0.3D;
/*     */     } 
/*  26 */     if (face == Qu.WEST || face == Qu.EAST) {
/*  27 */       z += 0.15D;
/*     */     }
/*  29 */     if (face == Qu.SOUTH || face == Qu.NORTH) {
/*  30 */       x += 0.15D;
/*     */     }
/*  32 */     return new er(x, y, z);
/*     */   }
/*     */   
/*     */   public static double getYLevel() {
/*  36 */     return (Utility.getPosY() - 1.0D >= Scaffold.keepYCoord && 
/*  37 */       Math.max(Utility.getPosY(), Scaffold.keepYCoord) - 
/*  38 */       Math.min(Utility.getPosY(), Scaffold.keepYCoord) <= 3.0D && 
/*  39 */       !(Utility.getGameSettings()).uw.f()) ? Scaffold.keepYCoord : (
/*     */       
/*  41 */       Utility.getPosY() - 1.0D);
/*     */   }
/*     */   
/*     */   public static BlockCache getBlockInfo() {
/*  45 */     BlockPos belowBlockPos = new BlockPos(Utility.getPosX(), getYLevel(), Utility.getPosZ());
/*     */     
/*  47 */     if (Utility.getBlockState(belowBlockPos).N() instanceof net.minecraft.fD)
/*  48 */       for (int x = 0; x < 4; x++) {
/*  49 */         for (int z = 0; z < 4; z++) {
/*  50 */           for (int i = 1; i > -3; i -= 2) {
/*     */             
/*  52 */             BlockPos blockPos = belowBlockPos.V(x * i, 0, z * i);
/*  53 */             fH block = Utility.getBlockState(blockPos).N();
/*     */             
/*  55 */             if (block instanceof net.minecraft.fD) {
/*  56 */               for (Qu direction : Qu.VALUES) {
/*  57 */                 BlockPos blockPos1 = blockPos.k(direction, 1);
/*  58 */                 NE material = Utility.getBlockState(blockPos).N().M();
/*     */                 
/*  60 */                 if (block.s((el)Utility.getTheWorld(), blockPos1, direction)) {
/*  61 */                   return new BlockCache(blockPos1, direction.H());
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }  
/*  68 */     return null;
/*     */   }
/*     */   
/*     */   public static int getBlockSlot() {
/*  72 */     for (int i = 0; i < 9; i++) {
/*  73 */       f3 itemStack = (Utility.getThePlayer()).Fc.i[i];
/*  74 */       if (itemStack != null && itemStack.C() instanceof ri && itemStack.r > 0) {
/*  75 */         ri itemBlock = (ri)itemStack.C();
/*  76 */         if (isBlockValid(itemBlock.y())) {
/*  77 */           return i;
/*     */         }
/*     */       } 
/*     */     } 
/*  81 */     return -1;
/*     */   }
/*     */   
/*     */   public static int getBlockCount() {
/*  85 */     int count = 0;
/*  86 */     for (int i = 0; i < 9; i++) {
/*  87 */       f3 itemStack = (Utility.getThePlayer()).Fc.i[i];
/*  88 */       if (itemStack != null && itemStack.C() instanceof ri && itemStack.r > 0) {
/*  89 */         ri itemBlock = (ri)itemStack.C();
/*  90 */         if (isBlockValid(itemBlock.y())) {
/*  91 */           count += itemStack.r;
/*     */         }
/*     */       } 
/*     */     } 
/*  95 */     return count;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean getFullBlockFlag(fH block) {
/*     */     try {
/* 103 */       Method m = fH.class.getDeclaredMethod("q", new Class[0]);
/* 104 */       m.setAccessible(true);
/* 105 */       Object result = m.invoke(block, new Object[0]);
/*     */       
/* 107 */       if (result instanceof Boolean) {
/* 108 */         return ((Boolean)result).booleanValue();
/*     */       }
/* 110 */     } catch (Exception exception) {}
/*     */     
/* 112 */     return false;
/*     */   }
/*     */   
/*     */   private static boolean isBlockValid(fH block) {
/* 116 */     boolean fullBlock = getFullBlockFlag(block);
/*     */     
/* 118 */     return ((fullBlock || block.toString().contains("glass")) && 
/* 119 */       !block.toString().contains("sand") && 
/* 120 */       !block.toString().contains("gravel") && 
/* 121 */       !block.toString().contains("dispenser") && 
/* 122 */       !block.toString().contains("commandBlock") && 
/* 123 */       !block.toString().contains("musicBlock") && 
/* 124 */       !block.toString().contains("furnace") && 
/* 125 */       !block.toString().contains("workbench") && 
/* 126 */       !block.toString().contains("tnt") && 
/* 127 */       !block.toString().contains("dropper") && 
/* 128 */       !block.toString().contains("beacon"));
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\ScaffoldUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */