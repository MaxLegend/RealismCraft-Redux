package ru.legendgamer.Realism.API.BasicBlock;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class BasicFallingLog extends BlockRotatedPillar {

	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);
	public static final PropertyBool SETTET = PropertyBool.create("setet");

	public BasicFallingLog(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype,CreativeTabs cTab) {
		super(materialIn);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setSoundType(soundtype);
		this.setHardness(hardness);
		this.setResistance(resistanse);
		this.setCreativeTab(cTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.Y).withProperty(SETTET, false));

	}

	public static enum EnumAxis implements IStringSerializable {
		X("x"),
		Y("y"),
		Z("z"),
		NONE("none");

		private final String name;

		private EnumAxis(String name)
		{
			this.name = name;
		}

		public String toString()
		{
			return this.name;
		}

		public static BasicFallingLog.EnumAxis fromFacingAxis(EnumFacing.Axis axis)
		{
			switch (axis)
			{
			case X:
				return X;
			case Y:
				return Y;
			case Z:
				return Z;
			default:
				return NONE;
			}
		}

		public String getName()
		{
			return this.name;
		}
	}
	@Override
	public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis)
	{
		IBlockState state = world.getBlockState(pos);
		for (net.minecraft.block.properties.IProperty<?> prop : state.getProperties().keySet())
		{
			if (prop.getName().equals("axis"))
			{
				world.setBlockState(pos, state.cycleProperty(prop));
				return true;
			}
		}
		return false;
	}

	public IBlockState withRotation(IBlockState state, Rotation rot)
	{
		switch (rot)
		{
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:

			switch ((EnumFacing.Axis)state.getValue(AXIS))
			{
			case X:
				return state.withProperty(AXIS, EnumFacing.Axis.Z);
			case Z:
				return state.withProperty(AXIS, EnumFacing.Axis.X);
			default:
				return state;
			}

		default:
			return state;
		}
	}

	public IBlockState getStateFromMeta(int meta)
	{
		EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Y;
		int i = meta & 12;

		if (i == 4)
		{
			enumfacing$axis = EnumFacing.Axis.X;
		}
		else if (i == 8)
		{
			enumfacing$axis = EnumFacing.Axis.Z;
		}

		return this.getDefaultState().withProperty(AXIS, enumfacing$axis);
	}

	public int getMetaFromState(IBlockState state)
	{
		int i = 0;
		EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis)state.getValue(AXIS);

		if (enumfacing$axis == EnumFacing.Axis.X)
		{
			i |= 4;
		}
		else if (enumfacing$axis == EnumFacing.Axis.Z)
		{
			i |= 8;
		}

		return i;
	}

	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {AXIS,SETTET});
	}

	protected ItemStack getSilkTouchDrop(IBlockState state)
	{
		return new ItemStack(Item.getItemFromBlock(this));
	}
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(AXIS, facing.getAxis());
	}
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}

	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
	}


	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if(getState(state)) return;
		if (!world.isRemote)
		{		
			this.checkFallableDown(world, pos);
		}
	}


	int lenght(World world, BlockPos pos) {
		return lenght(world, pos,0);
	}

	int lenght(World world, BlockPos pos,int curLen){
		/*if(world.getBlockState(pos.up()) == RegBlocks.test_falling_log.getDefaultState())
		{	

			return lenght(world, pos.add(0,1,0),curLen + 1); 
		}
		else 
		*/
		{
			return curLen;
		}
	}

	protected PropertyBool getBlockStated()
	{
		return SETTET;
	}
	protected boolean getState(IBlockState state)
	{
		return state.getValue(this.getBlockStated());
	}
	public IBlockState setState(boolean states)
	{
		return this.getDefaultState().withProperty(this.getBlockStated(), Boolean.valueOf(states));
	}
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

		world.setBlockState(pos, world.getBlockState(pos).withProperty(SETTET, Boolean.valueOf(true)));

	}
	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		if(getState(state)) return;
		this.checkDestroy(world, pos, state);
	}

	public void checkDestroy(World world, BlockPos pos, IBlockState state) 
	{
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if(!world.isRemote) {
			this.lenght(world, pos);
			int highTree = this.lenght(world, pos) + 1;
			int var = world.rand.nextInt(4);
			System.out.println(var);
			if(var == 0) { 
				for(int i = 1; i < highTree; i++) {
					world.setBlockState(new BlockPos(x, y + i, z + i), state.withProperty(AXIS, EnumFacing.Axis.Z));
					world.setBlockToAir(new BlockPos(x, y + i, z));
				}
			} 
			if(var == 1) { 
				for(int i = 1; i < highTree; i++) {
					world.setBlockState(new BlockPos(x, y + i, z - i), state.withProperty(AXIS, EnumFacing.Axis.Z));
					world.setBlockToAir(new BlockPos(x, y + i, z));
				}
			} 
			if(var == 2) { 
				for(int i = 1; i < highTree; i++) {
					world.setBlockState(new BlockPos(x + i, y + i, z), state.withProperty(AXIS, EnumFacing.Axis.X));
					world.setBlockToAir(new BlockPos(x, y + i, z));
				}
			}
			if(var == 3) { 
				for(int i = 1; i < highTree; i++) {
					world.setBlockState(new BlockPos(x - i, y + i, z), state.withProperty(AXIS, EnumFacing.Axis.X));
					world.setBlockToAir(new BlockPos(x, y + i, z));
				}
			}
		}
	}



	private void checkFallableDown(World world, BlockPos pos)
	{	
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {

				if(world.isAirBlock(pos.down())) {
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos));
					this.onStartFalling(efb);
					world.spawnEntity(efb);
				}
			}
		}
	}

	protected void onStartFalling(EntityFallingBlock fallingEntity)
	{
	}


	public int tickRate(World worldIn)
	{
		return 2;
	}

	public static boolean canFallThrough(IBlockState state)
	{
		Block block = state.getBlock();
		Material material = state.getMaterial();
		return block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA;
	}

	public void onEndFalling(World worldIn, BlockPos pos, IBlockState p_176502_3_, IBlockState p_176502_4_)
	{
	}

	public void onBroken(World worldIn, BlockPos pos)
	{
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if (rand.nextInt(16) == 0)
		{
			BlockPos blockpos = pos.down();

			if (canFallThrough(worldIn.getBlockState(blockpos)))
			{
				double d0 = (double)((float)pos.getX() + rand.nextFloat());
				double d1 = (double)pos.getY() - 0.05D;
				double d2 = (double)((float)pos.getZ() + rand.nextFloat());
				worldIn.spawnParticle(EnumParticleTypes.FALLING_DUST, d0, d1, d2, 0.0D, 0.0D, 0.0D, Block.getStateId(stateIn));
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public int getDustColor(IBlockState state)
	{
		return -16777216;
	}

}
