package ru.legendgamer.Realism.PacketSystem.Packets.Client;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;


public class HUDSyncMessage implements IMessage {  
	public int value;  
	public HUDSyncMessage(){}
	
	public HUDSyncMessage(int value){  
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
	
	public static class Handler implements IMessageHandler<HUDSyncMessage, IMessage> {

		@Override
		public IMessage onMessage(HUDSyncMessage message, MessageContext ctx) { 
			  
				Minecraft mc = Minecraft.getMinecraft();      
				IPlayerCap cap = mc.player.getCapability(PlayerCapProvider.LEVEL_CAP, null);   
				if(cap != null)        
					cap.setWaterLevel(message.value);    
				return null;    
			} 
		
	}
}
