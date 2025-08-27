package com.movies.profile.navigation

import com.movies.core.navigation.Navigator
import com.movies.core.navigation.profile.ProfileNavigator
import javax.inject.Inject

class DefaultProfileNavigator @Inject constructor() : ProfileNavigator {
  override fun openProfile(
    userId: String,
    navigator: Navigator
  ) {
    navigator.navigate(ProfileDestination.Profile(userId))
  }
}