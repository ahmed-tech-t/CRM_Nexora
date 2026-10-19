package com.example.crm_nexora.ui.screens.statics.main

import androidx.compose.runtime.Immutable
import com.example.crm_nexora.ui.screens.statics.models.StaticsAccountCategoryUi
import com.example.crm_nexora.ui.screens.statics.models.StaticsGiftsItemUi
import com.example.crm_nexora.ui.screens.statics.models.StaticsMetricUi

@Immutable
data class StaticsState(
    val completedPlan: Int,
    val todayVisits: Int,
    val unplannedVisits: Int,

    val custodyItems: List<StaticsGiftsItemUi>,
    val accountCategories: List<StaticsAccountCategoryUi>,
)
