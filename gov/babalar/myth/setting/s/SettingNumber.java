/*    */ package gov.babalar.myth.setting.s;
/*    */ 
/*    */ import gov.babalar.myth.setting.AbstractSetting;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SettingNumber
/*    */   extends AbstractSetting
/*    */ {
/*    */   public double value;
/*    */   public double min;
/*    */   public double max;
/*    */   public double inc;
/*    */   public String name;
/*    */   
/*    */   public SettingNumber(double value, double min, double max, double inc, String name) {
/* 20 */     this.value = value;
/* 21 */     this.min = min;
/* 22 */     this.max = max;
/* 23 */     this.inc = inc;
/* 24 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\setting\s\SettingNumber.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */