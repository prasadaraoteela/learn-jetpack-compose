package com.movies.core.navigation

import androidx.compose.runtime.mutableStateListOf

class Navigator {
  val backstack = mutableStateListOf<AppDestination>()

  fun push(destination: AppDestination) {
    backstack.add(destination)
  }

  fun pop(): Boolean = backstack.removeLastOrNull()?.let { true } ?: false
}