plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
    kotlin("plugin.jpa") version "2.0.0"
    `java-test-fixtures`
}

group = "com.abogomazov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

object Versions {
    val kotlin = "2.0.0"
    val spring = "3.3.1"
    val pg = "42.7.3"
    val pg_testcontainers = "1.19.8"
    val kotest = "5.9.1"
    val hypersistence = "3.8.1"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Versions.spring}")
    runtimeOnly("org.postgresql:postgresql:${Versions.pg}")
    implementation("io.hypersistence:hypersistence-utils-hibernate-63:${Versions.hypersistence}")

    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.spring}")
    testFixturesImplementation("org.springframework.boot:spring-boot-testcontainers:${Versions.spring}")
    testFixturesImplementation("org.testcontainers:postgresql:${Versions.pg_testcontainers}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${Versions.kotest}")
}

tasks.test {
    useJUnitPlatform()
}
