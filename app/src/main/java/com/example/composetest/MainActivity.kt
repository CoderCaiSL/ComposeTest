package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest.ui.theme.ComposeTestTheme

class MainActivity : ComponentActivity() {
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

@Composable
fun MyApp(modifier: Modifier = Modifier,nameList: List<String> = listOf("world","android")){
//    Column(modifier) {
//        for (tmp in nameList){
//            Greeting(name = tmp)
//        }
//    }
    GreetingList(modifier)
}

@Composable
fun GreetingList(
    modifier: Modifier,
    nameList: List<TestBean>  = List(1000){TestBean(tmp = "$it", boolean = false)}
){
    LazyColumn(){
        items(items = nameList){ name ->
            Greeting(name)
        }
    }

}

data class TestBean(var boolean: Boolean,var tmp : String){

}

@Composable
fun Greeting(testBean: TestBean) {
    val extraPadding = if (testBean.boolean) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary, modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
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