package com.example.composetest.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlin.properties.Delegates

/**
 * 模拟网络操作的paging
 * @author: CaiSongL
 * @date: 2023/1/16 11:19
 */
class ListViewModel : ViewModel(){

    var pageSizeValue by Delegates.notNull<Int>()

    val getListData : Flow<PagingData<String>> =
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 1)){
            ListMovieSouce()
        }.flow

}