package monomorphism.manager.module.util.other;

import java.util.Comparator;
import monomorphism.manager.module.Module;
import monomorphism.manager.module.util.Client;

public class ModuleComparator extends Client implements Comparator<Module> {
   public int compare(Module arg0, Module arg1) {
      String name = arg0.getModuleName();
      double d1 = (double)mc.ar.a(name);
      String name1 = arg1.getModuleName();
      double d2 = (double)mc.ar.a(name1);
      return d1 < d2 ? -1 : 1;
   }
}
