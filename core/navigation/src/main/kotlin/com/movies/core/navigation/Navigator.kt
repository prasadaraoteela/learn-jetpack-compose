package com.movies.core.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.EntryProviderBuilder
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

typealias NavEntryPoint = EntryProviderBuilder<AppDestination>.() -> Unit

@ActivityRetainedScoped
class Navigator @Inject constructor() {

  private val _backstack = mutableStateListOf<AppDestination>()
  val backstack: List<AppDestination> = _backstack

  fun setup(startDestination: AppDestination) {
    _backstack.clear()
    _backstack.add(startDestination)
  }

  fun navigate(destination: AppDestination) {
    _backstack.add(destination)
  }

  fun goBack(): Boolean = _backstack.removeLastOrNull()?.let { true } ?: false

  fun clearBackstack() {
    if (_backstack.size <= 1) return
    _backstack.subList(1, _backstack.size).clear()
  }

  fun navigateUpTo(destination: AppDestination) {
    if (_backstack.isEmpty()) return
    val index = _backstack.indexOfFirst { it == destination }
    if (index == -1) return
    _backstack.subList(index + 1, _backstack.size).clear()
  }
}