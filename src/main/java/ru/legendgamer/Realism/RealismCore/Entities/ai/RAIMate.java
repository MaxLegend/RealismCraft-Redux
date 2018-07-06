package ru.legendgamer.Realism.RealismCore.Entities.ai;

import java.util.List;
import java.util.Random;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import ru.legendgamer.Realism.RealismCore.Entities.REntityAnimal;

public class RAIMate extends EntityAIBase {
	
    private final REntityAnimal animal;
    private final Class <? extends REntityAnimal > mateClass;
    World world;
    private REntityAnimal targetMate;
    int spawnBabyDelay;
    double moveSpeed;

    public RAIMate(REntityAnimal animal, double speed) {
        this(animal, speed, animal.getClass());
    }

    public RAIMate(REntityAnimal animal, double moveSpeed, Class <? extends REntityAnimal > mateClass) {
        this.animal = animal;
        this.world = animal.world;
        this.mateClass = mateClass;
        this.moveSpeed = moveSpeed;
        setMutexBits(3);
    }
    
    @Override
    public boolean shouldExecute() {
        if (!animal.isInLove()) return false;
        else {
            targetMate = getNearbyMate();
            return targetMate != null;
        }
    }
    
    @Override
    public boolean shouldContinueExecuting() {
        return targetMate.isEntityAlive() && targetMate.isInLove() && spawnBabyDelay < 60;
    }
    
    @Override
    public void resetTask() {
        targetMate = null;
        spawnBabyDelay = 0;
    }
    
    @Override
    public void updateTask() {
        animal.getLookHelper().setLookPositionWithEntity(targetMate, 10.0F, (float)animal.getVerticalFaceSpeed());
        animal.getNavigator().tryMoveToEntityLiving(targetMate, moveSpeed);
        ++spawnBabyDelay;

        if (spawnBabyDelay >= 60 && animal.getDistanceSq(targetMate) < 9.0D) spawnBaby();

    }
    
    private REntityAnimal getNearbyMate() {
        List<REntityAnimal> list = world.<REntityAnimal>getEntitiesWithinAABB(mateClass, animal.getEntityBoundingBox().grow(8.0D));
        double d0 = Double.MAX_VALUE;
        REntityAnimal entity = null;

        for (REntityAnimal entity2 : list) {
            if (this.animal.canMateWith(entity2) && animal.getDistanceSq(entity2) < d0) {
                entity = entity2;
                d0 = this.animal.getDistanceSq(entity2);
            }
        }

        return entity;
    }
    
    private void spawnBaby() {
        EntityAgeable entity = animal.createChild(targetMate);

        final BabyEntitySpawnEvent event = new BabyEntitySpawnEvent(animal, targetMate, entity);
        final boolean cancelled = MinecraftForge.EVENT_BUS.post(event);
        entity = event.getChild();
        if (cancelled) {
            animal.setGrowingAge(6000);
            targetMate.setGrowingAge(6000);
            animal.resetInLove();
            targetMate.resetInLove();
            return;
        }

        if (entity != null) {
            EntityPlayerMP playerMP = animal.getLoveCause();

            if (playerMP == null && targetMate.getLoveCause() != null) {
                playerMP = targetMate.getLoveCause();
            }

            if (playerMP != null) {
                playerMP.addStat(StatList.ANIMALS_BRED);
		  //На будущее: Здесь я сломал достижения майнкрафта. \0/ Когда будет создана новыя система достижений, создам новый 
		  //тригер.
//                CriteriaTriggers.BRED_ANIMALS.trigger(playerMP, animal, targetMate, entity);
            }

            animal.setGrowingAge(6000);
            targetMate.setGrowingAge(6000);
            animal.resetInLove();
            targetMate.resetInLove();
            entity.setGrowingAge(-24000);
            entity.setLocationAndAngles(animal.posX, animal.posY, animal.posZ, 0.0F, 0.0F);
            REntityAnimal entity2 = (REntityAnimal)entity;
	    //Вот здесь нужно добавить принадлежность к полу... Я забыл:) И ещё что-то нужно, но это тоже я забыл:)
            entity2.setType(world.rand.nextBoolean() ? animal.getType() : targetMate.getType());
            world.spawnEntity(entity2);
            Random random = animal.getRNG();

            for (int i = 0; i < 7; ++i) {
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                double d3 = random.nextDouble() * (double)animal.width * 2.0D - (double)animal.width;
                double d4 = 0.5D + random.nextDouble() * (double)animal.height;
                double d5 = random.nextDouble() * (double)animal.width * 2.0D - (double)animal.width;
                this.world.spawnParticle(EnumParticleTypes.HEART, animal.posX + d3, animal.posY + d4, animal.posZ + d5, d0, d1, d2);
            }

            if (world.getGameRules().getBoolean("doMobLoot")) {
                world.spawnEntity(new EntityXPOrb(world, animal.posX, animal.posY, animal.posZ, random.nextInt(7) + 1));
            }
        }
    }
}
