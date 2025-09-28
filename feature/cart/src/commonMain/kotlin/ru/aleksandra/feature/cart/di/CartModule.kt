package ru.aleksandra.feature.cart.di

import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.aleksandra.feature.cart.data.LoadCartRepository
import ru.aleksandra.feature.cart.data.LoadCartRepositoryImpl
import ru.aleksandra.feature.cart.presentation.CartViewModel

val repositoryModule = module {
    single { LoadCartRepositoryImpl() } bind LoadCartRepository::class
}

val viewModelModule = module {
    viewModel { (savedStateHandle: SavedStateHandle) ->
        CartViewModel(savedStateHandle, get(), get())
    }
}