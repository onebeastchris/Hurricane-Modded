package net.onebeastchris.hurricane;

import net.onebeastchris.hurricane.mixin.MixinConfigPlugin;
import net.onebeastchris.hurricane.config.Config;
import net.onebeastchris.hurricane.util.BedrockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Hurricane {
	public static final Logger LOGGER = LoggerFactory.getLogger("Hurricane");
	private final Config config = MixinConfigPlugin.getConfig();

	protected void onHurricaneInitialize() {
		LOGGER.info("Starting Hurricane...");
		if (config.isItemSteerableFix()) {
			if (!BedrockUtils.isGeyserOrFloodgateInstalled()) {
				LOGGER.warn("Hurricane's fix for item steerable mobs is enabled, but Geyser or Floodgate is not installed! This will not work.");
			}
		}

		if (config.isBamboo() || config.isPointedDripstone()) {
			registerBlockPlaceEvent();
			LOGGER.debug("BlockPlaceEvent registered, as the Bamboo or PointedDripstone fix is enabled.");
		}
		LOGGER.info("Started Hurricane!");
	}

	public abstract void registerBlockPlaceEvent();
}