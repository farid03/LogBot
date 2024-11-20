plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "logbot"

include(
    "logbot-commons",
    "logbot-server-rest-client",
    "logbot-server",
    "logbot-bot"
)
include("logbot-auth")
