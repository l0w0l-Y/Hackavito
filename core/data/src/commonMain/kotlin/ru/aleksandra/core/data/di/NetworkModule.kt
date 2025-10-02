package ru.aleksandra.core.data.di

import ru.aleksandra.core.data.builder
import org.koin.dsl.module

val networkModule = module {
    single {
        builder()
    }
}