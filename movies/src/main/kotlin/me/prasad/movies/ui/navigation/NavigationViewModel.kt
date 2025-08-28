package me.prasad.movies.ui.navigation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.design.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.prasad.movies.ui.Screen
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
  val snackbar: Snackbar
) : ViewModel() {

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

  fun onDeleteItem() {
    viewModelScope.launch {
      snackbar.showSnackbar(
        message = "Item deleted",
        actionLabel = "Undo",
        action = {
          addScreen(Screen.Details("123"))
        }
      )
    }
  }
}