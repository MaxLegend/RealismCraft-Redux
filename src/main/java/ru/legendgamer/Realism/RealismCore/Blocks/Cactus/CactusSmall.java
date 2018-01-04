package ru.legendgamer.Realism.RealismCore.Blocks.Cactus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;
import ru.legendgamer.Realism.RealismCore.Basic.BasicBlock.BasicBlockWithCustomModel;

public class CactusSmall extends BasicBlockWithCustomModel{
	List<TimerForCoord> time =  new ArrayList<TimerForCoord>();
	protected static final AxisAlignedBB cactussmall = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 0.31D, 0.625D);
	public CactusSmall(Material materialIn, String name, float hardness,
			float resistanse, String hravLevel, int level, SoundType soundtype) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, soundtype);
        this.setCreativeTab(Realism.tabDev);
	}
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return cactussmall;
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
				
				world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), RegBlocks.cactusone.getDefaultState());
				
			}		
		}
	}
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	@Override
    public boolean canPlaceBlockAt(World world, BlockPos pos)
    {
        return world.getBlockState(pos.down()).getBlock() == Blocks.SAND || world.getBlockState(pos.down()).getBlock() == RegBlocks.realismsand || world.getBlockState(pos.down()).getBlock() == RegBlocks.realismredsand;
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
