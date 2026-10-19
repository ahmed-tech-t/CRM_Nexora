package com.example.crm_nexora.ui.components.chrome

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraPrimary
import com.example.crm_nexora.ui.theme.NexoraOnPrimary

/**
 * App-wide snackbar look. Always use via [NexoraSnackbarHost].
 */
@Composable
fun SnackBarComponent(
    data: SnackbarData,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier.padding(
            horizontal = NexoraDimens.spaceMd,
            vertical = NexoraDimens.spaceSm,
        ),
    ) {
        Snackbar(
            shape = RoundedCornerShape(10.dp),
            containerColor = NexoraPrimary,
            dismissAction = {
                IconButton(
                    onClick = data::dismiss,
                    modifier = Modifier
                        .padding(end = NexoraDimens.spaceMd)
                        .size(20.dp),
                ) {
                    NexoraIcon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = stringResource(R.string.action_close),
                        tint = NexoraOnPrimary,
                        modifier = Modifier.size(16.dp),
                    )
                }
            },
        ) {
            NexoraText(
                text = data.visuals.message,
                modifier = modifier,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Start,
                ),
                color = NexoraOnPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

/** SnackbarHost that always renders [SnackBarComponent]. */
@Composable
fun NexoraSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    SnackbarHost(
        hostState = hostState,
        modifier = modifier,
    ) { data ->
        SnackBarComponent(data = data)
    }
}

@Preview(showBackground = true)
@Composable
private fun SnackBarComponentPreview() {
    val sampleData = object : SnackbarData {
        override val visuals: SnackbarVisuals = object : SnackbarVisuals {
            override val message: String = "تمت المزامنة بنجاح"
            override val actionLabel: String? = null
            override val withDismissAction: Boolean = true
            override val duration: SnackbarDuration = SnackbarDuration.Short
        }

        override fun dismiss() = Unit
        override fun performAction() = Unit
    }
    CRM_NexoraTheme {
        SnackBarComponent(data = sampleData)
    }
}
