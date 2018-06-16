package ru.legendgamer.Realism.Events.GameEvents.RenderEvents;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LayerEvent {
	/*
	 * Я придумал как
Так будет даже более оптимизированно
Получаем коры игрока
И в радиусе кастим в целое
получаем блоки
и рисуем\не рисуем текстуру
	 */
	Minecraft mc = Minecraft.getMinecraft();
	Block block;
	BlockPos  playerpos;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void eventClick(RenderWorldLastEvent event) {
		World world = Minecraft.getMinecraft().world;
		if(!mc.isGamePaused()) {
			GlStateManager.enableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
			GlStateManager.doPolygonOffset(-3.0F, -3.0F);
			GlStateManager.enablePolygonOffset();
			GlStateManager.alphaFunc(516, 0.1F);
			GlStateManager.enableAlpha();
			GlStateManager.pushMatrix();
		
			playerpos = mc.player.getPosition();
			block = mc.world.getBlockState(playerpos).getBlock();
			int x = playerpos.getX();
			int y = playerpos.getY();
			int z = playerpos.getZ();
			BlockPos pos = new BlockPos(x,y,z);

			if(playerpos == null || !playerpos.equals(pos.down())) {
				final int r = 10;
				final int rr = r * r;

				for (int i = pos.getX() - r, is = i + 2 * r + 1; i < is; i++) {
					for (int j = pos.getY() - r, js = j + 2 * r + 1; j < js; j++) {
						for (int k = pos.getZ() - r, ks = k + 2 * r + 1; k < ks; k++) {

							final int xx = pos.getX() - i;
							final int yy = pos.getY() - j;
							final int zz = pos.getZ() - k;
							if (xx * xx + yy * yy + zz * zz <= rr) {
								if(mc.world.getBlockState(new BlockPos(i,j,k)) == Blocks.COBBLESTONE.getDefaultState()) {
									drawBlockTexture(mc.player, mc.getRenderPartialTicks(), i, j, k, mc.world, "realism:textatlas/mold");
								}
							}
						}
					}

				}
			}
			else {


				block = mc.world.getBlockState(playerpos).getBlock();
				//	Block block = mc.world.getBlockState(RayTraceResultUtil.getRayTraceToBlock(mc.player, 1, 5, true).getBlockPos()).getBlock();

				drawBlockTexture(mc.player, 0.5f, pos.getX() - 6, pos.getY()-6 , pos.getZ()- 6, mc.world, "realism:textatlas/mold");


			}
		
			GlStateManager.disableAlpha();
			GlStateManager.doPolygonOffset(0.0F, 0.0F);
			GlStateManager.disablePolygonOffset();
			GlStateManager.enableAlpha();
			GlStateManager.depthMask(true);
			GlStateManager.popMatrix();
		}

	}
	public static void drawBlockTexture(Entity entityIn, float partialTicks, int x,int y,int z, World world, String texture) {
		double d3 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double) partialTicks;
		double d4 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double) partialTicks;
		double d5 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double) partialTicks;
		Tessellator tessellatorIn = Tessellator.getInstance();
		BufferBuilder bufferBuilderIn = tessellatorIn.getBuffer();
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		bufferBuilderIn.begin(7, DefaultVertexFormats.BLOCK);
		bufferBuilderIn.setTranslation(-d3, -d4, -d5);
		bufferBuilderIn.noColor();
		IBlockState iblockstate = world.getBlockState(new BlockPos(x,y,z));
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		blockrendererdispatcher.renderBlockDamage(iblockstate, new BlockPos(x,y,z), texturemap.getAtlasSprite("realism:textatlas/mold"), world);
		tessellatorIn.draw();
		bufferBuilderIn.setTranslation(0.0D, 0.0D, 0.0D);

	}
	@SubscribeEvent
	public void worldrender(EntityViewRenderEvent.CameraSetup event) {
		applyBobbing((float)event.getRenderPartialTicks());
	}

	public void applyBobbing(float partialTicks)
	{
		if (mc.getRenderViewEntity() instanceof EntityPlayer)
		{
			if(mc.gameSettings.viewBobbing) {
				EntityPlayer player = (EntityPlayer)mc.getRenderViewEntity();
				if(player.isSprinting()) {
					float f = player.distanceWalkedModified - player.prevDistanceWalkedModified;
					float f1 = -(player.distanceWalkedModified + f * partialTicks);
					float f2 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
					float f3 = player.prevCameraPitch + (player.cameraPitch - player.prevCameraPitch) * partialTicks;
					GlStateManager.translate(MathHelper.sin(f1 * (float)Math.PI) * f2 * 0.1F, -Math.abs(MathHelper.cos(f1 * (float)Math.PI) * f2), 0.0F);
					GlStateManager.rotate(MathHelper.sin(f1 * (float)Math.PI) * f2 * 30.0F, 0.0F, 0.0F, 1.0F);
					GlStateManager.rotate(Math.abs(MathHelper.cos(f1 * (float)Math.PI - 0.2F) * f2) * 5.0F, 1.0F, 0.0F, 0.0F);
					GlStateManager.rotate(f3, 1.0F, 0.0F, 1.0F);
				} else {
					float f = player.distanceWalkedModified - player.prevDistanceWalkedModified;
					float f1 = -(player.distanceWalkedModified + f * partialTicks);
					float f2 = player.prevCameraYaw + (player.cameraYaw - player.prevCameraYaw) * partialTicks;
					float f3 = player.prevCameraPitch + (player.cameraPitch - player.prevCameraPitch) * partialTicks;

					GlStateManager.translate(MathHelper.cos(f1 * (float)Math.PI) * f2 * 0.1F, -Math.abs(MathHelper.cos(f1 * (float)Math.PI) * f2), 0.0F);
					GlStateManager.rotate(MathHelper.sin(f1 * (float)Math.PI) * f2 * 20.0F, 0.0F, 0.0F, 1.0F);
					GlStateManager.rotate(Math.abs(MathHelper.cos(f1 * (float)Math.PI - 0.2F) * f2) * 5.0F, 1.0F, 0.0F, 0.0F);
					GlStateManager.rotate(f3, 1.0F, 0.0F, 1.0F);

				}
			}
		}
	}






	@SideOnly(Side.CLIENT)
	@SubscribeEvent 
	public void spriteRegisterEventPre(TextureStitchEvent.Pre event) {  
		ResourceLocation glass = new ResourceLocation("realism:textatlas/mold");  
		event.getMap().registerSprite(glass); 
	}

}
