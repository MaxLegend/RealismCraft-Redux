package ru.legendgamer.Realism.RealismCore;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.legendgamer.Realism.NewInventory.ContainerCustomInv;
import ru.legendgamer.Realism.NewInventory.GUICustomInv;
import ru.legendgamer.Realism.NewInventory.CAPforINV.CAPCustomInventoryProvider;
import ru.legendgamer.Realism.NewInventory.CAPforINV.ICAPCustomInventory;

public class GuiHandler implements IGuiHandler{
	

	 public static final int INVENTORY_GUI_ID = 0;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		ItemStack current = player.getActiveItemStack();
		if (current != null) {
		}
		 ICAPCustomInventory inv = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
		   switch (ID)
	        {
	
	            case INVENTORY_GUI_ID: return new ContainerCustomInv(player.inventory, inv.getInventory(), player);
	        
	            default: return null;
	        }	
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		   ICAPCustomInventory inv = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
		  switch (ID)
	        {

	        	case INVENTORY_GUI_ID: return new GUICustomInv(player, player.inventory, inv.getInventory());
	   
	        }

			
		return null;
	}

}
