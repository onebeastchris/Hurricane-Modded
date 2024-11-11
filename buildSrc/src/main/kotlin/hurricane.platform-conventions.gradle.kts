import gradle.kotlin.dsl.accessors._e054d9723d982fdb55b1e388b8ab0cbf.build
import net.fabricmc.loom.task.RemapJarTask

plugins {
    id("hurricane.shadow-conventions")
    id("architectury-plugin")
    id("dev.architectury.loom")
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
    mappings(loom.officialMojangMappings())
}

tasks {
    shadowJar {
        // Mirrors the example fabric project, otherwise tons of dependencies are shaded that shouldn't be
        configurations = listOf(project.configurations.shadow.get())

        // The remapped shadowJar is the final desired mod jar
        archiveVersion.set("")
    }

    remapJar {
        dependsOn(shadowJar)
        inputFile.set(shadowJar.get().archiveFile)
        archiveClassifier.set("")
        archiveVersion.set(project.version.toString())
    }

    register("remapModrinthJar", RemapJarTask::class) {
        dependsOn(shadowJar)
        inputFile.set(shadowJar.get().archiveFile)
        archiveVersion.set(project.version.toString() + "+build."  + System.getenv("GITHUB_RUN_NUMBER"))
        archiveClassifier.set("")
    }

    build {
        dependsOn(remapJar)
    }
}

modrinth {
    token.set(System.getenv("MODRINTH_TOKEN")) // Even though this is the default value, apparently this prevents GitHub Actions caching the token?
    projectId.set("hurricane")
    versionNumber.set(project.version as String + "-" + System.getenv("GITHUB_RUN_NUMBER"))
    versionType.set("release")

    syncBodyFrom.set(rootProject.file("README.md").readText())
    changelog.set(rootProject.file("CHANGELOG.md").readText())

    uploadFile.set(tasks.getByPath("remapModrinthJar"))
    gameVersions.addAll("1.21.3")
    failSilently.set(false)
}