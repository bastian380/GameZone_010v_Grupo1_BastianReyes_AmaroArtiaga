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

    // 👇👇 AÑADE ESTE BLOQUE AQUÍ 👇👇
    plugins {
        // Debes reemplazar estas versiones con las que estés usando:
        val kotlinVersion = "1.9.22"
        val androidToolsVersion = "8.2.2"

        id("com.android.application") version androidToolsVersion
        id("org.jetbrains.kotlin.android") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.compose") version kotlinVersion
        id("org.jetbrains.kotlin.kapt") version kotlinVersion // Para Room
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GameZone"
include(":app")
