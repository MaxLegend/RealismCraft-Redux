package ru.legendgamer.Realism.API.BasicItem;


import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.RealismCore.Realism;

public class BasicDamageFood extends BasicFood {
	private PotionEffect potionId;
	public static final String CHARACTERISTIC = Realism.MODID + "characteristic";
	private float potionEffectProbability;
	public  int FAT;
	public  int PROTEIN;
	public  int CARBONE;

	public int maxshelflife;
	
	public BasicDamageFood(int amount, float saturation, boolean isWolfFood, String name, int itemUseDuration,int maxEating,int protein,int fat,int carbone,int shelflife)
	{
		super(amount, saturation, isWolfFood, name, itemUseDuration);
		this.PROTEIN = protein;
		this.FAT = fat;
		this.CARBONE = carbone;
		this.maxshelflife = shelflife;
		this.setMaxStackSize(1);
		this.setMaxDamage(maxEating);
		
	}
	
	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
	{
		if (!worldIn.isRemote && this.potionId != null && worldIn.rand.nextFloat() < this.potionEffectProbability)
		{
			player.addPotionEffect(new PotionEffect(this.potionId));
		}
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {

		return true;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected)
	{
		NBTTagCompound tag = this.getNbt(stack);
        if(!tag.hasKey(CHARACTERISTIC))
        {
            tag.setInteger(CHARACTERISTIC, maxshelflife);
        }
        else if(entity.ticksExisted % 80 == 0) {
            int value = tag.getInteger(CHARACTERISTIC);
            if (value < 0) { 
            	tag.setInteger(CHARACTERISTIC, 0);
            	stack.shrink(1);
            }
            else
                tag.setInteger(CHARACTERISTIC, --value);
           
        }
        
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		NBTTagCompound tag = this.getNbt(stack);
		if (tag.hasKey(CHARACTERISTIC)) {
			int protein = tag.getInteger(CHARACTERISTIC);
			tooltip.add(TextFormatting.DARK_RED + "Protein: " + PROTEIN + "%");
			tooltip.add(TextFormatting.YELLOW + "Fat: " + FAT + "%");
			tooltip.add(TextFormatting.DARK_GREEN + "Carbon: " + CARBONE + "%");
		}
	}
	public static NBTTagCompound getNbt(ItemStack itemStack){
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt == null) {
			nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
		}
		return nbt;
	}
	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
	{
		if (entityLiving instanceof EntityPlayer)
		{
			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			entityplayer.getFoodStats().addStats(this, stack);
			worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
			this.onFoodEaten(stack, worldIn, entityplayer);
			entityplayer.addStat(StatList.getObjectUseStats(this));

			if (entityplayer instanceof EntityPlayerMP)
			{
				CriteriaTriggers.CONSUME_ITEM.trigger((EntityPlayerMP)entityplayer, stack);
			}
		}

		stack.getItem().setDamage(stack, stack.getItemDamage() + 1);
		if(stack.getItemDamage() >= stack.getMaxDamage()) stack.shrink(1);
		System.out.println(stack.getItemDamage());
		return stack;
	}
	@Override
	public BasicDamageFood setPotionEffect(PotionEffect effect, float probability)
	{
		this.potionId = effect;
		this.potionEffectProbability = probability;
		return this;
	}
}
