package net.onebeastchris.hurricane.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ItemSteerable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.onebeastchris.hurricane.util.BedrockUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract @Nullable LivingEntity getControllingPassenger();

    @Inject(method = "isControlledByLocalInstance", at = @At("RETURN"), cancellable = true)
    private void isLogicalSideForUpdatingMovement(CallbackInfoReturnable<Boolean> cir) {
        if (this.getControllingPassenger() instanceof Player player && this instanceof ItemSteerable) {
            if (BedrockUtils.isBedrockPlayer(player.getUUID())) {
                cir.setReturnValue(true);
            }
        }
    }
}