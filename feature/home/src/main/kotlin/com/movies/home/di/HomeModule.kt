package com.movies.home.di

import androidx.navigation3.runtime.entry
import com.movies.core.navigation.NavEntryPoint
import com.movies.core.navigation.Navigator
import com.movies.core.navigation.profile.ProfileNavigator
import com.movies.home.navigation.HomeDestination
import com.movies.home.ui.HomeScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object HomeModule {
  @Provides
  @IntoSet
  fun provideHomeMainEntry(
    navigator: Navigator,
    profileNavigator: ProfileNavigator
  ): NavEntryPoint = {
    entry<HomeDestination.Home> {
      HomeScreen { userId ->
        profileNavigator.openProfile(userId = userId, navigator = navigator)
      }
    }
  }
}