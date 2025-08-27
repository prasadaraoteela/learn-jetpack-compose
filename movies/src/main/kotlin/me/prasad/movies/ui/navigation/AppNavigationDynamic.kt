package me.prasad.movies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator

@Composable
fun AppNavigationDynamic(
  modifier: Modifier, // modifier parameter was already here
  navigationViewModel: DynamicNavigationViewModel = hiltViewModel()
) {

  val backstack = navigationViewModel.backstack

  NavDisplay(
    modifier = modifier, // Apply the modifier here
    backStack = backstack,
    onBack = { navigationViewModel.pop() },
    entryDecorators = listOf(
      rememberSceneSetupNavEntryDecorator(),
      rememberSavedStateNavEntryDecorator(),
      rememberViewModelStoreNavEntryDecorator(),
    ),
    entryProvider = entryProvider {
      backstack.forEach { destination ->
        entry(key = destination) {
          navigationViewModel.Render(destination)
        }
      }
    }
  )
}
