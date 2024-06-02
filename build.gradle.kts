plugins {
    kotlin("jvm") version "2.0.0"
    id("io.kotest") version "0.4.10"
    id("com.autonomousapps.dependency-analysis") version "1.32.0"
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

dependencies {
    testImplementation("io.kotest:kotest-assertions-shared:5.8.0")
    testImplementation("io.kotest:kotest-framework-api:5.8.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
