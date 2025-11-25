package monomorphism.manager.module.impl.combat;



import com.google.common.eventbus.Subscribe;

import monomorphism.manager.event.EventMotion;

import monomorphism.manager.mapper.Entity;

import monomorphism.manager.module.Category;

import monomorphism.manager.module.Module;

import net.minecraft.nn;



public class TargetTeleport extends Module {

    public TargetTeleport() {

        super("Target Teleport", 41, Category.Combat);

    }



    @Subscribe

    public void onMotion(EventMotion event) {

        if (KillAura.target != null) {

            nn target = KillAura.target;

            double targetPosX = Entity.getPosX(target);

            double targetPosY = Entity.getPosY(target);

            double targetPosZ = Entity.getPosZ(target);



            // Hedefin Y pozisyonunun 2.0D üzerine ışınlanmayı zorunlu kıl.

            // Bu, oyuncunun (yaklaşık 1.8 blok uzunluğunda) üstünde kalmanızı sağlar.

            double finalY = targetPosY + 2.0D;



            // Oyuncuyu hesaplanan pozisyona sürekli ışınla.

            getThePlayer().d(targetPosX, finalY, targetPosZ);

        }

    }

}