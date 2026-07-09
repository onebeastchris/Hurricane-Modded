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
                "description" to "An unofficial Fabric/NeoForge port of GeyserMC's Hurricane plugin, formerly known as Camotoy's GeyserHacks.",
                "url" to "https://github.com/onebeastchris/Hurricane-Modded",
                "author" to "onebeastchris",
                "minecraft_version" to "26.2"
            )
        }
    }
}