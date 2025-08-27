package me.prasad.movies.di

import com.movies.core.navigation.FeatureNavEntry
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.Multibinds

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Multibinds
    abstract fun bindFeatureNavEntrySet(): Set<FeatureNavEntry>
}
