package com.example.compose2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
            CreateRefDemo2()
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


}
