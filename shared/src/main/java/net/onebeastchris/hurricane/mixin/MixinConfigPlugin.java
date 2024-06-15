package net.onebeastchris.hurricane.mixin;

import lombok.Getter;
import net.onebeastchris.hurricane.config.Config;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinConfigPlugin implements IMixinConfigPlugin {

    @Getter
    static final Config config = new Config();

    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return switch (mixinClassName) {
            case "net.onebeastchris.hurricane.mixin.EntityMixin" -> config.isItemSteerableFix();
            case "net.onebeastchris.hurricane.mixin.BambooBlockMixin" -> config.isBamboo();
            case "net.onebeastchris.hurricane.mixin.PointedDripstoneBlockMixin" -> config.isPointedDripstone();
            case "net.onebeastchris.hurricane.mixin.ServerPlayerInteractionManagerMixin" -> config.isSuppressWarnings();
            default -> true;
        };
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

}