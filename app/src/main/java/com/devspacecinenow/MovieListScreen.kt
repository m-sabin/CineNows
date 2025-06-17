package com.devspacecinenow


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


@Composable
fun MovieListScreen() {
    var nowPlayingMovies by remember { mutableStateOf<List<MovieDto>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
            val response = apiService.getNowPlayingMovies()
            Log.d("MovieListScreen", "Response receive: $response")
            nowPlayingMovies = response.results.orEmpty()
            Log.d("MovieListScreen", "Movies List: $nowPlayingMovies")

        } catch (e: Exception) {
            Log.e("MovieListScreen", "Request error", e)
        }
    }

    MovieList(
        nowPlayingMovies

    ) { movieClicked -> }

}

@Composable
fun MovieList(
    movieList: List<MovieDto>,
    onClick: (MovieDto) -> Unit
) {
    LazyRow {
        items(movieList) {
            MovieItem (
                movieDto = it,
                onClick = onClick
            )
        }
    }
}

@Composable
fun MovieItem(
    movieDto: MovieDto,
    onClick: (MovieDto) -> Unit
) {
    Column (
        modifier = Modifier.clickable{
            onClick.invoke(movieDto)
        }
    ) {
        AsyncImage(
            modifier = Modifier
                .width(120.dp)
                .height(150.dp)
                .padding(end = 4.dp),
            model = movieDto.posterFullPath,
            contentScale = ContentScale.Crop,
            contentDescription = "${movieDto.title} Poster Path"
        )
    }

}