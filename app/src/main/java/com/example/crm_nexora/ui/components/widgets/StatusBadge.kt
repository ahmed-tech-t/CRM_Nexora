package com.example.crm_nexora.ui.components.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.models.ConnectionStatusUiModel
import com.example.crm_nexora.ui.models.StatusTone
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDanger
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraNavy
import com.example.crm_nexora.ui.theme.NexoraSuccess
import com.example.crm_nexora.ui.theme.NexoraSurface
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraWarning

@Composable
fun StatusBadge(
    text: String,
    modifier: Modifier = Modifier,
    tone: StatusTone = StatusTone.Neutral,
    showDot: Boolean = false,
) {
    val (bg, fg) = when (tone) {
        StatusTone.Success -> NexoraSuccess.copy(alpha = 0.15f) to NexoraSuccess
        StatusTone.Warning -> NexoraWarning.copy(alpha = 0.18f) to NexoraWarning
        StatusTone.Danger -> NexoraDanger.copy(alpha = 0.15f) to NexoraDanger
        StatusTone.Neutral -> NexoraSurfaceChip to NexoraTextMuted
    }
    Row(
        modifier = modifier
            .background(bg, RoundedCornerShape(NexoraDimens.radiusXl))
            .border(
                width = if (tone == StatusTone.Neutral) NexoraDimens.borderThin else 0.dp,
                color = if (tone == StatusTone.Neutral) NexoraBorder else Color.Transparent,
                shape = RoundedCornerShape(NexoraDimens.radiusXl),
            )
            .padding(horizontal = 8.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        // Dot at start (right in RTL), then text
        if (showDot) {
            Box(
                modifier = Modifier
                    .size(6.dp)
                    .background(fg, CircleShape),
            )
        }
        NexoraText(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 9.sp,
                lineHeight = 13.5.sp,
            ),
            color = fg,
            textAlign = TextAlign.Start,
        )
    }
}

