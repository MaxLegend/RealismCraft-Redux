package ru.legendgamer.Realism.RealismCore.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class WetSand extends Block{

	public WetSand(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype) {
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
		if(world.isDaytime() && !world.isRaining() && random.nextInt(160) == 3) {
			world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()),RegBlocks.realismsand.getDefaultState());
		}
	}



	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, this, 1, 0);
	}
}

