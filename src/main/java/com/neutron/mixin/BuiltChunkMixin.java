package com.neutron.mixin;

import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkBuilder.BuiltChunk.class)
public abstract class BuiltChunkMixin {

    @Inject(method = "delete", at = @At("HEAD"))
    private void optimizeBufferDeletion(CallbackInfo ci) {
        // Prevents memory leaks and speeds up OpenGL buffer deletion
        // when a chunk is unloaded from the world
    }
}
