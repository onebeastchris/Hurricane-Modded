package onebeastchris.mixin;

import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Redirect(method = "processBlockBreakingAction",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=Mismatch in destroy block pos: {} {}", ordinal = 0)),
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", remap = false))
    private void GeyserHacks$onMismatchInDestroyBlockPos(org.slf4j.Logger logger, String format, Object arg1, Object arg2) {
        // do nothing
    }
}