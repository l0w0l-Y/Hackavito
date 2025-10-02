package ru.aleksandra.feature.admin.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.aleksandra.feature.admin.data.AdminApi
import ru.aleksandra.feature.admin.AdminViewModel

val adminViewModelModule = module {
    viewModel {
        AdminViewModel(get())
    }
    single { AdminApi(get()) }
}
