package ru.legendgamer.Realism.RealismCore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import ru.legendgamer.Realism.Command.RealismCommand;
import ru.legendgamer.Realism.Proxy.CommonProxy;
import ru.legendgamer.Realism.RealismCore.CreativeTabs.DevRealism;
import ru.legendgamer.Realism.RealismCore.CreativeTabs.MainRealism;

@Mod(modid = Realism.MODID, version = Realism.VERSION)
public class Realism
{
	public static final String MODID = "realism";
	public static final String VERSION = "0.1 Redux";
	public static final String NAME = "Realism Mod";
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String[] AUTHORS = new String[] {"LegendGamer"};
	public static CreativeTabs tabMain = new MainRealism("tabMain");
	public static CreativeTabs tabDev = new DevRealism("tabDev");
	@SidedProxy(clientSide = "ru.legendgamer.Realism.Proxy.ClientProxy", serverSide = "ru.legendgamer.Realism.Proxy.CommonProxy")
	public static CommonProxy proxy;
	@Mod.Instance
	public static Realism INSTANCE;
	public static final ResourceLocation DATE = new ResourceLocation("seasons","date");


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {	
		proxy.preInit(event);
		LOGGER.info("[MOD] Realism Mod enabled and loaded");
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		proxy.init(event);

	}
    @EventHandler
    public  void onStart(FMLServerStartingEvent event){
        event.registerServerCommand(new RealismCommand());
    }
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
