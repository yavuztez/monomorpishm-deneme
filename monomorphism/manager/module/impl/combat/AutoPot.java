package monomorphism.manager.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.event.EventPacket;
import monomorphism.manager.mapper.Entity;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.time.TimeUtil;
import monomorphism.manager.setting.SettingsManager;
import net.minecraft.BlockPos;
import net.minecraft.aX; // C08PacketPlayerBlockPlacement
import net.minecraft.br; // C03PacketPlayer
import net.minecraft.o6; // Item
import net.minecraft.q_; // ItemStack
import net.minecraft.u0; // C09PacketHeldItemChange

public class AutoPot extends Module {

    private final TimeUtil timer = new TimeUtil();
    private boolean isPotting = false;
    private int potSlot = -1;

    public AutoPot() {
        super("Auto Pot", 0, Category.Combat);
        // Sadece gerekli ayarlar: Can sınırı ve Gecikme
        SettingsManager.manager.addDouble("Health", "potHealth", 1.0D, 20.0D, 14.0D, this);
        SettingsManager.manager.addDouble("Delay", "potDelay", 0.0D, 1000.0D, 500.0D, this);
    }

    @Subscribe
    public void onMotion(EventMotion event) {
        // Oyuncu bir şey kullanıyorsa (yemek/yay) dur
        if (isUsingItem()) return;

        // Önce Hotbar'a bak
        int slot = getPotFromHotbar();

        // Hotbar'da yoksa Envanterden indir (Refill)
        if (slot == -1) {
            getPotFromInventory();
            return;
        }

        // Gecikme kontrolü
        long delay = (long) SettingsManager.manager.getSettingByName("potDelay").getValDouble();
        if (this.timer.hasReached(delay)) {
            this.potSlot = slot;
            this.isPotting = true; // Packet eventini tetikle
            this.timer.reset();
        } else {
            this.isPotting = false;
        }
    }

    @Subscribe
    public void onPacket(EventPacket event) {
        if (!event.send || !this.isPotting || this.potSlot == -1) return;

        // Hareket paketi giderken araya gir
        if (event.packet instanceof br) {
            br packet = (br) event.packet;

            // Vulcan Bypass: Kafayı aşağı çevirmiş gibi göster (Silent)
            setPacketPitch(packet, 90.0f);

            int prevSlot = Client.mc.aQ.b3.f;

            // 1. İksir slotuna geç
            Entity.sendPacket(new u0(this.potSlot));

            // 2. İksiri fırlat
            q_ itemStack = Client.mc.aQ.b3.b(this.potSlot);
            Entity.sendPacket(new aX(new BlockPos(-1, -1, -1), 255, itemStack, 0.0F, 0.0F, 0.0F));

            // 3. Eski slota dön
            Entity.sendPacket(new u0(prevSlot));

            this.isPotting = false;
        }
    }

    private int getPotFromHotbar() {
        for (int i = 0; i < 9; ++i) {
            if (isValidPot(Client.mc.aQ.b3.b(i))) {
                return i;
            }
        }
        return -1;
    }

    private void getPotFromInventory() {
        for (int i = 9; i < 36; ++i) {
            if (isValidPot(Client.mc.aQ.b3.b(i))) {
                // Shift-Click ile envanterden hotbara indir
                Client.mc.Q.a(Client.mc.aQ.bN.e, i, 0, 1, Client.mc.aQ);
                break;
            }
        }
    }

    private boolean isValidPot(q_ stack) {
        if (stack == null) return false;

        o6 item = getItem(stack);
        if (item == null) return false;

        int itemId = getIdFromItem(item);

        // 373 = Potion Item ID
        if (itemId == 373) {
            int meta = stack.l; // Metadata

            // Sadece Patlayıcı (Splash) iksirler
            if (!ItemPotion_isSplash(meta)) return false;

            float myHealth = getPlayerHealth();

            // SADECE CAN POTU KONTROLÜ (Diğerlerini kaldırdık)
            // Eğer canımız belirlediğimiz sınırın altındaysa VE iksir Instant Health ise
            if (myHealth <= SettingsManager.manager.getSettingByName("potHealth").getValDouble()) {
                if (isHealthPotion(meta)) return true;
            }
        }
        return false;
    }

    // --- REFLECTION METOTLARI ---

    private boolean isUsingItem() {
        try {
            Method m = Client.mc.aQ.getClass().getMethod("bQ");
            m.setAccessible(true);
            return (boolean) m.invoke(Client.mc.aQ);
        } catch (Exception e) { return false; }
    }

    private o6 getItem(q_ stack) {
        try {
            Method m = stack.getClass().getMethod("a");
            m.setAccessible(true);
            return (o6) m.invoke(stack);
        } catch (Exception e) { return null; }
    }

    private int getIdFromItem(o6 item) {
        try {
            Method m = o6.class.getMethod("b", o6.class);
            m.setAccessible(true);
            return (int) m.invoke(null, item);
        } catch (Exception e) { return 0; }
    }

    private float getPlayerHealth() {
        try {
            Method m = Client.mc.aQ.getClass().getMethod("bm");
            m.setAccessible(true);
            return (float) m.invoke(Client.mc.aQ);
        } catch (Exception e) { return 20.0f; }
    }

    private void setPacketPitch(br packet, float pitch) {
        try {
            Field f = br.class.getDeclaredField("i");
            f.setAccessible(true);
            f.setFloat(packet, pitch);
        } catch (Exception e) {}
    }

    private boolean ItemPotion_isSplash(int meta) {
        return (meta & 16384) != 0;
    }

    // ID'si 5 ile bitenler Instant Health potlarıdır
    private boolean isHealthPotion(int meta) {
        return (meta & 15) == 5;
    }
}