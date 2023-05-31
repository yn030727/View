package com.example.compose

import android.app.AlertDialog
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
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material.icons.filled.*
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout

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
            SpacerDemo()
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
    fun SearchBar() {
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
        ) {
            BasicTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 2.dp, horizontal = 8.dp)
                    ) {

                        //添加前面的搜索图标
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "SearchIcon"
                        )
                        //添加Box帧布局，显示Placeholder文字。
                        //Box帧布局的作用是让innerTextField和Placeholder显示在同一个位置
                        Box(
                            modifier = Modifier
                                .padding(horizontal = 10.dp),
                            contentAlignment = Alignment.CenterStart //设置子元素为竖直方向上的中间，水平方向上的最左边。
                        ) {
                            if (text.isEmpty()) {
                                Text(
                                    text = "输入点东西看看吧~",
                                    style = TextStyle(
                                        color = Color(0, 0, 0, 128)
                                    )
                                )
                            }
                            innerTextField()
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            if (text.isNotEmpty()) {
                                IconButton(
                                    onClick = { text = "" },
                                    modifier = Modifier.size(16.dp),

                                    ) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "CancelIcon"
                                    )
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
    fun IconDemo() {
//        //Icon(painter = , contentDescription = )
//        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background), contentDescription = "矢量图资源")
//        Icon(bitmap = ImageBitmap.imageResource(id = R.drawable.ic_launcher_background), contentDescription = "图片资源")
//        Icon(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "任意类型资源")
        //Image(painter = , contentDescription = )

    }

    @Composable
    fun IconSample() {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = null,
            tint = Color.Red
        )
    }

    @Composable
    fun ButtonSample() {
        Button(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "确认",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )

            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))

            Text(text = "确认")
        }
    }

    @Composable
    fun InteractionSourceDemo() {
        val interactionSource = remember {
            MutableInteractionSource()
        }
        //判断是否按下状态，返回0或1
        val pressState = interactionSource.collectIsPressedAsState()
        //通过0和1，设置不同的颜色
        val borderColor = if (pressState.value) Color.Green else Color.White

        Button(
            onClick = {},
            border = BorderStroke(2.dp, color = borderColor),
            interactionSource = interactionSource
        ) {
            Text("Long Press")
        }
    }

    @Composable
    fun IconButtonSample() {
//        IconButton(
//            onClick = {},
//        ) {
//            Icon(
//                imageVector = Icons.Filled.Favorite,
//                contentDescription = "图标按钮"
//            )
//        }
//

//        FloatingActionButton(
//            onClick = {}
//        ) {
//            Icon(
//                imageVector = Icons.Filled.ArrowForward,
//                contentDescription = null
//            )
//        }

        ExtendedFloatingActionButton(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = null
                )
            },
            text = {
                Text(text = "添加到我喜欢的")
            },
            onClick = { /*TODO*/ }
        )
    }

    @Composable
    fun checkBoxSample() {
        val checkedState = remember {
            mutableStateOf(true)
        }

        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF0079D3)
            )
        )
    }

    @Composable
    fun TriStateCheckboxSample() {
        //为两个CheckBox定义状态
        //这是Kotlin的解构声明,这里理解成同时声明两个变量
        val (state, onStateChange) = remember {
            mutableStateOf(true)
        }
        val (state2, onStateChange2) = remember {
            mutableStateOf(true)
        }

        //根据子CheckBox的状态来设置TriStateCheckbox的状态
        val parentState = remember(key1 = state, key2 = state2) {
            if (state && state2) ToggleableState.On  //中间打钩状态
            else if (!state && !state2) ToggleableState.Off  //不选中状态
            else ToggleableState.Indeterminate  //中间是横杠状态
        }

        //TriStateCheckbox可以为从属的复选框设置状态
        val onParentClick = {
            val s = parentState != ToggleableState.On
            onStateChange(s)
            onStateChange2(s)
        }

        TriStateCheckbox(
            state = parentState,
            onClick = onParentClick,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primary
            )
        )

        Column(
            modifier = Modifier.padding(0.dp, 50.dp, 0.dp, 0.dp)
        ) {
            Checkbox(checked = state, onCheckedChange = onStateChange)
            Checkbox(checked = state2, onCheckedChange = onStateChange2)
        }
    }

    @Composable
    fun SwitchDemo() {
        val checkState = remember {
            mutableStateOf(true)
        }
        Switch(
            checked = checkState.value,
            onCheckedChange = { checkState.value = it }
        )
    }

    @Composable
    fun textFieldStateHasTextShow() {
        var value by remember {//这里就是对TextField中展示的文字进行状态保存的操作
            mutableStateOf("")
        }
        Box(modifier = Modifier.fillMaxSize(1f), contentAlignment = Alignment.Center) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    value = it//每次输入内容的时候，都回调这个更新状态，从而刷新重组ui
                },
                label = { Text("Name") }
            )
        }
    }


    @Composable
    fun SliderDemo() {
        var sliderPosition by remember {
            mutableStateOf(0f)
        }

        Text(text = "%.1f".format(sliderPosition * 100) + "%")
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
            }
        )
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    fun DialogDemo() {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
                dismissOnClickOutside = true
            )
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                color = Color.Gray
            ) {
                Text(text = "Hello World")
            }
        }
    }

    @Composable
    fun DialogSample() {
        val openDialog = remember {
            mutableStateOf(true)
        }
        val dialogWidth = 200.dp
        val dialogHeight = 50.dp

        if (openDialog.value) {
            Dialog(
                //当我们点击Dialog以外区域，修改openDialog状态为false
                //触发DialogSample的重组
                //此时Dialog无法被执行，对话框消失
                onDismissRequest = {
                    openDialog.value = false
                }
            ) {
                Box(
                    modifier = Modifier
                        .size(dialogWidth, dialogHeight)
                        .background(Color.White)
                )
            }
        }
    }

    @Composable
    fun AlertDialogSample() {
        val openDialog = remember {
            mutableStateOf(true)
        }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = {
                    Text(text = "开启位置服务")
                },
                text = {
                    Text(text = "这将意味着，我们会将您提供精准的位置服务，并且您将接受关于您订阅的位置信息")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            //其他需要执行的业务需求
                        }
                    ) {
                        Text(text = "同意")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text(text = "取消")
                    }
                }
            )
        }
    }

    @Composable
    fun ProgressDemo() {
        //创建一个进度值
        var progress by remember {
            mutableStateOf(0.1f)
        }

        //创建一个动画，根据progress变量
        val animatedProgress by animateFloatAsState(
            targetValue = progress,
            animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
        )

        Column {
            LinearProgressIndicator(progress = animatedProgress)

            //圆形进度条指示器
            Spacer(modifier = Modifier.requiredHeight(30.dp))

            OutlinedButton(
                onClick = {
                    if (progress < 1f) progress += 0.1f
                }
            ) {
                Text(text = "增加高度")
            }
        }
    }

    @Composable
    fun ColumnDemo() {
        Column(
            modifier = Modifier
                .border(1.dp, Color.Black)
                .size(150.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Hello , World",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(text = "Jetpack Compose")
        }
    }

    @Composable
    fun RowDemo() {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                //设置Surface组件的外边距
                .padding(horizontal = 12.dp, vertical = 12.dp)
                .fillMaxWidth(),
            elevation = 10.dp
        ) {

            Column(
                modifier = Modifier
                    //里面内容的外边距
                    .padding(12.dp)
            ) {

                Text(
                    text = "Jetpack Compose是什么？",
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.padding(vertical = 5.dp))

                Text(
                    text = "Jetpack Compose 是用于构建原生Android界面的新工具包，它可简化并加快Android上的界面开发，使用更少的代码、强大的工具和直观Kotlin API,让应用生动而精彩。"
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Call, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Share, contentDescription = null)
                    }
                }
            }
        }
    }

    @Composable
    fun BoxDemo() {
        Box {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Red)
            )
            Text(text = "世界")
        }
    }

    @Composable
    fun SurfaceDemo() {
        Surface(
            shape = RoundedCornerShape(8.dp), 
            elevation = 10.dp,
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
        ) {
            
            Row(
                modifier = Modifier.clickable { }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ning),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Crop
                )
                
                Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Liratie" , 
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(modifier = Modifier.padding(vertical = 8.dp))
                    Text(text = "礼谙")
                }
            }
            
        }
    }

    @Composable
    fun SpacerDemo(){
        Row {
           Box(
               Modifier
                   .size(100.dp)
                   .background(Color.Red))

           Spacer(modifier = Modifier.width(20.dp))

           Box(
               Modifier
                   .size(100.dp)
                   .background(Color.Magenta))

           Spacer(modifier = Modifier.weight(1f))

           Box(modifier = Modifier
               .size(100.dp)
               .background(Color.Black))
        }
    }
    
    @Composable
    fun ConstraintLayoutDemo(){
        ConstraintLayout() {
            
        }
    }

}


