package ru.aleksandra.core.ui

import hackavito.core.ui.generated.resources.Res
import hackavito.core.ui.generated.resources.ic_arrow
import hackavito.core.ui.generated.resources.ic_back
import hackavito.core.ui.generated.resources.ic_check
import hackavito.core.ui.generated.resources.ic_delete
import hackavito.core.ui.generated.resources.ic_favorite
import hackavito.core.ui.generated.resources.ic_minus
import hackavito.core.ui.generated.resources.ic_plus
import hackavito.core.ui.generated.resources.ic_pointer
import hackavito.core.ui.generated.resources.ic_star
import org.jetbrains.compose.resources.DrawableResource

//TODO: Добавить возможность искать иконки из других модулей
fun String.toDrawable(): DrawableResource? {
    return when (this) {
        "ic_arrow" -> Res.drawable.ic_arrow
        "ic_back" -> Res.drawable.ic_back
        "ic_check" -> Res.drawable.ic_check
        "ic_delete" -> Res.drawable.ic_delete
        "ic_favorite" -> Res.drawable.ic_favorite
        "ic_minus" -> Res.drawable.ic_minus
        "ic_plus" -> Res.drawable.ic_plus
        "ic_pointer" -> Res.drawable.ic_pointer
        "ic_star" -> Res.drawable.ic_star
        else -> null
    }
}