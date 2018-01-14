package gloomyfolken.hooklib.example;

import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.ReturnCondition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import ru.legendgamer.Realism.RealismCore.RegBlocks;
public class AnnotationHooks {

	public static final AxisAlignedBB NULL_AABB = null;
	public static boolean fallInstantly;
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color", EnumDyeColor.class);
	public static final PropertyEnum<BlockPlanks.EnumType> VARIANT = PropertyEnum.<BlockPlanks.EnumType>create("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>()
	{
		public boolean apply(@Nullable BlockPlanks.EnumType p_apply_1_)
		{
			return p_apply_1_.getMetadata() < 4;
		}
	});
   
  
   

    

	public static int tickRate(World worldIn)
	{
		return 2;
	}



	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void neighborChanged(BlockDirt bl,IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		worldIn.scheduleUpdate(pos, Blocks.DIRT, tickRate(worldIn));
	}
	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void onBlockAdded(BlockDirt bl,World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, Blocks.DIRT, tickRate(worldIn));
	}

	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void updateTick(BlockDirt bl,World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote)
		{
			checkFallableDown(world, pos);
			checkFallableWest(world, pos);
			checkFallableEast(world, pos);
			checkFallableNorth(world, pos);
			checkFallableSouth(world, pos);
		}
	}
	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void neighborChanged(BlockGrass bl,IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		worldIn.scheduleUpdate(pos, blockIn, tickRate(worldIn));
	}
	@Hook( createMethod = true,returnCondition = ReturnCondition.ALWAYS)
	public static void onBlockAdded(BlockGrass bl,World worldIn, BlockPos pos, IBlockState state)
	{
		Block block = state.getBlock();
		worldIn.scheduleUpdate(pos, Blocks.DIRT, tickRate(worldIn));
	}
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void updateTick(BlockGrass bl,World world, BlockPos pos, IBlockState state, Random rand) {

		//South
		if(world.isAirBlock(pos.down())) {
			world.setBlockState(pos, Blocks.DIRT.getDefaultState());
		}
	}



	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void updateTick(BlockFalling bl,World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote)
		{
			checkFallableDown(world, pos);
			checkFallableWest(world, pos);
			checkFallableEast(world, pos);
			checkFallableNorth(world, pos);
			checkFallableSouth(world, pos);
		}
	}
	private static void checkFallableDown(World world, BlockPos pos)
	{	
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())) {
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
			}
		}
	}
	private static void checkFallableSouth(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.south())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.south()));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX(),pos.getY() - 1,pos.getZ() + 1))) {

					world.setBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ() + 1), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	private static void checkFallableNorth(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.north())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.north()));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX(),pos.getY() - 1,pos.getZ() - 1))) {

					world.setBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ() - 1), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	private static void checkFallableWest(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.west())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.west()));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX() + 1,pos.getY() - 1,pos.getZ()))) {

					world.setBlockState(new BlockPos(pos.getX() + 1,pos.getY(),pos.getZ()), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	private static void checkFallableEast(World world, BlockPos pos)
	{
		IBlockState state = world.getBlockState(pos);

		if (world.isAreaLoaded(pos.add(-32, -32, -32), pos.add(32, 32, 32)))
		{
			if(!world.isRemote) {
				if(world.isAirBlock(pos.down())){
					return;
				}
				if(world.isAirBlock(pos.east())){
					EntityFallingBlock efb = new EntityFallingBlock(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, world.getBlockState(pos.east()));
					onStartFalling(efb);
					world.spawnEntity(efb);
				}
				if( world.isAirBlock(new BlockPos(pos.getX() - 1,pos.getY() - 1,pos.getZ()))) {

					world.setBlockState(new BlockPos(pos.getX() - 1,pos.getY(),pos.getZ()), state);
					world.setBlockToAir(pos);
				}
			}
		}
	}
	public static void onStartFalling(EntityFallingBlock fallingEntity)
	{
	}



	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void onEntityWalk(BlockPane pb,World worldIn, BlockPos pos, Entity entityIn) {
		worldIn.destroyBlock(pos, false);
	}
	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static AxisAlignedBB getCollisionBoundingBox(BlockLeaves bl,IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static boolean generate(WorldGenCactus wgc,World worldIn, Random rand, BlockPos position)
	{
		return true;
	}
	/*	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void dropApple(BlockOldLeaf hhfg, World world, BlockPos pos, IBlockState state, int chance)
	{
		if (state.getValue(VARIANT) == BlockPlanks.EnumType.OAK)
		{
			if ( world.rand.nextInt(4) == 2){
				Block.spawnAsEntity(world, pos, new ItemStack(Items.STICK));
			}
			if ( world.rand.nextInt(3) == 2){
				Block.spawnAsEntity(world, pos, new ItemStack(RegItems.acorn));
			}
			if ( world.rand.nextInt(5) == 2){
				Block.spawnAsEntity(world, pos, new ItemStack(RegItems.oaksapling));
			}
		}else
			if( state.getValue(VARIANT) == BlockPlanks.EnumType.SPRUCE){
				if ( world.rand.nextInt(4) == 2){
					Block.spawnAsEntity(world, pos, new ItemStack(Items.STICK));
				}
				if ( world.rand.nextInt(6) == 2){
					Block.spawnAsEntity(world, pos, new ItemStack(RegItems.sprucesapling));
				}
			}
	}

	if(!world.isAirBlock(pos.down())) {

			}
			if(world.isAirBlock(pos.down())) {		
					Realism.proxy.particleClient(world, pos, rand);

			}
	 */
	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void onBlockAdded(BlockLeaves bl,World world, BlockPos pos, IBlockState state) {
		world.scheduleBlockUpdate(pos, Blocks.LEAVES, 1, 0);
		world.scheduleBlockUpdate(pos, Blocks.LEAVES2, 1, 0);
	}




	@Hook(createMethod = true, returnCondition = ReturnCondition.ALWAYS)
	public static void onEntityCollidedWithBlock(BlockLeaves bl,World world, BlockPos pos, IBlockState state, Entity entity) {
		entity.motionX *= 0.5D;
		entity.motionZ *= 0.5D;
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void neighborChanged(BlockCactus bc, IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
	}


	private final java.util.Map<net.minecraftforge.registries.IRegistryDelegate<Block>, IBlockColor> blockColorMap = com.google.common.collect.Maps.newHashMap();


	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static BlockColors init(BlockColors bc) {
		final BlockColors blockcolors = new BlockColors();
		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				BlockDoublePlant.EnumPlantType blockdoubleplant$enumplanttype = (BlockDoublePlant.EnumPlantType)state.getValue(BlockDoublePlant.VARIANT);
				return worldIn != null && pos != null && (blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.GRASS || blockdoubleplant$enumplanttype == BlockDoublePlant.EnumPlantType.FERN) ? BiomeColorHelper.getGrassColorAtPos(worldIn, state.getValue(BlockDoublePlant.HALF) == BlockDoublePlant.EnumBlockHalf.UPPER ? pos.down() : pos) : -1;
			}
		}, Blocks.DOUBLE_PLANT);
		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				if (worldIn != null && pos != null)
				{
					TileEntity tileentity = worldIn.getTileEntity(pos);

					if (tileentity instanceof TileEntityFlowerPot)
					{
						Item item = ((TileEntityFlowerPot)tileentity).getFlowerPotItem();
						IBlockState iblockstate = Block.getBlockFromItem(item).getDefaultState();
						return blockcolors.colorMultiplier(iblockstate, worldIn, pos, tintIndex);
					}
					else
					{
						return -1;
					}
				}
				else
				{
					return -1;
				}
			}
		}, Blocks.FLOWER_POT);

		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{

				return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : ColorizerGrass.getGrassColor(0.5D, 1.0D);
			}
		}, Blocks.GRASS);

		//блоки хук окраска пиздата€
		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{

				return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
			}
		}, RegBlocks.leavesappletree,RegBlocks.fallenlayers,RegBlocks.smallleaves,RegBlocks.smallleavesappletree,RegBlocks.smallleavesbirch,RegBlocks.smallleavesspruce,RegBlocks.mossblock);


		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				BlockPlanks.EnumType blockplanks$enumtype = (BlockPlanks.EnumType)state.getValue(BlockOldLeaf.VARIANT);

				if (blockplanks$enumtype == BlockPlanks.EnumType.SPRUCE)
				{
					return ColorizerFoliage.getFoliageColorPine();
				}
				else
				{
					return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
				}
			}
		}, Blocks.LEAVES);
		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
			}
		}, Blocks.LEAVES2);
		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				return worldIn != null && pos != null ? BiomeColorHelper.getWaterColorAtPos(worldIn, pos) : -1;
			}
		}, Blocks.WATER, Blocks.FLOWING_WATER);
		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				return BlockRedstoneWire.colorMultiplier(((Integer)state.getValue(BlockRedstoneWire.POWER)).intValue());
			}
		}, Blocks.REDSTONE_WIRE);
		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				return worldIn != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(worldIn, pos) : -1;
			}
		}, Blocks.REEDS);
		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				int i = ((Integer)state.getValue(BlockStem.AGE)).intValue();
				int j = i * 32;
				int k = 255 - i * 8;
				int l = i * 4;
				return j << 16 | k << 8 | l;
			}
		}, Blocks.MELON_STEM, Blocks.PUMPKIN_STEM);

		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				if (worldIn != null && pos != null)
				{
					return BiomeColorHelper.getGrassColorAtPos(worldIn, pos);
				}
				else
				{
					return state.getValue(BlockTallGrass.TYPE) == BlockTallGrass.EnumType.DEAD_BUSH ? 16777215 : ColorizerGrass.getGrassColor(0.5D, 1.0D);
				}
			}
		}, Blocks.TALLGRASS);

		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				return worldIn != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(worldIn, pos) : ColorizerFoliage.getFoliageColorBasic();
			}
		}, Blocks.VINE);

		blockcolors.registerBlockColorHandler(new IBlockColor()
		{
			public int colorMultiplier(IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex)
			{
				return worldIn != null && pos != null ? 2129968 : 7455580;
			}
		}, Blocks.WATERLILY);

		net.minecraftforge.client.ForgeHooksClient.onBlockColorsInit(blockcolors);
		return blockcolors;
	}

	public int getColor(IBlockState state, World p_189991_2_, BlockPos p_189991_3_)
	{
		IBlockColor iblockcolor = this.blockColorMap.get(state.getBlock().delegate);

		if (iblockcolor != null)
		{
			return iblockcolor.colorMultiplier(state, (IBlockAccess)null, (BlockPos)null, 0);
		}
		else
		{
			MapColor mapcolor = state.getMapColor(p_189991_2_, p_189991_3_);
			return mapcolor != null ? mapcolor.colorValue : -1;
		}
	}

	public int colorMultiplier(IBlockState state, @Nullable IBlockAccess blockAccess, @Nullable BlockPos pos, int renderPass)
	{
		IBlockColor iblockcolor = this.blockColorMap.get(state.getBlock().delegate);
		return iblockcolor == null ? -1 : iblockcolor.colorMultiplier(state, blockAccess, pos, renderPass);
	}

	public void registerBlockColorHandler(IBlockColor blockColor, Block... blocksIn)
	{
		for (Block block : blocksIn)
		{
			if (block == null) throw new IllegalArgumentException("Block registered to block color handler cannot be null!");
			if (block.getRegistryName() == null) throw new IllegalArgumentException("Block must be registered before assigning color handler.");
			this.blockColorMap.put(block.delegate, blockColor);
		}
	}

	static Minecraft mc = Minecraft.getMinecraft();
	public static float thirdPersonDistancePrev = 4.0F;
	private static boolean cloudFog;
	//Big classe render
	//@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void updateLightmap(EntityRenderer render, float partialTicks)
	{
		//	System.out.println("ѕездато работет");
		boolean lightmapUpdateNeeded = ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, render, "lightmapUpdateNeeded");
		float torchFlickerX = ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, render, "torchFlickerX");
		float bossColorModifier = ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, render, "bossColorModifier");
		float bossColorModifierPrev = ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, render, "bossColorModifierPrev");
		int[] lightmapColors = ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, render, "lightmapColors");
		DynamicTexture lightmapTexture = ObfuscationReflectionHelper.getPrivateValue(EntityRenderer.class, render, "lightmapTexture");
		if (lightmapUpdateNeeded)
		{
			Minecraft.getMinecraft().mcProfiler.startSection("lightTex");
			World world = Minecraft.getMinecraft().world;

			if (world != null)
			{
				float getSunBrightness = world.getSunBrightness(1.0F);
				float f1 = getSunBrightness * 1F;

				for (int i = 0; i < 256; ++i)
				{
					float getLightBrightnessTable = world.provider.getLightBrightnessTable()[i / 16] * f1;
					float getLightBrightnessTableT = world.provider.getLightBrightnessTable()[i % 16] * (torchFlickerX * 0.1F + 1.5F);

					if (world.getLastLightningBolt() > 0)
					{
						getLightBrightnessTable = world.provider.getLightBrightnessTable()[i / 16];
					}

					float f4 = getLightBrightnessTable * (getSunBrightness * 0.65F + 0.35F);
					float f5 = getLightBrightnessTable * (getSunBrightness * 0.65F + 0.35F);
					float f6 = getLightBrightnessTableT * ((getLightBrightnessTableT * 0.6F + 0.4F) * 0.6F + 0.4F);
					float f7 = getLightBrightnessTableT * (getLightBrightnessTableT * getLightBrightnessTableT * 0.6F + 0.4F);
					float f8 = f4 + getLightBrightnessTableT;
					float f9 = f5 + f6;
					float f10 = getLightBrightnessTable + f7;
					f8 = f8 * 0.96F + 0.03F;
					f9 = f9 * 0.96F + 0.03F;
					f10 = f10 * 0.96F + 0.03F;


					if (bossColorModifier > 0.0F)
					{
						float f11 = bossColorModifierPrev + (bossColorModifier - bossColorModifierPrev) * partialTicks;
						f8 = f8 * (1.0F - f11) + f8 * 0.7F * f11;
						f9 = f9 * (1.0F - f11) + f9 * 0.6F * f11;
						f10 = f10 * (1.0F - f11) + f10 * 0.6F * f11;
					}
					if (world.provider.getDimensionType().getId() == 1)
					{
						f8 = 0.22F + getLightBrightnessTableT * 0.75F;
						f9 = 0.28F + f6 * 0.75F;
						f10 = 0.25F + f7 * 0.75F;
					}

					if (f8 > 1.0F)
					{
						f8 = 1.0F;
					}

					if (f9 > 1.0F)
					{
						f9 = 1.0F;
					}

					if (f10 > 1.0F)
					{
						f10 = 1.0F;
					}

					float f16 = mc.gameSettings.gammaSetting;
					float f17 = 1.0F - f8;
					float f13 = 1.0F - f9;
					float f14 = 1.0F - f10;
					f17 = 1.0F - f17 * f17 * f17 * f17;
					f13 = 1.0F - f13 * f13 * f13 * f13;
					f14 = 1.0F - f14 * f14 * f14 * f14;
					f8 = f8 * (1.0F - f16) + f17 * f16;
					f9 = f9 * (1.0F - f16) + f13 * f16;
					f10 = f10 * (1.0F - f16) + f14 * f16;
					f8 = f8 * 0.96F + 0.03F;
					f9 = f9 * 0.96F + 0.03F;
					f10 = f10 * 0.96F + 0.03F;

					if (f8 > 1.0F)
					{
						f8 = 1.0F;
					}

					if (f9 > 1.0F)
					{
						f9 = 1.0F;
					}

					if (f10 > 1.0F)
					{
						f10 = 1.0F;
					}

					if (f8 < 0.0F)
					{
						f8 = 0.0F;
					}

					if (f9 < 0.0F)
					{
						f9 = 0.0F;
					}

					if (f10 < 0.0F)
					{
						f10 = 0.0F;
					}


					//цвета?!
					int j = 255;
					int k = (int)(f8 * 255.0F);
					int l = (int)(f9 * 255.0F);
					int i1 = (int)(f10 * 255.0F);

					lightmapColors[i] = -16777216 | k << 16 | l << 8 | i1;


					if (mc.player.isPotionActive(MobEffects.NIGHT_VISION))
					{


						float getNightVis = getNightVisionBrightness(mc.player, partialTicks);
						float f12 = 4.0F / f8;

						if (f12 > 1.0F / f9)
						{
							f12 = 1.0F / f9;
						}

						if (f12 > 1.0F / f10)
						{
							f12 = 1.0F / f10;
						}

						f8 = f8 * (1.0F - getNightVis) + f8 * f12 * getNightVis;
						f9 = f9 * (1.0F - getNightVis) + f9 * f12 * getNightVis;
						f10 = f10 * (1.0F - getNightVis) + f10 * f12 * getNightVis;
						lightmapColors[i] = -40293745 | k << 16 | l << 8 | i1;

						//¬еселые комбинации
						/*
						 * 16777216 - норма
						 * 25500139 - €рко-темнозеленый
						 * 15678234 - сочно-зеленый
						 * 40293745 - кроваво -красный цвет €рости
						 */
					}
				}					   //12345678
				//¬еселые комбинации
				/*
				 * 16777216 - норма
				 * 25500139 - €рко-темнозеленый
				 * 15678234 - сочно-зеленый
				 * 40293745 - кроваво -красный цвет €рости
				 */

				lightmapTexture.updateDynamicTexture();
				lightmapUpdateNeeded = false;
				Minecraft.getMinecraft().mcProfiler.endSection();
			}
		}
		return;
	}
	public static float getNightVisionBrightness(EntityLivingBase entitylivingbaseIn, float partialTicks)
	{
		int i = entitylivingbaseIn.getActivePotionEffect(MobEffects.NIGHT_VISION).getDuration();
		return i > 200 ? 1.0F : 0.7F + MathHelper.sin(((float)i - partialTicks) * (float)Math.PI * 0.2F) * 0.3F;
	}
	public static void getLightmapColors(float partialTicks, float sunBrightness, float skyLight, float blockLight, float[] colors) {

	}
	
	  private  static void preRenderDamagedBlocks()
	    {
	        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.DST_COLOR, GlStateManager.DestFactor.SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        GlStateManager.enableBlend();
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
	        GlStateManager.doPolygonOffset(-3.0F, -3.0F);
	        GlStateManager.enablePolygonOffset();
	        GlStateManager.alphaFunc(516, 0.1F);
	        GlStateManager.enableAlpha();
	        GlStateManager.pushMatrix();
	    }

	    private static void postRenderDamagedBlocks()
	    {
	        GlStateManager.disableAlpha();
	        GlStateManager.doPolygonOffset(0.0F, 0.0F);
	        GlStateManager.disablePolygonOffset();
	        GlStateManager.enableAlpha();
	        GlStateManager.depthMask(true);
	        GlStateManager.popMatrix();
	    }
	    private  static Map<Integer, DestroyBlockProgress> damagedBlocks = Maps.<Integer, DestroyBlockProgress>newHashMap();
	    //хук в рендер ворд пасс либо запилить что то наподобие своей системы, но хуки придетс€ юзать все равно иначе без ванильки останусь
	    private  static TextureAtlasSprite destroyBlockIcons = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("realism:blocks/panehorizontal");
	//	@Hook(returnCondition = ReturnCondition.ALWAYS)
	

	
	
	
	
}
/*
 * получить игрока из ентиту
if (entity instanceof EntityPlayer) {
   EntityPlayer player = (EntityPlayer) entity;

  }
  получить игрока из мира
world.loadedEntityList
 */
