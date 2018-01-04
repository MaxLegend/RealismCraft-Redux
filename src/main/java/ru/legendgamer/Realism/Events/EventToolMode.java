package ru.legendgamer.Realism.Events;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import ru.legendgamer.Realism.Proxy.ClientProxy;
import ru.legendgamer.Realism.RealismCore.Items.ItemRealShovel;

public class EventToolMode {
	
	public static KeyBinding KEY_TOOL_MODE = new KeyBinding("key.toolMode", Keyboard.KEY_J, ClientProxy.KEY_CATEGORY);
	
	public static enum EnumToolMode {
		FULL(0), TAKE(1), ROAD(2);
		
		private int id;
		 
		EnumToolMode(int id) { this.id = id; }
		
		public int getId() { return id; }
		
		public EnumToolMode getNext() {
			switch(this) {
			case FULL : return TAKE;
			case TAKE : return ROAD;
			default: return FULL;
			}
		}
		
		public EnumToolMode getPrev() {
			switch(this) {
			case FULL : return ROAD;
			case TAKE : return FULL;
			default: return ROAD;
			}
		}
	}
	
	static Minecraft mc = Minecraft.getMinecraft();
	public static final ResourceLocation modesTexture = new ResourceLocation("realism:textures/gui/tools_modes.png");
	
	@SubscribeEvent
	public void renderWLevel(RenderGameOverlayEvent.Post event) {
		if (event.isCancelable() && event.getType() != ElementType.TEXT) return;
		if (mc.player.getHeldItemMainhand().getItem() instanceof ItemRealShovel) {
			GL11.glPushMatrix();
			GlStateManager.enableBlend();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			mc.renderEngine.bindTexture(modesTexture);
			mc.ingameGUI.drawTexturedModalRect(4, 4, ClientProxy.currentToolMode.getId() * 20, 0, 20, 20);
			GL11.glPopMatrix();
		}
	}
	
	@SubscribeEvent
	public void onKey(KeyInputEvent event) {
		if (KEY_TOOL_MODE.isPressed())
			ClientProxy.currentToolMode = ClientProxy.currentToolMode.getNext();
	}
}
