package com.example.crm_nexora.ui.components

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.crm_nexora.ui.components.primitives.NexoraButton
import com.example.crm_nexora.ui.theme.CRM_NexoraTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NexoraButtonUiTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun primaryButton_showsLabel_andHandlesClick() {
        var clicked = false
        composeRule.setContent {
            CRM_NexoraTheme {
                NexoraButton(text = "تسجيل الدخول", onClick = { clicked = true })
            }
        }

        composeRule.onNodeWithText("تسجيل الدخول").assertIsDisplayed().performClick()
        assertTrue(clicked)
    }
}
