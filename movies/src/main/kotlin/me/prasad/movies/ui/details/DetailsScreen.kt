package me.prasad.movies.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.prasad.movies.R

@Composable
fun DetailsScreen(
  modifier: Modifier = Modifier,
  id: String,
  navigateToHome: () -> Unit
) {
  Box(
    modifier = modifier
      .background(Color.Cyan)
      .fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Column(
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Text(id)
      Button(onClick = navigateToHome) {
        Text(text = stringResource(R.string.home))
      }
    }
  }
}