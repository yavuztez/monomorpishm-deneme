/*     */ package monomorphism.manager.module.impl.combat;
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import monomorphism.manager.event.EventMotion;
/*     */ import monomorphism.manager.event.EventPacket;
/*     */ import monomorphism.manager.mapper.Entity;
/*     */ import monomorphism.manager.module.Category;
/*     */ import monomorphism.manager.module.Module;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import monomorphism.manager.module.util.time.TimeUtil;
/*     */ import monomorphism.manager.setting.SettingsManager;
/*     */ import net.minecraft.BlockPos;
/*     */ import net.minecraft.aX;
/*     */ import net.minecraft.br;
/*     */ import net.minecraft.nn;
/*     */ import net.minecraft.o6;
/*     */ import net.minecraft.q_;
/*     */ import net.minecraft.u0;
/*     */ import net.minecraft.yg;
/*     */ 
/*     */ public class AutoPot extends Module {
/*  23 */   private final TimeUtil timer = new TimeUtil();
/*     */   private boolean isPotting = false;
/*  25 */   private int potSlot = -1;
/*     */   
/*     */   public AutoPot() {
/*  28 */     super("Auto Pot", 0, Category.Combat);
/*     */     
/*  30 */     SettingsManager.manager.addDouble("Health", "potHealth", 1.0D, 20.0D, 14.0D, this);
/*     */ 
/*     */ 
/*     */     
/*  34 */     SettingsManager.manager.addDouble("Delay", "potDelay", 0.0D, 5000.0D, 2000.0D, this);
/*     */   }
/*     */ 
/*     */   
/*     */   @Subscribe
/*     */   public void onMotion(EventMotion event) {
/*  40 */     if (isUsingItem() || isScreenOpen()) {
/*  41 */       this.isPotting = false;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  46 */     float myHealth = getPlayerHealth();
/*  47 */     double healthSetting = SettingsManager.manager.getSettingByName("potHealth").getValDouble();
/*     */ 
/*     */     
/*  50 */     if (myHealth > healthSetting) {
/*  51 */       this.isPotting = false;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  56 */     long delay = (long)SettingsManager.manager.getSettingByName("potDelay").getValDouble();
/*  57 */     if (!this.timer.hasReached(delay)) {
/*  58 */       this.isPotting = false;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  63 */     int slot = getPotFromHotbar();
/*     */ 
/*     */     
/*  66 */     if (slot == -1) {
/*  67 */       getPotFromInventory();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  72 */     this.potSlot = slot;
/*  73 */     this.isPotting = true;
/*  74 */     this.timer.reset();
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onPacket(EventPacket event) {
/*  79 */     if (!event.send || !this.isPotting || this.potSlot == -1)
/*     */       return; 
/*  81 */     if (event.packet instanceof br) {
/*  82 */       br packet = (br)event.packet;
/*     */ 
/*     */       
/*  85 */       setPacketPitch(packet, 90.0F);
/*     */       
/*  87 */       int prevSlot = Client.mc.aQ.b3.f;
/*     */ 
/*     */       
/*  90 */       Entity.sendPacket((yg)new u0(this.potSlot));
/*     */ 
/*     */       
/*  93 */       q_ itemStack = Client.mc.aQ.b3.b(this.potSlot);
/*  94 */       Entity.sendPacket((yg)new aX(new BlockPos(-1, -1, -1), 255, itemStack, 0.0F, 0.0F, 0.0F));
/*     */ 
/*     */       
/*  97 */       Entity.sendPacket((yg)new u0(prevSlot));
/*     */       
/*  99 */       this.isPotting = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getPotFromHotbar() {
/* 104 */     for (int i = 0; i < 9; i++) {
/* 105 */       if (isValidPot(Client.mc.aQ.b3.b(i))) {
/* 106 */         return i;
/*     */       }
/*     */     } 
/* 109 */     return -1;
/*     */   }
/*     */   
/*     */   private void getPotFromInventory() {
/* 113 */     for (int i = 9; i < 36; i++) {
/* 114 */       if (isValidPot(Client.mc.aQ.b3.b(i))) {
/* 115 */         Client.mc.Q.a(Client.mc.aQ.bN.e, i, 0, 1, (nn)Client.mc.aQ);
/* 116 */         this.timer.reset();
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isValidPot(q_ stack) {
/* 123 */     if (stack == null) return false;
/*     */     
/* 125 */     o6 item = getItem(stack);
/* 126 */     if (item == null) return false;
/*     */     
/* 128 */     int itemId = getIdFromItem(item);
/*     */     
/* 130 */     if (itemId == 373) {
/* 131 */       int meta = stack.l;
/* 132 */       if (!ItemPotion_isSplash(meta)) return false;
/*     */       
/* 134 */       if (isHealthPotion(meta)) {
/* 135 */         return true;
/*     */       }
/*     */     } 
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isScreenOpen() {
/*     */     try {
/* 145 */       Field f = Client.mc.getClass().getDeclaredField("aR");
/* 146 */       f.setAccessible(true);
/* 147 */       Object screen = f.get(Client.mc);
/* 148 */       return (screen != null);
/* 149 */     } catch (Exception e) {
/* 150 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isUsingItem() {
/*     */     
/* 156 */     try { Method m = Client.mc.aQ.getClass().getMethod("bQ", new Class[0]);
/* 157 */       m.setAccessible(true);
/* 158 */       return ((Boolean)m.invoke(Client.mc.aQ, new Object[0])).booleanValue(); }
/* 159 */     catch (Exception e) { return false; }
/*     */   
/*     */   }
/*     */   private o6 getItem(q_ stack) {
/*     */     
/* 164 */     try { Method m = stack.getClass().getMethod("a", new Class[0]);
/* 165 */       m.setAccessible(true);
/* 166 */       return (o6)m.invoke(stack, new Object[0]); }
/* 167 */     catch (Exception e) { return null; }
/*     */   
/*     */   }
/*     */   private int getIdFromItem(o6 item) {
/*     */     
/* 172 */     try { Method m = o6.class.getMethod("b", new Class[] { o6.class });
/* 173 */       m.setAccessible(true);
/* 174 */       return ((Integer)m.invoke(null, new Object[] { item })).intValue(); }
/* 175 */     catch (Exception e) { return 0; }
/*     */   
/*     */   }
/*     */   private float getPlayerHealth() {
/*     */     
/* 180 */     try { Method m = Client.mc.aQ.getClass().getMethod("bm", new Class[0]);
/* 181 */       m.setAccessible(true);
/* 182 */       return ((Float)m.invoke(Client.mc.aQ, new Object[0])).floatValue(); }
/* 183 */     catch (Exception e) { return 20.0F; }
/*     */   
/*     */   }
/*     */   private void setPacketPitch(br packet, float pitch) {
/*     */     try {
/* 188 */       Field f = br.class.getDeclaredField("i");
/* 189 */       f.setAccessible(true);
/* 190 */       f.setFloat(packet, pitch);
/* 191 */     } catch (Exception exception) {}
/*     */   }
/*     */   
/*     */   private boolean ItemPotion_isSplash(int meta) {
/* 195 */     return ((meta & 0x4000) != 0);
/*     */   }
/*     */   
/*     */   private boolean isHealthPotion(int meta) {
/* 199 */     return ((meta & 0xF) == 5);
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\combat\AutoPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */