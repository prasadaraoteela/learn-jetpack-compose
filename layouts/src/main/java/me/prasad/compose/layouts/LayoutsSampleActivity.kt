package me.prasad.compose.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import me.prasad.compose.layouts.ui.screen.LazyGridSample
import me.prasad.compose.layouts.ui.screen.LazyListWithState
import me.prasad.compose.layouts.ui.screen.VegetablesScreen
import me.prasad.compose.layouts.ui.theme.AppTheme

@AndroidEntryPoint
class LayoutsSampleActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      AppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          VegetablesScreen(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}