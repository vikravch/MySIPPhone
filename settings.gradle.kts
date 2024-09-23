pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "SampleApp"
include(":app")
include(":core")
include(":data")
include(":data:room-database")
include(":data:firestore-database")
include(":data:ktor-network")
include(":data:retrofit-network")
include(":features")
include(":features:simple_feature")
include(":features:simple_feature:data")
include(":features:simple_feature:domain")
include(":features:simple_feature:presentation")
include(":presentation")
include(":presentation:compose-core")
