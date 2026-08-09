plugins {
    id("net.neoforged.moddev") version "2.0.143"
    id("com.almostreliable.almostgradle") version "2.0.0"
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
    // temporarily pull Oritech jar from cursemaven until modmaven adds it
    implementation("curse.maven:oritech-1030830:8571095")
}
