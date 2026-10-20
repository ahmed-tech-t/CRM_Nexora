package com.example.crm_nexora.ui.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.components.primitives.NexoraTextField
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSurface
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

@Immutable
data class SearchableOptionUi(
    val id: String,
    val label: String,
)

@Composable
fun NexoraSearchableDropdown(
    state: TextFieldState,
    options: List<SearchableOptionUi>,
    selectedId: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    readOnly: Boolean = false,
    containerColor: Color = NexoraSurface,
    shape: RoundedCornerShape = RoundedCornerShape(NexoraDimens.spaceSection),
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    midTrailing: (@Composable () -> Unit)? = null,
    showDividers: Boolean = true,
    showDefaultLeading: Boolean = true,
    showDefaultTrailing: Boolean = true,
) {
    var expanded by remember { mutableStateOf(false) }
    val resolvedLeading: (@Composable () -> Unit)? = leadingIcon
        ?: if (showDefaultLeading) {
            {
                NexoraIcon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = NexoraTextFaint,
                    modifier = Modifier.size(NexoraDimens.iconSizeMd),
                )
            }
        } else {
            null
        }
    val resolvedTrailing: (@Composable () -> Unit)? = trailingIcon
        ?: if (showDefaultTrailing) {
            {
                NexoraIcon(
                    imageVector = Icons.Outlined.KeyboardArrowDown,
                    contentDescription = null,
                    tint = NexoraTextMuted,
                    modifier = Modifier.size(NexoraDimens.iconSizeXl),
                )
            }
        } else {
            null
        }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXs),
    ) {
        if (label.isNotBlank()) {
            NexoraText(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                ),
                color = NexoraTextMuted,
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(containerColor, shape)
                    .border(NexoraDimens.borderThin, NexoraBorder, shape)
                    .clickable {
                        expanded = !expanded
                    }
                    .padding(
                        horizontal = NexoraDimens.spaceMd,
                        vertical = NexoraDimens.spaceMd,
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm),
            ) {
                if (resolvedLeading != null) {
                    resolvedLeading()
                }
                NexoraTextField(
                    state = state,
                    modifier = Modifier.weight(1f),
                    placeholder = placeholder,
                    readOnly = readOnly,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = NexoraTextPrimary,
                    ),
                )
                if (midTrailing != null) {
                    midTrailing()
                }
                if (resolvedTrailing != null) {
                    Box(modifier = Modifier.clickable { expanded = !expanded }) {
                        resolvedTrailing()
                    }
                }
            }

            if (expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = NexoraDimens.spaceXxs)
                        .background(NexoraSurface, shape)
                        .border(NexoraDimens.borderThin, NexoraBorder, shape)
                        .padding(vertical = NexoraDimens.spaceXxs),
                ) {
                    if (options.isEmpty()) {
                        NexoraText(
                            text = "—",
                            style = MaterialTheme.typography.labelMedium,
                            color = NexoraTextMuted,
                            modifier = Modifier.padding(
                                horizontal = NexoraDimens.spaceMd,
                                vertical = NexoraDimens.spaceSm,
                            ),
                        )
                    } else {
                        options.forEach { option ->
                            val selected = option.id == selectedId
                            NexoraText(
                                text = option.label,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold,
                                ),
                                color = if (selected) NexoraTextPrimary else NexoraTextSecondary,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        state.setTextAndPlaceCursorAtEnd(option.label)
                                        onSelect(option.id)
                                        expanded = false
                                    }
                                    .padding(
                                        horizontal = NexoraDimens.spaceMd,
                                        vertical = NexoraDimens.spaceSm,
                                    ),
                            )
                            if (option != options.last()) {
                                HorizontalDivider(
                                    color = NexoraBorder,
                                    thickness = NexoraDimens.borderThin,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FieldDivider() {
    Box(
        modifier = Modifier
            .width(NexoraDimens.borderThin)
            .height(NexoraDimens.iconSizeLg)
            .background(NexoraBorder),
    )
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun NexoraSearchableDropdownSheetPreview() {
    CRM_NexoraTheme {
        NexoraSearchableDropdown(
            state = rememberTextFieldState("مج حراري + قلم معدني بشعار الشركة"),
            options = listOf(
                SearchableOptionUi("1", "مج حراري + قلم معدني بشعار الشركة"),
                SearchableOptionUi("2", "دفتر ملاحظات جلد فاخر"),
            ),
            selectedId = "1",
            onSelect = {},
            placeholder = "ابحث…",
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun NexoraSearchableDropdownEntityPreview() {
    CRM_NexoraTheme {
        NexoraSearchableDropdown(
            state = rememberTextFieldState("عيادة خاصة"),
            options = listOf(
                SearchableOptionUi("عيادة خاصة", "عيادة خاصة"),
                SearchableOptionUi("مستشفى", "مستشفى"),
            ),
            selectedId = "عيادة خاصة",
            onSelect = {},
            label = "اختر نوع الجهة",
            readOnly = true,
            containerColor = NexoraSurfaceChip,
            shape = RoundedCornerShape(NexoraDimens.radiusMd),
            showDividers = false,
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
