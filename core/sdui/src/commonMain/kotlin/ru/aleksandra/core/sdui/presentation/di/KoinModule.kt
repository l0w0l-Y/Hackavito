package ru.aleksandra.core.sdui.presentation.di

import androidx.lifecycle.SavedStateHandle
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.aleksandra.core.sdui.domain.LoadUIUseCase
import ru.aleksandra.core.sdui.domain.LoadUIUseCaseImpl
import ru.aleksandra.core.sdui.presentation.SDUIViewModel

val viewModelModule = module {
    viewModel { (savedStateHandle: SavedStateHandle) ->
        SDUIViewModel(savedStateHandle, get())
    }
}

val useCaseModule = module {
    single { LoadUIUseCaseImpl() } bind LoadUIUseCase::class
}

fun initModules() = module {
    includes(
        viewModelModule,
        useCaseModule,
    )
}