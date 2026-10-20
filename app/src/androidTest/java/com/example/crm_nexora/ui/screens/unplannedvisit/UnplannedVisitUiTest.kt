package com.example.crm_nexora.ui.screens.unplannedvisit

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
import com.example.crm_nexora.ui.screens.unplannedvisit.components.UnplannedVisitUi
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitEvents
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitStep
import com.example.crm_nexora.ui.screens.unplannedvisit.main.UnplannedVisitViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UnplannedVisitUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun unplannedVisit_showsTitleAndEntityStep() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val title = context.getString(R.string.unplanned_visit_title)
        val entityStep = context.getString(R.string.step_entity_data)

        composeRule.setContent {
            CRM_NexoraTheme {
                UnplannedVisitUi(
                    state = UnplannedVisitViewModel.initialState(),
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(title).assertIsDisplayed()
        composeRule.onNodeWithText(entityStep, substring = true).assertIsDisplayed()
    }

    @Test
    fun unplannedVisit_next_showsGiftsSection() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val next = context.getString(R.string.action_next)
        val gifts = context.getString(R.string.unplanned_section_gifts)
        val initial = UnplannedVisitViewModel.initialState()

        composeRule.setContent {
            var state by remember { mutableStateOf(initial) }
            CRM_NexoraTheme {
                UnplannedVisitUi(
                    state = state,
                    onEvent = { event ->
                        if (event is UnplannedVisitEvents.Next) {
                            state = state.copy(currentStep = UnplannedVisitStep.GiftsProducts)
                        }
                    },
                )
            }
        }

        composeRule.onNodeWithText(next).performClick()
        composeRule.onNodeWithText(gifts).assertIsDisplayed()
    }
}
