package ru.legendgamer.Realism.PacketSystem.Packets.Server;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.DateProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.IDate;

public class MonthSyncMessageServer implements IMessage {  
	public byte value;  
	public MonthSyncMessageServer(){}
	
	public MonthSyncMessageServer(byte value){  
		this.value = value;   
	} 
	@Override
	public void fromBytes(ByteBuf buf) {  
		value = buf.readByte();   
		}  
	@Override 
	public void toBytes(ByteBuf buf) {   
		buf.writeByte(value); 
	}   

	public static class Handler implements IMessageHandler<MonthSyncMessageServer, IMessage> {

		@Override
		public IMessage onMessage(MonthSyncMessageServer message, MessageContext ctx) { 	
			IDate date = ctx.getServerHandler().player.getEntityWorld().getCapability(DateProvider.DATE, null);   
			if(date != null)    
			
				date.setMonth(message.value);    
			return null;    
		}

	}
}



