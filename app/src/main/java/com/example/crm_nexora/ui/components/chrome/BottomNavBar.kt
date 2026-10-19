package com.example.crm_nexora.ui.components.chrome

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSurfaceFocus
import com.example.crm_nexora.ui.theme.NexoraTextMuted

data class BottomNavItem(
    val id: String,
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int,
)

/**
 * Controlled bottom nav — [selectedId] owned by parent/ViewModel.
 */
@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    selectedId: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
            .fillMaxWidth()
            .height(NexoraDimens.bottomNavHeight),
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        items.forEach { item ->
            val selected = item.id == selectedId
            val label = stringResource(item.labelRes)
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(item.id) },
                icon = {
                    NexoraIcon(
                        painter = painterResource(item.iconRes),
                        contentDescription = label,
                    )
                },
                label = {
                    NexoraText(
                        text = label,
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NexoraPrimary,
                    selectedTextColor = NexoraPrimary,
                    unselectedIconColor = NexoraTextMuted,
                    unselectedTextColor = NexoraTextMuted,
                    indicatorColor = NexoraSurfaceFocus,

                ),
            )
        }
    }
}

fun defaultBottomNavItems(): List<BottomNavItem> = listOf(
    BottomNavItem("home", R.string.nav_home, R.drawable.ic_home),
    BottomNavItem("stats", R.string.nav_stats, R.drawable.ic_statistics),
    BottomNavItem("visits", R.string.nav_visits, R.drawable.ic_visits),
    BottomNavItem("plan", R.string.nav_plan, R.drawable.ic_visits),
    BottomNavItem("custody", R.string.nav_custody, R.drawable.ic_packages),
)

@Preview(showBackground = true, locale = "ar")
@Composable
private fun BottomNavBarPreview() {
    CRM_NexoraTheme {
        BottomNavBar(
            items = defaultBottomNavItems(),
            selectedId = "home",
            onItemSelected = {},
        )
    }
}
