package ru.legendgamer.Realism.Events;

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
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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

		PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
		

				/*
				int x = oldpos.getX();
				int y = oldpos.getY();
				int z = oldpos.getZ();


				int x1;
				int y1;
				int z1;
				float r = 10;
				float i;

				while(r > 0) {
					float dn = (float)(1/(r*Math.PI));
					float j = 0;
					while(j < 2*Math.PI) {
						i = 0;
						z1 = (int)Math.round(r*Math.sin(j)) + z;
						while(i < 2*Math.PI) {
							x1 = (int)Math.round(r*Math.cos(i)*Math.cos(j)) + x;
							y1 = (int)Math.round(r*Math.sin(i)*Math.cos(j)) + y;
							drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(x1,y1,z1), mc.world,"double_plant_syringa_top");
							i=i+dn;
						}
						j=j+dn;
					}
					r = r - 1;
				}
				 */

				//drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(oldpos.getX() - j - 1, oldpos.getY(), oldpos.getZ() + i), mc.world,"name");
				//		drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(oldpos.getX() - i, oldpos.getY(), oldpos.getZ() -j -1), mc.world,"name");
				//	drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(oldpos.getX()+j + 1, oldpos.getY(), oldpos.getZ() - i), mc.world,"double_plant_syringa_top");}}}


				/*
				for(int i = 0; i < ConfigManager.radiusRenderMold; i++) {		
					drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(oldpos.getX() , oldpos.getY() - i , oldpos.getZ()), mc.world,"double_plant_syringa_top");
					for(int j = 0; j < ConfigManager.radiusRenderMold - 1; j++) {
						for(int k = 0; k < ConfigManager.radiusRenderMold - 2; k++) {
						//if(mc.world.getBlockState(oldpos)== Blocks.BEDROCK.getDefaultState()) {
								drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(oldpos.getX() + i, oldpos.getY() -k +j+i , oldpos.getZ()+j + 1), mc.world,"double_plant_syringa_top");
								drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(oldpos.getX() - j - 1, oldpos.getY()-k+j+i , oldpos.getZ() + i), mc.world,"double_plant_syringa_top");
								drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(oldpos.getX() - i, oldpos.getY()-k+j +i, oldpos.getZ() -j -1), mc.world,"double_plant_syringa_top");
								drawBlockTexture(mc.player, mc.getRenderPartialTicks(), new BlockPos(oldpos.getX()+j + 1, oldpos.getY()-k+j+i , oldpos.getZ() - i), mc.world,"double_plant_syringa_top");
						//	}
						}

					}
				}
				 */
		if(!mc.isGamePaused()) {
		
			
			playerpos = mc.player.getPosition();
			block = mc.world.getBlockState(playerpos).getBlock();
			int x = playerpos.getX();
			int y = playerpos.getY();
			int z = playerpos.getZ();
			BlockPos pos = new BlockPos(x,y,z);
			
			 if(playerpos == null || !playerpos.equals(pos.down())) {
				 final int r = 5;
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
			/*	 int r = 7;
				 for (int i = playerpos.getX() - r, is = i + 2 * r + 1; i < is; i++) {
				     for (int j = playerpos.getY() - r, js = j + 2 * r + 1; j < js; j++) {
				         for (int k = playerpos.getZ() - r, ks = k + 2 * r + 1; k < ks; k++) {
				        	 if(mc.world.getBlockState(new BlockPos(i,j,k)) == Blocks.DIRT.getDefaultState()) {
				             drawBlockTexture(mc.player, mc.getRenderPartialTicks(), i, j, k, mc.world, "name");
				        	 }
				         }
				     }
				     
				 }
				 */
			}
			else {

				
				block = mc.world.getBlockState(playerpos).getBlock();
				//	Block block = mc.world.getBlockState(RayTraceResultUtil.getRayTraceToBlock(mc.player, 1, 5, true).getBlockPos()).getBlock();
				
				drawBlockTexture(mc.player, 0.5f, pos.getX() - 6, pos.getY()-6 , pos.getZ()- 6, mc.world, "realism:textatlas/mold");
			
			}

		}

	}
	@SubscribeEvent
    public void worldrender(EntityViewRenderEvent.CameraSetup event) {
       event.setRoll(30);
    }
	private static void preRenderDamagedBlocks()
	{
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

	public static void drawBlockTexture(Entity entityIn, float partialTicks, int x,int y,int z, World world, String texture) {
		double d3 = entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double) partialTicks;
		double d4 = entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double) partialTicks;
		double d5 = entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double) partialTicks;
		Tessellator tessellatorIn = Tessellator.getInstance();
		BufferBuilder bufferBuilderIn = tessellatorIn.getBuffer();
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		preRenderDamagedBlocks();
		bufferBuilderIn.begin(7, DefaultVertexFormats.BLOCK);
		bufferBuilderIn.setTranslation(-d3, -d4, -d5);
		bufferBuilderIn.noColor();
		IBlockState iblockstate = world.getBlockState(new BlockPos(x,y,z));
		TextureMap texturemap = Minecraft.getMinecraft().getTextureMapBlocks();
		BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
		blockrendererdispatcher.renderBlockDamage(iblockstate, new BlockPos(x,y,z), texturemap.getAtlasSprite("realism:textatlas/mold"), world);
		
		tessellatorIn.draw();
		bufferBuilderIn.setTranslation(0.0D, 0.0D, 0.0D);
		postRenderDamagedBlocks();
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent 
	public void spriteRegisterEventPre(TextureStitchEvent.Pre event) {  
		ResourceLocation glass = new ResourceLocation("realism:textatlas/mold");  
		event.getMap().registerSprite(glass); 
	}

}
