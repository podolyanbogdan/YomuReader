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

rootProject.name = "YomuReader"
include(":app")
include(":core")
include(":features")
include(":features:bottom_bar")
include(":features:library_api")
include(":features:library")
include(":features:updates_api")
include(":features:updates")
include(":features:history_api")
include(":features:history")
include(":features:search_api")
include(":features:search")
include(":features:more_api")
include(":features:more")
include(":domain")
include(":data")
