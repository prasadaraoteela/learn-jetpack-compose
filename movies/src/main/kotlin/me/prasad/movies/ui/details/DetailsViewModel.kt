package me.prasad.movies.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel

class DetailsViewModel : ViewModel() {

  init {
    Log.i("DetailsViewModel", "INITIALISING DETAILS VIEW MODEL...")
  }

  override fun onCleared() {
    super.onCleared()
    Log.i("DetailsViewModel", "CLEARING DETAILS VIEW MODEL...")
  }
}