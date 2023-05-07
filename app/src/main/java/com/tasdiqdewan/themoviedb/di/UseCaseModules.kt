package com.tasdiqdewan.themoviedb.di

import com.tasdiqdewan.themoviedb.data.usecase.GetLocalMovieReleaseDateUseCase
import com.tasdiqdewan.themoviedb.data.usecase.GetLocalMovieReleaseDateUseCaseImpl
import com.tasdiqdewan.themoviedb.data.usecase.GetMovieDetailsUseCase
import com.tasdiqdewan.themoviedb.data.usecase.GetMovieDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindGetMovieDetailsUseCase(
        getMovieDetailsUseCaseImpl: GetMovieDetailsUseCaseImpl
    ): GetMovieDetailsUseCase

    @Binds
    abstract fun bindsGetMovieLocalReleaseDate(
        getLocalMovieReleaseDateUseCaseImpl: GetLocalMovieReleaseDateUseCaseImpl
    ): GetLocalMovieReleaseDateUseCase
}