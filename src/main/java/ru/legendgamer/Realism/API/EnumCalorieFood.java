package ru.legendgamer.Realism.API;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public enum EnumCalorieFood {
	
	CALORIE_APPLE(Items.APPLE,2),
	CALORIE_BREAD(Items.BREAD,4),
	CALORIE_BEEF(Items.BEEF,8);

	private final Item item;
	private final int calorie;
    EnumCalorieFood(Item item, int calorie)
    {
        this.item = item;
        this.calorie = calorie;
    }
    public int getCalorie() {
    	return this.calorie;
    }
    public Item getItem() {
    	return this.item;
    }
    
}
