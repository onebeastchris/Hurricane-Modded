package onebeastchris.util;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import onebeastchris.command.showhomes;

public class ModCommandRegister {

    public static void registerCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> showhomes.register(dispatcher));
    }
}