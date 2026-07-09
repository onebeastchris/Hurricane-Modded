plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()

    // Architectury Plugin and Architectury Loom
    maven("https://maven.architectury.dev/")

    // Fabric
    maven("https://maven.fabricmc.net/")

    // NeoForge
    maven("https://maven.neoforged.net/releases/")
}

dependencies {
    // This is okay as long as the same version catalog is used in the main build and build-logic.
    // See: https://github.com/gradle/gradle/issues/15383#issuecomment-779893192
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.shadow)
    implementation(libs.architectury.plugin)
    implementation(libs.architectury.loom)
    implementation(libs.minotaur)
}