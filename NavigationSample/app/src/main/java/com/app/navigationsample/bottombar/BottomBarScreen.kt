package com.app.navigationsample.bottombar

import com.app.navigationsample.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    data object Home: BottomBarScreen(
        route = "recipe",
        title = "Recipe",
        icon = R.drawable.ic_receipe,
        icon_focused = R.drawable.ic_receipe
    )

    // for report
    data object Report: BottomBarScreen(
        route = "nourish",
        title = "Nourish",
        icon = R.drawable.ic_nourish,
        icon_focused = R.drawable.ic_nourish
    )

    // for report
    data object Profile: BottomBarScreen(
        route = "yoga",
        title = "Yoga",
        icon = R.drawable.ic_yoga,
        icon_focused = R.drawable.ic_yoga
    )

}
