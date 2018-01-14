package ru.legendgamer.Realism.Config;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import scala.Int;

public class ConfigManager
{
   public static Configuration config;
   public static Property randomList;
   public static int xTextTempBody;
   public static int yTextTempBody;
   public static int powerThirst;
   public static boolean thirstDebug;
   public static boolean debugGeneration;
   public static boolean enableFirstGenerator;
   public static boolean enableTwoGenerator;
   public static boolean enableThirdGenerator;
   
   public static int updateInv;
   
   public static int timeOneSec;
   public static int timeOneMin;
   public static int timeOneHour;
   public static int timeOneDay;
   public static int timeOneMonth;
   public static int timeOneSeason;
   public static int timeOneYear;
   
   //pm = player motion 
   //spm = sprinting player motion 
   //the number at the end - the extreme weight
   public static float pm339;
   public static float spm339;
   
   public static float pm678;
   public static float spm678;
   
   public static float pm1017;
   public static float spm1017;
   
   public static float pm1356;
   public static float spm1356;
   
   public static float pm1695;
   public static float spm1695;
   
   public static float pm2034;
   public static float spm2034;

   //temp variables
   public static int tempWinter;
   public static int tempSpring;
   public static int tempSummer;
   public static int tempAutumn;
   
   public static int speedRemoveWeight;

   public static int radiusRenderMold;
   
   public static void register(FMLPreInitializationEvent e) {
       config = new Configuration(e.getSuggestedConfigurationFile());
       config.load();
       radiusRenderMold = config.getInt("radiusRenderMold", "", 9,  3, 70, I18n.translateToLocal("config.radiusRenderMold"), "config.radiusRenderMold.name");
       
       speedRemoveWeight = config.getInt("speedRemoveWeight", "System Weight", 240,  120, 960, I18n.translateToLocal("config.speedRemoveWeight"), "config.speedRemoveWeight.name");
       //они пока ничего не решают
       tempWinter = config.getInt("tempWinter", "Seasons:Temperature", 12,  8, 20, I18n.translateToLocal("config.tempWinter"), "config.tempWinter.name");
       tempSpring = config.getInt("tempSpring", "Seasons:Temperature", 12,  8, 20,  I18n.translateToLocal("config.tempSpring"), "config.tempSpring.name");
       tempSummer = config.getInt("tempSummer", "Seasons:Temperature", 12,  8, 20, I18n.translateToLocal("config.tempSummer"), "config.tempSummer.name");
       tempAutumn = config.getInt("tempAutumn", "Seasons:Temperature", 12,  8, 20, I18n.translateToLocal("config.tempAutumn"), "config.tempAutumn.name");
     
       
       pm339 = config.getFloat("pm339", "System Weight", 1.05F,  1.0F,1.2F, "config.pm339", "config.pm339.name");
       spm339 = config.getFloat("spm339", "System Weight", 1.01F, 1.0F, 1.07F,  "config.spm339", "config.spm339.name");

       pm678 = config.getFloat("pm678", "System Weight", 0.9F, 0.81F,0.96F,   "config.pm678", "config.pm678.name");
       spm678 = config.getFloat("spm678", "System Weight", 0.9F, 0.81F,0.96F,   "config.spm678", "config.spm678.name");

       pm1017 = config.getFloat("pm1017", "System Weight", 0.8F,  0.71F,0.89F,  "config.pm1017", "config.pm1017.name");
       spm1017 = config.getFloat("spm1017", "System Weight", 0.8F, 0.71F,0.89F,   "config.spm1017", "config.spm1017.name");
       
       pm1356 = config.getFloat("pm1356", "System Weight", 0.7F, 0.61F,0.79F,   "config.pm1356", "config.pm1356.name");
       spm1356 = config.getFloat("spm1356", "System Weight", 0.7F, 0.61F,0.79F,   "config.spm1356", "config.spm1356.name");
       
       pm1695 = config.getFloat("pm1695", "System Weight", 0.5F, 0.41F,0.59F,   "config.pm1695", "config.pm1695.name");
       spm1695 = config.getFloat("spm1695", "System Weight", 0.5F, 0.41F,0.59F,   "config.spm1695", "config.spm1695.name");
       
       pm2034 = config.getFloat("pm2034", "System Weight", 0.4F, 0.31F,0.49F,   "config.pm2034", "config.pm2034.name");
       spm2034 = config.getFloat("spm2034", "System Weight", 0.4F, 0.31F,0.49F,   "config.spm2034", "config.spm2034.name");
       
       timeOneSec = config.getInt("timeOneSec", "Time Ticker", 20, Int.MinValue(), Int.MinValue(),  "config.timeOneSec", "config.timeOneSec.name");
       timeOneMin = config.getInt("timeOneMin", "Time Ticker", 1200, Int.MinValue(), Int.MinValue(),  "config.timeOneMin", "config.timeOneMin.name");
       timeOneHour = config.getInt("timeOneHour", "Time Ticker", 72000, Int.MinValue(), Int.MinValue(),  "config.timeOneHour", "config.timeOneHour.name");
       timeOneDay = config.getInt("timeOneDay", "Time Ticker", 1728000, Int.MinValue(), Int.MinValue(),  "config.timeOneDay", "config.timeOneDay.name");
       timeOneMonth = config.getInt("timeOneMonth", "Time Ticker", 51840000, Int.MinValue(), Int.MinValue(),  "config.timeOneMonth", "config.timeOneMonth.name");
       timeOneSeason = config.getInt("timeOneSeason", "Time Ticker", 155520000, Int.MinValue(), Int.MinValue(),  "config.timeOneSeason", "config.timeOneSeason.name");
       timeOneYear = config.getInt("timeOneYear", "Time Ticker", 622080000, Int.MinValue(), Int.MinValue(),  "config.timeOneYear", "config.timeOneYear.name");
       
       updateInv = config.getInt("updateInv", "System Weight", 20, 20, 60,  "config.updateInv", "config.updateInv.name");
       
       xTextTempBody = config.getInt("xTextTempBody", "Customization", 190, Int.MinValue(), Int.MaxValue(),  "config.xTextTempBody", "config.xTextTempBody.name");
       yTextTempBody = config.getInt("yTextTempBody", "Customization", 20, Int.MinValue(),  Int.MaxValue(),  "config.yTextTempBody", "config.yTextTempBody.name");
       
       powerThirst = config.getInt("powerThirst", "Thirst", 240, 50, 72000,  "config.powerThirst", "config.powerThirst.name");
       thirstDebug = config.getBoolean("thirstDebug", "Debug", false,  "config.thirstDebug", "config.thirstDebug.name");
       debugGeneration = config.getBoolean("debugGeneration", "Debug", false,  "config.debugGeneration", "config.debugGeneration.name");
       enableFirstGenerator = config.getBoolean("enableFirstGenerator", "Customization", true,  "config.enableFirstGenerator", "config.enableFirstGenerator.name");
       enableTwoGenerator = config.getBoolean("enableTwoGenerator", "Customization", true,  "config.enableTwoGenerator", "config.enableTwoGenerator.name");
       enableThirdGenerator = config.getBoolean("enableThirdGenerator", "Customization", true,  "config.enableThirdGenerator", "config.enableThirdGenerator.name");
       config.save();
   }
}