package me.prasad.movies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import me.prasad.movies.ui.Screen
import me.prasad.movies.ui.details.DetailsScreen
import me.prasad.movies.ui.home.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
  val viewModel = viewModel<NavigationViewModel>()

  NavDisplay(
    backStack = viewModel.backStack,
    onBack = { viewModel.onBackPress() },
    entryDecorators = listOf(
      rememberSceneSetupNavEntryDecorator(),
      rememberSavedStateNavEntryDecorator(),
      rememberViewModelStoreNavEntryDecorator(),
    ),
    entryProvider = entryProvider {
      entry<Screen.Home> {
        HomeScreen(modifier) {
//          viewModel.addScreen(Screen.Details("123"))
          viewModel.onDeleteItem()
        }
      }
      entry<Screen.Details> { key ->
        DetailsScreen(modifier, id = key.id) {
          viewModel.onBackPress()
        }
      }
    }
  )
}