package ru.legendgamer.Realism.Events.SystemWeight;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import ru.legendgamer.Realism.Config.ConfigManager;

public class EventWeight {

	@SubscribeEvent
	public void weightm(TickEvent.PlayerTickEvent e) {
		
		EntityPlayer player = e.player;
		int weight = 0;
		
		if(player.isCreative()) {
			return;
		}
		if(player.ticksExisted % ConfigManager.updateInv == 0) {
			for(ItemStack stack : player.inventory.mainInventory) { 
				if(!stack.isEmpty()) { 
					weight += stack.getCount(); 
				}
			}
		}
	 
		
		if(weight <= 339) {
			player.motionX *= ConfigManager.pm339;
			player.motionZ *= ConfigManager.pm339;
			if(player.isSprinting()) {
				player.motionX *= ConfigManager.spm339;
				player.motionZ *= ConfigManager.spm339;
			}
		}
		if(weight > 339 && weight <= 678) {
			player.motionX *= ConfigManager.pm678;
			player.motionZ *= ConfigManager.pm678;
			if(player.isSprinting()) {
				player.motionX *= ConfigManager.spm678;
				player.motionZ *= ConfigManager.spm678;
			}
		}
		if(weight > 678 && weight <= 1017) {
			player.motionX *= ConfigManager.pm1017;
			player.motionZ *= ConfigManager.pm1017;
			if(player.isSprinting()) {
				player.motionX *= ConfigManager.spm1017;
				player.motionZ *= ConfigManager.spm1017;
			}
		}
		if(weight > 1017 && weight <= 1356) {
			player.motionX *= ConfigManager.pm1356;
			player.motionZ *= ConfigManager.pm1356;
			player.jumpMovementFactor *= 0.5;
			if(player.isSprinting()) {
				player.motionX *= ConfigManager.spm1356;
				player.motionZ *= ConfigManager.spm1356;
				player.jumpMovementFactor *= 0.5;
			}
		}
		if(weight > 1356 && weight <= 1695) {
			player.motionX *= ConfigManager.pm1695;
			player.motionZ *= ConfigManager.pm1695;
			player.jumpMovementFactor *= 0.5;
			if(player.isSprinting()) {
				player.motionX *= ConfigManager.spm1695;
				player.motionZ *= ConfigManager.spm1695;
				player.jumpMovementFactor *= 0.5;
			}
		}
		if(weight > 1695 && weight <= 2034) {
			player.motionX *= ConfigManager.pm2034;
			player.motionZ *= ConfigManager.pm2034;
			player.jumpMovementFactor *= 0.1;
			if(player.isSprinting()) {
				player.motionX *= ConfigManager.spm2034;
				player.motionZ *= ConfigManager.spm2034;
				player.jumpMovementFactor *= 0.1;
			}
		}
	}
}