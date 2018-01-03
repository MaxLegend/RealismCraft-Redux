package ru.legendgamer.Realism.RealismCore;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.legendgamer.Realism.Proxy.CommonProxy;

@Mod(modid = Realism.MODID, version = Realism.VERSION)
public class Realism
{
	public static final String MODID = "realism";
	public static final String VERSION = "0.1 Redux";
	public static final String NAME = "Realism Mod";
	
	public static final String[] AUTHORS = new String[] {"LegendGamer"};
	//public static CreativeTabs tabMain = new MainRealism("tabMain");
	//public static CreativeTabs tabDev = new DevRealism("tabDev");
	@SidedProxy(clientSide = "ru.legendgamer.Realism.Proxy.ClientProxy", serverSide = "ru.legendgamer.Realism.Proxy.CommonProxy")
	public static CommonProxy proxy;
	@Mod.Instance
	public static Realism INSTANCE;


	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {	
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		proxy.init(event);

	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}
