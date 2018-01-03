package ru.legendgamer.Realism.PacketSystem.Packets;

import net.minecraft.entity.player.EntityPlayer;
import ru.legendgamer.Realism.Capability.WaterCAP.PlayerCapProvider;
import ru.legendgamer.Realism.PacketSystem.AbstractPacket;

public final class HUDSyncMessage extends AbstractPacket
{
	public HUDSyncMessage() {}
	public HUDSyncMessage(int level)
	{
		buf().writeInt(level);
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		if (player != null)
		    player.getCapability(PlayerCapProvider.LEVEL_CAP, null).setWaterLevel(buf().readInt());
	}
}