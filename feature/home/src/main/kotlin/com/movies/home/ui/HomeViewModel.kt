package com.movies.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.core.navigation.profile.ProfileNavigator
import core.design.Snackbar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  val snackbar: Snackbar,
  val profileNavigator: ProfileNavigator
) : ViewModel() {

  fun gotoProfile(userId: String) {
    viewModelScope.launch {
      snackbar.showSnackbar(
        message = "Navigating to profile",
        actionLabel = "Dismiss",
        action = {
          profileNavigator.openProfile(userId)
        }
      )
    }
  }
}