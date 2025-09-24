package ru.aleksandra.hackavito

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import ru.aleksandra.core.sdui.presentation.di.initModules

fun initKoin(modules: List<Module> = emptyList()) {
    startKoin {
        modules(initModules() + modules)
    }
}