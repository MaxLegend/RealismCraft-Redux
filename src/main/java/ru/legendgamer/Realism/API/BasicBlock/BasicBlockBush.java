package ru.legendgamer.Realism.API.BasicBlock;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import ru.legendgamer.Realism.RealismCore.Realism;
/**
 * Basics API class by block bush
 * @author LegendGamer
 */
public class BasicBlockBush extends CustomBlockBush {
	public BasicBlockBush(final Material materialIn, final String name)
	{
		setRegistryName(name);
		setUnlocalizedName(name);
		setHardness(0.2F);
		this.setSoundType(SoundType.PLANT); 
		setCreativeTab(Realism.tabMain);
	}
}