package ru.legendgamer.Realism.RealismCore.CreativeTabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class DevRealism extends CreativeTabs {
	public DevRealism(String label) {
		super(label);
	//	this.setBackgroundImageName(Realism.MODID + ":maincreativebg.png");
	}
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.APPLE);
	}
}
