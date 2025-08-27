package com.movies.profile.di

import com.movies.core.navigation.FeatureNavEntry
import com.movies.core.navigation.profile.ProfileNavigator
import com.movies.profile.navigation.DefaultProfileNavigator
import com.movies.profile.navigation.ProfileNavEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ProfileModule {

  @Binds
  @IntoSet
  fun bindProfileEntry(entry: ProfileNavEntry): FeatureNavEntry<*>

  @Binds
  @Singleton
  fun bindProfileNavigator(navigator: DefaultProfileNavigator): ProfileNavigator
}