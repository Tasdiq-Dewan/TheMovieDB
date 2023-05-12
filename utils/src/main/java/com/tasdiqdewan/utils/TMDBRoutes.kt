package com.tasdiqdewan.utils

import androidx.annotation.StringRes

sealed class TMDBRoutes(val route: String, @StringRes val name: Int) {
    object Home : TMDBRoutes("home", R.string.home)
    object Details: TMDBRoutes("details", R.string.details)
    object Search: TMDBRoutes("search", R.string.search)
}