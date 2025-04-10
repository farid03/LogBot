plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.25"
}

group = "com.vk.logbot"
version = "0.0.1"

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

repositories {
    mavenCentral()
}

val versions = libs.versions

dependencies {
    // commons
    implementation(project(":logbot-commons"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // раскомментить когда появится БД
    implementation("org.flywaydb:flyway-core:9.22.3")
    runtimeOnly("org.postgresql:postgresql:42.7.4")
    runtimeOnly("com.h2database:h2")
    implementation("io.github.microutils:kotlin-logging:3.0.5")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("org.springframework:spring-messaging:6.2.0")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // json
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${versions.springdoc.get()}")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-assertions-core:${versions.kotest.get()}")
    testImplementation("io.mockk:mockk:1.13.16")
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
