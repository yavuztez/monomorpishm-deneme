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

        String mode = "Behind";
        try {
            mode = SettingsManager.manager.getSettingByName("tpMode").getValString();
        } catch (Exception e) {}

        double dstX = tX;
        double dstY = tY;
        double dstZ = tZ;

        switch (mode) {
            case "Above":
                // 3.0 blok yukarı (Garanti mesafe)
                dstY += 3.0D;
                break;

            case "Behind":
                // [FIX] Hedefin tam arkasına geçme matematiği
                float yaw = getRotationYaw(target);
                double dist = 2.0D; // Mesafe 2.0 blok (İçine girmesin)
                double rad = Math.toRadians(yaw);

                // Matematik düzeltildi: Target'ın baktığı yönün tersini ekliyoruz.
                // -sin(yaw) = İleri X, cos(yaw) = İleri Z (Genel MC kuralı)
                dstX += Math.sin(rad) * dist; // Ters X yönü
                dstZ -= Math.cos(rad) * dist; // Ters Z yönü
                break;

            case "Circle":
                // Yarıçapı artırdım (2.5)
                this.circleAngle += 0.4D;
                double radius = 2.5D;

                dstX += Math.cos(this.circleAngle) * radius;
                dstZ += Math.sin(this.circleAngle) * radius;
                break;
        }

        // --- VULCAN BYPASS VE GİT-GEL FİX ---
        double myX = getPosX();
        double myY = getPosY();
        double myZ = getPosZ();
        float myYaw = getSelfRotationYaw();

        double distance = Math.sqrt(Math.pow(dstX - myX, 2) + Math.pow(dstY - myY, 2) + Math.pow(dstZ - myZ, 2));

        // Mesafe çok fazlaysa veya çok azsa çalışmayı durdur
        if (distance > 6.0D || distance < 0.2D) return;

        // Packet Spam (Adım adım git)
        double stepSize = 0.25D;
        double packets = Math.ceil(distance / stepSize);

        for (int i = 1; i <= packets; i++) {
            double pct = (double) i / packets;
            double x = myX + (dstX - myX) * pct;
            double y = myY + (dstY - myY) * pct;
            double z = myZ + (dstZ - myZ) * pct;

            // [VULCAN SAFE] 5 Argümanlı paket
            sendPacket(new fx(x, y, z, true, myYaw));
        }

        // Client tarafında güncelle (Görsel olarak oraya geç)
        getThePlayer().d(dstX, dstY, dstZ);
    }

    // Hedefin Yaw değerini çekme (Reflection ile)
    private float getRotationYaw(nn entity) {
        try {
            Field f = entity.getClass().getField("rotationYaw");
            return f.getFloat(entity);
        } catch (Exception e) {
            try {
                // Obfuscated isimler
                Field f2 = entity.getClass().getDeclaredField("y");
                f2.setAccessible(true);
                return f2.getFloat(entity);
            } catch (Exception e2) {
                return 0.0f;
            }
        }
    }

    // Kendi Yaw değerimizi çekme
    private float getSelfRotationYaw() {
        return Entity.getRotationYaw();
    }
}