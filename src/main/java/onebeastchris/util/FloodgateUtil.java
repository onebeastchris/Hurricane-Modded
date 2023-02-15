package onebeastchris.util;

import net.fabricmc.loader.api.FabricLoader;
import onebeastchris.hereAmI;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

public class FloodgateUtil {
        public static boolean isFloodgatePresent = FabricLoader.getInstance().isModLoaded("floodgate");

        public static boolean isFloodgatePlayer(UUID uuid) {
            if (!isFloodgatePresent) {
                return false;
            } else if (FloodgateApi.getInstance() == null) {
                hereAmI.LOGGER.warn("Floodgate seems to be installed, but MODNAME cannot access it. Please report this as an issue on the GitHub page!");
                return false;
            } else
                return FloodgateApi.getInstance().isFloodgatePlayer(uuid);
        }

        public static String FloodgatePrefix() {
            if (!isFloodgatePresent) {
                hereAmI.LOGGER.debug("Floodgate is not installed! Disabling Bedrock forms.");
                return ".";
            } else if (FloodgateApi.getInstance() == null) {
                hereAmI.LOGGER.error("Floodgate seems to be installed, but MODNAME cannot access it. Please report this as an issue on the GitHub page!");
                return ".";
            } else
                return FloodgateApi.getInstance().getPlayerPrefix();
        }

}
