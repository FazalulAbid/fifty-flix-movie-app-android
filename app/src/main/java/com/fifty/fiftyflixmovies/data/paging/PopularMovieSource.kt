package com.fifty.fiftyflixmovies.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fifty.fiftyflixmovies.data.remote.TMDBApi
import com.fifty.fiftyflixmovies.model.Movie
import retrofit2.HttpException
import java.io.IOException

class PopularMovieSource(private val api: TMDBApi) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPage = params.key ?: 1
            val popularMovies = api.getPopularMovies(nextPage)
            LoadResult.Page(
                data = popularMovies.searches,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (popularMovies.searches.isEmpty()) null else popularMovies.page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}