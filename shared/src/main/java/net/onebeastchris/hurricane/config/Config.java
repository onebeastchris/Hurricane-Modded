package net.onebeastchris.hurricane.config;

import net.onebeastchris.hurricane.util.PlatformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;

public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger("Hurricane");
    private boolean suppressWarnings;
    private boolean itemSteerableFix;
    private boolean bamboo;
    private boolean pointedDripstone;

    public static HurricaneConfiguration config;

    public Config() {

        if (PlatformUtil.oldConfigPath().toFile().exists()) {
            LOGGER.warn("""
                    Old Hurricane config found (geyserhacks.conf).
                    This is no longer used - please migrate to the new config (hurricane.conf)!
                    """);
        }

        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .path(PlatformUtil.configPath())
                .defaultOptions(opts -> opts.header("Hurricane Configuration "))
                .prettyPrinting(true)
                .build();

        try {
            final CommentedConfigurationNode node = loader.load();
            config = node.get(HurricaneConfiguration.class);
            loader.save(node);
        } catch (ConfigurateException e) {
            LOGGER.warn("Could not load config!");
            e.printStackTrace();
            return;
        }

        this.itemSteerableFix = config.itemSteerableFix();
        this.bamboo = config.collisionFixes().bamboo();
        this.pointedDripstone = config.collisionFixes().pointedDripstone();
        this.suppressWarnings = config.suppressWarnings();
    }

    public boolean isItemSteerableFix() {
        return itemSteerableFix;
    }

    public boolean isBamboo() {
        return bamboo;
    }

    public boolean isPointedDripstone() {
        return pointedDripstone;
    }

    public boolean isSuppressWarnings() {
        return suppressWarnings;
    }
}