package onebeastchris;

import net.fabricmc.api.ModInitializer;

import onebeastchris.util.ModCommandRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class hereAmI implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("hereAmI");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Loading MODNAME! thanks konica for taking a look :) ignore jank, tis is an empty shell for something else");
		ModCommandRegister.registerCommand();
	}
}