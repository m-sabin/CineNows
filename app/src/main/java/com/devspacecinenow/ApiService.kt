package com.devspacecinenow

import androidx.compose.ui.tooling.data.UiToolingDataApi
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/top_rated?language=en-US&page=1")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("movie/now_playing?language=en-US&page=1'")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/upcoming?language=en-US&page=1")
    suspend fun getUpcomingMovies(): MovieResponse
}