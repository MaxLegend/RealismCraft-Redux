package ru.legendgamer.Realism.Proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.legendgamer.Realism.Events.RegEvents;

public class ClientProxy extends CommonProxy {

	
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		new RegEvents.Client();
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}
}
