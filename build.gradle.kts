plugins {
    id("net.neoforged.moddev") version "2.0.143"
    id("com.almostreliable.almostgradle") version "2.3.1"
}

almostgradle.setup {
    withSourcesJar = false
    downloadSources = true
    downloadJavadoc = true
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
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven") // GeckoLib
    maven("https://beta.cursemaven.com") // Jade
    maven("https://maven.teamresourceful.com/repository/maven-public/") // Athena
}

dependencies {
    // KubeJS
    implementation("dev.latvian.mods:kubejs-neoforge:${almostgradle.getProperty("kjsVersion")}")?.let {
        interfaceInjectionData(it)
    }
    // Oritech
    // implementation("rearth.oritech:oritech-neoforge-${almostgradle.minecraftVersion}:${almostgradle.getProperty("oritechVersion")}")
    // temporarily pull Oritech from CurseMaven until new version is available on official Maven
    implementation("curse.maven:oritech-1030830:8571095")
}
