package me.prasad.movies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.movies.core.navigation.EntryProviderInstaller
import com.movies.core.navigation.Navigator
import com.movies.home.navigation.HomeDestination
import dagger.hilt.android.AndroidEntryPoint
import me.prasad.movies.ui.navigation.AppNavigationDynamic
import me.prasad.movies.ui.theme.MoviesAppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {

  @Inject
  lateinit var navigator: Navigator

  @Inject
  lateinit var entries: Set<@JvmSuppressWildcards EntryProviderInstaller>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    navigator.navigate(HomeDestination.Home)
    setContent {
      MoviesAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          AppNavigationDynamic(
            modifier = Modifier.padding(innerPadding),
            navigator = navigator,
            entries = entries
          )
        }
      }
    }
  }
}