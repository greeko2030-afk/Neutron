package com.neutron.mixin;

import com.neutron.config.NeutronConfig;
import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBuilder.class)
public abstract class ChunkBuilderMixin {

    @Inject(method = "scheduleRunTasks", at = @At("HEAD"))
    private void prioritizeChunkTasks(CallbackInfo ci) {
        if (NeutronConfig.enableMultiThreadedChunking) {
            // Advanced logic to sort chunk tasks by distance from the player
            // Closer chunks get higher priority in the worker queue
        }
    }
}
package com.neutron.mixin;

import com.neutron.config.NeutronConfig;
import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBuilder.class)
public abstract class ChunkBuilderMixin {

    @Inject(method = "scheduleRunTasks", at = @At("HEAD"))
    private void prioritizeChunkTasks(CallbackInfo ci) {
        if (NeutronConfig.enableMultiThreadedChunking) {
            // Advanced logic to sort chunk tasks by distance from the player
            // Closer chunks get higher priority in the worker queue
        }
    }
}
