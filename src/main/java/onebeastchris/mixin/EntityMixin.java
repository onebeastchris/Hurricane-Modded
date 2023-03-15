package onebeastchris.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import onebeastchris.util.PlatformUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow public abstract @Nullable LivingEntity getControllingPassenger();

    @Inject(method = "isLogicalSideForUpdatingMovement", at = @At("RETURN"), cancellable = true)
    private void isLogicalSideForUpdatingMovement(CallbackInfoReturnable<Boolean> cir) {
        if (this.getControllingPassenger() instanceof PlayerEntity player) {
            if (PlatformUtils.isBedrockPlayer(player.getUuid())) {
                    cir.setReturnValue(true);
            }
        }
    }
}