package com.example.crm_nexora.ui.screens.unplannedvisit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorderFocus
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSurfaceFocus

@Composable
fun ClassBadge(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .border(
                NexoraDimens.borderThin,
                NexoraBorderFocus,
                RoundedCornerShape(NexoraDimens.radiusXs),
            )
            .background(NexoraSurfaceFocus, RoundedCornerShape(NexoraDimens.radiusXs))
            .padding(horizontal = NexoraDimens.spaceSm, vertical = NexoraDimens.borderFocus),
    ) {
        NexoraText(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
            ),
            color = NexoraPrimary,
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun ClassBadgePreview() {
    CRM_NexoraTheme {
        ClassBadge(
            text = "Class A",
            modifier = Modifier.padding(NexoraDimens.spaceLg),
        )
    }
}
