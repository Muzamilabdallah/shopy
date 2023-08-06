import Deps.ktorSerializationJson

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")

    kotlin("plugin.serialization").version(org.gradle.kotlin.dsl.embeddedKotlinVersion)

}

kotlin {
    android()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] =
            "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)


                implementation(Deps.ktorCore)
                implementation(Deps.ktorNegotiation)
                implementation(Deps.ktorKotlinSerialization)
                implementation (ktorSerializationJson)
                implementation(Deps.sqlDelightRuntime)
                implementation(Deps.clientLogging)
                implementation(Deps.clientJson)
                implementation(Deps.koin)


                implementation(Deps.clientLogging)
                implementation(Deps.clientJson)

                //MOKO
                implementation(Deps.MOKO_CORE)
                implementation(Deps.MOKO_COMPOSE)
                implementation(Deps.MOKO_FLOW)
                implementation(Deps.MOKO_COMPOSE_FLOW)

                // kamel image loader
                implementation(Deps.KAMEL_IMAGE_LOADER)

                //voyager navigation
                implementation(Deps.Voyager_navigation)
                implementation(Deps.Voyager_koin)

                //napier for logging
                implementation(Deps.Napier)

            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
                implementation(Deps.koinAndroid)
                implementation(Deps.ktorAndroid)
                implementation(Deps.sqlDelightAndroidDriver)

            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Deps.ktorIOS)
                implementation(Deps.sqlDelightNativeDriver)
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(17)
    }
}


