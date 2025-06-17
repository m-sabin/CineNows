package com.devspacecinenow

import androidx.compose.ui.tooling.data.UiToolingDataApi
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing?language=en-US&page=1'")
    suspend fun getNowPlayingMovies(): MovieResponse

}