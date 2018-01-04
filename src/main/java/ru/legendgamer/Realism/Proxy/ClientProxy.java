package ru.legendgamer.Realism.Proxy;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.legendgamer.Realism.Events.EventToolMode;
import ru.legendgamer.Realism.Events.RegEvents;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;
import ru.legendgamer.Realism.RealismCore.RegItems;
import ru.legendgamer.Realism.RealismCore.Particle.FallingLeaves;

public class ClientProxy extends CommonProxy {
	public static String KEY_CATEGORY = "key.categories." + Realism.MODID;
	public static EventToolMode.EnumToolMode currentToolMode = EventToolMode.EnumToolMode.FULL;
	 
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);

		RegItems.preRegisterRender();
		 ClientRegistry.registerKeyBinding(EventToolMode.KEY_TOOL_MODE);
		new RegEvents.Client();
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		//	RegRenderLayer.register();
		RegBlocks.registerRender();
		RegItems.registerRender();
		//SoundsRegister.register();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
		final BlockColors block = FMLClientHandler.instance().getClient().getBlockColors(); 
		ItemColors color = FMLClientHandler.instance().getClient().getItemColors();
		color.registerItemColorHandler(new IItemColor() {
			

			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) {
				return block.colorMultiplier( RegBlocks.smallleaves.getStateFromMeta(0), (IBlockAccess)null, (BlockPos)null, tintIndex);}
			},
				RegBlocks.leavesappletree,
				RegBlocks.smallleaves,
				RegBlocks.smallleavesspruce, 
				RegBlocks.fallenlayers,
				//RegItems.itemfallen,
				RegBlocks.mossblock,
				RegBlocks.smallleavesbirch,
				RegBlocks.smallleavesappletree);
	}
	
	@Override 
	public void particleClient(World world, BlockPos pos, Random rand) {
		FallingLeaves newEffect = new FallingLeaves(world, pos.getX() + rand.nextFloat(), pos.getY(), pos.getZ()+ rand.nextFloat(), 0, 0, 0);
		Minecraft.getMinecraft().effectRenderer.addEffect(newEffect);
	}
}
