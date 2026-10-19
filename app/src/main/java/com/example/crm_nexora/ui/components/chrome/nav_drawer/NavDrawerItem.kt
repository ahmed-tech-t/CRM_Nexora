package com.example.crm_nexora.ui.components.chrome.nav_drawer

import androidx.annotation.StringRes
import com.example.crm_nexora.R

sealed class NavDrawerItem(@StringRes val labelRes: Int) {
    data object Planning : NavDrawerItem(R.string.drawer_planning)
    data object Planned : NavDrawerItem(R.string.drawer_planned)
    data object Unplanned : NavDrawerItem(R.string.drawer_unplanned)
    data object Leaves : NavDrawerItem(R.string.drawer_leaves)
    data object AddDoctor : NavDrawerItem(R.string.drawer_add_doctor)
    data object InfoCenter : NavDrawerItem(R.string.drawer_info_center)

    companion object {
        val all: List<NavDrawerItem> = listOf(
            Planning,
            Planned,
            Unplanned,
            Leaves,
            AddDoctor,
            InfoCenter,
        )
    }
}
