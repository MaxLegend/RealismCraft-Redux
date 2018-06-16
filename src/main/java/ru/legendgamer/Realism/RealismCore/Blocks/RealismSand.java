package ru.legendgamer.Realism.RealismCore.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.BasicBlock.BasicBlock;

public class RealismSand extends BlockSand {

	public static final PropertyBool CHANGECOLOR = PropertyBool.create("iswet");

	public RealismSand(final String name) {
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		this.setDefaultState(this.blockState.getBaseState().withProperty(CHANGECOLOR, Boolean.valueOf(false)));
	}
/*	@Override

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		if(world.getBlockState(pos.east()).getBlock().getMaterial(state) == Material.WATER) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(CHANGECOLOR, Boolean.valueOf(true)));
		}
		else if(world.getBlockState(pos.east()).getBlock().getMaterial(state) != Material.WATER) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(CHANGECOLOR, Boolean.valueOf(false)));
		}
		
		 if(world.isRaining()) {
			world.setBlockState(pos, world.getBlockState(pos).withProperty(CHANGECOLOR, Boolean.valueOf(true)));
		}
	}
	*/
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {CHANGECOLOR, VARIANT});
	}

}
