package me.prasad.movies.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import me.prasad.movies.R

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  navigateToDetailsScreen: () -> Unit
) {
  Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Button(onClick = { navigateToDetailsScreen() }) {
      Text(text = stringResource(R.string.details))
    }
  }
}