package ru.legendgamer.Realism.Proxy;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapStorage;
import ru.legendgamer.Realism.Config.ConfigManager;
import ru.legendgamer.Realism.Events.RegEvents;
import ru.legendgamer.Realism.NewInventory.CAPforINV.CAPCustomInventory;
import ru.legendgamer.Realism.NewInventory.CAPforINV.CAPCustomInventoryStorage;
import ru.legendgamer.Realism.NewInventory.CAPforINV.ICAPCustomInventory;
import ru.legendgamer.Realism.NewInventory.CAPforINV.reg.CapabilityEventHandler;
import ru.legendgamer.Realism.PacketSystem.NetworkHandler;
import ru.legendgamer.Realism.RealismCore.GuiHandler;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;
import ru.legendgamer.Realism.RealismCore.RegItems;

public class CommonProxy {


    public void preInit(FMLPreInitializationEvent event)
    {
    	ConfigManager.register(event);
    	
    	RegBlocks.register();
    	RegItems.register();
    //	RegEntity.register();
    	
    	new RegEvents.Server();
    	CapabilityManager.INSTANCE.register(IPlayerCap.class, new PlayerCapStorage(), PlayerCap.class);
    	CapabilityManager.INSTANCE.register(ICAPCustomInventory.class, new CAPCustomInventoryStorage(), CAPCustomInventory.class);
    	NetworkHandler.init();
    	
    }
    public void init(FMLInitializationEvent event)
    {
    	
    	CapabilityEventHandler.register();
    	NetworkRegistry.INSTANCE.registerGuiHandler(Realism.INSTANCE, new GuiHandler());
    }
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
    
    public void particleClient(World world, BlockPos pos, Random rand) {}
}