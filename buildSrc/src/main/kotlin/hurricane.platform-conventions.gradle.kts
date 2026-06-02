plugins {
    id("hurricane.shadow-conventions")
    id("architectury-plugin")
    id("dev.architectury.loom-no-remap")
    id("com.modrinth.minotaur")
}

architectury {
    minecraft = libs.versions.minecraft.version.get()
}

java {
    withSourcesJar()
}

loom {
    silentMojangMappingsLicense()
}

dependencies {
    minecraft(libs.minecraft)
}

tasks {
    shadowJar {
        // Mirrors the example fabric project, otherwise tons of dependencies are shaded that shouldn't be
        configurations = listOf(project.configurations.getByName("shadow"))
        archiveBaseName.set("${project.name}-shaded")
        mergeServiceFiles()
    }

    // This task combines the output of the "jar" task, which includes JiJ dependencies,
    // and the shadowJar for the final jar.
    // thanks bluemap
    // https://github.com/BlueMap-Minecraft/BlueMap/blob/cfe73115dc4d1bdd97bc659f41364da65a6a2179/implementations/fabric/build.gradle.kts#L93-L107
    register<Jar>("mergeShadowAndJarJar") {
        dependsOn( tasks.shadowJar, tasks.jar )
        // from sources / final name are configured in the respective projects
        archiveVersion.set("")
        archiveClassifier.set("")
    }

    tasks.register<Copy>("renameModrinthJar") {
        val sourceJar = tasks.named<Jar>("mergeShadowAndJarJar")
        dependsOn(sourceJar)

        from(sourceJar.flatMap { it.archiveFile })
        into(layout.buildDirectory.dir("libs"))

        rename { "${(project)}.jar" }
    }

    build {
        dependsOn(tasks.getByName("mergeShadowAndJarJar"))
    }
}

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN")) // Even though this is the default value, apparently this prevents GitHub Actions caching the token?
    projectId.set("hurricane")
    versionNumber.set(project.version as String + "-" + System.getenv("GITHUB_RUN_NUMBER"))
    versionType.set("release")

    syncBodyFrom.set(rootProject.file("README.md").readText())
    changelog.set(rootProject.file("CHANGELOG.md").readText())

    uploadFile.set(tasks.getByPath("renameModrinthJar"))
    gameVersions.addAll("26.1")
    failSilently.set(false)
}