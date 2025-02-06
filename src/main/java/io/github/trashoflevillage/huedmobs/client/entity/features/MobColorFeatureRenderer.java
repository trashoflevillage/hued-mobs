package io.github.trashoflevillage.huedmobs.client.entity.features;

import io.github.trashoflevillage.huedmobs.access.LivingEntityMixinAccess;
import io.github.trashoflevillage.huedmobs.mixin.LivingEntityMixin;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.ColorHelper;
import org.joml.Vector3f;

public class MobColorFeatureRenderer<T extends LivingEntity> extends FeatureRenderer {
    public MobColorFeatureRenderer(FeatureRendererContext context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, Entity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity instanceof LivingEntity livingEntity) {
            Vector3f color = ((LivingEntityMixinAccess)livingEntity).getColorShift();
            this.getContextModel().render(matrices, vertexConsumers.getBuffer(
                    RenderLayer.getEntityTranslucent(getTexture(entity))), light, LivingEntityRenderer.getOverlay(livingEntity, 0),
                    ColorHelper.Argb.getArgb((int)color.x, (int)color.y, (int)color.z)
            );
        }
    }
}
