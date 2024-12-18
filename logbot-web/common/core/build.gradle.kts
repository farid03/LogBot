import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl


plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.konfig)
}


kotlin {

    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    jvmToolchain(17)
    jvm()
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
                implementation(libs.ktor.client.js)

            }
        }
        jvmMain {
            dependencies {
                implementation(libs.ktor.client.okhttp)
            }
        }
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(projects.logbotWeb.common.data)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.ktor.core)
                implementation(libs.ktor.contentNegotiation)
                implementation(libs.ktor.json)
                implementation(libs.ktor.logging)
                implementation(libs.coroutines.core)
                implementation(libs.koin.core)
                implementation(libs.koin.annotations)

//                implementation(libs.koin.ktor)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.coroutines.test)
            }
        }
        wasmJsMain {
            dependencies {
                implementation(libs.coroutines.wasm)
            }

        }
        wasmJsTest {

        }
    }
}

buildkonfig {
    packageName = "com.vk.logbot.client.core.ktor"
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "SERVER_URL", extra["SERVER_URL"] as String)
        buildConfigField(FieldSpec.Type.INT, "SERVER_PORT", extra["SERVER_PORT"] as String)
        buildConfigField(FieldSpec.Type.STRING, "SERVER_SHEME", extra["SERVER_SHEME"] as String)
    }
}
