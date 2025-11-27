package monomorphism.manager.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Field;
import java.util.ArrayList;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.mapper.Entity;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.setting.SettingsManager;
import net.minecraft.nn;
import net.minecraft.fx; // C06PacketPlayerPosLook

public class TargetTeleport extends Module {

    private double circleAngle = 0;

    public TargetTeleport() {
        super("Target Teleport", 41, Category.Combat);

        ArrayList<String> modes = new ArrayList<>();
        modes.add("Above");
        modes.add("Behind");
        modes.add("Circle");

        SettingsManager.manager.addMode("Mode", "tpMode", "Behind", modes, this);
    }

    @Subscribe
    public void onMotion(EventMotion event) {
        if (KillAura.target == null) return;

        nn target = KillAura.target;

        double tX = Entity.getPosX(target);
        double tY = Entity.getPosY(target);
        double tZ = Entity.getPosZ(target);

        String mode = "Above";
        try {
            mode = SettingsManager.manager.getSettingByName("tpMode").getValString();
        } catch (Exception e) {}

        double dstX = tX;
        double dstY = tY;
        double dstZ = tZ;

        switch (mode) {
            case "Above":
                // 2.5 blok yukarı (Kafasına çarpmaz)
                dstY += 2.5D;
                break;

            case "Behind":
                // Tam arkası (1.5 blok mesafe)
                float yaw = getRotationYaw(target);
                double dist = 1.5D;
                double rad = Math.toRadians(yaw);

                // Matematik düzeltildi: Sin/Cos yönleri ayarlandı
                dstX -= Math.sin(rad) * dist;
                dstZ += Math.cos(rad) * dist;
                break;

            case "Circle":
                // Hızlı dönüş (0.4 hız, 2.5 yarıçap - içine girmemesi için genişletildi)
                this.circleAngle += 0.4D;
                double radius = 2.5D;

                dstX += Math.cos(this.circleAngle) * radius;
                dstZ += Math.sin(this.circleAngle) * radius;
                break;
        }

        // --- VULCAN BYPASS ---
        double myX = getPosX();
        double myY = getPosY();
        double myZ = getPosZ();
        float myYaw = getSelfRotationYaw();

        double distance = Math.sqrt(Math.pow(dstX - myX, 2) + Math.pow(dstY - myY, 2) + Math.pow(dstZ - myZ, 2));

        // Mesafeyi sınırla (Vulcan uzun atlamayı sevmez)
        if (distance > 6.0D) return;
        if (distance < 0.2D) return; // Çok yakınsa elleme

        // Paket Spam (Adım adım git)
        double stepSize = 0.25D; // Küçük adımlar
        double packets = Math.ceil(distance / stepSize);

        for (int i = 1; i <= packets; i++) {
            double pct = (double) i / packets;
            double x = myX + (dstX - myX) * pct;
            double y = myY + (dstY - myY) * pct;
            double z = myZ + (dstZ - myZ) * pct;

            // [FIX] 5 Argümanlı (Pitch olmadan)
            sendPacket(new fx(x, y, z, true, myYaw));
        }

        // Client tarafında güncelle
        getThePlayer().d(dstX, dstY, dstZ);
    }

    private float getRotationYaw(nn entity) {
        try {
            Field f = entity.getClass().getField("rotationYaw");
            return f.getFloat(entity);
        } catch (Exception e) {
            try {
                Field f2 = entity.getClass().getDeclaredField("y");
                f2.setAccessible(true);
                return f2.getFloat(entity);
            } catch (Exception e2) {
                try {
                    Field f3 = entity.getClass().getDeclaredField("A");
                    f3.setAccessible(true);
                    return f3.getFloat(entity);
                } catch(Exception e3) { return 0.0f; }
            }
        }
    }

    private float getSelfRotationYaw() {
        return Entity.getRotationYaw();
    }
}