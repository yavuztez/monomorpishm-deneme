package monomorphism.manager.module;

import com.google.common.eventbus.Subscribe;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Gerekli Importlar
import monomorphism.manager.event.EventKey;
import monomorphism.manager.event.EventPacket;
import monomorphism.manager.module.impl.combat.*;
import monomorphism.manager.module.util.Client;
import monomorphism.manager.module.util.bypass.CokGizli;
import net.minecraft.client.dw;

// Modül Importları
import monomorphism.manager.module.impl.movement.Flight;
import monomorphism.manager.module.impl.movement.Phase;
import monomorphism.manager.module.impl.movement.Speed;
import monomorphism.manager.module.impl.movement.Step;
import monomorphism.manager.module.impl.movement.Strafe;
import monomorphism.manager.module.impl.movement.TargetStrafe;
import monomorphism.manager.module.impl.movement.VClipDown;
import monomorphism.manager.module.impl.movement.VClipUp;
import monomorphism.manager.module.impl.player.Manager;
import monomorphism.manager.module.impl.player.NoFall;
import monomorphism.manager.module.impl.player.NoWeb;
import monomorphism.manager.module.impl.player.Speedmine;
import monomorphism.manager.module.impl.player.Sprint;
import monomorphism.manager.module.impl.player.TitanyumManager;
import monomorphism.manager.module.impl.visual.ShiftGUI;
import monomorphism.manager.module.impl.world.ChestStealer;
import monomorphism.manager.module.impl.world.Scaffold;
import monomorphism.manager.module.impl.world.PacketSniffer;
import monomorphism.manager.module.impl.combat.Criticals;
import monomorphism.manager.module.impl.combat.AutoPot;

public enum ModuleManager {
    INSTANCE;

    public List<Module> modules = new ArrayList();

    @Subscribe
    public void listenKey(EventKey event) {
        Iterator<Module> var3 = this.modules.iterator();
        while(var3.hasNext()) {
            Module mod = var3.next();
            if (mod.getModuleKey() == event.getKey()) {
                mod.toggle();
            }
        }
    }

    // --- CRASH VERMEYEN GÜVENLİ BYPASS KODU ---
    @Subscribe
    public void onPacket(EventPacket event) {
        if (!event.send) return;
        Object packet = event.packet;
        if (packet == null) return;

        String pName = packet.getClass().getSimpleName();

        // 1. CustomPayload Bypass (Sunucu Bilgi İsteği - fK)
        if (pName.contains("fK") || pName.contains("CustomPayload")) {
            try {
                // Kanal ismini bul
                String channel = (String) getFirstFieldByType(packet, String.class);

                if (channel != null && channel.equalsIgnoreCase("Teyyapclntvars")) {

                    // Paketin içindeki MEVCUT Buffer nesnesini al
                    Object packetBufferObj = getFirstFieldByType(packet, ByteBuf.class);

                    // Eğer direkt ByteBuf olarak bulamazsa net.minecraft.hi (PacketBuffer) olabilir
                    if (packetBufferObj == null) {
                        try {
                            Class<?> hiClass = Class.forName("net.minecraft.hi");
                            packetBufferObj = getFirstFieldByType(packet, hiClass);
                        } catch (ClassNotFoundException ignored) {}
                    }

                    // Buffer bulunduysa işle
                    if (packetBufferObj != null && packetBufferObj instanceof ByteBuf) {
                        ByteBuf buffer = (ByteBuf) packetBufferObj;

                        // İçeriği okumak için kopyasını al (Orijinalin okuma index'ini bozmamak için)
                        ByteBuf copy = buffer.copy();
                        byte[] bytes = new byte[copy.readableBytes()];
                        copy.readBytes(bytes);
                        String data = new String(bytes, StandardCharsets.UTF_8);

                        // Process Listesi İsteği (Görev Yöneticisi)
                        if (data.contains("processList###")) {
                            System.out.println("[Monomorphism] Process check temizleniyor (Buffer Reset)...");

                            // Myth'den alınan temiz veri
                            String cleanProcessList = "{\"processList\": \"{\\\"[System Process]\\\":{\\\"idList\\\":[0]},\\\"System\\\":{\\\"idList\\\":[4]},\\\"java.exe\\\":{\\\"idList\\\":[2096]},\\\"explorer.exe\\\":{\\\"idList\\\":[5788]},\\\"Discord.exe\\\":{\\\"idList\\\":[2092]},\\\"Spotify.exe\\\":{\\\"idList\\\":[1140]}}\"}";

                            // ÖNEMLİ KISIM: Mevcut buffer'ı silip içine temiz veriyi yazıyoruz.
                            // Yeni bir nesne oluşturmadığımız için Constructor hatası ALMAZSIN.
                            buffer.clear();
                            buffer.writeBytes(cleanProcessList.getBytes(StandardCharsets.UTF_8));

                        }
                        // Sistem Bilgisi İsteği (HWID)
                        else if (data.contains("systemInfo###")) {
                            System.out.println("[Monomorphism] Sistem bilgisi temizleniyor (Buffer Reset)...");

                            String cleanSysData = data.substring(data.indexOf("systemInfo###")).replace(", Amazon.com Inc.", "");

                            // Buffer'ı temizle ve düzeltilmiş veriyi yaz
                            buffer.clear();
                            buffer.writeBytes(cleanSysData.getBytes(StandardCharsets.UTF_8));
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // 2. ConfirmTransaction Bypass (Ping/Lag Check - or)
        if (pName.contains("or") || pName.contains("ConfirmTransaction")) {
            try {
                String oldValue = (String) getFirstFieldByType(packet, String.class);
                // Eğer paket bir doğrulama kodu içeriyorsa
                if (oldValue != null) {
                    String newValue = CokGizli.yuh(); // İmzayı oluştur
                    // Eski verinin sonuna imzamızı ekleyip paketi güncelliyoruz
                    setFirstFieldByType(packet, String.class, oldValue + "###" + newValue);
                }
            } catch (Exception ignored) {}
        }
    }

    // --- Reflection Yardımcıları ---
    // (Bu metotlar, obfuscation olsa bile doğru field'ı otomatik bulur)

    private Object getFirstFieldByType(Object instance, Class<?> type) throws IllegalAccessException {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (type.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                return field.get(instance);
            }
        }
        return null;
    }

    private void setFirstFieldByType(Object instance, Class<?> type, Object value) throws IllegalAccessException {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (type.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                field.set(instance, value);
                return;
            }
        }
    }
    // --- BYPASS SONU ---

    public void initialize() {
        Client.bus.register(this);
        try {
            Field field = dw.class.getDeclaredField("w");
            field.setAccessible(true);
            Client.mc = (dw)field.get((Object)null);
        } catch (Exception var2) {}

        this.modules.add(new Sprint());
        this.modules.add(new Step());
        this.modules.add(new KillAura());
        this.modules.add(new TargetTeleport());
        this.modules.add(new Speed());
        this.modules.add(new Manager());
        this.modules.add(new AutoArmor());
        this.modules.add(new Scaffold());
        this.modules.add(new NoKnockback());
        this.modules.add(new ShiftGUI());
        this.modules.add(new NoFall());
        this.modules.add(new Flight());
        this.modules.add(new Speedmine());
        this.modules.add(new ChestStealer());
        this.modules.add(new VClipUp());
        this.modules.add(new VClipDown());
        this.modules.add(new Phase());
        this.modules.add(new NoWeb());
        this.modules.add(new Strafe());
        this.modules.add(new TitanyumManager());
        this.modules.add(new TargetStrafe());
        this.modules.add(new PacketSniffer());
        this.modules.add(new Criticals());
        this.modules.add(new AutoPot());
    }

    public Module getModule(Class<?> clazz) {
        Iterator<Module> var2 = this.getModules().iterator();
        while(var2.hasNext()) {
            Module mod = var2.next();
            if (mod.getClass() == clazz) {
                return mod;
            }
        }
        return null;
    }

    public boolean isToggled(Class<?> clazz) {
        Iterator<Module> var2 = this.getModules().iterator();
        Module mod;
        do {
            if (!var2.hasNext()) {
                return false;
            }
            mod = var2.next();
        } while(mod.getClass() != clazz || !mod.isEnabled());
        return true;
    }

    public List<Module> getModules() {
        return this.modules;
    }
}