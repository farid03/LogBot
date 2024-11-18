plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.vk.logbot"
version = "0.0.1"

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
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

    // telegram
    implementation("org.telegram:telegrambots-spring-boot-starter:6.9.7.1")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // json
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${versions.springdoc.get()}")

    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-assertions-core:${versions.kotest.get()}")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
