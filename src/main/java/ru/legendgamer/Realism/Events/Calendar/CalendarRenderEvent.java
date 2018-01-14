package ru.legendgamer.Realism.Events.Calendar;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.legendgamer.Realism.Capability.PlayerCAP.IPlayerCap;
import ru.legendgamer.Realism.Capability.PlayerCAP.PlayerCapProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.DateProvider;
import ru.legendgamer.Realism.Capability.WorldCAP.IDate;
import ru.legendgamer.Realism.RealismCore.RegItems;

public class CalendarRenderEvent {
	static Minecraft mc = Minecraft.getMinecraft();
	public static final ResourceLocation guiCalendar = new ResourceLocation("realism:textures/gui/calendar.png");


	@SubscribeEvent
	public void calendar(RenderGameOverlayEvent.Post event) {
		World world = FMLCommonHandler.instance().getMinecraftServerInstance().getServer().getEntityWorld();
       
		IDate date = world.getCapability(DateProvider.DATE, null);
		IPlayerCap cap = Minecraft.getMinecraft().player.getCapability(PlayerCapProvider.LEVEL_CAP, null);
		if(Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() == RegItems.calendar) {
			if (event.isCancelable() || event.getType() != ElementType.FOOD) return;
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			FontRenderer fontrenderer = Minecraft.getMinecraft().fontRenderer;
			mc.renderEngine.bindTexture(guiCalendar);
			int x = event.getResolution().getScaledWidth() / 2 + 10;
			int y = event.getResolution().getScaledHeight() - 49;
			mc.ingameGUI.drawTexturedModalRect(x + 170,y-117,0,0,150,150);
			fontrenderer.drawString("Day: "  + date.getDay(), x +180, y - 90, 0x000000);
			fontrenderer.drawString("Month: "  + date.getMonth(), x+ 180, y - 100, 0x000000);
			fontrenderer.drawString("Year: "  + date.getYear(), x+ 180, y - 110, 0x000000);
			fontrenderer.drawString("Hour: " + world.getWorldTime()/1000, x+180, y - 80, 0x000000);
			
		//	fontrenderer.drawString("Total time: "  + world.getTotalWorldTime(), x- 240, y - 50, 0x000000);
		//	fontrenderer.drawString("Time: "  + world.getWorldTime(), x- 240, y - 60, 0x000000);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
	}
}
