package ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Basic;

import java.util.Random;

import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.BasicBlock.BasicBlockWithCustomModel;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class DefaultOakGrowLog extends BasicBlockWithCustomModel {
	public static final PropertyInteger HIGH = PropertyInteger.create("high", 0, 16);
	public static final PropertyInteger RANDOM = PropertyInteger.create("rand", 0, 16);

	int maxHeightLeaves;
	protected static final AxisAlignedBB treeStageOne_AABB = new AxisAlignedBB(0.3D, 0.0D, 0.3D, 0.7D, 1D, 0.7D);

	public DefaultOakGrowLog(Material materialIn, String name, float hardness,float resistanse, String hravLevel, int level, SoundType blockSoundType) {
		super(materialIn, name, hardness, resistanse, hravLevel, level, blockSoundType);
		this.setTickRandomly(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(HIGH, 1).withProperty(RANDOM, 0));
	}

	protected PropertyInteger getStateHigh()
	{ return HIGH; }
	protected PropertyInteger getStateRand()
	{ return RANDOM; }

	protected int getRand(IBlockState state)
	{ 
		return state.getValue(this.getStateRand());
	}

	protected int getHigh(IBlockState state)
	{
		return state.getValue(this.getStateHigh());
	}

	public IBlockState setHigh(int high)
	{ 
		return this.getDefaultState().withProperty(this.getStateHigh(), Integer.valueOf(high)); 
	}
	public IBlockState setRand(int rand)
	{ 
		return this.getDefaultState().withProperty(this.getStateRand(), Integer.valueOf(rand)); 
	}
	@Override
	public int getMetaFromState(IBlockState state)
	{
		return 0;
	}
	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] {HIGH,RANDOM});
	}

	int maxHigh;
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, this, 40, 0);
	}
	private static int high = 5;

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);
        world.scheduleBlockUpdate(pos, this, 40, 0);

    

        if(!world.isRemote) {
            if(world.isAirBlock(pos.up())) {
                if(this.getRand(state) == 0){
                    if(this.getHigh(state) == high){
                        int randi = rand.nextInt(7) + 5;
                        world.setBlockState(pos.up(), this.getDefaultState().withProperty(HIGH, this.getHigh(state) + 1).withProperty(RANDOM, randi));
                    }else
                        world.setBlockState(pos.up(), this.getDefaultState().withProperty(HIGH, this.getHigh(state) + 1));
                }else if(this.getHigh(state) < this.getRand(state)){
                    world.setBlockState(pos.up(), this.getDefaultState().withProperty(HIGH, this.getHigh(state) + 1).withProperty(RANDOM, this.getRand(state)));
                }
            }
        }

    }
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return treeStageOne_AABB;
	}

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
	@SideOnly(Side.CLIENT)
	public boolean isFullCube(IBlockState state) {
		return false;
	}

}

/*
 * 			if(world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightTree, pos.getZ())).getBlock() != Blocks.LOG 
					|| world.getBlockState(new BlockPos(pos.getX(), pos.getY() - maxHeightTree, pos.getZ())).getBlock() != RegBlocks.oakgrowlog){

				int stage = this.getStage(state);

				if(world.isAirBlock(posUp)) {		

					world.setBlockState(pos, RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, 1));
					world.setBlockState(pos.up(), RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, 0));

				} else 
					if(world.getBlockState(posUp) == RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, this.getStage(state)) && stage < 3) {
						world.setBlockState(pos, RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, this.getStage(state) + 1));

					} else 
						if(stage >= 3) {
							world.setBlockState(pos, Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));
							if(world.getBlockState(pos.down()) == RegBlocks.oakgrowlog.getDefaultState().withProperty(STAGE, this.getStage(state))) {
								world.setBlockState(pos, Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.OAK));
							}
						}


			}
 */

