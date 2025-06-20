package com.devspacecinenow


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage


@Composable
fun MovieListScreen(navController: NavHostController) {

    var topRatedMovies by remember { mutableStateOf<List<MovieDto>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
            val response = apiService.getTopRatedMovies()
            Log.d("MovieListScreen", "Response receive: $response")
            topRatedMovies = response.results
            Log.d("MovieListScreen", "Movies List: $topRatedMovies")
        } catch (e: Exception) {
            Log.e("MovieListScreen", "Request error", e)
        }
    }

    var nowPlayingMovies by remember { mutableStateOf<List<MovieDto>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
            val response = apiService.getNowPlayingMovies()
            Log.d("MovieListScreen", "Response receive: $response")
            nowPlayingMovies = response.results
            Log.d("MovieListScreen", "Movies List: $nowPlayingMovies")

        } catch (e: Exception) {
            Log.e("MovieListScreen", "Request error", e)
        }
    }

    var popularMovies by remember { mutableStateOf<List<MovieDto>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
            val response = apiService.getPopularMovies()
            Log.d("MovieListScreen", "Response receive: $response")
            popularMovies = response.results
            Log.d("MovieListScreen", "Movies List: $popularMovies")

        } catch (e: Exception) {
            Log.e("MovieListScreen", "Request error", e)
        }
    }

    var upcomingMovies by remember { mutableStateOf<List<MovieDto>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
            val response = apiService.getUpcomingMovies()
            Log.d("MovieListScreen", "Response receive: $response")
            upcomingMovies = response.results
            Log.d("MovieListScreen", "Movie List: $upcomingMovies")

        } catch (e: Exception) {
            Log.e("MovieListScreen", "Request error", e)
        }
    }

    MovieListContent(
        topRatedMovies = topRatedMovies,
        nowPlayingMovies = nowPlayingMovies,
        popularMovies = popularMovies,
        upcomingMovies = upcomingMovies
    ) { itemClicked ->
        navController.navigate(route = "movieDetail/${itemClicked.id}")
    }
}

@Composable
private fun MovieListContent(
    topRatedMovies: List<MovieDto>,
    nowPlayingMovies: List<MovieDto>,
    popularMovies: List<MovieDto>,
    upcomingMovies: List<MovieDto>,
    onClick: (MovieDto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            text = "CineNow"
        )

        MovieSession(
            label = "Top Rated",
            movieList = topRatedMovies,
            onClick = onClick
        )

        MovieSession(
            label = "Now Playing",
            movieList = nowPlayingMovies,
            onClick = onClick
        )

        MovieSession(
            label = "Popular",
            movieList = popularMovies,
            onClick = onClick
        )

        MovieSession(
            label = "Upcoming",
            movieList = upcomingMovies,
            onClick = onClick
        )

    }
}

@Composable
private fun MovieSession(
    label: String,
    movieList: List<MovieDto>,
    onClick: (MovieDto) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            fontSize = 24.sp,
            text = label,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))
        MovieList(movieList = movieList, onClick = onClick)
    }
}

@Composable
private fun MovieList(
    movieList: List<MovieDto>,
    onClick: (MovieDto) -> Unit
) {
    LazyRow {
        items(movieList) {
            MovieItem(
                movieDto = it,
                onClick = onClick
            )
        }
    }
}

@Composable
private fun MovieItem(
    movieDto: MovieDto,
    onClick: (MovieDto) -> Unit
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Min)
            .clickable {
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
        Spacer(Modifier.size(4.dp))
        Text(
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = movieDto.title
        )
        Text(
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            text = movieDto.overview
        )
    }

}