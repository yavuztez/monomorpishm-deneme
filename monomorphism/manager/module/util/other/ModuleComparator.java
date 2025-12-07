/*    */ package monomorphism.manager.module.util.other;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import monomorphism.manager.module.Module;
/*    */ import monomorphism.manager.module.util.Client;
/*    */ 
/*    */ public class ModuleComparator
/*    */   extends Client
/*    */   implements Comparator<Module>
/*    */ {
/*    */   public int compare(Module arg0, Module arg1) {
/* 12 */     String name = arg0.getModuleName();
/* 13 */     double d1 = mc.ar.a(name);
/* 14 */     String name1 = arg1.getModuleName();
/* 15 */     double d2 = mc.ar.a(name1);
/* 16 */     if (d1 < d2) {
/* 17 */       return -1;
/*    */     }
/* 19 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\FlorexClient.zip!\monomorphism\manager\modul\\util\other\ModuleComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */