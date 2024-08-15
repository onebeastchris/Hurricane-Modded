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
		if (config.isBamboo() && !BedrockUtils.isGeyserOrFloodgateInstalled()) {
			LOGGER.warn("Bamboo fix is enabled, but Geyser or Floodgate are not found! Without these mods, Hurricane cannot " +
					"fix the bamboo lag-back for Bedrock players.");
		}

		if (config.isBamboo() || config.isPointedDripstone()) {
			registerBlockPlaceEvent();
			LOGGER.debug("BlockPlaceEvent registered, as the Bamboo or PointedDripstone fix is enabled.");
		}

		LOGGER.info("Started Hurricane!");

		if (Config.shouldWarn) {
			LOGGER.warn("Removed the item steerable configuration option, as Geyser now supports it natively! " +
					"Please update Geyser if you have not done so already!");
		}
	}

	public abstract void registerBlockPlaceEvent();
}