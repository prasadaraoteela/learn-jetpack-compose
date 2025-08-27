package com.movies.core.navigation

import androidx.compose.runtime.mutableStateListOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {
  private val _backstack = mutableStateListOf<AppDestination>()
  val backstack: List<AppDestination> = _backstack

  fun push(destination: AppDestination) {
    _backstack.add(destination)
  }

  fun pop(): Boolean = _backstack.removeLastOrNull()?.let { true } ?: false

  fun popToRoot() {
    if (_backstack.size <= 1) return
    _backstack.subList(1, _backstack.size).clear()
  }

  fun popTo(destination: AppDestination) {
    if (_backstack.isEmpty()) return
    val index = _backstack.indexOfFirst { it == destination }
    if (index == -1) return
    _backstack.subList(index + 1, _backstack.size).clear()
  }
}