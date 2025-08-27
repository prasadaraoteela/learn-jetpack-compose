package com.movies.home.di

import com.movies.core.navigation.FeatureNavEntry
import com.movies.home.navigation.HomeNavEntry
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

  @Binds
  @IntoSet
  abstract fun bindHomeNavEntry(entry: HomeNavEntry): FeatureNavEntry
}