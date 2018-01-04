package ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Spruce;

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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.TimerForCoord;
import ru.legendgamer.Realism.RealismCore.RegBlocks;
import ru.legendgamer.Realism.RealismCore.Basic.BasicBlock.BasicBlockWithCustomModel;

public class GrowTreeStageTwoSpruce extends BasicBlockWithCustomModel {
	List<TimerForCoord> time =  new ArrayList<TimerForCoord>();
	//make random
	int maxHeightTree;
	int maxHeightLeaves;
	protected static final AxisAlignedBB treeStageOne_AABB = new AxisAlignedBB(0.12D, 0.0D, 0.12D, 0.88D, 1D, 0.88D);

	public GrowTreeStageTwoSpruce(Material materialIn, String name, float hardness,float resistanse, String hravLevel, int level, SoundType blockSoundType) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, blockSoundType);
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, this, 1, 0);
		time.add(new TimerForCoord(pos.getX(),pos.getY(),pos.getZ(),0));
	}
	@Override
    public Block setBlockUnbreakable()
    {
        this.setHardness(-1.0F);
        return this;
    }
	
	private boolean setBlock(BlockPos pos,World world, IBlockState state) {
		world.setBlockState(pos, state);
		return true;
	}
	
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {

		super.updateTick(world, pos, state, rand);
		maxHeightTree = rand.nextInt(25);
		maxHeightLeaves = rand.nextInt(4);
		
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
			if (time2.time == 20) {
				if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightTree, pos.getZ())).getBlock() != Blocks.LOG){
					setBlock(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()),world, RegBlocks.growtreeonespruce.getDefaultState());
				}
				
			}
			if (time2.time == 40) {
				setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
				if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightLeaves, pos.getZ())).getBlock() == Blocks.LOG){	
					setBlock(new BlockPos(pos.getX() + 1, pos.getY() + 2, pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() - 1, pos.getY() + 2, pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() + 1),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX(), pos.getY() + 2, pos.getZ() -1),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() , pos.getY(), pos.getZ() + 1),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() , pos.getY(), pos.getZ() - 1),world, RegBlocks.smallleavesspruce.getDefaultState());
					if(world.rand.nextInt(8) >= 4){
						setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()),world, RegBlocks.sprucebranch.getDefaultState());
						setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()),world, RegBlocks.sprucebranch.getDefaultState());
						setBlock(new BlockPos(pos.getX() , pos.getY(), pos.getZ() + 1),world, RegBlocks.sprucebranch.getDefaultState());
						setBlock(new BlockPos(pos.getX() , pos.getY(), pos.getZ() - 1),world, RegBlocks.sprucebranch.getDefaultState());
					} 
					setBlock(new BlockPos(pos.getX() + 1 , pos.getY(), pos.getZ() + 1),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() + 2 , pos.getY(), pos.getZ() + 2),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() - 2),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 2),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ() + 2),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() + 2 , pos.getY(), pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() - 2, pos.getY(), pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 2),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 2),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() + 3 , pos.getY(), pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX() - 3, pos.getY(), pos.getZ()),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 3),world, RegBlocks.smallleavesspruce.getDefaultState());
					setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 3),world, RegBlocks.smallleavesspruce.getDefaultState());
				}
			}
			if (time2.time == 80) {
				setBlock(new BlockPos(pos.getX(), pos.getY(), pos.getZ()),world, Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE));
			}		
		}
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

