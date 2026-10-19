package com.example.crm_nexora.ui.components.chrome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary


@Composable
fun BrandMark(
    text: String = "Rx",
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(80.dp)
            .background(NexoraPrimary, RoundedCornerShape(NexoraDimens.radiusXl)),
        contentAlignment = Alignment.Center,
    ) {
        NexoraText(
            text = text,
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White,
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun BrandMarkPreview() {
    CRM_NexoraTheme {
        Column {
            BrandMark()
        }
    }
}
