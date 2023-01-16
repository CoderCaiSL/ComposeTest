package com.example.composetest.ui.viewmodel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import java.lang.Exception

/**
 * @author: CaiSongL
 * @date: 2023/1/16 11:28
 */
class ListMovieSouce : PagingSource<Int, String>() {

    val pageSize = 20

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {

        return try {
            val nextPage = params.key ?: 1
            val tmpListData = MutableList(pageSize){"测试数据{$it}:${System.currentTimeMillis()}"}
            if (nextPage > 1) {
                delay(2000)
            }
            LoadResult.Page(
                data = tmpListData,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1,
            )

        }catch (e : Exception){
            LoadResult.Error(e)
        }
    }
}