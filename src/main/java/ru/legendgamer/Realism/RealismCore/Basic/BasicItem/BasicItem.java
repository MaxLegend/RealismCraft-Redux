package ru.legendgamer.Realism.RealismCore.Basic.BasicItem;

import net.minecraft.item.Item;
import ru.legendgamer.Realism.RealismCore.Realism;

public class BasicItem extends Item{
	public BasicItem(String name,int maxStackSize){
		this.setRegistryName(name);
		this.setCreativeTab(Realism.tabMain);
		this.setUnlocalizedName(name);
		this.setMaxStackSize(maxStackSize);
		

	}

}
