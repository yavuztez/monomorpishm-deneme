/*    */ package gov.babalar.myth.setting.s;
/*    */ 
/*    */ import gov.babalar.myth.setting.AbstractSetting;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SettingMode
/*    */   extends AbstractSetting
/*    */ {
/*    */   public int index;
/*    */   public String name;
/*    */   public String[] values;
/*    */   
/*    */   public SettingMode(String name, String[] value, int index) {
/* 16 */     this.name = name;
/* 17 */     this.values = value;
/* 18 */     this.index = index;
/*    */   }
/*    */   
/*    */   public String getMode() {
/* 22 */     return this.values[this.index];
/*    */   }
/*    */   
/*    */   public void switchMode() {
/* 26 */     int indexUpper = this.index + 1;
/* 27 */     if (indexUpper >= this.values.length) {
/*    */       
/* 29 */       this.index = 0;
/*    */       return;
/*    */     } 
/* 32 */     this.index = indexUpper;
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\setting\s\SettingMode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */