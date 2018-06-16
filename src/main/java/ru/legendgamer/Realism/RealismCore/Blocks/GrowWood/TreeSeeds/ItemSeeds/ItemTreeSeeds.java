package ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.TreeSeeds.ItemSeeds;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegBlocks;

public class ItemTreeSeeds extends Item implements IPlantable
{
	public Block blockSet;
    public ItemTreeSeeds(String name,Block blockSet)
    {
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.blockSet = blockSet;
        this.setCreativeTab(Realism.tabMain);
    }

    @Override
   public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
   {
       final IBlockState state = worldIn.getBlockState(pos);
       final ItemStack stack = playerIn.inventory.getItemStack();
 
       if(facing == EnumFacing.UP && playerIn.canPlayerEdit(pos.offset(facing), facing, stack) && state.getBlock().canSustainPlant(state, worldIn, pos, EnumFacing.UP, this) && worldIn.isAirBlock(pos.up()) && worldIn.getBlockState(pos) == Blocks.FARMLAND.getDefaultState())
       {
          worldIn.setBlockState(pos.up(),blockSet.getDefaultState());
          stack.shrink(1);
          return EnumActionResult.SUCCESS;
       }
       return EnumActionResult.FAIL;
   }

    @Override
    public net.minecraftforge.common.EnumPlantType getPlantType(IBlockAccess world, BlockPos pos)
    {
        return EnumPlantType.Plains;
    }

    @Override
    public IBlockState getPlant(IBlockAccess world, BlockPos pos)
    {
        return blockSet.getDefaultState();
    }
}