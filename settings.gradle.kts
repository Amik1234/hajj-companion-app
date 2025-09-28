pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hajj-companion-app"

include(
    ":app",
    ":core:common",
    ":core:data",
    ":core:domain",
    ":core:ui",
    ":core:network",
    ":feature:rituals",
    ":feature:navigation",
    ":feature:tools",
    ":feature:settings",
    ":di"
)
