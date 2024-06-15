package net.onebeastchris.hurricane.util;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.nio.file.Path;

public class PlatformUtil {

    @ExpectPlatform
    public static Path path() {
        throw new IllegalArgumentException();
    }

    public static Path oldConfigPath() {
        return path().resolve("geyserhacks.conf");
    }

    public static Path configPath() {
        return path().resolve("hurricane.conf");
    }
}
