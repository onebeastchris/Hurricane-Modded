import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("hurricane.base-conventions")
    id("com.github.johnrengelman.shadow")
}

tasks {
    named<Jar>("jar") {
        archiveClassifier.set("unshaded")
        from(project.rootProject.file("LICENSE"))
    }
    val shadowJar = named<ShadowJar>("shadowJar") {
        archiveBaseName.set(project.name)
        archiveVersion.set("")
        archiveClassifier.set("shadowed")

        val sJar: ShadowJar = this

        doFirst {
            sJar.dependencies {
                exclude(dependency("org.checkerframework:checker-qual:.*"))
                exclude(dependency("org.jetbrains:annotations:.*"))
            }
        }
    }
    named("build") {
        dependsOn(shadowJar)
    }
}