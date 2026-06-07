package com.neutron.mixin;

import com.neutron.config.NeutronConfig;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public abstract class EntityRenderDispatcherMixin {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void cullingInterceptor(E entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!NeutronConfig.enableEntityCulling) return;

        // Custom Visibility Cache Rule: Cull rendering if the entity is extremely far
        double squaredDistance = x * x + y * y + z * z;
        if (squaredDistance > 4096.0) { // Beyond 64 blocks away
            ci.cancel(); // Stop entity geometry upload to GPU
        }
    }
}
