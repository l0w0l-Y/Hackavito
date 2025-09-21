package ru.aleksandra.core.sdui.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.json.Json
import ru.aleksandra.core.sdui.presentation.model.SDUIComponent

class LoadUIUseCaseImpl() : LoadUIUseCase {
    override suspend fun loadUI(screenName: String): Flow<SDUIComponent> {
        delay(2000)
        val json = Json {
            classDiscriminator = "type"
        }

        val input = """
        {
    "type": "Row",
    "modifier": [
        {
            "type": "BackgroundColor",
            "color": "#000000"
        }
    ],
    "verticalAlignment": "CenterVertically",
    "children": [
        {
            "type": "Text",
            "text": "Это экран корзины",
            "color": "#FF0000",
            "fontSize": "10sp"
        },
        {
            "type": "Text",
            "text": "Второй текст",
            "color": "#FFFFFF",
            "fontSize": "12sp"
        }
    ]
}
        """

        val obj = json.decodeFromString<SDUIComponent>(input)

        val value = if (screenName == "cart") {
            obj
        } else if (screenName == "details") {
            SDUIComponent.Text(
                modifier = emptyList(),
                text = "Все в деталях",
            )
        } else {
            SDUIComponent.Text(
                modifier = emptyList(),
                text = "Неизвестный экран",
            )
        }
        return flowOf(value)
    }

}

interface LoadUIUseCase {
    suspend fun loadUI(screenName: String): Flow<SDUIComponent>
}