package ru.legendgamer.Realism.API;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;
import ru.legendgamer.Realism.RealismCore.Realism;
/**
 * The base class of the object-drink. You simply extend from this class and set the name of the number of recoverable water. Full scale - 100 units. 10 - one "droplet"
 * Работает некорректно
 * Базовый класс предмета-напитка. Вам достаточно расширить от этого класса и установить имя количество восстанавливаемой воды. Полная шкала - 100 единиц. 10 - одна "капелька"
 * @author LegendGamer
 */
public class ItemDrink extends Item {
	public int thirstAdd;
	public Item backItem;
	public ItemDrink(String name,int maxStackSize, int thirstAdd, Item backItem) {
		super();
		this.setRegistryName(name);
		this.setCreativeTab(Realism.tabMain);
		this.setUnlocalizedName(name);
		this.setMaxStackSize(maxStackSize);
		this.thirstAdd = thirstAdd;
		this.backItem = backItem;
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}
	@Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
		 EntityPlayer player = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;
		 IPlayerCap capabilities = player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
	        if (player == null || !player.capabilities.isCreativeMode)
	        {
	            stack.shrink(1);
	        	capabilities.reduceWaterLevel(thirstAdd);
	        }

	        if (player == null || !player.capabilities.isCreativeMode)
	        {
	            if (stack.isEmpty())
	            {
	                return new ItemStack(backItem);
	            }

	            if (player != null)
	            {
	            	player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(backItem));
	            }
	        }

	        return stack;
	    }
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        playerIn.setActiveHand(handIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

	@Override
	public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, net.minecraft.nbt.NBTTagCompound nbt) {
		return new net.minecraftforge.fluids.capability.wrappers.FluidBucketWrapper(stack);
	}
}
