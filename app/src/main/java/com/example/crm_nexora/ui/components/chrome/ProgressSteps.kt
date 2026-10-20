package com.example.crm_nexora.ui.components.chrome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraSuccess
import com.example.crm_nexora.ui.theme.NexoraTextFaint

/**
 * Controlled stepper — [currentIndex] owned by parent/ViewModel.
 * Figma: past ✓ green, current ● primary, future label only (no bullet).
 */
@Composable
fun ProgressSteps(
    steps: List<String>,
    currentIndex: Int,
    modifier: Modifier = Modifier,
) {
    require(steps.isNotEmpty()) { "steps must not be empty" }
    val safeIndex = currentIndex.coerceIn(0, steps.lastIndex)
    val labelStyle = MaterialTheme.typography.labelMedium.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 16.5.sp,
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding( vertical = NexoraDimens.spaceSm),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        steps.forEachIndexed { index, label ->
            val (text, color) = when {
                index < safeIndex -> "✓ $label" to NexoraSuccess
                index == safeIndex -> "● $label" to NexoraPrimary
                else -> label to NexoraTextFaint
            }
            NexoraText(
                text = text,
                style = labelStyle,
                color = color,
            )
            if (index != steps.lastIndex) {
                NexoraText(
                    modifier = Modifier.alpha(.2f),
                    text = "  ──  ",
                    style = labelStyle,
                    color = NexoraTextFaint,
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

@Preview(showBackground = true, locale = "ar")
@Composable
private fun ProgressStepsMidPreview() {
    CRM_NexoraTheme {
        ProgressSteps(
            steps = listOf(
                stringResource(R.string.step_entity_data),
                stringResource(R.string.step_gifts_products),
                stringResource(R.string.step_result_finish),
            ),
            currentIndex = 1,
        )
    }
}
