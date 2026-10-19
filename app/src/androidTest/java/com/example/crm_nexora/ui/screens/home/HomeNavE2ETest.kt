package com.example.crm_nexora.ui.screens.home

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.crm_nexora.MainActivity
import com.example.crm_nexora.R
import com.example.crm_nexora.ui.preview.HomeSamples
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Light smoke: MainActivity NavHost starts on Home; menu opens nav drawer.
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeNavE2ETest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun mainActivity_startsOnHome_withSampleUser() {
        composeRule.onNodeWithText(HomeSamples.user().displayName).assertIsDisplayed()
    }

    @Test
    fun mainActivity_menuOpensDrawer_showsPlanning() {
        val menuCd = composeRule.activity.getString(R.string.cd_menu)
        val planning = composeRule.activity.getString(R.string.drawer_planning)

        composeRule.onNodeWithContentDescription(menuCd).performClick()
        composeRule.onNodeWithText(planning).assertIsDisplayed()
    }

    @Test
    fun mainActivity_infoCenterOpensDataCenter() {
        val menuCd = composeRule.activity.getString(R.string.cd_menu)
        val infoCenter = composeRule.activity.getString(R.string.drawer_info_center)
        val dataCenterTitle = composeRule.activity.getString(R.string.data_center_title)

        composeRule.onNodeWithContentDescription(menuCd).performClick()
        composeRule.onNodeWithText(infoCenter).performClick()
        composeRule.onNodeWithText(dataCenterTitle).assertIsDisplayed()
    }

    @Test
    fun mainActivity_staticsTab_showsStaticsTitle() {
        val staticsTab = composeRule.activity.getString(R.string.nav_statics)
        val staticsTitle = composeRule.activity.getString(R.string.statics_title)

        composeRule.onNodeWithText(staticsTab).performClick()
        composeRule.onNodeWithText(staticsTitle).assertIsDisplayed()
    }

    @Test
    fun mainActivity_custodyTab_showsSampleAndGiftsTitle() {
        val custodyTab = composeRule.activity.getString(R.string.nav_custody)
        val title = composeRule.activity.getString(R.string.sample_and_gifts_title)

        composeRule.onNodeWithText(custodyTab).performClick()
        composeRule.onNodeWithText(title).assertIsDisplayed()
    }
}
