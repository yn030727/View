package com.example.compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.Message
import com.example.compose.ui.theme.SampleData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme() {
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }




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
                    .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
            
            )
            
            //在图片和垂直文本块之间添加8dp的空白距离
            Spacer(modifier = Modifier.width(8.dp))
            
            Column() {
                Text(text = "Hello ${message.author}!" ,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                androidx.compose.material.Surface(shape = MaterialTheme.shapes.medium , elevation = 1.dp) {
                    Text(text = "Hello ${message.body}!",
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(all = 4.dp)
                    )
                }
            }
        }
    }


    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun previewMessageCard(){
        MaterialTheme() {
            androidx.compose.material.Surface() {
                MessageCard(message = Message("Colleague" , "Hey , take a look at Jetpack Compose !"))
            }
        }
    }
    
    
    @Composable
    fun Conversation(messages: List<com.example.compose.ui.theme.Message>){
        LazyColumn{
            items(messages){ message ->
                MessageCard(message = message)
            }
        }
    }
    
    @Preview
    @Composable
    fun PreviewConversation(){
        MaterialTheme(){
            Conversation(messages = SampleData.conversationSample)
        }
    }
    
}

