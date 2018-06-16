package ru.legendgamer.Realism.RealismCore.Items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.API.BasicItem.BasicItem;

public class WetVineItem extends BasicItem{

	public WetVineItem(String name, int maxStackSize) {
		super(name, maxStackSize);
		// TODO Auto-generated constructor stub
	}
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (world.getBlockState(pos).getBlock() == Blocks.LOG) {
			//crutch Block set in PlaceOnBlock Event
			player.getHeldItem(EnumHand.MAIN_HAND).shrink(1);
		}
		return EnumActionResult.PASS;
	}

}

