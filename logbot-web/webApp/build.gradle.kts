import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.konfig)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "logbot-web"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "logbotWeb.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                    port = 8082
                }
            }
        }
        binaries.executable()
    }
    js {
        browser()
    }
    sourceSets {
        jsMain {
            dependencies {
                implementation(compose.html.core)
            }
        }
        wasmJsMain {
            dependencies {
                implementation(libs.coroutines.wasm)
            }

        }
        commonMain {
            kotlin.srcDir("build/generated/ksp/metadata")
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.decompose)
                implementation(libs.decompose.compose)
                implementation(libs.koin.core)
                implementation(libs.koin.annotations)
                api(libs.koin.annotations)
                implementation(libs.koin.compose)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.coroutines.core)
            }
        }
    }
}
dependencies {
    commonMainImplementation(projects.logbotWeb.common.core)
    commonMainImplementation(projects.logbotWeb.common.data)
    commonMainImplementation(projects.logbotWeb.common.domain)
    commonMainImplementation(projects.logbotWeb.common.presentation)
    commonMainImplementation(projects.logbotWeb.common.compose)
}

tasks.register("cleanWebApp") {
    doLast {
        delete(rootProject.layout.projectDirectory.dir("docs").asFile)
    }
}

tasks.register<Copy>("copyWebApp") {
    mustRunAfter("wasmJsBrowserDistributionWrapper")

    from("build/dist/wasmJs/productionExecutable") {
        include("**/*") // Копируем все файлы и папки
    }
    into(rootProject.layout.projectDirectory.dir("docs"))
}

tasks.register("wasmJsBrowserDistributionWrapper") {
    mustRunAfter("cleanWebApp")
    dependsOn("wasmJsBrowserDistribution")
}

tasks.register("buildAndDeployWebApp") {
    dependsOn("wasmJsBrowserDistributionWrapper", "cleanWebApp", "copyWebApp")
}
dependencies {
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
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

buildkonfig {
    packageName = "com.vk.logbot.web"
    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.BOOLEAN,
            "SUPPORT_BROWSER_WITHOUT_TELEGRAM",
            extra["SUPPORT_BROWSER_WITHOUT_TELEGRAM"] as String
        )
    }
}

