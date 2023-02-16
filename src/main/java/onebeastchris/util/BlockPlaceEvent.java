package onebeastchris.util;


import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import static onebeastchris.GeyserHacks.LOGGER;

public class BlockPlaceEvent {

    private ItemStack collisionFixItems;
    public static void register() {
       registerInteractBlockCallback();
    }

    private static void registerInteractBlockCallback() {
        UseBlockCallback.EVENT.register((playerEntity, world, hand, blockHitResult) -> {
            if (playerEntity.getStackInHand(hand).getItem().equals(Blocks.BAMBOO.asItem())) {
                //todo: check if placed in themselves
                return ActionResult.PASS;
            }
            if (playerEntity.getStackInHand(hand).getItem().equals(Blocks.POINTED_DRIPSTONE.asItem())) {
                //todo: check if placed in themselves
                return ActionResult.PASS;
            }
            return ActionResult.PASS;
        });
    }
}