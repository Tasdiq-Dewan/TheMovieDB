package com.tasdiqdewan.themoviedb.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tasdiqdewan.themoviedb.ui.details.DetailsScreen
import com.tasdiqdewan.themoviedb.ui.details.DetailsViewModel
import com.tasdiqdewan.themoviedb.ui.home.HomeScreen
import com.tasdiqdewan.themoviedb.ui.home.HomeViewModel
import com.tasdiqdewan.utils.TMDBScreen
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheMovieDBNavHost(
    homeViewModel: HomeViewModel = hiltViewModel(),
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        topBar = { TMDBTopBar(currentDestination) },
        bottomBar = { TMDBBottomBar(navController, currentDestination) }
    ) { contentPadding ->
        val homeState by homeViewModel.state.collectAsState()
        val detailsState by detailsViewModel.state.collectAsState()
        NavHost(navController = navController, startDestination = TMDBScreen.Home.route, modifier = Modifier.padding(contentPadding)) {
            composable(TMDBScreen.Home.route) {
                HomeScreen(
                    state = homeState,
                    navigateToDetails = { id: Int -> navController.navigate(TMDBScreen.Details.route + "/${id}") }
                )
            }
            composable(
                "${TMDBScreen.Details.route}/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                navBackStackEntry.arguments?.getInt("movieId")
                    ?.let { detailsViewModel.getMovieDetails(it) }
                DetailsScreen(detailsState.data)
            }
            composable(TMDBScreen.Search.route) {
                Text(text = "Search")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TMDBTopBar(
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            when(currentDestination?.route) {
                TMDBScreen.Home.route -> Text("TheMovieDatabase")
                TMDBScreen.Search.route -> Text(stringResource(TMDBScreen.Search.name))
                else -> Text("TheMovieDatabase")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        modifier = modifier
    )
}

@Composable
fun TMDBBottomBar(
    navController: NavHostController,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    val screens = listOf(TMDBScreen.Home, TMDBScreen.Search)
   NavigationBar(
       modifier = modifier,
       containerColor = MaterialTheme.colorScheme.primary,
       contentColor = MaterialTheme.colorScheme.onPrimary
   ) {
        screens.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = {
                  TMBDNavigationIcon(screen)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                    unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                ),
                label = { Text(stringResource(screen.name)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = false
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = false
                    }
                }
            )
        }
   }
}

@Composable
fun TMBDNavigationIcon(screen: TMDBScreen) {
    when(screen.route) {
        TMDBScreen.Home.route -> Icon(Icons.Filled.Home, contentDescription = null)
        TMDBScreen.Search.route -> Icon(Icons.Filled.Search, contentDescription = null)
        else -> Icon(Icons.Filled.Home, contentDescription = null)
    }
}
