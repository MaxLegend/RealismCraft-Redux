package ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Jungle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.legendgamer.Realism.API.TimerForCoord;
import ru.legendgamer.Realism.API.BasicBlock.BasicBlockWithCustomModel;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class JungleGrowStage4 extends BasicBlockWithCustomModel {
	List<TimerForCoord> time =  new ArrayList<TimerForCoord>();
	int maxHeightTree;
	protected static final AxisAlignedBB treeStageOne_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 1D, 0.7D);

	public JungleGrowStage4(Material materialIn, String name, float hardness,float resistanse, String hravLevel, int level, SoundType blockSoundType) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, blockSoundType);
	}
	@Override
	public Block setBlockUnbreakable()
	{
		this.setHardness(-1.0F);
		return this;
	}
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, this, 1, 0);
		time.add(new TimerForCoord(pos.getX(),pos.getY(),pos.getZ(),0));
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);

		TimerForCoord time2 = null;
		for (TimerForCoord t : time) {
			if (t != null && t.x == pos.getX() && t.y == pos.getY() && t.z == pos.getZ()) {
				time2 = t;
				++t.time;
			}
		}
		if (time2 == null) return;
		world.scheduleBlockUpdate(pos, this, 1, 0);

		if(!world.isRemote) {
			if (time2.time == 60) {
				if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightTree, pos.getZ())).getBlock() != Blocks.LOG){
					world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), RegBlocks.jungle_stage_4.getDefaultState());
				}
			}	
			if (time2.time == 80) {
				
				world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));
				world.setBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()), Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));
				world.setBlockState(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1), Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));
				world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1), Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE));
				
			}	
		}
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return treeStageOne_AABB;
	}


}
