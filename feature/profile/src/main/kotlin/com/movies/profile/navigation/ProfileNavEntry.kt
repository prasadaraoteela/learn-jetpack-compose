package com.movies.profile.navigation

import androidx.compose.runtime.Composable
import com.movies.core.navigation.AppDestination
import com.movies.core.navigation.FeatureNavEntry
import com.movies.core.navigation.Navigator
import com.movies.profile.ui.ProfileScreen
import javax.inject.Inject

class ProfileNavEntry @Inject constructor() : FeatureNavEntry<ProfileDestination> {
  override fun canHandle(destination: AppDestination): Boolean =
    destination is ProfileDestination

  @Composable
  override fun Render(
    destination: ProfileDestination,
    navigator: Navigator
  ) {
    when (destination) {
      is ProfileDestination.Profile -> ProfileScreen(
        userId = destination.userId
      ) {
        navigator.pop()
      }
    }
  }
}