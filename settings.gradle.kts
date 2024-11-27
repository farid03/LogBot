rootProject.name = "logbot"

include(
    "logbot-commons",
    "logbot-server-rest-client",
    "logbot-server",
    "logbot-bot",
    "logbot-web",
    "logbot-web:composeApp"
)