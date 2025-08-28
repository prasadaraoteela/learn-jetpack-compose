package com.movies.home.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
  viewModel: HomeViewModel = hiltViewModel()
) {
  Scaffold(
    topBar = { TopAppBar(title = { Text("Home") }) }
  ) { padding ->
    Button(
      onClick = { viewModel.gotoProfile("12345") },
      modifier = Modifier
        .padding(padding)
        .padding(16.dp)
    ) {
      Text("Go to Profile")
    }
  }
}