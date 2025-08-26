package me.prasad.movies.ui.navigation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import me.prasad.movies.ui.Screen
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {

  private val _backStack = mutableStateListOf<Screen>(Screen.Home)
  val backStack: List<Screen> = _backStack

  init {
    Log.i("NavigationViewModel", "INITIALISING NAVIGATION VIEW MODEL...")
  }

  override fun onCleared() {
    super.onCleared()
    Log.i("NavigationViewModel", "CLEARING NAVIGATION VIEW MODEL...")
  }

  fun addScreen(screen: Screen) {
    _backStack.add(screen)
  }

  fun onBackPress() {
    _backStack.removeLastOrNull()
  }
}