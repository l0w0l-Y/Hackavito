package ru.aleksandra.core.di

import org.koin.dsl.bind
import org.koin.dsl.module
import ru.aleksandra.core.data.di.dispatcherModule
import ru.aleksandra.core.data.di.networkModule
import ru.aleksandra.core.sdui.data.di.SDUIApiModule
import ru.aleksandra.core.sdui.data.di.SDUIRepositoryModule
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCase
import ru.aleksandra.core.sdui.domain.usecase.LoadUIUseCaseImpl
import ru.aleksandra.feature.cart.data.di.cartApiModule
import ru.aleksandra.feature.cart.data.di.cartRepositoryModule
import ru.aleksandra.feature.cart.di.repositoryModule
import ru.aleksandra.feature.cart.di.viewModelModule
import ru.aleksandra.feature.cart.domain.di.cartUseCaseModule

//TODO: Возможно, стоит просто перенести в app модуль, разделить по модулям
val useCaseModule = module {
    single { LoadUIUseCaseImpl(get()) } bind LoadUIUseCase::class
}

fun initModules() = module {
    includes(
        viewModelModule,
        cartUseCaseModule,
        useCaseModule,
        SDUIApiModule,
        SDUIRepositoryModule,
        repositoryModule,
        cartRepositoryModule,
        cartApiModule,
        networkModule,
        dispatcherModule
    )
}