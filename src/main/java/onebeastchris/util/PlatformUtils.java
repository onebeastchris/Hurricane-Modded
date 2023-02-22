package onebeastchris.util;

import net.fabricmc.loader.api.FabricLoader;
import org.geysermc.floodgate.api.FloodgateApi;

import java.util.UUID;

public class PlatformUtils {

    public static boolean isGeyserOrFloodgateInstalled() {
            try {
                Class.forName("org.geysermc.floodgate.api.FloodgateApi");
                return true;
            } catch (ClassNotFoundException e) {
                return false;
            }
        }

    public static boolean isBedrockPlayer(UUID uuid){
        if (isGeyserOrFloodgateInstalled()) {
            return FloodgateApi.getInstance().isFloodgatePlayer(uuid);
        } else {
            return false;
        }
    }
}