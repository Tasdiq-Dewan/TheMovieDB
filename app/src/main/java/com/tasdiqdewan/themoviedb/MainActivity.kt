package com.tasdiqdewan.themoviedb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.tasdiqdewan.themoviedb.data.MoviesRepository
import com.tasdiqdewan.themoviedb.ui.TheMovieDBNavHost
import com.tasdiqdewan.themoviedb.ui.theme.TheMovieDBTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var moviesRepository: MoviesRepository

    private var movieName = mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieDBTheme {
                TheMovieDBNavHost()
            }
        }
//        lifecycleScope.launch {
//            val response = moviesRepository.getPopularMoviesList()
//            Log.v("API_RESPONSE", response.isSuccessful.toString())
//            movieName.value = response.body()?.results?.get(0)?.title ?: ""
//        }
//        setContent {
//            TheMovieDBApp(movieName = movieName.value)
//        }
    }
}

@Composable
fun TheMovieDBApp(
    movieName: String,
    darkTheme: Boolean = isSystemInDarkTheme()
) {
    var darkTheme by rememberSaveable { mutableStateOf(darkTheme) }
    TheMovieDBTheme(darkTheme = darkTheme) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting(movieName, changeTheme = { darkTheme = !darkTheme })
        }
    }
}

@Composable
fun Greeting(
    name: String,
    changeTheme: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
            Button(
                onClick = changeTheme,
            ) {
                Text("Button")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheMovieDBTheme {
        Greeting("Android", {})
    }
}