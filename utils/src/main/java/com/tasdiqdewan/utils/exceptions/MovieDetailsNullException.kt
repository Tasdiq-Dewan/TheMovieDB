package com.tasdiqdewan.utils.exceptions

import java.lang.Exception

class MovieDetailsNullException(message: String = "repository.getMovieDetails(id) has returned null"): Exception(message) {

}