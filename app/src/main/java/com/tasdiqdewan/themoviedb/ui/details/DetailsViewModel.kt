package com.tasdiqdewan.themoviedb.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasdiqdewan.themoviedb.data.repository.MoviesRepository
import com.tasdiqdewan.themoviedb.data.usecase.GetLocalMovieReleaseDateUsecase
import com.tasdiqdewan.themoviedb.data.usecase.GetMovieDetailsUsecase
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
    private val getMovieDetailsUsecase: GetMovieDetailsUsecase
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
                getMovieDetailsUsecase.execute(id)
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