package ru.legendgamer.Realism.RealismCore;



import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import ru.legendgamer.Realism.API.BasicBlock.BasicBlockWithDropItem;
import ru.legendgamer.Realism.API.BasicBlock.BasicFallingLog;
import ru.legendgamer.Realism.RealismCore.Blocks.Ash;
import ru.legendgamer.Realism.RealismCore.Blocks.BlockBurntGrass;
import ru.legendgamer.Realism.RealismCore.Blocks.BlockBurntTree;
import ru.legendgamer.Realism.RealismCore.Blocks.BlockDryGrass;
import ru.legendgamer.Realism.RealismCore.Blocks.CumpfireBlock;
import ru.legendgamer.Realism.RealismCore.Blocks.MossBlock;
import ru.legendgamer.Realism.RealismCore.Blocks.PaneHorizontal;
import ru.legendgamer.Realism.RealismCore.Blocks.TestGen;
import ru.legendgamer.Realism.RealismCore.Blocks.Cactus.CactusSmall;
import ru.legendgamer.Realism.RealismCore.Blocks.Cactus.OneStageCactus;
import ru.legendgamer.Realism.RealismCore.Blocks.Cactus.SideCactus;
import ru.legendgamer.Realism.RealismCore.Blocks.DeadTree.BlockDeadTree;
import ru.legendgamer.Realism.RealismCore.Blocks.DeadTree.DeadBranch;
import ru.legendgamer.Realism.RealismCore.Blocks.ExTorch.ExTorch;
import ru.legendgamer.Realism.RealismCore.Blocks.ExTorch.Torch;
import ru.legendgamer.Realism.RealismCore.Blocks.FallenLeaves.FallenBlockLeaves;
import ru.legendgamer.Realism.RealismCore.Blocks.Foods.PumpkinPie;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Acacia.GrowTreeStageOneAcacia;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.AppleTree.AppleTreeStageOne;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.AppleTree.AppleTreeStageTwo;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.AppleTree.BlockAppleTree;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.AppleTree.BranchAppleTree;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.AppleTree.LeavesAppleTree;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.AppleTree.SmallLeavesAppleTree;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Basic.BaseOakGrowLog;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Basic.DefaultOakGrowLog;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Basic.OakGrowLog;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Birch.BirchBranch;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Birch.GrowTreeStageOneBirch;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Birch.GrowTreeStageTwoBirch;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Birch.SmallLeavesBirch;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Oak.GrowTreeStageOne;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Oak.GrowTreeStageTwo;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Oak.OakBranch;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Oak.SmallLeaves;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Spruce.GrowTreeStageOneSpruce;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Spruce.GrowTreeStageTwoSpruce;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Spruce.SmallLeavesSpruce;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.Spruce.SpruceBranch;
import ru.legendgamer.Realism.RealismCore.Blocks.GrowWood.TreeSeeds.BlockSeeds.BlockAppleSeeds;
import ru.legendgamer.Realism.RealismCore.Blocks.Ocean.Corals;
import ru.legendgamer.Realism.RealismCore.Blocks.Ocean.Seaweeds;
import ru.legendgamer.Realism.RealismCore.Items.DryVine;
import ru.legendgamer.Realism.RealismCore.Items.WetVine;
 
public class RegBlocks {


	
	
	public static Block panehorizontal = new PaneHorizontal(Material.WOOD, "panehorizontal", 3F, 3F, SoundType.WOOD);
	//appletree 
	public static Block blockappleseed = new BlockAppleSeeds("blockappleseed",Blocks.FARMLAND, RegItems.itemapplesapling,RegItems.itemappleseeds,1,0,true);
	
	public static Block blockappletree = new BlockAppleTree(Material.WOOD, "blockappletree", 6F, 6F, SoundType.WOOD);
	public static Block appletreeone = new AppleTreeStageOne(Material.WOOD, "appletreeone", 3F, 3F, "axe", 1, SoundType.WOOD);
	public static Block appletreetwo = new AppleTreeStageTwo(Material.WOOD, "appletreetwo", 6F, 6F, "axe", 1, SoundType.WOOD);
	
	public static Block smallleavesappletree = new SmallLeavesAppleTree(Material.LEAVES, "smallleavesappletree", 2F, 2F, "shears", 1, SoundType.GROUND);
	public static Block leavesappletree = new LeavesAppleTree(Material.LEAVES, "leavesappletree", 2F, 2F, "shears", 1, SoundType.GROUND);
	
	//Oak
	public static Block growtreeone = new GrowTreeStageOne(Material.WOOD, "growtreeone", 3F, 3F, "axe", 1, SoundType.WOOD);
	public static Block growtreetwo = new GrowTreeStageTwo(Material.WOOD, "growtreetwo", 6F, 6F, "axe", 1, SoundType.WOOD);
	public static Block smallleaves = new SmallLeaves(Material.LEAVES, "smallleaves", 2F, 2F, "shears", 1, SoundType.GROUND);
	
	//Spruce
	public static Block growtreeonespruce = new GrowTreeStageOneSpruce(Material.WOOD, "growtreeonespruce", 3F, 3F, "axe", 1, SoundType.WOOD);
	public static Block growtreetwospruce = new GrowTreeStageTwoSpruce(Material.WOOD, "growtreetwospruce", 6F, 6F, "axe", 1, SoundType.WOOD);
	public static Block smallleavesspruce = new SmallLeavesSpruce(Material.LEAVES, "smallleavesspruce", 2F, 2F, "shears", 1, SoundType.GROUND);
	
	//birch
	public static Block growtreeonebirch = new GrowTreeStageOneBirch(Material.WOOD, "growtreeonebirch", 3F, 3F, "axe", 1, SoundType.WOOD);
	public static Block growtreetwobirch = new GrowTreeStageTwoBirch(Material.WOOD, "growtreetwobirch", 6F, 6F, "axe", 1, SoundType.WOOD);
	public static Block smallleavesbirch = new SmallLeavesBirch(Material.LEAVES, "smallleavesbirch", 2F, 2F, "shears", 1, SoundType.GROUND);
	
	//Acacia
	public static Block growtreeoneacacia = new GrowTreeStageOneAcacia(Material.WOOD, "growtreeoneacacia", 3F, 3F, "axe", 1, SoundType.WOOD);
//	public static Block growtreetwoacacia = new GrowTreeStageTwoAcacia(Material.WOOD, "growtreetwoacacia", 6F, 6F, "axe", 1, SoundType.WOOD);
//	public static Block smallleavesacacia = new SmallLeavesAcacia(Material.LEAVES, "smallleavesacacia", 2F, 2F, "shears", 1, SoundType.GROUND);
	
	//Branchs
	public static Block connectblock = new BirchBranch("connectblock");
	public static Block oakbranch = new OakBranch("oakbranch");
	public static Block branchappletree = new BranchAppleTree("branchappletree");
	public static Block deadbranch = new DeadBranch("deadbranch");
	public static Block sprucebranch = new SpruceBranch("sprucebranch");
	
	//new ore
	public static Block copperore = new BasicBlockWithDropItem(Material.ROCK, "copperore", 3F, 3F, SoundType.STONE, RegItems.oreitemcopper, 2,0);
	//kaolin
	public static Block kaolinit = new BasicBlockWithDropItem(Material.ROCK, "kaolinit", 3F, 3F, SoundType.STONE, RegItems.kaolin, 2,0);

	//Vine
	public static Block wetvine = new WetVine(Material.LEAVES, "wetvine", 0.1F, 0.1F, "shears", 1, SoundType.GROUND);
	public static Block dryvine = new DryVine(Material.LEAVES, "dryvine", 0.1F, 0.1F, "shears", 1, SoundType.GROUND);
	//sand

	

	//moss
	public static Block mossblock = new MossBlock(Material.LEAVES, "mossblock", 0.1F, 0.1F, "mossblock", 1, SoundType.GROUND);
	//salt
	public static Block saltblock = new BasicBlockWithDropItem(Material.ROCK, "saltblock", 2F, 2F, SoundType.STONE, RegItems.salt,4,0);
	//ocean
		//corals
	public static Block corals = new Corals(Material.WOOD, "corals", 3F, 3F, "axe", 1, SoundType.WOOD);
		//seaweeds
	public static Block seaweeds = new Seaweeds(Material.GRASS, "seaweeds");
	
	public static Block coralblock = new BasicBlockWithDropItem(Material.ROCK, "coralblock", 3F, 3F, SoundType.STONE, Item.getItemFromBlock(RegBlocks.corals), 2,0);
	
	public static Block cumpfire = new CumpfireBlock(Material.LEAVES, "cumpfire", 6F, 1, "pickaxe", 0, SoundType.WOOD);
	

	public static Block testgen = new TestGen(Material.LEAVES, "testgen", 0.1F, 0.1F, SoundType.GROUND);
	
	public static Block cactusone = new OneStageCactus(Material.WOOD, "cactusone", 3F, 3F, "axe", 1, SoundType.WOOD);
	public static Block cactusside = new SideCactus("cactusside"); 
	public static Block cactussmall = new CactusSmall(Material.GRASS, "cactussmall", 1F, 1F, "axe", 1, SoundType.WOOD);
	
	public static Block blockdrygrass = new BlockDryGrass(Material.GLASS, "blockdrygrass", 1F, 2F, SoundType.PLANT);
	public static Block blockburntgrass = new BlockBurntGrass(Material.GLASS, "blockburntgrass", 1F, 2F, SoundType.PLANT);
	public static Block ash = new Ash(Material.GLASS, "ash", 0.2F, 2F, SoundType.SNOW);

	public static Block blockburnttree = new BlockBurntTree(Material.WOOD, "blockburnttree", 6F, "axe",  1, 3F, SoundType.WOOD);
	public static Block blockdeadtree = new BlockDeadTree(Material.WOOD, "blockdeadtree", 6F, "axe",  1, 3F, SoundType.WOOD);
	

	public static Block torch = new Torch();
	public static Block extorch = new ExTorch();
	
//	public static Block compactworkbench = new BlockCompactWorkbench(Material.WOOD, "compactworkbench", 6F, 1, SoundType.WOOD);
	
//	public static Block oaksaplingfromseed = new OakSaplingsSEED("oaksaplingfromseed", RegItems.appleseed, RegItems.appleseed);

	public static Block fallenlayers = new FallenBlockLeaves(Material.LEAVES, "fallenlayers", 6F, 1, SoundType.WOOD);
	
	public static Block oakgrowlog = new OakGrowLog(Material.WOOD, "oakgrowlog", 6F, 1, "axe", 0, SoundType.WOOD);
	public static Block baseoakgrowlog = new BaseOakGrowLog(Material.WOOD, "baseoakgrowlog", 6F, 1, "axe", 0, SoundType.WOOD);
	public static Block defoakgrowlog = new DefaultOakGrowLog(Material.WOOD, "defoakgrowlog", 6F, 1, "axe", 0, SoundType.WOOD);
	
	public static final Block PUMPKIN_PIE = new PumpkinPie("pumpkin_pie");
	

	public static void register() {
		registerBlock(oakgrowlog);
		
		registerBlock(blockappleseed);
		registerBlock(baseoakgrowlog);
		registerBlock(defoakgrowlog);
		registerBlock(fallenlayers);
		registerBlock(PUMPKIN_PIE);
		registerBlock(sprucebranch);
		registerBlock(torch);
		registerBlock(extorch);
		registerBlock(connectblock);
		registerBlock(deadbranch);
		registerBlock(blockdeadtree);
		registerBlock(blockburnttree);
		registerBlock(ash);
		registerBlock(blockburntgrass);
		registerBlock(blockdrygrass);
		registerBlock(coralblock);
		registerBlock(cactussmall);
		registerBlock(seaweeds);
		registerBlock(corals);
		registerBlock(cactusside);
		registerBlock(cactusone);
		registerBlock(panehorizontal);
		registerBlock(appletreeone);
		registerBlock(appletreetwo);
		registerBlock(branchappletree);
		registerBlock(leavesappletree);
		registerBlock(smallleavesappletree);
		registerBlock(testgen);
		registerBlock(blockappletree);
		registerBlock(oakbranch);
		registerBlock(smallleavesbirch);
		
		registerBlock(growtreeonebirch);
		registerBlock(growtreetwobirch);
		registerBlock(copperore);
		registerBlock(growtreeone);
		registerBlock(growtreetwo);
		registerBlock(saltblock);

		registerBlock(kaolinit);
		registerBlock(cumpfire);


		registerBlock(wetvine);
		registerBlock(mossblock);

		registerBlock(dryvine);
		registerBlock(smallleaves);
		registerBlock(growtreeonespruce);
		registerBlock(growtreetwospruce);
		registerBlock(smallleavesspruce);
	}
	public static void registerRender() {
		registerRenderBlock(oakgrowlog);
		registerRenderBlock(defoakgrowlog);
		
		registerRenderBlock(blockappleseed);

		registerRenderBlock(baseoakgrowlog);
		registerRenderBlock(fallenlayers);
		registerRenderBlock(sprucebranch);
		registerRenderBlock(PUMPKIN_PIE);
		registerRenderBlock(extorch);
		registerRenderBlock(torch);
		registerRenderBlock(connectblock);
		registerRenderBlock(deadbranch);
		registerRenderBlock(blockdeadtree);
		registerRenderBlock(blockburnttree);
		registerRenderBlock(ash);
		registerRenderBlock(blockburntgrass);
		registerRenderBlock(blockdrygrass);
		registerRenderBlock(coralblock);
		registerRenderBlock(cactussmall);
		registerRenderBlock(seaweeds);
		registerRenderBlock(corals);
		registerRenderBlock(cactusside);
		registerRenderBlock(cactusone);
		registerRenderBlock(panehorizontal);
		registerRenderBlock(testgen);
		registerRenderBlock(leavesappletree);
		registerRenderBlock(appletreeone);
		registerRenderBlock(appletreetwo);
		registerRenderBlock(branchappletree);
		registerRenderBlock(smallleavesappletree);
		registerRenderBlock(blockappletree);
		registerRenderBlock(oakbranch);
		registerRenderBlock(smallleavesbirch);

		registerRenderBlock(growtreeonebirch);
		registerRenderBlock(growtreetwobirch);
		registerRenderBlock(copperore);
		registerRenderBlock(growtreeone);
		registerRenderBlock(cumpfire);
		registerRenderBlock(kaolinit);

		registerRenderBlock(saltblock);

		registerRenderBlock(growtreetwo);

		registerRenderBlock(wetvine);
		registerRenderBlock(dryvine);
		registerRenderBlock(mossblock);
		registerRenderBlock(smallleaves);
		registerRenderBlock(growtreeonespruce);
		registerRenderBlock(growtreetwospruce);
		registerRenderBlock(smallleavesspruce);
	}
	public static void registerBlock(Block block) {
		ForgeRegistries.BLOCKS.register(block);
		ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
	}
	public static void registerRenderBlock(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
	
}
