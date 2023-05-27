package com.example.compose.ui.theme

class AScope{
    fun visitA(){}
}

class BScope{
    fun visitB(){}
}

//高阶函数，传进来的高阶函数叫做scope
fun funA(scope : AScope.() -> Unit){
    scope(AScope())
}

fun funB(scope: BScope.() -> Unit){
    scope(BScope())
}

fun main(){
    funA {
        funB {
            visitA()
        }
    }
}