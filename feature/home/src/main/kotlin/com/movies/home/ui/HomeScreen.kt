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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(gotoProfile: (userId: String) -> Unit) {
  Scaffold(
    topBar = { TopAppBar(title = { Text("Home") }) }
  ) { padding ->
    Button(
      onClick = { gotoProfile("123") },
      modifier = Modifier
        .padding(padding)
        .padding(16.dp)
    ) {
      Text("Go to Profile")
    }
  }
}