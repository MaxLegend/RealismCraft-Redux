package ru.legendgamer.Realism.Events.Thermometr;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.DateProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.IDate;
import ru.legendgamer.Realism.RealismCore.RegItems;

public class ThermometrRenderEvent {


	static Minecraft mc = Minecraft.getMinecraft();
	

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void thermoRender(RenderGameOverlayEvent.Post event) {
		World world = mc.world;
		IPlayerCap cap = Minecraft.getMinecraft().player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
		
		if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() == RegItems.thermo || Minecraft.getMinecraft().player.getHeldItemOffhand().getItem() == RegItems.thermo) {
			 if (event.isCancelable() || event.getType() != ElementType.FOOD) return;
			  ResourceLocation textureLevelBattery = new ResourceLocation("realism:textures/gui/battery.png");
				
				GL11.glPushMatrix();
				GL11.glEnable(GL11.GL_BLEND);
				
				int x = event.getResolution().getScaledWidth() / 2 + 10;
				int y = event.getResolution().getScaledHeight() - 49;
				mc.renderEngine.bindTexture(textureLevelBattery);
				mc.ingameGUI.drawTexturedModalRect(x + 82,y+ 32,0,0,42,42);
				
				if(cap.getTempBody() < 36)
					mc.ingameGUI.drawTexturedModalRect(x,y,16,0,6,16);
				
				if(cap.getTempBody() < 37 && cap.getTempBody() > 36)
					mc.ingameGUI.drawTexturedModalRect(x + 84,y+ 32,45,0,10,42);
				
				if(cap.getTempBody() > 37 && cap.getTempBody() < 39)
					mc.ingameGUI.drawTexturedModalRect(x + 84,y+ 32,45,0,14,42);
				
				if(cap.getTempBody() > 39)
					mc.ingameGUI.drawTexturedModalRect(x + 84,y+ 32,45,0,18,42);
					
					
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glPopMatrix();
			}
		}
}
