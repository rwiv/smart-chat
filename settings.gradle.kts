rootProject.name = "smart-chat"

val commonUtils: String by settings
val appApi: String by settings
val domainCore: String by settings
val infraCore: String by settings

include(
    commonUtils,
    appApi,
    domainCore,
    infraCore,
)
