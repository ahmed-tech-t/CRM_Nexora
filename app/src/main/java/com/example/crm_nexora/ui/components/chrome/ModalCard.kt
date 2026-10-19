package com.example.crm_nexora.ui.components.chrome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.components.primitives.NexoraButtonVariant
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens

/**
 * Controlled dialog — visibility decided by parent (compose or not).
 * Actions/labels come from params; no business state inside.
 */
@Composable
fun ModalCard(
    title: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    confirmText: String? = null,
    dismissText: String? = null,
    onConfirm: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val resolvedDismiss = dismissText ?: stringResource(R.string.action_close)
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = modifier.fillMaxWidth(),
            shape = RoundedCornerShape(NexoraDimens.radiusLg),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = NexoraDimens.spaceSm,
        ) {
            Column(
                modifier = Modifier.padding(NexoraDimens.spaceLg),
                verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
            ) {
                NexoraText(text = title, style = MaterialTheme.typography.titleMedium)
                content()
                if (confirmText != null && onConfirm != null) {
                    NexoraButton(text = confirmText, onClick = onConfirm)
                }
                NexoraButton(
                    text = resolvedDismiss,
                    onClick = onDismiss,
                    variant = NexoraButtonVariant.Secondary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalCardPreview() {
    CRM_NexoraTheme {
        ModalCard(
            title = "Confirm Deletion",
            onDismiss = {},
            confirmText = "Delete",
            dismissText = "Cancel",
            onConfirm = {}
        ) {
            NexoraText(text = "Are you sure you want to delete this item? This action cannot be undone.")
        }
    }
}