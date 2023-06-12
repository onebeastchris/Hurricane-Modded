package net.onebeastchris.util;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;


public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger("GeyserHacks");
    private boolean suppressWarnings;
    private boolean itemSteerableFix;
    private boolean bamboo;
    private boolean pointedDripstone;

    public static HurricaneConfiguration config;

    public Config() {
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .path(FabricLoader.getInstance().getConfigDir().resolve("geyserhacks.conf"))
                .defaultOptions(opts -> opts.header("Geyser "))
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