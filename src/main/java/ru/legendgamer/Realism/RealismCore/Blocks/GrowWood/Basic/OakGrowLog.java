package ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.TimerForCoord;
import ru.legendgamer.Realism.API.BasicBlock.BasicBlockWithCustomModel;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class OakGrowLog extends BasicBlockWithCustomModel {
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 2);


	int maxHeightLeaves;
	protected static final AxisAlignedBB treeStageOne_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 1D, 0.7D);

	public OakGrowLog(Material materialIn, String name, float hardness,float resistanse, String hravLevel, int level, SoundType blockSoundType) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, blockSoundType);
		this.setDefaultState(this.blockState.getBaseState().withProperty(STAGE, 0));
		this.setTickRandomly(true);
	}

	protected PropertyInteger getBlockStage()
	{ return STAGE; }

	protected int getStage(IBlockState state)
	{ return state.getValue(this.getBlockStage()); }

	public IBlockState setStage(int stage)
	{ return this.getDefaultState().withProperty(this.getBlockStage(), Integer.valueOf(stage)); }


	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(STAGE, Integer.valueOf(meta));
	}
	public int getMetaFromState(IBlockState state)
	{
		return ((Integer)state.getValue(STAGE)).intValue();
	}
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {STAGE});
	}


	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return treeStageOne_AABB;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		return false;
	}

}

/*
 * 			if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightTree, pos.getZ())).getBlock() != Blocks.LOG 
					|| world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightTree, pos.getZ())).getBlock() != RegBlocks.oakgrowlog){

				int stage = this.getStage(state);

				if(world.isAirBlock(posUp)) {		

					world.setBlockState(pos, RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, 1));
					world.setBlockState(pos.up(), RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, 0));

				} else 
					if(world.getBlockState(posUp) == RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, this.getStage(state)) && stage < 3) {
						world.setBlockState(pos, RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, this.getStage(state) + 1));

					} else 
						if(stage >= 3) {
							world.setBlockState(pos, Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));
							if(world.getBlockState(pos.down()) == RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, this.getStage(state))) {
								world.setBlockState(pos, Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));
							}
						}


			}
 */

