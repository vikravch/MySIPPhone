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

        maven { // for com.github.chrisbanes:PhotoView
            url = uri("https://www.jitpack.io")
        }

            maven {
                println("Using CI built SDK from maven repository at https://linphone.org/maven_repository")
                name = "linphone.org maven repository"
                url = uri("https://linphone.org/maven_repository")
                content {
                    includeGroup("org.linphone")
                }
            }

    }
}

rootProject.name = "MySIPPhone"
include(":app")
include(":core")
include(":data")
include(":data:room-database")
include(":data:firestore-database")
include(":data:ktor-network")
include(":data:retrofit-network")
include(":features")
include(":features:sip_telephony")
include(":features:sip_telephony:data")
include(":features:sip_telephony:domain")
include(":features:sip_telephony:presentation")

include(":presentation")
include(":presentation:compose-core")
