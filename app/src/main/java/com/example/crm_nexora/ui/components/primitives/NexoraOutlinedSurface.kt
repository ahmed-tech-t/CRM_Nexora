package com.example.crm_nexora.ui.components.primitives

import androidx.compose.ui.Modifier

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraSurface

@Composable
fun NexoraOutlinedSurface(
    modifier: Modifier = Modifier,
    elevated: Boolean = false,
    containerColor: Color = NexoraSurface,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(NexoraDimens.radiusMd),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        border = BorderStroke(NexoraDimens.borderThin, NexoraBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = if (elevated) 4.dp else 0.dp),
    ) {
        Column(
            modifier = Modifier.padding(NexoraDimens.spaceMd),
            content = content,
        )
    }
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
