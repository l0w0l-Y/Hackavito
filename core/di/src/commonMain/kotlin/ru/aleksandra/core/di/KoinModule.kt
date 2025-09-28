package ru.aleksandra.core.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.aleksandra.core.sdui.domain.usecase.LoadDataUseCase
import ru.aleksandra.core.sdui.domain.usecase.LoadDataUseCaseImpl
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCase
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCaseImpl
import ru.aleksandra.feature.cart.di.repositoryModule
import ru.aleksandra.feature.cart.di.viewModelModule

//TODO: Возможно, стоит просто перенести в app модуль

val useCaseModule = module {
    single { LoadUIUseCaseImpl() } bind LoadUIUseCase::class
    single { LoadDataUseCaseImpl() } bind LoadDataUseCase::class
}

fun initModules() = module {
    includes(
        viewModelModule,
        useCaseModule,
        repositoryModule,
    )
}