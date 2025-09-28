package ru.aleksandra.hackavito

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import ru.aleksandra.core.di.initModules

fun initKoin() {
    startKoin {
        modules(initModules())
    }
}