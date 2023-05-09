package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.ui.theme.ViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageCard(Message("Android" , "Jetpack Compose"))
        }
    }

    //将两个参数封装成一个Message
    //author和body是主构造方法传入的两个参数
    data class Message(val author : String , val body : String)


    @Composable
    fun MessageCard(message : Message){
        Row{
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "contact profile picture"
            )
            Column() {
                Text(text = "Hello ${message.author}!")
                Text(text = "Hello ${message.body}!")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun previewMessageCard(){
        MessageCard(message = Message("Colleague" , "Hey , take a look at Jetpack Compose !"))
    }
    
}

