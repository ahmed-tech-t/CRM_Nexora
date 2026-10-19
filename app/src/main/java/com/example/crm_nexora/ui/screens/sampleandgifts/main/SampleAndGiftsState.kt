package com.example.crm_nexora.ui.screens.sampleandgifts.main

import androidx.compose.runtime.Immutable
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsItemType
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsItemUi

enum class SampleAndGiftsTab {
    Samples,
    Gifts,
}

@Immutable
data class SampleAndGiftsState(
    val selectedTab: SampleAndGiftsTab,
    val items: List<SampleAndGiftsItemUi>,
)
