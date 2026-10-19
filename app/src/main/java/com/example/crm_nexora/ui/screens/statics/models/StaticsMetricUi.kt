package com.example.crm_nexora.ui.screens.statics.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.crm_nexora.R

@Immutable
sealed class StaticsMetricUi(
    @StringRes val labelRes: Int,
    open val value: Int,
) {
    @Immutable
    data class Today(override val value: Int) : StaticsMetricUi(
        labelRes = R.string.statics_metric_today,
        value = value,
    )

    @Immutable
    data class Completed(override val value: Int) : StaticsMetricUi(
        labelRes = R.string.statics_metric_completed,
        value = value,
    )

    @Immutable
    data class Unplanned(override val value: Int) : StaticsMetricUi(
        labelRes = R.string.statics_metric_unplanned,
        value = value,
    )

    companion object {
        val samples  by lazy {
            listOf(
                Today(value = 0),
                Completed(value = 0),
                Unplanned(value = 0),
            )
        }
    }
}
