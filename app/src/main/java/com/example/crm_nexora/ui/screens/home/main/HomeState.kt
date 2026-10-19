package com.example.crm_nexora.ui.screens.home.main

import androidx.compose.runtime.Immutable
import com.example.crm_nexora.ui.models.AttendanceUi
import com.example.crm_nexora.ui.models.ConnectionStatusUiModel
import com.example.crm_nexora.ui.models.UserUi
import com.example.crm_nexora.ui.models.VisitUi

@Immutable
data class HomeState(
    val user: UserUi,
    val attendance: AttendanceUi,
    val connection: ConnectionStatusUiModel,
    val activeVisit: VisitUi?,
    val todayVisits: List<VisitUi>,
    val isDrawerOpen: Boolean = false,
)
