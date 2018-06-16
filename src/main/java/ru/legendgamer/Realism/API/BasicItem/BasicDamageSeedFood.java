package ru.legendgamer.Realism.API.BasicItem;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BasicDamageSeedFood extends BasicDamageFood implements net.minecraftforge.common.IPlantable
{
	private final Block crops;
	/** Block ID of the soil this seed food should be planted on. */
	private final Block soilId;

	public BasicDamageSeedFood(int healAmount, float saturation, Block crops, Block soil,String name, int itemUseDuration,int maxEating,int protein,int fat,int carbone,int shelflife)
	{
		super(healAmount, saturation, false, name, itemUseDuration, maxEating, protein, fat, carbone, shelflife);
		this.crops = crops;
		this.soilId = soil;
	}

	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		ItemStack itemstack = player.getHeldItem(hand);
		net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
		if(itemstack.getItemDamage() == itemstack.getMaxDamage()) {
			if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()))
			{
				worldIn.setBlockState(pos.up(), this.crops.getDefaultState(), 11);
				itemstack.shrink(1);
				return EnumActionResult.SUCCESS;
			}

		}

		return EnumActionResult.FAIL;

	}


	@Override
	public net.minecraftforge.common.EnumPlantType getPlantType(net.minecraft.world.IBlockAccess world, BlockPos pos)
	{
		return net.minecraftforge.common.EnumPlantType.Crop;
	}

	@Override
	public net.minecraft.block.state.IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos)
	{
		return this.crops.getDefaultState();
	}
}