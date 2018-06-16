package ru.legendgamer.Realism.RealismCore.Blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.BasicRegistry.BasicRegistry;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class RealismLog extends BlockLog {
	
	public static final PropertyBool SETTET = PropertyBool.create("setet");
	
	public RealismLog(final String name) {
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		   this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.OAK).withProperty(LOG_AXIS, BlockLog.EnumAxis.Y).withProperty(SETTET, false));
	}
	
	 public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType>create("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>()
	    {
	        public boolean apply(@Nullable BlockPlanks.EnumType p_apply_1_)
	        {
	            return p_apply_1_.getMetadata() < 4;
	        }
	    });


	    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos)
	    {
	    	BlockPlanks.EnumType enumtype = (BlockPlanks.EnumType)state.getValue(VARIANT);

	        switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
	        {
	            case X:
	            case Z:
	            case NONE:
	            default:

	                switch (enumtype)
	                {
	                    case OAK:
	                    default:
	                        return BlockPlanks.EnumType.SPRUCE.getMapColor();
	                    case SPRUCE:
	                        return BlockPlanks.EnumType.DARK_OAK.getMapColor();
	                    case BIRCH:
	                        return MapColor.QUARTZ;
	                    case JUNGLE:
	                        return BlockPlanks.EnumType.SPRUCE.getMapColor();
	                }

	            case Y:
	                return enumtype.getMapColor();
	        }
	    }

	    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	    {
	        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.OAK.getMetadata()));
	        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.SPRUCE.getMetadata()));
	        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.BIRCH.getMetadata()));
	        items.add(new ItemStack(this, 1, BlockPlanks.EnumType.JUNGLE.getMetadata()));
	    }

	    public IBlockState getStateFromMeta(int meta)
	    {
	        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, BlockPlanks.EnumType.byMetadata((meta & 3) % 4));

	        switch (meta & 12)
	        {
	            case 0:
	                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Y);
	                break;
	            case 4:
	                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.X);
	                break;
	            case 8:
	                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z);
	                break;
	            default:
	                iblockstate = iblockstate.withProperty(LOG_AXIS, BlockLog.EnumAxis.NONE);
	        }

	        return iblockstate;
	    }

	    @SuppressWarnings("incomplete-switch")
	    public int getMetaFromState(IBlockState state)
	    {
	        int i = 0;
	        i = i | ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();

	        switch ((BlockLog.EnumAxis)state.getValue(LOG_AXIS))
	        {
	            case X:
	                i |= 4;
	                break;
	            case Z:
	                i |= 8;
	                break;
	            case NONE:
	                i |= 12;
	        }

	        return i;
	    }

	    protected BlockStateContainer createBlockState()
	    {
	        return new BlockStateContainer(this, new IProperty[] {VARIANT, LOG_AXIS, SETTET});
	    }

		public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
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
			
			if(world.getBlockState(pos.up()).getBlock() == Blocks.LOG)
			{	

				return lenght(world, pos.add(0,1,0),curLen + 1); 
			}
			else 
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
			
				if(var == 0) { 
					for(int i = 1; i < highTree; i++) {
						world.setBlockState(new BlockPos(x, y + i, z + i), state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z));
						world.setBlockToAir(new BlockPos(x, y + i, z));
		
					}
				} 
				if(var == 1) { 
					for(int i = 1; i < highTree; i++) {
						world.setBlockState(new BlockPos(x, y + i, z - i), state.withProperty(LOG_AXIS, BlockLog.EnumAxis.Z));
						world.setBlockToAir(new BlockPos(x, y + i, z));
		
					}
				} 
				if(var == 2) { 
					for(int i = 1; i < highTree; i++) {
						world.setBlockState(new BlockPos(x + i, y + i, z), state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X));
						world.setBlockToAir(new BlockPos(x, y + i, z));

					}
				}
				if(var == 3) { 
					for(int i = 1; i < highTree; i++) {
						world.setBlockState(new BlockPos(x - i, y + i, z), state.withProperty(LOG_AXIS, BlockLog.EnumAxis.X));
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
		public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
		{
			worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
		}
	    protected ItemStack getSilkTouchDrop(IBlockState state)
	    {
	        return new ItemStack(Item.getItemFromBlock(this), 1, ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata());
	    }

	    public int damageDropped(IBlockState state)
	    {
	        return ((BlockPlanks.EnumType)state.getValue(VARIANT)).getMetadata();
	    }
}