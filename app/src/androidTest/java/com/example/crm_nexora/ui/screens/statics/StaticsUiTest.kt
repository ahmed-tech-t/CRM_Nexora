package com.example.crm_nexora.ui.screens.statics

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.screens.statics.components.StaticsUi
import com.example.crm_nexora.ui.screens.statics.main.StaticsViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StaticsUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun statics_showsTitleAndTodayMetric() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val title = context.getString(R.string.statics_title)
        val today = context.getString(R.string.statics_metric_today)

        composeRule.setContent {
            CRM_NexoraTheme {
                StaticsUi(
                    state = StaticsViewModel.initialState(),
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(title).assertIsDisplayed()
        composeRule.onNodeWithText(today).assertIsDisplayed()
        composeRule.onNodeWithText("26").assertIsDisplayed()
    }
}
