import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
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
        jvmMain {

        }

        wasmJsMain {

        }
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(libs.kotlin.serialization.json)
            }
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

    }
}


