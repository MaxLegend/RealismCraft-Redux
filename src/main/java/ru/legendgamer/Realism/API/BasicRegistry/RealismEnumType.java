package ru.legendgamer.Realism.API.BasicRegistry;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.material.MapColor;
import net.minecraft.util.IStringSerializable;

public  enum RealismEnumType implements IStringSerializable
{
    OAK(0, "oak", MapColor.WOOD),
    SPRUCE(1, "spruce", MapColor.OBSIDIAN),
    BIRCH(2, "birch", MapColor.SAND),
    JUNGLE(3, "jungle", MapColor.DIRT),
    ACACIA(4, "acacia", MapColor.ADOBE),
    DARK_OAK(5, "dark_oak", "big_oak", MapColor.BROWN);

    private static final RealismEnumType[] META_LOOKUP = new RealismEnumType[values().length];
    private final int meta;
    private final String name;
    private final String unlocalizedName;
    /** The color that represents this entry on a map. */
    private final MapColor mapColor;

    private RealismEnumType(int metaIn, String nameIn, MapColor mapColorIn)
    {
        this(metaIn, nameIn, nameIn, mapColorIn);
    }

    private RealismEnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
    {
        this.meta = metaIn;
        this.name = nameIn;
        this.unlocalizedName = unlocalizedNameIn;
        this.mapColor = mapColorIn;
    }

    public int getMetadata()
    {
        return this.meta;
    }

    /**
     * The color which represents this entry on a map.
     */
    public MapColor getMapColor()
    {
        return this.mapColor;
    }

    public String toString()
    {
        return this.name;
    }

    public static RealismEnumType byMetadata(int meta)
    {
        if (meta < 0 || meta >= META_LOOKUP.length)
        {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String getName()
    {
        return this.name;
    }

    public String getUnlocalizedName()
    {
        return this.unlocalizedName;
    }

    static
    {
        for (RealismEnumType enumtype : values())
        {
            META_LOOKUP[enumtype.getMetadata()] = enumtype;
        }
    }
}