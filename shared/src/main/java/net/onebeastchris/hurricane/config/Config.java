package net.onebeastchris.hurricane.config;

import lombok.Getter;
import net.onebeastchris.hurricane.util.PlatformUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.NodePath;
import org.spongepowered.configurate.hocon.HoconConfigurationLoader;
import org.spongepowered.configurate.transformation.ConfigurationTransformation;
import org.spongepowered.configurate.transformation.TransformAction;

@Getter
public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger("Hurricane");
    private boolean suppressWarnings;
    private boolean bamboo;
    private boolean pointedDripstone;

    public static boolean shouldWarn = false;

    public static HurricaneConfiguration config;

    private final ConfigurationTransformation.Versioned transformer = ConfigurationTransformation.versionedBuilder()
            .addVersion(1, zeroToOne())
            .build();

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
            final CommentedConfigurationNode rootNode = loader.load();
            config = rootNode.get(HurricaneConfiguration.class);

            // Attempt to upgrade our config here if we don't have a version set
            var versionNode = rootNode.node("version");
            if (versionNode.virtual()) {
                shouldWarn = true;
                versionNode.set(0); // Add a version config entry, set it to 1
                versionNode.comment("The version of the config. DO NOT CHANGE!");

                // Remove item steerable workaround
                transformer.apply(rootNode);
            }

            rootNode.set(HurricaneConfiguration.class, config);
            loader.save(rootNode);
        } catch (ConfigurateException e) {
            LOGGER.warn("Could not load config!");
            e.printStackTrace();
            return;
        }

        this.bamboo = config.collisionFixes().bamboo();
        this.pointedDripstone = config.collisionFixes().pointedDripstone();
        this.suppressWarnings = config.suppressWarnings();
    }

    private ConfigurationTransformation zeroToOne() {
        return ConfigurationTransformation.builder()
                .addAction(NodePath.path("item-steerable-fix"), TransformAction.remove())
                .build();
    }
}