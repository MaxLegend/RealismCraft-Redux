package ru.legendgamer.Realism.RealismCore.Basic.BasicItem;

import net.minecraft.item.ItemFood;
import ru.legendgamer.Realism.RealismCore.Realism;

/**
 * Basics API class by Food
 * @author LegendGamer
 */
public class BasicFood extends ItemFood{

	public BasicFood(int amount, float saturation, boolean isWolfFood,String name) {
		super(amount, saturation, isWolfFood);	
		this.setRegistryName(name);
		this.setCreativeTab(Realism.tabMain);
		this.setUnlocalizedName(name);  
	}
}
