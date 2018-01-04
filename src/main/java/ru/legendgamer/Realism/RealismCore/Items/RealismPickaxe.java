package ru.legendgamer.Realism.RealismCore.Items;

import net.minecraft.item.ItemPickaxe;
import ru.legendgamer.Realism.RealismCore.Realism;

public class RealismPickaxe extends ItemPickaxe
{
    public RealismPickaxe(String name, ToolMaterial material)
    {
        super(material);
   setCreativeTab(Realism.tabMain);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
       
    }
}
