package ru.legendgamer.Realism.RealismCore.Blocks;

import java.util.Random;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class RealismSand extends BlockFalling{

	public RealismSand(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setSoundType(soundtype);
		this.setHardness(hardness);
		this.setResistance(resistanse);
		this.setHarvestLevel("shovel", 1);
		this.setCreativeTab(Realism.tabMain);
	}
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		super.updateTick(world, pos, state, random);
		world.scheduleBlockUpdate(pos, this, 1, 0);

		if (!world.isRemote)
		{
			this.checkFallable(world, pos);
		}
		if(world.getBlockState(new BlockPos(pos.getX() -1, pos.getY(), pos.getZ())).getBlock().getMaterial(Blocks.WATER.getDefaultState()) == Material.WATER ||
				world.getBlockState(new BlockPos(pos.getX() +1, pos.getY(), pos.getZ())).getBlock().getMaterial(Blocks.WATER.getDefaultState()) == Material.WATER ||
				world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock().getMaterial(Blocks.WATER.getDefaultState()) == Material.WATER ||
				world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1)).getBlock().getMaterial(Blocks.WATER.getDefaultState()) == Material.WATER||
				world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)).getBlock().getMaterial(Blocks.WATER.getDefaultState()) == Material.WATER ||
				world.getBlockState(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ())).getBlock().getMaterial(Blocks.WATER.getDefaultState()) == Material.WATER) {
			world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()),RegBlocks.wetsand.getDefaultState());
		}
			if(world.isRaining()) {
				world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()),RegBlocks.wetsand.getDefaultState());
			}
		}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, this, 1, 0);
	}
	private void checkFallable(World worldIn, BlockPos pos)
	{
		if ((worldIn.isAirBlock(pos.down()) || canFallThrough(worldIn.getBlockState(pos.down()))) && pos.getY() >= 0)
		{
			int i = 32;

			if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
			{
				if (!worldIn.isRemote)
				{
					EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, worldIn.getBlockState(pos));
					this.onStartFalling(entityfallingblock);
					worldIn.spawnEntity(entityfallingblock);
				}
			}
			else
			{
				IBlockState state = worldIn.getBlockState(pos);
				worldIn.setBlockToAir(pos);
				BlockPos blockpos;

				for (blockpos = pos.down(); (worldIn.isAirBlock(blockpos) || canFallThrough(worldIn.getBlockState(blockpos))) && blockpos.getY() > 0; blockpos = blockpos.down())
				{
					;
				}

				if (blockpos.getY() > 0)
				{
					worldIn.setBlockState(blockpos.up(), state); //Forge: Fix loss of state information during world gen.
				}
			}
		}
	}
}
