package com.example.crm_nexora.ui.components.widgets.visit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.models.StatusTone
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraBorderFocus
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraStatusBlue
import com.example.crm_nexora.ui.theme.NexoraSuccess
import com.example.crm_nexora.ui.theme.NexoraSurface
import com.example.crm_nexora.ui.theme.NexoraSurfaceChip
import com.example.crm_nexora.ui.theme.NexoraSurfaceFocus
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextMuted
import com.example.crm_nexora.ui.theme.NexoraTextPrimary
import com.example.crm_nexora.ui.theme.NexoraTextSecondary

/** Shared surface chrome for visit cards (shadow + border + fill). */
internal fun Modifier.visitCardChrome(
    elevation: Dp,
    borderAlpha: Float = 1f,
    shadowAlpha: Float = 0.03f,
): Modifier {
    val shape = RoundedCornerShape(NexoraDimens.radiusLg)
    return this
        .fillMaxWidth()
        .shadow(
            elevation = elevation,
            shape = shape,
            ambientColor = Color.Black.copy(alpha = shadowAlpha),
            spotColor = Color.Black.copy(alpha = shadowAlpha),
        )
        .border(NexoraDimens.borderThin, NexoraBorder.copy(alpha = borderAlpha), shape)
        .background(NexoraSurface, shape)
}

internal fun Modifier.visitCardSurface(
    elevation: Dp,
    contentPadding: Dp,
    borderAlpha: Float = 1f,
    shadowAlpha: Float = 0.03f,
): Modifier = visitCardChrome(elevation, borderAlpha, shadowAlpha).padding(contentPadding)

@Composable
internal fun VisitStatusDot(tone: StatusTone, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(NexoraDimens.statusDot)
            .background(visitStatusDotColor(tone), CircleShape),
    )
}

@Composable
internal fun VisitDurationChip(label: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(NexoraSurfaceChip, RoundedCornerShape(NexoraDimens.radiusLg))
            .padding(horizontal = 10.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        NexoraIcon(
            painter = painterResource(R.drawable.ic_clock),
            contentDescription = stringResource(R.string.cd_clock),
            modifier = Modifier.size(10.dp),
            tint = NexoraTextSecondary,
        )
        NexoraText(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp,
                lineHeight = 16.sp,
            ),
            color = NexoraTextPrimary,
        )
    }
}

/** Bordered class chip (Planning header). */
@Composable
internal fun VisitClassChip(classCategory: String, modifier: Modifier = Modifier) {
    NexoraText(
        text = stringResource(R.string.class_chip_format, classCategory),
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp,
        ),
        color = NexoraStatusBlue,
        textAlign = TextAlign.Start,
        modifier = modifier
            .border(NexoraDimens.borderThin, NexoraBorderFocus, RoundedCornerShape(4.dp))
            .background(NexoraSurfaceFocus, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp),
    )
}

/** Soft tag chip (Unplanned account/specialty). */
@Composable
internal fun VisitTagChip(text: String, modifier: Modifier = Modifier) {
    NexoraText(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.SemiBold,
            fontSize = 9.sp,
            lineHeight = 13.5.sp,
        ),
        color = NexoraTextMuted,
        modifier = modifier
            .background(NexoraSurfaceChip, RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp),
    )
}

/** Specialty + class line under Planned title. */
@Composable
internal fun VisitSpecialtyClassLine(
    specialty: String,
    classCategory: String,
    modifier: Modifier = Modifier,
) {
    if (specialty.isBlank() && classCategory.isBlank()) return
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (specialty.isNotBlank()) {
            NexoraText(
                text = if (classCategory.isNotBlank()) "$specialty • " else specialty,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 9.sp,
                    lineHeight = 16.5.sp,
                ),
                color = NexoraTextFaint,
            )
        }
        if (classCategory.isNotBlank()) {
            NexoraText(
                text = stringResource(R.string.class_only_format, classCategory),
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp,
                    lineHeight = 16.5.sp,
                ),
                color = NexoraTextSecondary,
            )
        }
    }
}

internal fun visitStatusDotColor(tone: StatusTone): Color = when (tone) {
    StatusTone.Success -> NexoraSuccess
    StatusTone.Warning -> NexoraSuccess
    StatusTone.Danger -> Color(0xFFDC2626)
    StatusTone.Neutral -> NexoraTextFaint
}
