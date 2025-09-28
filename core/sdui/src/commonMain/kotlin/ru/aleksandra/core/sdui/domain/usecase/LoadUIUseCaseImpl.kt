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
  "type": "Column",
  "verticalArrangement": "SpacedBy(24)",
  "children": [
    {
      "type": "AvitoNavBar",
      "title": {
        "type": "Static",
        "value": "Корзина"
      }
    },
    {
      "type": "AvitoSelectAll",
      "isChecked": true,
      "deleteCount": {
        "type": "Dynamic",
        "path": "totalItems"
      }
    },
    {
      "type": "AvitoShopName",
      "isChecked": true,
      "shopName": {
        "type": "Dynamic",
        "path": "items.0.store.name"
      },
      "rating": {
        "type": "Dynamic",
        "path": "items.0.store.rating"
      },
      "reviewsCount": {
        "type": "Dynamic",
        "path": "items.0.store.reviewsCount"
      }
    },
    {
      "type": "Column",
      "children": [
        {
          "type": "RepetitiveComponent",
          "component": {
            "type": "AvitoCartItem",
            "isChecked": true,
            "name": {
                "type": "Dynamic",
                "path": "name"
            },
            "priceWithoutDiscount": {
                "type": "Dynamic",
                "path": "priceWithoutDiscount"
            },
              "priceWithDiscount": {
                "type": "Dynamic",
                "path": "priceWithDiscount"
              },
              "salePercent": {
                "type": "Dynamic",
                "path": "salePercent"
              },
              "count": {
                "type": "Dynamic",
                "path": "count"
              },
              "imageUrl": {
                "type": "Dynamic",
                "path": "imageUrl"
              }
          },
          "itemsPath": "items.0.items.*"
        }
      ]
    },
    {
      "type": "AvitoShopName",
      "isChecked": true,
      "shopName": {
        "type": "Dynamic",
        "path": "items.1.store.name"
      },
      "rating": {
        "type": "Dynamic",
        "path": "items.1.store.rating"
      },
      "reviewsCount": {
        "type": "Dynamic",
        "path": "items.1.store.reviewsCount"
      }
    },
    {
      "type": "Column",
      "children": [
        {
          "type": "RepetitiveComponent",
          "component": {
            "type": "AvitoCartItem",
            "isChecked": true,
            "name": {
                "type": "Dynamic",
                "path": "name"
            },
            "priceWithoutDiscount": {
                "type": "Dynamic",
                "path": "priceWithoutDiscount"
            },
              "priceWithDiscount": {
                "type": "Dynamic",
                "path": "priceWithDiscount"
              },
              "salePercent": {
                "type": "Dynamic",
                "path": "salePercent"
              },
              "count": {
                "type": "Dynamic",
                "path": "count"
              },
              "imageUrl": {
                "type": "Dynamic",
                "path": "imageUrl"
              }
          },
          "itemsPath": "items.1.items.*"
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