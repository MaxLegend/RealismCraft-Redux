package ru.legendgamer.Realism.API.BasicItem;

import net.minecraft.item.Item;

public class DevBasicItem extends Item{
	public DevBasicItem(String name,int maxStackSize){
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setMaxStackSize(maxStackSize);
	}
}
