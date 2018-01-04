package ru.legendgamer.Realism.RealismCore.Blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegItems;
import ru.legendgamer.Realism.RealismCore.Basic.BasicBlock.DevBasicBlock;

public class MossBlock extends DevBasicBlock{
	 
	protected static final AxisAlignedBB moss_AABB = new AxisAlignedBB(0D, 0.0D, 0D, 1D, 0.1D, 1D);
	public MossBlock(final Material materialIn, final String name, float hardness,float resistanse, String hravLevel, int level, SoundType soundtype) {
		super(materialIn, hravLevel, resistanse, resistanse, hravLevel, level, soundtype);
        this.setCreativeTab(Realism.tabDev);
	}

	
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return moss_AABB;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return RegItems.mossitem;
    }
}

