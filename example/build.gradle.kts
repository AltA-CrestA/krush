import pl.touk.krush.gradle.Versions

plugins {
    id("kotlin")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

dependencies {
    api("jakarta.persistence:jakarta.persistence-api:3.2.0-M1")

    api(project(":runtime"))
    api(project(":runtime-postgresql"))
    api(project(":annotation-processor"))
    kapt(project(":annotation-processor"))

    implementation("com.h2database:h2:2.1.210")
    implementation("org.postgresql:postgresql:${Versions.postgresDriver}")
    implementation("org.flywaydb:flyway-core:9.16.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")

    testImplementation("org.junit.jupiter:junit-jupiter:${Versions.junit}")
    testImplementation("org.testcontainers:testcontainers:${Versions.testContainers}")
    testImplementation("org.testcontainers:junit-jupiter:${Versions.testContainers}")
    testImplementation("org.testcontainers:postgresql:${Versions.testContainers}")
    testImplementation("org.assertj:assertj-core:${Versions.assertj}")
    testRuntimeOnly("ch.qos.logback:logback-classic:${Versions.logback}")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

