package com.app.navigationsample

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.app.navigationsample.ui.theme.NavigationSampleTheme
import com.app.navigationsample.uiscreen.BottomNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
   // val viewModel: UserViewModel by viewModels()

    @Composable
    override fun BuildContent() {
        setContent {
            NavigationSampleTheme {
                BottomNav()

            }
        }

    }
}

