repositories {
    // mavenLocal()
    mavenCentral()

    // Fabric
    maven("https://maven.fabricmc.net/")

    // NeoForge
    maven("https://maven.neoforged.net/releases")

    // Geyser and Floodgate
    maven("https://repo.opencollab.dev/main/")

    // JitPack
    maven("https://jitpack.io") {
        content {
            includeGroupByRegex("com.github.*")
        }
    }
}