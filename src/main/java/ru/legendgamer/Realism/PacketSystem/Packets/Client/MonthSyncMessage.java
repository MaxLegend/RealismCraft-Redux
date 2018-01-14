package ru.legendgamer.Realism.PacketSystem.Packets.Client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.legendgamer.Realism.Capability.WorldCAP.DateProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.IDate;

public class MonthSyncMessage implements IMessage {  
	public byte value;  
	public MonthSyncMessage(){}

	public MonthSyncMessage(byte value){  
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

	public static class Handler implements IMessageHandler<MonthSyncMessage, IMessage> {

		@Override
		public IMessage onMessage(MonthSyncMessage message, MessageContext ctx) { 

			EntityPlayerSP player = Minecraft.getMinecraft().player;   
			if(player != null) {
				IDate cap = player.getCapability(DateProvider.DATE, null);   
				if(cap != null)    {
					cap.setMonth(message.value);  
				}
			}
				return null;    
			} 
		}
	}


		
