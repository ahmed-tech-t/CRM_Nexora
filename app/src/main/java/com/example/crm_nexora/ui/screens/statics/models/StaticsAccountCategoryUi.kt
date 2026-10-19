package com.example.crm_nexora.ui.screens.statics.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.example.crm_nexora.R

@Immutable
sealed class StaticsAccountCategoryUi(
    @StringRes val labelRes: Int,
    @DrawableRes val iconRes: Int,
    open val count: Int,
) {
    @Immutable
    data class Clinic(override val count: Int) : StaticsAccountCategoryUi(
        labelRes = R.string.statics_account_clinic,
        iconRes = R.drawable.ic_statics_clinic,
        count = count,
    )

    @Immutable
    data class Pharmacy(override val count: Int) : StaticsAccountCategoryUi(
        labelRes = R.string.statics_account_pharmacy,
        iconRes = R.drawable.ic_statics_pharmacy,
        count = count,
    )

    @Immutable
    data class Hospital(override val count: Int) : StaticsAccountCategoryUi(
        labelRes = R.string.statics_account_hospital,
        iconRes = R.drawable.ic_statics_hospital,
        count = count,
    )

    @Immutable
    data class HealthUnit(override val count: Int) : StaticsAccountCategoryUi(
        labelRes = R.string.statics_account_health_unit,
        iconRes = R.drawable.ic_statics_health_unit,
        count = count,
    )

    companion object {
        val samples: List<StaticsAccountCategoryUi> by lazy {
            listOf(
                Clinic(count = 62),
                Pharmacy(count = 18),
                Hospital(count = 9),
                HealthUnit(count = 5),
            )
        }
    }
}
