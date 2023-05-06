package com.example.kotlin

class TestRunnable(num : Int , str : String) : Runnable {
    init {
        println("str is $str , num is $num")
    }
    override fun run() {
        println("Hello World")
    }

}