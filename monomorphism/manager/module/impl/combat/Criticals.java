package monomorphism.manager.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Constructor;
import monomorphism.manager.event.EventPacket;
import monomorphism.manager.mapper.Entity;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import net.minecraft.yg; // Base Packet Class

public class Criticals extends Module {

    // Reflection önbelleği
    private Constructor<?> packetPosConstructor;

    public Criticals() {
        super("Criticals", 0, Category.Combat);
    }

    @Subscribe
    public void onPacket(EventPacket event) {
        // Giden paketleri kontrol et. "Attack" paketini bulmak için basit string kontrolü yapıyoruz.
        // O (UseEntity) genelde Attack paketidir.
        if (event.send && event.packet.getClass().getSimpleName().equals("O")) {
            if (Entity.onGround()) {
                doCrit();
            }
        }
    }

    private void doCrit() {
        double x = Entity.getPosX();
        double y = Entity.getPosY();
        double z = Entity.getPosZ();

        // 1.8.9 Criticals Ofsetleri
        sendPosPacket(x, y + 0.0625D, z, false);
        sendPosPacket(x, y, z, false);
        sendPosPacket(x, y + 1.1E-5D, z, false);
        sendPosPacket(x, y, z, false);
    }

    // Reflection ile C04PacketPlayerPosition oluşturup gönderen metod
    private void sendPosPacket(double x, double y, double z, boolean onGround) {
        try {
            if (packetPosConstructor == null) {
                findPacketConstructor();
            }

            if (packetPosConstructor != null) {
                // Paketi oluştur
                Object packet = packetPosConstructor.newInstance(x, y, z, onGround);
                // Entity.sendPacket metoduna cast ederek gönder
                Entity.sendPacket((yg) packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void findPacketConstructor() {
        try {
            // KillAura'da 'fx' class'ı PacketPlayer olarak kullanılmış.
            // Bunun alt sınıfı (Inner Class) genelde Position paketidir.
            Class<?> packetPlayerClass = Class.forName("net.minecraft.client.fx"); // KillAura'daki import

            for (Class<?> subClass : packetPlayerClass.getDeclaredClasses()) {
                // Constructor parametrelerine bak: (double, double, double, boolean)
                for (Constructor<?> c : subClass.getDeclaredConstructors()) {
                    c.setAccessible(true);
                    if (c.getParameterCount() == 4) {
                        Class<?>[] types = c.getParameterTypes();
                        if (types[0] == double.class && types[3] == boolean.class) {
                            this.packetPosConstructor = c;
                            return;
                        }
                    }
                }
            }

            // Eğer fx içinde bulamazsa standart 'net.minecraft.k' içini tara (Yedek plan)
            try {
                Class<?> kClass = Class.forName("net.minecraft.k");
                for (Class<?> subClass : kClass.getDeclaredClasses()) {
                    for (Constructor<?> c : subClass.getDeclaredConstructors()) {
                        c.setAccessible(true);
                        if (c.getParameterCount() == 4 && c.getParameterTypes()[0] == double.class) {
                            this.packetPosConstructor = c;
                            return;
                        }
                    }
                }
            } catch (Exception ignored) {}

        } catch (Exception e) {
            System.out.println("Criticals Modulu Packet Classini Bulamadi!");
        }
    }
}