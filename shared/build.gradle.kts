import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "2.0.0"
    id("co.touchlab.skie") version "0.9.2"
    id("com.rickclephas.kmp.nativecoroutines") version "1.0.0-ALPHA-31"

}

val kmpBomVersion: String = "1.0.3"
val koinVersion: String = "3.5.6"
kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here

            //Coil
            implementation(libs.coil3.coil.compose)
            implementation(libs.coil.network.ktor)

            //Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.coroutines.core)


            //KOIN
            api(libs.koin.core)
            implementation(libs.koin.test)
            implementation(libs.koin.compose.multiplatform)
            implementation(libs.rickclephas.kmp.observableviewmodel.core)
            implementation(project.dependencies.platform("io.github.tweener:kmp-bom:$kmpBomVersion")) // Mandatory
            implementation("io.github.tweener:kmp-firebase")
            implementation(platform("io.insert-koin:koin-bom:$koinVersion"))
            implementation("io.insert-koin:koin-core")

            //Date
            implementation(libs.kotlinx.datetime)


        }
        androidMain.dependencies {

            //Ktor
            implementation(libs.ktor.client.okhttp)

            //KOIN
            implementation(libs.koin.androidx.compose)
        }
        iosMain.dependencies {
            //Ktor
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "org.example.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
dependencies {
    implementation(libs.androidx.lifecycle.viewmodel.android)
}

