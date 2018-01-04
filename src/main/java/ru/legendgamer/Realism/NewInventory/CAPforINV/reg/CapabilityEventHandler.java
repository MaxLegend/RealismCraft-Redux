package ru.legendgamer.Realism.NewInventory.CAPforINV.reg;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.legendgamer.Realism.NewInventory.CustomInventory;
import ru.legendgamer.Realism.NewInventory.CAPforINV.CAPCustomInventoryProvider;
import ru.legendgamer.Realism.NewInventory.CAPforINV.ICAPCustomInventory;
import ru.legendgamer.Realism.RealismCore.Realism;

public class CapabilityEventHandler {

    public static void register(){
        MinecraftForge.EVENT_BUS.register(new CapabilityEventHandler());
    }

    public static final ResourceLocation INVENTORY_CAP = new ResourceLocation(Realism.MODID, "inventory");

    
    //ОЧЕНЬ ВАЖНО! Добавляет капу игроку при его первом создании
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
    	
    	if(event.getObject() instanceof EntityPlayer){
			event.addCapability(INVENTORY_CAP, new CAPCustomInventoryProvider());
		}
    }

    //Копирование инвентаря, если по каким-то причинам произошло клонирование игрока. Иначе вещи пропадут
    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer player = event.getEntityPlayer();
        ICAPCustomInventory newCap = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
        ICAPCustomInventory oldCap = event.getOriginal().getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
        newCap.copyInventory(oldCap);
    }

    //Если игрок умрет то ничего не выпадет. Нужно выбросит вещи вручную. Выбрасываем
    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent event) {
        if(event.getEntity() instanceof EntityPlayer) {
            //Достаем КАПу, затем инвентарь
            EntityPlayer player = (EntityPlayer)event.getEntity();
            ICAPCustomInventory cap = player.getCapability(CAPCustomInventoryProvider.INVENTORY_CAP, null);
            CustomInventory inv = cap.getInventory();
            //Выбрасываем все вещи из инвентаря
            dropAllItems(player, inv);
            inv.clear();
        }
    }

    private static void dropAllItems(EntityPlayer player, CustomInventory inventory){
        NonNullList<ItemStack> aitemstack = inventory.getStacks();
        for (int i = 0; i < aitemstack.size(); ++i) {
            if (!aitemstack.get(i).isEmpty()) {
                player.dropItem(aitemstack.get(i), true, false);
            }
        }
    }
}