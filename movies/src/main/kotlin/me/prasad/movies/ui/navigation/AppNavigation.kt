package me.prasad.movies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import me.prasad.movies.ui.Screen
import me.prasad.movies.ui.details.DetailsScreen
import me.prasad.movies.ui.home.HomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
  val backstack = rememberNavBackStack<Screen>(Screen.Home)

  NavDisplay(
    backStack = backstack,
    onBack = { backstack.removeLastOrNull() },
    entryProvider = entryProvider {
      entry<Screen.Home> {
        HomeScreen(modifier) {
          backstack.add(Screen.Details("123"))
        }
      }
      entry<Screen.Details> { key ->
        DetailsScreen(modifier, id = key.id) {
          backstack.removeLastOrNull()
        }
      }
    }
  )
}