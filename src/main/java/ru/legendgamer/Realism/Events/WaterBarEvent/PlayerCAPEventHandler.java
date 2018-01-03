package ru.legendgamer.Realism.Events.WaterBarEvent;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.legendgamer.Realism.Capability.WaterCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.WaterCAP.PlayerCapProvider;
import ru.legendgamer.Realism.PacketSystem.NetworkHandler;
import ru.legendgamer.Realism.PacketSystem.Packets.HUDSyncMessage;
import ru.legendgamer.Realism.RealismCore.Realism;

public class PlayerCAPEventHandler {
	public static final ResourceLocation LEVEL_CAP = new ResourceLocation(Realism.MODID ,"waterLevel");

	/**
	 * ƒанные методы имеют отношение ко всей капе игрока - сохранение после смерти, при перезаходе в мир и так далее. ј также регисрации капы
	 * 
	 */
	@SubscribeEvent
	public void attachCapability(AttachCapabilitiesEvent event) {
		if(event.getObject() instanceof EntityPlayer){
			event.addCapability(LEVEL_CAP, new PlayerCapProvider());
		}
	}

	@SubscribeEvent
	public void onPlayerClone(PlayerEvent.Clone event) {
		EntityPlayer player = event.getEntityPlayer();
		IPlayerCap newCap = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
		IPlayerCap oldCap = event.getOriginal().getCapability(PlayerCapProvider.LEVEL_CAP, null);
		newCap.copyCapabilities(oldCap);
	}
	/**
	 * Ёти методы уже относ€тс€ к непосредственно обновлению waterstorage
	 */

	//ѕополнение капы действием предмета
	@SubscribeEvent
	public void counterDrinkFinish(LivingEntityUseItemEvent.Finish event) {
		EntityPlayer player = (EntityPlayer) event.getEntity();
		if(!player.world.isRemote) {
			IPlayerCap capabilities = event.getEntity().getCapability(PlayerCapProvider.LEVEL_CAP, null);
			/*	if (event.getItem().getItemUseAction() == EnumAction.DRINK) {
				if (event.getItem().getItem() != null && event.getItem().getItem() == RegItems.itemcupfill) {
					capabilities.reduceWaterLevel(30);
					event.getItem().shrink(1);
					player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RegItems.itemcup));
				}
				NetworkHandler.INSTANCE.sendToAll(new HUDSyncMessage(capabilities.getWaterLevel()));	
			 */
		}
	}
	//сохранение(передача новому игроку) капы после смерти сущности(игрока)
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {

			EntityPlayer player = (EntityPlayer) event.getEntity();
			IPlayerCap capabilities = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);

			NetworkHandler.NETWORK.sendTo(new HUDSyncMessage(capabilities.getWaterLevel()),(EntityPlayerMP)player);
		}
	}
	@SubscribeEvent
	public void updateTicker(LivingUpdateEvent event) {
		if(event.getEntity() instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient()) {
			
			EntityPlayer player = (EntityPlayer)event.getEntity();
			IPlayerCap capabilities = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);

			if(player.ticksExisted % 60 == 0) {
				
				Biome biome = player.getEntityWorld().getBiomeForCoordsBody(new BlockPos(player));
				
				if((biome == Biomes.DESERT_HILLS || biome == Biomes.DESERT)){
					if(player.isSprinting()) {
						capabilities.addWaterLevel(30);
			
					} else
						capabilities.addWaterLevel(20);
						
				} else
					if(player.isSprinting()) {
						capabilities.addWaterLevel(20);
			
					} else
						capabilities.addWaterLevel(10);

				NetworkHandler.NETWORK.sendTo(new HUDSyncMessage(capabilities.getWaterLevel()),(EntityPlayerMP)player);

				capabilities.incrementTicker();
			}
		}
	}



}
