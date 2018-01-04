package ru.legendgamer.Realism.RealismCore.Items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.RealismCore.Basic.BasicItem.BasicItem;

public class Rope extends BasicItem {


    public Rope(String name, int maxStackSize) {
		super(name, maxStackSize);
		// TODO Auto-generated constructor stub
	}

	/**
     * Called when a Block is right-clicked with this Item
     */
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Block block = worldIn.getBlockState(pos).getBlock();

        if (!(block instanceof BlockFence))
        {
            return EnumActionResult.PASS;
        }
        else
        {
            if (!worldIn.isRemote)
            {
                attachToFence(player, worldIn, pos);
            }

            return EnumActionResult.SUCCESS;
        }
    }

    public static boolean attachToFence(EntityPlayer player, World worldIn, BlockPos fence)
    {
        EntityLeashKnot entityleashknot = EntityLeashKnot.getKnotForPosition(worldIn, fence);
        boolean flag = false;
        double d0 = 7.0D;
        int i = fence.getX();
        int j = fence.getY();
        int k = fence.getZ();

        for (EntityLiving entityliving : worldIn.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB((double)i - 7.0D, (double)j - 7.0D, (double)k - 7.0D, (double)i + 7.0D, (double)j + 7.0D, (double)k + 7.0D)))
        {
        
        }

        return flag;
    }
}