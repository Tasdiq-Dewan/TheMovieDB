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
    private var _state = MutableStateFlow(DetailsScreenState())
    val state: StateFlow<DetailsScreenState> = _state.asStateFlow()


    init {

    }

    fun clearState() {
        _state.value = DetailsScreenState()
    }
    fun setLoading(loading: Boolean) {
        _state.value = _state.value.copy(isLoading = loading)
    }

    fun getMovieDetails(id: Int) {
        //if(state.value.data !is DetailsScreenData.Loading) clearState()
        viewModelScope.launch {
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

            _state.value = DetailsScreenState(data = movieDetails)
        }
    }
}