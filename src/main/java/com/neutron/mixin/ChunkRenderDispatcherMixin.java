package com.neutron.mixin;

import com.neutron.config.NeutronConfig;
import com.neutron.core.OptimizationEngine;
import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.concurrent.Executor;

@Mixin(ChunkBuilder.class)
public abstract class ChunkRenderDispatcherMixin {

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Ljava/util/concurrent/Executors;newFixedThreadPool(I)Ljava/util/concurrent/ExecutorService;"))
    private java.util.concurrent.ExecutorService overrideThreadPool(int nThreads) {
        if (NeutronConfig.enableMultiThreadedChunking) {
            // Hand over execution schedules to Neutron's core background thread engine
            return OptimizationEngine.getChunkWorkerPool();
        }
        return java.util.concurrent.Executors.newFixedThreadPool(nThreads);
    }
}
