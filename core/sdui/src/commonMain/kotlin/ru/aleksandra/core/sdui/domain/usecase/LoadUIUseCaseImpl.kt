package ru.aleksandra.core.sdui.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.json.Json
import ru.aleksandra.core.sdui.domain.model.SDUIComponentDomain

class LoadUIUseCaseImpl() : LoadUIUseCase {
    override suspend fun loadUI(screenName: String): Flow<SDUIComponentDomain> {
        delay(2000)
        val json = Json {
            classDiscriminator = "type"
        }

        val input = """
        {
    "type": "Column",
    "children": [
        {
            "type": "AvitoNavBar",
            "title": "Корзина"
        },
        {
            "type": "AvitoSelectAll",
            "isChecked": true,
            "deleteCount": 3
        },
        {
            "type": "Button",
            "modifier": [
                {
                    "type": "BackgroundColor",
                    "color": "#000000"
                }
            ],
            "action": {
                "type": "Navigate",
                "destination": "details"
            },
            "content": {
                "type": "Text",
                "text": "Мы получили текст кнопки"
            }
        }
    ]
}
        """

        val obj = json.decodeFromString<SDUIComponentDomain>(input)

        val value = if (screenName == "cart") {
            obj
        } else if (screenName == "details") {
            SDUIComponentDomain.Text(
                modifier = emptyList(),
                text = "Все в деталях",
            )
        } else {
            SDUIComponentDomain.Text(
                modifier = emptyList(),
                text = "Неизвестный экран",
            )
        }
        return flowOf(value)
    }

}

interface LoadUIUseCase {
    suspend fun loadUI(screenName: String): Flow<SDUIComponentDomain>
}