plugins {
    java
    id("hurricane.build-logic")
    alias(libs.plugins.lombok) apply false
}

subprojects {
    apply {
        plugin("java-library")
        plugin("io.freefair.lombok")
        plugin("hurricane.build-logic")
        plugin("hurricane.platform-conventions")
    }
}