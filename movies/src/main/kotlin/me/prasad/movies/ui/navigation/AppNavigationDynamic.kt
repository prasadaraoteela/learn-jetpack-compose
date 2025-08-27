package me.prasad.movies.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.movies.core.navigation.AppDestination
import com.movies.home.navigation.HomeDestination // Added import

@Composable
fun AppNavigationDynamic(
  modifier: Modifier, // modifier parameter was already here
  navigationViewModel: DynamicNavigationViewModel = hiltViewModel()
) {

  val navigator = navigationViewModel.navigator
  val backstack = navigator.backstack
  val entries = navigationViewModel.entries()

  NavDisplay(
    modifier = modifier, // Apply the modifier here
    backStack = backstack,
    onBack = { navigator.pop() },
    entryDecorators = listOf(
      rememberSceneSetupNavEntryDecorator(),
      rememberSavedStateNavEntryDecorator(),
      rememberViewModelStoreNavEntryDecorator(),
    ),
    entryProvider = entryProvider {
      // Specific entry for HomeDestination.Home
      entry(HomeDestination.Home) { destination ->
        val handler = entries.firstOrNull { it.canHandle(destination) }
        if (handler != null) {
          handler.Render(destination, navigator)
        } else {
          // This should ideally not happen if HomeNavEntry is correctly provided
          Text("No handler for Home screen: $destination")
        }
      }

      // Generic fallback for other AppDestinations
      entry<AppDestination> { destination ->
        // Safeguard: HomeDestination.Home should have been handled by the specific entry
        if (destination is HomeDestination.Home) {
            Text("Error: Home screen reached generic handler for $destination")
            return@entry
        }

        val handler = entries.firstOrNull { it.canHandle(destination) }
        if (handler != null) {
          handler.Render(destination, navigator)
        } else {
          Text("No screen found for $destination")
        }
      }
    }
  )
}
