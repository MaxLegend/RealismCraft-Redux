package ru.legendgamer.Realism.RealismCore.Items;

import java.util.Random;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.legendgamer.Realism.RealismCore.Realism;
import ru.legendgamer.Realism.RealismCore.RegItems;

public class PointedStone extends ItemTool {
	   private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.GRASS);
	    private static final float[] ATTACK_DAMAGES = new float[] {6.0F, 8.0F, 8.0F, 8.0F, 6.0F};
	    private static final float[] ATTACK_SPEEDS = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F};
	    
	    public PointedStone(String name, Item.ToolMaterial material, int maxDamage) {
	        super(material, EFFECTIVE_ON);
	        this.maxStackSize = 1;
	        this.setMaxDamage(maxDamage);
	     this.setRegistryName(name);
	  this.setCreativeTab(Realism.tabMain);
	  this.setUnlocalizedName(name);
	        this.attackDamage = ATTACK_DAMAGES[material.ordinal()];
	        this.attackSpeed = ATTACK_SPEEDS[material.ordinal()];
	    }
	    public PointedStone(Item.ToolMaterial material, float damage, float speed) {
	        super(material, EFFECTIVE_ON);
	        this.attackDamage = damage;
	        this.attackSpeed = speed;
	    }
	    public float getDestroySpeed(ItemStack stack, IBlockState state) {
	        Material material = state.getMaterial();
	        return material != Material.GRASS ? super.getDestroySpeed(stack, state) : this.efficiency;
	    }
	 @Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	 {
	  if (world.getBlockState(pos).getBlock() == Blocks.GRASS) {
	   world.setBlockState(pos,Blocks.DIRT.getDefaultState());
	   player.inventory.addItemStackToInventory(new ItemStack(RegItems.turf));
	   player.getHeldItem(EnumHand.MAIN_HAND).damageItem(1, player);
	  }
	  return EnumActionResult.PASS;
	 }
	  @Override
	     public ItemStack getContainerItem(ItemStack stack) {
	    stack.attemptDamageItem(1, new Random(), null);
	         return stack.copy();
	       }
	  
	  @Override
	  public boolean hasContainerItem(ItemStack stack) {
	   
	         return true;
	     }
	}