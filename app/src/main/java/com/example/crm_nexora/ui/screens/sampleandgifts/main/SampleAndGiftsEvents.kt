package com.example.crm_nexora.ui.screens.sampleandgifts.main

sealed interface SampleAndGiftsEvents {
    data class SelectTab(val tab: SampleAndGiftsTab) : SampleAndGiftsEvents
}
