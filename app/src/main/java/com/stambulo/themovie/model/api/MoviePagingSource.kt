package com.stambulo.themovie.model.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.stambulo.themovie.model.entity.VideoItem
import retrofit2.HttpException

class MoviePagingSource(private val api: Repository) : PagingSource<Int, VideoItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, VideoItem> {
        return try {
            val nextPage = params.key ?: 1
            val response = api.getMovies(nextPage)
            if (response.isSuccessful) {
                LoadResult.Page(
                    data = response.body()!!.results,
                    prevKey = if (nextPage == 1) null else response.body()!!.page - 1,
                    nextKey = if (nextPage > response.body()!!.total_pages) {
                        null
                    } else {
                        response.body()!!.page + 1
                    },
                )
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, VideoItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
