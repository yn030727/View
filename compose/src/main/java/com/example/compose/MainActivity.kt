package com.example.compose

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.ui.theme.Message
import com.example.compose.ui.theme.SampleData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MaterialTheme() {
//                Conversation(messages = SampleData.conversationSample)
//            }


            Column {
                AndroidView(factory = {context ->
                    WebView(context).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = WebViewClient()
                        loadUrl("https://jetpackcompose.cn/")
                    }
                } , modifier = Modifier.fillMaxSize())

                Image(painter = painterResource(id = R.drawable.ning),
                    contentDescription = null ,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape))
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

            var isExpanded by remember {
                mutableStateOf(false)
            }

            val surfaceColor by animateColorAsState(targetValue =
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface) {
            }

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(text = "Hello ${message.author}!" ,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                androidx.compose.material.Surface(shape = MaterialTheme.shapes.medium , elevation = 1.dp , color = surfaceColor , modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
                ) {
                    Text(text = "Hello ${message.body}!",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded) Int.MAX_VALUE else 1
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
    
    @Preview(showBackground = true)
    @Composable
    fun PreviewConversation(){
        MaterialTheme(){
            Conversation(messages = SampleData.conversationSample)
        }
    }

    
    @Composable
    fun ModifierBackground(){
        Row{
            Box(modifier = Modifier
                .size(50.dp)
                .background(color = Color.Red)){
                Text(text = "纯色" , Modifier.align(Alignment.Center))
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                Modifier
                    .size(50.dp)
                    .background(brush = verticalGradientBrush)){
                Text(text = "渐变色" , Modifier.align(Alignment.Center))
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun PreviewModifierBackground(){
        ModifierBackground()
    }

    //创建Brush渐变色
    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color.Red,
            Color.Yellow,
            Color.White
        )
    )

    @Composable
    fun ModifierBorderPadding(){
        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(2.dp, Color.Red, shape = RoundedCornerShape(2.dp))
                .padding(8.dp)
        ){
            Spacer(modifier = Modifier
                .size(width = 100.dp, height = 10.dp)
                .background(Color.Red)
            )
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun PreviewModifierBorderPadding(){
        ModifierBorderPadding()
    }
}

