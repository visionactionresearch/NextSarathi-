package com.example

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.core.app.ApplicationProvider
import com.example.data.NgoDatabase
import com.example.data.NgoRepository
import com.example.ui.NextSarathiAppContent
import com.example.ui.NgoViewModel
import com.example.ui.theme.MyApplicationTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun testAppUserWorkflowsAndNavigationTabs() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    
    // 1. Initialize real in-memory/local storage pipeline
    val database = NgoDatabase.getDatabase(context)
    val repository = NgoRepository(database.ngoDao())
    val viewModel = NgoViewModel(repository)
    
    // 2. Load the App in the simulation environment
    composeTestRule.setContent {
      MyApplicationTheme {
        NextSarathiAppContent(viewModel = viewModel)
      }
    }
    composeTestRule.waitForIdle()

    // 3. Test Dashboard Tab Screen rendering
    viewModel.setTab("DASHBOARD")
    composeTestRule.waitForIdle()

    // 4. Test Field Ops Tab Screen rendering
    viewModel.setTab("FIELD OPS")
    composeTestRule.waitForIdle()

    // 5. Test Join NGO Tab Screen rendering
    viewModel.setTab("JOIN NGO")
    composeTestRule.waitForIdle()

    // 6. Test Reports Tab Screen rendering
    viewModel.setTab("REPORTS")
    composeTestRule.waitForIdle()

    // 7. Test Security Tab Screen rendering
    viewModel.setTab("SECURITY")
    composeTestRule.waitForIdle()
    
    // Check that we loaded the context correctly and had no crashes
    val appName = context.getString(R.string.app_name)
    assertEquals("NextSarathi", appName)
  }
}
