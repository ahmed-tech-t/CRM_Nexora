package com.example.crm_nexora.data.mapper

import com.example.crm_nexora.data.local.datastore.model.AppSettingsDS
import com.example.crm_nexora.domain.models.local.AppSettings

fun AppSettingsDS.toAppSettings(): AppSettings{
    return AppSettings(
        language = language,

    )
}