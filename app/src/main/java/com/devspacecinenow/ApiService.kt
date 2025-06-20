package com.devspacecinenow

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/top_rated?language=en-US&page=1")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(): MovieResponse

    @GET("movie/upcoming?language=en-US&page=1")
    suspend fun getUpcomingMovies(): MovieResponse

    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMovieById(@Path("movie_id") movieId: String): MovieDto
}