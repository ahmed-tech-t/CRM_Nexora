package com.example.crm_nexora.ui.screens.unplannedvisit.main

import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.QuantityKind
import com.example.crm_nexora.domain.utils.UiEvent
import com.example.crm_nexora.domain.utils.UiText
import com.example.crm_nexora.ui.common.BaseViewModel
import com.example.crm_nexora.ui.models.GiftProductUiModel
import com.example.crm_nexora.ui.screens.unplannedvisit.models.UnplannedVisitFakes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class UnplannedVisitViewModel @Inject constructor() : BaseViewModel() {

    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<UnplannedVisitState> = _state.asStateFlow()

    fun onEvent(event: UnplannedVisitEvents) {
        when (event) {
            UnplannedVisitEvents.Back -> sendUiEvent(UiEvent.NavigationUp)
            UnplannedVisitEvents.Next -> advanceStep()
            UnplannedVisitEvents.Previous -> retreatStep()
            is UnplannedVisitEvents.SelectCity -> updateVisit { it.copy(city = event.value) }
            is UnplannedVisitEvents.SelectDistrict -> updateVisit { it.copy(region = event.value) }
            is UnplannedVisitEvents.SelectEntityType -> updateVisit { it.copy(account = event.value) }
            is UnplannedVisitEvents.SelectPlace -> updateVisit { it.copy(placeName = event.value) }
            is UnplannedVisitEvents.SelectSpecialty -> updateVisit { it.copy(specialty = event.value) }
            is UnplannedVisitEvents.SelectSupervisor -> updateVisit {
                it.copy(supervisorName = event.value)
            }
            is UnplannedVisitEvents.SelectShift -> updateVisit { it.copy(shift = event.shift) }
            is UnplannedVisitEvents.SelectDoubleVisit -> updateVisit {
                it.copy(isDoubleVisit = event.isDouble)
            }
            is UnplannedVisitEvents.OpenAddItem -> openAddItem(event.kind)
            UnplannedVisitEvents.DismissOverlay -> clearOverlay()
            is UnplannedVisitEvents.ConfirmAddItems -> confirmAddItems(event.items)
            is UnplannedVisitEvents.IncrementGift -> adjustGift(event.id, +1)
            is UnplannedVisitEvents.DecrementGift -> adjustGift(event.id, -1)
            is UnplannedVisitEvents.IncrementProduct -> adjustProduct(event.id, +1)
            is UnplannedVisitEvents.DecrementProduct -> adjustProduct(event.id, -1)
            UnplannedVisitEvents.EndVisit -> {
                sendUiEvent(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.unplanned_visit_ended)),
                )
                sendUiEvent(UiEvent.NavigationUp)
            }
            UnplannedVisitEvents.VoiceNoteClick,
            UnplannedVisitEvents.AttachClick,
            UnplannedVisitEvents.ViewLocationClick -> {
                sendUiEvent(
                    UiEvent.ShowSnackBar(UiText.StringResource(R.string.placeholder_coming_soon)),
                )
            }
        }
    }

    private fun advanceStep() {
        _state.update { state ->
            val next = when (state.currentStep) {
                UnplannedVisitStep.Entity -> UnplannedVisitStep.GiftsProducts
                UnplannedVisitStep.GiftsProducts -> UnplannedVisitStep.Result
                UnplannedVisitStep.Result -> UnplannedVisitStep.Result
            }
            state.copy(currentStep = next)
        }
    }

    private fun retreatStep() {
        _state.update { state ->
            val prev = when (state.currentStep) {
                UnplannedVisitStep.Entity -> UnplannedVisitStep.Entity
                UnplannedVisitStep.GiftsProducts -> UnplannedVisitStep.Entity
                UnplannedVisitStep.Result -> UnplannedVisitStep.GiftsProducts
            }
            state.copy(currentStep = prev)
        }
    }

    private fun updateVisit(transform: (com.example.crm_nexora.ui.models.VisitUi) -> com.example.crm_nexora.ui.models.VisitUi) {
        _state.update { it.copy(visit = transform(it.visit)) }
    }

    private fun openAddItem(kind: QuantityKind) {
        _state.update { it.copy(overlay = UnplannedVisitOverlay.AddItem(kind)) }
    }

    private fun clearOverlay() {
        _state.update { it.copy(overlay = UnplannedVisitOverlay.None) }
    }

    private fun confirmAddItems(items: List<GiftProductUiModel>) {
        if (items.isEmpty()) return
        val overlay = _state.value.overlay as? UnplannedVisitOverlay.AddItem ?: return
        val stamped = System.currentTimeMillis()
        val added = items.mapIndexed { index, item ->
            item.copy(id = "${item.id}-$stamped-$index")
        }
        _state.update { current ->
            when (overlay.kind) {
                QuantityKind.Gift -> {
                    val gifts = current.giftItems + added
                    current.copy(
                        giftItems = gifts,
                        visit = syncVisitLines(current.visit, gifts, current.productItems),
                        overlay = UnplannedVisitOverlay.None,
                    )
                }
                QuantityKind.Product -> {
                    val products = current.productItems + added
                    current.copy(
                        productItems = products,
                        visit = syncVisitLines(current.visit, current.giftItems, products),
                        overlay = UnplannedVisitOverlay.None,
                    )
                }
            }
        }
    }

    private fun adjustGift(id: String, delta: Int) {
        _state.update { current ->
            val gifts = current.giftItems.mapNotNull { item ->
                if (item.id != id) return@mapNotNull item
                val qty = (item.quantity + delta).coerceAtLeast(0)
                if (qty == 0) null else item.copy(quantity = qty, quantityLabel = qty.toString())
            }
            current.copy(
                giftItems = gifts,
                visit = syncVisitLines(current.visit, gifts, current.productItems),
            )
        }
    }

    private fun adjustProduct(id: String, delta: Int) {
        _state.update { current ->
            val products = current.productItems.mapNotNull { item ->
                if (item.id != id) return@mapNotNull item
                val qty = (item.quantity + delta).coerceAtLeast(0)
                if (qty == 0) null else item.copy(quantity = qty, quantityLabel = qty.toString())
            }
            current.copy(
                productItems = products,
                visit = syncVisitLines(current.visit, current.giftItems, products),
            )
        }
    }

    private fun syncVisitLines(
        visit: com.example.crm_nexora.ui.models.VisitUi,
        gifts: List<GiftProductUiModel>,
        products: List<GiftProductUiModel>,
    ) = visit.copy(
        gifts = gifts,
        products = products,
        giftsQty = gifts.sumOf { it.quantity },
        productsQty = products.sumOf { it.quantity },
    )

    companion object {
        fun initialState(): UnplannedVisitState {
            val gifts = UnplannedVisitFakes.initialGiftItems()
            val products = UnplannedVisitFakes.initialProductItems()
            val visit = UnplannedVisitFakes.initialVisit().copy(
                gifts = gifts,
                products = products,
                giftsQty = gifts.sumOf { it.quantity },
                productsQty = products.sumOf { it.quantity },
            )
            return UnplannedVisitState(
                currentStep = UnplannedVisitStep.Entity,
                visit = visit,
                giftItems = gifts,
                productItems = products,
                overlay = UnplannedVisitOverlay.None,
                cityOptions = UnplannedVisitFakes.cityOptions,
                districtOptions = UnplannedVisitFakes.districtOptions,
                entityTypeOptions = UnplannedVisitFakes.entityTypeOptions,
                placeOptions = UnplannedVisitFakes.placeOptions,
                specialtyOptions = UnplannedVisitFakes.specialtyOptions,
                supervisorOptions = UnplannedVisitFakes.supervisorOptions,
            )
        }
    }
}
