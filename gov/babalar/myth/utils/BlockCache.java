/*    */ package gov.babalar.myth.utils;
/*    */ 
/*    */ import net.minecraft.BlockPos;
/*    */ import net.minecraft.Qu;
/*    */ 
/*    */ public class BlockCache
/*    */ {
/*    */   private final BlockPos position;
/*    */   private final Qu facing;
/*    */   
/*    */   public BlockCache(BlockPos position, Qu facing) {
/* 12 */     this.position = position;
/* 13 */     this.facing = facing;
/*    */   }
/*    */   
/*    */   public BlockPos getPosition() {
/* 17 */     return this.position;
/*    */   }
/*    */   
/*    */   public Qu getFacing() {
/* 21 */     return this.facing;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myt\\utils\BlockCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */