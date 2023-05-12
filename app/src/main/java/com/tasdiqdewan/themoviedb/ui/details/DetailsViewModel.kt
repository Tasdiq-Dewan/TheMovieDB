package com.tasdiqdewan.themoviedb.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasdiqdewan.themoviedb.data.usecase.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
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
            //if(!(_state.value.data is DetailsScreenData.Loading)) clearState()
            val movieDetails: DetailsScreenData = try {
                getMovieDetailsUseCase.execute(id)
            }
            catch(e: IOException) {
                Log.e("DETAILS", e.message.toString())
                DetailsScreenData.Error(e)
            }
            catch(e: HttpException) {
                Log.e("DETAILS", e.message.toString())
                DetailsScreenData.Error(e)
            }
            catch(e: Exception) {
                Log.e("DETAILS", e.message.toString())
                DetailsScreenData.Error(e)
            }

            if (movieDetails != null) {
                _state.value.copy(data = movieDetails as DetailsScreenData).let {
                    Log.d("DETAILS", it.data.toString())
                    _state.emit(it)
                }
            }
        }
    }
}