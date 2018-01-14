package ru.legendgamer.Realism.PacketSystem.Packets.Server;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;

public class TemperatureBodyServerMessage implements IMessage {  
	public float value;  
	public TemperatureBodyServerMessage(){}
	
	public TemperatureBodyServerMessage(float value){  
		this.value = value;   
	} 
	@Override
	public void fromBytes(ByteBuf buf) {  
		value = buf.readFloat();   
		}  
	@Override 
	public void toBytes(ByteBuf buf) {   
		buf.writeFloat(value); 
	}   
	
	public static class Handler implements IMessageHandler<TemperatureBodyServerMessage, IMessage> {

		@Override
		public IMessage onMessage(TemperatureBodyServerMessage message, MessageContext ctx) { 
			  
				Minecraft mc = Minecraft.getMinecraft();      
				IPlayerCap cap = mc.player.getCapability(PlayerCapProvider.LEVEL_CAP, null);   
				if(cap != null)        
					cap.setTempBody(message.value);    
				return null;    
			} 
		
	}
}