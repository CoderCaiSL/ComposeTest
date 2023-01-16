package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.ui.page.ListPageActivity
import com.example.composetest.ui.theme.ComposeTestTheme
import com.google.accompanist.insets.ProvideWindowInsets

class MainActivity : ComponentActivity() {

    lateinit var tmpValue : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme{
                MyApp()
            }
        }
    }
}

//@Preview(showBackground = true, widthDp = 320)
//@Composable
//fun DefaultPreView(){
//    ComposeTestTheme{
//        MyApp()
//    }
//}

enum class AppState {
    Splash,
    Home,
    ListUpdatePage,
    ListPage,
    NetPage,
}

@Composable
fun MyApp(modifier: Modifier = Modifier,nameList: List<String> = listOf("world","android")){

    val context = LocalContext.current

    ProvideWindowInsets{
        val (appState,setAppState) = remember {
            mutableStateOf(AppState.Home)
        }
        Column {

            TopAppBar() {
                IconButton(onClick = { setAppState(AppState.Home) }) {
                }
                Text(text = appState.name)
            }

            Column(modifier = Modifier.weight(1F)) {
                when(appState){
                    AppState.Home ->{
                        Button(modifier = Modifier.fillMaxWidth(),
                            onClick = { setAppState(AppState.ListUpdatePage) }) {
                            Text(text = "列表更新")
                        }
                        Button(modifier = Modifier.fillMaxWidth(),
                            onClick = { ListPageActivity.show(context) }) {
                            Text(text = "列表Paging")
                        }
                        Button(modifier = Modifier.fillMaxWidth(),
                            onClick = { setAppState(AppState.NetPage) }) {
                            Text(text = "网络属性")
                        }
                    }
                    AppState.ListUpdatePage ->{
                        var tmpList =  MutableList(10){"更新数据：$it"}
                        updateNameList(nameList = tmpList, onClick = {
                            tmpList.add("新增")
                        })
                    }
                    AppState.ListPage -> {
                        GreetingList(modifier)
                    }
                }
            }
        }
    }
}



@Composable
fun updateNameList(nameList: MutableList<String>, onClick: () -> Unit) {
     val personList by remember {
           derivedStateOf {
             nameList.map {
               it
              }
            }
          }
    Column {
           Button(onClick = {
             onClick.invoke()
            }) {
             Text(text = "add")
            }
           for (person in personList) {
             Text(text = person)
            }
          }
}



@Composable
fun GreetingList(
    modifier: Modifier,
    nameList: MutableList<TestBean> = MutableList(10){TestBean(tmp = "$it", boolean = false)}
){
    var itemList = remember(nameList) {
        derivedStateOf {
            nameList.map {
                it
            }
        }
    }
    LazyColumn(){
        items(items = itemList.value){ name ->
            Greeting(name, onClickAdd = {
                nameList.add(TestBean(tmp = "新增", boolean = false))
            })
        }
    }

}

data class TestBean(var boolean: Boolean,var tmp : String){

}

@Composable
fun Greeting(testBean: TestBean,onClickAdd:() -> Unit) {
    val extraPadding = if (testBean.boolean) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
//       var testBeanClone = remember {
//            mutableStateOf(testBean)
//        }
        Row {
            Column(modifier = Modifier
                .weight(1F)
                .padding(top = 10.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)) {
                Text(text = "title")
                Text(text = testBean.tmp)
                Box(Modifier.height(extraPadding))
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
                onClick = { testBean.boolean = !testBean.boolean}) {
                Text(text = if (testBean.boolean) "open" else "close")
            }
            Box(Modifier.width(20.dp)) {

            }
        }

    }

}

@Composable
fun Greeting(name: String) {
    var exp = remember {
        mutableStateOf(false)
    }
    val extraPadding = if (exp.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row {
            Column(modifier = Modifier
                .weight(1F)
                .padding(top = 10.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)) {
                Text(text = "title")
                Text(text = name)
                Box(Modifier.height(extraPadding))
            }
            OutlinedButton(
                elevation =  ButtonDefaults.elevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 15.dp,
                    disabledElevation = 0.dp,
                    hoveredElevation = 15.dp,
                    focusedElevation = 10.dp
                ),
                onClick = { exp.value = !exp.value}) {
                Text(text = if (exp.value) "open" else "close")
            }
            Box(Modifier.width(20.dp)) {

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTestTheme {
        Greeting("Android")
    }
}