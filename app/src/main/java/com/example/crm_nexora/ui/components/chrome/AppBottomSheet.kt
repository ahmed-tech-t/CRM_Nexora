package com.example.crm_nexora.ui.components.chrome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.crm_nexora.ui.theme.NexoraDimens


/**
 * Controlled bottom sheet — [sheetState] provided by parent/ViewModel host.
 * Parent owns visibility by composing this only when open.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = NexoraDimens.radiusSheet,
            topEnd = NexoraDimens.radiusSheet,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(NexoraDimens.spaceLg),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd),
            content = content,
        )
    }
}

