package ru.aleksandra.feature.cart.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.aleksandra.feature.cart.data.CartApi
import ru.aleksandra.feature.cart.data.CartRepository
import ru.aleksandra.feature.cart.data.CartRepositoryImpl

val cartApiModule = module {
    single { CartApi(get()) }
}

val cartRepositoryModule = module {
    singleOf(::CartRepositoryImpl) bind CartRepository::class
}