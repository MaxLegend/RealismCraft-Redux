package ru.legendgamer.Realism.RealismCore.Items;

import net.minecraft.item.ItemAxe;
import ru.legendgamer.Realism.RealismCore.Realism;

public class NewStoneAxe extends ItemAxe {
    public NewStoneAxe(String name, ToolMaterial material) {
        super(material, 0, 0);
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(Realism.tabMain);
    }
}
