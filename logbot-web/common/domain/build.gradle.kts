import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.konfig)
}

kotlin {


    jvmToolchain(17)
    task("testClasses")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        nodejs()
    }
    js {
        browser()
    }
    sourceSets {
        jsMain {
            dependencies {
            }
        }
        wasmJsMain {
            dependencies {
                implementation(libs.coroutines.wasm)
            }
        }
        wasmJsTest { }
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(libs.ktor.core)
                implementation(projects.logbotWeb.common.core)
                implementation(projects.logbotWeb.common.data)
                implementation(libs.coroutines.core)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.koin.core)
                implementation(libs.koin.annotations)

            }
        }
        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

    }
}

buildkonfig {
    packageName = "com.vk.logbot.client.domain"
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "SERVER_PATH", extra["SERVER_PATH"] as String)
        buildConfigField(FieldSpec.Type.INT, "SERVER_PORT", extra["SERVER_PORT"] as String)
        buildConfigField(FieldSpec.Type.STRING, "SERVER_SHEME", extra["SERVER_SHEME"] as String)
    }
}




afterEvaluate {
    tasks.filter {
        it.name.contains("SourcesJar", true)
    }.forEach {
        println("SourceJarTask====>${it.name}")
        it.dependsOn("kspCommonMainKotlinMetadata")
    }
}