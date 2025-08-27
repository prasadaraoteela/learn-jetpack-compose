package com.movies.home.navigation

import androidx.compose.runtime.Composable
import com.movies.core.navigation.AppDestination
import com.movies.core.navigation.FeatureNavEntry
import com.movies.core.navigation.Navigator
import com.movies.core.navigation.profile.ProfileNavigator
import com.movies.home.ui.HomeScreen
import javax.inject.Inject

class HomeNavEntry @Inject constructor(
  private val profileNavigator: ProfileNavigator
) : FeatureNavEntry<HomeDestination> {

  override fun canHandle(destination: AppDestination): Boolean = destination is HomeDestination

  @Composable
  override fun Render(
    destination: HomeDestination,
    navigator: Navigator
  ) {
    when (destination) {
      is HomeDestination.Home -> HomeScreen { userId ->
        profileNavigator.openProfile(userId, navigator)
      }
    }
  }
}