package com.example.crm_nexora.ui.screens.unplannedvisit.main

import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.domain.models.Shift
import com.example.crm_nexora.ui.models.GiftProductUiModel

sealed interface UnplannedVisitEvents {
    data object Back : UnplannedVisitEvents
    data object Next : UnplannedVisitEvents
    data object Previous : UnplannedVisitEvents

    data class SelectCity(val value: String) : UnplannedVisitEvents
    data class SelectDistrict(val value: String) : UnplannedVisitEvents
    data class SelectEntityType(val value: String) : UnplannedVisitEvents
    data class SelectPlace(val value: String) : UnplannedVisitEvents
    data class SelectSpecialty(val value: String) : UnplannedVisitEvents
    data class SelectSupervisor(val value: String) : UnplannedVisitEvents
    data class SelectShift(val shift: Shift) : UnplannedVisitEvents
    data class SelectDoubleVisit(val isDouble: Boolean) : UnplannedVisitEvents

    data class OpenAddItem(val kind: QuantityKind) : UnplannedVisitEvents
    data object DismissOverlay : UnplannedVisitEvents
    data class ConfirmAddItems(val items: List<GiftProductUiModel>) : UnplannedVisitEvents

    data class IncrementGift(val id: String) : UnplannedVisitEvents
    data class DecrementGift(val id: String) : UnplannedVisitEvents
    data class IncrementProduct(val id: String) : UnplannedVisitEvents
    data class DecrementProduct(val id: String) : UnplannedVisitEvents

    data object EndVisit : UnplannedVisitEvents
    data object VoiceNoteClick : UnplannedVisitEvents
    data object AttachClick : UnplannedVisitEvents
    data object ViewLocationClick : UnplannedVisitEvents
}
