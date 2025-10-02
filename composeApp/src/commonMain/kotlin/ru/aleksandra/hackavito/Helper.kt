package ru.aleksandra.hackavito

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import ru.aleksandra.core.data.di.dispatcherModule
import ru.aleksandra.core.data.di.networkModule
import ru.aleksandra.core.di.initModules

expect val platformModules: List<Module>
fun initKoin() {
    startKoin {
        modules(initModules() + platformModules)
    }
}