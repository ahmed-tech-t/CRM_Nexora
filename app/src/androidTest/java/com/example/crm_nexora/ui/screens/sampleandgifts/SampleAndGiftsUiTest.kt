package com.example.crm_nexora.ui.screens.sampleandgifts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.screens.sampleandgifts.components.SampleAndGiftsUi
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsEvents
import com.example.crm_nexora.ui.screens.sampleandgifts.main.SampleAndGiftsViewModel
import com.example.crm_nexora.ui.screens.sampleandgifts.models.SampleAndGiftsItemType
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SampleAndGiftsUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun sampleAndGifts_showsTitleAndSampleItem() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val title = context.getString(R.string.sample_and_gifts_title)
        val state = SampleAndGiftsViewModel.initialState()
        val firstSample = state.visibleItems.first()

        composeRule.setContent {
            CRM_NexoraTheme {
                SampleAndGiftsUi(
                    state = state,
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(title).assertIsDisplayed()
        composeRule.onNodeWithText(firstSample.name).assertIsDisplayed()
    }

    @Test
    fun sampleAndGifts_tabSwitch_showsGiftItem() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val initial = SampleAndGiftsViewModel.initialState()
        val giftsTab = context.getString(
            R.string.sample_and_gifts_tab_gifts_format,
            initial.giftCount,
        )
        val firstGift = initial.items.first { it.type == SampleAndGiftsItemType.Gift }

        composeRule.setContent {
            var state by remember { mutableStateOf(initial) }
            CRM_NexoraTheme {
                SampleAndGiftsUi(
                    state = state,
                    onEvent = { event ->
                        if (event is SampleAndGiftsEvents.SelectTab) {
                            state = state.copy(selectedTab = event.tab)
                        }
                    },
                )
            }
        }

        composeRule.onNodeWithText(giftsTab).performClick()
        composeRule.onNodeWithText(firstGift.name).assertIsDisplayed()
    }
}
