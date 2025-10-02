package ru.aleksandra.core.data.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatcherModule = module {
    single { Dispatchers.Default }
}