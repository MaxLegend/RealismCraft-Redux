package ru.legendgamer.Realism.PacketSystem.Packets.Server;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;

public class HUDSyncMessageServer implements IMessage {  
	public int value;  
	public HUDSyncMessageServer(){}
	
	public HUDSyncMessageServer(int value){  
		this.value = value;   
	} 
	@Override
	public void fromBytes(ByteBuf buf) {  
		value = buf.readInt();   
		}  
	@Override 
	public void toBytes(ByteBuf buf) {   
		buf.writeInt(value); 
	}   
	
	public static class Handler implements IMessageHandler<HUDSyncMessageServer, IMessage> {

		@Override
		public IMessage onMessage(HUDSyncMessageServer message, MessageContext ctx) { 		
				IPlayerCap cap = ctx.getServerHandler().player.getCapability(PlayerCapProvider.LEVEL_CAP, null);   
				if(cap != null)        
					cap.setWaterLevel(message.value);    
				return null;    
			} 
		
	}
	
}
