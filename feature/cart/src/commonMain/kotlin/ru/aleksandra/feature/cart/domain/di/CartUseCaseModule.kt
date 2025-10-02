package ru.aleksandra.feature.cart.domain.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.aleksandra.feature.cart.domain.CartUseCase
import ru.aleksandra.feature.cart.domain.CartUseCaseImpl

val cartUseCaseModule = module {
    single { CartUseCaseImpl(get()) } bind CartUseCase::class
}