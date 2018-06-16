package ru.legendgamer.Realism.RealismCore;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class RegSounds
{
   public static SoundEvent buzzlump = new SoundEvent(new ResourceLocation("realism:buzzlump"));
   public static SoundEvent enablelump = new SoundEvent(new ResourceLocation("realism:enablelump"));
   
   public static SoundEvent regSound(String name) {
	   ResourceLocation res = new ResourceLocation(name);
	   return new SoundEvent(res).setRegistryName(res);
   }
   @SubscribeEvent
   public void init(RegistryEvent.Register<SoundEvent> event)
   {   
	   IForgeRegistry<SoundEvent> event2 = (IForgeRegistry<SoundEvent>)event.getRegistry();
	   event2.register(buzzlump);
	   event2.register(enablelump);
   }
   
}
