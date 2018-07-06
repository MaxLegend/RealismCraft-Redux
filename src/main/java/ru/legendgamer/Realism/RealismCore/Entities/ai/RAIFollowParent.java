package ru.legendgamer.Realism.RealismCore.Entities.ai;

import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityAnimal;
import ru.legendgamer.Realism.RealismCore.Entities.REntityAnimal;

public class RAIFollowParent extends EntityAIBase { 
    REntityAnimal childAnimal;
    REntityAnimal parentAnimal;
    double moveSpeed;
    private int delayCounter;

    public RAIFollowParent(REntityAnimal animal, double speed) {
        childAnimal = animal;
        moveSpeed = speed;
    } 
    
    @Override
    public boolean shouldExecute() {
        if (this.childAnimal.getGrowingAge() >= 0) return false;
        else {
            List<REntityAnimal> list = childAnimal.world.<REntityAnimal>getEntitiesWithinAABB(childAnimal.getClass(), childAnimal.getEntityBoundingBox().grow(8.0D, 4.0D, 8.0D));
            REntityAnimal entity = null;
            double d0 = Double.MAX_VALUE;

            for (REntityAnimal entity2 : list) {
                if (entity2.getGrowingAge() >= 0) {
                    double d1 = childAnimal.getDistanceSq(entity2);

                    if (d1 <= d0) {
                        d0 = d1;
                        entity = entity2;
                    }
                }
            }

            if (entity == null) return false;
            else if (d0 < 9.0D) return false;
            else {
                parentAnimal = entity;
                return true;
            }
        }
    }
    
    @Override
    public boolean shouldContinueExecuting() {
        if (childAnimal.getGrowingAge() >= 0) return false;
        else if (!parentAnimal.isEntityAlive()) return false;
        else {
            double d0 = childAnimal.getDistanceSq(parentAnimal);
            return d0 >= 9.0D && d0 <= 256.0D;
        }
    }
    
    @Override
    public void startExecuting() {delayCounter = 0;}
    
    @Override
    public void resetTask() {parentAnimal = null;}
    
    @Override
    public void updateTask() {
        if (--delayCounter <= 0) {
            delayCounter = 10;
            childAnimal.getNavigator().tryMoveToEntityLiving(parentAnimal, moveSpeed);
        }
    }
}
