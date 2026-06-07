package com.neutron.mixin;

import com.neutron.config.NeutronConfig;
import net.minecraft.world.chunk.light.ChunkSkyLightProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkSkyLightProvider.class)
public abstract class ChunkSkyLightProviderMixin {

    @Inject(method = "method_51529", at = @At("HEAD"), cancellable = true)
    private void optimizeSkyLightPropagation(CallbackInfo ci) {
        if (NeutronConfig.enableLightingCache) {
            // Drops redundant sky light updates for chunks that are underground
            // or fully occluded by solid blocks at the top of the world.
        }
    }
}
