package monomorphism.manager.module.impl.combat;

import com.google.common.eventbus.Subscribe;
import com.mojang.authlib.GameProfile;
import excluded.GetHealth;
import java.awt.Color;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import monomorphism.manager.event.EventMotion;
import monomorphism.manager.event.EventPacket;
import monomorphism.manager.event.EventRender2D;
import monomorphism.manager.event.EventRender3D;
import monomorphism.manager.mapper.Entity;
import monomorphism.manager.module.Category;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.ModuleManager;
import monomorphism.manager.module.impl.world.Scaffold;
import monomorphism.manager.module.util.Client; // EKLENDİ: Hata buradan kaynaklanıyordu
import monomorphism.manager.module.util.SilentUtil;
import monomorphism.manager.module.util.render.Particles;
import monomorphism.manager.module.util.render.RenderUtil;
import monomorphism.manager.module.util.time.TimeUtil;
import monomorphism.manager.setting.SettingsManager;
import net.minecraft.BlockPos;
import net.minecraft.aX;
import net.minecraft.nn;
import net.minecraft.pK;
import net.minecraft.u0;
import net.minecraft.uy;
import net.minecraft.vx;
import net.minecraft.yB;
import net.minecraft.client.dw;
import net.minecraft.client.eT;
import net.minecraft.client.fx;
import net.minecraft.client.kb;
import net.minecraft.client.kr;
import net.minecraft.client.a0;
import net.minecraft.eV;
import org.lwjgl.opengl.GL11;

public class KillAura extends Module {
    public static nn target;
    public List<nn> targets = new ArrayList();
    public static boolean blocking;
    public static boolean attacking;
    private float displayHealth;
    private float health;
    private final List<Particles> particles = new ArrayList();
    private boolean sentParticles;
    private double scale = 0.0D;
    private final TimeUtil timeUtil = new TimeUtil();
    private final TimeUtil attackTimer = new TimeUtil();
    private final Random random = new Random();
    private final TimeUtil timer = new TimeUtil();

    private float lastYaw;
    private float lastPitch;
    private dw mc;
    private float randomYawOffset;

    // --- Reflection ve Helper Metotları ---
    private static Object getFieldValue(Object instance, String fieldName) {
        if (instance == null) return null;
        try {
            Class<?> clazz = instance.getClass();
            while (clazz != null) {
                try {
                    Field f = clazz.getDeclaredField(fieldName);
                    f.setAccessible(true);
                    return f.get(instance);
                } catch (NoSuchFieldException e) {
                    clazz = clazz.getSuperclass();
                }
            }
        } catch (Exception e) { }
        return null;
    }
    private static Object callMethod(Object instance, String methodName) {
        if (instance == null) return null;
        try {
            Class<?> clazz = instance.getClass();
            while (clazz != null) {
                for (Method m : clazz.getDeclaredMethods()) {
                    if (m.getName().equals(methodName) && m.getParameterCount() == 0) {
                        m.setAccessible(true);
                        return m.invoke(instance);
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (Exception e) { }
        return null;
    }
    @SuppressWarnings("unchecked")
    private static <T> T castObject(Object o) {
        return (T) o;
    }

    // Entity.java'daki helper'ları kullanıyoruz.
    public float getDist(nn entityIn) {
        return Entity.getDistanceToEntity(entityIn);
    }
    private void swingClient() {
        Entity.swingItem();
    }
    private void sendPacket(Object packet) {
        Entity.sendPacket(castObject(packet));
    }

    // --- Rotasyon ve Hedef Yardımcıları ---

    private float normalizeAngle(float angle) {
        angle = angle % 360.0F;
        if (angle >= 180.0F) {
            angle -= 360.0F;
        }
        if (angle < -180.0F) {
            angle += 360.0F;
        }
        return angle;
    }

    private float smoothRotation(float current, float target, float speed) {
        float diff = normalizeAngle(target - current);
        float maxChange = speed * (this.timeUtil.getElapsedTime() / 1000.0F);

        if (Math.abs(diff) > maxChange) {
            return current + (diff > 0 ? maxChange : -maxChange);
        }
        return target;
    }

    private float getFOV(nn entity, float currentYaw) {
        float[] rotations = getRotationsRaw(entity);
        float diff = normalizeAngle(rotations[0] - currentYaw);
        return Math.abs(diff);
    }

    // --- Constructor ve Module Lifecycle ---

    public KillAura() {
        super("Suriyeli Aura", 19, Category.Combat);
        SettingsManager.manager.addDouble("Range", "auraR", 3.0D, 6.0D, 4.2D, this);
        SettingsManager.manager.addDouble("Min CPS", "minCPS", 1.0D, 20.0D, 13.0D, this);
        SettingsManager.manager.addDouble("Max CPS", "maxCPS", 1.0D, 20.0D, 16.0D, this);
        SettingsManager.manager.addDouble("Rotation Speed", "auraRotSpeed", 10.0D, 180.0D, 120.0D, this);
        SettingsManager.manager.addMode("Priority", "auraPrio",
                "Distance",
                new ArrayList<>(Arrays.asList("Distance", "Health", "FOV")),
                this);
        SettingsManager.manager.addDouble("Max FOV", "auraFOV", 1.0D, 360.0D, 180.0D, this);
        SettingsManager.manager.addDouble("Hitbox Expand", "auraExpand", 0.0D, 0.3D, 0.0D, this);
        SettingsManager.manager.addBoolean("Swing", "auraS", true, this);
        SettingsManager.manager.addBoolean("AutoBlock", "auraB", true, this);
    }

    @Override
    public void onEnable() {
        this.mc = Client.mc;
        super.onEnable();
        if (mc != null && mc.aQ != null) {
            this.lastYaw = Entity.getRotationYaw();
            this.lastPitch = Entity.getRotationPitch();
            this.scale = 0.0D;
            this.attackTimer.reset();
        }
    }

    @Override
    public void onDisable() {
        this.scale = 0.0D;
        target = null;
        this.particles.clear();
        super.onDisable();
    }

    // --- Target ve Saldırı Mantığı ---

    public void sortTargets() {
        if (mc == null || mc.aQ == null) return;
        double range = SettingsManager.manager.getSettingByName("auraR").getValDouble();
        double expand = SettingsManager.manager.getSettingByName("auraExpand").getValDouble();

        if (target != null) {
            if (getDist(target) > range + expand || target.aj) {
                target = null;
            }
        }

        if (target == null) {
            List<nn> validTargets = new ArrayList<>();
            List<nn> entityList = Entity.getEntityList();

            if (entityList != null) {
                for (nn entity : entityList) {
                    if (entity != mc.aQ && getDist(entity) <= range + expand && !entity.aj) {
                        validTargets.add(entity);
                    }
                }
            }

            if (!validTargets.isEmpty()) {
                String priority = SettingsManager.manager.getSettingByName("auraPrio").getValString();
                float currentYaw = Entity.getRotationYaw();

                if (priority.equalsIgnoreCase("Distance")) {
                    validTargets.sort(Comparator.comparingDouble(this::getDist));
                } else if (priority.equalsIgnoreCase("Health")) {
                    validTargets.sort(Comparator.comparingDouble(entity -> {
                        float health = GetHealth.get(entity);
                        return (String.valueOf(health).equals("NaN") || health <= 0) ? Double.MAX_VALUE : health;
                    }));
                } else if (priority.equalsIgnoreCase("FOV")) {
                    validTargets.sort(Comparator.comparingDouble(entity -> getFOV(entity, currentYaw)));
                }
                target = validTargets.get(0);
            }
        }

        this.targets.clear();
        if (target != null) {
            this.targets.add(target);
        }
    }

    @Subscribe
    public void onUpdate(EventMotion event) {
        if (mc == null || mc.aQ == null) return;

        if (ModuleManager.INSTANCE.getModule(Scaffold.class).isEnabled()) {
            target = null;
            blocking = false;
            return;
        }

        this.sortTargets();
        this.randomYawOffset = random.nextFloat() * 2.0F - 1.0F;
        this.timeUtil.reset();

        if (target != null) {
            // 1. Rotasyonları yumuşat
            float[] desiredRotations = getRotationsRaw(target);
            float rotSpeed = (float)SettingsManager.manager.getSettingByName("auraRotSpeed").getValDouble();

            lastYaw = smoothRotation(lastYaw, desiredRotations[0], rotSpeed);
            lastPitch = smoothRotation(lastPitch, desiredRotations[1], rotSpeed);
            lastPitch = Math.max(-90.0F, Math.min(90.0F, lastPitch));

            lastYaw += randomYawOffset * 0.05F;
            lastPitch += randomYawOffset * 0.05F;

            // 2. Saldırı Hızı ve FOV kontrolü
            double minCPS = SettingsManager.manager.getSettingByName("minCPS").getValDouble();
            double maxCPS = SettingsManager.manager.getSettingByName("maxCPS").getValDouble();
            double cps = minCPS + (random.nextDouble() * (maxCPS - minCPS));
            long delay = (long) (1000.0 / cps);
            float maxFOV = (float)SettingsManager.manager.getSettingByName("auraFOV").getValDouble();

            if (attackTimer.hasReached(delay) && getFOV(target, lastYaw) <= maxFOV) {
                attacking = true;

                // AutoBlock'u Bırakma (Saldırı Öncesi)
                if (blocking) {
                    sendPacket(new aX(new BlockPos(-1, -1, -1), 255, castObject(callMethod(mc.aQ.b3, "b")), 0.0F, 0.0F, 0.0F));
                    blocking = false;
                }

                if (SettingsManager.manager.getSettingByName("auraS").getValBoolean()) {
                    swingClient();
                }

                // Saldırı Paketi
                sendPacket(new uy(target, yB.ATTACK));

                // AutoBlock'u Tekrar Başlatma (Saldırı Sonrası)
                Object itemStack = callMethod(mc.aQ.b3, "b");
                Object item = (itemStack != null) ? callMethod(itemStack, "a") : null;
                if (SettingsManager.manager.getSettingByName("auraB").getValBoolean() && itemStack != null && item instanceof pK) {
                    this.syncCurrentPlayItem();
                    mc.Q.a(mc.aQ, mc.I, castObject(itemStack));
                    blocking = true;
                }

                attackTimer.reset();
            } else {
                attacking = false;
            }
        } else {
            attacking = false;
            blocking = false;
            attackTimer.reset();
        }
    }

    // Rotation Packet Injection (Silent Aim)
    @Subscribe
    public void onPacket(EventPacket event) {
        if (target == null) return;

        if (event.packet instanceof fx) {
            try {
                fx packet = (fx) event.packet;
                Field[] fields = fx.class.getDeclaredFields();
                int floatCount = 0;
                int booleanCount = 0;

                for (Field f : fields) {
                    f.setAccessible(true);
                    if (f.getType() == float.class) {
                        if (floatCount == 0) {
                            f.setFloat(packet, lastYaw);
                        } else if (floatCount == 1) {
                            f.setFloat(packet, lastPitch);
                        }
                        floatCount++;
                    } else if (f.getType() == boolean.class) {
                        if (booleanCount == 2) {
                            f.setBoolean(packet, true);
                        }
                        booleanCount++;
                    }
                }

            } catch (Exception e) {
                // Hata durumunda yoksay
            }
        }
    }

    private float[] getRotationsRaw(nn ent) {
        if (mc == null || mc.aQ == null) return new float[]{0.0F, 0.0F};
        double x = ent.v - mc.aQ.v;
        double z = ent.r - mc.aQ.r;

        float entityEyeHeight = 1.62F;
        try {
            Object ret1 = callMethod(ent, "aR");
            if (ret1 instanceof Float) entityEyeHeight = (Float) ret1;
        } catch(Exception e) {}

        float myEyeHeight = 1.62F;
        try {
            Object ret2 = callMethod(mc.aQ, "aR");
            if (ret2 instanceof Float) myEyeHeight = (Float) ret2;
        } catch(Exception e) {}

        double verticalOffset = 0.05D + random.nextDouble() * 0.1D;
        double y = ent.ao + (double)entityEyeHeight * 0.9D - (mc.aQ.ao + (double)myEyeHeight) - verticalOffset;

        double dist = Math.sqrt(x * x + z * z);
        float yaw = (float) (Math.atan2(z, x) * 180.0D / Math.PI) - 90.0F;
        float pitch = (float) -(Math.atan2(y, dist) * 180.0D / Math.PI);

        yaw = normalizeAngle(yaw);
        pitch = normalizeAngle(pitch);
        return new float[]{yaw, pitch};
    }

    private void syncCurrentPlayItem() {
        if (mc == null || mc.Q == null || mc.aQ == null) return;
        int currentPlayerItem = -1;
        Object playerController = mc.Q;
        Object gField = getFieldValue(playerController, "g");
        if (gField instanceof Integer) {
            currentPlayerItem = (Integer) gField;
        }
        int n = mc.aQ.b3.f;
        if (n != currentPlayerItem) {
            sendPacket(new u0(currentPlayerItem));
        }
    }

    // --- Render Metotları (Target HUD için) ---

    @Subscribe
    public void onRender3D(EventRender3D event) {
        if (target != null) {
            this.drawCircle(target, 0.67D, true, event.getTicks());
        }
    }

    private void drawCircle(nn entity, double rad, boolean shade, float partialTicks) {
        if (mc == null) return;
        Object renderManager = getFieldValue(mc, "bI");
        if (renderManager == null) return;

        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glEnable(2832);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glHint(3153, 4354);
        GL11.glDepthMask(false);
        GL11.glAlphaFunc(516, 0.0F);
        if (shade) {
            GL11.glShadeModel(7425);
        }
        GL11.glDisable(2884);
        GL11.glBegin(5);

        kr renderMan = (kr)renderManager;
        Double renderPosX = castObject(getFieldValue(renderMan, "u"));
        Double renderPosY = castObject(getFieldValue(renderMan, "m"));
        Double renderPosZ = castObject(getFieldValue(renderMan, "r"));
        if (renderPosX == null || renderPosY == null || renderPosZ == null) return;

        double x = entity.ai + (Entity.getPosX(entity) - entity.ai) * partialTicks - renderPosX;
        double y = entity.a + (Entity.getPosY(entity) - entity.a) * partialTicks - renderPosY + Math.sin((double)System.currentTimeMillis() / 200.0D) + 1.0D;
        double z = entity.ah + (Entity.getPosZ(entity) - entity.ah) * partialTicks - renderPosZ;

        for(float i = 0.0F; (double)i < 6.283185307179586D; i = (float)((double)i + 0.09817477042468103D)) {
            double vecX = x + rad * Math.cos((double)i);
            double vecZ = z + rad * Math.sin((double)i);
            Color c = SilentUtil.getThemeColor(i, 1.0F);
            if (shade) {
                GL11.glColor4f((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F, 0.0F);
                GL11.glVertex3d(vecX, y - Math.cos((double)System.currentTimeMillis() / 200.0D) / 2.0D, vecZ);
                GL11.glColor4f((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F, 0.85F);
            }
            GL11.glVertex3d(vecX, y, vecZ);
        }

        GL11.glEnd();
        if (shade) {
            GL11.glShadeModel(7424);
        }
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glAlphaFunc(516, 0.1F);
        GL11.glEnable(2884);
        GL11.glDisable(2848);
        GL11.glDisable(2848);
        GL11.glEnable(2832);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }

    @Subscribe
    public void onRender2D(EventRender2D event) {
        if (mc == null || mc.aN == null || mc.ar == null) return;

        // HUD Scale Update (Target HUD'un görünür olmasını sağlayan temel düzeltme)
        if (this.timer.hasReached(9L)) {
            long elapsedTime = this.timer.getElapsedTime();
            if (target != null) {
                this.scale = Math.min(1.0D, this.scale + (double)elapsedTime / 4.0E14D + (1.0D - this.scale) / 10.0D);
            } else {
                this.scale = Math.max(0.0D, this.scale - (double)elapsedTime / 8.0E13D - (1.0D - this.scale) / 10.0D);
                if (this.scale == 0.0D) {
                    this.particles.clear();
                }
            }
            this.timer.reset();
        }

        if (target != null && target instanceof nn) {
            if (this.scale > 0.0D) {
                float nameWidth = 38.0F;
                float posX = (float)mc.s / (float)(mc.aN.ah * 2) - 38.0F - 45.0F + 80.0F;
                float posY = (float)mc.m / (float)(mc.aN.ah * 2) + 20.0F + 50.0F;

                RenderUtil.pushMatrix();
                RenderUtil.translate((double)(posX + 38.0F + 2.0F + 64.5F) * (1.0D - this.scale), (double)(posY - 34.0F + 24.0F) * (1.0D - this.scale), 0.0D);
                RenderUtil.scale(this.scale, this.scale, 0.0D);

                double dist = (double)getDist(target);
                GameProfile profile = null;
                try {
                    Field field = nn.class.getDeclaredField("b0");
                    field.setAccessible(true);
                    profile = (GameProfile)field.get(target);
                } catch (Exception var18) {}
                String name = (profile != null) ? profile.getName() : "Unknown";

                RenderUtil.roundedRect((double)(posX + 38.0F + 2.0F), (double)(posY - 34.0F), 129.0D, 48.0D, 8.0D, new Color(0, 0, 0, 110));

                int scaleOffset = (int)((float)target.bz * 0.35F);
                Iterator<Particles> var11 = this.particles.iterator();
                while(var11.hasNext()) {
                    Particles p = (Particles)var11.next();
                    if (p.opacity > 4.0D) {
                        p.render2D();
                    }
                }

                if (target instanceof eT) {
                    double fontHeight = (double)(-(((eT)target).bz * 23));
                    RenderUtil.color(new Color(255, (int)(255.0D + fontHeight), (int)(255.0D + fontHeight)));
                    renderPlayerModelTexture((double)(posX + 38.0F + 6.0F + (float)scaleOffset / 2.0F), (double)(posY - 34.0F + 5.0F + (float)scaleOffset / 2.0F), 3.0F, 3.0F, 3, 3, 30 - scaleOffset, 30 - scaleOffset, 24.0F, 24.5F, (eT)target);
                    renderPlayerModelTexture((double)(posX + 38.0F + 6.0F + (float)scaleOffset / 2.0F), (double)(posY - 34.0F + 5.0F + (float)scaleOffset / 2.0F), 15.0F, 3.0F, 3, 3, 30 - scaleOffset, 30 - scaleOffset, 24.0F, 24.5F, (eT)target);
                    RenderUtil.color(Color.WHITE);
                }

                double fontHeight = (double)mc.ar.i;
                mc.ar.a("Distance: " + this.round(dist, 1), posX + 38.0F + 6.0F + 30.0F + 3.0F, posY - 34.0F + 5.0F + 15.0F + 2.0F, Color.WHITE.hashCode());
                RenderUtil.pushMatrix();
                mc.ar.a("Name: " + name, posX + 38.0F + 6.0F + 30.0F + 3.0F, posY - 34.0F + 5.0F + 15.0F - (float)fontHeight, Color.WHITE.hashCode());
                RenderUtil.popMatrix();

                if (!String.valueOf(GetHealth.get(target)).equals("NaN")) {
                    this.health = Math.min(20.0F, GetHealth.get(target));
                }
                if (String.valueOf(this.displayHealth).equals("NaN")) {
                    this.displayHealth = (float)(Math.random() * 20.0D);
                }
                if (dist > 20.0D || target.aj) {
                    this.health = 0.0F;
                }

                if (this.timer.hasReached(16L)) {
                    this.displayHealth = (this.displayHealth * 5.0F + this.health) / 6.0F;
                }

                float offset = 6.0F;
                float drawBarPosX = posX + 38.0F;
                if ((double)this.displayHealth > 0.1D) {
                    for(int i = 0; (float)i < this.displayHealth * 4.0F; ++i) {
                        int color = SilentUtil.getColor((float)(10 + i), 0.5F, 1.0F);
                        fx.a((int)drawBarPosX + (int)offset, (int)posY + 5, (int)((double)(drawBarPosX + 1.0F) + (double)offset * 1.25D), (int)posY + 10, color);
                        ++offset;
                    }
                }

                if (!(dist > 20.0D) && !target.aj) {
                    mc.ar.a(String.valueOf(this.round((double)this.displayHealth, 1)), (float)((double)(drawBarPosX + 2.0F) + (double)offset * 1.25D), posY + 2.5F, -1);
                }

                ArrayList<Particles> removeList = new ArrayList();
                Iterator<Particles> var25 = this.particles.iterator();
                while(var25.hasNext()) {
                    Particles p = (Particles)var25.next();
                    p.updatePosition();
                    if (p.opacity <= 1.0D) {
                        removeList.add(p);
                    }
                }
                this.particles.removeAll(removeList);

                RenderUtil.popMatrix();
                this.timeUtil.reset();
            }
        } else {
            this.particles.clear();
        }
    }

    // --- Yardımcı Metotların Devamı ---

    public double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        } else {
            BigDecimal bd = new BigDecimal(Double.toString(value));
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }

    public static double getDistanceSqToEntity(nn target, nn entityIn) {
        double d0 = Entity.getPosX(target) - Entity.getPosX(entityIn);
        double d1 = Entity.getPosY(target) - Entity.getPosY(entityIn);
        double d2 = Entity.getPosZ(target) - Entity.getPosZ(entityIn);
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public static void renderPlayerModelTexture(double x, double y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight, eT target) {
        dw mcStatic = Client.mc;
        vx skin = (vx) callMethod(target, "b");
        kb text = castObject(getFieldValue(mcStatic, "h"));
        if (skin != null && text != null) {
            text.b(skin);
        }
        GL11.glEnable(3042);
        fx.a((int)x, (int)y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
        GL11.glDisable(3042);
    }

    public static void renderPlayerModelTexture(double x, double y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        dw mcStatic = Client.mc;
        vx skin = new vx("textures/entity/steve.png");
        kb text = castObject(getFieldValue(mcStatic, "h"));
        if (text != null) {
            text.b(skin);
        }
        GL11.glEnable(3042);
        fx.a((int)x, (int)y, u, v, uWidth, vHeight, width, height, tileWidth, tileHeight);
        GL11.glDisable(3042);
    }

    public Color getThemeColor(float colorOffset) {
        return this.getThemeColor(colorOffset, 1.0F);
    }

    public Color getThemeColor(float colorOffset, float timeMultiplier) {
        float colorOffsetMultiplier = 2.2F;
        colorOffset *= colorOffsetMultiplier;
        double timer = (double)System.currentTimeMillis() / 1.0E8D * (double)timeMultiplier * 400000.0D;
        double factor = (Math.sin(timer + (double)(colorOffset * 0.55F)) + 1.0D) * 0.5D;
        return this.mixColors(new Color(190, 0, 255, 255), new Color(0, 190, 255, 255), factor);
    }

    public Color mixColors(Color color1, Color color2, double percent) {
        double inverse_percent = 1.0D - percent;
        int redPart = (int)((double)color1.getRed() * percent + (double)color2.getRed() * inverse_percent);
        int greenPart = (int)((double)color1.getGreen() * percent + (double)color2.getGreen() * inverse_percent);
        int bluePart = (int)((double)color1.getBlue() * percent + (double)color2.getBlue() * inverse_percent);
        return new Color(redPart, greenPart, bluePart);
    }

    public static void anim(float f, float f1) {
        float var15 = (float)Math.sin((double)(f1 * f1 * 3.1415927F));
        transformFirstPersonItem(f / 2.0F, 0.0F);
        GL11.glRotatef(-var15 * 40.0F / 2.0F, var15 / 2.0F, -0.0F, 9.0F);
        GL11.glRotatef(-var15 * 30.0F, 1.0F, var15 / 2.0F, -0.0F);
        doBlockTransformations();
        GL11.glTranslatef(-0.05F, 0.0F, 0.1F);
    }

    private static void transformFirstPersonItem(float equipProgress, float swingProgress) {
        GL11.glTranslatef(0.56F, -0.52F, -0.71999997F);
        GL11.glTranslatef(0.0F, equipProgress * -0.6F, 0.0F);
        GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        float f = (float)Math.sin((double)(swingProgress * swingProgress * 3.1415927F));
        float f1 = (float)Math.sin((double)(sqrt_float(swingProgress) * 3.1415927F));
        GL11.glRotatef(f * -20.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(f1 * -20.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(0.4F, 0.4F, 0.4F);
    }

    private static void doBlockTransformations() {
        GL11.glTranslatef(-0.5F, 0.2F, 0.0F);
        GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
    }

    public static float sqrt_float(float value) {
        return (float)Math.sqrt((double)value);
    }
}