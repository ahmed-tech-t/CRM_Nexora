package com.example.crm_nexora.ui.screens.datacenter

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.preview.DataCenterSamples
import com.example.crm_nexora.ui.screens.datacenter.models.DataCenterSyncStatus
import com.example.crm_nexora.ui.screens.datacenter.components.DataCenterUi
import com.example.crm_nexora.ui.screens.datacenter.main.DataCenterViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataCenterUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun dataCenter_showsTitleAndFirstCard() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val title = context.getString(R.string.data_center_title)
        val fullTitle = context.getString(R.string.data_center_card_full_title)

        composeRule.setContent {
            CRM_NexoraTheme {
                DataCenterUi(
                    state = DataCenterViewModel.initialState(),
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(title).assertIsDisplayed()
        composeRule.onNodeWithText(fullTitle).assertIsDisplayed()
    }

    @Test
    fun dataCenter_syncingCard_showsLastUpdated() {
        val syncingState = DataCenterViewModel.initialState().copy(
            items = DataCenterSamples.items().mapIndexed { index, item ->
                if (index == 0) item.copy(status = DataCenterSyncStatus.Syncing) else item
            },
        )

        composeRule.setContent {
            CRM_NexoraTheme {
                DataCenterUi(
                    state = syncingState,
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(DataCenterSamples.items().first().lastUpdatedLabel)
            .assertIsDisplayed()
    }
}
