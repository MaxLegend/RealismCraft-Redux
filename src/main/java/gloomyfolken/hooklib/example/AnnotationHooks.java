package gloomyfolken.hooklib.example;

import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

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
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStem;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.API.BasicItem.BasicDamageFood;
import ru.legendgamer.Realism.API.BasicItem.BasicFood;
import ru.legendgamer.Realism.API.BasicRegistry.BasicRegistry;
import ru.legendgamer.Realism.RealismCore.Realism;
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
	public double getDurabilityForDisplay(ItemStack stack)
	{
		return (double)stack.getItemDamage() / (double)stack.getMaxDamage();
	}
	

	@Hook(returnCondition = ReturnCondition.ALWAYS, isMandatory = true)
	public static void registerBlock(Block block, int id, ResourceLocation res, Block blockToRegister) {
		if(id == 17) {
			Block.REGISTRY.register(id, res, BasicRegistry.realismLogs);
		} else 	if(id == 12) {
			Block.REGISTRY.register(id, res, BasicRegistry.realsand);
		}
		else
			Block.REGISTRY.register(id, res, blockToRegister);
	}

	@Hook(returnCondition = ReturnCondition.ON_NOT_NULL, isMandatory = true)
	public static Block getRegisteredBlock(Blocks inst, String name) {
		if(name.equals("log")){
			return BasicRegistry.realismLogs;
		} 
		else 	
			if(name.equals("sand")){
			return BasicRegistry.realsand;
		} else

		return null;
	}

	
	

	@SideOnly(Side.CLIENT)
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void renderItemOverlayIntoGUI(RenderItem ri, FontRenderer fr, ItemStack stack, int xPosition, int yPosition, @Nullable String text)
	{
		if (!stack.isEmpty())
		{
			if (stack.getCount() != 1 || text != null)
			{
				String s = text == null ? String.valueOf(stack.getCount()) : text;
				GlStateManager.disableLighting();
				GlStateManager.disableDepth();
				GlStateManager.disableBlend();
				fr.drawStringWithShadow(s, (float)(xPosition + 19 - 2 - fr.getStringWidth(s)), (float)(yPosition + 6 + 3), 16777215);
				GlStateManager.enableLighting();
				GlStateManager.enableDepth();
				GlStateManager.enableBlend();
			}

			if (stack.getItem().showDurabilityBar(stack))
			{
				GlStateManager.disableLighting();
				GlStateManager.disableDepth();
				GlStateManager.disableTexture2D();
				GlStateManager.disableAlpha();
				GlStateManager.disableBlend();
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuffer();
				double health = stack.getItem().getDurabilityForDisplay(stack);
				int rgbfordisplay = stack.getItem().getRGBDurabilityForDisplay(stack);
				int i = Math.round(13.0F - (float)health * 13.0F);
				int j = rgbfordisplay;
				draw(bufferbuilder, xPosition + 2, yPosition + 12, 13, 3, 0, 0, 0, 255);
				draw(bufferbuilder, xPosition + 2, yPosition + 12, i, 1, j >> 16 & 255, j >> 8 & 255, j & 255, 255);
				GlStateManager.enableBlend();
				GlStateManager.enableAlpha();
				GlStateManager.enableTexture2D();
				GlStateManager.enableLighting();
				GlStateManager.enableDepth();
			}

			EntityPlayerSP entityplayersp = Minecraft.getMinecraft().player;
			float f3 = entityplayersp == null ? 0.0F : entityplayersp.getCooldownTracker().getCooldown(stack.getItem(), Minecraft.getMinecraft().getRenderPartialTicks());

			if (stack.getItem() instanceof BasicFood)
			{
				GlStateManager.disableLighting();
				GlStateManager.disableDepth();
				GlStateManager.disableTexture2D();
				GlStateManager.disableAlpha();
				GlStateManager.disableBlend();
				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder bufferbuilder = tessellator.getBuffer();	

				BasicDamageFood bf = (BasicDamageFood)stack.getItem();	
				NBTTagCompound tag = BasicDamageFood.getNbt(stack);

				int health = tag.getInteger(BasicDamageFood.CHARACTERISTIC);
				int rgbfordisplay = stack.getItem().getRGBDurabilityForDisplay(stack);
				int i = Math.round(13.0F - (float)health * 13.0F);
				int j = rgbfordisplay; 

				draw(bufferbuilder, xPosition + 2, yPosition + 13, health, 1,
						j >> 16 & 255,
						j >> 8 & 255,
						j & 255, 255 
						);
				GlStateManager.enableBlend();
				GlStateManager.enableAlpha();
				GlStateManager.enableTexture2D();
				GlStateManager.enableLighting();
				GlStateManager.enableDepth();
			}

			if (f3 > 0.0F)
			{
				GlStateManager.disableLighting();
				GlStateManager.disableDepth();
				GlStateManager.disableTexture2D();
				Tessellator tessellator1 = Tessellator.getInstance();
				BufferBuilder bufferbuilder1 = tessellator1.getBuffer();
				draw(bufferbuilder1, xPosition, yPosition + MathHelper.floor(16.0F * (1.0F - f3)), 16, MathHelper.ceil(16.0F * f3), 255, 255, 255, 127);
				GlStateManager.enableTexture2D();
				GlStateManager.enableLighting();
				GlStateManager.enableDepth();
			}
		}
	}

	private static void draw(BufferBuilder renderer, int x, int y, int width, int height, int red, int green, int blue, int alpha)
	{
		renderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
		renderer.pos((double)(x + 0), (double)(y + 0), 0.0D).color(red, green, blue, alpha).endVertex();
		renderer.pos((double)(x + 0), (double)(y + height), 0.0D).color(red, green, blue, alpha).endVertex();
		renderer.pos((double)(x + width), (double)(y + height), 0.0D).color(red, green, blue, alpha).endVertex();
		renderer.pos((double)(x + width), (double)(y + 0), 0.0D).color(red, green, blue, alpha).endVertex();
		Tessellator.getInstance().draw();
	}

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

	
	@Hook(returnCondition = ReturnCondition.ALWAYS)
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        int i = 1;
        int j = 2;
        int k = pos.getX();
        int l = pos.getY();
        int i1 = pos.getZ();
		if(worldIn.isAirBlock(pos.down())) {		
			Realism.proxy.particleClient(worldIn, pos, worldIn.rand);
	
		}
        if (worldIn.isAreaLoaded(new BlockPos(k - 2, l - 2, i1 - 2), new BlockPos(k + 2, l + 2, i1 + 2)))
        {
            for (int j1 = -1; j1 <= 1; ++j1)
            {
                for (int k1 = -1; k1 <= 1; ++k1)
                {
                    for (int l1 = -1; l1 <= 1; ++l1)
                    {
                        BlockPos blockpos = pos.add(j1, k1, l1);
                        IBlockState iblockstate = worldIn.getBlockState(blockpos);

                        if (iblockstate.getBlock().isLeaves(iblockstate, worldIn, blockpos))
                        {
                            iblockstate.getBlock().beginLeavesDecay(iblockstate, worldIn, blockpos);
                        }
                    }
                }
            }
        }
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

	@SideOnly(Side.CLIENT)
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

		//блоки хук окраска пиздатая
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


}
/*
 * получить игрока из ентиту
if (entity instanceof EntityPlayer) {
   EntityPlayer player = (EntityPlayer) entity;

  }
  получить игрока из мира
world.loadedEntityList
 */
