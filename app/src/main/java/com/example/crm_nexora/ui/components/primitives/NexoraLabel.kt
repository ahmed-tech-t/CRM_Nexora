package com.example.crm_nexora.ui.components.primitives

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

@Composable
fun NexoraLabel(
    text: String,
    modifier: Modifier = Modifier,
    alignEnd: Boolean = false,
) {
    NexoraText(
        text = text,
        modifier = modifier.fillMaxWidth(),
        style = MaterialTheme.typography.labelMedium,
        color = NexoraTextSecondary,
        textAlign = if (alignEnd) TextAlign.End else TextAlign.Start,
    )
}

@Composable
fun NexoraSectionTitle(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    alignEnd: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = if (alignEnd) Alignment.End else Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXxs),
    ) {
        NexoraText(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = if (alignEnd) TextAlign.End else TextAlign.Start,
            modifier = Modifier.fillMaxWidth(),
        )
        if (!subtitle.isNullOrBlank()) {
            NexoraText(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = NexoraTextMuted,
                textAlign = if (alignEnd) TextAlign.End else TextAlign.Start,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun NexoraLabelPreview() {
    CRM_NexoraTheme {
        Column(verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceMd)) {
            NexoraLabel(text = "كود الشركة")
            NexoraSectionTitle(
                title = "اسم المكان والتخصص",
                subtitle = "تظهر القائمة بناءً على المنطقة والنوع المختارين",
            )
        }
    }
}
