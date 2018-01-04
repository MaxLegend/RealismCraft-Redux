package ru.legendgamer.Realism.RealismCore.Items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.TimerForCoord;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;
import ru.legendgamer.Realism.RealismCore.RegItems;
import ru.legendgamer.Realism.RealismCore.Basic.BasicBlock.DevBasicBlock;

public class WetVine extends DevBasicBlock {
	List<TimerForCoord> time =  new ArrayList<TimerForCoord>();
	protected static final AxisAlignedBB wetvine_AABB = new AxisAlignedBB(0D, 0.0D, 0D, 1D, 0.1D, 1D);
	public WetVine(Material materialIn, String name, float hardness,
 
			float resistanse, String hravLevel, int level, SoundType soundtype) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, soundtype);
		this.setCreativeTab(Realism.tabDev);
	}
	@Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return RegItems.wetvineitem;
    }
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return wetvine_AABB;
    }
	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, this, 1, 0);
		time.add(new TimerForCoord(pos.getX(),pos.getY(),pos.getZ(),0));
	}
	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {

		super.updateTick(world, pos, state, rand);

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
			if (time2.time >= 80 && world.isDaytime() && !world.isRaining()) {
					world.setBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()), RegBlocks.dryvine.getDefaultState());
			}
		}

	}
	
	
}