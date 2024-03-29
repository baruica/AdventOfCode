plugins {
    kotlin("jvm") version "1.9.23"
    id("io.kotest") version "0.4.10"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "me.baruica"
version = "1.0"
description = "Advent of Code"

repositories {
    mavenCentral()
}

val kotestVersion = "5.8.0"

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
