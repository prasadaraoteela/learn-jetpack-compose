package com.movies.core.navigation

import androidx.compose.runtime.mutableStateListOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
  val backstack = mutableStateListOf<AppDestination>()

  fun push(destination: AppDestination) {
    backstack.add(destination)
  }

  fun pop(): Boolean = backstack.removeLastOrNull()?.let { true } ?: false
}