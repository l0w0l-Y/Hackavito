package ru.aleksandra.core.sdui.data

class SDUIRepositoryImpl(
    private val api: SDUIApi
) : SDUIRepository {
    override suspend fun getSDUIByName(name: String): String {
        return api.getSDUIByName(name)
    }
}

interface SDUIRepository {
    suspend fun getSDUIByName(name: String): String
}