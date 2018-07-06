package ru.legendgamer.Realism.RealismCore.Blocks.Workbench;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiWorkbench extends GuiContainer
{
   private static final ResourceLocation TEXTURE = new ResourceLocation("realism:textures/gui/bedside.png");
   
   public GuiWorkbench(Container inventorySlotsIn)
   {
       super(inventorySlotsIn);
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
   {
       mc.renderEngine.bindTexture(TEXTURE);
       int x = (this.width - this.xSize) / 2;
       int y = (this.height - this.xSize) / 2;
       drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
   }
}