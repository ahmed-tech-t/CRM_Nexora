package com.example.crm_nexora.ui.screens.unplannedvisit.main

import androidx.compose.runtime.Immutable
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.ui.models.GiftProductUiModel
import com.example.crm_nexora.ui.models.VisitUi

enum class UnplannedVisitStep {
    Entity,
    GiftsProducts,
    Result,
}

sealed interface UnplannedVisitOverlay {
    data object None : UnplannedVisitOverlay
    data class AddItem(val kind: QuantityKind) : UnplannedVisitOverlay
}

@Immutable
data class UnplannedVisitState(
    val currentStep: UnplannedVisitStep,
    val visit: VisitUi,
    val giftItems: List<GiftProductUiModel>,
    val productItems: List<GiftProductUiModel>,
    val overlay: UnplannedVisitOverlay,
    val cityOptions: List<String>,
    val districtOptions: List<String>,
    val entityTypeOptions: List<String>,
    val placeOptions: List<String>,
    val specialtyOptions: List<String>,
    val supervisorOptions: List<String>,
)
