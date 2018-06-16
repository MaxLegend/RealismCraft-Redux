package ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.TreeSeeds.ItemSaplings;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.API.BasicItem.BasicItem;

public class ItemAppleSapling extends BasicItem {
	public Block blockOn;
	public Block blockThis;
	public ItemAppleSapling(String name, int maxStackSize, Block blocks, Block blockThis) {
		super(name, maxStackSize);
		this.blockOn = blocks;
		this.blockThis = blockThis;
	}
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (world.getBlockState(pos).getBlock() == blockOn && world.isAirBlock(pos.up()) && facing == EnumFacing.UP) {
			//crutch Block set in PlaceOnBlock Event
			world.setBlockState(pos.up(), blockThis.getDefaultState());
			player.getHeldItem(EnumHand.MAIN_HAND).shrink(1);
		}
		return EnumActionResult.PASS;
	}

}

