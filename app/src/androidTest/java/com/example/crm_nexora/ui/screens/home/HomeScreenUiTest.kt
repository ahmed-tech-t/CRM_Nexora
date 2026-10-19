package com.example.crm_nexora.ui.screens.home

import androidx.compose.ui.test.assertDoesNotExist
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.preview.HomeSamples
import com.example.crm_nexora.ui.screens.home.main.HomeUi
import com.example.crm_nexora.ui.screens.home.main.HomeViewModel
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun home_showsUserName_andTodayVisitsSection() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val todayTitle = context.getString(R.string.home_today_visits)

        composeRule.setContent {
            CRM_NexoraTheme {
                HomeUi(
                    state = HomeViewModel.initialState(),
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(HomeSamples.user().displayName).assertIsDisplayed()
        composeRule.onNodeWithText(todayTitle).assertIsDisplayed()
        composeRule.onNodeWithText("د. هاني المسيري").assertIsDisplayed()
    }

    @Test
    fun home_hidesActiveVisit_whenNull() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val inProgress = context.getString(R.string.visit_status_in_progress)
        val base = HomeViewModel.initialState()

        composeRule.setContent {
            CRM_NexoraTheme {
                HomeUi(
                    state = base.copy(activeVisit = null),
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(inProgress).assertDoesNotExist()
        composeRule.onNodeWithText(HomeSamples.user().displayName).assertIsDisplayed()
    }

    @Test
    fun home_showsActiveVisit_whenPresent() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val inProgress = context.getString(R.string.visit_status_in_progress)

        composeRule.setContent {
            CRM_NexoraTheme {
                HomeUi(
                    state = HomeViewModel.initialState(),
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(inProgress).assertIsDisplayed()
        composeRule.onNodeWithText("عيادة د. محمود عبد الرحمن").assertIsDisplayed()
    }

    @Test
    fun home_drawerOpen_showsPlanningItem() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val planning = context.getString(R.string.drawer_planning)

        composeRule.setContent {
            CRM_NexoraTheme {
                HomeUi(
                    state = HomeViewModel.initialState().copy(isDrawerOpen = true),
                    onEvent = {},
                )
            }
        }

        composeRule.onNodeWithText(planning).assertIsDisplayed()
        composeRule.onNodeWithText(HomeSamples.user().displayName).assertIsDisplayed()
    }

}
