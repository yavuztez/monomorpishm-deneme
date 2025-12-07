/*    */ package monomorphism.manager.module.util.bypass;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ public class StartSpoofer
/*    */ {
/*  8 */   static File sox64 = new File(System.getProperty("user.home"), "runtime\\so-x64\\5981b2b6a5315c6c50f5c2473e5a5d11788e3f9e");
/*    */ 
/*    */ 
/*    */   
/*    */   public static void start() {
/*    */     try {
/* 14 */       String appData = System.getenv("APPDATA");
/* 15 */       if (appData == null) appData = System.getProperty("user.home") + "\\AppData\\Roaming";
/*    */       
/* 17 */       String soPath = appData + "\\.sonoyuncu";
/*    */ 
/*    */       
/* 20 */       System.setProperty("sun.java.command", soPath + "\\launcher.jar -XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump -Dcom.sun.net.ssl.checkRevocation=false -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy -XX:+DisableAttachMechanism -Dcom.ibm.tools.attach.enable=no -Djna.encoding=UTF-8 -Dlog4j2.formatMsgNoLookups=true -Dr=1 -Xmn256M -Xmx4096M -Djava.net.preferIPv4Stack=true -95452474040" + soPath + "\\bootstrap.exe -nb:0.1.38");
/*    */ 
/*    */       
/* 23 */       System.setProperty("sun.boot.library.path", (new File(sox64, "/bin")).getAbsolutePath());
/* 24 */       System.setProperty("java.vm.version", "25.51-b03");
/* 25 */       System.setProperty("java.vm.vendor", "Oracle Corporation");
/* 26 */       System.setProperty("java.vendor.url", "http://java.oracle.com/");
/* 27 */       System.setProperty("path.separator", ";");
/* 28 */       System.setProperty("java.vm.name", "Java HotSpot(TM) 64-Bit Server VM");
/* 29 */       System.setProperty("file.encoding.pkg", "sun.io");
/* 30 */       System.setProperty("user.country", "TR");
/* 31 */       System.setProperty("sun.java.launcher", "SUN_STANDARD");
/* 32 */       System.setProperty("com.ibm.tools.attach.enable", "no");
/* 33 */       System.setProperty("java.vm.specification.name", "Java Virtual Machine Specification");
/* 34 */       System.setProperty("user.dir", soPath);
/* 35 */       System.setProperty("java.runtime.version", "1.8.0_51-b16");
/* 36 */       System.setProperty("java.awt.graphicsenv", "sun.awt.Win32GraphicsEnvironment");
/* 37 */       System.setProperty("java.endorsed.dirs", (new File(sox64, "\\lib\\endorsed")).getAbsolutePath());
/* 38 */       System.setProperty("os.arch", "amd64");
/* 39 */       System.setProperty("java.vm.specification.vendor", "Oracle Corporation");
/* 40 */       System.setProperty("sun.jnu.encoding", "Cp1254");
/*    */ 
/*    */       
/* 43 */       String libPath = (new File(sox64, "/bin")).getAbsolutePath() + ";;C:\\WINDOWS\\Sun\\Java\\bin;C:\\WINDOWS\\system32;C:\\WINDOWS;C:\\Program Files\\Zulu\\zulu-17\\bin\\;C:\\ProgramData\\Oracle\\Java\\javapath;C:\\Program Files\\Common Files\\Oracle\\Java\\javapath;C:\\Program Files (x86)\\Common Files\\Oracle\\Java\\javapath;C:\\Program Files\\Zulu\\zulu-8\\bin\\;C:\\WINDOWS\\system32;C:\\WINDOWS;C:\\WINDOWS\\System32\\Wbem;C:\\WINDOWS\\System32\\WindowsPowerShell\\v1.0\\;C:\\WINDOWS\\System32\\OpenSSH\\;C:\\Program Files\\Microsoft SQL Server\\150\\Tools\\Binn\\;C:\\Program Files\\CMake\\bin;C:\\Program Files\\NVIDIA Corporation\\NVIDIA NvDLISR;C:\\Program Files (x86)\\NVIDIA Corporation\\PhysX\\Common;C:\\Program Files\\Git\\cmd;C:\\Program Files\\PuTTY\\;C:\\Program Files\\nodejs\\;.";
/* 44 */       System.setProperty("java.library.path", libPath);
/*    */       
/* 46 */       System.setProperty("sun.awt.enableExtraMouseButtons", "true");
/* 47 */       System.setProperty("java.specification.name", "Java Platform API Specification");
/* 48 */       System.setProperty("java.class.version", "52.0");
/* 49 */       System.setProperty("java.net.preferIPv4Stack", "true");
/* 50 */       System.setProperty("sun.management.compiler", "HotSpot 64-Bit Tiered Compilers");
/* 51 */       System.setProperty("user.timezone", "Asia/Istanbul");
/* 52 */       System.setProperty("file.encoding", "UTF-8");
/* 53 */       System.setProperty("java.specification.version", "1.8");
/* 54 */       System.setProperty("java.home", sox64.getAbsolutePath());
/* 55 */       System.setProperty("user.language", "tr");
/* 56 */       System.setProperty("java.specification.vendor", "Oracle Corporation");
/* 57 */       System.setProperty("java.vm.info", "mixed mode");
/* 58 */       System.setProperty("r", "1");
/* 59 */       System.setProperty("java.version", "1.8.0_51");
/* 60 */       System.setProperty("com.sun.net.ssl.checkRevocation", "false");
/* 61 */       System.setProperty("java.ext.dirs", (new File(sox64, "\\lib\\ext")).getAbsolutePath());
/*    */ 
/*    */       
/* 64 */       String bootCP = new File(sox64, "\\lib\\resources.jar") + ";" + new File(sox64, "\\lib\\rt.jar") + ";" + new File(sox64, "\\lib\\sunrsasign.jar") + ";" + new File(sox64, "\\lib\\jsse.jar") + ";" + new File(sox64, "\\lib\\jce.jar") + ";" + new File(sox64, "\\lib\\charsets.jar") + ";" + new File(sox64, "\\lib\\jfr.jar") + ";" + new File(sox64, "\\classes");
/* 65 */       System.setProperty("sun.boot.class.path", bootCP);
/*    */       
/* 67 */       System.out.println("[Monomorphism] Sistem ozellikleri Myth gibi spooflandi.");
/*    */     }
/* 69 */     catch (Exception e) {
/* 70 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\bypass\StartSpoofer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */