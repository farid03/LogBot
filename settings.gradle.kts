rootProject.name = "logbot"

include(
    "logbot-commons",
    "logbot-server-rest-client",
    "logbot-server",
    "logbot-bot",
    "logbot-log-generator",
    "logbot-web",
    "logbot-auth",
    "logbot-web",
    "logbot-web:webApp",
    "logbot-web:common:core",
    "logbot-web:common:data",
    "logbot-web:common:domain",
    "logbot-web:common:presentation",
    "logbot-web:common:compose",
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}
