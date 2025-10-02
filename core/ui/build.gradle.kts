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
        namespace = "ru.aleksandra.core.ui"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
    }

    val xcfName = "core-uiKit"
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

        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:3.0.1")
        }

        commonMain {
            dependencies {
                implementation(compose.components.uiToolingPreview)
                implementation(compose.material3)
                implementation(compose.components.resources)

                implementation(projects.core.theme)

                implementation(libs.coil.compose)
                implementation(libs.coil.network.ktor3)
            }
        }
    }

    androidLibrary {
        experimentalProperties["android.experimental.kmp.enableAndroidResources"] = true
    }
}