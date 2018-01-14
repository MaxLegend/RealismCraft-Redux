package ru.legendgamer.Realism.PacketSystem.Packets.Server;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;
import ru.legendgamer.Realism.PacketSystem.Packets.Client.PlayerWeightMessage;

public class PlayerWeightMessageServer implements IMessage {  
	public float value;  
	public PlayerWeightMessageServer(){}
	
	public PlayerWeightMessageServer(float value){  
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
	
	public static class Handler implements IMessageHandler<PlayerWeightMessageServer, IMessage> {

		@Override
		public IMessage onMessage(PlayerWeightMessageServer message, MessageContext ctx) { 		
				IPlayerCap cap = ctx.getServerHandler().player.getCapability(PlayerCapProvider.LEVEL_CAP, null);   
				if(cap != null)        
					cap.setWeight(message.value);    
				return null;    
			} 
		
	}
}

