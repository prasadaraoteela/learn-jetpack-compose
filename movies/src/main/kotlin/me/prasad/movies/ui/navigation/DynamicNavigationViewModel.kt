package me.prasad.movies.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.movies.core.navigation.AppDestination
import com.movies.core.navigation.FeatureNavEntry
import com.movies.core.navigation.Navigator
import com.movies.home.navigation.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DynamicNavigationViewModel @Inject constructor(
  val entries: Set<@JvmSuppressWildcards FeatureNavEntry<*>>,
  val navigator: Navigator
) : ViewModel() {

  val backstack: List<AppDestination> = navigator.backstack

  init {
    navigator.push(HomeDestination.Home)
  }

  fun push(destination: AppDestination) {
    navigator.push(destination)
  }

  fun pop() {
    navigator.pop()
  }

  @Composable
  fun Render(destination: AppDestination) {
    val handler = entries.firstOrNull { it.canHandle(destination) }
    checkNotNull(handler) { "No handler found for $destination" }
    handler.Render(destination, navigator)
  }
}
