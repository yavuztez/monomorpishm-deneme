/*      */ package monomorphism.manager.module.util.render;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.math.BigDecimal;
/*      */ import java.math.RoundingMode;
/*      */ import java.nio.FloatBuffer;
/*      */ import monomorphism.manager.module.util.Client;
/*      */ import net.minecraft.client.jF;
/*      */ import org.lwjgl.Sys;
/*      */ import org.lwjgl.opengl.GL11;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RenderUtil
/*      */ {
/*      */   public static void glRenderStart() {
/*   20 */     GL11.glPushMatrix();
/*   21 */     GL11.glPushAttrib(1048575);
/*   22 */     GL11.glEnable(3042);
/*   23 */     GL11.glDisable(2884);
/*   24 */     GL11.glDisable(3553);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void glRenderStop() {
/*   29 */     GL11.glEnable(3553);
/*   30 */     GL11.glEnable(2884);
/*   31 */     GL11.glDisable(3042);
/*   32 */     GL11.glPopAttrib();
/*   33 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static float convertColor(int count, int color) {
/*   38 */     return (color >> count & 0xFF) / 255.0F;
/*      */   }
/*      */ 
/*      */   
/*      */   public static void setColor(Color c) {
/*   43 */     GL11.glColor4d((c.getRed() / 255.0F), (c.getGreen() / 255.0F), (c.getBlue() / 255.0F), (c.getAlpha() / 255.0F));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawGradient(double x, double y, double x2, double y2, int col1, int col2) {
/*   48 */     float f = (col1 >> 24 & 0xFF) / 255.0F;
/*   49 */     float f1 = (col1 >> 16 & 0xFF) / 255.0F;
/*   50 */     float f2 = (col1 >> 8 & 0xFF) / 255.0F;
/*   51 */     float f3 = (col1 & 0xFF) / 255.0F;
/*   52 */     float f4 = (col2 >> 24 & 0xFF) / 255.0F;
/*   53 */     float f5 = (col2 >> 16 & 0xFF) / 255.0F;
/*   54 */     float f6 = (col2 >> 8 & 0xFF) / 255.0F;
/*   55 */     float f7 = (col2 & 0xFF) / 255.0F;
/*   56 */     glRenderStart();
/*   57 */     GL11.glEnable(3042);
/*   58 */     GL11.glDisable(3553);
/*   59 */     GL11.glBlendFunc(770, 771);
/*   60 */     GL11.glEnable(2848);
/*   61 */     GL11.glShadeModel(7425);
/*   62 */     GL11.glPushMatrix();
/*   63 */     GL11.glBegin(7);
/*   64 */     GL11.glColor4f(f1, f2, f3, f);
/*   65 */     GL11.glVertex2d(x2, y);
/*   66 */     GL11.glVertex2d(x, y);
/*   67 */     GL11.glColor4f(f5, f6, f7, f4);
/*   68 */     GL11.glVertex2d(x, y2);
/*   69 */     GL11.glVertex2d(x2, y2);
/*   70 */     GL11.glEnd();
/*   71 */     GL11.glPopMatrix();
/*   72 */     GL11.glEnable(3553);
/*   73 */     GL11.glDisable(3042);
/*   74 */     GL11.glDisable(2848);
/*   75 */     GL11.glShadeModel(7424);
/*   76 */     GL11.glColor4d(1.0D, 1.0D, 1.0D, 1.0D);
/*   77 */     glRenderStop();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawGradientSideways(double left, double top, double right, double bottom, int col1, int col2) {
/*   82 */     float f = (col1 >> 24 & 0xFF) / 255.0F;
/*   83 */     float f1 = (col1 >> 16 & 0xFF) / 255.0F;
/*   84 */     float f2 = (col1 >> 8 & 0xFF) / 255.0F;
/*   85 */     float f3 = (col1 & 0xFF) / 255.0F;
/*   86 */     float f4 = (col2 >> 24 & 0xFF) / 255.0F;
/*   87 */     float f5 = (col2 >> 16 & 0xFF) / 255.0F;
/*   88 */     float f6 = (col2 >> 8 & 0xFF) / 255.0F;
/*   89 */     float f7 = (col2 & 0xFF) / 255.0F;
/*   90 */     glRenderStart();
/*   91 */     GL11.glEnable(3042);
/*   92 */     GL11.glDisable(3553);
/*   93 */     GL11.glBlendFunc(770, 771);
/*   94 */     GL11.glEnable(2848);
/*   95 */     GL11.glShadeModel(7425);
/*   96 */     GL11.glPushMatrix();
/*   97 */     GL11.glBegin(7);
/*   98 */     GL11.glColor4f(f1, f2, f3, f);
/*   99 */     GL11.glVertex2d(left, top);
/*  100 */     GL11.glVertex2d(left, bottom);
/*  101 */     GL11.glColor4f(f5, f6, f7, f4);
/*  102 */     GL11.glVertex2d(right, bottom);
/*  103 */     GL11.glVertex2d(right, top);
/*  104 */     GL11.glEnd();
/*  105 */     GL11.glPopMatrix();
/*  106 */     GL11.glEnable(3553);
/*  107 */     GL11.glDisable(3042);
/*  108 */     GL11.glDisable(2848);
/*  109 */     GL11.glShadeModel(7424);
/*  110 */     glRenderStop();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawRect(double x, double y, double d, double e, int color) {
/*  115 */     float f = (color >> 24 & 0xFF) / 255.0F;
/*  116 */     float f1 = (color >> 16 & 0xFF) / 255.0F;
/*  117 */     float f2 = (color >> 8 & 0xFF) / 255.0F;
/*  118 */     float f3 = (color & 0xFF) / 255.0F;
/*  119 */     glRenderStart();
/*  120 */     GL11.glColor4f(f1, f2, f3, f);
/*  121 */     GL11.glBegin(7);
/*  122 */     GL11.glVertex2d(x, y);
/*  123 */     GL11.glVertex2d(d, y);
/*  124 */     GL11.glVertex2d(d, e);
/*  125 */     GL11.glVertex2d(x, e);
/*  126 */     GL11.glEnd();
/*  127 */     glRenderStop();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawBorderedRect(float xPos, float yPos, float width, float height, float lineWidth, int lineColor, int bgColor) {
/*  132 */     drawRect(xPos, yPos, width, height, bgColor);
/*  133 */     float f = (lineColor >> 24 & 0xFF) / 255.0F;
/*  134 */     float f1 = (lineColor >> 16 & 0xFF) / 255.0F;
/*  135 */     float f2 = (lineColor >> 8 & 0xFF) / 255.0F;
/*  136 */     float f3 = (lineColor & 0xFF) / 255.0F;
/*  137 */     glRenderStart();
/*  138 */     GL11.glColor4f(f1, f2, f3, f);
/*  139 */     GL11.glLineWidth(lineWidth);
/*  140 */     GL11.glEnable(2848);
/*  141 */     GL11.glBegin(1);
/*  142 */     GL11.glVertex2d(xPos, yPos);
/*  143 */     GL11.glVertex2d(width, yPos);
/*  144 */     GL11.glVertex2d(width, yPos);
/*  145 */     GL11.glVertex2d(width, height);
/*  146 */     GL11.glVertex2d(width, height);
/*  147 */     GL11.glVertex2d(xPos, height);
/*  148 */     GL11.glVertex2d(xPos, height);
/*  149 */     GL11.glVertex2d(xPos, yPos);
/*  150 */     GL11.glEnd();
/*  151 */     glRenderStop();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawOctagon(float xPos, float yPos, float width, float height, float length, float angle, int color) {
/*  156 */     float f = convertColor(24, color);
/*  157 */     float f1 = convertColor(16, color);
/*  158 */     float f2 = convertColor(8, color);
/*  159 */     float f3 = convertColor(0, color);
/*  160 */     glRenderStart();
/*  161 */     GL11.glColor4f(f1, f2, f3, f);
/*  162 */     GL11.glBegin(9);
/*  163 */     GL11.glVertex2d((xPos + length), yPos);
/*  164 */     GL11.glVertex2d((xPos + width - length), yPos);
/*  165 */     GL11.glVertex2d((xPos + width - length), yPos);
/*  166 */     GL11.glVertex2d((xPos + width), (yPos + height / 2.0F - angle));
/*  167 */     GL11.glVertex2d((xPos + width), (yPos + height / 2.0F - angle));
/*  168 */     GL11.glVertex2d((xPos + width), (yPos + height / 2.0F + angle));
/*  169 */     GL11.glVertex2d((xPos + width), (yPos + height / 2.0F + angle));
/*  170 */     GL11.glVertex2d((xPos + width - length), (yPos + height));
/*  171 */     GL11.glVertex2d((xPos + width - length), (yPos + height));
/*  172 */     GL11.glVertex2d((xPos + length), (yPos + height));
/*  173 */     GL11.glVertex2d((xPos + length), (yPos + height));
/*  174 */     GL11.glVertex2d(xPos, (yPos + height / 2.0F + angle));
/*  175 */     GL11.glVertex2d(xPos, (yPos + height / 2.0F + angle));
/*  176 */     GL11.glVertex2d(xPos, (yPos + height / 2.0F - angle));
/*  177 */     GL11.glVertex2d(xPos, (yPos + height / 2.0F - angle));
/*  178 */     GL11.glVertex2d((xPos + length), yPos);
/*  179 */     GL11.glEnd();
/*  180 */     glRenderStop();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawBorderedCircle(float x, float y, float radius, int lineWidth, int outsideC, int insideC) {
/*  185 */     drawCircle(x, y, radius, insideC);
/*  186 */     drawUnfilledCircle(x, y, radius, lineWidth, outsideC);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawCircle228(float x, float y, float radius, int lineWidth, int outsideC, int insideC, int jopaSlona) {
/*  191 */     drawCircle228(x, y, radius, insideC, jopaSlona);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawUnfilledCircle228(float x, float y, float radius, float lineWidth, int color, int jopaSlona) {
/*  196 */     float f = (color >> 16 & 0xFF) / 255.0F;
/*  197 */     float f1 = (color >> 8 & 0xFF) / 255.0F;
/*  198 */     float f2 = (color & 0xFF) / 255.0F;
/*  199 */     float f3 = (color >> 24 & 0xFF) / 255.0F;
/*  200 */     GL11.glEnable(2848);
/*  201 */     GL11.glEnable(3042);
/*  202 */     GL11.glDisable(3553);
/*  203 */     GL11.glBlendFunc(770, 771);
/*  204 */     GL11.glDepthMask(true);
/*  205 */     GL11.glEnable(2848);
/*  206 */     GL11.glHint(3154, 4354);
/*  207 */     GL11.glHint(3155, 4354);
/*  208 */     enableBlend();
/*  209 */     GL11.glColor4f(f, f1, f2, f3);
/*  210 */     GL11.glLineWidth(lineWidth);
/*  211 */     GL11.glBegin(2);
/*  212 */     for (int i = 0; i <= jopaSlona; i++)
/*      */     {
/*  214 */       GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * radius, y + Math.cos(i * Math.PI / 180.0D) * radius);
/*      */     }
/*  216 */     GL11.glEnd();
/*  217 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/*  218 */     GL11.glEnable(3553);
/*  219 */     GL11.glDisable(3042);
/*  220 */     GL11.glDisable(2848);
/*  221 */     GL11.glHint(3154, 4352);
/*  222 */     GL11.glHint(3155, 4352);
/*  223 */     disableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawUnfilledCircle(float x, float y, float radius, float lineWidth, int color) {
/*  228 */     float f = (color >> 16 & 0xFF) / 255.0F;
/*  229 */     float f1 = (color >> 8 & 0xFF) / 255.0F;
/*  230 */     float f2 = (color & 0xFF) / 255.0F;
/*  231 */     float f3 = (color >> 24 & 0xFF) / 255.0F;
/*  232 */     GL11.glEnable(2848);
/*  233 */     GL11.glEnable(3042);
/*  234 */     GL11.glDisable(3553);
/*  235 */     GL11.glBlendFunc(770, 771);
/*  236 */     GL11.glDepthMask(true);
/*  237 */     GL11.glEnable(2848);
/*  238 */     GL11.glHint(3154, 4354);
/*  239 */     GL11.glHint(3155, 4354);
/*  240 */     enableBlend();
/*  241 */     GL11.glColor4f(f, f1, f2, f3);
/*  242 */     GL11.glLineWidth(lineWidth);
/*  243 */     GL11.glBegin(2);
/*  244 */     for (int i = 0; i <= 360; i++)
/*      */     {
/*  246 */       GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * radius, y + Math.cos(i * Math.PI / 180.0D) * radius);
/*      */     }
/*  248 */     GL11.glEnd();
/*  249 */     GL11.glScalef(2.0F, 2.0F, 2.0F);
/*  250 */     GL11.glEnable(3553);
/*  251 */     GL11.glDisable(3042);
/*  252 */     GL11.glDisable(2848);
/*  253 */     GL11.glHint(3154, 4352);
/*  254 */     GL11.glHint(3155, 4352);
/*  255 */     disableBlend();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawCircle228(float x, float y, float radius, int color, int jopaSlona) {
/*  260 */     float f = (color >> 24 & 0xFF) / 255.0F;
/*  261 */     float f1 = (color >> 16 & 0xFF) / 255.0F;
/*  262 */     float f2 = (color >> 8 & 0xFF) / 255.0F;
/*  263 */     float f3 = (color & 0xFF) / 255.0F;
/*  264 */     boolean flag = GL11.glIsEnabled(3042);
/*  265 */     boolean flag1 = GL11.glIsEnabled(2848);
/*  266 */     boolean flag2 = GL11.glIsEnabled(3553);
/*  267 */     if (!flag)
/*      */     {
/*  269 */       GL11.glEnable(3042);
/*      */     }
/*  271 */     if (!flag1)
/*      */     {
/*  273 */       GL11.glEnable(2848);
/*      */     }
/*  275 */     if (flag2)
/*      */     {
/*  277 */       GL11.glDisable(3553);
/*      */     }
/*  279 */     GL11.glEnable(2848);
/*  280 */     GL11.glBlendFunc(770, 771);
/*  281 */     GL11.glColor4f(f1, f2, f3, f);
/*  282 */     GL11.glLineWidth(2.5F);
/*  283 */     GL11.glBegin(3);
/*  284 */     for (int i = 0; i <= jopaSlona; i++)
/*      */     {
/*  286 */       GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * radius, y + Math.cos(i * Math.PI / 180.0D) * radius);
/*      */     }
/*  288 */     GL11.glEnd();
/*  289 */     GL11.glDisable(2848);
/*  290 */     if (flag2)
/*      */     {
/*  292 */       GL11.glEnable(3553);
/*      */     }
/*  294 */     if (!flag1)
/*      */     {
/*  296 */       GL11.glDisable(2848);
/*      */     }
/*  298 */     if (!flag)
/*      */     {
/*  300 */       GL11.glDisable(3042);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawCircle(float x, float y, float radius, int color) {
/*  306 */     float f = (color >> 24 & 0xFF) / 255.0F;
/*  307 */     float f1 = (color >> 16 & 0xFF) / 255.0F;
/*  308 */     float f2 = (color >> 8 & 0xFF) / 255.0F;
/*  309 */     float f3 = (color & 0xFF) / 255.0F;
/*  310 */     boolean flag = GL11.glIsEnabled(3042);
/*  311 */     boolean flag1 = GL11.glIsEnabled(2848);
/*  312 */     boolean flag2 = GL11.glIsEnabled(3553);
/*  313 */     if (!flag)
/*      */     {
/*  315 */       GL11.glEnable(3042);
/*      */     }
/*  317 */     if (!flag1)
/*      */     {
/*  319 */       GL11.glEnable(2848);
/*      */     }
/*  321 */     if (flag2)
/*      */     {
/*  323 */       GL11.glDisable(3553);
/*      */     }
/*  325 */     GL11.glEnable(2848);
/*  326 */     GL11.glBlendFunc(770, 771);
/*  327 */     GL11.glColor4f(f1, f2, f3, f);
/*  328 */     GL11.glBegin(9);
/*  329 */     for (int i = 0; i <= 360; i++)
/*      */     {
/*  331 */       GL11.glVertex2d(x + Math.sin(i * Math.PI / 180.0D) * radius, y + Math.cos(i * Math.PI / 180.0D) * radius);
/*      */     }
/*  333 */     GL11.glEnd();
/*  334 */     GL11.glDisable(2848);
/*  335 */     if (flag2)
/*      */     {
/*  337 */       GL11.glEnable(3553);
/*      */     }
/*  339 */     if (!flag1)
/*      */     {
/*  341 */       GL11.glDisable(2848);
/*      */     }
/*  343 */     if (!flag)
/*      */     {
/*  345 */       GL11.glDisable(3042);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableScissoring() {
/*  351 */     GL11.glEnable(3089);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void disableScissoring() {
/*  357 */     GL11.glDisable(3089);
/*      */   }
/*      */   
/*      */   public static void pushAttrib() {
/*  361 */     GL11.glPushAttrib(8256);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void popAttrib() {
/*  366 */     GL11.glPopAttrib();
/*      */   }
/*      */   
/*      */   public static void color(int color, float alpha) {
/*  370 */     float r = (color >> 16 & 0xFF) / 255.0F;
/*  371 */     float g = (color >> 8 & 0xFF) / 255.0F;
/*  372 */     float b = (color & 0xFF) / 255.0F;
/*  373 */     GL11.glColor4f(r, g, b, alpha);
/*      */   }
/*      */   
/*      */   public static void color(int color) {
/*  377 */     color(color, (color >> 24 & 0xFF) / 255.0F);
/*      */   }
/*      */   
/*      */   public static void resetColor() {
/*  381 */     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void clear(int mask) {
/*  386 */     GL11.glClear(mask);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void matrixMode(int mode) {
/*  391 */     GL11.glMatrixMode(mode);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void loadIdentity() {
/*  396 */     GL11.glLoadIdentity();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void pushMatrix() {
/*  401 */     GL11.glPushMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void popMatrix() {
/*  406 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void getFloat(int pname, FloatBuffer params) {
/*  411 */     GL11.glGetFloat(pname, params);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void ortho(double left, double right, double bottom, double top, double zNear, double zFar) {
/*  416 */     GL11.glOrtho(left, right, bottom, top, zNear, zFar);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void rotate(float angle, float x, float y, float z) {
/*  421 */     GL11.glRotatef(angle, x, y, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void scale(float x, float y, float z) {
/*  426 */     GL11.glScalef(x, y, z);
/*      */   }
/*      */   
/*      */   public static Color rainbow(int speed, int index, float saturation, float brightness, float opacity) {
/*  430 */     int angle = (int)((System.currentTimeMillis() / speed + index) % 360L);
/*  431 */     float hue = angle / 360.0F;
/*  432 */     Color color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
/*  433 */     return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, Math.min(255, (int)(opacity * 255.0F))));
/*      */   }
/*      */ 
/*      */   
/*      */   public static void scale(double x, double y, double z) {
/*  438 */     GL11.glScaled(x, y, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void translate(float x, float y, float z) {
/*  443 */     GL11.glTranslatef(x, y, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void translate(double x, double y, double z) {
/*  448 */     GL11.glTranslated(x, y, z);
/*      */   }
/*      */   
/*      */   public static void bindTexture(int texture) {
/*  452 */     GL11.glBindTexture(3553, texture);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void depthMask(boolean flagIn) {
/*  457 */     GL11.glDepthMask(flagIn);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableBlend() {
/*  462 */     GL11.glDisable(3042);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableBlend() {
/*  467 */     GL11.glEnable(3042);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableAlpha() {
/*  472 */     GL11.glDisable(3008);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableAlpha() {
/*  477 */     GL11.glEnable(3008);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableTexture2D() {
/*  482 */     GL11.glDisable(3553);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableTexture2D() {
/*  487 */     GL11.glEnable(3553);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void enableLighting() {
/*  492 */     GL11.glEnable(2896);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void disableLighting() {
/*  497 */     GL11.glDisable(2896);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void blendFunc(int srcFactor, int dstFactor) {
/*  502 */     GL11.glBlendFunc(srcFactor, dstFactor);
/*      */   }
/*      */   
/*      */   public static void setAlphaLimit(float limit) {
/*  506 */     GL11.glEnable(3008);
/*  507 */     GL11.glAlphaFunc(516, (float)(limit * 0.01D));
/*      */   }
/*      */   
/*      */   public static long getSystemTime() {
/*  511 */     return Sys.getTime() * 1000L / Sys.getTimerResolution();
/*      */   }
/*      */ 
/*      */   
/*      */   public static int clamp(int num, int min, int max) {
/*  516 */     return (num < min) ? min : Math.min(num, max);
/*      */   }
/*      */ 
/*      */   
/*      */   public static float clamp(float num, float min, float max) {
/*  521 */     return (num < min) ? min : Math.min(num, max);
/*      */   }
/*      */   
/*      */   public static int getRandomInRange(int min, int max) {
/*  525 */     return (int)(Math.random() * (max - min) + min);
/*      */   }
/*      */ 
/*      */   
/*      */   public static double clamp(double num, double min, double max) {
/*  530 */     return (num < min) ? min : Math.min(num, max);
/*      */   }
/*      */ 
/*      */   
/*      */   public static double getNormalDouble(double d, int numberAfterZopyataya) {
/*  535 */     return (new BigDecimal(d)).setScale(numberAfterZopyataya, RoundingMode.HALF_EVEN).doubleValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public static double getNormalDouble(double d) {
/*  540 */     return (new BigDecimal(d)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
/*      */   }
/*      */   
/*  543 */   public long last2DFrame = System.currentTimeMillis();
/*  544 */   public long last3DFrame = System.currentTimeMillis();
/*      */   
/*      */   public float delta2DFrameTime;
/*      */   public float delta3DFrameTime;
/*      */   
/*      */   public static void push() {
/*  550 */     GL11.glPushMatrix();
/*      */   }
/*      */   
/*      */   public static void pop() {
/*  554 */     GL11.glPopMatrix();
/*      */   }
/*      */   
/*      */   public static void enable(int glTarget) {
/*  558 */     GL11.glEnable(glTarget);
/*      */   }
/*      */   
/*      */   public static void disable(int glTarget) {
/*  562 */     GL11.glDisable(glTarget);
/*      */   }
/*      */   
/*      */   public static void start() {
/*  566 */     enable(3042);
/*  567 */     GL11.glBlendFunc(770, 771);
/*  568 */     disable(3553);
/*  569 */     disable(2884);
/*  570 */     disableAlpha();
/*  571 */     disable(2929);
/*      */   }
/*      */   
/*      */   public static void stop() {
/*  575 */     enableAlpha();
/*  576 */     enable(2929);
/*  577 */     enable(2884);
/*  578 */     enable(3553);
/*  579 */     disable(3042);
/*  580 */     color(Color.white);
/*      */   }
/*      */   
/*      */   public static void startSmooth() {
/*  584 */     enable(2881);
/*  585 */     enable(2848);
/*  586 */     enable(2832);
/*      */   }
/*      */   
/*      */   public static void endSmooth() {
/*  590 */     disable(2832);
/*  591 */     disable(2848);
/*  592 */     disable(2881);
/*      */   }
/*      */   
/*      */   public static void begin(int glMode) {
/*  596 */     GL11.glBegin(glMode);
/*      */   }
/*      */   
/*      */   public static void end() {
/*  600 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */   public static void vertex(double x, double y) {
/*  604 */     GL11.glVertex2d(x, y);
/*      */   }
/*      */   
/*      */   public static void translate(double x, double y) {
/*  608 */     GL11.glTranslated(x, y, 0.0D);
/*      */   }
/*      */   
/*      */   public static void scale(double x, double y) {
/*  612 */     GL11.glScaled(x, y, 1.0D);
/*      */   }
/*      */   
/*      */   public static void rotate(double x, double y, double z, double angle) {
/*  616 */     GL11.glRotated(angle, x, y, z);
/*      */   }
/*      */   
/*      */   public static void color(double red, double green, double blue, double alpha) {
/*  620 */     GL11.glColor4d(red, green, blue, alpha);
/*      */   }
/*      */   
/*      */   public static void color(double red, double green, double blue) {
/*  624 */     color(red, green, blue, 1.0D);
/*      */   }
/*      */   
/*      */   public static void color(Color color) {
/*  628 */     if (color == null)
/*  629 */       color = Color.white; 
/*  630 */     color((color.getRed() / 255.0F), (color.getGreen() / 255.0F), (color.getBlue() / 255.0F), (color.getAlpha() / 255.0F));
/*      */   }
/*      */   
/*      */   public static void color(Color color, int alpha) {
/*  634 */     if (color == null)
/*  635 */       color = Color.white; 
/*  636 */     color((color.getRed() / 255.0F), (color.getGreen() / 255.0F), (color.getBlue() / 255.0F), 0.5D);
/*      */   }
/*      */   
/*      */   public static void lineWidth(double width) {
/*  640 */     GL11.glLineWidth((float)width);
/*      */   }
/*      */   
/*      */   public static void rect(double x, double y, double width, double height, boolean filled, Color color) {
/*  644 */     start();
/*  645 */     if (color != null)
/*  646 */       color(color); 
/*  647 */     begin(filled ? 6 : 1);
/*      */ 
/*      */     
/*  650 */     vertex(x, y);
/*  651 */     vertex(x + width, y);
/*  652 */     vertex(x + width, y + height);
/*  653 */     vertex(x, y + height);
/*  654 */     if (!filled) {
/*  655 */       vertex(x, y);
/*  656 */       vertex(x, y + height);
/*  657 */       vertex(x + width, y);
/*  658 */       vertex(x + width, y + height);
/*      */     } 
/*      */     
/*  661 */     end();
/*  662 */     stop();
/*      */   }
/*      */   
/*      */   public static void rect(double x, double y, double width, double height, boolean filled) {
/*  666 */     rect(x, y, width, height, filled, null);
/*      */   }
/*      */   
/*      */   public static void rect(double x, double y, double width, double height, Color color) {
/*  670 */     rect(x, y, width, height, true, color);
/*      */   }
/*      */   
/*      */   public static void rect(double x, double y, double width, double height) {
/*  674 */     rect(x, y, width, height, true, null);
/*      */   }
/*      */   
/*      */   public static void rectCentered(double x, double y, double width, double height, boolean filled, Color color) {
/*  678 */     x -= width / 2.0D;
/*  679 */     y -= height / 2.0D;
/*  680 */     rect(x, y, width, height, filled, color);
/*      */   }
/*      */   
/*      */   public static void rectCentered(double x, double y, double width, double height, boolean filled) {
/*  684 */     x -= width / 2.0D;
/*  685 */     y -= height / 2.0D;
/*  686 */     rect(x, y, width, height, filled, null);
/*      */   }
/*      */   
/*      */   public static void rectCentered(double x, double y, double width, double height, Color color) {
/*  690 */     x -= width / 2.0D;
/*  691 */     y -= height / 2.0D;
/*  692 */     rect(x, y, width, height, true, color);
/*      */   }
/*      */   
/*      */   public static void rectCentered(double x, double y, double width, double height) {
/*  696 */     x -= width / 2.0D;
/*  697 */     y -= height / 2.0D;
/*  698 */     rect(x, y, width, height, true, null);
/*      */   }
/*      */   
/*      */   public static void gradient(double x, double y, double width, double height, boolean filled, Color color1, Color color2) {
/*  702 */     start();
/*  703 */     GL11.glShadeModel(7425);
/*  704 */     enableAlpha();
/*  705 */     GL11.glAlphaFunc(516, 0.0F);
/*  706 */     if (color1 != null)
/*  707 */       color(color1); 
/*  708 */     begin(filled ? 7 : 1);
/*      */     
/*  710 */     vertex(x, y);
/*  711 */     vertex(x + width, y);
/*  712 */     if (color2 != null)
/*  713 */       color(color2); 
/*  714 */     vertex(x + width, y + height);
/*  715 */     vertex(x, y + height);
/*  716 */     if (!filled) {
/*  717 */       vertex(x, y);
/*  718 */       vertex(x, y + height);
/*  719 */       vertex(x + width, y);
/*  720 */       vertex(x + width, y + height);
/*      */     } 
/*      */     
/*  723 */     end();
/*  724 */     GL11.glAlphaFunc(516, 0.1F);
/*  725 */     disableAlpha();
/*  726 */     GL11.glShadeModel(7424);
/*  727 */     stop();
/*      */   }
/*      */   
/*      */   public static void gradient(double x, double y, double width, double height, Color color1, Color color2) {
/*  731 */     gradient(x, y, width, height, true, color1, color2);
/*      */   }
/*      */   
/*      */   public static void gradientCentered(double x, double y, double width, double height, Color color1, Color color2) {
/*  735 */     x -= width / 2.0D;
/*  736 */     y -= height / 2.0D;
/*  737 */     gradient(x, y, width, height, true, color1, color2);
/*      */   }
/*      */   
/*      */   public static void gradientSideways(double x, double y, double width, double height, boolean filled, Color color1, Color color2) {
/*  741 */     start();
/*  742 */     GL11.glShadeModel(7425);
/*  743 */     disableAlpha();
/*  744 */     if (color1 != null)
/*  745 */       color(color1); 
/*  746 */     begin(filled ? 6 : 1);
/*      */     
/*  748 */     vertex(x, y);
/*  749 */     vertex(x, y + height);
/*  750 */     if (color2 != null)
/*  751 */       color(color2); 
/*  752 */     vertex(x + width, y + height);
/*  753 */     vertex(x + width, y);
/*      */     
/*  755 */     end();
/*  756 */     enableAlpha();
/*  757 */     GL11.glShadeModel(7424);
/*  758 */     stop();
/*      */   }
/*      */   
/*      */   public static void gradientSideways(double x, double y, double width, double height, Color color1, Color color2) {
/*  762 */     gradientSideways(x, y, width, height, true, color1, color2);
/*      */   }
/*      */   
/*      */   public static void gradientSidewaysCentered(double x, double y, double width, double height, Color color1, Color color2) {
/*  766 */     x -= width / 2.0D;
/*  767 */     y -= height / 2.0D;
/*  768 */     gradientSideways(x, y, width, height, true, color1, color2);
/*      */   }
/*      */   
/*      */   public static void polygon(double x, double y, double sideLength, double amountOfSides, boolean filled, Color color) {
/*  772 */     sideLength /= 2.0D;
/*  773 */     start();
/*  774 */     if (color != null)
/*  775 */       color(color); 
/*  776 */     if (!filled) GL11.glLineWidth(2.0F); 
/*  777 */     GL11.glEnable(2848);
/*  778 */     begin(filled ? 6 : 3);
/*      */     
/*  780 */     for (double i = 0.0D; i <= amountOfSides / 4.0D; i++) {
/*  781 */       double angle = i * 4.0D * 6.283185307179586D / 360.0D;
/*  782 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + sideLength * Math.sin(angle) + sideLength);
/*      */     } 
/*      */     
/*  785 */     end();
/*  786 */     GL11.glDisable(2848);
/*  787 */     stop();
/*      */   }
/*      */   
/*      */   public static void polygon(double x, double y, double sideLength, int amountOfSides, boolean filled) {
/*  791 */     polygon(x, y, sideLength, amountOfSides, filled, null);
/*      */   }
/*      */   
/*      */   public static void polygon(double x, double y, double sideLength, int amountOfSides, Color color) {
/*  795 */     polygon(x, y, sideLength, amountOfSides, true, color);
/*      */   }
/*      */   
/*      */   public static void polygon(double x, double y, double sideLength, int amountOfSides) {
/*  799 */     polygon(x, y, sideLength, amountOfSides, true, null);
/*      */   }
/*      */   
/*      */   public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, boolean filled, Color color) {
/*  803 */     x -= sideLength / 2.0D;
/*  804 */     y -= sideLength / 2.0D;
/*  805 */     polygon(x, y, sideLength, amountOfSides, filled, color);
/*      */   }
/*      */   
/*      */   public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, boolean filled) {
/*  809 */     x -= sideLength / 2.0D;
/*  810 */     y -= sideLength / 2.0D;
/*  811 */     polygon(x, y, sideLength, amountOfSides, filled, null);
/*      */   }
/*      */   
/*      */   public static void polygonCentered(double x, double y, double sideLength, int amountOfSides, Color color) {
/*  815 */     x -= sideLength / 2.0D;
/*  816 */     y -= sideLength / 2.0D;
/*  817 */     polygon(x, y, sideLength, amountOfSides, true, color);
/*      */   }
/*      */   
/*      */   public static void polygonCentered(double x, double y, double sideLength, int amountOfSides) {
/*  821 */     x -= sideLength / 2.0D;
/*  822 */     y -= sideLength / 2.0D;
/*  823 */     polygon(x, y, sideLength, amountOfSides, true, null);
/*      */   }
/*      */   
/*      */   public static void circle(double x, double y, double radius, boolean filled, Color color) {
/*  827 */     polygon(x, y, radius, 360.0D, filled, color);
/*      */   }
/*      */   
/*      */   public static void circle(double x, double y, double radius, boolean filled) {
/*  831 */     polygon(x, y, radius, 360, filled);
/*      */   }
/*      */   
/*      */   public static void circle(double x, double y, double radius, Color color) {
/*  835 */     polygon(x, y, radius, 360, color);
/*      */   }
/*      */   
/*      */   public static void circle(double x, double y, double radius) {
/*  839 */     polygon(x, y, radius, 360);
/*      */   }
/*      */   
/*      */   public static void circleCentered(double x, double y, double radius, boolean filled, Color color) {
/*  843 */     x -= radius / 2.0D;
/*  844 */     y -= radius / 2.0D;
/*  845 */     polygon(x, y, radius, 360.0D, filled, color);
/*      */   }
/*      */   
/*      */   public static void circleCentered(double x, double y, double radius, boolean filled) {
/*  849 */     x -= radius / 2.0D;
/*  850 */     y -= radius / 2.0D;
/*  851 */     polygon(x, y, radius, 360, filled);
/*      */   }
/*      */   
/*      */   public static void circleCentered(double x, double y, double radius, boolean filled, int sides) {
/*  855 */     x -= radius / 2.0D;
/*  856 */     y -= radius / 2.0D;
/*  857 */     polygon(x, y, radius, sides, filled);
/*      */   }
/*      */   
/*      */   public static void circleCentered(double x, double y, double radius, Color color) {
/*  861 */     x -= radius / 2.0D;
/*  862 */     y -= radius / 2.0D;
/*  863 */     polygon(x, y, radius, 360, color);
/*      */   }
/*      */   
/*      */   public static void circleCentered(double x, double y, double radius) {
/*  867 */     x -= radius / 2.0D;
/*  868 */     y -= radius / 2.0D;
/*  869 */     polygon(x, y, radius, 360);
/*      */   }
/*      */   
/*      */   public static void triangle(double x, double y, double sideLength, boolean filled, Color color) {
/*  873 */     polygon(x, y, sideLength, 3.0D, filled, color);
/*      */   }
/*      */   
/*      */   public static void triangle(double x, double y, double sideLength, boolean filled) {
/*  877 */     polygon(x, y, sideLength, 3, filled);
/*      */   }
/*      */   
/*      */   public static void triangle(double x, double y, double sideLength, Color color) {
/*  881 */     polygon(x, y, sideLength, 3, color);
/*      */   }
/*      */   
/*      */   public static void triangle(double x, double y, double sideLength) {
/*  885 */     polygon(x, y, sideLength, 3);
/*      */   }
/*      */   
/*      */   public static void triangleCentered(double x, double y, double sideLength, boolean filled, Color color) {
/*  889 */     x -= sideLength / 2.0D;
/*  890 */     y -= sideLength / 2.0D;
/*  891 */     polygon(x, y, sideLength, 3.0D, filled, color);
/*      */   }
/*      */   
/*      */   public static void triangleCentered(double x, double y, double sideLength, boolean filled) {
/*  895 */     x -= sideLength / 2.0D;
/*  896 */     y -= sideLength / 2.0D;
/*  897 */     polygon(x, y, sideLength, 3, filled);
/*      */   }
/*      */   
/*      */   public static void triangleCentered(double x, double y, double sideLength, Color color) {
/*  901 */     x -= sideLength / 2.0D;
/*  902 */     y -= sideLength / 2.0D;
/*  903 */     polygon(x, y, sideLength, 3, color);
/*      */   }
/*      */   
/*      */   public static void triangleCentered(double x, double y, double sideLength) {
/*  907 */     x -= sideLength / 2.0D;
/*  908 */     y -= sideLength / 2.0D;
/*  909 */     polygon(x, y, sideLength, 3);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void lineNoGl(double firstX, double firstY, double secondX, double secondY, Color color) {
/*  914 */     start();
/*  915 */     if (color != null)
/*  916 */       color(color); 
/*  917 */     lineWidth(1.0F);
/*  918 */     GL11.glEnable(2848);
/*  919 */     begin(1);
/*      */     
/*  921 */     vertex(firstX, firstY);
/*  922 */     vertex(secondX, secondY);
/*      */     
/*  924 */     end();
/*  925 */     GL11.glDisable(2848);
/*  926 */     stop();
/*      */   }
/*      */   
/*      */   public static void line(double firstX, double firstY, double secondX, double secondY, double lineWidth, Color color) {
/*  930 */     start();
/*  931 */     if (color != null)
/*  932 */       color(color); 
/*  933 */     lineWidth(lineWidth);
/*  934 */     GL11.glEnable(2848);
/*  935 */     begin(1);
/*      */     
/*  937 */     vertex(firstX, firstY);
/*  938 */     vertex(secondX, secondY);
/*      */     
/*  940 */     end();
/*  941 */     GL11.glDisable(2848);
/*  942 */     stop();
/*      */   }
/*      */   
/*      */   public static void line(double firstX, double firstY, double secondX, double secondY, double lineWidth) {
/*  946 */     line(firstX, firstY, secondX, secondY, lineWidth, null);
/*      */   }
/*      */   
/*      */   public static void line(double firstX, double firstY, double secondX, double secondY, Color color) {
/*  950 */     line(firstX, firstY, secondX, secondY, 0.0D, color);
/*      */   }
/*      */   
/*      */   public static void line(double firstX, double firstY, double secondX, double secondY) {
/*  954 */     line(firstX, firstY, secondX, secondY, 0.0D, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void outlineInlinedGradientRect(double x, double y, double width, double height, double inlineOffset, Color color1, Color color2) {
/*  961 */     gradient(x, y, width, inlineOffset, color1, color2);
/*  962 */     gradient(x, y + height - inlineOffset, width, inlineOffset, color2, color1);
/*  963 */     gradientSideways(x, y, inlineOffset, height, color1, color2);
/*  964 */     gradientSideways(x + width - inlineOffset, y, inlineOffset, height, color2, color1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void roundedRect(double x, double y, double width, double height, double edgeRadius, Color color) {
/*  972 */     double halfRadius = edgeRadius / 2.0D;
/*  973 */     width -= halfRadius;
/*  974 */     height -= halfRadius;
/*      */     
/*  976 */     float sideLength = (float)edgeRadius;
/*  977 */     sideLength /= 2.0F;
/*  978 */     start();
/*  979 */     if (color != null)
/*  980 */       color(color); 
/*  981 */     begin(6);
/*      */     
/*      */     double i;
/*  984 */     for (i = 180.0D; i <= 270.0D; i++) {
/*  985 */       double angle = i * 6.283185307179586D / 360.0D;
/*  986 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + sideLength * Math.sin(angle) + sideLength);
/*      */     } 
/*  988 */     vertex(x + sideLength, y + sideLength);
/*      */ 
/*      */     
/*  991 */     end();
/*  992 */     stop();
/*      */     
/*  994 */     sideLength = (float)edgeRadius;
/*  995 */     sideLength /= 2.0F;
/*  996 */     start();
/*  997 */     if (color != null)
/*  998 */       color(color); 
/*  999 */     GL11.glEnable(2848);
/* 1000 */     begin(6);
/*      */ 
/*      */     
/* 1003 */     for (i = 0.0D; i <= 90.0D; i++) {
/* 1004 */       double angle = i * 6.283185307179586D / 360.0D;
/* 1005 */       vertex(x + width + sideLength * Math.cos(angle), y + height + sideLength * Math.sin(angle));
/*      */     } 
/* 1007 */     vertex(x + width, y + height);
/*      */ 
/*      */     
/* 1010 */     end();
/* 1011 */     GL11.glDisable(2848);
/* 1012 */     stop();
/*      */     
/* 1014 */     sideLength = (float)edgeRadius;
/* 1015 */     sideLength /= 2.0F;
/* 1016 */     start();
/* 1017 */     if (color != null)
/* 1018 */       color(color); 
/* 1019 */     GL11.glEnable(2848);
/* 1020 */     begin(6);
/*      */ 
/*      */     
/* 1023 */     for (i = 270.0D; i <= 360.0D; i++) {
/* 1024 */       double angle = i * 6.283185307179586D / 360.0D;
/* 1025 */       vertex(x + width + sideLength * Math.cos(angle), y + sideLength * Math.sin(angle) + sideLength);
/*      */     } 
/* 1027 */     vertex(x + width, y + sideLength);
/*      */ 
/*      */     
/* 1030 */     end();
/* 1031 */     GL11.glDisable(2848);
/* 1032 */     stop();
/*      */     
/* 1034 */     sideLength = (float)edgeRadius;
/* 1035 */     sideLength /= 2.0F;
/* 1036 */     start();
/* 1037 */     if (color != null)
/* 1038 */       color(color); 
/* 1039 */     GL11.glEnable(2848);
/* 1040 */     begin(6);
/*      */ 
/*      */     
/* 1043 */     for (i = 90.0D; i <= 180.0D; i++) {
/* 1044 */       double angle = i * 6.283185307179586D / 360.0D;
/* 1045 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + height + sideLength * Math.sin(angle));
/*      */     } 
/* 1047 */     vertex(x + sideLength, y + height);
/*      */ 
/*      */     
/* 1050 */     end();
/* 1051 */     GL11.glDisable(2848);
/* 1052 */     stop();
/*      */ 
/*      */     
/* 1055 */     rect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);
/*      */ 
/*      */     
/* 1058 */     rect(x, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
/* 1059 */     rect(x + width, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
/*      */ 
/*      */     
/* 1062 */     rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
/* 1063 */     rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
/*      */   }
/*      */   
/*      */   public static void roundedOutLine(double x, double y, double width, double height, double thickness, double edgeRadius, Color color) {
/* 1067 */     double halfRadius = edgeRadius / 2.0D;
/* 1068 */     width -= halfRadius;
/* 1069 */     height -= halfRadius;
/*      */     
/* 1071 */     float sideLength = (float)edgeRadius;
/* 1072 */     sideLength /= 2.0F;
/* 1073 */     start();
/* 1074 */     if (color != null)
/* 1075 */       color(color); 
/* 1076 */     GL11.glEnable(2848);
/* 1077 */     begin(1);
/*      */     
/*      */     double i;
/* 1080 */     for (i = 180.0D; i <= 270.0D; i++) {
/* 1081 */       double angle = i * 6.283185307179586D / 360.0D;
/* 1082 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + sideLength * Math.sin(angle) + sideLength);
/*      */     } 
/* 1084 */     vertex(x + sideLength, y + sideLength);
/*      */ 
/*      */     
/* 1087 */     end();
/* 1088 */     stop();
/*      */     
/* 1090 */     sideLength = (float)edgeRadius;
/* 1091 */     sideLength /= 2.0F;
/* 1092 */     start();
/* 1093 */     if (color != null)
/* 1094 */       color(color); 
/* 1095 */     GL11.glEnable(2848);
/* 1096 */     begin(1);
/*      */ 
/*      */     
/* 1099 */     for (i = 0.0D; i <= 90.0D; i++) {
/* 1100 */       double angle = i * 6.283185307179586D / 360.0D;
/* 1101 */       vertex(x + width + sideLength * Math.cos(angle), y + height + sideLength * Math.sin(angle));
/*      */     } 
/* 1103 */     vertex(x + width, y + height);
/*      */ 
/*      */     
/* 1106 */     end();
/* 1107 */     GL11.glDisable(2848);
/* 1108 */     stop();
/*      */     
/* 1110 */     sideLength = (float)edgeRadius;
/* 1111 */     sideLength /= 2.0F;
/* 1112 */     start();
/* 1113 */     if (color != null)
/* 1114 */       color(color); 
/* 1115 */     GL11.glEnable(2848);
/* 1116 */     begin(1);
/*      */ 
/*      */     
/* 1119 */     for (i = 270.0D; i <= 360.0D; i++) {
/* 1120 */       double angle = i * 6.283185307179586D / 360.0D;
/* 1121 */       vertex(x + width + sideLength * Math.cos(angle), y + sideLength * Math.sin(angle) + sideLength);
/*      */     } 
/* 1123 */     vertex(x + width, y + sideLength);
/*      */ 
/*      */     
/* 1126 */     end();
/* 1127 */     GL11.glDisable(2848);
/* 1128 */     stop();
/*      */     
/* 1130 */     sideLength = (float)edgeRadius;
/* 1131 */     sideLength /= 2.0F;
/* 1132 */     start();
/* 1133 */     if (color != null)
/* 1134 */       color(color); 
/* 1135 */     GL11.glEnable(2848);
/* 1136 */     begin(1);
/*      */ 
/*      */     
/* 1139 */     for (i = 90.0D; i <= 180.0D; i++) {
/* 1140 */       double angle = i * 6.283185307179586D / 360.0D;
/* 1141 */       vertex(x + sideLength * Math.cos(angle) + sideLength, y + height + sideLength * Math.sin(angle));
/*      */     } 
/* 1143 */     vertex(x + sideLength, y + height);
/*      */ 
/*      */     
/* 1146 */     end();
/* 1147 */     GL11.glDisable(2848);
/* 1148 */     stop();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void roundedRectCustom(double x, double y, double width, double height, double edgeRadius, Color color, boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
/* 1162 */     double halfRadius = edgeRadius / 2.0D;
/* 1163 */     width -= halfRadius;
/* 1164 */     height -= halfRadius;
/*      */     
/* 1166 */     float sideLength = (float)edgeRadius;
/* 1167 */     sideLength /= 2.0F;
/* 1168 */     start();
/* 1169 */     if (color != null) {
/* 1170 */       color(color);
/*      */     }
/* 1172 */     if (topLeft) {
/*      */       
/* 1174 */       GL11.glEnable(2848);
/* 1175 */       begin(6);
/*      */       
/* 1177 */       for (double i = 180.0D; i <= 270.0D; i++) {
/* 1178 */         double angle = i * 6.283185307179586D / 360.0D;
/* 1179 */         vertex(x + sideLength * Math.cos(angle) + sideLength, y + sideLength * Math.sin(angle) + sideLength);
/*      */       } 
/* 1181 */       vertex(x + sideLength, y + sideLength);
/*      */ 
/*      */       
/* 1184 */       end();
/* 1185 */       GL11.glDisable(2848);
/* 1186 */       stop();
/*      */     }
/*      */     else {
/*      */       
/* 1190 */       rect(x, y, sideLength, sideLength, color);
/*      */     } 
/*      */ 
/*      */     
/* 1194 */     sideLength = (float)edgeRadius;
/* 1195 */     sideLength /= 2.0F;
/* 1196 */     start();
/* 1197 */     if (color != null) {
/* 1198 */       color(color);
/*      */     }
/*      */     
/* 1201 */     if (bottomRight) {
/* 1202 */       GL11.glEnable(2848);
/* 1203 */       begin(6);
/* 1204 */       for (double i = 0.0D; i <= 90.0D; i++) {
/* 1205 */         double angle = i * 6.283185307179586D / 360.0D;
/* 1206 */         vertex(x + width + sideLength * Math.cos(angle), y + height + sideLength * Math.sin(angle));
/*      */       } 
/* 1208 */       vertex(x + width, y + height);
/* 1209 */       end();
/* 1210 */       GL11.glDisable(2848);
/* 1211 */       stop();
/*      */     } else {
/* 1213 */       rect(x + width, y + height, sideLength, sideLength, color);
/*      */     } 
/*      */ 
/*      */     
/* 1217 */     sideLength = (float)edgeRadius;
/* 1218 */     sideLength /= 2.0F;
/* 1219 */     start();
/* 1220 */     if (color != null) {
/* 1221 */       color(color);
/*      */     }
/*      */     
/* 1224 */     if (topRight) {
/* 1225 */       GL11.glEnable(2848);
/* 1226 */       begin(6);
/* 1227 */       for (double i = 270.0D; i <= 360.0D; i++) {
/* 1228 */         double angle = i * 6.283185307179586D / 360.0D;
/* 1229 */         vertex(x + width + sideLength * Math.cos(angle), y + sideLength * Math.sin(angle) + sideLength);
/*      */       } 
/* 1231 */       vertex(x + width, y + sideLength);
/* 1232 */       end();
/* 1233 */       GL11.glDisable(2848);
/* 1234 */       stop();
/*      */     } else {
/* 1236 */       rect(x + width, y, sideLength, sideLength, color);
/*      */     } 
/*      */ 
/*      */     
/* 1240 */     sideLength = (float)edgeRadius;
/* 1241 */     sideLength /= 2.0F;
/* 1242 */     start();
/* 1243 */     if (color != null) {
/* 1244 */       color(color);
/*      */     }
/*      */     
/* 1247 */     if (bottomLeft) {
/* 1248 */       GL11.glEnable(2848);
/* 1249 */       begin(6);
/* 1250 */       for (double i = 90.0D; i <= 180.0D; i++) {
/* 1251 */         double angle = i * 6.283185307179586D / 360.0D;
/* 1252 */         vertex(x + sideLength * Math.cos(angle) + sideLength, y + height + sideLength * Math.sin(angle));
/*      */       } 
/* 1254 */       vertex(x + sideLength, y + height);
/* 1255 */       end();
/* 1256 */       GL11.glDisable(2848);
/* 1257 */       stop();
/*      */     } else {
/* 1259 */       rect(x, y + height, sideLength, sideLength, color);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1264 */     rect(x + halfRadius, y + halfRadius, width - halfRadius, height - halfRadius, color);
/*      */ 
/*      */     
/* 1267 */     rect(x, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
/* 1268 */     rect(x + width, y + halfRadius, edgeRadius / 2.0D, height - halfRadius, color);
/*      */ 
/*      */     
/* 1271 */     rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
/* 1272 */     rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
/*      */   }
/*      */   
/*      */   public static void roundedRectTop(double x, double y, double width, double height, double edgeRadius, Color color) {
/* 1276 */     double halfRadius = edgeRadius / 2.0D;
/* 1277 */     width -= halfRadius;
/* 1278 */     height -= halfRadius;
/*      */ 
/*      */     
/* 1281 */     circle(x, y, edgeRadius, color);
/* 1282 */     circle(x + width - edgeRadius / 2.0D, y, edgeRadius, color);
/*      */     
/* 1284 */     rect(x, y + halfRadius, width + halfRadius, height, color);
/*      */ 
/*      */     
/* 1287 */     rect(x + halfRadius, y, width - halfRadius, halfRadius, color);
/*      */   }
/*      */   
/*      */   public static void roundedRectBottom(double x, double y, double width, double height, double edgeRadius, Color color) {
/* 1291 */     double halfRadius = edgeRadius / 2.0D;
/* 1292 */     width -= halfRadius;
/* 1293 */     height -= halfRadius;
/*      */ 
/*      */     
/* 1296 */     circle(x + width - edgeRadius / 2.0D, y + height - edgeRadius / 2.0D, edgeRadius, color);
/* 1297 */     circle(x, y + height - edgeRadius / 2.0D, edgeRadius, color);
/*      */ 
/*      */     
/* 1300 */     rect(x, y, width + halfRadius, height, color);
/*      */ 
/*      */     
/* 1303 */     rect(x + halfRadius, y + height, width - halfRadius, halfRadius, color);
/*      */   }
/*      */   
/*      */   public static void roundedRectRight(double x, double y, double width, double height, double edgeRadius, Color color1, Color color2) {
/* 1307 */     double halfRadius = edgeRadius / 2.0D;
/* 1308 */     width -= halfRadius;
/* 1309 */     height -= halfRadius;
/*      */ 
/*      */     
/* 1312 */     circle(x + width - edgeRadius / 2.0D, y, edgeRadius, color2);
/* 1313 */     circle(x + width - edgeRadius / 2.0D, y + height - edgeRadius / 2.0D, edgeRadius, color2);
/*      */ 
/*      */     
/* 1316 */     gradientSideways(x, y, width, height + halfRadius, color1, color2);
/*      */ 
/*      */     
/* 1319 */     rect(x + width, y + halfRadius, 5.0D, height - halfRadius, color2);
/*      */   }
/*      */   
/*      */   public static void roundedRectRightTop(double x, double y, double width, double height, double edgeRadius, Color color1, Color color2) {
/* 1323 */     double halfRadius = edgeRadius / 2.0D;
/* 1324 */     width -= halfRadius;
/* 1325 */     height -= halfRadius;
/*      */ 
/*      */     
/* 1328 */     circle(x + width - edgeRadius / 2.0D, y, edgeRadius, color2);
/*      */ 
/*      */     
/* 1331 */     gradientSideways(x, y, width, height + halfRadius, color1, color2);
/*      */ 
/*      */     
/* 1334 */     rect(x + width, y + halfRadius, 5.0D, height, color2);
/*      */   }
/*      */   
/*      */   public static void roundedRectRightBottom(double x, double y, double width, double height, double edgeRadius, Color color1, Color color2) {
/* 1338 */     double halfRadius = edgeRadius / 2.0D;
/* 1339 */     width -= halfRadius;
/* 1340 */     height -= halfRadius;
/*      */ 
/*      */     
/* 1343 */     circle(x + width - edgeRadius / 2.0D, y + height - edgeRadius / 2.0D, edgeRadius, color2);
/*      */ 
/*      */     
/* 1346 */     gradientSideways(x, y, width, height + halfRadius, color1, color2);
/*      */     
/* 1348 */     rect(x + width, y, 5.0D, height, color2);
/*      */   }
/*      */   
/*      */   public static void drawBorder(float x, float y, float x2, float y2, float width, int color1) {
/* 1352 */     GL11.glEnable(3042);
/* 1353 */     GL11.glDisable(3553);
/* 1354 */     GL11.glBlendFunc(770, 771);
/* 1355 */     GL11.glEnable(2848);
/*      */     
/* 1357 */     color(new Color(color1));
/* 1358 */     GL11.glLineWidth(width);
/*      */     
/* 1360 */     glBegin(2);
/*      */     
/* 1362 */     GL11.glVertex2d(x2, y);
/* 1363 */     GL11.glVertex2d(x, y);
/* 1364 */     GL11.glVertex2d(x, y2);
/* 1365 */     GL11.glVertex2d(x2, y2);
/*      */     
/* 1367 */     glEnd();
/*      */     
/* 1369 */     GL11.glEnable(3553);
/* 1370 */     GL11.glDisable(3042);
/* 1371 */     GL11.glDisable(2848);
/*      */   }
/*      */   
/*      */   public static void drawTracerLine(double x, double y, double z, float red, float green, float blue, float alpha, float lineWdith) {
/* 1375 */     GL11.glPushMatrix();
/* 1376 */     GL11.glEnable(3042);
/* 1377 */     GL11.glEnable(2848);
/* 1378 */     GL11.glDisable(2929);
/* 1379 */     GL11.glDisable(3553);
/* 1380 */     GL11.glBlendFunc(770, 771);
/* 1381 */     GL11.glEnable(3042);
/* 1382 */     GL11.glLineWidth(lineWdith);
/* 1383 */     GL11.glColor4f(red, green, blue, alpha);
/* 1384 */     GL11.glBegin(2);
/* 1385 */     GL11.glVertex3d(0.0D, 1.6200000047683716D, 0.0D);
/* 1386 */     GL11.glVertex3d(x, y, z);
/* 1387 */     GL11.glEnd();
/* 1388 */     GL11.glDisable(3042);
/* 1389 */     GL11.glEnable(3553);
/* 1390 */     GL11.glEnable(2929);
/* 1391 */     GL11.glDisable(2848);
/* 1392 */     GL11.glDisable(3042);
/* 1393 */     GL11.glPopMatrix();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void color4f(float red, float green, float blue, float alpha) {
/* 1400 */     GL11.glColor4f(red, green, blue, alpha);
/*      */   }
/*      */   
/*      */   public static void lineWidth(float width) {
/* 1404 */     GL11.glLineWidth(width);
/*      */   }
/*      */   
/*      */   public static void glBegin(int mode) {
/* 1408 */     GL11.glBegin(mode);
/*      */   }
/*      */   
/*      */   public static void glEnd() {
/* 1412 */     GL11.glEnd();
/*      */   }
/*      */   
/*      */   public static void putVertex3d(double x, double y, double z) {
/* 1416 */     GL11.glVertex3d(x, y, z);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawCircle(int x, int y, double r, float f1, float f2, float f3, float f) {
/* 1421 */     GL11.glEnable(3042);
/* 1422 */     GL11.glDisable(3553);
/* 1423 */     GL11.glEnable(2848);
/* 1424 */     GL11.glBlendFunc(770, 771);
/* 1425 */     GL11.glColor4f(f1, f2, f3, f);
/* 1426 */     GL11.glBegin(2);
/*      */     
/* 1428 */     for (int i = 0; i <= 360; i++) {
/* 1429 */       double x2 = Math.sin(i * Math.PI / 180.0D) * r;
/* 1430 */       double y2 = Math.cos(i * Math.PI / 180.0D) * r;
/* 1431 */       GL11.glVertex2d(x + x2, y + y2);
/*      */     } 
/*      */     
/* 1434 */     GL11.glEnd();
/* 1435 */     GL11.glDisable(2848);
/* 1436 */     GL11.glEnable(3553);
/* 1437 */     GL11.glDisable(3042);
/*      */   }
/*      */   
/*      */   public static void drawFilledCircle(int x, int y, double r, int c) {
/* 1441 */     float f = (c >> 24 & 0xFF) / 255.0F;
/* 1442 */     float f1 = (c >> 16 & 0xFF) / 255.0F;
/* 1443 */     float f2 = (c >> 8 & 0xFF) / 255.0F;
/* 1444 */     float f3 = (c & 0xFF) / 255.0F;
/* 1445 */     GL11.glEnable(3042);
/* 1446 */     GL11.glDisable(3553);
/* 1447 */     GL11.glEnable(2848);
/* 1448 */     GL11.glBlendFunc(770, 771);
/* 1449 */     GL11.glColor4f(f1, f2, f3, f);
/* 1450 */     GL11.glBegin(6);
/*      */     
/* 1452 */     for (int i = 0; i <= 360; i++) {
/* 1453 */       double x2 = Math.sin(i * Math.PI / 180.0D) * r;
/* 1454 */       double y2 = Math.cos(i * Math.PI / 180.0D) * r;
/* 1455 */       GL11.glVertex2d(x + x2, y + y2);
/*      */     } 
/*      */     
/* 1458 */     GL11.glEnd();
/* 1459 */     GL11.glDisable(2848);
/* 1460 */     GL11.glEnable(3553);
/* 1461 */     GL11.glDisable(3042);
/*      */   }
/*      */   
/*      */   public static void drawFilledCircle(int x, int y, double r, int c, int quality) {
/* 1465 */     float f = (c >> 24 & 0xFF) / 255.0F;
/* 1466 */     float f1 = (c >> 16 & 0xFF) / 255.0F;
/* 1467 */     float f2 = (c >> 8 & 0xFF) / 255.0F;
/* 1468 */     float f3 = (c & 0xFF) / 255.0F;
/* 1469 */     GL11.glEnable(3042);
/* 1470 */     GL11.glDisable(3553);
/* 1471 */     GL11.glEnable(2848);
/* 1472 */     GL11.glBlendFunc(770, 771);
/* 1473 */     GL11.glColor4f(f1, f2, f3, f);
/* 1474 */     GL11.glBegin(6);
/*      */     
/* 1476 */     for (int i = 0; i <= 360 / quality; i++) {
/* 1477 */       double x2 = Math.sin((i * quality) * Math.PI / 180.0D) * r;
/* 1478 */       double y2 = Math.cos((i * quality) * Math.PI / 180.0D) * r;
/* 1479 */       GL11.glVertex2d(x + x2, y + y2);
/*      */     } 
/*      */     
/* 1482 */     GL11.glEnd();
/* 1483 */     GL11.glDisable(2848);
/* 1484 */     GL11.glEnable(3553);
/* 1485 */     GL11.glDisable(3042);
/*      */   }
/*      */   
/*      */   public static void drawFilledCircle(double x, double y, double r, int c, int quality) {
/* 1489 */     float f = (c >> 24 & 0xFF) / 255.0F;
/* 1490 */     float f1 = (c >> 16 & 0xFF) / 255.0F;
/* 1491 */     float f2 = (c >> 8 & 0xFF) / 255.0F;
/* 1492 */     float f3 = (c & 0xFF) / 255.0F;
/* 1493 */     GL11.glEnable(3042);
/* 1494 */     GL11.glDisable(3553);
/* 1495 */     GL11.glEnable(2848);
/* 1496 */     GL11.glBlendFunc(770, 771);
/* 1497 */     GL11.glColor4f(f1, f2, f3, f);
/* 1498 */     GL11.glBegin(6);
/*      */     
/* 1500 */     for (int i = 0; i <= 360 / quality; i++) {
/* 1501 */       double x2 = Math.sin((i * quality) * Math.PI / 180.0D) * r;
/* 1502 */       double y2 = Math.cos((i * quality) * Math.PI / 180.0D) * r;
/* 1503 */       GL11.glVertex2d(x + x2, y + y2);
/*      */     } 
/*      */     
/* 1506 */     GL11.glEnd();
/* 1507 */     GL11.glDisable(2848);
/* 1508 */     GL11.glEnable(3553);
/* 1509 */     GL11.glDisable(3042);
/*      */   }
/*      */   
/*      */   public static void drawFilledCircleNoGL(int x, int y, double r, int c) {
/* 1513 */     float f = (c >> 24 & 0xFF) / 255.0F;
/* 1514 */     float f1 = (c >> 16 & 0xFF) / 255.0F;
/* 1515 */     float f2 = (c >> 8 & 0xFF) / 255.0F;
/* 1516 */     float f3 = (c & 0xFF) / 255.0F;
/*      */     
/* 1518 */     GL11.glColor4f(f1, f2, f3, f);
/* 1519 */     GL11.glBegin(6);
/*      */     
/* 1521 */     for (int i = 0; i <= 18; i++) {
/* 1522 */       double x2 = Math.sin((i * 20) * Math.PI / 180.0D) * r;
/* 1523 */       double y2 = Math.cos((i * 20) * Math.PI / 180.0D) * r;
/* 1524 */       GL11.glVertex2d(x + x2, y + y2);
/*      */     } 
/*      */     
/* 1527 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void drawFilledCircleNoGL(int x, int y, double r, int c, int quality) {
/* 1532 */     float f = (c >> 24 & 0xFF) / 255.0F;
/* 1533 */     float f1 = (c >> 16 & 0xFF) / 255.0F;
/* 1534 */     float f2 = (c >> 8 & 0xFF) / 255.0F;
/* 1535 */     float f3 = (c & 0xFF) / 255.0F;
/*      */     
/* 1537 */     GL11.glColor4f(f1, f2, f3, f);
/* 1538 */     GL11.glBegin(6);
/*      */     
/* 1540 */     for (int i = 0; i <= 360 / quality; i++) {
/* 1541 */       double x2 = Math.sin((i * quality) * Math.PI / 180.0D) * r;
/* 1542 */       double y2 = Math.cos((i * quality) * Math.PI / 180.0D) * r;
/* 1543 */       GL11.glVertex2d(x + x2, y + y2);
/*      */     } 
/*      */     
/* 1546 */     GL11.glEnd();
/*      */   }
/*      */ 
/*      */   
/*      */   public static void quickDrawRect(float x, float y, float x2, float y2, int color) {
/* 1551 */     glColor(color);
/* 1552 */     glBegin(7);
/*      */     
/* 1554 */     GL11.glVertex2d(x2, y);
/* 1555 */     GL11.glVertex2d(x, y);
/* 1556 */     GL11.glVertex2d(x, y2);
/* 1557 */     GL11.glVertex2d(x2, y2);
/*      */     
/* 1559 */     glEnd();
/*      */   }
/*      */   
/*      */   public static void quickDrawBorderedRect(float x, float y, float x2, float y2, float width, int color1, int color2) {
/* 1563 */     quickDrawRect(x, y, x2, y2, color2);
/*      */     
/* 1565 */     glColor(color1);
/* 1566 */     GL11.glLineWidth(width);
/*      */     
/* 1568 */     glBegin(2);
/*      */     
/* 1570 */     GL11.glVertex2d(x2, y);
/* 1571 */     GL11.glVertex2d(x, y);
/* 1572 */     GL11.glVertex2d(x, y2);
/* 1573 */     GL11.glVertex2d(x2, y2);
/*      */     
/* 1575 */     glEnd();
/*      */   }
/*      */   
/*      */   private static void glColor(int hex) {
/* 1579 */     float alpha = (hex >> 24 & 0xFF) / 255.0F;
/* 1580 */     float red = (hex >> 16 & 0xFF) / 255.0F;
/* 1581 */     float green = (hex >> 8 & 0xFF) / 255.0F;
/* 1582 */     float blue = (hex & 0xFF) / 255.0F;
/*      */     
/* 1584 */     color(red, green, blue, alpha);
/*      */   }
/*      */   
/*      */   public static void scissor(double x, double y, double width, double height) {
/* 1588 */     jF sr = new jF(Client.mc);
/* 1589 */     double scale = sr.c();
/*      */     
/* 1591 */     y = sr.b() - y;
/*      */     
/* 1593 */     x *= scale;
/* 1594 */     y *= scale;
/* 1595 */     width *= scale;
/* 1596 */     height *= scale;
/*      */     
/* 1598 */     GL11.glScissor((int)x, (int)(y - height), (int)width, (int)height);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void rainbowRectangle(float x, float y, float width, float height, float divider) {
/* 1603 */     for (int i = 0; i <= width; i++) {
/* 1604 */       rect((x + i), y, 1.0D, height, new Color(getColor(i / divider, 0.7F, 1.0F)));
/*      */     }
/*      */   }
/*      */   
/*      */   public static int getColor(float hueoffset, float saturation, float brightness) {
/* 1609 */     float speed = 4500.0F;
/* 1610 */     float hue = (float)(System.currentTimeMillis() % 4500L) / 4500.0F;
/*      */     
/* 1612 */     return Color.HSBtoRGB(hue - hueoffset / 54.0F, saturation, brightness);
/*      */   }
/*      */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\render\RenderUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */