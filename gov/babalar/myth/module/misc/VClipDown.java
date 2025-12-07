/*    */ package gov.babalar.myth.module.misc;
/*    */ 
/*    */ import gov.babalar.myth.Utility;
/*    */ import gov.babalar.myth.module.Module;
/*    */ import gov.babalar.myth.module.ModuleType;
/*    */ import gov.babalar.myth.setting.s.SettingBool;
/*    */ import gov.babalar.myth.setting.s.SettingNumber;
/*    */ import net.minecraft.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VClipDown
/*    */   extends Module
/*    */ {
/* 15 */   public SettingNumber downValue = new SettingNumber(5.0D, 1.0D, 10.0D, 1.0D, "Value");
/* 16 */   public SettingBool mode = new SettingBool(false, "Smart");
/* 17 */   public SettingNumber startValue = new SettingNumber(1.0D, 1.0D, 9.0D, 1.0D, "Start Value");
/*    */ 
/*    */   
/*    */   public VClipDown() {
/* 21 */     super(ModuleType.MISC, "VClip Down", 208);
/* 22 */     this.abstractSettings.add(this.downValue);
/* 23 */     this.abstractSettings.add(this.mode);
/* 24 */     this.abstractSettings.add(this.startValue);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onEnable() {
/* 29 */     if (this.mode.value) {
/* 30 */       for (int i = (int)this.startValue.value; i < 11; i++) {
/* 31 */         BlockPos belowBlockPos = new BlockPos(Utility.getPosX(), Utility.getPosY() - i, Utility.getPosZ());
/* 32 */         BlockPos pos2 = new BlockPos(Utility.getPosX(), Utility.getPosY() - (i - 1), Utility.getPosZ());
/* 33 */         if (Utility.getBlockState(belowBlockPos) != null && Utility.getBlockState(pos2) != null && 
/* 34 */           Utility.getBlockState(belowBlockPos).N() instanceof net.minecraft.fD && Utility.getBlockState(pos2).N() instanceof net.minecraft.fD) {
/* 35 */           Utility.getThePlayer().e(Utility.getPosX(), Utility.getPosY() - i - 1.0D, Utility.getPosZ());
/* 36 */           toggle();
/*    */           return;
/*    */         } 
/*    */       } 
/*    */     } else {
/* 41 */       Utility.getThePlayer().e(Utility.getPosX(), Utility.getPosY() - this.downValue.value, Utility.getPosZ());
/*    */     } 
/* 43 */     toggle();
/*    */   }
/*    */ }


/* Location:              C:\Users\yavuz\Desktop\masaüstü\FlorexClient\gov.zip!\gov\babalar\myth\module\misc\VClipDown.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */