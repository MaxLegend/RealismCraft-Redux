
package ru.legendgamer.Realism.RealismCore.Blocks.Workbench;

import java.util.Map;
import java.util.WeakHashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockSpecial;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class WorkbenchRenderer extends TileEntitySpecialRenderer<WorkbenchTile> {
    Map<WorkbenchTile, RenderingState> states = new WeakHashMap<WorkbenchTile, RenderingState>();
    static final int ANIMATION_DURATION = 1000;

    public void render(WorkbenchTile tile, double xOffset, double yOffset, double zOffset, float partialTicks, int destroyStage, float alpha) {
        super.render((WorkbenchTile)tile, xOffset, yOffset, zOffset, partialTicks, destroyStage, alpha);
        for (int i = 0; i < tile.mCraftMatrix.size(); ++i) {
            Item item;
            ItemStack itemStack = (ItemStack)tile.mCraftMatrix.get(i);
            if (itemStack == ItemStack.EMPTY) continue;
            RenderingState state = this.states.computeIfAbsent(tile, k -> new RenderingState());
            double playerAngle = (Math.atan2(xOffset + 0.5, zOffset + 0.5) + 3.9269908169872414) % 6.283185307179586;
            byte sector = (byte)(playerAngle * 2.0 / 3.141592653589793);
            long time = System.currentTimeMillis();
            if (state.sector != sector) {
                state.animating = true;
                state.animationAngleStart = state.currentAngle;
                float delta1 = (float)sector * 90.0f - state.currentAngle;
                float abs1 = Math.abs(delta1);
                float delta2 = delta1 + 360.0f;
                float abs2 = Math.abs(delta2);
                float delta3 = delta1 - 360.0f;
                float abs3 = Math.abs(delta3);
                state.animationAngleEnd = abs3 < abs1 && abs3 < abs2 ? delta3 + state.currentAngle : (abs2 < abs1 && abs2 < abs3 ? delta2 + state.currentAngle : delta1 + state.currentAngle);
                state.startTime = time;
                state.sector = sector;
            }
            if (state.animating) {
                if (time >= state.startTime + 1000) {
                    state.animating = false;
                    state.currentAngle = (state.animationAngleEnd + 360.0f) % 360.0f;
                } else {
                    state.currentAngle = (WorkbenchRenderer.easeOutQuad(time - state.startTime, state.animationAngleStart, state.animationAngleEnd - state.animationAngleStart, 1000) + 360.0f) % 360.0f;
                }
            }
            Block block = (item = itemStack.getItem()) instanceof ItemBlock ? ((ItemBlock)item).getBlock() : (item instanceof ItemBlockSpecial ? ((ItemBlockSpecial)item).getBlock() : null);
            boolean normalBlock = block != null && block.getDefaultState().getMaterial().isSolid();
            float shift = (float)Math.abs((time + (long)(i * 1000)) % 5000 - 2500) / 200000.0f;
            GlStateManager.pushMatrix();
            GlStateManager.translate((double)(xOffset + 0.5), (double)(yOffset + (double)shift), (double)(zOffset + 0.5));
            GlStateManager.rotate((float)state.currentAngle, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.translate((double)((double)(i % 3) * 3.0 / 16.0 + 0.3125 - 0.5), (double)1.09375, (double)((double)(i / 3) * 3.0 / 16.0 + 0.3125 - 0.5));
            if (!normalBlock) {
                GlStateManager.rotate((float)(- this.rendererDispatcher.entityPitch), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GlStateManager.scale((float)0.125f, (float)0.125f, (float)0.125f);
            Minecraft.getMinecraft().getRenderItem().renderItem(itemStack, ItemCameraTransforms.TransformType.NONE);
            GlStateManager.popMatrix();
        }
    }

    private static float easeOutQuad(long t, float b, float c, int d) {
        float z = (float)t / (float)d;
        return (- c) * z * (z - 2.0f) + b;
    }

    static class RenderingState {
        byte sector;
        float currentAngle;
        boolean animating;
        float animationAngleStart;
        float animationAngleEnd;
        long startTime;

        RenderingState() {
        }
    }

}

