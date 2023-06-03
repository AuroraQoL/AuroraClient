import dev.architectury.pack200.java.Pack200Adapter

plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("gg.essential.loom") version "0.10.0.+"
    id("io.github.juuxel.loom-quiltflower-mini") version "7d04f32023"
    id("dev.architectury.architectury-pack200") version "0.1.3"
    java
    idea
}

version = "3.1.0"
group = "me.aurora.client"

base {
    archivesName.set("AuroraClient")
}

loom {
    silentMojangMappingsLicense()
    launchConfigs {
        getByName("client") {
            arg("--tweakClass", "me.cephetir.bladecore.loader.BladeCoreTweaker")
        }
    }
    runConfigs {
        getByName("client") {
            isIdeConfigGenerated = true
        }
        remove(getByName("server"))
    }
    forge {
        pack200Provider.set(Pack200Adapter())
    }
}

val include: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

repositories {
    mavenCentral()
    maven("https://repo.sk1er.club/repository/maven-public/")
    maven("https://jitpack.io/")
    maven("https://repo.spongepowered.org/repository/maven-public/")
    maven("https://maven.ilarea.ru/releases")
}

dependencies {
    minecraft("com.mojang:minecraft:1.8.9")
    mappings("de.oceanlabs.mcp:mcp_stable:22-1.8.9")
    forge("net.minecraftforge:forge:1.8.9-11.15.1.2318-1.8.9")

    include("net.objecthunter:exp4j:0.4.8")

    include("me.cephetir:bladecore-loader-1.8.9-forge:1.2")
    implementation("me.cephetir:bladecore-1.8.9-forge:0.0.2-c")

    include("me.cephetir:communist-scanner:1.1.5")

    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    testCompileOnly("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
    test {
        java {
            srcDirs("src/test")
        }
    }
}

tasks {
    processResources {
        inputs.property("version", project.version)
        inputs.property("mcversion", "1.8.9")

        filesMatching("mcmod.info") {
            expand(mapOf("version" to project.version, "mcversion" to "1.8.9"))
        }
        dependsOn(compileJava)
    }
    jar {
        manifest {
            attributes(
                mapOf(
                    "ForceLoadAsMod" to true,
                    "ModSide" to "CLIENT",
                    "ModType" to "FML",
                    "TweakClass" to "me.cephetir.bladecore.loader.BladeCoreTweaker",
                    "TweakOrder" to "0"
                )
            )
        }
        dependsOn(shadowJar)
        enabled = false
    }
    remapJar {
        archiveFileName.set("bin.jar")
        input.set(shadowJar.get().archiveFile)
    }
    shadowJar {
        archiveClassifier.set("dev")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        configurations = listOf(include)

        exclude(
            "**/LICENSE.md",
            "**/LICENSE.txt",
            "**/LICENSE",
            "**/NOTICE",
            "**/NOTICE.txt",
            "pack.mcmeta",
            "dummyThing",
            "**/module-info.class",
            "META-INF/proguard/**",
            "META-INF/maven/**",
            "META-INF/versions/**",
            "META-INF/com.android.tools/**",
            "fabric.mod.json"
        )
        mergeServiceFiles()
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        sourceCompatibility = JavaVersion.VERSION_1_8.toString()
        targetCompatibility = JavaVersion.VERSION_1_8.toString()
    }
}

tasks.test {
    useJUnitPlatform()
}