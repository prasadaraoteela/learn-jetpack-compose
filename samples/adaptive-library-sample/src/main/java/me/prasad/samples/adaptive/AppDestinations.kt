package me.prasad.samples.adaptive

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class AppDestinations(
    @param:StringRes val label: Int,
    @param:DrawableRes val icon: Int,
    @param:StringRes val contentDescription: Int
) {
    HOME(R.string.home, R.drawable.home, R.string.home),
    FAVORITES(R.string.favorites, R.drawable.favorite, R.string.favorites),
    SHOPPING(R.string.shopping, R.drawable.shopping_bag, R.string.shopping),
    PROFILE(R.string.profile, R.drawable.account_circle, R.string.profile)
}