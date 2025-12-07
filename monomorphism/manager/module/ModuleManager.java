/*     */ package monomorphism.manager.module;
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import monomorphism.manager.event.EventKey;
/*     */ import monomorphism.manager.event.EventPacket;
/*     */ import monomorphism.manager.module.impl.combat.Criticals;
/*     */ import monomorphism.manager.module.impl.combat.NoKnockback;
/*     */ import monomorphism.manager.module.impl.combat.TargetTeleport;
/*     */ import monomorphism.manager.module.impl.movement.Phase;
/*     */ import monomorphism.manager.module.impl.movement.Speed;
/*     */ import monomorphism.manager.module.impl.movement.Step;
/*     */ import monomorphism.manager.module.impl.movement.TargetStrafe;
/*     */ import monomorphism.manager.module.impl.movement.VClipDown;
/*     */ import monomorphism.manager.module.impl.player.NoFall;
/*     */ import monomorphism.manager.module.impl.player.NoWeb;
/*     */ import monomorphism.manager.module.impl.visual.ShiftGUI;
/*     */ import monomorphism.manager.module.impl.world.ChestStealer;
/*     */ import monomorphism.manager.module.impl.world.PacketSniffer;
/*     */ import monomorphism.manager.module.impl.world.Scaffold;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import monomorphism.manager.module.util.bypass.CokGizli;
/*     */ import net.minecraft.client.dw;
/*     */ 
/*     */ public enum ModuleManager {
/*  32 */   INSTANCE; public List<Module> modules;
/*     */   ModuleManager() {
/*  34 */     this.modules = new ArrayList<>();
/*     */   }
/*     */   @Subscribe
/*     */   public void listenKey(EventKey event) {
/*  38 */     Iterator<Module> var3 = this.modules.iterator();
/*  39 */     while (var3.hasNext()) {
/*  40 */       Module mod = var3.next();
/*  41 */       if (mod.getModuleKey() == event.getKey()) {
/*  42 */         mod.toggle();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Subscribe
/*     */   public void onPacket(EventPacket event) {
/*  50 */     if (!event.send)
/*  51 */       return;  Object packet = event.packet;
/*  52 */     if (packet == null)
/*     */       return; 
/*  54 */     String pName = packet.getClass().getSimpleName();
/*     */ 
/*     */     
/*  57 */     if (pName.contains("fK") || pName.contains("CustomPayload")) {
/*     */       try {
/*  59 */         String channel = (String)getFirstFieldByType(packet, String.class);
/*     */         
/*  61 */         if (channel != null && channel.equalsIgnoreCase("Teyyapclntvars")) {
/*     */ 
/*     */           
/*  64 */           Object packetBufferObj = getFirstFieldByType(packet, ByteBuf.class);
/*  65 */           if (packetBufferObj == null) {
/*     */             try {
/*  67 */               Class<?> hiClass = Class.forName("net.minecraft.hi");
/*  68 */               packetBufferObj = getFirstFieldByType(packet, hiClass);
/*  69 */             } catch (ClassNotFoundException classNotFoundException) {}
/*     */           }
/*     */           
/*  72 */           if (packetBufferObj != null) {
/*     */ 
/*     */ 
/*     */             
/*  76 */             ByteBuf buffer = (ByteBuf)packetBufferObj;
/*     */             
/*  78 */             ByteBuf copy = buffer.copy();
/*  79 */             byte[] bytes = new byte[copy.readableBytes()];
/*  80 */             copy.readBytes(bytes);
/*  81 */             String data = new String(bytes, StandardCharsets.UTF_8);
/*     */ 
/*     */             
/*  84 */             if (data.contains("processList###")) {
/*  85 */               System.out.println("[Monomorphism] Process List temizleniyor (Myth Buffer Swap)...");
/*     */ 
/*     */               
/*  88 */               String mythProcessData = "{\\\"[System Process]\\\":{\\\"idList\\\":[0]},\\\"System\\\":{\\\"idList\\\":[4]},\\\"svchost.exe\\\":{\\\"idList\\\":[6660,4132,2088,4656,1076,584,1116,1636,6244,5236,12932,7304,1164,1676,3216,4752,1684,2196,2200,8344,2212,3748,3756,3252,3764,3256,3780,11972,7896,1768,8424,3820,3316,2292,2820,7452,1840,6476,2392,3932,4444,8032,8040,1900,4464,1396,4980,1916,11140,7052,4000,3492,6056,10664,4012,1456,2480,1464,2488,8120,6588,1472,1484,4556,7120,6624,9184,2532,3044,2024,3564,7148,1524,3572,3580],\\\"windows\\\":[\\\"Default IME\\\",\\\"Windows Push Notifications Platform\\\",\\\"Default IME\\\"]},\\\"fontdrvhost.exe\\\":{\\\"idList\\\":[1032,1040]},\\\"fsnotifier.exe\\\":{\\\"idList\\\":[6672]},\\\"smss.exe\\\":{\\\"idList\\\":[532]},\\\"RuntimeBroker.exe\\\":{\\\"idList\\\":[8732,8832,3744,5376,7972],\\\"windows\\\":[\\\"Default IME\\\",\\\"Default IME\\\",\\\"Default IME\\\"]},\\\"Discord.exe\\\":{\\\"idList\\\":[2092,6280,12952,12576,12076,3540],\\\"windows\\\":[\\\"Temp Window\\\",\\\"Default IME\\\",\\\" - Discord\\\",\\\"MSCTFIME UI\\\",\\\"Default IME\\\",\\\"Default IME\\\"]},\\\"brave.exe\\\":{\\\"idList\\\":[7728,14392,6224,4740,10376,4788,9928,760,14132,6468,5552,8140],\\\"windows\\\":[\\\"ChatGPT - Brave\\\",\\\"MSCTFIME UI\\\",\\\"Default IME\\\",\\\"Default IME\\\",\\\"Default IME\\\"]},\\\"java.exe\\\":{\\\"idList\\\":[2096,13376,7756,5220,15728,1980]},\\\"NVDisplay.Container.exe\\\":{\\\"idList\\\":[2100,2780],\\\"windows\\\":[\\\"NvSvc\\\",\\\"UxdService\\\",\\\"NvContainerWindowClass00000ADC\\\"]},\\\"WmiPrvSE.exe\\\":{\\\"idList\\\":[7732]},\\\"conhost.exe\\\":{\\\"idList\\\":[2108,9804,6792,13976,8360,7448,10592,14176],\\\"windows\\\":[\\\"MSCTFIME UI\\\",\\\"Default IME\\\"]},\\\"winlogon.exe\\\":{\\\"idList\\\":[76]},\\\"javaw.exe\\\":{\\\"idList\\\":[14428,4792,16200],\\\"windows\\\":[\\\"Minecraft 1.8.9 - SonOyuncu Client\\\",\\\"__wglDummyWindowFodder\\\",\\\"NVOGLDC invisible\\\",\\\"D3DFocusWindow\\\",\\\"theAwtToolkitWindow\\\",\\\"MSCTFIME UI\\\",\\\"Default IME\\\",\\\"Default IME\\\",\\\"Default IME\\\",\\\"MSCTFIME UI\\\",\\\"Default IME\\\",\\\"MSCTFIME UI\\\",\\\"Default IME\\\", \\\"__wglDummyWindowFodder\\\",\\\"NVOGLDC invisible\\\",\\\"TrayMessageWindow\\\",\\\"PopupMessageWindow\\\",\\\"D3DFocusWindow\\\",\\\"theAwtToolkitWindow\\\",\\\"MSCTFIME UI\\\",\\\"Default IME\\\",\\\"Default IME\\\",\\\"Default IME\\\",\\\"MSCTFIME UI\\\",\\\"Default IME\\\"]},\\\"explorer.exe\\\":{\\\"idList\\\":[5788],\\\"windows\\\":[\\\"Battery Meter\\\",\\\"Network Flyout\\\",\\\"Window\\\",\\\"GDI+ Window (Explorer.EXE)\\\",\\\"Program Manager\\\"]},\\\"Spotify.exe\\\":{\\\"idList\\\":[1140,10428,12536,1876,1936,10220],\\\"windows\\\":[\\\"Default IME\\\",\\\"Spotify Free\\\",\\\"GDI+ Window (Spotify.exe)\\\"]}}";
/*  89 */               String cleanProcessList = "{\"processList\": \"" + mythProcessData + "\"}";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/*  96 */               ByteBuf rawData = Unpooled.buffer();
/*  97 */               rawData.writeBytes(cleanProcessList.getBytes(StandardCharsets.UTF_8));
/*     */ 
/*     */               
/* 100 */               Class<?> hiClass = Class.forName("net.minecraft.hi");
/*     */ 
/*     */               
/* 103 */               Constructor<?> hiConstructor = hiClass.getConstructor(new Class[] { ByteBuf.class });
/* 104 */               Object newPacketBuffer = hiConstructor.newInstance(new Object[] { rawData });
/*     */ 
/*     */               
/* 107 */               setFirstFieldByType(packet, hiClass, newPacketBuffer);
/*     */             
/*     */             }
/* 110 */             else if (data.contains("systemInfo###")) {
/* 111 */               System.out.println("[Monomorphism] Sistem bilgisi temizleniyor...");
/* 112 */               String cleanSysData = data.substring(data.indexOf("systemInfo###")).replace(", Amazon.com Inc.", "");
/*     */ 
/*     */               
/* 115 */               ByteBuf rawData = Unpooled.buffer();
/* 116 */               rawData.writeBytes(cleanSysData.getBytes(StandardCharsets.UTF_8));
/*     */               
/* 118 */               Class<?> hiClass = Class.forName("net.minecraft.hi");
/* 119 */               Constructor<?> hiConstructor = hiClass.getConstructor(new Class[] { ByteBuf.class });
/* 120 */               Object newPacketBuffer = hiConstructor.newInstance(new Object[] { rawData });
/*     */               
/* 122 */               setFirstFieldByType(packet, hiClass, newPacketBuffer);
/*     */             } 
/*     */           } 
/*     */         } 
/* 126 */       } catch (Exception ex) {
/* 127 */         ex.printStackTrace();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 132 */     if (pName.contains("or") || pName.contains("ConfirmTransaction")) {
/*     */       try {
/* 134 */         String oldValue = (String)getFirstFieldByType(packet, String.class);
/* 135 */         if (oldValue != null) {
/* 136 */           String newValue = CokGizli.yuh();
/* 137 */           setFirstFieldByType(packet, String.class, oldValue + "###" + newValue);
/*     */         } 
/* 139 */       } catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */   
/*     */   private Object getFirstFieldByType(Object instance, Class<?> type) throws IllegalAccessException {
/* 144 */     Class<?> clazz = instance.getClass();
/* 145 */     while (clazz != null) {
/* 146 */       for (Field field : clazz.getDeclaredFields()) {
/* 147 */         if (type.isAssignableFrom(field.getType())) {
/* 148 */           field.setAccessible(true);
/* 149 */           return field.get(instance);
/*     */         } 
/*     */       } 
/* 152 */       clazz = clazz.getSuperclass();
/*     */     } 
/* 154 */     return null;
/*     */   }
/*     */   
/*     */   private void setFirstFieldByType(Object instance, Class<?> type, Object value) throws IllegalAccessException {
/* 158 */     Class<?> clazz = instance.getClass();
/* 159 */     while (clazz != null) {
/* 160 */       for (Field field : clazz.getDeclaredFields()) {
/* 161 */         if (type.isAssignableFrom(field.getType())) {
/* 162 */           field.setAccessible(true);
/* 163 */           field.set(instance, value);
/*     */           return;
/*     */         } 
/*     */       } 
/* 167 */       clazz = clazz.getSuperclass();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 172 */     StartSpoofer.start();
/*     */     
/* 174 */     Client.bus.register(this);
/*     */     try {
/* 176 */       Field field = dw.class.getDeclaredField("w");
/* 177 */       field.setAccessible(true);
/* 178 */       Client.mc = (dw)field.get(null);
/* 179 */     } catch (Exception exception) {}
/*     */     
/* 181 */     this.modules.add(new Sprint());
/* 182 */     this.modules.add(new Step());
/* 183 */     this.modules.add(new KillAura());
/* 184 */     this.modules.add(new TargetTeleport());
/* 185 */     this.modules.add(new Speed());
/* 186 */     this.modules.add(new Manager());
/* 187 */     this.modules.add(new AutoArmor());
/* 188 */     this.modules.add(new Scaffold());
/* 189 */     this.modules.add(new NoKnockback());
/* 190 */     this.modules.add(new ShiftGUI());
/* 191 */     this.modules.add(new NoFall());
/* 192 */     this.modules.add(new Flight());
/* 193 */     this.modules.add(new Speedmine());
/* 194 */     this.modules.add(new ChestStealer());
/* 195 */     this.modules.add(new VClipUp());
/* 196 */     this.modules.add(new VClipDown());
/* 197 */     this.modules.add(new Phase());
/* 198 */     this.modules.add(new NoWeb());
/* 199 */     this.modules.add(new Strafe());
/* 200 */     this.modules.add(new TitanyumManager());
/* 201 */     this.modules.add(new TargetStrafe());
/* 202 */     this.modules.add(new PacketSniffer());
/* 203 */     this.modules.add(new Criticals());
/* 204 */     this.modules.add(new AutoPot());
/*     */   }
/*     */   
/*     */   public Module getModule(Class<?> clazz) {
/* 208 */     Iterator<Module> var2 = getModules().iterator();
/* 209 */     while (var2.hasNext()) {
/* 210 */       Module mod = var2.next();
/* 211 */       if (mod.getClass() == clazz) {
/* 212 */         return mod;
/*     */       }
/*     */     } 
/* 215 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isToggled(Class<?> clazz) {
/* 219 */     Iterator<Module> var2 = getModules().iterator();
/*     */     
/*     */     while (true) {
/* 222 */       if (!var2.hasNext()) {
/* 223 */         return false;
/*     */       }
/* 225 */       Module mod = var2.next();
/* 226 */       if (mod.getClass() == clazz && mod.isEnabled())
/* 227 */         return true; 
/*     */     } 
/*     */   }
/*     */   public List<Module> getModules() {
/* 231 */     return this.modules;
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\ModuleManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */