package com.example.crm_nexora.ui.catalog

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComponentCatalogSmokeTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun catalog_rendersSmokeTitle() {
        composeRule.setContent {
            CRM_NexoraTheme {
                ComponentCatalogScreen()
            }
        }
        composeRule.onNodeWithText("Nexora Shared Components").assertIsDisplayed()
    }
}
