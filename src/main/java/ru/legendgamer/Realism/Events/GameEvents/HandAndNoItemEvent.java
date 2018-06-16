package ru.legendgamer.Realism.Events.GameEvents;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.legendgamer.Realism.Proxy.ClientProxy;

public class HandAndNoItemEvent {
	public static KeyBinding keyDestroyOffhand = new KeyBinding("key.keyDestroyOffhand", Keyboard.KEY_B, ClientProxy.KEY_CATEGORY);
	static Minecraft mc = Minecraft.getMinecraft();
	@SubscribeEvent
	public void offcanning(EntityItemPickupEvent e) {
		e.setCanceled(true);
		if(keyDestroyOffhand.isPressed()) {
			e.setCanceled(false);
		}
	}
	@SubscribeEvent
	public void etop(RenderHandEvent e) {
		
	//	}
	}

}
