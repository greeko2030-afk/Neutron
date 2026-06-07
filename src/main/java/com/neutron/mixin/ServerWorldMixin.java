package com.neutron.mixin;

import com.neutron.config.NeutronConfig;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {

    @Inject(method = "tickEntity", at = @At("HEAD"), cancellable = true)
    private void optimizeEntityTick(Entity entity, CallbackInfo ci) {
        if (!NeutronConfig.enableSmartTicking) return;

        // Player entities must update every tick
        if (entity instanceof ServerPlayerEntity) return;

        // Smart Ticking Profile: Skip ticks for distant non-player entities
        int checkInterval = 4; // Ticks only once every 4 frames if far away
        if (entity.age % checkInterval != 0) {
            ServerWorld world = (ServerWorld) (Object) this;
            boolean playerNearby = world.isPlayerInRange(entity.getX(), entity.getY(), entity.getZ(), 48.0);
            
            if (!playerNearby) {
                ci.cancel(); // Drop the tick execution to save CPU cycles
            }
        }
    }
}
