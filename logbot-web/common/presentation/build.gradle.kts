import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
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
        jsMain{
            dependencies{
            }
        }
        wasmJsMain {

        }
        wasmJsTest { }
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(projects.logbotWeb.common.core)
                implementation(projects.logbotWeb.common.domain)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.coroutines.core)
                implementation(libs.date.time)

            }
        }
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