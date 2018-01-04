package ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.AppleTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.TimerForCoord;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.Basic.BasicBlock.BasicBlockWithCustomModel;

public class LeavesAppleTree extends BasicBlockWithCustomModel{
	List<TimerForCoord> time =  new ArrayList<TimerForCoord>();
	protected static final AxisAlignedBB leaves_AABB = new AxisAlignedBB(0D, 0.0D, 0D, 1D, 1D, 1D);

	public LeavesAppleTree(Material materialIn, String name, float hardness,
			float resistanse, String hravLevel, int level, SoundType soundtype) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, soundtype);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return leaves_AABB;
	}
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, this, 1, 0);
		time.add(new TimerForCoord(pos.getX(),pos.getY(),pos.getZ(),0));
	}
	private boolean setBlock(BlockPos pos,World world, IBlockState state) {
		world.setBlockState(pos, state);
		return true;
	}
	

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		//	super.updateTick(world, pos, state, rand);

		TimerForCoord time2 = null;
		for (TimerForCoord t : time) {
			if (t != null && t.x == pos.getX() && t.y == pos.getY() && t.z == pos.getZ()) {
				time2 = t;
				++t.time;
			}
		} 
		if (time2 == null) return;
		world.scheduleBlockUpdate(pos, this, 1, 0);


		if(time2.time % 60 == 0) {
			if(!world.isAirBlock(pos.down())) {

			}
			if(world.isAirBlock(pos.down())) {
				


							
					Realism.proxy.particleClient(world, pos, rand);
			
			}
		}

		if(time2.time % 240 == 0) {
			if(world.isAirBlock(pos.down())){


			}

			//осталось реализовать генерацию скопления листвы снизу под деревом

		}
	}



	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	{
		return this.blockMapColor;
	}
	@Override
	public  void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		entity.motionX *= 0.5D;
		entity.motionZ *= 0.5D;
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
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

}

