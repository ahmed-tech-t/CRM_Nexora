package com.example.crm_nexora.ui.screens.datacenter.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.crm_nexora.R

@Immutable
data class DataCenterSyncItemUi(
    val id: String,
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int,
    val lastUpdatedLabel: String,
    val status: DataCenterSyncStatus = DataCenterSyncStatus.Idle,
) {
    companion object {
        val ID_FULL = "full_master"
        val ID_DOCTORS = "doctors"
        val ID_CLINICS = "clinics"
        val ID_PLANNED = "planned_visits"
        val SYNCED_JUST_NOW = "آخر تحديث: الآن"

        fun items(): List<DataCenterSyncItemUi> = listOf(
            DataCenterSyncItemUi(
                id = ID_FULL,
                titleRes = R.string.data_center_card_full_title,
                subtitleRes = R.string.data_center_card_full_subtitle,
                lastUpdatedLabel = "آخر تحديث: منذ 18 دقيقة",
                status = DataCenterSyncStatus.Idle,
            ),
            DataCenterSyncItemUi(
                id = ID_DOCTORS,
                titleRes = R.string.data_center_card_doctors_title,
                subtitleRes = R.string.data_center_card_doctors_subtitle,
                lastUpdatedLabel = "آخر تحديث: منذ 18 دقيقة",
                status = DataCenterSyncStatus.Idle,
            ),
            DataCenterSyncItemUi(
                id = ID_CLINICS,
                titleRes = R.string.data_center_card_clinics_title,
                subtitleRes = R.string.data_center_card_clinics_subtitle,
                lastUpdatedLabel = "آخر تحديث: منذ 25 دقيقة",
                status = DataCenterSyncStatus.Idle,
            ),
            DataCenterSyncItemUi(
                id = ID_PLANNED,
                titleRes = R.string.data_center_card_planned_title,
                subtitleRes = R.string.data_center_card_planned_subtitle,
                lastUpdatedLabel = "آخر تحديث: اليوم 08:30 ص",
                status = DataCenterSyncStatus.Idle,
            ),
        )
    }
}
