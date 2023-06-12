package net.onebeastchris.mixin;

import net.onebeastchris.util.Config;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinConfigPlugin implements IMixinConfigPlugin {

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
            case "net.onebeastchris.mixin.EntityMixin" -> config.isItemSteerableFix();
            case "net.onebeastchris.mixin.BambooBlockMixin" -> config.isBamboo();
            case "net.onebeastchris.mixin.PointedDripstoneBlockMixin" -> config.isPointedDripstone();
            case "net.onebeastchris.mixin.ServerPlayerInteractionManagerMixin" -> config.isSuppressWarnings();
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

    public static Config getConfig() {
        return config;
    }
}