package ru.legendgamer.Realism.PacketSystem.Packets;

import net.minecraft.entity.player.EntityPlayer;
import ru.legendgamer.Realism.PacketSystem.AbstractPacket;

public final class ColdMessageClient extends AbstractPacket {
	
	public ColdMessageClient() {}
	public ColdMessageClient(boolean temp)
	{
        buf().writeBoolean(temp);
	}
	
	@Override
	public void handleClientSide(EntityPlayer player)
    {
		
		  
	}
}