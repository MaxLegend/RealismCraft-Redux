package ru.legendgamer.Realism.RealismCore.Particle;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleEvent{
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent 
	public void spriteRegisterEventPre(TextureStitchEvent.Pre event) {      
		ResourceLocation leavespart = new ResourceLocation("realism:particles/leavespart");  
		event.getMap().registerSprite(leavespart); 
		}
	}
