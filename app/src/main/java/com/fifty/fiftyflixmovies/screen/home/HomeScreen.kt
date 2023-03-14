package com.fifty.fiftyflixmovies.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fifty.fiftyflixmovies.data.model.Movie
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val dashboardContentScrollState = rememberScrollState()
    val trendingMovies = viewModel.trendingMovies.observeAsState(emptyList())
    val popularMovies = viewModel.popularMovies.observeAsState(emptyList())
    val upcomingMovies = viewModel.upcomingMovies.observeAsState(emptyList())
    val nowPlayingMovies = viewModel.nowPlayingMovies.observeAsState(emptyList())
    val topRatedMovies = viewModel.topRatedMovies.observeAsState(emptyList())

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(Modifier.verticalScroll(dashboardContentScrollState)) {
            // Banner Image.
            val bannerMovie = viewModel.bannerMovie.collectAsState().value
            BannerImage(bannerMovie)
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                // Trending movies today.
                LazyColumnMovieItem(listHead = "Trending Movies", movieState = trendingMovies)
                LazyColumnMovieItem(listHead = "Popular Movies", movieState = popularMovies)
                LazyColumnMovieItem(listHead = "Upcoming Movies", movieState = upcomingMovies)
                LazyColumnMovieItem(listHead = "Now Playing Movies", movieState = nowPlayingMovies)
                LazyColumnMovieItem(listHead = "Top Rated Movies", movieState = topRatedMovies)
            }
        }

        // Custom home top-bar.
        HomeTopBar(dashboardContentScrollState)
    }
}

@Composable
fun LazyColumnMovieItem(listHead: String, movieState: State<List<Movie>>) {
    Text(
        text = listHead,
        color = Color.White,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )

    HorizontalMoviesList(movieState)
    Spacer(modifier = Modifier.height(8.dp))
}