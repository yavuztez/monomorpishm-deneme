/*     */ package gov.babalar.myth;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Method;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.zip.GZIPInputStream;
/*     */ import net.minecraft.BlockPos;
/*     */ import net.minecraft.DN;
/*     */ import net.minecraft.DP;
/*     */ import net.minecraft.DZ;
/*     */ import net.minecraft.HX;
/*     */ import net.minecraft.P1;
/*     */ import net.minecraft.Qu;
/*     */ import net.minecraft.Tz;
/*     */ import net.minecraft.client.CC;
/*     */ import net.minecraft.client.CY;
/*     */ import net.minecraft.client.Pe;
/*     */ import net.minecraft.client.eT;
/*     */ import net.minecraft.client.en;
/*     */ import net.minecraft.client.hj;
/*     */ import net.minecraft.client.iz;
/*     */ import net.minecraft.client.kX;
/*     */ import net.minecraft.er;
/*     */ import net.minecraft.f3;
/*     */ import net.minecraft.o0;
/*     */ 
/*     */ public class Utility {
/*     */   public static Pe getMc() {
/*     */     try {
/*  32 */       Field f = Pe.class.getDeclaredField("eb");
/*  33 */       f.setAccessible(true);
/*  34 */       return (Pe)f.get(null);
/*  35 */     } catch (Exception e) {
/*  36 */       e.printStackTrace();
/*     */       
/*  38 */       return null;
/*     */     } 
/*     */   } private static Method setPositionMethod;
/*     */   public static void log(String msg) {
/*  42 */     Pe.Q().info(msg);
/*     */   }
/*     */   
/*     */   public static kX getThePlayer() {
/*  46 */     return Client.mc.eS;
/*     */   }
/*     */   
/*     */   public static hj getFontRendererObj() {
/*  50 */     return Client.mc.er;
/*     */   }
/*     */   
/*     */   public static iz getTextureManager() {
/*     */     try {
/*  55 */       Field f = Pe.class.getDeclaredField("e2");
/*  56 */       f.setAccessible(true);
/*  57 */       return (iz)f.get(Client.mc);
/*  58 */     } catch (Exception e) {
/*  59 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static eT getFrameBuffer() {
/*     */     try {
/*  65 */       Field f = Pe.class.getDeclaredField("eP");
/*  66 */       f.setAccessible(true);
/*  67 */       return (eT)f.get(Client.mc);
/*  68 */     } catch (Exception e) {
/*  69 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static CY getTheWorld() {
/*  74 */     return Client.mc.e9;
/*     */   }
/*     */   
/*     */   public static HX getBlockState(BlockPos pos) {
/*     */     try {
/*  79 */       Method m = filterMethodsFromReturn("B", P1.class, HX.class);
/*  80 */       return (HX)m.invoke(getTheWorld(), new Object[] { pos });
/*  81 */     } catch (Exception e) {
/*  82 */       e.printStackTrace();
/*     */       
/*  84 */       return null;
/*     */     } 
/*     */   }
/*     */   public static o0 getMovementInput() {
/*  88 */     return (getThePlayer()).Yz;
/*     */   }
/*     */   
/*     */   public static Ll getGameSettings() {
/*  92 */     return Client.mc.eu;
/*     */   }
/*     */   
/*     */   public static float getMIForward() {
/*  96 */     return (getMovementInput()).W;
/*     */   }
/*     */   
/*     */   public static float getMIStrafe() {
/* 100 */     return (getMovementInput()).a;
/*     */   }
/*     */   
/*     */   public static List<DZ> getEntityList() {
/* 104 */     return Client.mc.e9.P;
/*     */   }
/*     */   
/*     */   public static void setMotionX(double d) {
/* 108 */     (getThePlayer()).z = d;
/*     */   }
/*     */   
/*     */   public static void setMotionY(double d) {
/* 112 */     getThePlayer().u(d);
/*     */   }
/*     */   
/*     */   public static void setMotionZ(double d) {
/* 116 */     (getThePlayer()).O = d;
/*     */   }
/*     */   
/*     */   public static double getMotionX() {
/* 120 */     return (getThePlayer()).z;
/*     */   }
/*     */   
/*     */   public static double getMotionY() {
/* 124 */     return (getThePlayer()).E;
/*     */   }
/*     */   
/*     */   public static double getMotionZ() {
/* 128 */     return (getThePlayer()).O;
/*     */   }
/*     */   
/*     */   public static float getRotationYaw() {
/* 132 */     return (getThePlayer()).e;
/*     */   }
/*     */   
/*     */   public static float getRotationPitch() {
/* 136 */     return (getThePlayer()).B;
/*     */   }
/*     */   
/*     */   public static void setStepHeight(float f) {
/* 140 */     (getThePlayer()).Q = f;
/*     */   }
/*     */   
/*     */   public static void drawStringWithShadow(String s, float x, float y, int color) {
/* 144 */     getFontRendererObj().X(s, x, y, color, true);
/*     */   }
/*     */   
/*     */   public static void drawString(String s, float x, float y, int color) {
/* 148 */     getFontRendererObj().X(s, x, y, color, false);
/*     */   }
/*     */   
/*     */   public static double getPosX(DN entity) {
/* 152 */     return entity.l;
/*     */   }
/*     */   
/*     */   public static double getPosX() {
/* 156 */     return getPosX((DN)getThePlayer());
/*     */   }
/*     */   
/*     */   public static double getPosY(DN entity) {
/* 160 */     return entity.ph;
/*     */   }
/*     */   
/*     */   public static double getPosY() {
/* 164 */     return getPosY((DN)getThePlayer());
/*     */   }
/*     */   
/*     */   public static double getPosZ(DN entity) {
/* 168 */     return entity.p6;
/*     */   }
/*     */   
/*     */   public static double getPosZ() {
/* 172 */     return getPosZ((DN)getThePlayer());
/*     */   }
/*     */   
/*     */   public static float getDistanceToEntity(DZ entityIn) {
/* 176 */     return getDistanceToEntity1(entityIn);
/*     */   }
/*     */   
/*     */   public static boolean isDead(DN entity) {
/* 180 */     return entity.C;
/*     */   }
/*     */   
/*     */   public static boolean isInvisible(DN entity) {
/*     */     try {
/* 185 */       Method m = filterMethodsFromReturn("m", DN.class, boolean.class);
/* 186 */       return ((Boolean)m.invoke(entity, new Object[0])).booleanValue();
/* 187 */     } catch (Exception e) {
/* 188 */       e.printStackTrace();
/*     */       
/* 190 */       return false;
/*     */     } 
/*     */   }
/*     */   public static Method filterMethodsFromReturn(String name, Class<?> clazz, Class<?> returnType) {
/* 194 */     for (Method declaredMethod : clazz.getDeclaredMethods()) {
/* 195 */       if (declaredMethod.getName().equals(name) && 
/* 196 */         declaredMethod.getReturnType().equals(returnType)) {
/* 197 */         return declaredMethod;
/*     */       }
/*     */     } 
/* 200 */     return null;
/*     */   }
/*     */   
/*     */   public static int getDisplayWidth() {
/* 204 */     return Client.mc.eI;
/*     */   }
/*     */   
/*     */   public static int getDisplayHeight() {
/* 208 */     return Client.mc.eg;
/*     */   }
/*     */   
/*     */   public static boolean isOnGround() {
/* 212 */     return getThePlayer().I();
/*     */   }
/*     */   
/*     */   public static float getDistanceToEntity1(DZ entityIn) {
/* 216 */     return (float)Math.sqrt(getThePlayer().G((DN)entityIn));
/*     */   }
/*     */   
/*     */   public static void sendPacket(Tz<?> packetIn) {
/* 220 */     (getThePlayer()).YU.A(packetIn);
/*     */   }
/*     */   
/*     */   public static String getPlayerName(DZ entity) {
/* 224 */     GameProfile profile = null;
/*     */     try {
/* 226 */       Field field = DZ.class.getDeclaredField("Fe");
/* 227 */       field.setAccessible(true);
/* 228 */       profile = (GameProfile)field.get(entity);
/* 229 */     } catch (Exception exception) {}
/*     */     
/* 231 */     return profile.getName();
/*     */   }
/*     */   
/*     */   public static String getPlayerName() {
/* 235 */     return getPlayerName((DZ)getThePlayer());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getHealth(DP entity) {
/* 243 */     return invokeDPNumber(entity, "j");
/*     */   }
/*     */   
/*     */   public static float getMaxHealth(DP entity) {
/* 247 */     return invokeDPNumber(entity, "k");
/*     */   }
/*     */   
/*     */   private static float invokeDPNumber(DP entity, String methodName) {
/*     */     try {
/* 252 */       Method m = DP.class.getDeclaredMethod(methodName, new Class[0]);
/* 253 */       m.setAccessible(true);
/* 254 */       return ((Number)m.invoke(entity, new Object[0])).floatValue();
/* 255 */     } catch (Exception e) {
/* 256 */       e.printStackTrace();
/* 257 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static double[] scales() {
/* 264 */     CC cc = new CC(Client.mc);
/* 265 */     return new double[] { cc
/* 266 */         .K(), cc.n() };
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean onPlayerRightClick(f3 itemStack, BlockPos pos, Qu facing, er vec3) {
/* 271 */     return getPlayerController().v(getThePlayer(), getTheWorld(), itemStack, pos, facing, vec3);
/*     */   }
/*     */   
/*     */   public static ec getPlayerController() {
/* 275 */     return Client.mc.N;
/*     */   }
/*     */   
/*     */   public static f3 getStackInSlot(int slot) {
/*     */     try {
/* 280 */       Method stackInSlot = filterMethodsFromReturn("c", LH.class, f3.class);
/* 281 */       return (f3)stackInSlot.invoke((getThePlayer()).Fc, new Object[] { Integer.valueOf(slot) });
/* 282 */     } catch (Exception e) {
/* 283 */       e.printStackTrace();
/*     */       
/* 285 */       return null;
/*     */     } 
/*     */   }
/*     */   public static int getFPS() {
/*     */     try {
/* 290 */       Field fps = Pe.class.getDeclaredField("F");
/* 291 */       fps.setAccessible(true);
/* 292 */       return fps.getInt(Client.mc);
/* 293 */     } catch (Exception e) {
/* 294 */       e.printStackTrace();
/*     */       
/* 296 */       return -1;
/*     */     } 
/*     */   }
/*     */   public static int getStringWidth(String s) {
/* 300 */     return getFontRendererObj().X(s);
/*     */   }
/*     */   
/*     */   public static boolean isAnyGuiOpen() {
/*     */     try {
/* 305 */       Field f = Client.mc.getClass().getDeclaredField("ee");
/* 306 */       return (f.get(Client.mc) == null);
/* 307 */     } catch (Exception e) {
/* 308 */       e.printStackTrace();
/* 309 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String decompress(byte[] compressedData) {
/*     */     try {
/* 315 */       ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedData);
/* 316 */       GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
/* 317 */       ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/*     */       
/* 319 */       byte[] buffer = new byte[1024];
/*     */       int len;
/* 321 */       while ((len = gzipInputStream.read(buffer)) != -1) {
/* 322 */         outputStream.write(buffer, 0, len);
/*     */       }
/*     */       
/* 325 */       gzipInputStream.close();
/* 326 */       outputStream.close();
/*     */       
/* 328 */       return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
/* 329 */     } catch (Exception e) {
/* 330 */       e.printStackTrace();
/*     */       
/* 332 */       return null;
/*     */     } 
/*     */   }
/*     */   public static float getFallDistance() {
/* 336 */     return (getThePlayer()).g;
/*     */   }
/*     */   
/*     */   public static void setFallDistance(float fallDistance) {
/* 340 */     (getThePlayer()).g = fallDistance;
/*     */   }
/*     */   
/*     */   public static void displayGui(en gui) {
/* 344 */     Client.mc.I(gui);
/*     */   }
/*     */   
/*     */   public static void drawCentered(String s, int x, int y, int color) {
/* 348 */     drawStringWithShadow(s, (x - getStringWidth(s) / 2), y, color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/* 356 */       setPositionMethod = kX.class.getDeclaredMethod("e", new Class[] { double.class, double.class, double.class });
/* 357 */       setPositionMethod.setAccessible(true);
/* 358 */     } catch (Exception e) {
/* 359 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void setPosition(double x, double y, double z) {
/*     */     try {
/* 365 */       setPositionMethod.invoke(getThePlayer(), new Object[] { Double.valueOf(x), Double.valueOf(y), Double.valueOf(z) });
/* 366 */     } catch (Exception e) {
/* 367 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\Utility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */