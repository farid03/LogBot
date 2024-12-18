import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("jvm") version "1.9.25" apply false
    kotlin("plugin.spring") version "1.9.25" apply false
    id("org.springframework.boot") version "3.3.5" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    kotlin("plugin.jpa") version "1.9.25" apply false


    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.kotlin.ksp).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.compose.compiler).apply(false)
    alias(libs.plugins.konfig).apply(false)
}
buildscript {
    dependencies {
        val konfigVersion = libs.versions.konfig.get()
        classpath("com.codingfeline.buildkonfig:buildkonfig-gradle-plugin:$konfigVersion")
    }
}
allprojects {
    val props = Properties().apply {
        load(FileInputStream("$rootDir/logbot-web/config.gradle"))
    }
    extra["SERVER_PATH"] = props.getProperty("SERVER_PATH")
    extra["SERVER_PORT"] = props.getProperty("SERVER_PORT")
    extra["SERVER_SHEME"] = props.getProperty("SERVER_SHEME")
    extra["SUPPORT_BROWSER_WITHOUT_TELEGRAM"] = props.getProperty("SUPPORT_BROWSER_WITHOUT_TELEGRAM")
}
tasks.register("clean ") {
    doLast {
        delete(rootProject.layout.buildDirectory)
    }
}
