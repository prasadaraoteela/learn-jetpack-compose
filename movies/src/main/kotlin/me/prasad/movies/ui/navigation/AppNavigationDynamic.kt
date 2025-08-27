package me.prasad.movies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.movies.core.navigation.NavEntryPoint
import com.movies.core.navigation.Navigator

@Composable
fun AppNavigationDynamic(
  modifier: Modifier, // modifier parameter was already here
  navigator: Navigator,
  entries: Set<NavEntryPoint>,
) {

  NavDisplay(
    modifier = modifier, // Apply the modifier here
    backStack = navigator.backstack,
    onBack = { navigator.goBack() },
    entryDecorators = listOf(
      rememberSceneSetupNavEntryDecorator(),
      rememberSavedStateNavEntryDecorator(),
      rememberViewModelStoreNavEntryDecorator(),
    ),
    entryProvider = entryProvider {
      entries.forEach { entry -> this.entry() }
    }
  )
}
