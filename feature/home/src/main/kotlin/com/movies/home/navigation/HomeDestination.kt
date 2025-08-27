package com.movies.home.navigation

import com.movies.core.navigation.AppDestination
import kotlinx.serialization.Serializable

sealed interface HomeDestination : AppDestination {
  @Serializable
  data object Home : HomeDestination
}