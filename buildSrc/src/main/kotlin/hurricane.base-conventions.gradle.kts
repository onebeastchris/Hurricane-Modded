plugins {
    `java-library`
}

tasks {
    processResources {
        filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml")) {
            expand(
                "id" to "hurricane",
                "name" to "Hurricane",
                "version" to project.version,
                "description" to "Hacky fixes to make Bedrock players that join via Geyser happy. Fabric port of Hurricane by GeyserMC.",
                "url" to "https://geysermc.org",
                "author" to "onebeastchris",
                "minecraft_version" to "1.21.3"
            )
        }
    }
}