package onebeastchris.util;


import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.util.ActionResult;

public class BlockPlaceEvent {
    public static void register() {
       registerInteractBlockCallback();
    }
    private static void registerInteractBlockCallback() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            if (playerEntity.getStackInHand(hand).getItem().equals(Blocks.BAMBOO.asItem())) {
                if (blockHitResult.getBlockPos().equals(playerEntity.getBlockPos())) {
                    return ActionResult.FAIL;
                }
            }
            if (playerEntity.getStackInHand(hand).getItem().equals(Blocks.POINTED_DRIPSTONE.asItem())) {
                if (blockHitResult.getBlockPos().equals(playerEntity.getBlockPos().add(0, -1, 0))) {
                    //you can place dripstone in yourself
                    return ActionResult.FAIL;
                }
            }
            return ActionResult.PASS;
        });
    }
}