package ru.aleksandra.feature.admin.model

data class Screen(
    val id: String,
    val name: String,
    val description: String,
    val screenImageUrl: String?,
)

data class ScreenVersionControlModel(
    val screen: Screen,
    val versions: List<VersionControl>,
)

data class VersionControl(
    val id: String,
    val version: String,
    val createdAt: String,
    val authorId: String,
    val commit: String,
    val jsonContent: String,
    val isDefault: Boolean
)