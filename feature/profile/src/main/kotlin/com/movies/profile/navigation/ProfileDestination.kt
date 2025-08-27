package com.movies.profile.navigation

import com.movies.core.navigation.AppDestination
import kotlinx.serialization.Serializable

sealed interface ProfileDestination : AppDestination {

  @Serializable
  data class Profile(val userId: String) : ProfileDestination
}