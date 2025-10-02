package ru.aleksandra.core.sdui.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.json.Json
import ru.aleksandra.core.sdui.data.SDUIRepository
import ru.aleksandra.core.sdui.domain.model.SDUIComponentDomain

class LoadUIUseCaseImpl(
    private val repository: SDUIRepository
) : LoadUIUseCase {
    override suspend fun loadUI(screenName: String): Flow<SDUIComponentDomain> {
        val json = Json {
            classDiscriminator = "type"
        }
        val input = repository.getSDUIByName(screenName)
        val value = json.decodeFromString<SDUIComponentDomain>(input)
        return flowOf(value)
    }

}

interface LoadUIUseCase {
    suspend fun loadUI(screenName: String): Flow<SDUIComponentDomain>
}