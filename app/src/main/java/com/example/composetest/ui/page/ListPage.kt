package com.example.composetest.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.composetest.ui.viewmodel.ListViewModel
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * @author: CaiSongL
 * @date: 2023/1/16 11:18
 */

@Composable
fun ListPage(viewModel: ListViewModel = ListViewModel()){

    var lazyListModel = viewModel.getListData.collectAsLazyPagingItems()
    var lazyListState = rememberSwipeRefreshState(false)
    Box{
        SwipeRefresh(state = lazyListState, onRefresh = {
            lazyListModel.refresh()
        }) {
            LazyColumn(content = {
                itemsIndexed(lazyListModel){_,name ->
                    listItem(name!!, onClickAdd = {})
                }
                lazyListModel.apply {
                    when(loadState.append){
                        LoadState.Loading ->{
                            item { LoadingItem() }
                        }else ->{

                        }
                    }
                }
            })
        }
//        LazyColumn(state = lazyListState){
//            items(lazyListModel){
//                it.let {
//                }
//            }
//            lazyListModel.apply {
//                when(loadState.append){
//                    LoadState.Loading -> {
//                        item { LoadingItem() }
//                    }
//                }
//            }
//        }
    }

}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        color = Color(0xFF7F6351),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun listItem(name : String,onClickAdd:() -> Unit) {
    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {

        Row {
            Column(modifier = Modifier
                .weight(1F)
                .padding(top = 10.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)) {
                Text(text = "title")
                Text(text = name)
            }
            OutlinedButton(
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                ),
                onClick = { onClickAdd()}) {
                Text(text = "添加")
            }
            Box(Modifier.width(10.dp)) {}
            OutlinedButton(
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                ),
                onClick = { }) {
                Text(text = "open")
            }
            Box(Modifier.width(20.dp)) {

            }
        }

    }

}
