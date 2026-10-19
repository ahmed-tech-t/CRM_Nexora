package com.example.crm_nexora.ui.components.primitives

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSurface

@Composable
fun NexoraOutlinedSurface(
    modifier: Modifier = Modifier,
    @Suppress("UNUSED_PARAMETER") elevated: Boolean = false,
    containerColor: Color = NexoraSurface,
    borderColor: Color = NexoraBorder,
    content: @Composable ColumnScope.() -> Unit,
) {
    NexoraCard(
        modifier = modifier,
        borderColor = borderColor,
        containerColor = containerColor,
        contentPadding = PaddingValues(NexoraDimens.spaceMd),
        content = content,
    )
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun NexoraOutlinedSurfacePreview() {
    CRM_NexoraTheme {
        NexoraOutlinedSurface(elevated = true) {
            NexoraText("بطاقة مشتركة")
        }
    }
}
