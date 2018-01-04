package ru.legendgamer.Realism.RealismCore.Particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FallingLeaves extends Particle {  
	private final ResourceLocation PARTICLE_TEXTURE = new ResourceLocation("realism:particles/leavespart");
	 
    public float partRed;
    /** The green amount of color. Used as a percentage, 1.0 = 255 and 0.0 = 0. */
    public float partGreen;
    /** The blue amount of color. Used as a percentage, 1.0 = 255 and 0.0 = 0. */
    public float partBlue;
    
	public FallingLeaves(World worldIn, double posXIn, double posYIn, double posZIn, double motionX, double motionY, double motionZ) {  
		super(worldIn, posXIn, posYIn, posZIn); 
		this.particleAlpha = 0.99f;   
		this.particleScale = (this.rand.nextFloat() * 0.5F + 0.5F) * 1.0F;
		particleMaxAge = 1000;     
		this.motionX = motionX;    
		this.motionY = motionY;     
		this.motionY = motionZ;   
		
		Biome biome = worldIn.getBiome(new BlockPos(posXIn,posYIn,posZIn));	
		int color = biome.getFoliageColorAtPos(new BlockPos(posXIn,posYIn,posZIn));
		int R = (color >> 16) & 0xff;         
		int G = (color >> 8) & 0xff;       
		int B = (color) & 0xff;
		
		this.setRBGColorF((float)R/255, (float)G/255, (float)B/255);

	
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(PARTICLE_TEXTURE.toString());
		setParticleTexture(sprite);    
	}   

	@Override 
	public int getFXLayer() { 
		return 1;    
	}    
	@Override   
	public void onUpdate() {   
		super.onUpdate();    
		motionY = -0.00989;      
		if(!this.onGround){  
			motionX += (rand.nextInt(2) == 0 ? rand.nextFloat() *0.003 : -rand.nextFloat() * 0.003); 
			motionZ += (rand.nextInt(2) == 0 ? rand.nextFloat() *0.003 : -rand.nextFloat() * 0.003);   
		}

	}
}