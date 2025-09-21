import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization")
}


kotlin {

    androidLibrary {
        namespace = "ru.aleksandra.core.sdui"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }

    val xcfName = "core-sduiKit"
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = xcfName
        }
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(compose.material3)
                implementation(compose.components.resources)

                // Navigation
                implementation(libs.navigation.compose)

                // Koin
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)

                // Napier for logging
                implementation(libs.napier)

                // Serialization Json
                implementation(libs.kotlinx.serialization.json)

                implementation(projects.core.theme)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.koin.androidx.compose)
            }
        }
    }
}
