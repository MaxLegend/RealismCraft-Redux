package ru.legendgamer.Realism.RealismCore.Items;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegItems;

public class ItemRealPickaxe extends Item {
	
	public ItemRealPickaxe(String regName, String name) {
		setRegistryName(regName).setUnlocalizedName(name).setCreativeTab(Realism.tabMain).setMaxStackSize(1);
		setHarvestLevel("pickaxe", 4);
	}
	
	public float getStrVsBlock(ItemStack stack, IBlockState state)
    {
		NBTTagCompound nbt = stack.getTagCompound();
		if (nbt != null) {
			return 10F / nbt.getFloat("UpSharpness");
		}
        return 1F;
    }
	
	public boolean canHarvestBlock(IBlockState block)
    {
        return true;
    }
	
	@Override
    public int getHarvestLevel(ItemStack stack, String toolClass, @javax.annotation.Nullable net.minecraft.entity.player.EntityPlayer player, @javax.annotation.Nullable IBlockState blockState)
    {
		return 4;
    }
	
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)
    {
        if (!world.isRemote)
        {
        	NBTTagCompound nbt = stack.getTagCompound();
        	if (nbt == null)
        		nbt = new NBTTagCompound();
        	nbt.setFloat("UpDamage", nbt.getFloat("UpDamage") + 0.5F);
        	nbt.setFloat("UpSharpness", nbt.getFloat("UpSharpness") + 0.65F);
        	nbt.setFloat("HandleDamage", nbt.getFloat("HandleDamage") + 0.25F);
			
        	if (nbt.getFloat("UpSharpness") > 10F)
        		nbt.setFloat("UpSharpness", 10F);
        	
			if (nbt.getFloat("UpDamage") >= 10F) {
				stack.shrink(1);
				if (entityLiving instanceof EntityPlayer)
					((EntityPlayer)entityLiving).inventory.addItemStackToInventory(new ItemStack(RegItems.wood_pickaxe_handle));
			} else
				stack.setTagCompound(nbt);
        }
        return true;
    }
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
		if (stack.hasTagCompound()) {
			tooltip.add("Upper Damage: " + stack.getTagCompound().getFloat("UpDamage"));
			tooltip.add("Handle Damage: " + stack.getTagCompound().getFloat("HandleDamage"));
		}
    }
	
	public boolean showDurabilityBar(ItemStack stack)
    {
        return stack.hasTagCompound();
    }
	
	public double getDurabilityForDisplay(ItemStack stack)
    {
		NBTTagCompound nbt = stack.getTagCompound();
        return nbt.getFloat("UpDamage") / 10F;
    }
}
