package onebeastchris.util;

import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger("GeyserHacks");
    private boolean itemSteerableFix;
    private boolean bamboo;
    private boolean pointedDripstone;

    private static GeyserHacksConfiguration config;


    public Config() {
        final HoconConfigurationLoader loader = HoconConfigurationLoader.builder()
                .path(FabricLoader.getInstance().getConfigDir().resolve("geyserhacks.conf"))
                .defaultOptions(opts -> opts.header("Geyser "))
                .build();

        GeyserHacksConfiguration config;

        try {
            final CommentedConfigurationNode node = loader.load();
            config = node.get(GeyserHacksConfiguration.class);
            loader.save(node);
        } catch (ConfigurateException e) {
            LOGGER.warn("Could not load config!");
            e.printStackTrace();
            return;
        }

        this.itemSteerableFix = Objects.requireNonNull(config).itemSteerableFix();
        this.bamboo = GeyserHacksConfiguration.CollisionFixes.bamboo();
        this.pointedDripstone = GeyserHacksConfiguration.CollisionFixes.pointedDripstone();
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
}