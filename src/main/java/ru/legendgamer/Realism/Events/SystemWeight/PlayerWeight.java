package ru.legendgamer.Realism.Events.SystemWeight;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.legendgamer.Realism.API.EnumCalorieFood;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;
import ru.legendgamer.Realism.Config.ConfigManager;
import ru.legendgamer.Realism.PacketSystem.NetworkHandler;
import ru.legendgamer.Realism.PacketSystem.Packets.Client.PlayerWeightMessage;

public class PlayerWeight {
/*
 * запилить свои эффекты - ожирение и дистрофию. тут накладывать
 */
	@SubscribeEvent
	public void setWeight(LivingEntityUseItemEvent.Finish e) {
		//Не кастить энтити в игрока, может быть краш если жрать будет не игрок.
	
		EntityPlayer player = (EntityPlayer)e.getEntity();
		IPlayerCap cap = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);	
		if(player.getHeldItemMainhand().getItem() == Items.APPLE) {
			float addCalorie = (float)EnumCalorieFood.CALORIE_APPLE.getCalorie();
			System.out.println("Pre " + cap.getWeight());
			cap.addWeight(addCalorie);
		//	NetworkHandler.network.sendTo(new PlayerWeightMessage(cap.getWeight()),(EntityPlayerMP)player);
			System.out.println("Post " + cap.getWeight());
		}
	}
	@SubscribeEvent
	public void delWeight(TickEvent.PlayerTickEvent e) { 
		IPlayerCap cap = e.player.getCapability(PlayerCapProvider.LEVEL_CAP, null);	
		if(!e.player.getEntityWorld().isRemote) {
			if(e.player.ticksExisted % ConfigManager.speedRemoveWeight == 0) {
				cap.reduceWeight(0.1F);
				System.out.println("TickEvent " + cap.getWeight());
			}
		}
		if(cap.getWeight() < 100) {}
		if(cap.getWeight() >= 100 && cap.getWeight() <= 140) {
			e.player.motionX *= 0.8;
			e.player.motionZ *= 0.8;
		}
		if(cap.getWeight() > 140) {
			e.player.motionX *= 0.6;
			e.player.motionZ *= 0.6;
		}
	}
}
