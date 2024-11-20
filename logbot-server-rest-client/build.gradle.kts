plugins {
    kotlin("jvm") version "1.9.25"
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

dependencies {
    // commons
    implementation(project(":logbot-commons"))

    // spring
    implementation("org.springframework:spring-web:6.1.13")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}