
package ru.legendgamer.Realism.RealismCore.Blocks.Workbench;

import javax.annotation.Nullable;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;


public class WorkbenchInventory extends InventoryCrafting {
    private ContainerRWorkbench container;
    private WorkbenchTile mTile;

    public WorkbenchInventory(Container container, int width, int height) {
        super(container, width, height);
        this.initRealBench(width * height);
    }

    private void initRealBench(int capacity) {
        ContainerRWorkbench ContainerRWorkbench = this.container = this.eventHandler instanceof ContainerRWorkbench ? (ContainerRWorkbench)this.eventHandler : null;
        if (this.eventHandler == null) {
            return;
        } 
        this.mTile = this.getTile(this.container);
        if (this.mTile != null) {
            this.mTile.ensureCraftMatrixCapacity(capacity);
            this.stackList = this.mTile.mCraftMatrix;
        }
    }

    public void markDirty() {
        if (this.mTile == null) {
            return;
        }
        this.mTile.markDirty();
        if (this.mTile.getWorld().isRemote) {
            return;
        }
        WorldServer world = (WorldServer)this.mTile.getWorld();
        world.getPlayerChunkMap().markBlockForUpdate(this.mTile.getPos());
    }
    public native WorkbenchTile getTile(ContainerRWorkbench var0);
    public  WorkbenchTile getTile(World world, BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        return tile instanceof WorkbenchTile ? (WorkbenchTile)tile : null;
    }
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        super.setInventorySlotContents(index, stack);
        this.markDirty();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @MethodsReturnNonnullByDefault
    public ItemStack decrStackSize(int index, int count) {
        try {
            ItemStack itemStack = super.decrStackSize(index, count);
            return itemStack;
        }
        finally {
            this.markDirty();
        }
    }
}

