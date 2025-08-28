package com.movies.profile.navigation

import com.movies.core.navigation.Navigator
import com.movies.core.navigation.profile.ProfileNavigator
import javax.inject.Inject

class DefaultProfileNavigator @Inject constructor(
  private val navigator: Navigator
) : ProfileNavigator {
  override fun openProfile(userId: String) {
    navigator.navigate(ProfileDestination.Profile(userId))
  }
}