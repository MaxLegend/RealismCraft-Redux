package ru.legendgamer.Realism.RealismCore;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import ru.legendgamer.Realism.API.BasicItem.BasicDamageFood;
import ru.legendgamer.Realism.API.BasicItem.BasicDamageSeedFood;
import ru.legendgamer.Realism.RealismCore.Blocks.Foods.PumpkinPie;

public class RegFoods {
	public static final BasicDamageFood KONIN = new BasicDamageFood(8, 0.3F, true, "konin", 32, 8, 70, 20 ,10, 20);
	public static final BasicDamageFood COOKED_KONIN = new BasicDamageFood(12, 0.7F, true, "cookedkonin", 32, 8, 70, 20 ,10, 2);
	public static final BasicDamageFood PLAYER_MEAT = new BasicDamageFood(6, 6F, true, "playermeat", 32, 8, 73, 12 ,15, 2); 
	public static final BasicDamageFood COOKED_PLAYER_MEAT = new BasicDamageFood(8, 12F, true, "cookedplayermeat", 32, 8, 73, 12 ,15, 2);
	public static final BasicDamageFood PUMPKIN_NUGGET = new BasicDamageFood(2, 1.2F, false, "pumpkinnugget",32,4, 15, 25 ,60, 2);
	//custom about vanilla foods
	public static final BasicDamageFood APPLE_FOOD = new BasicDamageFood(4, 0.3F, false, "apple_food", 64, 12, 5, 8, 87, 13);
	public static final BasicDamageFood BREAD_FOOD = new BasicDamageFood(5, 0.6F, false, "bread_food", 64, 24, 7, 10, 83, 2);
	public static final BasicDamageFood PORKCHOP_FOOD = new BasicDamageFood(3, 0.3F, true, "porkchop_food", 64, 10, 76, 18 ,6, 2);
	public static final BasicDamageFood COOKED_PORKCHOP_FOOD = new BasicDamageFood(8, 0.8F, true, "cooked_porkchop_food", 64, 10, 76, 18 ,6, 2);
	public static final BasicDamageFood COOKIE_FOOD = new BasicDamageFood(2, 0.1F, false, "cookie_food", 64, 4, 6, 18 ,76, 2);
	public static final BasicDamageFood MELON_FOOD = new BasicDamageFood(2, 0.3F, false, "melon_food", 64, 36, 2, 9 ,89, 2);
	public static final BasicDamageFood COOKED_BEEF_FOOD = new BasicDamageFood(10, 1F, true, "cooked_beef_food", 64, 12, 88, 6 , 6, 2);
	public static final BasicDamageFood BEEF_FOOD = new BasicDamageFood(10, 1F, true, "beef_food", 64, 12, 88, 6 , 6, 2);
	public static final BasicDamageFood CHIKEN_FOOD = new BasicDamageFood(2, 0.3F, true, "chiken_food", 64, 8, 87, 7 , 6, 2).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.8F);
	public static final BasicDamageFood COOKED_CHIKEN_FOOD = new BasicDamageFood(6, 0.6F, true, "cooked_chiken_food", 64, 12, 87, 7 , 6, 2);
	public static final BasicDamageFood SUGAR_FOOD = new BasicDamageFood(1, 0.8F, true, "sugar_food", 64, 2, 1, 0 , 99, 2);

	public static final BasicDamageSeedFood CARROT_FOOD = new BasicDamageSeedFood(3, 0.6F, Blocks.CARROTS, Blocks.FARMLAND, "carrot_food", 64, 6,12,14,74,4);
	public static final BasicDamageFood COOKED_CARROT_FOOD = new BasicDamageFood(6, 0.7F, false, "cooked_carrot_food", 64, 6,12,14,74, 2);
	public static final BasicDamageSeedFood POTATO_FOOD = new BasicDamageSeedFood(4, 0.2F, Blocks.POTATOES, Blocks.FARMLAND, "potato_food", 64, 6, 40, 20 ,40,4);
	public static final BasicDamageFood COOKED_POTATO_FOOD = new BasicDamageFood(6, 0.7F, false, "cooked_potato_food", 64, 6, 40, 20 ,40, 2);
	public static final BasicDamageFood RABBIT_FOOD = new BasicDamageFood(3, 0.3F, true, "rabbit_food", 64, 8, 74, 11 ,15, 2).setPotionEffect(new PotionEffect(MobEffects.HUNGER, 600, 0), 0.8F);
	public static final BasicDamageFood COOKED_RABBIT_FOOD = new BasicDamageFood(5, 0.6F, true, "cooked_rabbit_food", 64, 12, 74, 11 ,15, 2);
	public static final BasicDamageFood MUTTON_FOOD = new BasicDamageFood(2, 0.3F, true, "mutton_food", 64, 10, 69, 11 ,20, 2);
	public static final BasicDamageFood COOKED_MUTTON_FOOD = new BasicDamageFood(6, 0.8F, true, "cooked_mutton_food", 64, 12, 69, 11 ,20, 2);
	public static final BasicDamageFood FISH_FOOD = new BasicDamageFood(1, 0.3F, true, "fish_food", 64, 10, 69, 11 ,20, 2);
	public static final BasicDamageFood COOKED_FISH_FOOD = new BasicDamageFood(4, 0.6F, true, "cooked_fish_food", 64, 12, 69, 11 ,20, 2);
	public static final BasicDamageFood BEETROOT_FOOD = new BasicDamageFood(5, 0.1F, false, "beetroot_food", 64, 10, 10, 11 ,79, 2);
	public static final BasicDamageFood COOKED_BEETROOT_FOOD = new BasicDamageFood(8, 0.4F, false, "cooked_beetroot_food", 64, 12, 10, 11 ,79, 2);




	public static void register() {
		registerItem(COOKED_FISH_FOOD);
		registerItem(COOKED_BEETROOT_FOOD);
		registerItem(COOKED_BEEF_FOOD);
		registerItem(COOKED_MUTTON_FOOD);
		registerItem(FISH_FOOD);
		registerItem(BEETROOT_FOOD);
		registerItem(BEEF_FOOD);
		registerItem(MUTTON_FOOD);
		registerItem(POTATO_FOOD);
		registerItem(RABBIT_FOOD);
		registerItem(COOKED_POTATO_FOOD);
		registerItem(COOKED_RABBIT_FOOD);
		registerItem(COOKED_CARROT_FOOD);
		registerItem(CARROT_FOOD);
		registerItem(SUGAR_FOOD);
		registerItem(CHIKEN_FOOD);
		registerItem(COOKED_CHIKEN_FOOD);
		registerItem(COOKIE_FOOD);
		registerItem(MELON_FOOD);
		registerItem(COOKED_PORKCHOP_FOOD);
		registerItem(PORKCHOP_FOOD);
		registerItem(BREAD_FOOD);
		registerItem(APPLE_FOOD);
		registerItem(PUMPKIN_NUGGET);
		registerItem(PLAYER_MEAT);
		registerItem(COOKED_PLAYER_MEAT);
		registerItem(COOKED_KONIN);
		registerItem(KONIN);
	}
	public static void registerRender() {
		registerRenderItem(COOKED_FISH_FOOD);
		registerRenderItem(COOKED_BEETROOT_FOOD);
		registerRenderItem(COOKED_BEEF_FOOD);
		registerRenderItem(COOKED_MUTTON_FOOD);
		registerRenderItem(FISH_FOOD);
		registerRenderItem(BEETROOT_FOOD);
		registerRenderItem(BEEF_FOOD);
		registerRenderItem(MUTTON_FOOD);
		registerRenderItem(PORKCHOP_FOOD);
		registerRenderItem(POTATO_FOOD);
		registerRenderItem(RABBIT_FOOD);
		registerRenderItem(COOKED_POTATO_FOOD);
		registerRenderItem(COOKED_RABBIT_FOOD);
		registerRenderItem(CARROT_FOOD);
		registerRenderItem(COOKED_CARROT_FOOD);
		registerRenderItem(SUGAR_FOOD);
		registerRenderItem(CHIKEN_FOOD);
		registerRenderItem(COOKED_CHIKEN_FOOD);
		registerRenderItem(COOKIE_FOOD);	
		registerRenderItem(MELON_FOOD);
		registerRenderItem(PORKCHOP_FOOD);
		registerRenderItem(COOKED_PORKCHOP_FOOD);
		registerRenderItem(BREAD_FOOD);
		registerRenderItem(APPLE_FOOD);
		registerRenderItem(PUMPKIN_NUGGET);
		registerRenderItem(COOKED_PLAYER_MEAT);
		registerRenderItem(PLAYER_MEAT);
		registerRenderItem(COOKED_KONIN);
		registerRenderItem(KONIN);
	}
	private static void registerItem(Item item) {
		ForgeRegistries.ITEMS.register(item);
	}
	private static void registerRenderItem(Item item) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
