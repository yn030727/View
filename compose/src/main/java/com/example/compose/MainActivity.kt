package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.ViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme() {
                androidx.compose.material.Surface(modifier = Modifier.fillMaxSize()) {
                    MessageCard(Message("Android" , "Jetpack Compose"))
                }
            }
        }
    }

    //将两个参数封装成一个Message
    //author和body是主构造方法传入的两个参数
    data class  Message(val author : String , val body : String)


    @Composable
    fun MessageCard(message : Message){
        //在我们的Message之间添加间距
        Row(modifier = Modifier.padding(all = 8.dp)){

            Image(
                painter = painterResource(id = R.drawable.ning),
                contentDescription = "contact profile picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp , MaterialTheme.colors.secondary , CircleShape)
            )
            
            //在图片和垂直文本块之间添加8dp的空白距离
            Spacer(modifier = Modifier.width(8.dp))
            
            Column() {
                Text(text = "Hello ${message.author}!" ,
                    color = MaterialTheme.colors.secondaryVariant
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "Hello ${message.body}!")
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun previewMessageCard(){
        MaterialTheme() {
            androidx.compose.material.Surface() {
                MessageCard(message = Message("Colleague" , "Hey , take a look at Jetpack Compose !"))
            }
        }
    }
    
}

