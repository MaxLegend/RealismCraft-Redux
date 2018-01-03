package ru.legendgamer.Realism.Proxy;

import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.legendgamer.Realism.Capability.WaterCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.WaterCAP.PlayerCap;
import ru.legendgamer.Realism.Capability.WaterCAP.PlayerCapStorage;
import ru.legendgamer.Realism.Events.RegEvents;
import ru.legendgamer.Realism.PacketSystem.NetworkHandler;

public class CommonProxy {


    public void preInit(FMLPreInitializationEvent event)
    {

    	new NetworkHandler();
    	new RegEvents.Server();
    	CapabilityManager.INSTANCE.register(IPlayerCap.class, new PlayerCapStorage(), PlayerCap.class);
    }
    public void init(FMLInitializationEvent event)
    {

    }
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
}