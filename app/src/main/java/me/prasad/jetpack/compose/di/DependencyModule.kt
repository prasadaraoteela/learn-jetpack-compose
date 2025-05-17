package me.prasad.jetpack.compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.prasad.jetpack.compose.data.repository.DefaultSectionRepository
import me.prasad.jetpack.compose.data.repository.SectionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DependencyModule {

  @Binds
  @Singleton
  abstract fun bindSectionRepository(defaultSectionRepository: DefaultSectionRepository): SectionRepository
}
