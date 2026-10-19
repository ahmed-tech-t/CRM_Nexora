package com.example.crm_nexora.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme

@Composable
fun PlaceholderScreen(
    title: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        NexoraText(
            text = "$title — ${stringResource(R.string.placeholder_coming_soon)}",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun PlaceholderScreenPreview() {
    CRM_NexoraTheme {
        PlaceholderScreen(title = stringResource(R.string.nav_stats))
    }
}
