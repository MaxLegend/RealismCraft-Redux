package ru.legendgamer.Realism.API.BasicItem;

import net.minecraft.item.Item;
import ru.legendgamer.Realism.RealismCore.Realism;
/**
 * Basics API class by Item
 * @author LegendGamer
 */
public class BasicItem extends Item{
	public BasicItem(String name,int maxStackSize){
		this.setRegistryName(name);
		this.setCreativeTab(Realism.tabMain);
		this.setUnlocalizedName(name);
		this.setMaxStackSize(maxStackSize);
		

	}

}
