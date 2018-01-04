package ru.legendgamer.Realism.RealismCore.Items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import ru.legendgamer.Realism.Events.EventToolMode;
import ru.legendgamer.Realism.Proxy.ClientProxy;
import ru.legendgamer.Realism.RealismCore.Realism;

public class ItemRealShovel extends Item {
	
	private String name;
	
	public ItemRealShovel(String name, String regName) {
		setRegistryName(regName).setHasSubtypes(true).setCreativeTab(Realism.tabMain).setMaxStackSize(1);
		this.name = name;
	}
	
	public String getUnlocalizedName(ItemStack stack)
    {
		switch(stack.getMetadata()) {
		case 1: return "item.dirt" + name + "Shovel";
		case 2: return "item.grass" + name + "Shovel";
		case 3: return "item.gravel" + name + "Shovel";
		default: return "item.empty" + name + "Shovel";
		}
    }
	
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if (isInCreativeTab(tab))
        {
        	for (int a = 0; a < 4; a++)
        		items.add(new ItemStack(this, 1, a));
        }
    }
	
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
		ItemStack stack = player.getHeldItem(hand);
		RayTraceResult raytrace = rayTrace(world, player, false);
		if (raytrace != null && raytrace.typeOfHit == RayTraceResult.Type.BLOCK) {
			BlockPos pos = raytrace.getBlockPos();
			
			Block block = world.getBlockState(pos).getBlock();
			
			if (stack.getMetadata() == 0) {
				if (ClientProxy.currentToolMode == EventToolMode.EnumToolMode.FULL) {
					if (block == Blocks.DIRT)
						if (world.setBlockToAir(pos))
							stack = new ItemStack(this, 1, 1);
					if (block == Blocks.GRASS)
						if (world.setBlockToAir(pos))
							stack = new ItemStack(this, 1, 2);
					if (block == Blocks.GRAVEL)
						if (world.setBlockToAir(pos))
							stack = new ItemStack(this, 1, 3);
				} else if (ClientProxy.currentToolMode == EventToolMode.EnumToolMode.TAKE) {
					if (block == Blocks.DIRT)
						if (world.setBlockToAir(pos))
							player.addItemStackToInventory(new ItemStack(block));
					if (block == Blocks.GRASS)
						if (world.setBlockToAir(pos))
							player.addItemStackToInventory(new ItemStack(block));
					if (block == Blocks.GRAVEL)
						if (world.setBlockToAir(pos))
			 				player.addItemStackToInventory(new ItemStack(block));
				} else if (ClientProxy.currentToolMode == EventToolMode.EnumToolMode.ROAD) {
					if (block == Blocks.GRASS)
						world.setBlockState(pos, Blocks.GRASS_PATH.getDefaultState());
				}
			} else {
				if (!world.getBlockState(pos).getBlock().isReplaceable(world, pos))
					pos = pos.offset(raytrace.sideHit);
				if (stack.getMetadata() == 1)
					if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
						if (world.setBlockState(pos, Blocks.DIRT.getDefaultState()))
							stack = new ItemStack(this, 1, 0);
				if (stack.getMetadata() == 2)
					if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
						if (world.setBlockState(pos, Blocks.GRASS.getDefaultState()))
							stack = new ItemStack(this, 1, 0);
				if (stack.getMetadata() == 3)
					if (world.getBlockState(pos).getBlock().isReplaceable(world, pos))
						if (world.setBlockState(pos, Blocks.GRAVEL.getDefaultState()))
							stack = new ItemStack(this, 1, 0);
			}
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
		}
		return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
    }
}