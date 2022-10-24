package com.qxdzbc.pcr.screen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import com.qxdzbc.pcr.err.OtherErrors
import org.junit.Rule
import org.junit.Test

/**
 * This is a compose test, remember to turn on the device's screen, or else, the tests will crash
 */
class ErrDisplayKtTest{
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNoErrorDialog() {
        // Start the app
        composeTestRule.setContent {
            ErrDisplay(
                errorList = emptyList(),
                removeErr = {},
                testTag = "A"
            )
        }
        composeTestRule.onAllNodesWithTag("A").assertCountEquals(0)
    }

    @Test
    fun testErrorDialog() {
        // Start the app
        val l = listOf(
            OtherErrors.UnableToLogin.report(),
            OtherErrors.UnableToLogin.report(),
        )
        composeTestRule.setContent {
            ErrDisplay(
                errorList =l,
                removeErr = {},
                testTag = "A"
            )
        }
        composeTestRule.onAllNodesWithTag("A").assertCountEquals(l.size)
    }
}
