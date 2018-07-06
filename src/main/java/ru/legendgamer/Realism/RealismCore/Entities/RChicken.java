package ru.legendgamer.Realism.RealismCore.Entities;

import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import ru.legendgamer.Realism.RealismCore.Entities.ai.RAIFollowParent;
import ru.legendgamer.Realism.RealismCore.Entities.ai.RAIMate;

public class RChicken extends REntityAnimal {
	
    private static final Set<Item> TEMPTATION_ITEMS = Sets.newHashSet(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public float wingRotation;
    public float destPos;
    public float oFlapSpeed;
    public float oFlap;
    public float wingRotDelta = 1.0F;
    public int timeUntilNextEgg;
    
    public RChicken(World world) {
    	this(world, 0, 0, false);
    }
    
    public RChicken(World world, int gender, int type, boolean isTamed) {
    	super(world, gender, type, isTamed);
        setSize(0.4F, 0.7F);
        timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        setPathPriority(PathNodeType.WATER, 0.0F);
    }
    
    public RChicken(World world, int gender, int type, boolean isTamed, double posX, double posY, double posZ) {
		super(world, gender, type, isTamed, posX, posY, posZ);
        setSize(0.4F, 0.7F);
        timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
        setPathPriority(PathNodeType.WATER, 0.0F);
	}
    
    @Override
    protected void initEntityAI() {
        tasks.addTask(0, new EntityAIAvoidEntity<>(this, EntityPlayer.class, 16.0F, 1.0D, 1.53D));
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new RAIMate(this, 1.0D));
        tasks.addTask(3, new EntityAITempt(this, 1.0D, false, TEMPTATION_ITEMS));
        tasks.addTask(4, new RAIFollowParent(this, 1.1D));
        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
        tasks.addTask(6, new EntityAILookIdle(this));
    }
    
    @Override
    public float getEyeHeight() {
        return height;
    }
    
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(4.0D);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }
    
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        oFlap = wingRotation;
        oFlapSpeed = destPos;
        destPos = (float)((double)destPos + (double)(onGround ? -1 : 4) * 0.3D);
        destPos = MathHelper.clamp(destPos, 0.0F, 1.0F);

        if (!onGround && wingRotDelta < 1.0F) {
            wingRotDelta = 1.0F;
        }

        wingRotDelta = (float)((double)wingRotDelta * 0.9D);

        if (!onGround && motionY < 0.0D) {
            motionY *= 0.6D;
        }

        wingRotation += wingRotDelta * 2.0F;

        if (!world.isRemote && !isChild() && --timeUntilNextEgg <= 0) {
            playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F);
            dropItem(Items.EGG, 1);
            timeUntilNextEgg = rand.nextInt(6000) + 6000;
        }
    }
    
    @Override
    public void fall(float distance, float damageMultiplier){}

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_CHICKEN_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.ENTITY_CHICKEN_HURT;
    }
    
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CHICKEN_DEATH;
    }
    
    @Override
    protected void playStepSound(BlockPos pos, Block block) {
    	playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableList.ENTITIES_CHICKEN;
    }

    @Override
    public RChicken createChild(EntityAgeable ageable) {
        return new RChicken(world);
    }
    
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return TEMPTATION_ITEMS.contains(stack.getItem());
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("EggLayTime")) timeUntilNextEgg = compound.getInteger("EggLayTime");
    }
    
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("EggLayTime", timeUntilNextEgg);
    }

	@Override
	public int getCountType() {
		return 3;
	}
}
