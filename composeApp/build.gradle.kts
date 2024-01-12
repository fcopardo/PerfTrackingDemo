import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    kotlin("plugin.serialization").version("1.9.10")
    //id("app.cash.sqldelight") version "2.0.0"
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.swiftKLib)
    alias(libs.plugins.sqldelight)
}

val coroutinesVersion = "1.7.3"
val ktorVersion = "2.3.5"
val sqlDelightVersion = "2.0.0"
val dateTimeVersion = "0.4.1"
var apikey = System.getenv("ml_access_token")

buildConfig {
    className("Constants")   // forces the class name. Defaults to 'BuildConfig'
    packageName("github.fcopardo.perfdemo")
    buildConfigField("String", "api_key", "\"$apikey\"")
    buildConfigField("String", "api_key2", "\"$apikey\"")
}

kotlin {

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(libs.compose.ui)
                implementation(libs.compose.ui.tooling.preview)
                implementation(libs.androidx.activity.compose)
                // ViewModel
                implementation(libs.androidx.lifecycle.viewmodel.ktx)
                // ViewModel utilities for Compose
                implementation(libs.androidx.lifecycle.viewmodel.compose)
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("io.coil-kt:coil:2.5.0")
                implementation("io.coil-kt:coil-compose:2.5.0")
                implementation("app.cash.sqldelight:android-driver:$sqlDelightVersion")
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.paging.common)
                implementation(libs.paging.compose)
                implementation(project(":commonLib"))
            }
        }
        val iosMain by creating {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
                implementation("app.cash.sqldelight:native-driver:$sqlDelightVersion")
                implementation("app.cash.sqldelight:native-driver:2.0.0")
                implementation(project(":commonLib"))
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("io.ktor:ktor-client-java:$ktorVersion")
            }
        }
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("app.cash.sqldelight:runtime:$sqlDelightVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$dateTimeVersion")
                implementation("com.github.skydoves:orbital:0.3.2")
                implementation(libs.kotlinx.coroutines.core)
                implementation("app.cash.paging:paging-compose-common:3.3.0-alpha02-0.4.0")
                implementation("app.cash.paging:paging-common:3.3.0-alpha02-0.4.0")
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
            }
        }
    }
}

android {
    namespace = "github.fcopardo.perfdemo"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "github.fcopardo.perfdemo"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/versions/**"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "github.fcopardo.perfdemo"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            //packageName.set("github.fcopardo.sqldelight")
            packageName.set("github.fcopardo.perfdemo.cache")
        }
    }
}
