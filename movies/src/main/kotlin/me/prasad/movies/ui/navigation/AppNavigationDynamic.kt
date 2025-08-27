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

@Composable
fun AppNavigationDynamic(
  modifier: Modifier,
  navigationViewModel: DynamicNavigationViewModel = hiltViewModel()
) {

  val navigator = navigationViewModel.navigator
  val backstack = navigator.backstack
  val entries = navigationViewModel.entries()

  NavDisplay(
    backStack = backstack,
    onBack = { navigator.pop() },
    entryDecorators = listOf(
      rememberSceneSetupNavEntryDecorator(),
      rememberSavedStateNavEntryDecorator(),
      rememberViewModelStoreNavEntryDecorator(),
    ),
    entryProvider = entryProvider {
      entry<AppDestination> { destination ->
        val handler = entries.firstOrNull { it.canHandle(destination) }
        if (handler != null) {
          handler.Render(destination, navigator)
        } else {
          // Optional: fallback UI
          Text("No screen found for $destination")
        }
      }
    }
  )
}