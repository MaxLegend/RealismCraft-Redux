package ru.legendgamer.Realism.RealismCore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.legendgamer.Realism.NewInventory.ContainerCustomInv;
import ru.legendgamer.Realism.NewInventory.GUICustomInv;
import ru.legendgamer.Realism.NewInventory.CAPforINV.CAPCustomInventoryProvider;
import ru.legendgamer.Realism.NewInventory.CAPforINV.ICAPCustomInventory;
import ru.legendgamer.Realism.RealismCore.Blocks.Workbench.ContainerRWorkbench;
import ru.legendgamer.Realism.RealismCore.Blocks.Workbench.GuiWorkbench;

public class GuiHandler implements IGuiHandler{
	

	 public static final int INVENTORY_GUI_ID = 0;
	 public static final int RWORKBENCH_GUI_ID = 1;


	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		ItemStack current = player.getActiveItemStack();
		if (current != null) {
		}
		 ICAPCustomInventory inv = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
		   switch (ID)
	        {
	
	            case INVENTORY_GUI_ID: return new ContainerCustomInv(player.inventory, inv.getInventory(), player);
	            case RWORKBENCH_GUI_ID: return new ContainerWorkbench(player.inventory, world, new BlockPos(x,y,z));
	            default: return null;
	        }	
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		   ICAPCustomInventory inv = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
		  switch (ID)
	        {

	        	case INVENTORY_GUI_ID: return new GUICustomInv(player, player.inventory, inv.getInventory());
	        	   case RWORKBENCH_GUI_ID: return new GuiWorkbench(new ContainerRWorkbench(player.inventory, world,  new BlockPos(x,y,z)));
	        }

			
		return null;
	}

}
