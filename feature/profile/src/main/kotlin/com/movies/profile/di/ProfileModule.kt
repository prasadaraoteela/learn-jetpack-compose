package com.movies.profile.di

import androidx.navigation3.runtime.entry
import com.movies.core.navigation.EntryProviderInstaller
import com.movies.core.navigation.Navigator
import com.movies.core.navigation.profile.ProfileNavigator
import com.movies.profile.navigation.DefaultProfileNavigator
import com.movies.profile.navigation.ProfileDestination
import com.movies.profile.ui.ProfileScreen
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.multibindings.IntoSet

@Module
@InstallIn(ActivityRetainedComponent::class)
object ProfileModule {
  @Provides
  @IntoSet
  fun provideProfileEntry(navigator: Navigator): EntryProviderInstaller = {
    entry<ProfileDestination.Profile> { profile ->
      ProfileScreen(profile.userId) {
        navigator.goBack()
      }
    }
  }

  @Provides
  @ActivityRetainedScoped
  fun provideProfileNavigator(profileNavigator: DefaultProfileNavigator): ProfileNavigator =
    profileNavigator
}