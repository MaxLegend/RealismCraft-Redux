package ru.legendgamer.Realism.RealismCore.Entities;

import java.util.UUID;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class REntityAnimal extends EntityAgeable implements IAnimals {

    protected Block spawnableBlock = Blocks.GRASS;
    private int inLove;
    private UUID playerInLove;
    private boolean isFemale;
    private boolean isTamed;
    private int type;
    private int tick;

    public REntityAnimal(World world) {
    	this(world, 0, 0, false);
    }
    
    public REntityAnimal(World world, int gender, int type) {
    	this(world, gender, type, false);
    }
    
    public REntityAnimal(World world, int gender, int type, boolean isTamed) {
    	super(world);
    	setGender(gender);
    	this.type = type;
    	this.isTamed = isTamed;
    }
    
    public REntityAnimal(World world, int gender, int type, EntityLivingBase entity) {
    	this(world, gender, type, false, entity.posX, entity.posY, entity.posZ);
    }
    
    public REntityAnimal(World world, int gender, int type, boolean isTamed, double posX, double posY, double posZ) {
		super(world);
		setGender(gender);
		this.type = type;
		setPosition(posX, posY, posZ);
    	this.isTamed = isTamed;
	}
    
    /**
     * Returns the gender of the animal. 0 - male, 1 - female
     */
    public int getGender() {return isFemale ? 1 : 0;}
    
    /**
     * Returns the type of the animal.
     */
    public int getType() {return type;}
    
    /**
     * Returns true if the animal is tamed
     */
    public boolean isTamed() {
    	return isTamed;
    }
    
    public void setTamed() {
    	isTamed = true;
    }
    
    public void setType(int type) {
    	if(type >= getCountType()) this.type = getCountType();
    	else if(type < 0) this.type = 0;
    	else this.type = type;
    }
    
    public void setGender(int gender) {
    	int g = 0;
    	if(gender > 1) g = 1;
    	else if(gender < 0) g = 0;
    	else g = gender;
    	isFemale = (g == 1) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public abstract int getCountType();
    
    @Override
    protected float getSoundPitch() {return super.getSoundPitch() + (isFemale ? -0.2F : 0.2F);}

    @Override
    protected void updateAITasks() {if(getGrowingAge() != 0) inLove = 0;super.updateAITasks();}
    
    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        
       if(tick < 600) tick++;
       else {
    	   tick = 0;
    	   if(rand.nextBoolean()) setHealth(getHealth() - (isChild() ? 0.03F : 0.05F));
       }

        if (getGrowingAge() != 0) inLove = 0;

        if (inLove > 0){
            --inLove;

            if (this.inLove % 10 == 0) {
                double d0 = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 =  rand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.HEART, posX + (double)(rand.nextFloat() * width * 2.0F) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), posZ + (double)(rand.nextFloat() * width * 2.0F) - (double)width, d0, d1, d2);
            }
        }
    }
    
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)  {
        if (isEntityInvulnerable(source)) return false;
        else {
            inLove = 0;
            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public float getBlockPathWeight(BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() == spawnableBlock ? 10.0F : world.getLightBrightness(pos) - 0.5F;
    }
    
    @Override
    public double getYOffset() {return 0.14D;}
    
    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(posX);
        int j = MathHelper.floor(getEntityBoundingBox().minY);
        int k = MathHelper.floor(posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        return world.getBlockState(blockpos.down()).getBlock() == spawnableBlock && world.getLight(blockpos) > 8 && super.getCanSpawnHere();
    }
    
    @Override
    public int getTalkInterval() {return 120;}
    
    @Override
    protected boolean canDespawn() {return !isTamed;}
    
    @Override
    protected int getExperiencePoints(EntityPlayer player) {
        return 1 + world.rand.nextInt(3);
    }
    
    public abstract boolean isBreedingItem(ItemStack stack);

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
    	
    	if(!isTamed) return false;
    	
        ItemStack stack = player.getHeldItem(hand);

        if (!stack.isEmpty()) {
            if (isBreedingItem(stack) && getGrowingAge() == 0 && inLove <= 0 && getHealth() == getMaxHealth()) {
                consumeItemFromStack(player, stack);
                setInLove(player);
                return true;
            }
            else setHealth(getHealth() + 0.1F);

            if (isChild() && isBreedingItem(stack) && getHealth() == getMaxHealth()) {
                consumeItemFromStack(player, stack);
                ageUp((int)((float)(-getGrowingAge() / 20) * 0.1F), true);
                return true;
            }
            else setHealth(getHealth() + 0.07F);
        }

        return super.processInteract(player, hand);
    }
    
    protected void consumeItemFromStack(EntityPlayer player, ItemStack stack) {
        if (!player.capabilities.isCreativeMode) stack.shrink(1);
    }

    public void setInLove(@Nullable EntityPlayer player) {
        inLove = 600;
        if (player != null) playerInLove = player.getUniqueID();
        world.setEntityState(this, (byte)18);
    }

    @Nullable
    public EntityPlayerMP getLoveCause() {
        if (playerInLove == null) return null;
        else {
            EntityPlayer entityplayer = world.getPlayerEntityByUUID(playerInLove);
            return entityplayer instanceof EntityPlayerMP ? (EntityPlayerMP)entityplayer : null;
        }
    }
    
    public boolean isInLove() {return inLove > 0;}
    public void resetInLove() {inLove = 0;}
    
    public boolean canMateWith(REntityAnimal otherAnimal) {
        if (otherAnimal == this) return false;
        else if (otherAnimal.getClass() != this.getClass()) return false;
        else {
        	boolean flag1 = isInLove() && otherAnimal.isInLove(); 
        	boolean flag2 = isFemale == otherAnimal.isFemale;
        	boolean flag3 = getHealth() == getMaxHealth() && otherAnimal.getHealth() == otherAnimal.getMaxHealth();
        	return 	flag1 && flag2 && flag3;
        }
    } 
    
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("InLove", inLove);
        compound.setBoolean("Female", isFemale);
        compound.setInteger("Type", type);
        compound.setBoolean("Tamed", isTamed);
        if (playerInLove != null) compound.setUniqueId("LoveCause", playerInLove);
    }
    
    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        inLove = compound.getInteger("InLove");
        isFemale = compound.getBoolean("Female");
        type = compound.getInteger("Type");
        isTamed = compound.getBoolean("Tamed");
        playerInLove = compound.hasUniqueId("LoveCause") ? compound.getUniqueId("LoveCause") : null;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 18) {
        	for (int i = 0; i < 7; ++i) {
                double d0 = this.rand.nextGaussian() * 0.02D;
                double d1 = this.rand.nextGaussian() * 0.02D;
                double d2 = this.rand.nextGaussian() * 0.02D;
                this.world.spawnParticle(EnumParticleTypes.HEART, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2);
            }
        }
        else super.handleStatusUpdate(id);
    }
    
    
}