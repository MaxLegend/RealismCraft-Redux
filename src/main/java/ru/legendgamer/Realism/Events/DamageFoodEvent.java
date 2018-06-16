package ru.legendgamer.Realism.Events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.legendgamer.Realism.RealismCore.RegFoods;

public class DamageFoodEvent {


	@SubscribeEvent
	public void checkVannilaFood(PlayerInteractEvent.RightClickItem e) {	
	
		Item item = e.getEntityPlayer().getHeldItemMainhand().getItem();
		if(item == Items.APPLE) {
			e.setCanceled(true);
		}
	}
}
