package me.prasad.jetpack.compose.data.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import me.prasad.jetpack.compose.data.model.Section
import me.prasad.jetpack.compose.data.model.Tile
import javax.inject.Inject

class DefaultSectionRepository @Inject constructor() : SectionRepository {
    override fun fetchSections(): List<Section> = listOf(
        Section(
            title = "Bill Sense",
            tiles = listOf(
                Tile(
                    title = "Bills and Payments",
                    description = "Stay on top of your bills and make payments with ease.",
                    icon = Icons.Rounded.Home
                )
            )
        ),
        Section(
            title = "Money Management",
            tiles = listOf(
                Tile(
                    title = "Cash Flow View",
                    description = "Get a clear picture of your finances with Cash Flow View! Track your income and expenses, identify spending patterns, and make informed financial decisions.",
                    icon = Icons.Rounded.Home
                ),
                Tile(
                    title = "Cash Flow View",
                    description = "Get a clear picture of your finances with Cash Flow View! Track your income and expenses, identify spending patterns, and make informed financial decisions.",
                    icon = Icons.Rounded.Home
                )
            )
        )
    )
}