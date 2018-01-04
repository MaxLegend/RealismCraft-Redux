package ru.legendgamer.Realism.RealismCore.Blocks.Cactus;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.legendgamer.Realism.API.BlockSide;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class SideCactus extends BlockSide
{
	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis", EnumFacing.Axis.class);
    protected static final AxisAlignedBB[] SIDE_AABB = new AxisAlignedBB[] {
    		
    		new AxisAlignedBB(0.375D, 0.375D, 0.75D, 0.625D, 0.625D, 1D),
    		new AxisAlignedBB(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 0.25D),
    		new AxisAlignedBB(0.0D, 0.375D, 0.375D, 0.25D, 0.625D, 0.625D), 
    		new AxisAlignedBB(0.75D, 0.375D, 0.375D, 1D, 0.625D, 0.625D)
    		
    };

    public SideCactus(String name)
    {
        super(name, Material.WOOD, SIDE_AABB);
        this.setHardness(1.2F);
        this.setCreativeTab(Realism.tabDev);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }


    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);

            worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Items.STICK)));
     
        
    }
 
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos.up()).getBlock();
        return state;
    }
    @Override
	public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos,IBlockState state, int fortune) {
		Random rand = new Random();
		java.util.List<ItemStack> ret = super.getDrops(world, pos, state,fortune);
		ret.add(new ItemStack(Item.getItemFromBlock(RegBlocks.cactussmall), 1, 0));
		return ret;
	}
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta & 3));
    }
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        EnumFacing enumfacing = EnumFacing.fromAngle((double)placer.rotationYaw);
        worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | state.getValue(FACING).getHorizontalIndex();
            i |= 8;
            i |= 4;
        

        return i;
    }
    
}