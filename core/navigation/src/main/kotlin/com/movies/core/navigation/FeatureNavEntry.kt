package com.movies.core.navigation

import androidx.compose.runtime.Composable

/** Each feature contributes one (or more) entries that can render its destinations */
interface FeatureNavEntry {

  /** Return true if this entry can render the given destination instance */
  fun canHandle(destination: AppDestination): Boolean

  /** Render the screen for the destination */
  @Composable
  fun Render(destination: AppDestination, navigator: Navigator)
}