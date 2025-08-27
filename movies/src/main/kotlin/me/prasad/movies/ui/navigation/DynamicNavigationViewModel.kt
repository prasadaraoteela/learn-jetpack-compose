package me.prasad.movies.ui.navigation

import androidx.lifecycle.ViewModel
import com.movies.core.navigation.FeatureNavEntry
import com.movies.core.navigation.Navigator
import com.movies.home.navigation.HomeDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.jvm.JvmSuppressWildcards

@HiltViewModel
class DynamicNavigationViewModel @Inject constructor(
  val entries: @JvmSuppressWildcards Set<FeatureNavEntry>
) : ViewModel() {

  val navigator = Navigator().apply {
    if (backstack.isEmpty()) {
      backstack.add(HomeDestination.Home)
    }
  }

  fun entries() = entries
}
