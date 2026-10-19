package com.example.crm_nexora.ui.screens.statics.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.components.primitives.NexoraCard
import com.example.crm_nexora.ui.components.primitives.NexoraIcon
import com.example.crm_nexora.ui.components.primitives.NexoraText
import com.example.crm_nexora.ui.screens.statics.models.StaticsAccountCategoryUi
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import com.example.crm_nexora.ui.theme.NexoraBorder
import com.example.crm_nexora.ui.theme.NexoraDimens
import com.example.crm_nexora.ui.theme.NexoraEndShiftBorder
import com.example.crm_nexora.ui.theme.NexoraSurfaceMuted
import com.example.crm_nexora.ui.theme.NexoraTextFaint
import com.example.crm_nexora.ui.theme.NexoraTextPrimary

@Composable
fun StaticsAccountDistributionCard(
    categories: List<StaticsAccountCategoryUi>,
    modifier: Modifier = Modifier,
) {
    NexoraCard(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(NexoraDimens.space10),
        ) {
            NexoraText(
                text = stringResource(R.string.statics_section_accounts),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                ),
                color = NexoraTextPrimary,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(NexoraDimens.spaceSm),
            ) {
                categories.forEach { category ->
                    StaticsAccountCategoryChip(
                        category = category,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Composable
private fun StaticsAccountCategoryChip(
    category: StaticsAccountCategoryUi,
    modifier: Modifier = Modifier,
) {
    val chipShape = RoundedCornerShape(NexoraDimens.radiusMd)
    Column(
        modifier = modifier
            .clip(chipShape)
            .background(NexoraSurfaceMuted)
            .border(NexoraDimens.borderThin, NexoraBorder, chipShape)
            .padding(vertical = NexoraDimens.spaceSm, horizontal = NexoraDimens.spaceXxs),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(NexoraDimens.spaceXxs),
    ) {
        NexoraIcon(
            painter = painterResource(category.iconRes),
            contentDescription = stringResource(category.labelRes),
            tint = NexoraEndShiftBorder,
            modifier = Modifier.size(
                width = NexoraDimens.iconStaticsWidth,
                height = NexoraDimens.iconStaticsHeight,
            ),
        )
        NexoraText(
            text = category.count.toString(),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 12.sp,
                lineHeight = 16.sp,
            ),
            color = NexoraTextPrimary,
        )
        NexoraText(
            text = stringResource(category.labelRes),
            style = MaterialTheme.typography.labelSmall.copy(
                fontSize = 9.sp,
                lineHeight = 13.5.sp,
            ),
            color = NexoraTextFaint,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, locale = "ar")
@Composable
private fun StaticsAccountDistributionCardPreview() {
    CRM_NexoraTheme {
        StaticsAccountDistributionCard(categories = StaticsAccountCategoryUi.samples)
    }
}
