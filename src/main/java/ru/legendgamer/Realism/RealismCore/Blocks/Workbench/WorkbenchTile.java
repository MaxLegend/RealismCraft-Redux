/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  javax.annotation.Nullable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.SPacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package ru.legendgamer.Realism.RealismCore.Blocks.Workbench;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorkbenchTile extends TileEntity {
    public NonNullList<ItemStack> mCraftMatrix = NonNullList.withSize((int)9, ItemStack.EMPTY);

    public List<ItemStack> getCraftMatrixItems() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (ItemStack stack : this.mCraftMatrix) {
            if (stack == null) continue;
            builder.add(stack);
        }
        return builder.build();
    }

    private void writeSlots(NBTTagCompound nbt) {
        nbt.setInteger("Capacity", this.mCraftMatrix.size());
        for (int i = 0; i < 9; ++i) {
            if (this.mCraftMatrix.get(i) == ItemStack.EMPTY) {
                nbt.removeTag("Slot" + i);
                continue;
            }
            NBTTagCompound slot = new NBTTagCompound();
            ((ItemStack)this.mCraftMatrix.get(i)).writeToNBT(slot);
            nbt.setTag("Slot" + i, (NBTBase)slot);
        }
    }

    private void readSlots(NBTTagCompound nbt) {
        int capacity = 9;
        if (nbt.hasKey("Capacity", 3)) {
            capacity = nbt.getInteger("Capacity");
        }
        this.ensureCraftMatrixCapacity(capacity);
        for (int i = 0; i < 9; ++i) {
            if (!nbt.hasKey("Slot" + i, 10)) {
                this.mCraftMatrix.set(i, ItemStack.EMPTY);
                continue;
            }
            this.mCraftMatrix.set(i, new ItemStack(nbt.getCompoundTag("Slot" + i)));
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.readSlots(nbt);
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.writeSlots(nbt);
        return nbt;
    }

    public NBTTagCompound getUpdateTag() {
        NBTTagCompound nbt = super.getUpdateTag();
        this.writeSlots(nbt);
        return nbt;
    }

    public void handleUpdateTag(NBTTagCompound tag) {
        super.handleUpdateTag(tag);
        this.readSlots(tag);
    }

    @Nullable
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(), 0, this.getUpdateTag());
    }

    @SideOnly(value=Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        this.handleUpdateTag(pkt.getNbtCompound());
    }

    public void ensureCraftMatrixCapacity(int capacity) {
        if (this.mCraftMatrix.size() != capacity) {
            this.mCraftMatrix = NonNullList.withSize((int)capacity, ItemStack.EMPTY);
        }
    }
}

