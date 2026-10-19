package com.example.crm_nexora.ui.components.chrome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraIconButton
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraPrimary

@Composable
fun AppHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    showBack: Boolean = false,
    showMenu: Boolean = false,
    onBack: (() -> Unit)? = null,
    onMenu: (() -> Unit)? = null,
    trailing: (@Composable () -> Unit)? = null,
    containerColor: Color = NexoraNavy,
    contentColor: Color = Color.White,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(NexoraDimens.headerHeight)
            .background(containerColor)
            .padding(horizontal = NexoraDimens.spaceLg, vertical = NexoraDimens.spaceMd),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (showBack) {
                NexoraIconButton(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.cd_back),
                    onClick = { onBack?.invoke() },
                    tint = contentColor,
                )
            }
            if (showMenu) {
                NexoraIconButton(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(R.string.cd_menu),
                    onClick = { onMenu?.invoke() },
                    tint = contentColor,
                )
            }
            trailing?.invoke()
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.End,
        ) {
            NexoraText(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = contentColor,
                modifier = Modifier.fillMaxWidth(),
            )
            if (!subtitle.isNullOrBlank()) {
                Spacer(Modifier.height(NexoraDimens.spaceXxs))
                NexoraText(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = contentColor.copy(alpha = 0.8f),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

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
private fun AppHeaderPreview() {
    CRM_NexoraTheme {
        Column {
            AppHeader(title = "حجز زيارة", subtitle = "الخطوة 1", showBack = true, onBack = {})
            Spacer(Modifier.height(8.dp))
            BrandMark()
        }
    }
}
