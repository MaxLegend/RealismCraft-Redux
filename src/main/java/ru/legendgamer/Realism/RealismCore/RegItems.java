package ru.legendgamer.Realism.RealismCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import ru.legendgamer.Realism.API.ItemDrink;
import ru.legendgamer.Realism.API.BasicItem.BasicDamageItem;
import ru.legendgamer.Realism.API.BasicItem.BasicFood;
import ru.legendgamer.Realism.API.BasicItem.BasicItem;
import ru.legendgamer.Realism.RealismCore.Blocks.FallenLeaves.ItemFallenLeaves;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.TreeSeeds.ItemSaplings.ItemAppleSapling;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.TreeSeeds.ItemSeeds.ItemTreeSeeds;
import ru.legendgamer.Realism.RealismCore.Items.Counter;
import ru.legendgamer.Realism.RealismCore.Items.ItemRealPickaxe;
import ru.legendgamer.Realism.RealismCore.Items.ItemRealShovel;
import ru.legendgamer.Realism.RealismCore.Items.Kaolin;
import ru.legendgamer.Realism.RealismCore.Items.PointedStone;
import ru.legendgamer.Realism.RealismCore.Items.Rope;
import ru.legendgamer.Realism.RealismCore.Items.Stonehand;
import ru.legendgamer.Realism.RealismCore.Items.Thermometr;
import ru.legendgamer.Realism.RealismCore.Items.WetVineItem;

public class RegItems {

	
//	public static Item fullstoneshovel = new FullStoneShovel("fullstoneshovel",1);
//	public static Item fullwoodshovel = new FullWoodenShovel("fullwoodshovel",1);
//	public static Item fullironshovel = new FullIronShovel("fullironshovel",1);
////	public static Item fullgoldshovel = new FullGoldShovel("fullgoldshovel",1);
//	public static Item fulldiamondshovel = new FullDiamondShovel("fulldiamondshovel",1);
	
//	public static Item branchbirchitem = new ItemBranchBirch("branchbirchitem",1);
	
//	public static Item newstoneaxe = new NewStoneAxe("newstoneaxe",Realism.StoneMaterial);
	public static final Item BLADE_STONE_AXE = new BasicItem("bladestoneaxe",64);
	
	public static final Item bladestoneshovel = new BasicItem("bladestoneshovel",64);
			
	public static final Item handfuldirt = new BasicItem("handfuldirt",64);
	public static final Item dirtformshovel = new BasicItem("dirtformshovel",64);
	public static final Item dirtformaxe = new BasicItem("dirtformaxe",64);
	public static final Item dirtformchisel = new BasicItem("dirtformchisel",64);
	
	public static final Item kaolin = new BasicItem("kaolin",64);
	public static final Item kaolindust = new Kaolin("kaolindust",64);
	
	public static final Item stonebladechisel = new BasicItem("stonebladechisel",64);
	
	public static final Item annealedclaycup = new BasicItem("annealedclaycup",1);
	public static final Item annealedclaycupfull = new ItemDrink("annealedclaycupfull",1,10,RegItems.annealedclaycup);
	
	public static final Item stonechisel = new BasicDamageItem("stonechisel",1,64);
	public static final Item pistil = new BasicDamageItem("pistil",1,64);
	
	public static final Item claycup = new BasicItem("claycup",64);
	public static final Item clayplate = new BasicItem("clayplate",64);
	public static final Item annealedclayplate = new BasicItem("annealedclayplate",64);
	
	public static final Item itemcup = new BasicItem("itemcup",1);
	public static final Item itemcupfill = new ItemDrink("itemcupfill",1,30, RegItems.itemcup);
	

	
	public static final Item stonehand = new Stonehand("stonehand",64);
	public static final Item clumpclay = new BasicItem("clumpclay",64);
	public static final Item mossitem = new BasicItem("mossitem",64);
	public static final Item turf = new BasicItem("turf",64);
	public static final Item rope = new Rope("rope",64);
	public static final Item salt = new BasicItem("salt",64);

	public static final Item wetvineitem = new WetVineItem("wetvineitem",64);
	public static final Item dryvineitem = new BasicItem("dryvineitem",64);
	public static final Item spoke = new BasicItem("spoke",64);
	public static final Item spokes = new BasicDamageItem("spokes",1,64);
	public static final Item pointedstone = new PointedStone("pointedstone",ToolMaterial.IRON, 64);
	
	
	public static final Item acorn = new BasicItem("acorn",64);

	
//	public static Item stonepickaxe = new RealismPickaxe("stonepickaxe",Realism.StoneMaterial);
//	public static Item copperpickaxe = new RealismPickaxe("copperpickaxe",Realism.CopperMaterial);
//	public static Item ironpickaxe = new RealismPickaxe("ironpickaxe",Realism.IronMaterial);
//	public static Item goldpickaxe = new RealismPickaxe("goldpickaxe",Realism.GoldenMaterial);
//	public static Item diamondpickaxe = new RealismPickaxe("diamondpickaxe",Realism.DiamondMaterial);
	
	
	//Семена для саженцев

	//Саженцы которые уставливают саженец
	public static final Item itemapplesapling = new ItemAppleSapling("itemapplesapling",64, Blocks.DIRT, RegBlocks.appletreeone);
	public static final Item itemappleseeds = new ItemTreeSeeds("itemappleseeds", RegBlocks.blockappleseed);
	
	
	
//	public static Item birchsapling = new BirchSapling("birchsapling",64);
//	public static Item sprucesapling = new SpruceSapling("sprucesapling",64);
	
	public static final Item thermo = new Thermometr("thermo",1);
	
	public static final Item oreitemiron = new BasicItem("oreitemiron",64),
			   oreitemgold = new BasicItem("oreitemgold",64),
			   oreitemcopper = new BasicItem("oreitemcopper",64),
			   oreitemdiamond = new BasicItem("oreitemdiamond",64);
		// MaximPixel's Items
	

	public static Item wood_shovel = new ItemRealShovel("Wood", "wood_shovel");
	public static Item stone_shovel = new ItemRealShovel("Stone", "stone_shovel");
	
	public static Item wood_pickaxe = new ItemRealPickaxe("wood_pickaxe", "woodPickaxe");
	
	public static Item wood_pickaxe_handle = new BasicItem("wood_pickaxe_hancdle", 1);
	
	public static Item counter = new Counter("counter",1);
	public static Item calendar = new BasicItem("calendar",1);

	public static Item backpack = new BasicItem("backpack",1);
	
	public static Item itemfallen = new ItemFallenLeaves("itemfallen");

	public static void register() {
		registerItem(calendar);
		
		// MP's register
		registerItem(itemappleseeds);
		registerItem(itemfallen);
		registerItem(itemapplesapling);
		registerItem(wood_shovel);
		registerItem(stone_shovel);
		registerItem(wood_pickaxe);
		registerItem(wood_pickaxe_handle);
		
		registerItem(backpack);
		registerItem(thermo);
		
		registerItem(counter);

	//	registerItem(birchsapling);
	//	registerItem(diamondpickaxe);
	//	registerItem(goldpickaxe);
	//	registerItem(ironpickaxe);
	//	registerItem(copperpickaxe);
	//	registerItem(stonepickaxe);
	//	registerItem(branchbirchitem);
		registerItem(oreitemiron);
		registerItem(oreitemgold);
		registerItem(oreitemcopper);
		registerItem(oreitemdiamond);
		registerItem(kaolindust);
		registerItem(pistil);
		registerItem(dirtformchisel);
		registerItem(stonechisel);
		registerItem(clayplate);
		registerItem(annealedclayplate);
		registerItem(annealedclaycupfull);
		registerItem(annealedclaycup);
		registerItem(claycup);
		registerItem(stonebladechisel);
		registerItem(itemcup);
		registerItem(acorn);
		registerItem(itemcupfill);
	//	registerItem(oaksapling);
	//	registerItem(sprucesapling);
		registerItem(handfuldirt);
		registerItem(stonehand);
		registerItem(kaolin);
		registerItem(rope);
		registerItem(salt);
		registerItem(bladestoneshovel);
	
		registerItem(mossitem);
	//	registerItem(newstoneaxe);
		registerItem(BLADE_STONE_AXE);
		registerItem(turf);
		registerItem(dirtformaxe);
		registerItem(dirtformshovel);
		registerItem(spokes);
		registerItem(spoke);
	//	registerItem(fullstoneshovel);
	//	registerItem(fullwoodshovel);
	//	registerItem(fullironshovel);
	//	registerItem(fullgoldshovel);
	//	registerItem(fulldiamondshovel);
		registerItem(wetvineitem);
		registerItem(dryvineitem);
		registerItem(pointedstone);
		registerItem(clumpclay);

	}
	public static void registerRender() {
		registerRenderItem(calendar);
		registerRenderItem(itemapplesapling);
		registerRenderItem(itemappleseeds);
		registerRenderItem(itemfallen);
		registerRenderItem(thermo);
		registerRenderItem(backpack);
		registerRenderItem(counter);

	//	registerRenderItem(birchsapling);
	//	registerRenderItem(branchbirchitem);
		
		
		registerRenderItem(oreitemiron);
		registerRenderItem(oreitemgold);
		registerRenderItem(oreitemcopper);
		registerRenderItem(oreitemdiamond);
		
	//	registerRenderItem(diamondpickaxe);
	//	registerRenderItem(goldpickaxe);
	//	registerRenderItem(ironpickaxe);
	//	registerRenderItem(copperpickaxe);
	//	registerRenderItem(stonepickaxe);
		
		registerRenderItem(kaolindust);
		registerRenderItem(pistil);
		registerRenderItem(dirtformchisel);
		registerRenderItem(stonechisel);
		registerRenderItem(annealedclayplate);
		registerRenderItem(clayplate);
		
		registerRenderItem(annealedclaycupfull);
		registerRenderItem(annealedclaycup);
		registerRenderItem(claycup);
		registerRenderItem(itemcup);
		registerRenderItem(kaolin);
		registerRenderItem(acorn);
		
		registerRenderItem(itemcupfill);
	//	registerRenderItem(oaksapling);
	//	registerRenderItem(sprucesapling);
		registerRenderItem(dirtformaxe);
		registerRenderItem(dirtformshovel);
		registerRenderItem(handfuldirt);
		registerRenderItem(turf);
		
		registerRenderItem(stonebladechisel);
		registerRenderItem(salt);
		registerRenderItem(bladestoneshovel);
	
		
		registerRenderItem(rope);
		registerRenderItem(BLADE_STONE_AXE);
	//	registerRenderItem(newstoneaxe);
		registerRenderItem(mossitem);
		registerRenderItem(spokes);
		registerRenderItem(spoke);
		
	//	registerRenderItem(fullstoneshovel);
	//	registerRenderItem(fullwoodshovel);
	//	registerRenderItem(fullironshovel);
	//	registerRenderItem(fullgoldshovel);
	//	registerRenderItem(fulldiamondshovel);
		
		registerRenderItem(stonehand);
		registerRenderItem(clumpclay);
		registerRenderItem(wetvineitem);
		registerRenderItem(dryvineitem);
		registerRenderItem(pointedstone);
	// MP's register
		
		String modid = Realism.MODID;
		
		registerRenderItem(wood_shovel, 0, new ResourceLocation(modid, "wooden_shovel_empty"));
		registerRenderItem(wood_shovel, 1, new ResourceLocation(modid, "wooden_shovel_dirt"));
		registerRenderItem(wood_shovel, 2, new ResourceLocation(modid, "wooden_shovel_grass"));
		registerRenderItem(wood_shovel, 3, new ResourceLocation(modid, "wooden_shovel_gravel"));
		registerRenderItem(stone_shovel, 0, new ResourceLocation(modid, "stone_shovel_empty"));
		registerRenderItem(stone_shovel, 1, new ResourceLocation(modid, "stone_shovel_dirt"));
		registerRenderItem(stone_shovel, 2, new ResourceLocation(modid, "stone_shovel_grass"));
		registerRenderItem(stone_shovel, 3, new ResourceLocation(modid, "stone_shovel_gravel"));
		
		registerRenderItem(wood_pickaxe, new ResourceLocation("wooden_pickaxe"));
		registerRenderItem(wood_pickaxe_handle, new ResourceLocation("stick"));
	}
	
	public static void preRegisterRender() {
		String modid = Realism.MODID;
		
		registerCustomModel(wood_shovel, 0, new ResourceLocation(modid, "wooden_shovel_empty"));
		registerCustomModel(wood_shovel, 1, new ResourceLocation(modid, "wooden_shovel_dirt"));
		registerCustomModel(wood_shovel, 2, new ResourceLocation(modid, "wooden_shovel_grass"));
		registerCustomModel(wood_shovel, 3, new ResourceLocation(modid, "wooden_shovel_gravel"));
		registerCustomModel(stone_shovel, 0, new ResourceLocation(modid, "stone_shovel_empty"));
		registerCustomModel(stone_shovel, 1, new ResourceLocation(modid, "stone_shovel_dirt"));
		registerCustomModel(stone_shovel, 2, new ResourceLocation(modid, "stone_shovel_grass"));
		registerCustomModel(stone_shovel, 3, new ResourceLocation(modid, "stone_shovel_gravel"));
	}
	
	private static void registerItem(Item item) {
		ForgeRegistries.ITEMS.register(item);
	}
	
	private static void registerCustomModel(Item item, int meta, ResourceLocation location) {
		ModelLoader.setCustomModelResourceLocation(item, meta, getModel(location));
	}
	
	private static void registerRenderItem(Item item) {
		registerRenderItem(item, item.getRegistryName());
	}
	
	private static void registerRenderItem(Item item, ResourceLocation location) {
		registerRenderItem(item, 0, location);
	}
	
	private static void registerRenderItem(Item item, int meta, ResourceLocation location) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, getModel(location));
	}
	
	public static ModelResourceLocation getModel(ResourceLocation location) {
		return new ModelResourceLocation(location, "inventory");
	}
}