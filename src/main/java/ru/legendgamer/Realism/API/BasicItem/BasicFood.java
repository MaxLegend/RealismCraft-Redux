package ru.legendgamer.Realism.API.BasicItem;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import ru.legendgamer.Realism.RealismCore.Realism;

/**
 * Basics API class by Food
 * @author LegendGamer
 */
public class BasicFood extends ItemFood {
	public int itemUseDuration;
	private  int healAmount;
	private  float saturationModifier;
	private  boolean isWolfsFavoriteMeat;

	private boolean alwaysEdible;


	public BasicFood(int amount, float saturation, boolean isWolfFood,String name,int itemUseDuration)
	{
		super(amount,saturation,isWolfFood);
		this.itemUseDuration = itemUseDuration;
		this.healAmount = amount;
		this.isWolfsFavoriteMeat = isWolfFood;
		this.setRegistryName(name);
		this.setCreativeTab(Realism.tabFood);
		this.setUnlocalizedName(name);  
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.EAT;
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);

		if (playerIn.canEat(this.alwaysEdible))
		{
			playerIn.setActiveHand(handIn);
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
		}
		else
		{
			return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
		}
	}
	@Override
	public int getHealAmount(ItemStack stack)
	{
		return this.healAmount;
	}
	@Override
	public float getSaturationModifier(ItemStack stack)
	{
		return this.saturationModifier;
	}
	@Override
	public boolean isWolfsFavoriteMeat()
	{
		return this.isWolfsFavoriteMeat;
	}
	@Override
	public BasicFood setAlwaysEdible()
	{
		this.alwaysEdible = true;
		return this;
	}
}
