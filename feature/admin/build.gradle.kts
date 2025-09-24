import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}


kotlin {

    androidLibrary {
        namespace = "ru.aleksandra.feature.admin"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }

    val xcfName = "feature-adminKit"
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

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)

                implementation(libs.navigation.compose)

                implementation(projects.core.sdui)
                implementation(projects.core.ui)
                implementation(projects.core.theme)

                // Serialization Json
                implementation(libs.kotlinx.serialization.json)

                // Napier for logging
                implementation(libs.napier)
                // FileKit
                implementation("io.github.vinceglb:filekit-core:0.11.0")
                implementation("io.github.vinceglb:filekit-dialogs:0.11.0")
                implementation("io.github.vinceglb:filekit-dialogs-compose:0.11.0")
            }
        }
    }
}