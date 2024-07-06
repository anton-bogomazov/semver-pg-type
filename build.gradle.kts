plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "2.0.0"
}

group = "com.abogomazov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.1")
    implementation("org.postgresql:postgresql:42.7.3")

    testImplementation("org.testcontainers:postgresql:1.19.8")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.1")
}

tasks.test {
    useJUnitPlatform()
}
