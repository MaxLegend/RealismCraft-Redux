package ru.legendgamer.Realism.Events.GameEvents;

import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.GuiScreenEvent.PotionShiftEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class IsSleepingNightEvent {

	static boolean night;
	@SubscribeEvent
	public void dayOrNight(TickEvent.WorldTickEvent e) {
		if(e.world.getWorldTime() % 1000 == 0) {
			if(e.world.getWorldTime() >= 0 && e.world.getWorldTime() <= 17000) {
				night = false;
			} else if(e.world.getWorldTime() > 17000 && e.world.getWorldTime() < 20600)
			{
				night = true;
			}
		}
	}
	@SubscribeEvent
	public void sleep(TickEvent.PlayerTickEvent e) {
		if(e.player.ticksExisted % 240 == 0) {
			if(night) {
				if(!e.player.isPlayerSleeping()) {
					e.player.addPotionEffect(new PotionEffect(MobEffects.UNLUCK, 900, 1));
					e.player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 900, 1));
				}
			}
		}
	}

}
