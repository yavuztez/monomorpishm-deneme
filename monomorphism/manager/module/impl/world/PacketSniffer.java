package monomorphism.manager.module.impl.world;

import com.google.common.eventbus.Subscribe;
import io.netty.buffer.ByteBuf;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import monomorphism.manager.event.EventPacket;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;

public class PacketSniffer extends Module {

    public PacketSniffer() {
        super("Ultra Sniffer", 0, Category.World);
        this.setModuleState(true); // Otomatik açık başlar
    }

    @Override
    public void onEnable() {
        System.out.println(">> ULTRA SNIFFER AKTIF: Tum paketler izleniyor...");
        super.onEnable();
    }

    @Subscribe
    public void onPacket(EventPacket event) {
        Object packet = event.packet;
        if (packet == null) return;

        // Paket Yönü: Send=True (Bizden Giden), Send=False (Bize Gelen)
        String yon = event.send ? "[Giden -> Server]" : "[Gelen <- Server]";
        String paketIsmi = packet.getClass().getSimpleName(); // Örn: fK, S3F, PacketPlayIn...

        // Spam olmaması için sadece önemli olabilecek paketleri detaylı inceleyelim
        // "Custom" veya "Payload" geçen paketler (İsim değişse bile yakalar)
        // Veya fK (Client Custom Payload) ve S3F (Server Custom Payload) gibi kısa isimler
        boolean isCustomPayload = paketIsmi.contains("Custom") || paketIsmi.contains("Payload")
                || paketIsmi.equals("fK") || paketIsmi.equals("FA");

        if (isCustomPayload) {
            analyzeCustomPayload(packet, yon, paketIsmi);
        } else {
            // Diğer tüm paketlerin sadece ismini yazdır (Spam olmasın diye)
            // Eğer her şeyi görmek istersen burayı açabilirsin:
            // System.out.println(yon + " " + paketIsmi);
        }

        // Özel: Eğer sunucu bizi atıyorsa "Kick" veya "Disconnect" paketini yakala
        if (paketIsmi.contains("Disconnect") || paketIsmi.contains("Kick")) {
            System.out.println("!!! " + yon + " KICK/DISCONNECT PAKETI YAKALANDI: " + paketIsmi);
            dumpPacketFields(packet);
        }
    }

    // CustomPayload Paketini Analiz Eden Metot
    private void analyzeCustomPayload(Object packet, String yon, String paketIsmi) {
        try {
            System.out.println("========================================");
            System.out.println(yon + " CUSTOM PAYLOAD: " + paketIsmi);

            // 1. Kanal İsmini Bul (Paketin içindeki ilk String genelde kanal ismidir)
            String channel = (String) getFirstFieldByType(packet, String.class);
            if (channel == null) channel = "BULUNAMADI";
            System.out.println("   -> Kanal: " + channel);

            // 2. Veriyi Bul (ByteBuf veya PacketBuffer)
            Object bufferObj = getFirstFieldByType(packet, ByteBuf.class);

            // Eğer direkt ByteBuf yoksa PacketBuffer (hi) olabilir, reflection ile bulalım
            if (bufferObj == null) {
                try {
                    Class<?> hiClass = Class.forName("net.minecraft.hi");
                    bufferObj = getFirstFieldByType(packet, hiClass);
                } catch (ClassNotFoundException ignored) {}
            }

            if (bufferObj != null && bufferObj instanceof ByteBuf) {
                ByteBuf buffer = (ByteBuf) bufferObj;

                // Buffer'ı okumak için kopyala (Orijinali bozma)
                ByteBuf copy = buffer.copy();
                int size = copy.readableBytes();
                byte[] bytes = new byte[size];
                copy.readBytes(bytes);

                // String olarak oku
                String content = new String(bytes, StandardCharsets.UTF_8);
                // Okunabilir karakterleri temizle (Gereksiz sembolleri sil)
                String cleanContent = content.replaceAll("[^\\x20-\\x7E]", ".");

                System.out.println("   -> Boyut: " + size + " byte");
                System.out.println("   -> İçerik (String): " + cleanContent);

                // Eğer veri boşsa uyar
                if (size == 0) {
                    System.out.println("   -> [UYARI] BU PAKET BOS! (Sorun burada olabilir)");
                }
            } else {
                System.out.println("   -> [HATA] Veri Buffer'ı (Data) bulunamadı!");
            }
            System.out.println("========================================");

        } catch (Exception e) {
            System.out.println("   -> Analiz Hatası: " + e.getMessage());
        }
    }

    // Paketin içindeki tüm değişkenleri döker (Debug için)
    private void dumpPacketFields(Object packet) {
        try {
            for (Field field : packet.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println("   - " + field.getName() + " (" + field.getType().getSimpleName() + "): " + field.get(packet));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Reflection Yardımcısı: Tipe göre ilk değişkeni bulur
    private Object getFirstFieldByType(Object instance, Class<?> type) throws IllegalAccessException {
        // Üst sınıfları da tara (Inheritance varsa)
        Class<?> currentClass = instance.getClass();
        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (type.isAssignableFrom(field.getType())) {
                    field.setAccessible(true);
                    return field.get(instance);
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return null;
    }
}