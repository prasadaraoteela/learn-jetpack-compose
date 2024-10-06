package me.prasad.jetpack.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import me.prasad.jetpack.compose.ui.screen.MoneyPlan
import me.prasad.jetpack.compose.ui.theme.LearnJetpackComposeTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      LearnJetpackComposeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          MoneyPlan(
            modifier = Modifier.padding(innerPadding)
          )
        }
      }
    }
  }
}