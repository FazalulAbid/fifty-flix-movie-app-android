package com.fifty.fiftyflixmovies.util

import com.fifty.fiftyflixmovies.data.model.MovieCategory

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val STARTING_PAGE_INDEX = 0
    const val IMAGE_BASE_UR = "https://image.tmdb.org/t/p/w500/"
    const val API_LANGUAGE = "en"
    const val DATABASE_NAME = "favorites_database"
    const val TABLE_NAME = "favorites_table"
    const val SPLASH_SCREEN_DURATION = 2000L
    const val MOVIE_CACHE_LIFETIME_IN_MINUTES = 30

    // Database table names.
    const val MOVIES_TABLE = "movies"
    const val GENRES_TABLE = "genres"
    const val MOVIE_CATEGORIES_TABLE = "movie_categories"
    const val THUMBNAIL_TABLE = "thumbnail"

    // Movies categories.
    const val TRENDING_MOVIES_ID = 1001
    const val POPULAR_MOVIES_ID = 1002
    const val UPCOMING_MOVIES_ID = 1003
    const val NOW_PLAYING_MOVIES_ID = 1004
    const val TOP_RATED_MOVIES_ID = 1005

    val movieCategories = listOf(
        MovieCategory(TRENDING_MOVIES_ID, "Trending Movies"),
        MovieCategory(POPULAR_MOVIES_ID, "Popular Movies"),
        MovieCategory(UPCOMING_MOVIES_ID, "Upcoming Movies"),
        MovieCategory(NOW_PLAYING_MOVIES_ID, "Now Playing Movies"),
        MovieCategory(TOP_RATED_MOVIES_ID, "Top Rated Movies"),
    )
}