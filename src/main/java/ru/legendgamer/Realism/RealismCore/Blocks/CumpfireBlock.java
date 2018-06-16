package ru.legendgamer.Realism.RealismCore.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.TimerForCoord;
import ru.legendgamer.Realism.API.BasicBlock.BasicBlockWithCustomModel;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class CumpfireBlock extends BasicBlockWithCustomModel {


	public static final PropertyInteger WITH_COAL = PropertyInteger.create("withcoal",0,5);

	public CumpfireBlock(Material materialIn, String name, float hardness, float resistanse, String hravLevel,int level, SoundType soundtype) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, soundtype);
		this.setDefaultState(this.blockState.getBaseState().withProperty(WITH_COAL, 0));

	}


	protected static final AxisAlignedBB block_AABB = new AxisAlignedBB(0.04D, 0.0D, 0.04D, 0.96D, 0.15D, 0.96D);
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
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {WITH_COAL});
	}





	protected PropertyInteger getNumberCoal()
	{
		return WITH_COAL;
	}
	protected int getCountCoal(IBlockState state)
	{
		return ((Integer)state.getValue(this.getNumberCoal())).intValue();
	}
	public IBlockState setCount(int age)
	{
		return this.getDefaultState().withProperty(this.getNumberCoal(), Integer.valueOf(age));
	}


	public boolean isRun;

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{

		if(!worldIn.isRemote) {

			int i = this.getCountCoal(state);
			if(player.getHeldItemMainhand().getItem() == Items.STICK) {
				if(worldIn.getBlockState(pos) != RegBlocks.cumpfire.getDefaultState().withProperty(getNumberCoal(), 0)) {

				}
			}


			if(player.getHeldItemMainhand().isEmpty()) {


				if(i >= 0) {

					worldIn.setBlockState(pos, this.setCount(0), 2);	
					player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.COAL,getCountCoal(state),0));
					return true;
				}
				if(i < 5) {
					return false;
				}
			}
			if(player.getHeldItemMainhand().getItem() == Items.COAL || player.getHeldItemMainhand() == new ItemStack(Items.COAL,1,1)) {

				if(i <= 5) {

					worldIn.setBlockState(pos, this.setCount(i + 1), 2);	
					player.getHeldItemMainhand().shrink(1);
					return true;
				}
				if(i > 5) {
					return false;
				}
			}
		}
		return true;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		return false;
	}
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return block_AABB;
	}
	@Nullable
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(RegBlocks.cumpfire);
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
	{
		return new ItemStack(RegBlocks.cumpfire);
	}

	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
	}



	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{

		if (entity instanceof EntityItem) {
			if(!world.isRainingAt(pos)) {
				EntityItem ei = (EntityItem)entity;
				BlockPos posItem = ei.getPosition();
				double d0 = (double)pos.getX() + 0.5D;
				double d1 = (double)pos.getY() + world.rand.nextDouble() * 6.0D / 16.0D;
				double d2 = (double)pos.getZ() + 0.5D;
				double d3 = 0.52D;
				double d4 = world.rand.nextDouble() * 0.6D - 0.3D;
				int i = this.getCountCoal(state);
				ItemStack stack = ei.getItem();
				Item item = ei.getItem().getItem();
				int cookedTime = stack.getCount();
				int count = stack.getCount();
				if(entity.collidedVertically) {

					if(world.getBlockState(pos) != RegBlocks.cumpfire.getDefaultState().withProperty(getNumberCoal(), 0)) {
						if(stack.getItem() == Items.COOKED_BEEF) {		
							if(entity.ticksExisted % 300 == 0){
								stack.setCount(0);
							}
						}
						if(stack.getItem() == Items.BEEF) {		
							if(entity.ticksExisted % 300 == 0) {
								stack.setCount(0);
								this.spawnAsEntity(world, posItem, new ItemStack(Items.COOKED_BEEF,count));
								world.setBlockState(pos, this.setCount(i - 1), 2);	
							}
							if (world.rand.nextDouble() < 0.1D)
							{
								world.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
							}

							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.1D, d1, d2 + d4, 0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.1D, d1, d2 + d4, 0.0D, 0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.1D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.1D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.1D, 0.0D, 0.0D, 0.0D);
						}
						if(stack.getItem() == Items.PORKCHOP) {		

							if(entity.ticksExisted % 300 == 0){
								stack.setCount(0);
								this.spawnAsEntity(world, posItem, new ItemStack(Items.COOKED_PORKCHOP,count));
								world.setBlockState(pos, this.setCount(i - 1), 2);	
							}
							if (world.rand.nextDouble() < 0.1D)
							{
								world.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
							}
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.1D, d1, d2 + d4, 0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.1D, d1, d2 + d4, 0.0D, 0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.1D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.1D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.1D, 0.0D, 0.0D, 0.0D);

						}
						if(stack.getItem() == Items.CHICKEN) {		

							if(entity.ticksExisted % 210 == 0){
								stack.setCount(0);
								this.spawnAsEntity(world, posItem, new ItemStack(Items.COOKED_CHICKEN,count));
								world.setBlockState(pos, this.setCount(i - 1), 2);	
							}
							if (world.rand.nextDouble() < 0.1D)
							{
								world.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
							}
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.1D, d1, d2 + d4, 0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.1D, d1, d2 + d4, 0.0D, 0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.1D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.1D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.1D, 0.0D, 0.0D, 0.0D);
							world.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.1D, 0.0D, 0.0D, 0.0D);

						}

					}
				}
			}
		}
	}
}
