package ru.legendgamer.Realism.Proxy;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapStorage;
import ru.legendgamer.Realism.Capability.WorldCAP.DateStorage;
import ru.legendgamer.Realism.Capability.WorldCAP.IDate;
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
import ru.legendgamer.Realism.RealismCore.RegFoods;
import ru.legendgamer.Realism.RealismCore.RegItems;
import ru.legendgamer.Realism.WorldGen.WorldGenHui;

public class CommonProxy {

	public static WorldGenHui wgo = new WorldGenHui();
    public void preInit(FMLPreInitializationEvent event)
    {
    	ConfigManager.register(event);
    	
    	RegItems.register();
    	RegFoods.register();
    	RegBlocks.register();

    	
    	new RegEvents.Server();
    	CapabilityManager.INSTANCE.register(IPlayerCap.class, new PlayerCapStorage(), PlayerCap.class);
    	CapabilityManager.INSTANCE.register(ICAPCustomInventory.class, new CAPCustomInventoryStorage(), CAPCustomInventory.class);
    	 CapabilityManager.INSTANCE.register(IDate.class, DateStorage.INSTANCE, DateStorage.INSTANCE);
    	NetworkHandler.init();
    	
    }
    public void init(FMLInitializationEvent event)
    {
    	//  	GameRegistry.registerWorldGenerator(wgo, 0);
    	CapabilityEventHandler.register();
    	NetworkRegistry.INSTANCE.registerGuiHandler(Realism.INSTANCE, new GuiHandler());
    }
    public void postInit(FMLPostInitializationEvent event)
    {
    	
    }
    
    public void particleClient(World world, BlockPos pos, Random rand) {}
}