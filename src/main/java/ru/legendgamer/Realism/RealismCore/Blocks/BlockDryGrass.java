package ru.legendgamer.Realism.RealismCore.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.RealismCore.Realism;

public class BlockDryGrass extends Block
{

    public BlockDryGrass(final Material materialIn, final String name, float hardness,float resistanse, SoundType soundtype) {
        super(materialIn);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setSoundType(soundtype);
        this.setHardness(hardness);
        this.setResistance(resistanse);
        this.setCreativeTab(Realism.tabMain);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
        super.updateTick(world, pos, state, random);
        world.scheduleBlockUpdate(pos, this, 1, 0);
        if(!world.isRemote){
            if(world.getBlockState(new BlockPos(pos.getX(),pos.getY() + 1,pos.getZ())).getBlock() != Blocks.AIR || world.getBlockState(new BlockPos(pos.getX(),pos.getY() + 1,pos.getZ())).getBlock().getMaterial(Blocks.WATER.getDefaultState()) == Material.WATER){
                world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()),Blocks.DIRT.getDefaultState());
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack itemStack) {
        world.scheduleBlockUpdate(pos, this, 1, 0);
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Blocks.DIRT.getItemDropped(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), rand, fortune);
    }
}
