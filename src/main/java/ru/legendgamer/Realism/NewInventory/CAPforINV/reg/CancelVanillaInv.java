package ru.legendgamer.Realism.NewInventory.CAPforINV.reg;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.PacketSystem.NetworkHandler;
import ru.legendgamer.Realism.PacketSystem.Packets.Server.OpenInventoryMessage;

public class CancelVanillaInv {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void cancel(GuiOpenEvent event) {
		EntityPlayer ep = Minecraft.getMinecraft().player;
		if( ep !=null && !ep.isCreative()) {	
			if(event.getGui() instanceof GuiInventory) {		
				NetworkHandler.network.sendToServer(new OpenInventoryMessage());
				event.setCanceled(true);
			}
		}

	}
}
