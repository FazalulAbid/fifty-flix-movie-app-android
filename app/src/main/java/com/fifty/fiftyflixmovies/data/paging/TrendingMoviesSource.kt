package com.fifty.fiftyflixmovies.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fifty.fiftyflixmovies.data.remote.TMDBApi
import com.fifty.fiftyflixmovies.model.Movie
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class TrendingMoviesSource(private val api: TMDBApi) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val trendingMoviesList = api.getTrendingTodayMovies(nextPage)
            LoadResult.Page(
                data = trendingMoviesList.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (trendingMoviesList.searches.isEmpty()) null else trendingMoviesList.page - 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}