package com.tasdiqdewan.themoviedb.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.themoviedb.data.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {
    private var _state = MutableStateFlow(HomeScreenState())
    val state: StateFlow<HomeScreenState> = _state.asStateFlow()

    fun getPopularMovies() {
        viewModelScope.launch {
            val popularMovies = try {
                HomePopularMovies.Success(
                    getPopularMoviesUseCase.execute()
                )
            }
            catch(e: IOException) {
                HomePopularMovies.Error(e)
            }
            catch(e: HttpException) {
                HomePopularMovies.Error(e)
            }
            _state.value.copy(popularMovies = popularMovies).let {
                _state.emit(it)
            }
        }
    }
}