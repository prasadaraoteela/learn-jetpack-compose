package com.movies.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileScreen(
  userId: String,
  onBackPress: () -> Unit
) {
  Scaffold { innerPadding ->
    Box(
      modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()
        .background(Color.LightGray),
      contentAlignment = Alignment.Center
    ) {
      Button(onClick = { onBackPress() }) {
        Text(text = "Goto Home! ($userId)")
      }
    }
  }
}