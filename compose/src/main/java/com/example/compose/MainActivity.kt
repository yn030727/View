package com.example.compose

import android.content.Context
import android.content.res.Configuration
import android.media.MediaRouter.UserRouteInfo
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
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MaterialTheme() {
//                Conversation(messages = SampleData.conversationSample)
//            }


//            Column {
//                AndroidView(factory = {context ->
//                    WebView(context).apply {
//                        settings.javaScriptEnabled = true
//                        webViewClient = WebViewClient()
//                        loadUrl("https://jetpackcompose.cn/")
//                    }
//                } , modifier = Modifier.fillMaxSize())
//
//                Image(painter = painterResource(id = R.drawable.ning),
//                    contentDescription = null ,
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clip(CircleShape))
//            }

            IconSample()
        }
    }


    @Composable
    fun MessageCard(message: Message) {
        //在我们的Message之间添加间距
        Row(modifier = Modifier.padding(all = 8.dp)) {

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

            val surfaceColor by animateColorAsState(
                targetValue =
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
            ) {
            }

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = "Hello ${message.author}!",
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                androidx.compose.material.Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = "Hello ${message.body}!",
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1
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
    fun previewMessageCard() {
        MaterialTheme() {
            androidx.compose.material.Surface() {
                MessageCard(
                    message = Message(
                        "Colleague",
                        "Hey , take a look at Jetpack Compose !"
                    )
                )
            }
        }
    }


    @Composable
    fun Conversation(messages: List<com.example.compose.ui.theme.Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message = message)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewConversation() {
        MaterialTheme() {
            Conversation(messages = SampleData.conversationSample)
        }
    }


    @Composable
    fun ModifierBackground() {
        Row {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(color = Color.Red)
            ) {
                Text(text = "纯色", Modifier.align(Alignment.Center))
            }

            Spacer(modifier = Modifier.width(10.dp))

            Box(
                Modifier
                    .size(50.dp)
                    .background(brush = verticalGradientBrush)
            ) {
                Text(text = "渐变色", Modifier.align(Alignment.Center))
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewModifierBackground() {
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
    fun ModifierBorderPadding() {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .border(2.dp, Color.Red, shape = RoundedCornerShape(2.dp))
                .padding(8.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .size(width = 100.dp, height = 10.dp)
                    .background(Color.Red)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewModifierBorderPadding() {
        ModifierBorderPadding()
    }

    @Composable
    fun ModifierBox() {
        Box {

        }
    }

    @Composable
    fun MatchParentModifierDemo() {
        Box {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.LightGray)
            )
        }
    }


    @Composable
    fun WeightModifierDemo() {
        Column(
            modifier = Modifier
                .width(300.dp)
                .height(200.dp)
        ) {

//            Box(modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//                .background(Color.White))
//            Box(modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth()
//                .background(Color.White)) //Blue
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) { //Red

//                //指定字符串
//                Text(text = "Android111111111111111111")
//                //指定文字资源
//                Text(text = stringResource(id = R.string.app_name))
                Column {
                    Text(
                        text = "Hello World\n" + "Goodbye World",
                        style = TextStyle(
                            fontSize = 25.sp, //字体大小
                            fontWeight = FontWeight.Bold, //字体粗细
                            background = Color.Cyan, //背景
                            lineHeight = 35.sp //行高
                        )
                    )

                    Text(
                        text = "Hello World",
                        style = TextStyle(
                            color = Color.Gray,
                            letterSpacing = 4.sp //字体间距
                        )
                    )

                    Text(
                        text = "Hello World",
                        style = TextStyle(
                            textDecoration = TextDecoration.LineThrough
                        )
                    )

                    Text(
                        text = "Hello World",
                        style = MaterialTheme.typography.h6.copy(fontStyle = FontStyle.Italic)
                    )
                }
            }
        }
    }

    @Composable
    fun maxLinesTextDemo() {
        Column {
            Text(
                text = "你好世界，我正在使用Jetpack Compose框架来开发我的App界面",
                style = MaterialTheme.typography.body1,
            )

            Text(
                text = "你好世界，我正在使用Jetpack Compose框架来开发我的App界面",
                style = MaterialTheme.typography.body1,
                maxLines = 1
            )

            Text(
                text = "你好世界，我正在使用Jetpack Compose框架来开发我的App界面",
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

    @Composable
    fun fontFamilyTextDemo() {
        Column {
            Text("Hello World")
            Text("Hello World", fontFamily = FontFamily.Monospace)
            Text("Hello World", fontFamily = FontFamily.Cursive)

        }
    }

    @Composable
    fun AnnotatedStringTextDemo() {
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(fontSize = 24.sp)) {
                append("你现在学习的章节是")
            }

            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.W900,
                    fontSize = 24.sp
                )
            ) {
                append("Text")
            }

            append("\n")

            withStyle(style = ParagraphStyle(lineHeight = 25.sp)) {
                append("在刚刚讲过的内容中，我们学会了如何应用文字样式，以及如何限制文本的行数和处理溢出的视觉效果。")
                append("\n")
                append("现在，我们正在学习")

                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.W900,
                        textDecoration = TextDecoration.Underline,
                        color = Color(0xFF59A869)
                    )
                ) {
                    append("AnnotatedString")
                }
            }

        }
        )
    }

    @Composable
    fun ClickableTextDemo() {
//            ClickableText

        SelectionContainer {
            Text("我是可以被复制的文字")

        }
    }

    @Composable
    fun TextFieldSample() {
        var text by remember {
            mutableStateOf("")
        }

        TextField(
            value = text,
            onValueChange = {
                text = it
            },
            label = { Text(text = "用户名") },
            modifier = Modifier.height(30.dp)
        )
        BasicTextField(value = "1", onValueChange = {})
    }

    @Composable
    fun TextFieldSample2() {
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Column {
            TextField(
                value = username,
                onValueChange = {
                    username = it
                },
                label = {
                    Text(text = "用户名")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = "username"
                    )
                }
            )

            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = {
                    Text(text = "密码")
                },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.description),
                            contentDescription = "password"
                        )
                    }
                }
            )
        }
    }


    @Composable
    fun OutlineTextFieldSample() {
        var username by remember {
            mutableStateOf("")
        }
        OutlinedTextField(
            value = username,
            onValueChange = {
                username = it
            },
            label = {
                Text(text = "用户名")
            }
        )
    }

    @Composable
    fun BasicTextFieldSample() {
        var text by remember {
            mutableStateOf("")
        }

        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            decorationBox = { innerTextField ->
                Column { //Column让里面的元素从上到下排列
                    innerTextField()

                    Divider( //这个组件的作用是创建一条分割线
                        thickness = 2.dp, //分割线的粗度
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                    )
                }
            }
        )

    }

    @Composable
    fun SearchBar(){
        var text by remember {
            mutableStateOf("")
        }

        Box(
            //填满父布局
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFD3D3D3)),
            //将Box里面的组件放置于Box容器的中央
            contentAlignment = Alignment.Center
        ){
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 2.dp , horizontal = 8.dp)
                    ){

                        //添加前面的搜索图标
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "SearchIcon")
                        //添加Box帧布局，显示Placeholder文字。
                        //Box帧布局的作用是让innerTextField和Placeholder显示在同一个位置
                        Box (
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            contentAlignment = Alignment.CenterStart //设置子元素为竖直方向上的中间，水平方向上的最左边。
                        ){
                            if(text.isEmpty()){
                                Text(
                                    text = "输入点东西看看吧~",
                                    style = TextStyle(
                                        color = Color(0 , 0 , 0 , 128)
                                    )
                                )
                            }
                            innerTextField()
                        }

                        Box(modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ){
                            if(text.isNotEmpty()){
                                IconButton(
                                    onClick = { text = ""},
                                    modifier = Modifier.size(16.dp),

                                    ) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "CancelIcon")
                                }
                            }
                        }
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .background(Color.White, CircleShape)
                    .height(30.dp)
                    .fillMaxWidth()
            )
        }
    }

    @Composable
    fun IconDemo(){
//        //Icon(painter = , contentDescription = )
//        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background), contentDescription = "矢量图资源")
//        Icon(bitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_background), contentDescription = "图片资源")
//        Icon(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "任意类型资源")
        //Image(painter = , contentDescription = )

    }

    @Composable
    fun IconSample(){
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color.Red
        )
    }

}

