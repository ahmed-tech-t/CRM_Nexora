package com.example.crm_nexora.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crm_nexora.R
import com.example.crm_nexora.domain.models.VisitPrimaryAction
import com.example.crm_nexora.ui.components.widgets.visit.PlannedVisitCard
import com.example.crm_nexora.ui.components.widgets.visit.PlanningVisitCard
import com.example.crm_nexora.ui.mapper.toUi
import com.example.crm_nexora.ui.preview.VisitSamples
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VisitCardUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun planned_rendersPlaceNameTypeAndContinue() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val typeLabel = context.getString(R.string.planned)
        val actionLabel = context.getString(VisitPrimaryAction.Continue.labelRes)

        composeRule.setContent {
            CRM_NexoraTheme {
                PlannedVisitCard(
                    model = VisitSamples.planned(
                        placeName = "عيادة تجريبية",
                        durationLabel = null,
                    ).toUi(),
                    onPrimaryAction = {},
                )
            }
        }

        composeRule.onNodeWithText("عيادة تجريبية").assertIsDisplayed()
        composeRule.onNodeWithText(typeLabel).assertIsDisplayed()
        composeRule.onNodeWithText(actionLabel).assertIsDisplayed()
    }

    @Test
    fun unplanned_rendersNameAndStart() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val start = context.getString(VisitPrimaryAction.Start.labelRes)

        composeRule.setContent {
            CRM_NexoraTheme {
                PlannedVisitCard(
                    model = VisitSamples.unplanned(
                        placeName = "د. هاني",
                        account = "عيادة باطنة",
                    ).toUi(),
                    onAction = {},
                )
            }
        }

        composeRule.onNodeWithText("د. هاني").assertIsDisplayed()
        composeRule.onNodeWithText(start).assertIsDisplayed()
    }

    @Test
    fun planning_rendersPlaceName() {
        composeRule.setContent {
            CRM_NexoraTheme {
                PlanningVisitCard(
                    model = VisitSamples.planning(placeName = "عيادة خاصة").toUi(),
                    onVisitModeChange = {},
                )
            }
        }

        composeRule.onNodeWithText("عيادة خاصة").assertIsDisplayed()
    }
}
