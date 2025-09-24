package ru.aleksandra.hackavito

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import ru.aleksandra.feature.admin.AdminApp
import ru.aleksandra.feature.admin.WebApp
import ru.aleksandra.feature.admin.di.adminViewModelModule

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoin(listOf(adminViewModelModule))
    ComposeViewport(document.body!!) {
        WebApp()
    }
}