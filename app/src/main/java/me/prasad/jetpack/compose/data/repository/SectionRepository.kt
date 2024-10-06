package me.prasad.jetpack.compose.data.repository

import me.prasad.jetpack.compose.data.model.Section

interface SectionRepository {

  fun fetchSections(): List<Section>
}