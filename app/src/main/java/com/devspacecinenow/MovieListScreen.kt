package com.devspacecinenow


import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MovieListScreen() {
    var nowPlayingMovies by remember { mutableStateOf<List<MovieDto>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            val apiService = RetrofitClient.retrofitInstance.create(ApiService::class.java)
            val response = apiService.getNowPlayingMovies()
            Log.d("MovieListScreen", "Resposta recebida: $response")
            nowPlayingMovies = response.results.orEmpty()
            Log.d("MovieListScreen", "Lista de filmes: $nowPlayingMovies")

        } catch (e: Exception) {
            Log.e("MovieListScreen", "Erro na requisição", e)
        }
    }
    LazyColumn {
        items(nowPlayingMovies) { movie ->
            Text(text = movie.title)
        }
    }

}