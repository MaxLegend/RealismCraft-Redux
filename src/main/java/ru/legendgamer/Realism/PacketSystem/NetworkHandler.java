package ru.legendgamer.Realism.PacketSystem;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.legendgamer.Realism.PacketSystem.Packets.HUDSyncMessage;
import ru.legendgamer.Realism.RealismCore.Realism;

public final class NetworkHandler
{
	private static short id;
	public static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper(Realism.MODID);

	public NetworkHandler()
	{

		register(HUDSyncMessage.class, Side.CLIENT);
	}

	private void register(Class<? extends AbstractPacket> packet, Side side)
	{
		try
		{
			NETWORK.registerMessage(packet.newInstance(), packet, id++, side);
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
}