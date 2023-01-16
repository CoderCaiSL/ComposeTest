package com.example.composetest.ui.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.composetest.ui.theme.ComposeTestTheme

/**
 * @author: CaiSongL
 * @date: 2023/1/16 17:27
 */
class ListPageActivity : ComponentActivity() {

    companion object{
        fun show(context: Context){
            context.startActivity(Intent(context,ListPageActivity::class.java))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme{
                ListPage()
            }
        }
    }
}

