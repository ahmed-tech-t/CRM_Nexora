package com.example.crm_nexora.ui.components.primitives

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.VisitPrimaryAction
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

enum class NexoraButtonVariant {
    Primary,
    Navy,
    Secondary,
    /** Filled chip surface — cancel / low-emphasis actions. */
    Muted,
    Text,
}

/**
 * Controlled button — label/enabled/clicks come from parent/ViewModel.
 */
@Composable
fun NexoraButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: NexoraButtonVariant = NexoraButtonVariant.Primary,
    enabled: Boolean = true,
    fillMaxWidth: Boolean = true,
    compact: Boolean = false,
    leadingIcon: (@Composable () -> Unit)? = null,
) {
    val shape = RoundedCornerShape(NexoraDimens.radiusMd)
    val widthModifier = if (fillMaxWidth) modifier.fillMaxWidth() else modifier
    val minHeight = if (compact) NexoraDimens.buttonCompactHeight else NexoraDimens.buttonMinHeight
    val minSize = Modifier.defaultMinSize(minHeight = minHeight)
    val contentPadding = if (compact) {
        PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    } else {
        PaddingValues(horizontal = 16.dp, vertical = 14.dp)
    }
    val labelStyle = if (compact) {
        MaterialTheme.typography.labelMedium
    } else {
        MaterialTheme.typography.labelLarge
    }

    when (variant) {
        NexoraButtonVariant.Primary -> Button(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier.then(minSize),
            shape = shape,
            contentPadding = contentPadding,
            colors = ButtonDefaults.buttonColors(
                containerColor = NexoraPrimary,
                contentColor = Color.White,
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        ) {
            NexoraButtonLabel(text = text, style = labelStyle, leadingIcon = leadingIcon)
        }

        NexoraButtonVariant.Navy -> Button(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier.then(minSize),
            shape = RoundedCornerShape(NexoraDimens.radiusLg),
            contentPadding = contentPadding,
            colors = ButtonDefaults.buttonColors(
                containerColor = NexoraNavy,
                contentColor = Color.White,
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        ) {
            NexoraButtonLabel(text = text, style = labelStyle, leadingIcon = leadingIcon)
        }

        NexoraButtonVariant.Secondary -> OutlinedButton(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier.then(minSize),
            shape = shape,
            contentPadding = contentPadding,
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = NexoraSurfaceMuted,
                contentColor = NexoraTextPrimary,
            ),
        ) {
            NexoraButtonLabel(text = text, style = labelStyle, leadingIcon = leadingIcon)
        }

        NexoraButtonVariant.Muted -> Button(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier.then(minSize),
            shape = shape,
            contentPadding = contentPadding,
            colors = ButtonDefaults.buttonColors(
                containerColor = NexoraSurfaceChip,
                contentColor = NexoraTextSecondary,
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
        ) {
            NexoraButtonLabel(
                text = text,
                style = labelStyle,
                color = NexoraTextSecondary,
                leadingIcon = leadingIcon,
            )
        }

        NexoraButtonVariant.Text -> TextButton(
            onClick = onClick,
            enabled = enabled,
            modifier = widthModifier,
            contentPadding = contentPadding,
        ) {
            NexoraButtonLabel(
                text = text,
                style = labelStyle,
                color = NexoraPrimary,
                leadingIcon = leadingIcon,
            )
        }
    }
}

@Composable
private fun NexoraButtonLabel(
    text: String,
    style: TextStyle,
    color: Color = Color.Unspecified,
    leadingIcon: (@Composable () -> Unit)?,
) {
    if (leadingIcon == null) {
        NexoraText(text = text, style = style, color = color)
        return
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
    ) {
        leadingIcon()
        NexoraText(text = text, style = style, color = color)
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun NexoraButtonPreview() {
    CRM_NexoraTheme {
        Column() {
            NexoraButton(text = "تسجيل الدخول", onClick = {})
            NexoraButton(
                text = stringResource(VisitPrimaryAction.Start.labelRes),
                onClick = {},
                fillMaxWidth = false,
                compact = true,
                leadingIcon = {
                    NexoraIcon(
                        painter = painterResource(R.drawable.ic_arrow_visit),
                        contentDescription = null,
                        modifier = Modifier.size(11.dp).offset(y = 1.dp),
                        tint = Color.White,
                    )
                },
            )
        }

    }
}
