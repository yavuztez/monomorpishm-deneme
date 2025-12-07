/*     */ package monomorphism.manager.module.impl.visual;
/*     */ 
/*     */ import com.google.common.eventbus.Subscribe;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import monomorphism.manager.event.EventRender3D;
/*     */ import monomorphism.manager.module.Category;
/*     */ import monomorphism.manager.module.Module;
/*     */ import monomorphism.manager.module.util.Client;
/*     */ import net.minecraft.client.dw;
/*     */ import net.minecraft.nn;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ public class Tracers
/*     */   extends Module
/*     */ {
/*     */   public Tracers() {
/*  18 */     super("Tracers", 0, Category.Visual);
/*     */   }
/*     */   
/*     */   @Subscribe
/*     */   public void onRender3D(EventRender3D event) {
/*  23 */     dw mc = Client.mc;
/*  24 */     if (mc.I == null || mc.aQ == null) {
/*     */       return;
/*     */     }
/*  27 */     GL11.glPushMatrix();
/*  28 */     GL11.glEnable(2848);
/*  29 */     GL11.glDisable(2929);
/*  30 */     GL11.glDisable(3553);
/*  31 */     GL11.glEnable(3042);
/*  32 */     GL11.glBlendFunc(770, 771);
/*  33 */     GL11.glLineWidth(1.5F);
/*     */     
/*  35 */     double rX = 0.0D, rY = 0.0D, rZ = 0.0D;
/*     */     
/*     */     try {
/*  38 */       Object rm = getFieldValue(mc, "bI");
/*  39 */       rX = ((Double)getFieldValue(rm, "u")).doubleValue();
/*  40 */       rY = ((Double)getFieldValue(rm, "m")).doubleValue();
/*  41 */       rZ = ((Double)getFieldValue(rm, "r")).doubleValue();
/*  42 */     } catch (Exception e) {
/*  43 */       GL11.glPopMatrix();
/*     */       
/*     */       return;
/*     */     } 
/*  47 */     GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
/*     */     
/*  49 */     for (Object obj : mc.I.x) {
/*  50 */       if (obj instanceof nn && obj != mc.aQ) {
/*  51 */         nn entity = (nn)obj;
/*     */         
/*  53 */         if (entity.aj)
/*     */           continue; 
/*  55 */         double x = entity.ai + (entity.v - entity.ai) * event.getTicks() - rX;
/*  56 */         double y = entity.a + (entity.ao - entity.a) * event.getTicks() - rY;
/*  57 */         double z = entity.ah + (entity.r - entity.ah) * event.getTicks() - rZ;
/*     */ 
/*     */         
/*  60 */         float eyeHeight = getEyeHeight(mc.aQ);
/*     */         
/*  62 */         GL11.glBegin(1);
/*     */         
/*  64 */         GL11.glVertex3d(0.0D, eyeHeight, 0.0D);
/*     */         
/*  66 */         GL11.glVertex3d(x, y + 1.0D, z);
/*  67 */         GL11.glEnd();
/*     */       } 
/*     */     } 
/*     */     
/*  71 */     GL11.glDisable(3042);
/*  72 */     GL11.glEnable(3553);
/*  73 */     GL11.glEnable(2929);
/*  74 */     GL11.glDisable(2848);
/*  75 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object getFieldValue(Object instance, String fieldName) throws Exception {
/*  83 */     Class<?> clazz = instance.getClass();
/*  84 */     while (clazz != null) {
/*     */       try {
/*  86 */         Field f = clazz.getDeclaredField(fieldName);
/*  87 */         f.setAccessible(true);
/*  88 */         return f.get(instance);
/*  89 */       } catch (NoSuchFieldException e) {
/*  90 */         clazz = clazz.getSuperclass();
/*     */       } 
/*     */     } 
/*  93 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private float getEyeHeight(Object player) {
/*     */     try {
/* 100 */       Method m = player.getClass().getDeclaredMethod("aR", new Class[0]);
/* 101 */       m.setAccessible(true);
/* 102 */       return ((Float)m.invoke(player, new Object[0])).floatValue();
/* 103 */     } catch (Exception e) {
/* 104 */       return 1.62F;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\module\impl\visual\Tracers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */