package ru.aleksandra.feature.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hackavito.feature.admin.generated.resources.Res
import hackavito.feature.admin.generated.resources.ic_branch
import hackavito.feature.admin.generated.resources.ic_figure_scobe
import hackavito.feature.admin.generated.resources.ic_folder
import hackavito.feature.admin.generated.resources.ic_info
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import ru.aleksandra.core.theme.AvitoTheme
import ru.aleksandra.core.theme.bgPage
import ru.aleksandra.core.theme.contentPrimary
import ru.aleksandra.core.theme.controlBgMasterPrimary
import ru.aleksandra.core.theme.controlContentMasterPassive
import ru.aleksandra.core.theme.m20

@Composable
fun WebApp() {
    AvitoTheme() {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                val navController = rememberNavController()
                AdminNavigation { navController.navigate(it.route) }
                AdminNavHost(navController)
            }
        }
    }
}

@Composable
fun AdminNavigation(onClick: (AdminNavRoute) -> Unit) {
    val navRoute = listOf(
        TopLevelRoute(
            name = "Code Editor",
            route = AdminNavRoute.Create,
            drawableResource = Res.drawable.ic_figure_scobe
        ),
        TopLevelRoute(
            name = "Version control",
            route = AdminNavRoute.VersionControl,
            drawableResource = Res.drawable.ic_branch
        ),
        TopLevelRoute(
            name = "File Manager",
            route = AdminNavRoute.VersionControl,
            drawableResource = Res.drawable.ic_folder
        ),
        TopLevelRoute(
            name = "Projects",
            route = AdminNavRoute.VersionControl,
            drawableResource = Res.drawable.ic_info
        )
    )
    var selectedItem by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .padding(12.dp)
            .width(200.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        navRoute.forEachIndexed { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        selectedItem = index
                        onClick(item.route)
                    }
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        if (index == selectedItem) MaterialTheme.colorScheme.controlBgMasterPrimary else MaterialTheme.colorScheme.bgPage,
                    )
                    .padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Icon(
                    painter = painterResource(item.drawableResource),
                    contentDescription = item.name,
                    tint = if (index == selectedItem) MaterialTheme.colorScheme.controlContentMasterPassive else MaterialTheme.colorScheme.contentPrimary,
                )
                Text(
                    item.name,
                    style = MaterialTheme.typography.m20,
                    color = if (index == selectedItem) MaterialTheme.colorScheme.controlContentMasterPassive else MaterialTheme.colorScheme.contentPrimary,
                )
            }

        }
    }
}

@Composable
fun RowScope.AdminNavHost(navController: NavHostController) {
    NavHost(
        navController,
        startDestination = AdminNavRoute.Create.route,
        modifier = Modifier.weight(1f)
    ) {
        composable(AdminNavRoute.Create.route) {
            CodeEditorScreen()
        }
        composable(AdminNavRoute.VersionControl.route) {
            VersionControlScreen()
        }
    }
}

data class TopLevelRoute(
    val name: String, val route: AdminNavRoute, val drawableResource: DrawableResource
)

@Serializable
sealed class AdminNavRoute(val route: String) {
    @Serializable
    object Create : AdminNavRoute("create")

    @Serializable
    object VersionControl : AdminNavRoute("screens")
}