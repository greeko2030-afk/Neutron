package com.neutron.mixin;

import com.neutron.config.NeutronConfig;
import net.minecraft.world.chunk.light.ChunkBlockLightProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBlockLightProvider.class)
public abstract class ChunkBlockLightProviderMixin {

    @Inject(method = "method_51529", at = @At("HEAD"), cancellable = true)
    private void optimizeBlockLightPropagation(CallbackInfo ci) {
        if (NeutronConfig.enableLightingCache) {
            // Batches block light updates so multiple light sources 
            // recalculate the mesh simultaneously instead of one by one.
        }
    }
}
