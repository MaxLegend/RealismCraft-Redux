package ru.legendgamer.Realism.RealismCore.Blocks.Cactus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
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
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;
import ru.legendgamer.Realism.RealismCore.Basic.BasicBlock.BasicBlockWithCustomModel;

public class OneStageCactus extends BasicBlockWithCustomModel {
	List<TimerForCoord> time =  new ArrayList<TimerForCoord>();
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	protected static final AxisAlignedBB treeStageOne_AABB = new AxisAlignedBB(0.4D, 0.0D, 0.4D, 0.6D, 1D, 0.6D);

	public OneStageCactus(Material materialIn, String name, float hardness,float resistanse, String hravLevel, int level, SoundType blockSoundType) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, blockSoundType);
        this.setCreativeTab(Realism.tabDev);
	}
	@Override
	public Block setBlockUnbreakable()
	{
		this.setHardness(-1.0F);
		return this;
	}
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos)
	{
		return world.getBlockState(pos.down()).getBlock() == Blocks.SAND || world.getBlockState(pos.down()).getBlock() == RegBlocks.realismsand || world.getBlockState(pos.down()).getBlock() == RegBlocks.realismredsand;
	}

	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, this, 1, 0);
		time.add(new TimerForCoord(pos.getX(),pos.getY(),pos.getZ(),0));
	}
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(world, pos, state, rand);

		int maxHeightCactus = rand.nextInt(4);

		int variator = rand.nextInt(3);

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
			if (time2.time == 40) {
				world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), Blocks.CACTUS.getDefaultState());
				if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightCactus, pos.getZ())).getBlock() != Blocks.CACTUS){	

					world.setBlockState(new BlockPos(pos.getX(), pos.getY()+1, pos.getZ()), RegBlocks.cactussmall.getDefaultState());
				}
				if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightCactus, pos.getZ())).getBlock() == Blocks.CACTUS){	
			
					if(variator == 0 || variator == 1){
						world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1), RegBlocks.cactusside.getDefaultState().withProperty(FACING, EnumFacing.NORTH), 2);
					}
					if(variator == 1){
						world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1), RegBlocks.cactusside.getDefaultState().withProperty(FACING, EnumFacing.SOUTH), 2);
					}
					if(variator == 2 || variator == 1){
						world.setBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ()), RegBlocks.cactusside.getDefaultState().withProperty(FACING, EnumFacing.EAST), 2);
					}
					if(variator == 3 || variator == 2){
						world.setBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ()), RegBlocks.cactusside.getDefaultState().withProperty(FACING, EnumFacing.WEST), 2);
					}
				}
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

