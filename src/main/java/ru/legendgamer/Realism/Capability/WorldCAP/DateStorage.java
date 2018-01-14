package ru.legendgamer.Realism.Capability.WorldCAP;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

public final class DateStorage implements Capability.IStorage<IDate>, Callable<IDate>
{
    public static final DateStorage INSTANCE = new DateStorage();

    @Override
    public NBTBase writeNBT(Capability<IDate> capability, IDate instance, EnumFacing side)
    {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setByte("day", instance.getDay());
        compound.setByte("week", instance.getWeek());
        compound.setByte("month", instance.getMonth());
        compound.setShort("year", instance.getYear());
        compound.setBoolean("isSnow", instance.getEnableSnow());
        return compound;
    }

    @Override
    public void readNBT(Capability<IDate> capability, IDate instance, EnumFacing side, NBTBase nbt)
    {
        NBTTagCompound compound = (NBTTagCompound) nbt;
        instance.setDay(compound.getByte("day"));
        instance.setWeek(compound.getByte("week"));
        instance.setMonth(compound.getByte("month"));
        instance.setYear(compound.getShort("year"));
        instance.setEnableSnow(compound.getBoolean("isSnow"));
    }

    @Override
    public IDate call()
    {
        return new Date();
    }
}