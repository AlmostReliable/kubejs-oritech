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
    maven("https://maven.latvian.dev/mirror")
    // Oritech
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven") // GeckoLib
    maven("https://maven.teamresourceful.com/repository/maven-public/") // Athena
}

dependencies {
    // KubeJS
    implementation("dev.latvian.mods:kubejs-neoforge:${almostgradle.getProperty("kjsVersion")}")?.let {
        interfaceInjectionData(it)
    }
    // Oritech
    implementation("rearth.oritech:oritech:${almostgradle.getProperty("oritechVersion")}-citest2")
}
