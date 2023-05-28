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
//    funA {
//        funB {
//            visitA()
//        }
//    }

    var data1 = Data(3 , "123")
    println(data1.toString())
}

fun strToInt(str : String) : Int{
    val length = str.length
    var flag = 0
    var number = 0
    var temp = 1
    for (i in 0 until length){
        if(str.get(i) != ' '){
            if(flag == 0 && !(str.get(i) <= '9' && str.get(i) >= '0')){
                if(str.get(i) == '-'){
                    temp = -1
                }else {
                    return 0
                }
            }else if(str.get(i) <= '9' && str.get(i) >= '0'){
                if(flag == 0){
                    number = str.get(i) - '0'
                }else{
                    number = number * 10 + (str.get(i) - '0')
                }
                flag++
            }else{
                return number
            }
        }
        println(number)
    }
    if(number * temp > Int.MAX_VALUE){
        return Int.MAX_VALUE
    }else if(number * temp < Int.MIN_VALUE){
        return Int.MIN_VALUE
    }
    return number * temp
}
