package com.example.crm_nexora.data.local.datastore.model

import com.example.crm_nexora.domain.utils.Constants.DEFAULT_LANG
import kotlinx.serialization.Serializable

@Serializable
data class AppSettingsDS(
    val language: String = DEFAULT_LANG,
)
