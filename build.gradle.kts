import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
}

group = "net.bestlinuxgamers"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.varoplugin.de/repository/maven-public/")
}

dependencies {
    implementation("de.varoplugin:VaroPlugin:4.11.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}
