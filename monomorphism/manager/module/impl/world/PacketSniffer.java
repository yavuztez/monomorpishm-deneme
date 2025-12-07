/*     */ package monomorphism.manager.module.impl.world;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import monomorphism.manager.event.EventPacket;
/*     */ import monomorphism.manager.module.Category;
/*     */ import monomorphism.manager.module.Module;
/*     */ 
/*     */ public class PacketSniffer
/*     */   extends Module
/*     */ {
/*     */   public PacketSniffer() {
/*  15 */     super("Ultra Sniffer", 0, Category.World);
/*  16 */     setModuleState(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onEnable() {
/*  21 */     System.out.println(">> ULTRA SNIFFER AKTIF: Tum paketler izleniyor...");
/*  22 */     super.onEnable();
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onPacket(EventPacket event) {
/*  27 */     Object packet = event.packet;
/*  28 */     if (packet == null) {
/*     */       return;
/*     */     }
/*  31 */     String yon = event.send ? "[Giden -> Server]" : "[Gelen <- Server]";
/*  32 */     String paketIsmi = packet.getClass().getSimpleName();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     boolean isCustomPayload = (paketIsmi.contains("Custom") || paketIsmi.contains("Payload") || paketIsmi.equals("fK") || paketIsmi.equals("FA"));
/*     */     
/*  40 */     if (isCustomPayload) {
/*  41 */       analyzeCustomPayload(packet, yon, paketIsmi);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     if (paketIsmi.contains("Disconnect") || paketIsmi.contains("Kick")) {
/*  50 */       System.out.println("!!! " + yon + " KICK/DISCONNECT PAKETI YAKALANDI: " + paketIsmi);
/*  51 */       dumpPacketFields(packet);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void analyzeCustomPayload(Object packet, String yon, String paketIsmi) {
/*     */     try {
/*  58 */       System.out.println("========================================");
/*  59 */       System.out.println(yon + " CUSTOM PAYLOAD: " + paketIsmi);
/*     */ 
/*     */       
/*  62 */       String channel = (String)getFirstFieldByType(packet, String.class);
/*  63 */       if (channel == null) channel = "BULUNAMADI"; 
/*  64 */       System.out.println("   -> Kanal: " + channel);
/*     */ 
/*     */       
/*  67 */       Object bufferObj = getFirstFieldByType(packet, ByteBuf.class);
/*     */ 
/*     */       
/*  70 */       if (bufferObj == null) {
/*     */         try {
/*  72 */           Class<?> hiClass = Class.forName("net.minecraft.hi");
/*  73 */           bufferObj = getFirstFieldByType(packet, hiClass);
/*  74 */         } catch (ClassNotFoundException classNotFoundException) {}
/*     */       }
/*     */       
/*  77 */       if (bufferObj != null && bufferObj instanceof ByteBuf) {
/*  78 */         ByteBuf buffer = (ByteBuf)bufferObj;
/*     */ 
/*     */         
/*  81 */         ByteBuf copy = buffer.copy();
/*  82 */         int size = copy.readableBytes();
/*  83 */         byte[] bytes = new byte[size];
/*  84 */         copy.readBytes(bytes);
/*     */ 
/*     */         
/*  87 */         String content = new String(bytes, StandardCharsets.UTF_8);
/*     */         
/*  89 */         String cleanContent = content.replaceAll("[^\\x20-\\x7E]", ".");
/*     */         
/*  91 */         System.out.println("   -> Boyut: " + size + " byte");
/*  92 */         System.out.println("   -> İçerik (String): " + cleanContent);
/*     */ 
/*     */         
/*  95 */         if (size == 0) {
/*  96 */           System.out.println("   -> [UYARI] BU PAKET BOS! (Sorun burada olabilir)");
/*     */         }
/*     */       } else {
/*  99 */         System.out.println("   -> [HATA] Veri Buffer'ı (Data) bulunamadı!");
/*     */       } 
/* 101 */       System.out.println("========================================");
/*     */     }
/* 103 */     catch (Exception e) {
/* 104 */       System.out.println("   -> Analiz Hatası: " + e.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void dumpPacketFields(Object packet) {
/*     */     try {
/* 111 */       for (Field field : packet.getClass().getDeclaredFields()) {
/* 112 */         field.setAccessible(true);
/* 113 */         System.out.println("   - " + field.getName() + " (" + field.getType().getSimpleName() + "): " + field.get(packet));
/*     */       } 
/* 115 */     } catch (Exception e) {
/* 116 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Object getFirstFieldByType(Object instance, Class<?> type) throws IllegalAccessException {
/* 123 */     Class<?> currentClass = instance.getClass();
/* 124 */     while (currentClass != null) {
/* 125 */       for (Field field : currentClass.getDeclaredFields()) {
/* 126 */         if (type.isAssignableFrom(field.getType())) {
/* 127 */           field.setAccessible(true);
/* 128 */           return field.get(instance);
/*     */         } 
/*     */       } 
/* 131 */       currentClass = currentClass.getSuperclass();
/*     */     } 
/* 133 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\world\PacketSniffer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */