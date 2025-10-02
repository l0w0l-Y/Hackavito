package ru.aleksandra.hackavito

import org.koin.core.module.Module
import ru.aleksandra.feature.admin.di.adminViewModelModule

actual val platformModules: List<Module> = listOf(adminViewModelModule)