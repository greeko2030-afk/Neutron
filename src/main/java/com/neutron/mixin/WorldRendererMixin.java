package com.neutron.mixin;

import com.neutron.core.OptimizationEngine;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
    private long frameStart;

    @Inject(method = "render", at = @At("HEAD"))
    private void captureFrameStart(CallbackInfo ci) {
        this.frameStart = System.nanoTime();
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void evaluatePerformance(CallbackInfo ci) {
        long duration = System.nanoTime() - this.frameStart;
        OptimizationEngine.updateDynamicResolution(duration);
    }
}
