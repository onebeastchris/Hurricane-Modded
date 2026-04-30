architectury {
    platformSetupLoomIde()
    neoForge()
}

val common: Configuration by configurations.creating
// Without this, the mixin config isn't read properly with the runServer neoforge task
val developmentNeoForge: Configuration = configurations.getByName("developmentNeoForge")

configurations {
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    developmentNeoForge.extendsFrom(configurations["common"])
}

dependencies {
    neoForge(libs.neoforge)
    common(project(":shared")) { isTransitive = false }
    shadow(project(path = ":shared", configuration = "transformProductionNeoForge")) { isTransitive = false }

    include(libs.configurate.hocon)
    include(libs.configurate.core)
    include(libs.geantyref)
    include(libs.typesafe)
}

tasks {
    named<Jar>("mergeShadowAndJarJar") {
        from(
            zipTree(shadowJar.map { it.outputs.files.singleFile }),
            zipTree(jar.map { it.outputs.files.singleFile }).matching {
                include("META-INF/jars/**")
                include("META-INF/jarjar/**")
            }
        )
        archiveBaseName.set("Geyser-NeoForge")
    }
}

modrinth {
    loaders.add("neoforge")
}