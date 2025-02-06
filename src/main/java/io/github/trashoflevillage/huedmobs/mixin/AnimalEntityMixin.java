package io.github.trashoflevillage.huedmobs.mixin;

import io.github.trashoflevillage.huedmobs.access.LivingEntityMixinAccess;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin {
    @Inject(method = "breed(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/AnimalEntity;Lnet/minecraft/entity/passive/PassiveEntity;)V", at = @At("TAIL"))
    public void breed(ServerWorld world, AnimalEntity other, PassiveEntity baby, CallbackInfo ci) {
        LivingEntityMixinAccess babyAccess = (LivingEntityMixinAccess)baby;
        ((LivingEntityMixinAccess) baby).setColorShift(
                new Vector3f(

                )
        );
    }
}
