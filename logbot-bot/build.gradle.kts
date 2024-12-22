plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "2.0.21"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
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

    // rest client
    implementation(project(":logbot-server-rest-client"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.kafka:spring-kafka")

    // database
    implementation("org.liquibase:liquibase-core")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("com.h2database:h2")

    // cache
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.8")

    // telegram
    implementation("org.telegram:telegrambots-spring-boot-starter:6.9.7.1")

    // logging
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")

    // kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))

    // json
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${versions.springdoc.get()}")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-assertions-core:${versions.kotest.get()}")
}

tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}
