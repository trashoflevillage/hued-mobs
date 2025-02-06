package io.github.trashoflevillage.huedmobs.mixin;

import io.github.trashoflevillage.huedmobs.access.LivingEntityMixinAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements LivingEntityMixinAccess {
    @Unique
    private static final TrackedData<Vector3f> COLOR_TRACKER = DataTracker.registerData(LivingEntityMixin.class, TrackedDataHandlerRegistry.VECTOR3F);
    @Unique
    private static final String COLOR_SHIFT_KEY = "ColorShift";


    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "initDataTracker", at = @At("TAIL"))
    public void initDataTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(COLOR_TRACKER, getRandomColorVector());
    }

    @Unique
    public Vector3f getRandomColorVector() {
        int randColorRange = 150;
        return new Vector3f(
                (int)(Math.random() * randColorRange) + (255 - randColorRange),
                (int)(Math.random() * randColorRange) + (255 - randColorRange),
                (int)(Math.random() * randColorRange) + (255 - randColorRange)
        );
    }

    @Unique
    public Vector3f getColorShift() {
        return dataTracker.get(COLOR_TRACKER);
    }

    @Unique
    public void setColorShift(Vector3f val) {
        dataTracker.set(COLOR_TRACKER, val);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        int r = (int)(getColorShift().x);
        int g = (int)(getColorShift().y);
        int b = (int)(getColorShift().z);
        nbt.putIntArray(COLOR_SHIFT_KEY, new int[] { r, g, b });
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    public void readCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        Vector3f newColorShift = new Vector3f();
        int[] colorsToSet = nbt.getIntArray(COLOR_SHIFT_KEY);
        if (colorsToSet != null && colorsToSet.length != 0) {
            newColorShift.x = colorsToSet[0];
            newColorShift.y = colorsToSet[1];
            newColorShift.z = colorsToSet[2];
        } else {
            newColorShift = getRandomColorVector();
        }

        setColorShift(newColorShift);
    }
}
