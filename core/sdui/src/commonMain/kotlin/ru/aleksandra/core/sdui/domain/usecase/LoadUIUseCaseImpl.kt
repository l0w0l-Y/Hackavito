package ru.aleksandra.core.sdui.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.serialization.json.Json
import ru.aleksandra.core.sdui.domain.model.BindableValue
import ru.aleksandra.core.sdui.domain.model.SDUIComponentDomain

class LoadUIUseCaseImpl() : LoadUIUseCase {
    override suspend fun loadUI(screenName: String): Flow<SDUIComponentDomain> {
        delay(2000)
        val json = Json {
            classDiscriminator = "type"
        }

        val input = """
        {
  "type": "Row",
  "modifier": [
    {
      "type": "Width",
      "value": "200"
    },
    {
      "type": "Padding",
      "value": {
        "start": 16,
        "top": 0,
        "end": 0,
        "bottom": 0
      }
    }
  ],
  "horizontalArrangement": "start",
  "verticalAlignment": "center",
  "children": [
    {
      "type": "Icon",
      "modifier": [
        {
          "type": "Width",
          "value": 24
        },
        {
          "type": "Height",
          "value": 24
        }
      ],
      "url": "Res.drawable.ic_arrow"
    },
    {
      "type": "Column",
      "modifier": [
        {
          "type": "Padding",
          "value": {
            "start": 6,
            "top": 0,
            "end": 0,
            "bottom": 0
          }
        }
      ],
      "verticalArrangement": "start",
      "horizontalAlignment": "start",
      "children": [
        {
          "type": "Row",
          "modifier": [
            {
              "type": "Padding",
              "value": {
                "start": 0,
                "top": 4,
                "end": 0,
                "bottom": 0
              }
            }
          ],
          "horizontalArrangement": "start",
          "verticalAlignment": "center",
          "children": [
            {
              "type": "Text",
              "text": {
              "type": "Static",
                "value": "Добавьте ещё 1 товар до скидки 5%"
              },
              "modifier": []
            },
            {
              "type": "Icon",
              "modifier": [
                {
                  "type": "Width",
                  "value": 16
                },
                {
                  "type": "Height",
                  "value": 16
                }
              ],
              "url": "Res.drawable.ic_pointer"
            }
          ]
        }
      ]
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
                text = BindableValue.Static("Все в деталях"),
            )
        } else {
            SDUIComponentDomain.Text(
                modifier = emptyList(),
                text = BindableValue.Static("Неизвестный экран"),
            )
        }
        return flowOf(value)
    }

}

interface LoadUIUseCase {
    suspend fun loadUI(screenName: String): Flow<SDUIComponentDomain>
}