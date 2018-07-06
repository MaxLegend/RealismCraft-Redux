package ru.legendgamer.Realism.RealismCore.Blocks.Workbench;



import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RBlockWorkbench extends Block{
	public RBlockWorkbench(String name)
	{
		super(Material.WOOD);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);

	}
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntity tile = world.getTileEntity(pos);
		if (tile == null || !(tile instanceof WorkbenchTile)) {
			return;
		}
		List<ItemStack> items = ((WorkbenchTile)tile).getCraftMatrixItems();
		for (ItemStack item : items) {
			if (item == ItemStack.EMPTY) continue;
			this.dropBlockAsItem(world, pos.getX(), pos.getY(), pos.getZ(), item);
		}
	}

	protected  void dropBlockAsItem(World world, int x, int y, int z, ItemStack item) {
		if (!world.isRemote && world.getGameRules().getBoolean("doTileDrops") && !world.restoringBlockSnapshots) {
			float f = 0.7f;
			double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
			double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
			double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0f - f) * 0.5;
			EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, item);
			entityitem.setDefaultPickupDelay();
			world.spawnEntity((Entity)entityitem);
		}
	}

	public  WorkbenchTile createNewTileEntity() {
		return new WorkbenchTile();
	}
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)

		{
			  playerIn.displayGui(new BlockWorkbench.InterfaceCraftingTable(worldIn, pos));

	
		}

		return true;

	}

}
