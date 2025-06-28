package com.devspacecinenow.list.data

import com.devspacecinenow.common.model.MovieResponse
import retrofit2.http.GET

interface ListService {
    @GET("movie/top_rated?language=en-US&page=1")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/upcoming?language=en-US&page=1")
    suspend fun getUpcomingMovies(): MovieResponse
}