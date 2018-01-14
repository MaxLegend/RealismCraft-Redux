package ru.legendgamer.Realism.RealismCore.Layers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface ILayerRenderBlock<T extends Block>
{
    void render(Block block, BlockPos pos, World world, EntityPlayer player);
    
}
