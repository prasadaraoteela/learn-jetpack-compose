package com.movies.core.navigation.profile

import com.movies.core.navigation.Navigator

interface ProfileNavigator {

  fun openProfile(userId: String, navigator: Navigator)
}