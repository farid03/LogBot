plugins {
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false

}
repositories {
    mavenCentral()
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
