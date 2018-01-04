package ru.legendgamer.Realism.PacketSystem.Packets.Server;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.legendgamer.Realism.RealismCore.GuiHandler;
import ru.legendgamer.Realism.RealismCore.Realism;

public class OpenInventoryMessage implements IMessage {
    public OpenInventoryMessage() { }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<OpenInventoryMessage, IMessage> {

  
        @Override
        public IMessage onMessage(OpenInventoryMessage message, MessageContext ctx) {

            EntityPlayerMP player = ctx.getServerHandler().player;
            player.openGui(Realism.INSTANCE, GuiHandler.INVENTORY_GUI_ID, player.getEntityWorld(), (int)player.posX, (int)player.posY, (int)player.posZ);
            return null;
        }
    }
}
