package ru.legendgamer.Realism.RealismCore.Items;

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
import ru.legendgamer.Realism.API.BasicBlock.DevBasicBlock;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegItems;

public class DryVine extends DevBasicBlock{
	protected static final AxisAlignedBB dryvine_AABB = new AxisAlignedBB(0D, 0.0D, 0D, 1D, 0.1D, 1D);
	//i have plan for this block
	public DryVine(Material materialIn, String name, float hardness,
			float resistanse, String hravLevel, int level, SoundType soundtype) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, soundtype);
		this.setCreativeTab(Realism.tabDev);
	}
	
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return dryvine_AABB;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return RegItems.dryvineitem;
    }
}

