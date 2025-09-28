package ru.aleksandra.core.sdui.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import ru.aleksandra.core.sdui.domain.model.SDUIDataDomain

class LoadDataUseCaseImpl : LoadDataUseCase {
    override suspend fun loadData(screenName: String): Flow<List<SDUIDataDomain>> {
        return flowOf(listOf(SDUIDataDomain("navbar.title", "Карзина")))
    }
}

interface LoadDataUseCase {
    suspend fun loadData(screenName: String): Flow<List<SDUIDataDomain>>
}