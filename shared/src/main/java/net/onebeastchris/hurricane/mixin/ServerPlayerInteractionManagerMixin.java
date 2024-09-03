package net.onebeastchris.hurricane.mixin;

import net.minecraft.server.level.ServerPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(value = ServerPlayerGameMode.class)
public class ServerPlayerInteractionManagerMixin {
    @Redirect(method = "handleBlockBreakAction",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=Mismatch in destroy block pos: {} {}", ordinal = 0)),
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V"))
    private void GeyserHacks$onMismatchInDestroyBlockPos(org.slf4j.Logger logger, String format, Object arg1, Object arg2) {
        // do nothing
    }
}