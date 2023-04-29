package com.tasdiqdewan.themoviedb.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasdiqdewan.themoviedb.data.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private var _state = MutableStateFlow(DetailsScreenState(DetailsScreenData.Loading))
    val state: StateFlow<DetailsScreenState> = _state.asStateFlow()


    init {

    }

    private suspend fun clearState() {
        _state.value.copy(DetailsScreenData.Loading).let {
            _state.emit(it)
        }
    }

    fun getMovieDetails(id: Int) {
        viewModelScope.launch {
            val movieDetails = try {
                moviesRepository.getMovieDetails(id).body()?.let {
                    DetailsScreenData.Success(it)
                }
            }
            catch(e: IOException) {
                DetailsScreenData.Error
            }
            catch(e: HttpException) {
                DetailsScreenData.Error
            }

            if (movieDetails != null) {
                _state.value.copy(data = movieDetails).let {
                    Log.d("DETAILS", it.data.toString())
                    _state.emit(it)
                }
            }
        }
    }
}