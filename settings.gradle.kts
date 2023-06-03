pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://maven.architectury.dev/")
        maven("https://maven.fabricmc.net")
        maven("https://maven.minecraftforge.net/")
        maven("https://maven.ilarea.ru/snapshots")
        maven("https://jitpack.io")
    }
    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "net.minecraftforge.gradle.forge" -> useModule("com.github.asbyth:ForgeGradle:${requested.version}")
                "io.github.juuxel.loom-quiltflower-mini" -> useModule("com.github.Cephetir:loom-quiltflower-mini:${requested.version}")
            }
        }
    }
}

rootProject.name = "AuroraClient"