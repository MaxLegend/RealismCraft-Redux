package ru.legendgamer.Realism.RealismCore.Blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.API.BasicBlock.BasicBlock;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class TestGen extends BasicBlock{

	public TestGen(Material materialIn, String name, float hardness,
			float resistanse, SoundType soundtype) {
		super(materialIn, name, hardness, resistanse, soundtype);
		// TODO Auto-generated constructor stub
	}
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	for(int i = 0; i < 8; i++)
    	world.setBlockState(new BlockPos(pos.getX(),pos.getY() + i, pos.getZ()), RegBlocks.test_falling_log.getDefaultState());
        return true;
    }
}
