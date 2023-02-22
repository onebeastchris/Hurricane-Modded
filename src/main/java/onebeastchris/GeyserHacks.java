package onebeastchris;

import net.fabricmc.api.ModInitializer;

import onebeastchris.util.BlockPlaceEvent;
import onebeastchris.util.Config;
import onebeastchris.util.PlatformUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeyserHacks implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("GeyserHacks");

	Config config = new Config();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Loading GeyserHacks-Fabric");
		if (config.isItemSteerableFix()) {
			if (PlatformUtils.isGeyserOrFloodgateInstalled()){
				LOGGER.info("ItemSteerableFix enabled.");
			} else {
				LOGGER.info("ItemSteerableFix is enabled, but Geyser or Floodgate is not installed. The ItemSteerable fix will not work.");
			}
		}
		if (config.isBamboo() || config.isPointedDripstone()) {
			BlockPlaceEvent.register();
			LOGGER.info("BlockPlaceEvent registered, as Bamboo or PointedDripstone is enabled.");
		}
	}
}