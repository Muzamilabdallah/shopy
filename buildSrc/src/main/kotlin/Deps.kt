import Versions.gradleVersion
import Versions.kotlinVersion
import Versions.ktorVersion
import Versions.sqlDelightGradleVersion
import Versions.sqlDelightVersion

object Deps {


    const val compose_material3="androidx.compose.material3:material3:1.1.1"
    const val compose_foundation="androidx.compose.foundation:foundation:1.4.3"
    const val compose_runtime="androidx.compose.runtime:runtime:1.4.3"

    //ktor
    const val ktorCore = "io.ktor:ktor-client-core:$ktorVersion"
    const val ktorNegotiation = "io.ktor:ktor-client-content-negotiation:$ktorVersion"
    const val ktorKotlinSerialization = "io.ktor:ktor-serialization-kotlinx-json:$ktorVersion"
    const val ktorSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1"

    const val ktorAndroid = "io.ktor:ktor-client-android:$ktorVersion"
    const val ktorIOS = "io.ktor:ktor-client-ios:$ktorVersion"
    const val ktorIosDrawin= "io.ktor:ktor-client-darwin:$ktorVersion"
    const val clientJson = "io.ktor:ktor-client-json:${ktorVersion}"
    const val clientLogging = "io.ktor:ktor-client-logging:${ktorVersion}"


    //gradle
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val androidBuildTools = "com.android.tools.build:gradle:$gradleVersion"
    const val sqlDelightGradlePlugin =
        "com.squareup.sqldelight:gradle-plugin:$sqlDelightGradleVersion"

    // SQLDELIGHT
    const val sqlDelightRuntime = "com.squareup.sqldelight:runtime:$sqlDelightVersion"
    const val sqlDelightAndroidDriver = "com.squareup.sqldelight:android-driver:$sqlDelightVersion"
    const val sqlDelightNativeDriver = "com.squareup.sqldelight:native-driver:$sqlDelightVersion"
    const val sqlDelightCoroutinesExtensions =
        "com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion"

    //koin
    const val koin = "io.insert-koin:koin-core:${Versions.koin}"

    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"

    const val MOKO_CORE = "dev.icerock.moko:mvvm-core:0.16.1"
    const val MOKO_COMPOSE = "dev.icerock.moko:mvvm-compose:0.16.1"
    const val MOKO_FLOW = "dev.icerock.moko:mvvm-flow:0.16.1"
    const val MOKO_COMPOSE_FLOW = "dev.icerock.moko:mvvm-flow-compose:0.16.1"
    const val KAMEL_IMAGE_LOADER = "media.kamel:kamel-image:0.7.0"

    const val Voyager_navigation="cafe.adriel.voyager:voyager-navigator:${Versions.voyagerVersion}"
    const val Voyager_koin="cafe.adriel.voyager:voyager-koin:${Versions.voyagerVersion}"
    const val Napier="io.github.aakira:napier:${Versions.Napier}"



    // Multiplatform

    // Navigator


}