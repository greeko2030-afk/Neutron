package com.neutron.mixin;

import com.neutron.config.NeutronConfig;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityNavigation.class)
public abstract class EntityNavigationMixin {

    @Inject(method = "recalculatePath", at = @At("HEAD"), cancellable = true)
    private void throttlePathfinding(CallbackInfo ci) {
        if (NeutronConfig.enableSmartTicking) {
            // Path recalculations are intensive. You can insert random offsets 
            // or step-counters here to lower execution frequency.
        }
    }
}
