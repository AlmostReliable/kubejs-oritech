plugins {
    id("net.neoforged.moddev") version "2.0.80"
    id("com.almostreliable.almostgradle") version "1.5.0"
}

almostgradle.setup {
    withSourcesJar = false
}

repositories {
    // KubeJS
    maven("https://maven.latvian.dev/releases")
    maven("https://jitpack.io") { // Animated Gif Library
        content {
            includeGroup("com.github.rtyley")
        }
    }

    // Oritech
    maven("https://modmaven.dev")
    maven("https://maven.architectury.dev") // Architectury
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven") // GeckoLib
    maven("https://maven.wispforest.io") // WeebLib
    maven("https://maven.su5ed.dev/releases") // Forgified Fabric API
    maven("https://beta.cursemaven.com") // Jade
    maven("https://maven.teamresourceful.com/repository/maven-public/") // Athena
}

dependencies {
    // KubeJS
    implementation("dev.latvian.mods:kubejs-neoforge:${almostgradle.getProperty("kjsVersion")}")
    // Oritech
    implementation("rearth.oritech:oritech-neoforge-${almostgradle.minecraftVersion}:${almostgradle.getProperty("oritechVersion")}")
}
