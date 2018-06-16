package ru.legendgamer.Realism.RealismCore.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.RealismCore.Realism;

public class Ash extends Block{

	protected static final AxisAlignedBB ash_AABB = new AxisAlignedBB(0D, 0.0D, 0D, 1D, 0.0625D, 1D);

	public Ash(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setSoundType(soundtype);
		this.setHardness(hardness);
		this.setResistance(resistanse);
		this.setCreativeTab(Realism.tabMain);
	}

	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
	
		return ash_AABB;
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
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if(entity.isSprinting()){
				double d5 = (double)pos.getX() + world.rand.nextDouble();
				double d10 = (double)pos.getY() + world.rand.nextDouble();
				double d15 = (double)pos.getZ() + world.rand.nextDouble();
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d5, d10, d15, 0.0D, 0.0D, 0.0D);
		}
		if(entity.collidedVertically){
			if(entity.motionX != 0 && entity.motionZ != 0) {
			double d6 = (double)pos.getX() + world.rand.nextDouble();
			double d7 = (double)pos.getY() + world.rand.nextDouble();
			double d8 = (double)pos.getZ() + world.rand.nextDouble();
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d6, d7, d8, 0.0D, 0.0D, 0.0D);
			}
		}
		
	}

}
