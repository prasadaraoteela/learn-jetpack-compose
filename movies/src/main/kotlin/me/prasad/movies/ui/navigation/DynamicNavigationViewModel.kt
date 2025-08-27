package me.prasad.movies.ui.navigation

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
    if (backstack.lastOrNull() !is HomeDestination.Home) {
      navigator.push(HomeDestination.Home)
    }
  }

  fun push(destination: AppDestination) {
    navigator.push(destination)
  }

  fun pop() {
    navigator.pop()
  }

  fun handler(destination: AppDestination): FeatureNavEntry<*> {
    val handler = entries.firstOrNull { it.canHandle(destination) }
    checkNotNull(handler) { "No handler found for $destination" }
    return handler
  }
}
