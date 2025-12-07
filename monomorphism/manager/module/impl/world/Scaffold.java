/*     */ package monomorphism.manager.module.impl.world;
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import excluded.WorldClient;
/*     */ import java.lang.reflect.Field;
/*     */ import monomorphism.manager.event.EventPacket;
/*     */ import monomorphism.manager.module.util.move.MovementUtil;
/*     */ import monomorphism.manager.module.util.other.BlockInfo;
/*     */ import monomorphism.manager.module.util.other.EnumFacingUtil;
/*     */ import monomorphism.manager.setting.SettingsManager;
/*     */ import net.minecraft.BlockPos;
/*     */ import net.minecraft.cl;
/*     */ import net.minecraft.fI;
/*     */ import net.minecraft.q_;
/*     */ import net.minecraft.tF;
/*     */ import net.minecraft.u0;
/*     */ import net.minecraft.y5;
/*     */ import net.minecraft.yg;
/*     */ 
/*     */ public class Scaffold extends Module {
/*  20 */   private int lastSlot = -1;
/*     */   
/*     */   public Scaffold() {
/*  23 */     super("Scaffold [FIX]", 45, Category.World);
/*  24 */     SettingsManager.manager.addBoolean("Swing", "scafS", true, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  29 */     super.onEnable();
/*  30 */     this.lastSlot = -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onDisable() {
/*  35 */     super.onDisable();
/*  36 */     if (this.lastSlot != (getThePlayer()).b3.f) {
/*  37 */       (getThePlayer()).ct.a((yg)new u0((getThePlayer()).b3.f));
/*     */     }
/*  39 */     this.lastSlot = -1;
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onPacket(EventPacket event) {
/*  44 */     yg<?> packet = event.packet;
/*  45 */     if (packet instanceof u0) {
/*  46 */       u0 c09 = (u0)packet;
/*  47 */       this.lastSlot = c09.b();
/*     */     } 
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onMotion(EventMotion event) {
/*  53 */     BlockPos targetPos = null; double forwardExtend;
/*  54 */     label36: for (forwardExtend = 0.0D; forwardExtend <= 0.2D; forwardExtend += 0.1D) {
/*  55 */       for (int i = 0; i <= 1; i++) {
/*  56 */         BlockPos temp = MovementUtil.getForwardBlockFromMovement(forwardExtend).a(0, -1, 0);
/*  57 */         if (WorldClient.getBlock(WorldClient.getBlockState(temp)) == cl.L) {
/*  58 */           targetPos = temp;
/*     */           break label36;
/*     */         } 
/*  61 */         if (!MovementUtil.isMoving()) {
/*     */           break label36;
/*     */         }
/*     */       } 
/*     */     } 
/*  66 */     BlockInfo info = findFacingAndBlockPosForBlock(targetPos);
/*  67 */     if (info == null)
/*     */       return; 
/*  69 */     int y = 0;
/*     */     try {
/*  71 */       Field field1 = tF.class.getDeclaredField("b");
/*  72 */       field1.setAccessible(true);
/*  73 */       y = ((Integer)field1.get(info.pos)).intValue();
/*  74 */     } catch (Exception exception) {}
/*     */     
/*  76 */     if (info.facing == fI.UP && (getThePlayer()).ao - y > 0.0D && (getThePlayer()).ao - y <= 2.1D && 
/*  77 */       !MovementUtil.isOnGround(1.0E-4D)) {
/*     */       return;
/*     */     }
/*  80 */     y5 vector = MovementUtil.getVectorForRotation(getRotationYaw(), getRotationPitch());
/*  81 */     q_ block = setStackToPlace();
/*  82 */     if (SettingsManager.manager.getSettingByName("scafS").getValBoolean()) {
/*  83 */       swingItem();
/*     */     }
/*  85 */     mc.Q.a(getThePlayer(), mc.I, block, info.pos, info.facing, vector);
/*     */   }
/*     */   
/*     */   public q_ setStackToPlace() {
/*  89 */     q_ block = (getThePlayer()).b3.b();
/*  90 */     if (block != null && block.a() != null && !(block.a() instanceof net.minecraft.oZ)) {
/*  91 */       block = null;
/*     */     }
/*  93 */     int slot = (getThePlayer()).b3.f;
/*  94 */     for (short g = 0; g < 9; g = (short)(g + 1)) {
/*  95 */       if ((getThePlayer()).bN.a(g + 36).b() && (getThePlayer()).bN.a(g + 36).a().a() instanceof net.minecraft.oZ && 
/*  96 */         ((getThePlayer()).bN.a(g + 36).a()).l != 0 && (
/*  97 */         block == null || (block.a() instanceof net.minecraft.oZ && ((getThePlayer()).bN.a(g + 36).a()).l >= block.l))) {
/*  98 */         slot = g;
/*  99 */         block = (getThePlayer()).bN.a(g + 36).a();
/*     */       } 
/*     */     } 
/* 102 */     if (this.lastSlot + ((this.lastSlot >= 36) ? -36 : 0) != slot) {
/* 103 */       (getThePlayer()).ct.a((yg)new u0(slot));
/* 104 */       this.lastSlot = slot;
/*     */     } 
/* 106 */     return block;
/*     */   }
/*     */   
/*     */   private BlockInfo findFacingAndBlockPosForBlock(BlockPos input) {
/* 110 */     BlockInfo output = new BlockInfo();
/* 111 */     output.pos = input; byte b; int i; fI[] arrayOfFI;
/* 112 */     for (i = (arrayOfFI = fI.VALUES).length, b = 0; b < i; ) { fI face = arrayOfFI[b];
/* 113 */       if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face))) != cl.L) {
/* 114 */         output.pos = output.pos.a(face);
/* 115 */         output.facing = EnumFacingUtil.getOpposite(face);
/* 116 */         int x = 0, y = 0, z = 0;
/*     */         try {
/* 118 */           Field field = tF.class.getDeclaredField("e");
/* 119 */           field.setAccessible(true);
/* 120 */           x = ((Integer)field.get(input)).intValue();
/* 121 */           Field field1 = tF.class.getDeclaredField("b");
/* 122 */           field1.setAccessible(true);
/* 123 */           y = ((Integer)field1.get(input)).intValue();
/* 124 */           Field field2 = tF.class.getDeclaredField("a");
/* 125 */           field2.setAccessible(true);
/* 126 */           z = ((Integer)field2.get(input)).intValue();
/* 127 */         } catch (Exception exception) {}
/*     */         
/* 129 */         output.targetPos = new BlockPos(x, y, z);
/* 130 */         return output;
/*     */       }  b++; }
/*     */     
/* 133 */     for (i = (arrayOfFI = fI.VALUES).length, b = 0; b < i; ) { fI face = arrayOfFI[b];
/* 134 */       if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face))) == cl.L) {
/* 135 */         byte b1; int j; fI[] arrayOfFI1; for (j = (arrayOfFI1 = fI.VALUES).length, b1 = 0; b1 < j; ) { fI face1 = arrayOfFI1[b1];
/* 136 */           if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face).a(face1))) != cl.L) {
/* 137 */             output.pos = output.pos.a(face).a(face1);
/* 138 */             output.facing = EnumFacingUtil.getOpposite(face);
/* 139 */             output.targetPos = output.pos.a(face);
/* 140 */             return output;
/*     */           }  b1++; }
/*     */       
/*     */       }  b++; }
/*     */     
/* 145 */     for (i = (arrayOfFI = fI.VALUES).length, b = 0; b < i; ) { fI face2 = arrayOfFI[b]; byte b1; int j; fI[] arrayOfFI1;
/* 146 */       for (j = (arrayOfFI1 = fI.VALUES).length, b1 = 0; b1 < j; ) { fI face = arrayOfFI1[b1];
/* 147 */         if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face).a(face2))) == cl.L) {
/* 148 */           byte b2; int k; fI[] arrayOfFI2; for (k = (arrayOfFI2 = fI.VALUES).length, b2 = 0; b2 < k; ) { fI face1 = arrayOfFI2[b2];
/*     */             
/* 150 */             if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face).a(face1).a(face2))) != cl.L) {
/* 151 */               output.pos = output.pos.a(face).a(face1).a(face2);
/* 152 */               output.facing = EnumFacingUtil.getOpposite(face2);
/* 153 */               output.targetPos = output.pos.a(face).a(face2);
/* 154 */               return output;
/*     */             }  b2++; }
/*     */         
/*     */         }  b1++; }
/*     */        b++; }
/*     */     
/* 160 */     for (i = (arrayOfFI = fI.VALUES).length, b = 0; b < i; ) { fI face3 = arrayOfFI[b]; byte b1; int j; fI[] arrayOfFI1;
/* 161 */       for (j = (arrayOfFI1 = fI.VALUES).length, b1 = 0; b1 < j; ) { fI face2 = arrayOfFI1[b1]; byte b2; int k; fI[] arrayOfFI2;
/* 162 */         for (k = (arrayOfFI2 = fI.VALUES).length, b2 = 0; b2 < k; ) { fI face = arrayOfFI2[b2];
/* 163 */           if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face).a(face2).a(face3))) == cl.L) {
/* 164 */             byte b3; int m; fI[] arrayOfFI3; for (m = (arrayOfFI3 = fI.VALUES).length, b3 = 0; b3 < m; ) { fI face1 = arrayOfFI3[b3];
/* 165 */               if (WorldClient.getBlock(
/* 166 */                   WorldClient.getBlockState(output.pos.a(face).a(face1).a(face2).a(face3))) != cl.L) {
/* 167 */                 output.pos = output.pos.a(face).a(face1).a(face2).a(face3);
/* 168 */                 output.facing = EnumFacingUtil.getOpposite(face3);
/* 169 */                 output.targetPos = output.pos.a(face).a(face2).a(face3);
/* 170 */                 return output;
/*     */               }  b3++; }
/*     */           
/*     */           }  b2++; }
/*     */          b1++; }
/*     */        b++; }
/*     */     
/* 177 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\world\Scaffold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */