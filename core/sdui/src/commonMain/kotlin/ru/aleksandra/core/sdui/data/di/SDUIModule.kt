package ru.aleksandra.core.sdui.data.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.aleksandra.core.sdui.data.SDUIApi
import ru.aleksandra.core.sdui.data.SDUIRepository
import ru.aleksandra.core.sdui.data.SDUIRepositoryImpl

val SDUIRepositoryModule = module {
    single { SDUIRepositoryImpl(get()) } bind SDUIRepository::class
}

val SDUIApiModule = module {
    single { SDUIApi(get()) }
}