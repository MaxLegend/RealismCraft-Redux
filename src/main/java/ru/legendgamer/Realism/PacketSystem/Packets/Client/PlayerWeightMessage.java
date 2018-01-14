package ru.legendgamer.Realism.PacketSystem.Packets.Client;

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

public class PlayerWeightMessage implements IMessage {  
	public float value;  
	public PlayerWeightMessage(){}

	public PlayerWeightMessage(float value){  
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

	public static class Handler implements IMessageHandler<PlayerWeightMessage, IMessage> {

		@Override
		public IMessage onMessage(PlayerWeightMessage message, MessageContext ctx) { 

			EntityPlayerSP player = Minecraft.getMinecraft().player;   
			if(player != null) {
				IPlayerCap cap = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);	
				if(cap != null)    {
					cap.setWeight(message.value);  
				}
			}
				return null;    
			} 
		}
	}


		