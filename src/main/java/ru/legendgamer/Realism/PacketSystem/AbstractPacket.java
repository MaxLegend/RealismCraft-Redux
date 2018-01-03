package ru.legendgamer.Realism.PacketSystem;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Ivasik and Dahaka
 */
public abstract class AbstractPacket implements IMessage, IMessageHandler<AbstractPacket, AbstractPacket>
{
	private ByteBuf buf;

	@Override
	public AbstractPacket onMessage(final AbstractPacket message, final MessageContext ctx)
    {
		if (ctx.side.isServer())
			handleServerSide(ctx.getServerHandler().player);
		else
			handleClientSide(getClientPlayer());
		return null;
	}

	public ByteBuf buf()
	{
		return buf != null ? buf : (buf = Unpooled.buffer());
	}

	public void handleClientSide(final EntityPlayer player) {}
	public void handleServerSide(final EntityPlayer player) {}

	@Override
	public final void fromBytes(ByteBuf buf)
	{
		this.buf = buf;
	}

	@Override
	public final void toBytes(ByteBuf buf)
	{
		if (buf != null)
			buf.writeBytes(this.buf);
	}

	@SideOnly(Side.CLIENT)
	private EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().player;
	}
}