package ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Basic;

import java.util.Random;

import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.API.TimerForCoord;
import ru.legendgamer.Realism.API.BasicBlock.BasicBlockWithCustomModel;

public class BaseOakGrowLog extends BasicBlockWithCustomModel{
	public static final PropertyBool ISBASE = PropertyBool.create("isbase");

	public BaseOakGrowLog(Material materialIn, String name, float hardness, float resistanse, String hravLevel,
			int level, SoundType soundtype) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, soundtype);
		this.setDefaultState(this.blockState.getBaseState().withProperty(ISBASE, false));

	} 
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		if(world.getBlockState(pos.down()) == Blocks.GRASS.getDefaultState()) {
			world.setBlockState(pos, state.withProperty(ISBASE, true));
		} else world.setBlockState(pos, state.withProperty(ISBASE, false));
	}
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);
		if(world.getBlockState(pos.down()) == Blocks.GRASS.getDefaultState()) {
			world.setBlockState(pos, state.withProperty(ISBASE, true));
		} else world.setBlockState(pos, state.withProperty(ISBASE, false));
	}
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	protected PropertyBool getBase()
	{ return ISBASE; }

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {ISBASE});
	}

}
