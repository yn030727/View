package com.example.compose2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.compose2.ui.theme.ViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuidelineDemo()
        }
    }


    @Composable
    fun CreateRefDemo() {
        ConstraintLayout() {
            //创建一个引用
            val portraitImageRef = remember {
                createRef()
            }
            val usernameTextRef = remember {
                createRef()
            }
            //创建多个引用
            val (portraitImageRef1, usernameTextRef1) = remember {
                createRefs()
            }
        }
    }

    @Composable
    fun CreateRefDemo2() {
        ConstraintLayout(
            modifier = Modifier
                .width(300.dp)
                .height(100.dp)
                .padding(10.dp)
        ) {
            val portraitImageRef = remember {
                createRef()
            }

            Image(
                painter = painterResource(id = R.drawable.ning),
                contentDescription = null,
                modifier = Modifier.constrainAs(portraitImageRef) {
                    //指定组件的约束布局
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
            )

        }
    }

    @Composable
    fun DimensionConstraintDemo() {
        ConstraintLayout() {
            //创建一个引用
            val portraitImageRef = remember {
                createRef()
            }
            val usernameTextRef = remember {
                createRef()
            }
            //创建多个引用
            val (portraitImageRef1, usernameTextRef1) = remember {
                createRefs()
            }
            Text(
                text = "一个名字特别特别特别特别特别特别长的用户名",
                fontSize = 16.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .constrainAs(usernameTextRef){
                        top.linkTo(portraitImageRef.top)
                        start.linkTo(portraitImageRef.end , 10.dp)
                        end.linkTo(parent.end , 10.dp)
                        width = Dimension.preferredWrapContent
                    }

            )
        }
    }

    @Composable
    fun ConstraintBarrierDemo(){
        ConstraintLayout() {
            val (usernameTextRef , passwordTextRef , usernameInputRef , passwordInputRef , dividerRef) = remember {
                createRefs()
            }
            //使用createEndBarrier创建一条结尾分界线
            //此时分界线位置位于两个文本中较长文本的结尾处
            var barrier = createEndBarrier(usernameTextRef , passwordTextRef)

            //接下来设置输入框的约束信息
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .constrainAs(usernameInputRef){
                        start.linkTo(barrier , 10.dp) //指定起始位置
                        top.linkTo(usernameTextRef.top)
                        bottom.linkTo(usernameTextRef.bottom)
                        height = Dimension.fillToConstraints
                    }
            )
        }
    }


    @Composable
    fun GuidelineDemo(){
        ConstraintLayout() {
            val guideline = createGuidelineFromTop(0.2f)
            val (userPortraitBackgroundRef , userPortraitImgRef) = remember {
                createRefs()
            }

            Box(modifier = Modifier
                .constrainAs(userPortraitBackgroundRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(guideline)
                    height = Dimension.fillToConstraints
                    width = Dimension.matchParent
                }
                .background(Color(0xFF1E9FFF))
            )

            Image(
                painter = painterResource(id = R.drawable.ning),
                contentDescription = null ,
                modifier = Modifier
                    .constrainAs(userPortraitImgRef) {
                        top.linkTo(guideline)
                        bottom.linkTo(guideline)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color(0xFF5FB878), shape = CircleShape)
            )
        }
    }

}
