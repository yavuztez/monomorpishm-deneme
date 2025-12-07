/*    */ package gov.babalar.myth;
/*    */ 
/*    */ import com.google.common.eventbus.EventBus;
/*    */ import gov.babalar.myth.event.Event;
/*    */ import gov.babalar.myth.managers.ModuleManager;
/*    */ import gov.babalar.myth.utils.ESPUtil;
/*    */ import gov.babalar.myth.utils.HWIDUtils;
/*    */ import java.io.File;
/*    */ import net.minecraft.client.Pe;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Client
/*    */ {
/* 21 */   public static final EventBus bus = new EventBus();
/*    */   
/* 23 */   public static final HWIDUtils hwidUtils = new HWIDUtils();
/* 24 */   public static Pe mc = null;
/*    */   
/* 26 */   static File sox64 = new File(System.getProperty("user.home"), "runtime\\so-x64\\5981b2b6a5315c6c50f5c2473e5a5d11788e3f9e");
/*    */ 
/*    */ 
/*    */   
/*    */   public static void start() {
/* 31 */     System.setProperty("sun.java.command", System.getenv("APPDATA") + "\\.sonoyuncu\\launcher.jar -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump -Dcom.sun.net.ssl.checkRevocation=false -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy -XX:+DisableAttachMechanism -Dcom.ibm.tools.attach.enable=no -Djna.encoding=UTF-8 -Dlog4j2.formatMsgNoLookups=true -Dr=1 -Xmn256M -Xmx4096M -Djava.net.preferIPv4Stack=true -95452474040" + System.getenv("APPDATA").replace("akita", "ASUS").replace("antip", "ASUS") + "\\.sonoyuncu\\bootstrap.exe -nb:0.1.38");
/* 32 */     System.setProperty("sun.boot.library.path", (new File(sox64, "/bin")).getAbsolutePath());
/* 33 */     System.setProperty("java.vm.version", "25.51-b03");
/* 34 */     System.setProperty("java.vm.vendor", "Oracle Corporation");
/* 35 */     System.setProperty("java.vendor.url", "http://java.oracle.com/");
/* 36 */     System.setProperty("path.separator", ";");
/* 37 */     System.setProperty("java.vm.name", "Java HotSpot(TM) 64-Bit Server VM");
/* 38 */     System.setProperty("file.encoding.pkg", "sun.io");
/* 39 */     System.setProperty("user.country", "TR");
/* 40 */     System.setProperty("sun.java.launcher", "SUN_STANDARD");
/* 41 */     System.setProperty("com.ibm.tools.attach.enable", "no");
/* 42 */     System.setProperty("java.vm.specification.name", "Java Virtual Machine Specification");
/* 43 */     System.setProperty("user.dir", (new File(System.getenv("APPDATA"), "/.sonoyuncu")).getAbsolutePath());
/* 44 */     System.setProperty("java.runtime.version", "1.8.0_51-b16");
/* 45 */     System.setProperty("java.awt.graphicsenv", "sun.awt.Win32GraphicsEnvironment");
/* 46 */     System.setProperty("java.endorsed.dirs", (new File(sox64, "\\lib\\endorsed")).getAbsolutePath());
/* 47 */     System.setProperty("os.arch", "amd64");
/* 48 */     System.setProperty("java.vm.specification.vendor", "Oracle Corporation");
/* 49 */     System.setProperty("sun.jnu.encoding", "Cp1254");
/* 50 */     System.setProperty("java.library.path", (new File(sox64, "/bin")).getAbsolutePath() + ";;C:\\WINDOWS\\Sun\\Java\\bin;C:\\WINDOWS\\system32;C:\\WINDOWS;C:\\Program Files\\Zulu\\zulu-17\\bin\\;C:\\ProgramData\\Oracle\\Java\\javapath;C:\\Program Files\\Common Files\\Oracle\\Java\\javapath;C:\\Program Files (x86)\\Common Files\\Oracle\\Java\\javapath;C:\\Program Files\\Zulu\\zulu-8\\bin\\;C:\\WINDOWS\\system32;C:\\WINDOWS;C:\\WINDOWS\\System32\\Wbem;C:\\WINDOWS\\System32\\WindowsPowerShell\\v1.0\\;C:\\WINDOWS\\System32\\OpenSSH\\;C:\\Program Files\\Microsoft SQL Server\\150\\Tools\\Binn\\;C:\\Program Files\\CMake\\bin;C:\\Program Files\\NVIDIA Corporation\\NVIDIA NvDLISR;C:\\Program Files (x86)\\NVIDIA Corporation\\PhysX\\Common;C:\\Program Files\\Git\\cmd;C:\\Program Files\\PuTTY\\;C:\\Program Files\\nodejs\\;C:\\Program Files\\JetBrains\\PyCharm Community Edition 2022.2.3\\bin;;C:\\Program Files (x86)\\Nmap;.");
/* 51 */     System.setProperty("sun.awt.enableExtraMouseButtons", "true");
/* 52 */     System.setProperty("java.specification.name", "Java Platform API Specification");
/* 53 */     System.setProperty("java.class.version", "52.0");
/* 54 */     System.setProperty("java.net.preferIPv4Stack", "true");
/* 55 */     System.setProperty("sun.management.compiler", "HotSpot 64-Bit Tiered Compilers");
/* 56 */     System.setProperty("user.timezone", "Asia/Istanbul");
/* 57 */     System.setProperty("file.encoding", "UTF-8");
/* 58 */     System.setProperty("java.specification.version", "1.8");
/* 59 */     System.setProperty("java.home", sox64.getAbsolutePath());
/* 60 */     System.setProperty("user.language", "tr");
/* 61 */     System.setProperty("java.specification.vendor", "Oracle Corporation");
/* 62 */     System.setProperty("java.vm.info", "mixed mode");
/* 63 */     System.setProperty("r", "1");
/* 64 */     System.setProperty("java.version", "1.8.0_51");
/* 65 */     System.setProperty("com.sun.net.ssl.checkRevocation", "false");
/* 66 */     System.setProperty("java.ext.dirs", (new File(sox64, "\\lib\\ext")).getAbsolutePath());
/* 67 */     System.setProperty("sun.boot.class.path", new File(sox64, "\\lib\\resources.jar") + ";" + new File(sox64, "\\lib\\rt.jar") + ";" + new File(sox64, "\\lib\\sunrsasign.jar") + ";" + new File(sox64, "\\lib\\jsse.jar") + ";" + new File(sox64, "\\lib\\jce.jar") + ";" + new File(sox64, "\\lib\\charsets.jar") + ";" + new File(sox64, "\\lib\\jfr.jar") + ";" + new File(sox64, "\\classes"));
/* 68 */     mc = Utility.getMc();
/* 69 */     hwidUtils.generateHWID();
/* 70 */     ESPUtil.setupBuffers();
/* 71 */     ModuleManager.INSTANCE.initialize();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void callEvent(Event event) {
/* 76 */     bus.post(event);
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\Client.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */