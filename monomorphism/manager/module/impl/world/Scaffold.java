package monomorphism.manager.module.impl.world;

import com.google.common.eventbus.Subscribe;
import excluded.WorldClient;
import java.lang.reflect.Field;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.event.EventPacket;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.move.MovementUtil;
import monomorphism.manager.module.util.other.BlockInfo;
import monomorphism.manager.module.util.other.EnumFacingUtil;
import monomorphism.manager.setting.SettingsManager;
import net.minecraft.BlockPos;
import net.minecraft.cl;
import net.minecraft.fI;
import net.minecraft.oZ;
import net.minecraft.q_;
import net.minecraft.tF;
import net.minecraft.u0;
import net.minecraft.y5;
import net.minecraft.yg;

public class Scaffold extends Module {
   private int lastSlot = -1;

   public Scaffold() {
      super("Scaffold [FIX]", 45, Category.World);
      SettingsManager.manager.addBoolean("Swing", "scafS", true, this);
   }

   public void onEnable() {
      super.onEnable();
      this.lastSlot = -1;
   }

   public void onDisable() {
      super.onDisable();
      if (this.lastSlot != getThePlayer().b3.f) {
         getThePlayer().ct.a(new u0(getThePlayer().b3.f));
      }

      this.lastSlot = -1;
   }

   @Subscribe
   public void onPacket(EventPacket event) {
      yg<?> packet = event.packet;
      if (packet instanceof u0) {
         u0 c09 = (u0)packet;
         this.lastSlot = c09.b();
      }

   }

   @Subscribe
   public void onMotion(EventMotion event) {
      BlockPos targetPos = null;

      label55:
      for(double forwardExtend = 0.0D; forwardExtend <= 0.2D; forwardExtend += 0.1D) {
         for(int i = 0; i <= 1; ++i) {
            BlockPos temp = MovementUtil.getForwardBlockFromMovement(forwardExtend).a(0, -1, 0);
            if (WorldClient.getBlock(WorldClient.getBlockState(temp)) == cl.L) {
               targetPos = temp;
               break label55;
            }

            if (!MovementUtil.isMoving()) {
               break label55;
            }
         }
      }

      BlockInfo info = this.findFacingAndBlockPosForBlock(targetPos);
      if (info != null) {
         int y = 0;

         try {
            Field field1 = tF.class.getDeclaredField("b");
            field1.setAccessible(true);
            y = (Integer)field1.get(info.pos);
         } catch (Exception var7) {
         }

         if (info.facing != fI.UP || !(getThePlayer().ao - (double)y > 0.0D) || !(getThePlayer().ao - (double)y <= 2.1D) || MovementUtil.isOnGround(1.0E-4D)) {
            y5 vector = MovementUtil.getVectorForRotation(getRotationYaw(), getRotationPitch());
            q_ block = this.setStackToPlace();
            if (SettingsManager.manager.getSettingByName("scafS").getValBoolean()) {
               swingItem();
            }

            mc.Q.a(getThePlayer(), mc.I, block, info.pos, info.facing, vector);
         }
      }
   }

   public q_ setStackToPlace() {
      q_ block = getThePlayer().b3.b();
      if (block != null && block.a() != null && !(block.a() instanceof oZ)) {
         block = null;
      }

      int slot = getThePlayer().b3.f;

      for(short g = 0; g < 9; ++g) {
         if (getThePlayer().bN.a(g + 36).b() && getThePlayer().bN.a(g + 36).a().a() instanceof oZ && getThePlayer().bN.a(g + 36).a().l != 0 && (block == null || block.a() instanceof oZ && getThePlayer().bN.a(g + 36).a().l >= block.l)) {
            slot = g;
            block = getThePlayer().bN.a(g + 36).a();
         }
      }

      if (this.lastSlot + (this.lastSlot >= 36 ? -36 : 0) != slot) {
         getThePlayer().ct.a(new u0(slot));
         this.lastSlot = slot;
      }

      return block;
   }

   private BlockInfo findFacingAndBlockPosForBlock(BlockPos input) {
      BlockInfo output = new BlockInfo();
      output.pos = input;
      fI[] var6;
      int var5 = (var6 = fI.VALUES).length;

      fI face3;
      int var4;
      int y;
      int z;
      for(var4 = 0; var4 < var5; ++var4) {
         face3 = var6[var4];
         if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face3))) != cl.L) {
            output.pos = output.pos.a(face3);
            output.facing = EnumFacingUtil.getOpposite(face3);
            int x = 0;
            y = 0;
            z = 0;

            try {
               Field field = tF.class.getDeclaredField("e");
               field.setAccessible(true);
               x = (Integer)field.get(input);
               Field field1 = tF.class.getDeclaredField("b");
               field1.setAccessible(true);
               y = (Integer)field1.get(input);
               Field field2 = tF.class.getDeclaredField("a");
               field2.setAccessible(true);
               z = (Integer)field2.get(input);
            } catch (Exception var19) {
            }

            output.targetPos = new BlockPos(x, y, z);
            return output;
         }
      }

      var5 = (var6 = fI.VALUES).length;

      fI face2;
      fI[] var21;
      for(var4 = 0; var4 < var5; ++var4) {
         face3 = var6[var4];
         if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face3))) == cl.L) {
            z = (var21 = fI.VALUES).length;

            for(y = 0; y < z; ++y) {
               face2 = var21[y];
               if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face3).a(face2))) != cl.L) {
                  output.pos = output.pos.a(face3).a(face2);
                  output.facing = EnumFacingUtil.getOpposite(face3);
                  output.targetPos = output.pos.a(face3);
                  return output;
               }
            }
         }
      }

      var5 = (var6 = fI.VALUES).length;

      int var13;
      fI[] var14;
      fI face;
      int var23;
      for(var4 = 0; var4 < var5; ++var4) {
         face3 = var6[var4];
         z = (var21 = fI.VALUES).length;

         for(y = 0; y < z; ++y) {
            face2 = var21[y];
            if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face2).a(face3))) == cl.L) {
               var13 = (var14 = fI.VALUES).length;

               for(var23 = 0; var23 < var13; ++var23) {
                  face = var14[var23];
                  if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face2).a(face).a(face3))) != cl.L) {
                     output.pos = output.pos.a(face2).a(face).a(face3);
                     output.facing = EnumFacingUtil.getOpposite(face3);
                     output.targetPos = output.pos.a(face2).a(face3);
                     return output;
                  }
               }
            }
         }
      }

      var5 = (var6 = fI.VALUES).length;

      for(var4 = 0; var4 < var5; ++var4) {
         face3 = var6[var4];
         z = (var21 = fI.VALUES).length;

         for(y = 0; y < z; ++y) {
            face2 = var21[y];
            var13 = (var14 = fI.VALUES).length;

            for(var23 = 0; var23 < var13; ++var23) {
               face = var14[var23];
               if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face).a(face2).a(face3))) == cl.L) {
                  fI[] var18;
                  int var17 = (var18 = fI.VALUES).length;

                  for(int var16 = 0; var16 < var17; ++var16) {
                     fI face1 = var18[var16];
                     if (WorldClient.getBlock(WorldClient.getBlockState(output.pos.a(face).a(face1).a(face2).a(face3))) != cl.L) {
                        output.pos = output.pos.a(face).a(face1).a(face2).a(face3);
                        output.facing = EnumFacingUtil.getOpposite(face3);
                        output.targetPos = output.pos.a(face).a(face2).a(face3);
                        return output;
                     }
                  }
               }
            }
         }
      }

      return null;
   }
}
