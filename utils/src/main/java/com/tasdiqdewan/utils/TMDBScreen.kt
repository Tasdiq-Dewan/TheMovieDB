package com.tasdiqdewan.utils

import androidx.annotation.StringRes

sealed class TMDBScreen(val route: String, @StringRes val name: Int) {
    object Home : TMDBScreen("home", R.string.home)
    object Search: TMDBScreen("search", R.string.search)
}