package ru.legendgamer.Realism.PacketSystem;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import ru.legendgamer.Realism.PacketSystem.Packets.Client.HUDSyncMessage;
import ru.legendgamer.Realism.PacketSystem.Packets.Server.HUDSyncMessageServer;
import ru.legendgamer.Realism.PacketSystem.Packets.Server.MonthSyncMessageServer;
import ru.legendgamer.Realism.PacketSystem.Packets.Server.OpenInventoryMessage;
import ru.legendgamer.Realism.RealismCore.Realism;

public class NetworkHandler {

    public static SimpleNetworkWrapper network;

    public static void init() {

        network = NetworkRegistry.INSTANCE.newSimpleChannel(Realism.MODID);

        network.registerMessage(HUDSyncMessage.Handler.class, HUDSyncMessage.class, 0, Side.CLIENT);
        network.registerMessage(HUDSyncMessageServer.Handler.class, HUDSyncMessageServer.class, 1, Side.SERVER);
        network.registerMessage(OpenInventoryMessage.Handler.class, OpenInventoryMessage.class, 2, Side.SERVER);
        network.registerMessage(MonthSyncMessageServer.Handler.class, MonthSyncMessageServer.class, 3, Side.SERVER);
        
     //   network.registerMessage(PlayerWeightMessage.Handler.class, PlayerWeightMessage.class, 4, Side.CLIENT);
  //      network.registerMessage(PlayerWeightMessageServer.Handler.class, PlayerWeightMessageServer.class, 5, Side.SERVER);
    }


	public void sendTo(final IMessage message, final EntityPlayerMP player) {
		network.sendTo(message, player);
	}

	public void sendToServer(final IMessage message){
		network.sendToServer(message);
	}
}