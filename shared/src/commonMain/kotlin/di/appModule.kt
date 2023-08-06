package com.muzamil.dictionarykmm.di

import core.platformModule
import data.product.ProductRepoImp
import data.product.ProductRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.core.parameter.parametersOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import ui.theme.screen.home.HomeScreenViewModel
import kotlin.reflect.KClass

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule, platformModule(), homeModule)
}

fun initKoin() = initKoin {}
val commonModule = module {
    single { createJson() }
    single { createHttpClient(get(), get()) }
    single<ProductRepository> { ProductRepoImp(get()) }
    factoryOf(::HomeScreenViewModel)
}

val homeModule = module {
    factory { HomeScreenViewModel(get()) }
}


fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }
fun createHttpClient(
    json: Json,
    engine: HttpClientEngine,
) = HttpClient(engine) {
    install(ContentNegotiation) {
        json(json)
    }
    install(Logging) {
        level = LogLevel.ALL

    }

}


