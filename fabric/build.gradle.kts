architectury {
    platformSetupLoomIde()
    fabric()
}

val common: Configuration by configurations.creating
val developmentFabric: Configuration = configurations.getByName("developmentFabric")

configurations {
    compileClasspath.get().extendsFrom(configurations["common"])
    runtimeClasspath.get().extendsFrom(configurations["common"])
    developmentFabric.extendsFrom(configurations["common"])
}

tasks {
    named<Jar>("mergeShadowAndJarJar") {
        from (
            zipTree( shadowJar.map { it.outputs.files.singleFile } ).matching {
                exclude("fabric.mod.json")
            },
            zipTree( jar.map { it.outputs.files.singleFile } ).matching {
                include("META-INF/jars/**")
                include("fabric.mod.json")
            }
        )
        archiveBaseName.set("Geyser-Fabric")
    }
}

dependencies {
    implementation(libs.fabric.loader)
    api(libs.fabric.api)
    common(project(":shared")) { isTransitive = false }
    shadow(project(path = ":shared", configuration = "transformProductionFabric")) {
        isTransitive = false
    }

    include(libs.configurate.hocon)
    include(libs.configurate.core)
    include(libs.geantyref)
    include(libs.typesafe)

    localRuntime(libs.configurate.hocon)
    localRuntime(libs.configurate.core)
    localRuntime(libs.geantyref)
    localRuntime(libs.typesafe)
}

modrinth {
    loaders.add("fabric")
}