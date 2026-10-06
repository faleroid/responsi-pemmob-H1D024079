package com.pemmob.responsipemmob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pemmob.responsipemmob.navigation.AppNavigation
import com.pemmob.responsipemmob.ui.theme.ResponsipemmobTheme
import com.pemmob.responsipemmob.ui.viewmodel.GempaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResponsipemmobTheme {
                val viewModel: GempaViewModel = viewModel()
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}