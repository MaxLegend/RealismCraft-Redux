package ru.legendgamer.Realism.Events.WaterBarEvent;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;
import ru.legendgamer.Realism.PacketSystem.NetworkHandler;
import ru.legendgamer.Realism.PacketSystem.Packets.Client.HUDSyncMessage;
import ru.legendgamer.Realism.PacketSystem.Packets.Server.HUDSyncMessageServer;
import ru.legendgamer.Realism.RealismCore.Realism;

public class PlayerCAPEventHandler {
	public static final ResourceLocation LEVEL_CAP = new ResourceLocation(Realism.MODID ,"waterLevel");

	/**
	 * Данные методы имеют отношение ко всей капе игрока - сохранение после смерти, при перезаходе в мир и так далее. А также регисрации капы
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
	 * Эти методы уже относятся к непосредственно обновлению waterstorage
	 */

	//Пополнение капы действием предмета
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

			NetworkHandler.network.sendTo(new HUDSyncMessage(capabilities.getWaterLevel()),(EntityPlayerMP)player);
		}
	}
	//основной метод где с течением времени с капой что то происходит.	
	@SubscribeEvent
	public void updateTicker(LivingUpdateEvent event) {
		if(event.getEntity() instanceof EntityPlayer && !FMLCommonHandler.instance().getEffectiveSide().isClient()) {

			EntityPlayer player = (EntityPlayer)event.getEntity();
			IPlayerCap capabilities = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
			if( !event.getEntity().getEntityWorld().isRemote) {
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

					NetworkHandler.network.sendTo(new HUDSyncMessage(capabilities.getWaterLevel()),(EntityPlayerMP)player);


				}
			}
		}
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void hadDrinken(PlayerInteractEvent.RightClickBlock event){

		final World world = (World)Minecraft.getMinecraft().world;
		EntityPlayer player = (EntityPlayer) event.getEntity();
		ItemStack is = player.getHeldItem(EnumHand.MAIN_HAND);
		RayTraceResult raytraceresult = this.rayTrace(world, player, true);	

		if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
		{
			BlockPos blockpos = raytraceresult.getBlockPos();
			IPlayerCap capabilities = event.getEntity().getCapability(PlayerCapProvider.LEVEL_CAP, null);

			if (world.getBlockState(blockpos).getMaterial() == Material.WATER)
			{
				if(is.isEmpty()){
					capabilities.reduceWaterLevel(5);

					player.world.playSound(player, blockpos, SoundEvents.ENTITY_GENERIC_DRINK, SoundCategory.NEUTRAL, 0.8F, 1.0F);
					NetworkHandler.network.sendToServer(new HUDSyncMessageServer(capabilities.getWaterLevel()));
					
				}
			}
		}
	}
	protected RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
	{
		float f = playerIn.rotationPitch;
		float f1 = playerIn.rotationYaw;
		double d0 = playerIn.posX;
		double d1 = playerIn.posY + (double)playerIn.getEyeHeight();
		double d2 = playerIn.posZ;
		Vec3d vec3d = new Vec3d(d0, d1, d2);
		float f2 = MathHelper.cos(-f1 * 0.017453292F - (float)Math.PI);
		float f3 = MathHelper.sin(-f1 * 0.017453292F - (float)Math.PI);
		float f4 = -MathHelper.cos(-f * 0.017453292F);
		float f5 = MathHelper.sin(-f * 0.017453292F);
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d3 = 5.0D;
		if (playerIn instanceof net.minecraft.entity.player.EntityPlayerMP)
		{
			d3 = ((net.minecraft.entity.player.EntityPlayerMP)playerIn).interactionManager.getBlockReachDistance();
		}
		Vec3d vec3d1 = vec3d.addVector((double)f6 * d3, (double)f5 * d3, (double)f7 * d3);
		return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
	}
}

