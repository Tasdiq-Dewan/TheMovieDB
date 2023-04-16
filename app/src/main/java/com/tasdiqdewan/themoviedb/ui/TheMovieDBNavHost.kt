package com.tasdiqdewan.themoviedb.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tasdiqdewan.themoviedb.ui.screens.home.HomeScreen
import com.tasdiqdewan.themoviedb.ui.screens.home.HomeViewModel

@Composable
fun TheMovieDBNavHost() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val homeState by homeViewModel.state.collectAsState()
            HomeScreen(state = homeState)
        }
    }
}