package net.onebeastchris.hurricane.platform.fabric;

import net.fabricmc.api.ModInitializer;
import net.onebeastchris.hurricane.Hurricane;

public class FabricHurricaneMod extends Hurricane implements ModInitializer {

	@Override
	public void onInitialize() {
		super.onHurricaneInitialize();
	}

	@Override
	public void registerBlockPlaceEvent() {
		FabricBlockPlaceEvent event = new FabricBlockPlaceEvent();
		event.registerInteractBlockCallback();
	}
}