package ru.aleksandra.hackavito

import org.koin.core.context.startKoin
import ru.aleksandra.core.sdui.presentation.di.initModules

fun initKoin() {
    startKoin {
        modules(initModules())
    }
}