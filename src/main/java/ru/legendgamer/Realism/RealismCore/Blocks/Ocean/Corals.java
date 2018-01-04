package ru.legendgamer.Realism.RealismCore.Blocks.Ocean;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.RealismCore.Basic.BasicBlock.BasicBlockWithCustomModel;

public class Corals extends BasicBlockWithCustomModel{
	protected static final AxisAlignedBB corals_AABB = new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.8D, 0.8D);

	public Corals(Material materialIn, String name, float hardness,float resistanse, String hravLevel, int level, SoundType blockSoundType) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, blockSoundType);
	}
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return corals_AABB;
	}
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
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
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		world.destroyBlock(pos, true);
	}
	
}
