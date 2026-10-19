package com.example.crm_nexora.ui.components.chrome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSuccess
import com.example.crm_nexora.ui.theme.NexoraTextMuted

/**
 * Controlled stepper — [currentIndex] owned by parent/ViewModel.
 */
@Composable
fun ProgressSteps(
    steps: List<String>,
    currentIndex: Int,
    modifier: Modifier = Modifier,
) {
    require(steps.isNotEmpty()) { "steps must not be empty" }
    val safeIndex = currentIndex.coerceIn(0, steps.lastIndex)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = NexoraDimens.spaceLg, vertical = NexoraDimens.spaceSm),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        steps.forEachIndexed { index, label ->
            val color = when {
                index < safeIndex -> NexoraSuccess
                index == safeIndex -> NexoraPrimary
                else -> NexoraTextMuted
            }
            val prefix = when {
                index < safeIndex -> "✓ "
                else -> "● "
            }
            NexoraText(
                text = prefix + label,
                style = MaterialTheme.typography.labelMedium,
                color = color,
            )
            if (index != steps.lastIndex) {
                NexoraText(
                    text = "  ──  ",
                    style = MaterialTheme.typography.labelMedium,
                    color = NexoraTextMuted,
                )
            }
        }
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun ProgressStepsPreview() {
    CRM_NexoraTheme {
        ProgressSteps(
            steps = listOf(
                stringResource(R.string.step_entity_data),
                stringResource(R.string.step_gifts_products),
                stringResource(R.string.step_result_finish),
            ),
            currentIndex = 0,
        )
    }
}
